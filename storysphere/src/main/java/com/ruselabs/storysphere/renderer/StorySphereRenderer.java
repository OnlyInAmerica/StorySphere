package com.ruselabs.storysphere.renderer;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;

import com.ruselabs.storysphere.R;
import com.ruselabs.storysphere.tricks.CameraScreen;
import com.ruselabs.storysphere.tricks.ChromaVideoScreen;
import com.ruselabs.storysphere.tricks.VideoSphereScreen;

import javax.microedition.khronos.opengles.GL10;

import rajawali.lights.DirectionalLight;
import rajawali.materials.Material;
import rajawali.materials.methods.DiffuseMethod;
import rajawali.materials.textures.Texture;
import rajawali.math.Quaternion;
import rajawali.math.vector.Vector3;
import rajawali.primitives.Sphere;
import rajawali.vuforia.RajawaliVuforiaRenderer;

/**
 * This class handles all things graphics.
 *
 * Here we initialize the 3D scene via {@link #initScene()} and update
 * the scene on each new frame via {@link #onRender(double)}
 *
 *  @author David Brodsky (dbro@dbro.pro)
 */
public class StorySphereRenderer extends RajawaliVuforiaRenderer {
    public static final String TAG = "StorySphereRenderer";

    /** Photosphere textures */
    private Texture mFirstTexture;
    private Texture mSecondTexture;

    private ChromaVideoScreen mVideoScreen;
    private VideoSphereScreen mVideoSphereScreen;
    private CameraScreen mCameraScreen;
//    private PhotosphereBlender mBlender;

//    private SkeletalAnimationObject3D mBob;
    private Sphere mEarth;

    public StorySphereRenderer(Context context) {
        super(context);
        setFrameRate(60);
    }

    @Override
    protected void foundFrameMarker(int markerId, Vector3 position, Quaternion orientation) {
        Log.i(TAG, "Found  marker " + markerId);
    }

    @Override
    protected void foundImageMarker(String trackableName, Vector3 position, Quaternion orientation) {
        if (trackableName.equals("stones")) {
//            Log.i(TAG, String.format("found image marker %s at (%f, %f, %f)",trackableName, position.x, position.y, position.z ));
            mEarth.setVisible(true);
            mEarth.setPosition(position);
            mEarth.setOrientation(orientation);
        }
    }

    @Override
    public void noFrameMarkersFound() {

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
//            getCurrentCamera().setPosition(0,0,0);

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

//            getCurrentScene().setBackgroundColor(0f, 0f, 0f, 1f);
//            getCurrentScene().setSkybox(R.drawable.starfield_front,
//                                        R.drawable.starfield_right,
//                                        R.drawable.starfield_back,
//                                        R.drawable.starfield_left,
//                                        R.drawable.starfield_top,
//                                        R.drawable.starfield_back);


//            mEnableHeadTracking = false;

//            ChromaVideoScreen chromaVideoScreen = new ChromaVideoScreen(mContext, R.raw.explosion, 16, 9, 0, 0, -12);
//            getCurrentScene().addChild(chromaVideoScreen.getScreen());

//            Camera camera = ((StorySphereActivity) mContext).getCamera();
//            Camera.Parameters params = camera.getParameters();
//            choosePreviewSize(params, 1280, 720);
//            camera.setParameters(params);
//            camera.startPreview();
//            mCameraScreen = new CameraScreen(camera, 9, 16, 0, 0, -12);
//            mCameraScreen.getScreen().setRotY(180);
//            getCurrentScene().addChild(mCameraScreen.getScreen());

//            Sphere sphere = new Sphere(10, 50, 50);
//            Material material = new Material();
//            material.setColorInfluence(1f);
//            sphere.setMaterial(material);
//            sphere.setPosition(-10, 10, 0);
//            getCurrentScene().addChild(sphere);

            Texture earth = new Texture("earth", R.drawable.earthmap);
            mEarth = new Sphere(10, 50, 50);
            mEarth.setPosition(0,0,0);
            Material material = new Material();
            material.addTexture(earth);
            material.setColorInfluence(0f);
            material.enableLighting(true);
            material.setDiffuseMethod(new DiffuseMethod.Lambert());
            mEarth.setMaterial(material);
            mEarth.setScale(10);
//            mEarth.setPosition(80, 0, 0);
            getCurrentScene().addChild(mEarth);

//            LoaderMD5Mesh meshParser = new LoaderMD5Mesh(this,
//                    R.raw.boblampclean_mesh);
//            meshParser.parse();
//            mBob = (SkeletalAnimationObject3D) meshParser
//                    .getParsedAnimationObject();
//            mBob.setScale(2);
//
//            LoaderMD5Anim animParser = new LoaderMD5Anim("dance", this,
//                    R.raw.boblampclean_anim);
//            animParser.parse();
//            mBob.setAnimationSequence((SkeletalAnimationSequence) animParser
//                    .getParsedAnimationSequence());
//
//            getCurrentScene().addChild(mBob);
//
//            mBob.play();
//            mBob.setVisible(false);

//            RotateAnimation3D anim = new RotateAnimation3D(2f, 10f, 1f);
//            anim.setDurationMilliseconds(4*1000);
//            anim.setRepeatMode(Animation.RepeatMode.INFINITE);
//            anim.setTransformable3D(mEarth);
//            getCurrentScene().registerAnimation(anim);
//            anim.play();

            // END SPACE

//            FBXParser parser = new FBXParser(this, R.raw.cooper-hewitt_floor_01_final);
//            parser.parse();
//            BaseObject3D mObject = parser.getParsedObject();
//            mObject.addLight(mLight);
//            addChild(mObject);

//            LoaderAWD awdParser = new LoaderAWD(mContext.getResources(), mTextureManager, R.raw.broc_un);
//            awdParser.parse();
//            Object3D broc = awdParser.getParsedObject();
//            broc.setPosition(0, 0, -12);
//            for (int x = 0; x < broc.getNumChildren(); x++) {
//                broc.getChildAt(x).getMaterial().setColorInfluence(0f);
//            }
//            getCurrentScene().addChild(broc);

//            RotateAnimation3D anim = new RotateAnimation3D(-400f, 1f, 1f);
//            anim.setDurationMilliseconds(40*1000);
//            anim.setTransformable3D(broc);
//            getCurrentScene().registerAnimation(anim);
//            anim.play();


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
//        mCameraScreen.advanceFrame();
        super.onRender(deltaTime);
    }

    private static void choosePreviewSize(Camera.Parameters parms, int width, int height) {
        // We should make sure that the requested MPEG size is less than the preferred
        // size, and has the same aspect ratio.
        Camera.Size ppsfv = parms.getPreferredPreviewSizeForVideo();
        if (ppsfv != null) {
            Log.d(TAG, "Camera preferred preview size for video is " +
                    ppsfv.width + "x" + ppsfv.height);
        }

        for (Camera.Size size : parms.getSupportedPreviewSizes()) {
            if (size.width == width && size.height == height) {
                parms.setPreviewSize(width, height);
                return;
            }
        }

        Log.w(TAG, "Unable to set preview size to " + width + "x" + height);
        if (ppsfv != null) {
            parms.setPreviewSize(ppsfv.width, ppsfv.height);
        }
        // else use whatever the default size is
    }

    public void onDrawFrame(GL10 glUnused) {
        mEarth.setVisible(false);

        super.onDrawFrame(glUnused);
    }
}
