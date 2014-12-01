package com.ruselabs.storysphere.activities;

import android.content.pm.ActivityInfo;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;

import com.ruselabs.storysphere.renderer.StorySphereRenderer;

import rajawali.vr.RajawaliVRActivity;

/**
 * This class is the entry point for the StorySphere experience.
 * All we're doing is extending {@link rajawali.RajawaliActivity}
 * to connect our custom renderer, which will handle all things graphics.
 *
 *  @author David Brodsky (dbro@dbro.pro)
 */
public class StorySphereActivity extends RajawaliVRActivity {
	private StorySphereRenderer mRenderer;
    private Camera mCamera;

	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide all Android window chrome
        enterStickyImmersiveMode();

        // Lock the orientation to landscape
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        if (mCamera == null) {
            mCamera = Camera.open();
        }

        // Connect our Storysphere renderer
		mRenderer = new StorySphereRenderer(this);
		mRenderer.setSurfaceView(mSurfaceView);
		setRenderer(mRenderer);
	}


    /**
     * Enter Sticky Immersion Mode, hiding the status and navigation bars.
     * This allows the user to swipe from screen edge to temporarily
     * reveal the status and navigation interface.
     *
     * @see <a href="https://developer.android.com/training/system-ui/immersive.html#sticky">Sticky Immersion</a>
     */
    private void enterStickyImmersiveMode() {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            // Prevent user touches from permanently resuming the Navigation Bar
            enterStickyImmersiveMode();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getWindow().addFlags(android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    public void onPause() {
        super.onPause();
        getWindow().clearFlags(android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mCamera != null) {
            mCamera.release();
        }
    }

    public Camera getCamera() {
        return mCamera;
    }
}
