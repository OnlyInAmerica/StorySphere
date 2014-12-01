package com.ruselabs.storysphere.renderer;

import android.content.Context;
import android.hardware.Camera;

import com.ruselabs.storysphere.R;
import com.ruselabs.storysphere.activities.StorySphereActivity;
import com.ruselabs.storysphere.tricks.CameraScreen;
import com.ruselabs.storysphere.tricks.ChromaVideoScreen;
import com.ruselabs.storysphere.tricks.VideoSphereScreen;

import rajawali.lights.DirectionalLight;
import rajawali.materials.textures.Texture;
import rajawali.vr.RajawaliVRRenderer;

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

    private ChromaVideoScreen mVideoScreen;
    private VideoSphereScreen mVideoSphereScreen;
    private CameraScreen mCameraScreen;
//    private PhotosphereBlender mBlender;

    public StorySphereRenderer(Context context) {
        super(context);
        setFrameRate(60);
    }

    @Override
    public void initScene() {
        try {

            /** These values don't have a great significance if we're not placing objects within the sphere */
            final int PHOTOSPHERE_RADIUS = 70;

            /** Create Photosphere */
//            String videoPath = new File(Environment.getExternalStorageDirectory(), "output.mp4").getAbsolutePath();
//            mVideoSphereScreen = new VideoSphereScreen(mContext, videoPath, PHOTOSPHERE_RADIUS, 0, 0, 0);
//            getCurrentScene().addChild(mVideoSphereScreen.getScreen());
            getCurrentCamera().setPosition(0,0,0);

            /** Setup Model loader */


//            Loader3DSMax obj = new Loader3DSMax(this, R.raw.hemlock); // hemlock tree
//            obj.parse();
//            Object3D hemlock = obj.getParsedObject();
//            hemlock.setPosition(-40, 0, 0);
//            getCurrentScene().addChild(hemlock);

//            ObjParser objParser = new ObjParser(mContext.getResources(), mTextureManager, R.raw.myobject_obj);
//            objParser.parse();
//            BaseObject3D mObject = objParser.getParsedObject();
//            mObject.setLight(mLight);
//            addChild(mObject);

//            LoaderOBJ obj = new LoaderOBJ(this, R.raw.earth_obj); // hemlock tree
//            LoaderOBJ obj = new LoaderOBJ(getContext().getResources(), mTextureManager, R.raw.rocky_planets_obj); // hemlock tree
//            obj.parse();
//            Object3D earth = obj.getParsedObject();
//            earth.setPosition(-10, 0, 0);
//            getCurrentScene().addChild(earth);

//            LoaderFBX fbx = new LoaderFBX(this, R.raw.cooper_hewitt_floor_03_final);
//            fbx.parse();
//            Object3D hewitt = fbx.getParsedObject();
//            hewitt.setPosition(-40,0,0);
//            hewitt.setScale(1f);
//            getCurrentScene().addChild(hewitt);

//            SpotLight light = new SpotLight();
//            light.setPosition(80, 80, 0);
//            light.setPower(1f);
//            light.setLookAt(80, 0, 0);
//            getCurrentScene().addLight(light);

            // BEGIN SPACE

            DirectionalLight light = new DirectionalLight(0.2f, -.5f, -1f);
            light.setPower(1f);
            getCurrentScene().addLight(light);

            getCurrentScene().setBackgroundColor(0f, 0f, 0f, 1f);
            getCurrentScene().setSkybox(R.drawable.starfield_front,
                                        R.drawable.starfield_right,
                                        R.drawable.starfield_back,
                                        R.drawable.starfield_left,
                                        R.drawable.starfield_top,
                                        R.drawable.starfield_back);


            mEnableHeadTracking = false;

            Camera camera = ((StorySphereActivity) mContext).getCamera();
            mCameraScreen = new CameraScreen(camera, 6, 8, 0, 0, -10);
            mCameraScreen.getScreen().setRotY(180);
            getCurrentScene().addChild(mCameraScreen.getScreen());
            camera.startPreview();

//            Sphere sphere = new Sphere(10, 50, 50);
//            Material material = new Material();
//            material.setColorInfluence(1f);
//            sphere.setMaterial(material);
//            sphere.setPosition(-10, 10, 0);
//            getCurrentScene().addChild(sphere);

            /*
            Texture earth = new Texture("earth", R.drawable.earthmap);
            Sphere sphere = new Sphere(10, 50, 50);
            Material material = new Material();
            material.addTexture(earth);
            material.setColorInfluence(0f);
            material.enableLighting(true);
            material.setDiffuseMethod(new DiffuseMethod.Lambert());
            sphere.setMaterial(material);
            sphere.setPosition(80, 0, 0);
            getCurrentScene().addChild(sphere);

            RotateAnimation3D anim = new RotateAnimation3D(2f, 10f, 1f);
            anim.setDurationMilliseconds(4*1000);
            anim.setRepeatMode(Animation.RepeatMode.INFINITE);
            anim.setTransformable3D(sphere);
            getCurrentScene().registerAnimation(anim);
            anim.play();
            */

            // END SPACE

//            FBXParser parser = new FBXParser(this, R.raw.cooper-hewitt_floor_01_final);
//            parser.parse();
//            BaseObject3D mObject = parser.getParsedObject();
//            mObject.addLight(mLight);
//            addChild(mObject);


        } catch (Exception e) {
            e.printStackTrace();
        }

        super.initScene();
    }

    @Override
    public void onRender(double deltaTime) {
//        mBlender.blendPhotosphereByYaw(mCameraOrientation, mFirstTexture, mSecondTexture);
//        mVideoScreen.advanceFrame();
//        mVideoSphereScreen.advanceFrame();
        mCameraScreen.advanceFrame();
        super.onRender(deltaTime);
    }
}
