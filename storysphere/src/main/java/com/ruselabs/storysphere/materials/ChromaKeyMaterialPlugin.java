package com.ruselabs.storysphere.materials;

import android.util.Log;

import java.util.List;

import rajawali.materials.Material;
import rajawali.materials.plugins.IMaterialPlugin;
import rajawali.materials.shaders.AShader;
import rajawali.materials.shaders.IShaderFragment;
import rajawali.materials.shaders.fragments.texture.ATextureFragmentShaderFragment;
import rajawali.materials.shaders.fragments.texture.AlphaMapFragmentShaderFragment;
import rajawali.materials.textures.ATexture;

/**
 * Created by davidbrodsky on 10/5/14.
 */
public class ChromaKeyMaterialPlugin implements IMaterialPlugin {
    public static final String TAG = "ChromaKeyMaterialPlugin";

    private AlphaMapFragmentShaderFragment mFragmentShader;

    public ChromaKeyMaterialPlugin(Material host) {
        mFragmentShader = new AlphaMapFragmentShaderFragment(host.getTextureList());
    }

    @Override
    public Material.PluginInsertLocation getInsertLocation() {
        return Material.PluginInsertLocation.POST_TRANSFORM;
    }

    @Override
    public IShaderFragment getVertexShaderFragment() {
        return null;
    }

    @Override
    public IShaderFragment getFragmentShaderFragment() {
        return mFragmentShader;
    }

    @Override
    public void bindTextures(int nextIndex) {

    }

    @Override
    public void unbindTextures() {

    }

//    private final class ChromaFragmentShaderFragment extends ATextureFragmentShaderFragment implements IShaderFragment {
//        public final static String SHADER_ID = "CHROMA_FRAGMENT_SHADER_FRAGMENT";
//
//        public ChromaFragmentShaderFragment(List<ATexture> textures) {
//            super(textures);
//        }
//
//        @Override
//        public void main() {
////            "#extension GL_OES_EGL_image_external : require\n" +
////                    "precision mediump float;\n" +
////                    "varying vec2 vTextureCoord;\n" +
////                    "uniform samplerExternalOES sTexture;\n" +
////                    "void main() {\n" +
////                    "    vec4 tc = texture2D(sTexture, vTextureCoord);\n" +
////                    "    if(tc.g > 0.6 && tc.b < 0.6 && tc.r < 0.6){ \n" +
////                    "        gl_FragColor = vec4(0, 0, 0, 0.0);\n" +
////                    "    }else{ \n" +
////                    "        gl_FragColor = texture2D(sTexture, vTextureCoord);\n" +
////                    "    }\n" +
////                    "}\n";
//
////            *	// corresponds to GLSL: 	vec3 myVar = maMyVec3Uniform;
////            * 	RVec3 myVar = new RVec3("myVar");
////            *	myVar.assign(maMyVec3Uniform);
////            *	// corresponds to GLSL:		myVar *= 1.0f;
////            * 	myVar.assignMultiply(1.0f);
////            * 	// etc ..
//
//            super.main();
//            RVec2 textureCoord = (RVec2)getGlobal(DefaultShaderVar.G_TEXTURE_COORD);
//            RVec4 alphaMaskColor = new RVec4("alphaMaskColor");
//
//            for(int i=0; i < mTextures.size(); i++)
//            {
//                alphaMaskColor.assign(texture2D(muTextures[i], textureCoord));
//                startif(new Condition(alphaMaskColor.r(), Operator.LESS_THAN, .5f));
//                {
//                    discard();
//                }
//                endif();
//            }
//
////            RVec4 color = new RVec4(texture2D(mTextureSampler, getGlobal(DefaultShaderVar.V_TEXTURE_COORD)));
//
//            // Traditionally green chroma
////            if (Float.parseFloat(color.g().getValue()) > .6f &&
////                    Float.parseFloat(color.b().getValue()) < .6f &&
////                    Float.parseFloat(color.r().getValue()) < .6f) {
//            // Black chroma
////            if (color.g().getValue() != null) {
////                Log.i(TAG, "got color for fragment");
////                if (Float.parseFloat(color.g().getValue()) < .1f &&
////                        Float.parseFloat(color.b().getValue()) < .1f &&
////                        Float.parseFloat(color.r().getValue()) < .1f) {
////                    GL_FRAG_COLOR.a().assign(0);
////                    Log.i(TAG, "got chromad fragment");
////
////                } else {
////                    GL_FRAG_COLOR.a().assign(1);
////                }
////            }
////            GL_FRAG_COLOR.a().assign(0.5f);
//        }
//
//        @Override
//        public Material.PluginInsertLocation getInsertLocation() {
//            return Material.PluginInsertLocation.POST_TRANSFORM;
//        }
//
//        @Override
//        public String getShaderId() {
//            return SHADER_ID;
//        }
//
//        @Override
//        public void bindTextures(int nextIndex) {}
//
//        @Override
//        public void unbindTextures() {}
//    }
}
