package com.hs.mobile.gw.fragment;

import java.util.ArrayList;
import java.util.List;

import com.hs.mobile.gw.ext.ViewModel;
import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.gnb.GnbDataModel;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.SubActivity;
import com.hs.mobile.gw.openapi.square.SquareDefaultCallback;
import com.hs.mobile.gw.service.HMGWServiceHelper;
import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.util.INetworkFailListener;
import com.hs.mobile.gw.util.PopupUtil;
import com.hs.mobile.gw.util.RestAPI;
import com.hs.mobile.gw.util.RestApiProgress;
import com.hs.mobile.gw.util.RestApiProgress.IProgressListener;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import pub.devrel.easypermissions.EasyPermissions;

public class CommonFragment extends Fragment implements INetworkFailListener, IPreBackKeyListener, View.OnClickListener,
		EasyPermissions.PermissionCallbacks {
	private List<RestAPI<?>> mRestApiStack = new ArrayList<RestAPI<?>>();
	private static MainModel mMainModel;
	private String mTitle;
	private boolean mAlreadyLoaded;
	private int mContainerId;
	private String mParentTag;
	private RestApiProgress m_restApiProgress;

	public RestApiProgress getLoadingProgressHandler() {
		if (m_restApiProgress == null && getActivity() != null) {
			m_restApiProgress = new RestApiProgress(getActivity(), new IProgressListener() {

				@Override
				public void showProgress() {
					if (getActivity() != null && MainModel.getInstance().isLoadingProgressShow()) {
						getActivity().runOnUiThread(new Runnable() {
							@Override
							public void run() {
								PopupUtil.showLoading(getActivity());
							}
						});
					}
				}

				@Override
				public void onProgress(int n) {

				}

				@Override
				public void hideProgress() {
					if (getActivity() != null) {
						getActivity().runOnUiThread(new Runnable() {
							@Override
							public void run() {
								PopupUtil.hideLoading(getActivity());
							}
						});
					}
				}
			});
		}
		return m_restApiProgress;
	}

	public void setContainerId(int n) {
		mContainerId = n;
	}

	public int getContainerId() {
		return mContainerId;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		if (savedInstanceState == null && !mAlreadyLoaded) {
			mAlreadyLoaded = true;
			Debug.trace("onViewCreated", mAlreadyLoaded);
		}
	}

	public SubActivity getSubActivity() {
		if (getActivity() instanceof SubActivity) {
			return (SubActivity) getActivity();
		} else {
			return null;
		}
	}

	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String s) {
		mTitle = s;
	}

	public void setTitle(Resources r, int nTitleRes) {
		mTitle = r.getString(nTitleRes);
	}

	public void setMainModel(MainModel model) {
		if (mMainModel == null) {
			mMainModel = model;
		}
	}

	public MainModel getMainModel() {
		return mMainModel;
	}

	@Override
	public void onResume() {
		ViewModel.Log("ComFrg blue 해제", "bluetooth");
		super.onResume();
		if (!TextUtils.isEmpty(mTitle) && getActivity().getActionBar() != null) {
			getActivity().getActionBar().setTitle(mTitle);
		}
		Debug.trace("CommonFragment::onResume", mTitle);
	}

	@Override
	public void onStart() {
		super.onStart();
		Debug.trace("CommonFragment::onStart", mTitle);
		RestAPI.setLoadingProgressHandler(getLoadingProgressHandler());
	}

	@Override
	public void onStop() {
		super.onStop();
		Debug.trace("CommonFragment::onStop", mTitle);
		PopupUtil.hideLoading(getActivity());
	}

	@Override
	public void onDestroyOptionsMenu() {
		super.onDestroyOptionsMenu();
		Debug.trace("CommonFragment::onDestroyOptionsMenu", mTitle);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Debug.trace("CommonFragment::onActivityResult", mTitle, requestCode, resultCode, data);
	}

	@Override
	public void onPause() {
		super.onPause();
		Debug.trace("CommonFragment::onPause", mTitle);
	}

	public void addNetworkRequst(RestAPI<?> api) {
		mRestApiStack.add(api);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		Debug.trace("CommonFragment::Attach", mTitle);
		SquareDefaultCallback.setDefaultNetworkFailListener(this);
	}

	@Override
	public void onDetach() {
		super.onDetach();
		Debug.trace("CommonFragment::Detach", mTitle);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		resetNetwork();
		mRestApiStack = null;
		Debug.trace("CommonFragment::onDestroy", mTitle);
		PopupUtil.cancelAllDlg();
	}

	public void resetNetwork() {
		for (RestAPI api : mRestApiStack) {
			if (api.isRunning()) {
				api.cancel();
			}
		}
		mRestApiStack.clear();
	}

	public void onFragmentResume() {
		Debug.trace("CommonFragment::onFragmentResume", mTitle);
	}

	// @Override
	// public boolean onBackKey() {
	// if (getFragmentManager().getBackStackEntryCount() > 0) {
	// if (!TextUtils.isEmpty(getParentTag())
	// && TextUtils.equals(getParentTag(),
	// getFragmentManager().getBackStackEntryAt(getFragmentManager().getBackStackEntryCount()
	// - 1).getName())) {
	//
	// CommonFragment f = (CommonFragment)
	// getFragmentManager().findFragmentByTag(
	// getFragmentManager().getBackStackEntryAt(getFragmentManager().getBackStackEntryCount()
	// - 1).getName());
	// if (f != null) {
	// f.onFragmentResume();
	// }
	// }
	// }
	// Debug.trace("CommonFragment::onBackKey", mTitle);
	// return false;
	// }

	public boolean isForegroundFragment(CommonFragment f) {
		Debug.trace("CommonFragment::isForegroundFragment", mTitle);
		if (getFragmentManager().findFragmentById(getContainerId()) == f) {
			return true;
		}
		return false;
	}

	public void setParentTag(String strParentTag) {
		mParentTag = strParentTag;
	}

	public String getParentTag() {
		return mParentTag;
	}

	@Override
	public void onNetworkFail(String strMsg) {
		if (isAdded()) {
			if (TextUtils.isEmpty(strMsg)) {
				PopupUtil.showDialog(getActivity(), R.string.message_not_connection);
			} else {
				PopupUtil.showDialog(getActivity(), strMsg);
			}
		}
	}

	@Override
	public boolean onPreBackKeyPressed() {
		return true;
	}

	public static final int RC_WRITE_STORAGE_PERM = 1230;
	public static final int RC_READ_STORAGE_PERM_GET_CONTENT = 1241;
	public static final int RC_EXT_STORAGE_PERM_GET_CONTENT = 1242;
	public static final int RC_CONTACTS_PERM = 1251;

	public boolean hasWriteStoragePermission() {
		if (getContext() == null) {
			Debug.trace("Context is null. Nothing to do.");
			return false;
		}
        return EasyPermissions.hasPermissions(getContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
	}

	public void requestWriteStoragePermission() {
		requestPermissions(
				new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
				RC_WRITE_STORAGE_PERM);
	}

	public boolean hasContactsPermission() {
		if (getContext() == null) {
			Debug.trace("Context is null. Nothing to do.");
			return false;
		}
		return EasyPermissions.hasPermissions(getContext(),
				Manifest.permission.READ_CONTACTS);
	}

	public void requestContactsPermission() {
		requestPermissions(
				new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS},
				RC_CONTACTS_PERM);
	}

	@Override
	public void onRequestPermissionsResult(int requestCode,
										   @NonNull String[] permissions,
										   @NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		getMainModel().onRequestPermissionsResult(this, requestCode, permissions, grantResults);
		// EasyPermissions handles the request result.
		EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
	}

	@Override
	public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
		Debug.trace("onPermissionsGranted:" + requestCode + ":" + perms.size());
	}

	@Override
	public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
		Debug.trace( "onPermissionsDenied:" + requestCode + ":" + perms.size());

		// (Optional) Check whether the user denied any permissions and checked "NEVER ASK AGAIN."
		// This will display a dialog directing them to enable the permission in app settings.
//		if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
//			new AppSettingsDialog.Builder(this).build().show();
//		}
	}

	protected void downloadFile(String contentsId, String attachId, String targetPath) {
	if (hasWriteStoragePermission()) {
			getMainModel().downloadFile(getActivity(), contentsId, attachId, targetPath);
		}
		else {
			requestWriteStoragePermission();
		}
	}

	protected String getTargetFilePath(String strFileName, String strFileExt) {
		return getMainModel().getExternamDownloadDirectory(strFileName, strFileExt);
	}

	/**
	 * 헤더 추가
	 * @Autor tkofs
	 * @Date 20.06.23
	 * @param container
	 */
	public void createHead(View container) {
		LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = inflater.inflate(R.layout.fragment_sub_header_content, null);
		LinearLayout subContainer = container.findViewById(R.id.sub_head_container);
		subContainer.addView(v, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

		v.findViewById(R.id.BTN_SUB_LNB_OPEN).setOnClickListener(this);
		v.findViewById(R.id.BTN_SUB_HOME).setOnClickListener(this);
		v.findViewById(R.id.BTN_SUB_HOME_LY).setOnClickListener(this);
		v.findViewById(R.id.btn_sub_haeder_logo).setOnClickListener(this);

		try { // 웹뷰는 팝업으로 뜨기 때문에 헤더 날림
			if (MainModel.getInstance().isPopupMode() || MainModel.getInstance().getNavibarVisible()) {
				subContainer.setVisibility(View.GONE);
			} else {
				subContainer.setVisibility(View.VISIBLE);
			}
		} catch (Exception e) {
			Debug.trace("[CommonFragment] 헤더 숨김 에러 > " + e);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.BTN_SUB_LNB_OPEN:
				ViewModel.Log(HMGWServiceHelper.mgw_menu, "tkofs_menu_grid_commonfrag");
				GnbDataModel.mainActivity.mOnPopupClick(v);
				break;
			case R.id.BTN_SUB_HOME:
			case R.id.btn_sub_haeder_logo:
			case R.id.BTN_SUB_HOME_LY:
				MainFragment.menuListHelper.showMenuByID("home_home");
				break;
		}
	}
}
