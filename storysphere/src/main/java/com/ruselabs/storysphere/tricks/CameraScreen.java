package com.ruselabs.storysphere.tricks;

import android.graphics.SurfaceTexture;
import android.hardware.Camera;

import rajawali.Object3D;
import rajawali.materials.Material;
import rajawali.materials.textures.ATexture;
import rajawali.materials.textures.VideoTexture;
import rajawali.primitives.Plane;

/**
 * Created by davidbrodsky on 10/5/14.
 */
public class CameraScreen {

    Plane mVideoScreen;
    VideoTexture mVideoTexture;
    Material mVideoMaterial;
    Camera mCamera;


    public CameraScreen(Camera camera, int initialHeight, int initialWidth, double x, double y, double z) {
        try {
            mCamera = camera;
            mVideoTexture = new VideoTexture("camera", mCamera, new SurfaceTexture.OnFrameAvailableListener() {
                @Override
                public void onFrameAvailable(SurfaceTexture surfaceTexture) {
                    // do nothing
                }
            });

            mVideoScreen = new Plane(initialWidth, initialHeight, 5, 5);
            mVideoScreen.setPosition(x, y, z);
//            mVideoScreen.setRotY(90);
            mVideoScreen.setDoubleSided(true);
            mVideoMaterial = new Material();
            mVideoMaterial.addTexture(mVideoTexture);
            mVideoMaterial.setColorInfluence(0f);

            mVideoScreen.setMaterial(mVideoMaterial);

        } catch (ATexture.TextureException e) {
            e.printStackTrace();
        }
    }

    public Object3D getScreen() {
        return mVideoScreen;
    }

    public void advanceFrame() {
        mVideoTexture.update();
    }
}
