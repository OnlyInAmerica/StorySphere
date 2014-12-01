package com.ruselabs.storysphere.tricks;

import android.content.Context;
import android.media.MediaPlayer;

import rajawali.Object3D;
import rajawali.materials.Material;
import rajawali.materials.textures.ATexture;
import rajawali.materials.textures.AlphaVideoTexture;
import rajawali.materials.textures.VideoTexture;
import rajawali.primitives.Plane;

/**
 * Created by davidbrodsky on 10/5/14.
 */
public class VideoScreen {

    Plane mVideoScreen;
//    AlphaVideoTexture mVideoTexture;
    VideoTexture mVideoTexture;
    Material mVideoMaterial;
    MediaPlayer mMediaPlayer;


    public VideoScreen(Context context, int movieResId, int initialHeight, int initialWidth, double x, double y, double z) {
        try {
            mMediaPlayer = MediaPlayer.create(context, movieResId);
            mMediaPlayer.setLooping(true);
            mMediaPlayer.start();
            mVideoTexture = new VideoTexture("video", mMediaPlayer);

            mVideoScreen = new Plane(initialWidth, initialHeight, 1, 1);
            mVideoScreen.setPosition(x, y, z);
            mVideoScreen.setRotY(90);
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
