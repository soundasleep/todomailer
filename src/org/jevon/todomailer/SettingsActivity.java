package org.jevon.todomailer;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;

public class SettingsActivity extends PreferenceActivity implements OnSharedPreferenceChangeListener {
	
	public static final String KEY_PREF_TO = "pref_to";

	@SuppressWarnings("deprecation")		// I'd rather not but I couldn't get PreferenceFragment to work
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		addPreferencesFromResource(R.xml.preferences);
		
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this); 
		sharedPreferences.registerOnSharedPreferenceChangeListener(this);
		// and initialise the summary
		onSharedPreferenceChanged(sharedPreferences, KEY_PREF_TO);

	}
	
	// based on http://developer.android.com/reference/android/preference/PreferenceActivity.html
	/*
	public static class Prefs1Fragment extends PreferenceFragment {

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			
			// Make sure default values are applied. In a real app, you would
			// want this in a shared function that is used to retrieve the
			// SharedPreferences wherever they are needed.
			PreferenceManager.setDefaultValues(getActivity(),
					R.xml.preferences, false);

			// Load the preferences from an XML resource
			addPreferencesFromResource(R.xml.preferences);
			
		}
		
	}
	*/
	
	// -- code so that the 'to' address can be displayed in the settings dialog --
	// based on http://developer.android.com/guide/topics/ui/settings.html
	// (even though it's not very clear what has to happen, and we're using lots of deprecated methods)
	
	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		if (key.equals(KEY_PREF_TO)) {
			Preference pref = findPreference(key);
			// update the summary
			String def = getResources().getString(R.string.pref_to_summary);
			String computed = sharedPreferences.getString(key, def);
			pref.setSummary(computed.isEmpty() ? def : computed);
		}
		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		getPreferenceScreen().getSharedPreferences()
				.registerOnSharedPreferenceChangeListener(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		getPreferenceScreen().getSharedPreferences()
				.unregisterOnSharedPreferenceChangeListener(this);
	}
	
}
