package com.raiseabarn.cigar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileFragment extends Fragment implements OnClickListener {

	Button profilePicture, cigars, badges, wishList, friends, photoPreview,
			viewAllPhotos, seeMoreActivity;
	/**
	 * The fragment argument representing the section number for this fragment.
	 */
	private static final String ARG_SECTION_NUMBER = "section_number";

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static ProfileFragment newInstance(int sectionNumber) {
		ProfileFragment fragment = new ProfileFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.profile_main, container,
				false);

		cigars = (Button) rootView.findViewById(R.id.cigars);
		cigars.setOnClickListener(this);
		badges = (Button) rootView.findViewById(R.id.badges);
		badges.setOnClickListener(this);
		wishList = (Button) rootView.findViewById(R.id.wishList);
		wishList.setOnClickListener(this);
		friends = (Button) rootView.findViewById(R.id.friends);
		friends.setOnClickListener(this);
		photoPreview = (Button) rootView.findViewById(R.id.photoPreview);
		photoPreview.setOnClickListener(this);
		
		viewAllPhotos= (Button) rootView.findViewById(R.id.viewAllPhotos);
		viewAllPhotos.setOnClickListener(this);
		return rootView;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(getArguments().getInt(
				ARG_SECTION_NUMBER));
	}

	public void onClick(View view) {
		FragmentManager f = getFragmentManager();
		switch (view.getId()) {

		case R.id.cigars:
			NavigationDrawerFragment.mDrawerToggle
					.setDrawerIndicatorEnabled(false);

			f.beginTransaction().replace(R.id.container, new Cigars())
					.addToBackStack(null).commit();

			break;
		case R.id.badges:
			NavigationDrawerFragment.mDrawerToggle
					.setDrawerIndicatorEnabled(false);
			f.beginTransaction().replace(R.id.container, new AllBadges())
					.addToBackStack(null).commit();

			break;
		case R.id.wishList:
			NavigationDrawerFragment.mDrawerToggle
					.setDrawerIndicatorEnabled(false);
			f.beginTransaction().replace(R.id.container, new WishList())
					.addToBackStack(null).commit();

			break;
		case R.id.friends:
			((MainActivity) getActivity()).onNavigationDrawerItemSelected(1);

			break;
			
		case R.id.viewAllPhotos:
			NavigationDrawerFragment.mDrawerToggle
					.setDrawerIndicatorEnabled(false);
			f.beginTransaction().replace(R.id.container, new AllPhotos())
					.addToBackStack(null).commit();

			break;
		case R.id.photoPreview:
			NavigationDrawerFragment.mDrawerToggle
					.setDrawerIndicatorEnabled(false);
			f.beginTransaction().replace(R.id.container, new Photo())
					.addToBackStack(null).commit();

			break;
		
		default:

			break;
		}
	}

}