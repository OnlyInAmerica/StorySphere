/**
 * Copyright 2013 Dennis Ippel
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package rajawali.materials.shaders.fragments.texture;

import java.util.List;

import rajawali.materials.Material.PluginInsertLocation;
import rajawali.materials.textures.ATexture;


public class AlphaVideoFragmentShaderFragment extends ATextureFragmentShaderFragment {
	public final static String SHADER_ID = "ALPHA_VIDEO_FRAGMENT";

	public AlphaVideoFragmentShaderFragment(List<ATexture> textures)
	{
		super(textures);
	}
	
	public String getShaderId() {
		return SHADER_ID;
	}
	
	@Override
	public void main() {
		super.main();
		RVec2 textureCoord = (RVec2)getGlobal(DefaultShaderVar.G_TEXTURE_COORD);
        RVec4 color = (RVec4) getGlobal(DefaultShaderVar.G_COLOR);
		RVec4 fragColor = new RVec4("fragColor");

        int videoTextureCount = 0;
		for(int i=0; i < mTextures.size(); i++) {
            if (mTextures.get(i).getTextureType() == ATexture.TextureType.ALPHA_VIDEO_TEXTURE) {
                fragColor.assign(texture2D(muVideoTextures[videoTextureCount++], textureCoord));

                fragColor.assignMultiply(muInfluence[i]);
                color.assign(fragColor);

                Condition[] conditions = new Condition[3];
                conditions[0] = new Condition(fragColor.r(), Operator.LESS_THAN, .5f);
                conditions[1] = new Condition(Operator.AND, fragColor.g(), Operator.LESS_THAN, .5f);
                conditions[2] = new Condition(Operator.AND, fragColor.b(), Operator.LESS_THAN, .5f);
                startif(conditions);
                {
                    discard();
                }
                endif();
            }
        }


            //color.r().assign(alphaMaskColor.r()); // Black screen
            //color.g().assign(alphaMaskColor.g()); // green tint image. lower aberrations
            //color.b().assign(alphaMaskColor.b()); // Black screen
            //color.a().assign(alphaMaskColor.a()); // Black screen
            //color.g().assign(alphaMaskColor.r()); // Black screen


//            Rough algorithm for Chroma-keying dark pixels

	}
	
	@Override
	public PluginInsertLocation getInsertLocation() {
		return PluginInsertLocation.PRE_TRANSFORM;
	}
	
	public void bindTextures(int nextIndex) {}
	public void unbindTextures() {}
}
