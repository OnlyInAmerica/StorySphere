package com.ruselabs.storysphere.tricks;

import android.content.Context;
import android.media.MediaPlayer;

import com.ruselabs.storysphere.materials.ChromaKeyMaterialPlugin;

import rajawali.Object3D;
import rajawali.materials.Material;
import rajawali.materials.textures.ATexture;
import rajawali.materials.textures.VideoTexture;
import rajawali.primitives.Plane;

/**
 * Created by davidbrodsky on 10/5/14.
 */
public class ChromaVideoScreen extends VideoScreen{

    public ChromaVideoScreen(Context context, int movieResId, int initialHeight, int initialWidth) {
        super(context, movieResId, initialHeight, initialWidth);
        mVideoMaterial.addPlugin(new ChromaKeyMaterialPlugin(mVideoMaterial));
    }
}
