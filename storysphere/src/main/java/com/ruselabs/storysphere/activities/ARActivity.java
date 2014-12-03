package com.ruselabs.storysphere.activities;

import android.os.Bundle;
import android.view.View;

import com.google.vrtoolkit.cardboard.sensors.HeadTracker;

import rajawali.util.RajLog;
import rajawali.vr.RajawaliVRRenderer;
import rajawali.vuforia.RajawaliVuforiaActivity;

/**
 * This class is the entry point for the StorySphere experience.
 * All we're doing is extending {@link rajawali.RajawaliActivity}
 * to connect our custom renderer, which will handle all things graphics.
 *
 *  @author David Brodsky (dbro@dbro.pro)
 */
public class ARActivity extends RajawaliVuforiaActivity {
    private HeadTracker mHeadTracker;
    private RajawaliVRRenderer mRenderer;
    private boolean mSetRenderer = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mSurfaceView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
//                | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
//                | View.SYSTEM_UI_FLAG_IMMERSIVE);
        mHeadTracker = new HeadTracker(this);
    }

    @Override
    protected void createSurfaceView() {
        super.createSurfaceView();

        if (!mSetRenderer) super.setRenderer(mRenderer);

        mSurfaceView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                | View.SYSTEM_UI_FLAG_IMMERSIVE);
    }

    protected void setRenderer(RajawaliVRRenderer renderer) {
        mRenderer = renderer;
        mRenderer.setHeadTracker(mHeadTracker);

        if (mSurfaceView == null) return; // We'll process this call on createSurfaceView
        mSetRenderer = true;
        super.setRenderer(renderer);
    }

    @Override
    public void onResume() {
        super.onResume();
        mHeadTracker.startTracking();
    }

    @Override
    public void onPause() {
        super.onPause();
        mHeadTracker.stopTracking();
    }

    @Override
    protected void setupTracker() {
        int result = initTracker(TRACKER_TYPE_MARKER);
        if (result == 1) {
            result = initTracker(TRACKER_TYPE_IMAGE);
            if (result == 1) {
                super.setupTracker();
            } else {
                RajLog.e("Couldn't initialize image tracker.");
            }
        } else {
            RajLog.e("Couldn't initialize marker tracker.");
        }
    }

    @Override
    protected void initApplicationAR() {
        super.initApplicationAR();

        createFrameMarker(1, "Marker1", 50, 50);
        createFrameMarker(2, "Marker2", 50, 50);

        createImageMarker("StonesAndChips.xml");

        // -- this is how you add a cylinder target:
        // https://developer.vuforia.com/resources/dev-guide/cylinder-targets
        // createImageMarker("MyCylinderTarget.xml");

        // -- this is how you add a multi-target:
        // https://developer.vuforia.com/resources/dev-guide/multi-targets
        // createImageMarker("MyMultiTarget.xml");
    }
}