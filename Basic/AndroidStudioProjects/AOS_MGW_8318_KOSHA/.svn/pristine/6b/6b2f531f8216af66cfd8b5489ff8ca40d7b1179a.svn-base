package com.hs.mobile.gw;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;

import com.hs.mobile.gw.ext.ViewModel;
import com.hs.mobile.gw.ext.vac.VacHelper;
import com.hs.mobile.gw.ext.vpn.VPNConnectHelper;
import com.hs.mobile.gw.fragment.AddCommandFragment;
import com.hs.mobile.gw.fragment.AddTopicFragment;
import com.hs.mobile.gw.fragment.CommonFragment;
import com.hs.mobile.gw.fragment.CustomWebViewFragment;
import com.hs.mobile.gw.fragment.DocViewFragment;
import com.hs.mobile.gw.fragment.FavoriteListFragment;
import com.hs.mobile.gw.fragment.GroupInfoFragment;
import com.hs.mobile.gw.fragment.HomeFragment;
import com.hs.mobile.gw.fragment.HtmlViewFragment;
import com.hs.mobile.gw.fragment.IPreBackKeyListener;
import com.hs.mobile.gw.fragment.MemberSelectFragment;
import com.hs.mobile.gw.fragment.SquareContentsDetailFragment;
import com.hs.mobile.gw.fragment.SquareContentsFragment;
import com.hs.mobile.gw.fragment.SquareSearchFragment;
import com.hs.mobile.gw.fragment.SquareSettingFragment;
import com.hs.mobile.gw.fragment.mailwrite.MailWriteFragment;
import com.hs.mobile.gw.fragment.square.file.SquareFileFragment;
import com.hs.mobile.gw.fragment.square.makegroup.MakeNewWorkGroupFragment;
import com.hs.mobile.gw.fragment.square.order.SquareOrderFragment;
import com.hs.mobile.gw.fragment.square.searchresult.SquareSearchResultFragment;
import com.hs.mobile.gw.fragment.squareplus.SpContentWriteFragment;
import com.hs.mobile.gw.fragment.squareplus.SpContentsDetailFragment;
import com.hs.mobile.gw.fragment.squareplus.SpContentsFragment;
import com.hs.mobile.gw.fragment.squareplus.SpContentsSubFragment;
import com.hs.mobile.gw.fragment.squareplus.SpContentsSubFragment.ContentsViewType;
import com.hs.mobile.gw.fragment.squareplus.SpFileFragment;
import com.hs.mobile.gw.fragment.squareplus.SpFolderSelectFragment;
import com.hs.mobile.gw.fragment.squareplus.SpMemberSelectFragment;
import com.hs.mobile.gw.fragment.squareplus.SpNoticeFragment;
import com.hs.mobile.gw.fragment.squareplus.SpOpinionModifyFragment;
import com.hs.mobile.gw.fragment.squareplus.SpPopularTagFragment;
import com.hs.mobile.gw.fragment.squareplus.SpSearchFragment;
import com.hs.mobile.gw.fragment.squareplus.SpSettingFragment;
import com.hs.mobile.gw.fragment.squareplus.SpSquareInfoFragment;
import com.hs.mobile.gw.fragment.squareplus.createsquare.SpAddSquareFragment;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.openapi.square.SquareDefaultCallback;
import com.hs.mobile.gw.service.HMGWServiceHelper;
import com.hs.mobile.gw.util.AndroidUtils;
import com.hs.mobile.gw.util.INetworkFailListener;
import com.hs.mobile.gw.util.PopupUtil;
import com.hs.mobile.gw.util.RootActivity;

public class SubActivity extends RootActivity implements INetworkFailListener {

	public enum RequestCode {
	}

	public enum ResultCode {
	}

	public static final int REQ_CLOSE = 0xFF;
	public static final int RESULT_CLOSE = 0xF8;
    
	public enum SubActivityType {
		HOME_HOME(R.string.label_menu_home_home), 
		MAIL_WRITE(R.string.label_menu_mail_write), 
		ORG_CHART(R.string.label_menu_orgchart), 
		MAKE_NEW_WORK_GROUP(R.string.label_square_new_work_group_title),
		SQUARE_CONTENTS(R.string.label_square_contents), 
		SQUARE_SEARCH(R.string.label_square_menu_search), 
		SQUARE_CONTENTS_DETAIL(R.string.label_square_detail), 
		SQUARE_ADD_COMMAND(R.string.label_square_add_command_title), 
		MEMBER_SELECT(R.string.label_square_group_info_member), 
		SQUARE_ADD_TOPIC(R.string.label_square_add_topic_title), 
		SQUARE_SETTING(R.string.label_menu_settings), 
		SQUARE_GROUP_INFO(R.string.label_square_group_info), 
		CUSTOM_WEBVIEW(R.string.dummy_string), 
		SQUARE_FAVORITE_LIST(R.string.label_square_menu_favorite), 
		DOC_VIEWER(R.string.dummy_string), 
		HTML_VIEWER(R.string.dummy_string), 
		SQUAR_SEARCH_RESULT(R.string.dummy_string), 
		SQUARE_ORDER(R.string.label_square_menu_command), 
		SQUARE_FILE(R.string.label_square_menu_file),
		//SquarePlus
		SP_CREATE(R.string.label_sp_create), 
		SP_CONTENTS(R.string.label_sp_contents), 
		SP_SEARCH(R.string.label_squareplus_menu_search),
		SP_POPULAR_TAG(R.string.label_squareplus_menu_popular_tag),
		SP_SETTING(R.string.label_menu_settings), 
		SP_CONTENTS_DETAIL(R.string.label_square_detail),
		SP_SQUARE_INFO(R.string.label_square_group_info),
		SP_MEMBER_SELECT(R.string.label_square_group_info_member),
		SP_CONTENTS_WORKFEED_NOTICE(R.string.label_squareplus_menu_notice),
		SP_CONTENTS_WORKFEED(R.string.label_squareplus_menu_workfeed),
		SP_CONTENTS_FAVORITE(R.string.label_squareplus_menu_favorite),
		SP_CONTENTS_MENTION(R.string.label_squareplus_menu_mention),
		SP_CONTENTS_FILE(R.string.label_squareplus_menu_file),
		SP_CONTENTS_WRITE(R.string.label_squareplus_write), 
		SP_OPNION_WRITE(R.string.label_squareplus_opinion_write),
		SP_SEARCH_RESULT(R.string.dummy_string),
		SP_POPULAR_TAG_RESULT(R.string.dummy_string),
		SP_GROUP_FOLDER(R.string.label_squareplus_groupfolder_select)
		;

		private int mTitleResources;

		SubActivityType(int nTitleResource) {
			mTitleResources = nTitleResource;
		}

		public int getTitleRes() {
			return mTitleResources;
		}
	}

	public SubActivity getSubActivity() {
		return this;
	}

	@Override
	public void onDestroy() {
        ViewModel.isLiveSubAct = false;
        if(!ViewModel.isAllLiveAct()){
			// ÇÊ¼ö·Î Á¾·áÇØ¾ßÇÒ ±â´É
        }
		// if (getModel().getLoadingProgressDialog() != null &&
		// getModel().getLoadingProgressDialog().isShowing()) {
		// getModel().getLoadingProgressDialog().cancel();
		// }
		// if (getModel().getProgressDialog() != null &&
		// getModel().getProgressDialog().isShowing()) {
		// getModel().getProgressDialog().cancel();
		// }
		MainModel.getInstance().setCurrentSubActivity(null);
        clearApplicationCache(null);
		super.onDestroy();
	}

	public static final String INTENT_KEY_FRAGMENT_NAME = "fragmentName";
	public static final String INTENT_KEY_VALUE = "value";
	private SubActivityType mSubActivityType;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// getActionBar().setLogo(R.drawable.btn_backward);
		// getActionBar().setHomeButtonEnabled(true);
		setContentView(R.layout.activity_sub);
		mSubActivityType = SubActivityType.valueOf(getIntent().getStringExtra("fragmentName"));
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		CommonFragment f = null;
		// tkofs
        ViewModel.backKey_Lock = false;
		switch (mSubActivityType) {
		case HOME_HOME:
			f = new HomeFragment();
			break;
		case MAIL_WRITE:
			ViewModel.backKey_Lock = true;
			f = new MailWriteFragment();
			break;
		case ORG_CHART:
			f = new CustomWebViewFragment();
			break;
		case MAKE_NEW_WORK_GROUP:
			f = new MakeNewWorkGroupFragment();
			break;
		case SQUARE_CONTENTS:
			f = new SquareContentsFragment();
			break;
		case SQUARE_SEARCH:
			f = new SquareSearchFragment();
			break;
		case SQUARE_CONTENTS_DETAIL:
			f = new SquareContentsDetailFragment();
			break;
		case SQUARE_ADD_COMMAND:
			f = new AddCommandFragment();
			break;
		case MEMBER_SELECT:
			f = new MemberSelectFragment();
			break;
		case SQUARE_ADD_TOPIC:
			f = new AddTopicFragment();
			break;
		case SQUARE_SETTING:
			f = new SquareSettingFragment();
			break;
		case SQUARE_GROUP_INFO:
			f = new GroupInfoFragment();
			break;
		case CUSTOM_WEBVIEW:
			f = new CustomWebViewFragment();
			break;
		case SQUARE_FAVORITE_LIST:
			f = new FavoriteListFragment();
			break;
		case DOC_VIEWER:
			f = new DocViewFragment();
			MainModel.getInstance().setDocViewer((DocViewFragment) f);
			break;
		case HTML_VIEWER:
			f = new HtmlViewFragment();
			MainModel.getInstance().setHtmlViewer((HtmlViewFragment) f);
			overridePendingTransition(R.anim.sliding_up, R.anim.stay);
			break;
		case SQUAR_SEARCH_RESULT:
			f = new SquareSearchResultFragment();			
			break;
		case SQUARE_ORDER:
			f = new  SquareOrderFragment();
			break;
		case SQUARE_FILE:
			f = new SquareFileFragment();
			break;
		// SquarePLUS
		case SP_CREATE:
			f = new SpAddSquareFragment();
			break;
		case SP_SETTING:
			f = new SpSettingFragment();
			break;
		case SP_CONTENTS:
			f = new SpContentsFragment();
			break;
		case SP_SEARCH:
			f = new SpSearchFragment();
			break;
		case SP_CONTENTS_DETAIL:
			f = new SpContentsDetailFragment();
			break;
		case SP_CONTENTS_WORKFEED_NOTICE:
			f = new SpNoticeFragment();
			break;
		case SP_CONTENTS_WORKFEED:
		case SP_CONTENTS_FAVORITE:
		case SP_CONTENTS_MENTION:
		case SP_SEARCH_RESULT:
		case SP_POPULAR_TAG_RESULT:
			f = new SpContentsSubFragment();
			break;
		case SP_CONTENTS_FILE:
			f = new SpFileFragment();
			break;
		case SP_CONTENTS_WRITE:
			f = new SpContentWriteFragment();
			break;
		case SP_SQUARE_INFO:
			f = new SpSquareInfoFragment();
			break;
		case SP_MEMBER_SELECT:
			f = new SpMemberSelectFragment();
			break;
		case SP_OPNION_WRITE:
			f = new SpOpinionModifyFragment();
			break;
		case SP_POPULAR_TAG:
			f= new SpPopularTagFragment();
			break;
		case SP_GROUP_FOLDER:
			f= new SpFolderSelectFragment();
			break;
		default:
			break;
		}
		if (f != null) {
			f.setMainModel(getModel());
			f.setTitle(getResources(), mSubActivityType.getTitleRes());
			f.setArguments(getIntent().getBundleExtra(INTENT_KEY_VALUE));
			transaction.replace(R.id.ID_LAY_L_SUB_CONTENT, f, mSubActivityType.name());
		}
		transaction.commit();


	}

	@Override
    protected void onStart() {
        ViewModel.isLiveSubAct = true;
        super.onStart();
    }

	@Override
	protected void onStop() {
		super.onStop();
	}


	@Override
	protected void onResume() {
		ViewModel.Log("SubAct blue 해제", "bluetooth");
		SquareDefaultCallback.setDefaultNetworkFailListener(this);
		// if (getModel().getLoadingProgressDialog() != null &&
		// getModel().getLoadingProgressDialog().isShowing()) {
		// getModel().getLoadingProgressDialog().cancel();
		// }
		// if (getModel().getProgressDialog() != null &&
		// getModel().getProgressDialog().isShowing()) {
		// getModel().getProgressDialog().cancel();
		// }
		// getModel().getProgressDlg().setActivity(this);
		// getModel().getLoadingProgressDialog().setOwnerActivity(this);
		// getActionBar().setTitle(mSubActivityType.getTitleRes());
		super.onResume();
	}

	@Override
	protected void onPause() {

		super.onPause();

		/* tkofs if(mSubActivityType.equals(mSubActivityType.HTML_VIEWER)){
			this.overridePendingTransition(R.anim.stay, R.anim.sliding_down);
		}else if (onStartCount > 1) {
			this.overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);

		} else if (onStartCount == 1) {
			onStartCount++;
		}*/
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			onBackPressed();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onBackPressed() {
		if (getSupportFragmentManager().findFragmentById(R.id.ID_LAY_L_SUB_CONTENT) != null
				&& getSupportFragmentManager().findFragmentById(R.id.ID_LAY_L_SUB_CONTENT) instanceof IPreBackKeyListener) {
			IPreBackKeyListener backKeyListener = (IPreBackKeyListener) getSupportFragmentManager().findFragmentById(R.id.ID_LAY_L_SUB_CONTENT);
			if (backKeyListener.onPreBackKeyPressed()) {
				super.onBackPressed();
            } else {
                // tkofs || 팝업 웹뷰는 입력폼이고 뒤로가기시 취소 confirm이 뜨기 때문에 종료 알럿은 띄우기 않는다.
                if( !MainModel.getInstance().isPopupMode() && ViewModel.backKey_Lock ) {
                    return;
                }else if(MainModel.getInstance().isPopupMode()){
                    super.onBackPressed();
                    return;
                }
                // tkofs 백버튼 종료
                try {
                    final Context mc = this;
                    new AlertDialog.Builder(this).setTitle(R.string.menu_exit).setMessage(R.string.alert_message_app_exit)
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
									ViewModel.Log("업무 종료 SubActivity", "tkofs_");
									if(!ViewModel.apiName.VPN.isApiPass()) {
										ViewModel.Log("VPN 종료 SubActivity", "tkofs_vpn");
										VPNConnectHelper.sslvpnDisconnect();
									}
									if(!ViewModel.apiName.VAC.isApiPass()) {
										ViewModel.Log("VAC 종료 SubActivity", "tkofs_vac");
										VacHelper.release();
									}
                                    // 앱 인증 추가 화면인 ( Permission으로 안돌아가고 바로 앱 종료 ) 18.12.07 likearts
                                    AndroidUtils.deleteSaveFolder(null);
                                    //moveTaskToBack(true);

									if(ViewModel.apiName.VPN.isApiPass()) {
										finishAffinity();
									}
                                    //finishAffinity();
                                    //finish();
                                    //android.os.Process.killProcess(android.os.Process.myPid());

                                }
                            }).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    }).create().show();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }

    }

	@Override
	public void onNetworkFail(String strMsg) {
		if (TextUtils.isEmpty(strMsg)) {
			PopupUtil.showDialog(this, R.string.message_not_connection);
		} else {
			PopupUtil.showDialog(this, strMsg);
		}
	}
	
	// tkofs
    public void mOnPopupClick(View v) {
        //데이터 담아서 팝업(액티비티) 호출
		ViewModel.Log(HMGWServiceHelper.mgw_menu, "tkofs_menu_grid");
        Intent intent = new Intent(this, LnbActivity.class);
        intent.putExtra("activity", "Main");
        startActivityForResult(intent, 1);
        finish();
    }
    // 앱 캐쉬 삭제 tkofs
    private void clearApplicationCache(java.io.File dir) {
        if (dir == null) dir = getCacheDir();
        if (dir == null) return;

        java.io.File[] children = dir.listFiles();
        try {
            for (int i = 0; i < children.length; i++)
                if (children[i].isDirectory())
                    clearApplicationCache(children[i]);
                else children[i].delete();
        } catch (Exception e) {
        }
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == MainModel.REQ_FROM_SP_CONTENTS_DETAIL) {
			if (resultCode == RESULT_OK) {
				Bundle bundle = data.getBundleExtra(INTENT_KEY_VALUE);
				if (bundle.getSerializable(SpContentsSubFragment.ARG_KEY_CONTENTS_VIEW_TYPE) != null) {
					ContentsViewType currentContentsViewType = (ContentsViewType) bundle
							.getSerializable(SpContentsSubFragment.ARG_KEY_CONTENTS_VIEW_TYPE);
					if (currentContentsViewType == ContentsViewType.POPULAR_TAG) {
						FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
						CommonFragment f = new SpContentsSubFragment();
						if (f != null) {
							f.setMainModel(getModel());
							f.setTitle(getResources(), mSubActivityType.getTitleRes());
							f.setArguments(bundle);
							transaction.replace(R.id.ID_LAY_L_SUB_CONTENT, f, mSubActivityType.name());
						}
						transaction.commit();
					}
				}
			}
		}
        MainModel.getInstance().setNavibarVisible(false); // tkofs
	}
    @Override
    public void exitApp(String msg){
        super.exitApp(msg);
    }
}
