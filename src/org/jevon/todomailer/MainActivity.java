package org.jevon.todomailer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {
	
	private static final int RESULT_SETTINGS = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// from http://viralpatel.net/blogs/android-preferences-activity-example/
		switch (item.getItemId()) {
			case R.id.action_settings:
				Intent i = new Intent(this, SettingsActivity.class);
				startActivityForResult(i, RESULT_SETTINGS);		// this allows us to do onActivityResult() as a callback..?
				break;
		}
		
		// TODO Auto-generated method stub
		return super.onOptionsItemSelected(item);
	}

	/**
	 * User has clicked the "Send" button. The plan is:
	 * 
	 * <ol>
	 *   <li>In the background, send the message (maybe using an ongoing notification to display progress)
	 *   <li>Reset the current view parameters
	 * </ol>
	 * 
	 * @param view
	 */
	public void sendMessage(View view) {
		// no need to redirect to a separate view; we can just display a popup box "done!"
		// and then reset the existing values
	}

}
