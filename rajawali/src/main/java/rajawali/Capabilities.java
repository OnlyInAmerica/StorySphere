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
package rajawali;

import android.opengl.GLES20;


/**
 * Lists all OpenGL specific capabilities
 * 
 * @author dennis.ippel
 *
 */
public class Capabilities {
	private static Capabilities instance = null;
	
	private int mMaxTextureSize;
	private int mMaxCombinedTextureImageUnits;
	private int mMaxCubeMapTextureSize;
	private int mMaxFragmentUniformVectors;
	private int mMaxRenderbufferSize;
	private int mMaxTextureImageUnits;
	private int mMaxVaryingVectors;
	private int mMaxVertexAttribs;
	private int mMaxVertexTextureImageUnits;
	private int mMaxVertexUniformVectors;
	private int mMaxViewportWidth;
	private int mMaxViewportHeight;
	private int mMinAliasedLineWidth;
	private int mMaxAliasedLineWidth;
	private int mMinAliasedPointSize;
	private int mMaxAliasedPointSize; 
	
	private int[] mParam;
	
	private Capabilities()
	{
		initialize();
	}
	
	public static Capabilities getInstance()
	{
		if(instance == null)
		{
			instance = new Capabilities();
		}
		return instance;
	}
	
	public void initialize()
	{
		mParam = new int[1];
		
		mMaxCombinedTextureImageUnits = getInt(GLES20.GL_MAX_COMBINED_TEXTURE_IMAGE_UNITS);
		mMaxCubeMapTextureSize = getInt(GLES20.GL_MAX_CUBE_MAP_TEXTURE_SIZE);
		mMaxFragmentUniformVectors = getInt(GLES20.GL_MAX_FRAGMENT_UNIFORM_VECTORS);
		mMaxRenderbufferSize = getInt(GLES20.GL_MAX_RENDERBUFFER_SIZE);
		mMaxTextureImageUnits = getInt(GLES20.GL_MAX_TEXTURE_IMAGE_UNITS);
		mMaxTextureSize = getInt(GLES20.GL_MAX_TEXTURE_SIZE);
		mMaxVaryingVectors = getInt(GLES20.GL_MAX_VARYING_VECTORS);
		mMaxVertexAttribs = getInt(GLES20.GL_MAX_VERTEX_ATTRIBS);
		mMaxVertexTextureImageUnits = getInt(GLES20.GL_MAX_VERTEX_TEXTURE_IMAGE_UNITS);
		mMaxVertexUniformVectors = getInt(GLES20.GL_MAX_VERTEX_UNIFORM_VECTORS);
		mMaxViewportWidth = getInt(GLES20.GL_MAX_VIEWPORT_DIMS, 2, 0);
		mMaxViewportHeight = getInt(GLES20.GL_MAX_VIEWPORT_DIMS, 2, 1);
		mMinAliasedLineWidth = getInt(GLES20.GL_ALIASED_LINE_WIDTH_RANGE, 2, 0);
		mMaxAliasedLineWidth = getInt(GLES20.GL_ALIASED_LINE_WIDTH_RANGE, 2, 1);
		mMinAliasedPointSize = getInt(GLES20.GL_ALIASED_POINT_SIZE_RANGE, 2, 0);
		mMaxAliasedPointSize = getInt(GLES20.GL_ALIASED_POINT_SIZE_RANGE, 2, 1);
	}
	
	private int getInt(int pname)
	{
		GLES20.glGetIntegerv(pname, mParam, 0);
		return mParam[0];
	}
	
	private int getInt(int pname, int length, int index)
	{
		int[] params = new int[length];
		GLES20.glGetIntegerv(pname, params, 0);
		return params[index];
	}
	
	/**
	 * A rough estimate of the largest texture that OpenGL can handle.
	 * @return
	 */
	public int getMaxTextureSize()
	{
		return mMaxTextureSize;
	}
	
	/**
	 * The maximum supported texture image units that can be used to access texture maps from the vertex shader 
	 * and the fragment processor combined. If both the vertex shader and the fragment processing stage access 
	 * the same texture image unit, then that counts as using two texture image units against this limit.
	 * @return
	 */
	public int getMaxCombinedTextureUnits()
	{
		return mMaxCombinedTextureImageUnits;
	}
	
	/**
	 * The value gives a rough estimate of the largest cube-map texture that the GL can handle. 
	 * The value must be at least 1024. 
	 * @return
	 */
	public int getMaxCubeMapTextureSize()
	{
		return mMaxCubeMapTextureSize;
	}
	
	/**
	 * The maximum number of individual 4-vectors of floating-point, integer, or boolean values that can be held 
	 * in uniform variable storage for a fragment shader. 
	 * @return
	 */
	public int getMaxFragmentUniformVectors()
	{
		return mMaxFragmentUniformVectors;
	}
	
	/**
	 * Indicates the maximum supported size for renderbuffers. 
	 * @return
	 */
	public int getMaxRenderbufferSize()
	{
		return mMaxRenderbufferSize;
	}
	
	/**
	 * The maximum supported texture image units that can be used to access texture maps from the fragment shader. 
	 * @return
	 */
	public int getMaxTextureImageUnits()
	{
		return mMaxTextureImageUnits;
	}
	
	/**
	 * The maximum number of 4-vectors for varying variables.
	 * @return
	 */
	public int getMaxVaryingVectors()
	{
		return mMaxVaryingVectors;
	}
	
	/**
	 * The maximum number of 4-component generic vertex attributes accessible to a vertex shader.
	 * @return
	 */
	public int getMaxVertexAttribs()
	{
		return mMaxVertexAttribs;
	}
	
	/**
	 * The maximum supported texture image units that can be used to access texture maps from the vertex shader.
	 * @return
	 */
	public int getMaxVertexTextureImageUnits()
	{
		return mMaxVertexTextureImageUnits;
	}
	
	/**
	 * The maximum number of 4-vectors that may be held in uniform variable storage for the vertex shader.
	 * @return
	 */
	public int getMaxVertexUniformVectors()
	{
		return mMaxVertexUniformVectors;
	}
	
	/**
	 * The maximum supported viewport width
	 * @return
	 */
	public int getMaxViewportWidth()
	{
		return mMaxViewportWidth;
	}
	
	/**
	 * The maximum supported viewport height
	 * @return
	 */
	public int getMaxViewportHeight()
	{
		return mMaxViewportHeight;
	}
	
	/**
	 * Indicates the minimum width supported for aliased lines
	 * @return
	 */
	public int getMinAliasedLineWidth()
	{
		return mMinAliasedLineWidth;
	}
	
	/**
	 * Indicates the maximum width supported for aliased lines
	 * @return
	 */
	public int getMaxAliasedLineWidth()
	{
		return mMaxAliasedLineWidth;
	}
	
	/**
	 * Indicates the minimum size supported for aliased points
	 * @return
	 */
	public int getMinAliasedPointSize()
	{
		return mMinAliasedPointSize;
	}
	
	/**
	 * Indicates the maximum size supported for aliased points
	 * @return
	 */
	public int getMaxAliasedPointSize()
	{
		return mMaxAliasedPointSize;
	}
	
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("-=-=-=- OpenGL Capabilities -=-=-=-\n");
		sb.append("Max Combined Texture Image Units   : ").append(mMaxCombinedTextureImageUnits).append("\n");
		sb.append("Max Cube Map Texture Size          : ").append(mMaxCubeMapTextureSize).append("\n");
		sb.append("Max Fragment Uniform Vectors       : ").append(mMaxFragmentUniformVectors).append("\n");
		sb.append("Max Renderbuffer Size              : ").append(mMaxRenderbufferSize).append("\n");
		sb.append("Max Texture Image Units            : ").append(mMaxTextureImageUnits).append("\n");
		sb.append("Max Texture Size                   : ").append(mMaxTextureSize).append("\n");
		sb.append("Max Varying Vectors                : ").append(mMaxVaryingVectors).append("\n");
		sb.append("Max Vertex Attribs                 : ").append(mMaxVertexAttribs).append("\n");
		sb.append("Max Vertex Texture Image Units     : ").append(mMaxVertexTextureImageUnits).append("\n");
		sb.append("Max Vertex Uniform Vectors         : ").append(mMaxVertexUniformVectors).append("\n");
		sb.append("Max Viewport Width                 : ").append(mMaxViewportWidth).append("\n");
		sb.append("Max Viewport Height                : ").append(mMaxViewportHeight).append("\n");
		sb.append("Min Aliased Line Width             : ").append(mMinAliasedLineWidth).append("\n");
		sb.append("Max Aliased Line Width             : ").append(mMaxAliasedLineWidth).append("\n");
		sb.append("Min Aliased Point Size             : ").append(mMinAliasedPointSize).append("\n");
		sb.append("Max Aliased Point Width            : ").append(mMaxAliasedPointSize).append("\n");
		sb.append("-=-=-=- /OpenGL Capabilities -=-=-=-\n");
		return sb.toString();
	}
}
