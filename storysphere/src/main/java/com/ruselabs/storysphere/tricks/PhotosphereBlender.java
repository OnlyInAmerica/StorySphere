package com.ruselabs.storysphere.tricks;

import rajawali.materials.textures.Texture;
import rajawali.math.Quaternion;

/**
 * Toggle the influence of two textures based on the camera yaw position.
 *
 *  @author David Brodsky (dbro@dbro.pro)
 */
public class PhotosphereBlender {

    /** Show second texture when absolute camera yaw is greater than this value */
    private double mSecondTextureAngle = Math.PI / 2;
    /** Fade the two textures between mSecondTextureAngle and (mSecondTextureAngle + mFadeAngle) */
    private double mFadeAngle = Math.PI / 8;

    public PhotosphereBlender() {}

    /**
     * Create a Photosphere blender that transitions the view between two Photospheres
     * as the camera yaw varies.
     *
     * @param cutoffAngleRadians When the absolute value of the camera yaw is less than this value,
     *                           the first texture is shown. Else the second texture is shown.
     * @param fadeAngleRadians The angle after cutoffAngleRadians to blend between the first and second texture.
     */
    public PhotosphereBlender(double cutoffAngleRadians, double fadeAngleRadians) {
        mSecondTextureAngle = cutoffAngleRadians;
        mFadeAngle = fadeAngleRadians;
    }

    /**
     * Calculate the influence of two textures based on camera Yaw.
     *
     * If the absolute value of the camera yaw is less than {@link #mSecondTextureAngle},
     * show firstTexture.
     *
     * If the absolute value of the camera yaw is between {@link #mSecondTextureAngle} and
     * {@link #mSecondTextureAngle} + {@link #mFadeAngle} show a blend of the two textures.
     *
     * If the absolute value of camera yaw is greater than {@link #mSecondTextureAngle} show
     * secondTexture.
     *
     * Should be called by {@link rajawali.renderer.RajawaliRenderer#onRender(double)}
     */
    public void blendPhotosphereByYaw(Quaternion cameraOrientation, Texture firstTexture, Texture secondTexture) {
        double mCameraYawAngle =  Math.abs(cameraOrientation.getYaw(true));
        if (mCameraYawAngle <= mSecondTextureAngle) {
            // Show first texture
            secondTexture.setInfluence(0);
            firstTexture.setInfluence(1);
        } else if (mCameraYawAngle > mSecondTextureAngle + mFadeAngle) {
            // Show second texture
            secondTexture.setInfluence(1);
            firstTexture.setInfluence(0);
        } else {
            // Perform fade
            float fade = (float) (Math.abs(mCameraYawAngle - mSecondTextureAngle) / mFadeAngle);
            secondTexture.setInfluence(fade);
            firstTexture.setInfluence(1 - fade);
        }
    }
}
