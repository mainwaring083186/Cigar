package com.raiseabarn.cigar;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



public  class SearchFragment extends BaseFragment {
	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */
	//private static final String ARG_SECTION_NUMBER = "section_number";
	
	public SearchFragment(){
        super();
    }
 

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static SearchFragment newInstance(int sectionNumber) {
		SearchFragment fragment = new SearchFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main, container,
				false);
		TextView textView = (TextView) rootView
				.findViewById(R.id.section_label);
		textView.setText(getResources().getString(R.string.search));
		return rootView;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(getArguments().getInt(
				ARG_SECTION_NUMBER));
	}
	
}