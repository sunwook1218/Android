package com.hs.mobile.gw.util;

import com.hs.mobile.gw.hsuco.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.text.TextUtils;
import android.view.WindowManager.BadTokenException;
import android.widget.EditText;
import android.widget.Toast;

public class PopupUtil {

	private static ProgressDialog m_progressDialog;
	private static AlertDialog m_dialog;

	public static void cancelAllDlg() {
		if (m_dialog != null) {
			m_dialog.cancel();
		}
		if (m_progressDialog != null) {
			m_progressDialog.cancel();
		}
	}

	public static void showDialog(final Activity a, final int nRes) {
		showDialog(a, a.getResources().getString(nRes));
	}

	public static void showDialog(final Activity a, final String message) {
		showDialog(a, message, null);
	}

	public static void showDialog(final Activity a, final int nRes, final OnClickListener listener) {
		showDialog(a, a.getResources().getString(nRes), listener);
	}

	public static void showDialog(final Activity a, final String message, final OnClickListener listener) {
		showDialog(a, message, a.getString(R.string.label_confirm), listener, null, null);
	}

	public static void showDialog(final Activity a, final int nMessage, final int nPositive,
			final OnClickListener positiveListener, final int nNegative, final OnClickListener negativeListener) {
		showDialog(a, a.getString(nMessage), a.getString(nPositive), positiveListener, a.getString(nNegative),
				negativeListener);
	}

	public static void showDialog(final Activity a, final String strMessage, final String strPositive,
			final OnClickListener positiveListener, final String strNegative, final OnClickListener negativeListener) {
		a.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				try {
					if (m_dialog != null) {
						m_dialog.cancel();
					}
					Builder builder = new AlertDialog.Builder(a);
					builder.setTitle(R.string.message_confirm_title);
					if (!TextUtils.isEmpty(strPositive) && positiveListener != null) {
						builder.setPositiveButton(strPositive, positiveListener);
					} else {
						builder.setPositiveButton(R.string.message_confirm, positiveListener);
					}
					if (!TextUtils.isEmpty(strNegative) && negativeListener != null) {
						builder.setNegativeButton(strNegative, negativeListener);
					}
					builder.setMessage(strMessage);
					builder.setCancelable(false);
					m_dialog = builder.show();
				} catch (BadTokenException e) {
					Debug.trace(e.getMessage());
				}
			}
		});
	}

	public static void showOkCancelDialog(final Activity a, final int nRes, final OnClickListener listener) {
		showOkCancelDialog(a, a.getResources().getString(nRes), listener, null);
	}

	public static void showOkCancelDialog(final Activity a, final String message, final OnClickListener listener,
			final OnClickListener cancelListener) {
		a.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (m_dialog != null) {
					m_dialog.cancel();
				}
				Builder builder = new AlertDialog.Builder(a);
				builder.setTitle(R.string.message_confirm_title);
				builder.setPositiveButton(R.string.label_confirm, listener);
				builder.setNegativeButton(R.string.label_cancel, cancelListener);
				builder.setMessage(message);
				builder.setCancelable(false);
				m_dialog = builder.show();
			}
		});
	}

	public static void showOkCancelDialog(Activity activity, int nRes, OnClickListener okListener,
			OnClickListener cancelListener) {
		showOkCancelDialog(activity, activity.getResources().getString(nRes), okListener, cancelListener);
	}

	public static void showYesNoDialog(final Activity a, final int nRes, final OnClickListener listener) {
		showYesNoDialog(a, a.getResources().getString(nRes), listener);
	}

	public static void showYesNoDialog(final Activity a, final String message, final OnClickListener listener) {
		a.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (m_dialog != null) {
					m_dialog.cancel();
				}
				Builder builder = new AlertDialog.Builder(a);
				builder.setTitle(R.string.message_confirm_title);
				builder.setPositiveButton(R.string.button_name_yes, listener);
				builder.setNegativeButton(R.string.button_name_no, null);
				builder.setMessage(message);
				builder.setCancelable(false);
				m_dialog = builder.show();
			}
		});
	}

	public static void showOkCancelDialog(Activity context, String string, OnClickListener onClickListener) {
		showOkCancelDialog(context, string, onClickListener, null);
	}

	public static void showInputDialog(final Activity context,final String message,final EditText edit ,final OnClickListener onClickListener) {
		context.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (m_dialog != null) {
					m_dialog.cancel();
				}
				Builder builder = new AlertDialog.Builder(context);
				builder.setView(edit);
				builder.setPositiveButton(R.string.label_confirm, onClickListener);
				builder.setNegativeButton(R.string.label_cancel, new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.dismiss();
					}
				});
				builder.setMessage(message);
				builder.setCancelable(false);
				m_dialog = builder.show();
			}
		});
	}

	public static void showLoading(final Activity activity, final String msg) {
		synchronized (PopupUtil.class) {
			activity.runOnUiThread(new Runnable() {
				@Override
				public void run() {
					if (m_progressDialog != null) {
						hideLoading(activity);
					}
					m_progressDialog = ProgressDialog.show(activity, "", msg,  true, false);
				}
			});
		}
	}

	public static void showLoading(final Activity activity) {
		Debug.trace("PopupUtil.showLoading");
		synchronized (PopupUtil.class) {
			activity.runOnUiThread(new Runnable() {
				@Override
				public void run() {
					if (m_progressDialog != null) {
						hideLoading(activity);
					}
					m_progressDialog = ProgressDialog.show(activity, "", activity.getString(R.string.ptr_refreshing),
							true, false);
				}
			});
		}
	}

	public static void hideLoading(Activity activity) {
		Debug.trace("PopupUtil.hideLoading");
		synchronized (PopupUtil.class) {
			activity.runOnUiThread(new Runnable() {
				@Override
				public void run() {
					if (m_progressDialog != null) {
						m_progressDialog.cancel();
						m_progressDialog = null;
					}
				}
			});
		}
	}

	public static ProgressDialog getProgressDialog(Activity a) {
		if (m_progressDialog != null) {
			m_progressDialog.cancel();
		}
		m_progressDialog = ProgressDialog.show(a, "", a.getString(R.string.ptr_refreshing), true, false);
		return m_progressDialog;
	}

	public static void showToastMessage(final Activity activity, final int nStringRes) {
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(activity.getApplicationContext(), nStringRes, Toast.LENGTH_SHORT).show();
			}
		});
	}

	public static void showToastMessage(final Activity activity, final String strMsg) {
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(activity.getApplicationContext(), strMsg, Toast.LENGTH_SHORT).show();
			}
		});
	}
}
