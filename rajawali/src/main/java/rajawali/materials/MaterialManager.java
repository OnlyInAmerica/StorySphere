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
package rajawali.materials;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import rajawali.renderer.RajawaliRenderer;


public class MaterialManager extends AResourceManager {
	private static MaterialManager instance = null;
	private List<Material> mMaterialList;
	
	private MaterialManager()
	{
		mMaterialList = Collections.synchronizedList(new CopyOnWriteArrayList<Material>());
		mRenderers = Collections.synchronizedList(new CopyOnWriteArrayList<RajawaliRenderer>());
	}
	
	public static MaterialManager getInstance()
	{
		if(instance == null)
		{
			instance = new MaterialManager();
		}
		return instance;
	}
	
	public Material addMaterial(Material material)
	{
		if(material == null) return null;
		for(Material mat : mMaterialList)
		{
			if(mat == material)
				return material;
		}
		mRenderer.queueAddTask(material);
		mMaterialList.add(material);
		return material;
	}
	
	public void taskAdd(Material material)
	{
		material.setOwnerIdentity(mRenderer.getClass().toString());
		material.add();
	}
	
	public void removeMaterial(Material material)
	{
		if(material == null) return;
		mRenderer.queueRemoveTask(material);
	}
	
	public void taskRemove(Material material)
	{
		material.remove();
		mMaterialList.remove(material);
	}
	
	public void reload()
	{
		mRenderer.queueReloadTask(this);
	}
	
	public void taskReload()
	{
		int len = mMaterialList.size();
		for (int i = 0; i < len; i++)
		{
			Material material = mMaterialList.get(i);
			material.reload();
		}
	}
	
	public void reset()
	{
		mRenderer.queueResetTask(this);
	}
	
	public void taskReset()
	{
		int count = mMaterialList.size();
		
		for(int i=0; i<count; i++)
		{
			Material material = mMaterialList.get(i);
			
			if(material.getOwnerIdentity() != null && material.getOwnerIdentity().equals(mRenderer.getClass().toString()))
			{
				material.remove();
				mMaterialList.remove(i);
				i -= 1;
				count -= 1;							
			}			
		}
		
		if (mRenderers.size() > 0)
		{
			mRenderer = mRenderers.get(mRenderers.size() - 1);
			reload();
		} else {
			mMaterialList.clear();
		}
	}
	
	public void taskReset(RajawaliRenderer renderer)
	{
		if (mRenderers.size() == 0)
		{
			taskReset();
		}
	}
	
	public int getNumMaterials()
	{
		return mMaterialList.size();
	}
	
	public TYPE getFrameTaskType() {
		return TYPE.MATERIAL_MANAGER;
	}
}
