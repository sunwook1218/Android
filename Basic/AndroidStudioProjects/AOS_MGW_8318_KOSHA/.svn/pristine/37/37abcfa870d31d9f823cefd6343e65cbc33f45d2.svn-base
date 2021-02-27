package com.hs.mobile.gw.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import androidx.core.content.FileProvider;
import android.text.TextUtils;

public class ImageHelper {
	public static final int CAMERA = 0;
	public static final int ALBUM = 1;
	public static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	public static final int GET_ALBUM_ACTIVITY_REQUEST_CODE = 200;

	public static final int MEDIA_TYPE_IMAGE = 1;
	public static final int MEDIA_TYPE_VIDEO = 2;
	private static Uri fileUri;
	private static File s_mediaFile;

	/**
	 * Create a file Uri for saving an image or video
	 * 
	 * @param a
	 */
	public static Uri getOutputMediaFileUri(Activity a, int type) {
		// SEOJAEHWA : 기존 코드는 보호하고 'N' 이상 버전에서는 FileProvider 로 접근
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
			String authority = a.getPackageName() + ".fileprovider";
			Debug.trace("authority: " + authority);
			return FileProvider.getUriForFile(a, authority, getOutputMediaFile(a, type));
		}
		return Uri.fromFile(getOutputMediaFile(a, type));
	}

	/**
	 * Create a File for saving an image or video
	 * 
	 * @param a
	 */
	private static File getOutputMediaFile(Activity a, int type) {
		// To be safe, you should check that the SDCard is mounted
		// using Environment.getExternalStorageState() before doing this.

		File mediaStorageDir = new File(a.getExternalCacheDir().getAbsolutePath());
		// This location works best if you want the created images to be shared
		// between applications and persist after your app has been uninstalled.

		// Create the storage directory if it does not exist

		// Create a media file name
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
		if (type == MEDIA_TYPE_IMAGE) {
			s_mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");
		} else if (type == MEDIA_TYPE_VIDEO) {
			s_mediaFile = new File(mediaStorageDir.getPath() + File.separator + "VID_" + timeStamp + ".mp4");
		} else {
			return null;
		}

		return s_mediaFile;
	}

	// public static void showSelectDialog(final Activity a) {
	// new AlertDialog.Builder(a).setItems(R.array.get_picture_array, new
	// DialogInterface.OnClickListener() {
	// @Override
	// public void onClick(DialogInterface dialog, int which) {
	// switch (which) {
	// case CAMERA:
	// Intent intentCamera = new
	// Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
	// fileUri = getOutputMediaFileUri(a, MEDIA_TYPE_IMAGE);
	// intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
	// a.startActivityForResult(intentCamera,
	// CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
	// dialog.dismiss();
	// break;
	// case ALBUM:
	// Intent intentAlbum = new Intent(Intent.ACTION_PICK,
	// android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
	// a.startActivityForResult(intentAlbum, GET_ALBUM_ACTIVITY_REQUEST_CODE);
	// dialog.dismiss();
	// break;
	// }
	// }
	// }).setNegativeButton(R.string.cancel_string, null).show();
	// }

	public static Uri getLastCaptureImageUri(Activity a) {
		// Uri uri = null;
		// String[] IMAGE_PROJECTION = { MediaStore.Images.ImageColumns.DATA,
		// MediaStore.Images.ImageColumns._ID, };
		//
		// try {
		// Cursor cursorImages =
		// a.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
		// IMAGE_PROJECTION, null, null,
		// null);
		// if (cursorImages != null && cursorImages.moveToLast()) {
		// uri = Uri.parse(cursorImages.getString(0)); // 경로
		// int id = cursorImages.getInt(1); // 아이디
		// cursorImages.close(); // 커서 사용이 끝나면 꼭 닫아준다.
		// }
		// } catch (Exception e) {
		// Debug.trace(e.getMessage());
		// }
		Uri uri = null;
		if (s_mediaFile.exists()) {
			uri = Uri.fromFile(s_mediaFile);
		}
		return uri;
	}

	public static String getRealPathFromURI(Activity context, Uri contentUri) {
		String result = "";
		boolean isok = false;

		Cursor cursor = null;
		try {
			String[] proj = {  };
			cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
			if (cursor != null) {
				int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
				cursor.moveToFirst();
				result = cursor.getString(column_index);
				isok = true;
			}
		} finally {
			if (cursor != null) {
				cursor.close();
			}
		}

		return isok ? result : "";
	}

	public static Bitmap resizeBitmap(int nStandardWidth, int nStandardHeight, String strFilePath) {
		if (TextUtils.isEmpty(strFilePath)) {
			return null;
		}
		Options opts = new Options();
		opts.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(strFilePath, opts);

		int ninusWidth = opts.outWidth - nStandardWidth;
		int ninusHeight = opts.outHeight - nStandardHeight;

		Bitmap bmp = null;
		if (ninusWidth < 0 && ninusHeight < 0) {
			bmp = autoRotate(strFilePath, null);
		} else {

			Options optResize = new Options();
			optResize.inSampleSize = selectOptsByShorterSize(opts.outWidth, opts.outHeight, nStandardWidth, nStandardHeight);
			bmp = autoRotate(strFilePath, optResize);
		}

		return bmp;
	}

	public static Bitmap resizeBitmap(Resources res, int nStandardWidth, int nStandardHeight, int nRes) {
		Options opts = new Options();
		opts.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(res, nRes, opts);

		int ninusWidth = opts.outWidth - nStandardWidth;
		int ninusHeight = opts.outHeight - nStandardHeight;

		Bitmap bmp = null;
		if (ninusWidth < 0 && ninusHeight < 0) {
			bmp = BitmapFactory.decodeResource(res, nRes);
		} else {

			Options optResize = new Options();
			optResize.inSampleSize = selectOptsByShorterSize(opts.outWidth, opts.outHeight, nStandardWidth, nStandardHeight);
			bmp = BitmapFactory.decodeResource(res, nRes, optResize);
		}

		return bmp;
	}

	public static int selectOptsByShorterSize(int nSrcWidth, int nSrcHeight, int nDstWidth, int nDstHeight) {
		int nRet = 1;

		if (nSrcWidth > nSrcHeight) {
			while (nSrcHeight > nDstHeight) {
				nSrcHeight = nSrcHeight / nRet;
				nRet += 1;
			}
		} else {
			while (nSrcWidth > nDstWidth) {
				nSrcWidth = nSrcWidth / nRet;
				nRet += 1;
			}
		}
		return nRet;
	}

	public static Bitmap autoRotate(String strImagePath, Options opts) {
		ExifInterface exif;
		try {
			exif = new ExifInterface(strImagePath);
			int exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
			int exifDegree = exifOrientationToDegrees(exifOrientation);

			final Bitmap bitmap = rotate(BitmapFactory.decodeFile(strImagePath, opts), exifDegree);
			return bitmap;
		} catch (IOException e) {
			Debug.trace(e.getMessage());
		} catch (OutOfMemoryError ex) {
			Debug.trace(ex.getMessage());
		}
		return null;
	}

	public static int exifOrientationToDegrees(int exifOrientation) {
		if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) {
			return 90;
		} else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180) {
			return 180;
		} else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) {
			return 270;
		} else {
			return 0;
		}
	}

	public static Bitmap rotate(Bitmap b, int degrees) {
		if (degrees != 0 && b != null) {
			Matrix m = new Matrix();
			m.setRotate(degrees, (float) b.getWidth() / 2, (float) b.getHeight() / 2);
			try {
				Bitmap b2 = Bitmap.createBitmap(b, 0, 0, b.getWidth(), b.getHeight(), m, true);
				if (b != b2) {
					b.recycle();
					b = b2;
				}
			} catch (OutOfMemoryError ex) {
				Debug.trace(ex.getMessage());
			}
		}
		return b;
	}

	public static Bitmap getCroppedCircleBitmap(BitmapDrawable bitmapDrawable, int nBorderWidth, int nColor) {
		return getCroppedCircleBitmap(bitmapDrawable.getBitmap(), nBorderWidth, nColor);
	}

	public static Bitmap getCroppedCircleBitmap(Bitmap bitmap, int nBorderWidth, int nColor) {
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final Paint borderPaint = new Paint();
		borderPaint.setColor(nColor);
		borderPaint.setStrokeWidth(nBorderWidth);
		borderPaint.setStyle(Style.STROKE);
		borderPaint.setAntiAlias(true);

		final Paint paint = new Paint();
		paint.setAntiAlias(true);
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

		canvas.drawARGB(0, 0, 0, 0);
		canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2, bitmap.getWidth() / 2 - nBorderWidth, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2, bitmap.getWidth() / 2 - nBorderWidth / 2, borderPaint);
		return output;
	}
	
	public static void SaveBitmapToFileCache(Bitmap bitmap, String strFilePath) {
        
        File fileCacheItem = new File(strFilePath);
        OutputStream out = null;
 
        try
        {
            fileCacheItem.createNewFile();
            out = new FileOutputStream(fileCacheItem);
 
            bitmap.compress(CompressFormat.JPEG, 100, out);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                out.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
  }
}
