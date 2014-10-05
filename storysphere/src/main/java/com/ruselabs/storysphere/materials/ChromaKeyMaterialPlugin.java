package com.ruselabs.storysphere.materials;

import rajawali.materials.Material;
import rajawali.materials.plugins.IMaterialPlugin;
import rajawali.materials.shaders.AShader;
import rajawali.materials.shaders.IShaderFragment;

/**
 * Created by davidbrodsky on 10/5/14.
 */
public class ChromaKeyMaterialPlugin implements IMaterialPlugin {

    private ChromaFragmentShaderFragment mFragmentShader;

    public ChromaKeyMaterialPlugin() {
        mFragmentShader = new ChromaFragmentShaderFragment();
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

    private final class ChromaFragmentShaderFragment extends AShader implements IShaderFragment {
        public final static String SHADER_ID = "CHROMA_FRAGMENT_SHADER_FRAGMENT";

        public ChromaFragmentShaderFragment() {
            super(ShaderType.FRAGMENT_SHADER_FRAGMENT);
            initialize();
        }

        @Override
        public void main() {
//            "#extension GL_OES_EGL_image_external : require\n" +
//                    "precision mediump float;\n" +
//                    "varying vec2 vTextureCoord;\n" +
//                    "uniform samplerExternalOES sTexture;\n" +
//                    "void main() {\n" +
//                    "    vec4 tc = texture2D(sTexture, vTextureCoord);\n" +
//                    "    float color = ((tc.r * 0.3 + tc.g * 0.59 + tc.b * 0.11) - 0.5 * 1.5) + 0.8;\n" +
//                    "    if(tc.g > 0.6 && tc.b < 0.6 && tc.r < 0.6){ \n" +
//                    "        gl_FragColor = vec4(0, 0, 0, 0.0);\n" +
//                    "    }else{ \n" +
//                    "        gl_FragColor = texture2D(sTexture, vTextureCoord);\n" +
//                    "    }\n" +
//                    "}\n";

//            *	// corresponds to GLSL: 	vec3 myVar = maMyVec3Uniform;
//            * 	RVec3 myVar = new RVec3("myVar");
//            *	myVar.assign(maMyVec3Uniform);
//            *	// corresponds to GLSL:		myVar *= 1.0f;
//            * 	myVar.assignMultiply(1.0f);
//            * 	// etc ..
            RVec4 color = (RVec4) getGlobal(DefaultShaderVar.G_COLOR);

            // Traditionally green chroma
//            if (Float.parseFloat(color.g().getValue()) > .6f &&
//                    Float.parseFloat(color.b().getValue()) < .6f &&
//                    Float.parseFloat(color.r().getValue()) < .6f) {
            // Black chroma
            if (Float.parseFloat(color.g().getValue()) < .1f &&
                    Float.parseFloat(color.b().getValue()) < .1f &&
                    Float.parseFloat(color.r().getValue()) < .1f) {
                GL_FRAG_COLOR.a().assign(0);
            } else {
                GL_FRAG_COLOR.a().assign(1);
            }

        }

        @Override
        public Material.PluginInsertLocation getInsertLocation() {
            return Material.PluginInsertLocation.PRE_TRANSFORM;
        }

        @Override
        public String getShaderId() {
            return SHADER_ID;
        }

        @Override
        public void bindTextures(int nextIndex) {}

        @Override
        public void unbindTextures() {}
    }
}
