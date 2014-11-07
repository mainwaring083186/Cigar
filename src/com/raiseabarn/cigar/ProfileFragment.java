package com.raiseabarn.cigar;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.net.Uri;
import android.os.Bundle;

import android.provider.MediaStore;

import android.support.v4.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import com.raiseabarn.cigar.R;
import com.raiseabarn.cigar.MainActivity;

public class ProfileFragment extends BaseFragment implements OnClickListener {
	private static final int IMAGE_PICKER_SELECT = 1000;
	Button changeProfilePicture, cigars, badges, wishList, friends,
			photoPreview, viewAllPhotos, seeMoreActivity;
	private ImageView profilePicture;

	/**
	 * The fragment argument representing the section number for this fragment.
	 */
	// private static final String ARG_SECTION_NUMBER = "section_number";
	public ProfileFragment() {
		super();
	}

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
		View rootView = null;
		rootView = inflater.inflate(R.layout.profile_main, container, false);

		changeProfilePicture = (Button) rootView
				.findViewById(R.id.changeProfilePicture);
		changeProfilePicture.setOnClickListener(this);
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
		viewAllPhotos = (Button) rootView.findViewById(R.id.viewAllPhotos);
		viewAllPhotos.setOnClickListener(this);
		profilePicture = (ImageView) rootView.findViewById(R.id.profilePicture);
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
		case R.id.changeProfilePicture:

			Intent i = new Intent(
					Intent.ACTION_PICK,
					android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			startActivityForResult(i, IMAGE_PICKER_SELECT);

			break;

		default:

			break;
		}
	}

	/**
	 * Photo Selection result
	 */
	public void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == IMAGE_PICKER_SELECT
				&& resultCode == Activity.RESULT_OK) {
			MainActivity activity = (MainActivity) getActivity();
			Bitmap bitmap = getBitmapFromCameraData(data, activity);
			profilePicture.setImageBitmap(bitmap);
		}

	}

	/**
	 * Scale the photo down and fit it to our image views.
	 * 
	 * "Drastically increases performance" to set images using this technique.
	 * Read more:http://developer.android.com/training/camera/photobasics.html
	 */

	private void setFullImageFromFilePath(String imagePath) {
		// Get the dimensions of the View
		int targetW = profilePicture.getWidth();
		int targetH = profilePicture.getHeight();

		// Get the dimensions of the bitmap
		BitmapFactory.Options bmOptions = new BitmapFactory.Options();
		bmOptions.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(imagePath, bmOptions);
		int photoW = bmOptions.outWidth;
		int photoH = bmOptions.outHeight;

		// Determine how much to scale down the image
		int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

		// Decode the image file into a Bitmap sized to fill the View
		bmOptions.inJustDecodeBounds = false;
		bmOptions.inSampleSize = scaleFactor;
		bmOptions.inPurgeable = true;

		Bitmap bitmap = BitmapFactory.decodeFile(imagePath, bmOptions);
		profilePicture.setImageBitmap(bitmap);
	}

	/**
	 * Use for decoding camera response data.
	 * 
	 * @param data
	 * @param context
	 * @return
	 */

	public static Bitmap getBitmapFromCameraData(Intent data, Context context) {
		Uri selectedImage = data.getData();
		String[] filePathColumn = { MediaStore.Images.Media.DATA };
		Cursor cursor = context.getContentResolver().query(selectedImage,
				filePathColumn, null, null, null);
		cursor.moveToFirst();
		int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
		String picturePath = cursor.getString(columnIndex);
		cursor.close();
		return BitmapFactory.decodeFile(picturePath);
	}

}