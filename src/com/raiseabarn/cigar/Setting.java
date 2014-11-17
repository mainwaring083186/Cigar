package com.raiseabarn.cigar;

import android.app.Activity;

import android.os.Bundle;

import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Setting extends BaseFragment implements OnClickListener {
	Button editSearchButton, editAccountButton, editNotificationButton,
			helpAndSupportButton;

	/**
	 * The fragment argument representing the section number for this fragment.
	 */
	// private static final String ARG_SECTION_NUMBER = "section_number";
	public Setting() {
		super();
	}

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static Setting newInstance(int sectionNumber) {
		Setting fragment = new Setting();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.settings, container, false);

		setHasOptionsMenu(true);
		// update the actionbar to show the up carat/affordance
		getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
		editSearchButton = (Button) rootView
				.findViewById(R.id.editSearchButton);
		editSearchButton.setOnClickListener(this);
		editAccountButton = (Button) rootView
				.findViewById(R.id.editAccountButton);
		editAccountButton.setOnClickListener(this);
		editNotificationButton = (Button) rootView
				.findViewById(R.id.editNotificationButton);
		editNotificationButton.setOnClickListener(this);
		helpAndSupportButton = (Button) rootView
				.findViewById(R.id.helpAndSupportButton);
		helpAndSupportButton.setOnClickListener(this);

		return rootView;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(getArguments().getInt(
				ARG_SECTION_NUMBER));
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Get item selected and deal with it
		switch (item.getItemId()) {
		case android.R.id.home:
			getActivity().onBackPressed();
			return true;
		}
		return false;
	}

	public void onClick(View view) {
		FragmentManager f = getFragmentManager();
		switch (view.getId()) {

		case R.id.editSearchButton:
			NavigationDrawerFragment.mDrawerToggle
					.setDrawerIndicatorEnabled(false);

			f.beginTransaction().replace(R.id.container, new EditSearch())
					.addToBackStack(null).commit();

			break;
		case R.id.editAccountButton:
			NavigationDrawerFragment.mDrawerToggle
					.setDrawerIndicatorEnabled(false);
			f.beginTransaction().replace(R.id.container, new EditAccount())
					.addToBackStack(null).commit();

			break;
		case R.id.editNotificationButton:
			NavigationDrawerFragment.mDrawerToggle
					.setDrawerIndicatorEnabled(false);
			f.beginTransaction()
					.replace(R.id.container, new EditNotification())
					.addToBackStack(null).commit();

			break;
		case R.id.helpAndSupportButton:

			NavigationDrawerFragment.mDrawerToggle
					.setDrawerIndicatorEnabled(false);
			f.beginTransaction().replace(R.id.container, new WishList())
					.addToBackStack(null).commit();
			break;

		default:

			break;
		}
	}
}