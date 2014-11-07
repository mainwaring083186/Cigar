package com.raiseabarn.cigar;

import com.raiseabarn.cigar.R;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;

import android.support.v4.app.FragmentManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;

public class MainActivity extends ActionBarActivity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks,
		BaseFragment.OnFragmentInteractionListener {
	/**
	 * Actions
	 */
	public static final int SELECT_PHOTO_ACTION = 0;

	/**
	 * Fragment Identifiers
	 */
	public static final int SMOKE_ROOM_FRAGMENT = 0;
	public static final int FRIENDS_FRAGMENT = 1;
	public static final int SEARCH_FRAGMENT = 2;
	public static final int PROFILE_FRAGMENT = 3;
	public static final int NOTIFICATION_FRAGMENT = 4;
	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {

		// update the main content by replacing fragments
		FragmentManager fragmentManager = getSupportFragmentManager();
		BaseFragment targetFragment = null;

		// Populate the fragment
		switch (position) {
		case SMOKE_ROOM_FRAGMENT: {
			targetFragment = SmokeRoomFragment.newInstance(position + 1);
			break;
		}
		case FRIENDS_FRAGMENT: {
			targetFragment = FriendsFragment.newInstance(position + 1);
			break;
		}
		case SEARCH_FRAGMENT: {
			targetFragment = SearchFragment.newInstance(position + 1);
			break;
		}
		case PROFILE_FRAGMENT: {
			targetFragment = ProfileFragment.newInstance(position + 1);
			break;
		}
		case NOTIFICATION_FRAGMENT: {
			targetFragment = NotificationFragment.newInstance(position + 1);
			break;
		}
		default:
			break;
		}

		// Select the fragment.
		fragmentManager.beginTransaction()
				.replace(R.id.container, targetFragment).commit();
	}

	/*
	 * @Override public void onNavigationDrawerItemSelected(int position) { //
	 * update the main content by replacing fragments FragmentManager f =
	 * getSupportFragmentManager(); switch (position) { case 0:
	 * 
	 * f.beginTransaction() .replace(R.id.container,
	 * SmokeRoomFragment.newInstance(position + 1)) .commit(); break; case 1:
	 * 
	 * f.beginTransaction() .replace(R.id.container,
	 * FriendsFragment.newInstance(position + 1)).commit(); break; case 2:
	 * 
	 * f.beginTransaction() .replace(R.id.container,
	 * SearchFragment.newInstance(position + 1)).commit(); break; case 3:
	 * 
	 * f.beginTransaction() .replace(R.id.container,
	 * ProfileFragment.newInstance(position + 1)).commit(); break; case 4:
	 * 
	 * f.beginTransaction() .replace(R.id.container,
	 * NotificationFragment.newInstance(position + 1)) .commit(); break;
	 * 
	 * }
	 * 
	 * }
	 */

	public void onSectionAttached(int number) {
		switch (number) {
		case 1:
			mTitle = getString(R.string.smoke_room);
			break;
		case 2:
			mTitle = getString(R.string.friends);
			break;
		case 3:
			mTitle = getString(R.string.search);
			break;
		case 4:
			mTitle = getString(R.string.profile);
			break;
		case 5:
			mTitle = getString(R.string.notification);
			break;
		case 6:
			mTitle = getString(R.string.settings);
			break;
	
		}
	}

	public void restoreActionBar() {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.main, menu);

			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			FragmentManager f = getSupportFragmentManager();
			NavigationDrawerFragment.mDrawerToggle
			.setDrawerIndicatorEnabled(false);

	f.beginTransaction().replace(R.id.container, new Setting())
			.addToBackStack(null).commit();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		// turn on the Navigation Drawer image;
		// this is called in the LowerLevelFragments
		NavigationDrawerFragment.mDrawerToggle.setDrawerIndicatorEnabled(true);
	}

	/**
	 * Handle Incoming messages from contained fragments.
	 */

	@Override
	public void onFragmentInteraction(Uri uri) {

	}

	@Override
	public void onFragmentInteraction(String id) {

	}

	@Override
	public void onFragmentInteraction(int actionId) {

	}

}
