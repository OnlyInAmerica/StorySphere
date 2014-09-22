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
package rajawali.wallpaper;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;

@SuppressWarnings("deprecation")
public class WallpaperSettings extends PreferenceActivity
implements SharedPreferences.OnSharedPreferenceChangeListener {

@Override
protected void onCreate(Bundle icicle) {
    super.onCreate(icicle);
    getPreferenceManager().setSharedPreferencesName(
    		Wallpaper.SHARED_PREFS_NAME);
    //addPreferencesFromResource(R.xml.settings);
    getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(
            this);
}

@Override
protected void onResume() {
    super.onResume();
}

@Override
protected void onDestroy() {
    getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(
            this);
    super.onDestroy();
}

public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
        String key) {
}

}