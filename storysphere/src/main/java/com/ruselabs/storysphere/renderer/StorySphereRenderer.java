package com.ruselabs.storysphere.renderer;

import android.content.Context;
import android.media.MediaPlayer;

import com.ruselabs.storysphere.tricks.VideoScreen;

import rajawali.materials.Material;
import rajawali.materials.textures.Texture;
import rajawali.materials.textures.VideoTexture;
import rajawali.primitives.Plane;
import rajawali.primitives.Sphere;
import rajawali.vr.RajawaliVRRenderer;
import rajawali.vr.example.R;

/**
 * This class handles all things graphics.
 *
 * Here we initialize the 3D scene via {@link #initScene()} and update
 * the scene on each new frame via {@link #onRender(double)}
 *
 *  @author David Brodsky (dbro@dbro.pro)
 */
public class StorySphereRenderer extends RajawaliVRRenderer {
    public static final String TAG = "StorySphereRenderer";

    /** Photosphere textures */
    private Texture mFirstTexture;
    private Texture mSecondTexture;

    private VideoScreen mVideoScreen;
//    private PhotosphereBlender mBlender;

    public StorySphereRenderer(Context context) {
        super(context);
        setFrameRate(60);
//        mBlender = new PhotosphereBlender();
        mVideoScreen = new VideoScreen(mContext, R.raw.bday, 16, 9);
    }

    @Override
    public void initScene() {
        try {
            // If we could convert the Photospheres into skybox graphics, we might
            // be able to get away with higher-resolution textures!

            //getCurrentScene().setSkybox(R.drawable.posx, R.drawable.negx, R.drawable.posy, R.drawable.negy, R.drawable.posz, R.drawable.negz);

            /** These values don't have a great significance if we're not placing objects within the sphere */
            final int PHOTOSPHERE_RADIUS = 50;
            final int PHOTOSPHERE_SEGMENTS_VERTICAL = 24;
            final int PHOTOSPHERE_SEGMENTS_HORIZONTAL = 24;

            /** Create Photosphere */
            getCurrentCamera().setPosition(0,0,0);
            Sphere sphere = new Sphere(PHOTOSPHERE_RADIUS, PHOTOSPHERE_SEGMENTS_HORIZONTAL, PHOTOSPHERE_SEGMENTS_VERTICAL);
            sphere.setPosition(0, 0, 0);
            sphere.setDoubleSided(true);

            /** Setup video */
            getCurrentScene().addChild(mVideoScreen.getScreen());


            mFirstTexture = new Texture("room", R.drawable.living_room_4096);
//            mFirstTexture = new Texture("hawaii", R.drawable.hawaii_4096);
//            mSecondTexture = new Texture("brc", R.drawable.brc_4096);
//            mSecondTexture.setInfluence(0f);
            Material material = new Material();

            // It may be better to bundle ETC1 Textures vs raw JPEGs
            // but I was having issues. Also fuck it and JPEGs are smaller on disk.
            // See http://malideveloper.arm.com/develop-for-mali/tools/asset-creation/mali-gpu-texture-compression-tool/

            //material.addTexture(mTextureManager.addTexture(new Etc1Texture(R.raw.brc_sm)));
            //material.addTexture(new Etc1Texture(R.raw.brc_sm));

            material.addTexture(mFirstTexture);
//            material.addTexture(mSecondTexture);
            material.enableLighting(true);
            material.setColorInfluence(0f);
            sphere.setMaterial(material);

            getCurrentScene().addChild(sphere);

        } catch (Exception e) {
            e.printStackTrace();
        }

        super.initScene();
    }

    @Override
    public void onRender(double deltaTime) {
//        mBlender.blendPhotosphereByYaw(mCameraOrientation, mFirstTexture, mSecondTexture);
        mVideoScreen.advanceFrame();
        super.onRender(deltaTime);
    }
}
