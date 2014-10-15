package com.raiseabarn.cigar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class AllBadges extends Fragment implements OnClickListener {
	Button badge;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.all_badges, container, false);

		badge = (Button) rootView.findViewById(R.id.badges);
		badge.setOnClickListener(this);
		setHasOptionsMenu(true);
		// update the actionbar to show the up carat/affordance
		getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
		return rootView;
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

		case R.id.badges:
			NavigationDrawerFragment.mDrawerToggle
					.setDrawerIndicatorEnabled(false);

			f.beginTransaction().replace(R.id.container, new Badge())
					.addToBackStack(null).commit();

			break;

		default:

			break;
		}
	}
}
