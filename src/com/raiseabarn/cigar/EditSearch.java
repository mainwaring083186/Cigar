package com.raiseabarn.cigar;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

public class EditSearch extends Fragment {
	Spinner editSearchSpinner;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.edit_search,
				container, false);
		
		setHasOptionsMenu(true);
		// update the actionbar to show the up carat/affordance
		getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
		editSearchSpinner = (Spinner) rootView.findViewById(R.id.editSearchSpinner);
		
		//editSearchSpinner.setPrompt("Problem Solving");
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
}