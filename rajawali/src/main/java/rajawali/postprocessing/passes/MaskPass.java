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
package rajawali.postprocessing.passes;

/**
 * Code heavily referenced from Three.js post processing framework.
 */
import rajawali.postprocessing.APass;
import rajawali.primitives.ScreenQuad;
import rajawali.renderer.RajawaliRenderer;
import rajawali.renderer.RenderTarget;
import rajawali.scene.RajawaliScene;
import android.opengl.GLES20;

/**
 * Masked render pass for drawing to stencil buffer.
 * @author Andrew Jo (andrewjo@gmail.com / www.andrewjo.com)
 */
public class MaskPass extends APass {
	protected RajawaliScene mScene;
	protected boolean mInverse;
	
	public MaskPass(RajawaliScene scene) {
		mPassType = PassType.MASK;
		mScene = scene;
		mEnabled = true;
		mClear = true;
		mNeedsSwap = false;
		mInverse = false;
	}
	
	/**
	 * Returns whether the stencil is inverted.
	 * @return True if inverted, false otherwise.
	 */
	public boolean isInverse() {
		return mInverse;
	}
	
	/**
	 * Sets whether to invert the stencil buffer.
	 * @param inverse True to invert, false otherwise.
	 */
	public void setInverse(boolean inverse) {
		mInverse = inverse;
	}
	
	@Override
	public void render(RajawaliScene scene, RajawaliRenderer render, ScreenQuad screenQuad, RenderTarget writeBuffer, RenderTarget readBuffer, double deltaTime) {
		// Do not update color or depth.
		GLES20.glColorMask(false, false, false, false);
		GLES20.glDepthMask(false);
		
		// Set up stencil.
		int writeValue, clearValue;
		
		if (mInverse) {
			writeValue = 0;
			clearValue = 1;
		} else {
			writeValue = 1;
			clearValue = 0;
		}
		
		GLES20.glEnable(GLES20.GL_STENCIL_TEST);
		GLES20.glStencilOp(GLES20.GL_REPLACE, GLES20.GL_REPLACE, GLES20.GL_REPLACE);
		GLES20.glStencilFunc(GLES20.GL_ALWAYS, writeValue, 0xffffffff);
		GLES20.glClearStencil(clearValue);
		
		// Draw into the stencil buffer.
		mScene.render(deltaTime, readBuffer);
		mScene.render(deltaTime, writeBuffer);
		
		// Re-enable color and depth.
		GLES20.glColorMask(true, true, true, true);
		GLES20.glDepthMask(true);
		
		// Only render where stencil is set to 1.
		GLES20.glStencilFunc(GLES20.GL_EQUAL, 1, 0xffffffff);
		GLES20.glStencilOp(GLES20.GL_KEEP, GLES20.GL_KEEP, GLES20.GL_KEEP);
	}
}
