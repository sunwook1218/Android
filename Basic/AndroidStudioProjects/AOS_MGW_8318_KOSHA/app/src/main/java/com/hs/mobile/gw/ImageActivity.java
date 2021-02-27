package com.hs.mobile.gw;

import java.io.File;

import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.util.Debug;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.ImageView;

public class ImageActivity extends Activity implements OnTouchListener {
	private String imagePath;
	private ImageView view;
	
	// These matrices will be used to move and zoom image
	Matrix matrix = new Matrix();
	Matrix savedMatrix = new Matrix();
	private Matrix savedMatrix2 = new Matrix();

	// Touch State
	static final int NONE = 0;
	static final int DRAG = 1;
	static final int ZOOM = 2;
	int mode = NONE;

	private static final int WIDTH = 0;
	private static final int HEIGHT = 1;

	// Remember some things for zooming
	PointF start = new PointF();
	PointF mid = new PointF();
	float oldDist = 1f;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.image_view);
		view = (ImageView) findViewById(R.id.imageView);
		view.setOnTouchListener(this);
		
		Bundle bun = getIntent().getExtras();
		imagePath = bun.getString("url");

		File imgFile = new File(imagePath);

		if (imgFile.exists()) {
			Bitmap imgBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath()); 
			view.setImageBitmap(imgBitmap);
			view.invalidate();
		} else {
			finish();
		}
	}
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		setImageFit(view);
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
	    super.onConfigurationChanged(newConfig);

	    switch(newConfig.orientation) {
	        case Configuration.ORIENTATION_PORTRAIT:
	        	setImageFit(view);
	            break;
	        case Configuration.ORIENTATION_LANDSCAPE:
	        	setImageFit(view);
	            break;
	    }
	}

	public void setImageFit(ImageView view) {
		// 매트릭스 값
		float[] value = new float[9];
		this.matrix.getValues(value);

		// 뷰 크기
		int width = view.getWidth();
		int height = view.getHeight();

		// 이미지 크기
		Drawable d = view.getDrawable();
		if (d == null)
			return;
		int imageWidth = d.getIntrinsicWidth();
		int imageHeight = d.getIntrinsicHeight();
		int scaleWidth = (int) (imageWidth * value[0]);
		int scaleHeight = (int) (imageHeight * value[4]);

		// 이미지가 바깥으로 나가지 않도록.
		value[2] = 0;
		value[5] = 0;

		if (imageWidth > width || imageHeight > height) {
			int target = WIDTH;
			if (imageWidth < imageHeight)
				target = HEIGHT;

			if (target == WIDTH)
				value[0] = value[4] = (float) width / imageWidth;
			if (target == HEIGHT)
				value[0] = value[4] = (float) height / imageHeight;

			scaleWidth = (int) (imageWidth * value[0]);
			scaleHeight = (int) (imageHeight * value[4]);

			if (scaleWidth > width)
				value[0] = value[4] = (float) width / imageWidth;
			if (scaleHeight > height)
				value[0] = value[4] = (float) height / imageHeight;
		}

		// 그리고 가운데 위치하도록 한다.
		scaleWidth = (int) (imageWidth * value[0]);
		scaleHeight = (int) (imageHeight * value[4]);
		if (scaleWidth < width) {
			value[2] = (float) width / 2 - (float) scaleWidth / 2;
		}
		if (scaleHeight < height) {
			value[5] = (float) height / 2 - (float) scaleHeight / 2;
		}

		matrix.setValues(value);
		view.setImageMatrix(matrix);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		ImageView view = (ImageView) v;

		// Dump touch event to log
		dumpEvent(event);

		switch (event.getAction() & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_DOWN:
			savedMatrix.set(matrix);
			start.set(event.getX(), event.getY());
			Debug.trace("ImageActivity - , mode=DRAG");
			mode = DRAG;
			break;
		case MotionEvent.ACTION_POINTER_DOWN:
			oldDist = spacing(event);
			Debug.trace("ImageActivity - oldDist=" + oldDist);
			if (oldDist > 10f) {
				savedMatrix.set(matrix);
				midPoint(mid, event);
				mode = ZOOM;
				Debug.trace("ImageActivity - mode=ZOOM");
			}
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_POINTER_UP:
			mode = NONE;
			Debug.trace("ImageActivity - mode=NONE");
			break;
		case MotionEvent.ACTION_MOVE:
			if (mode == DRAG) {
				// ...
				matrix.set(savedMatrix);
				matrix.postTranslate(event.getX() - start.x, event.getY()
						- start.y);
			} else if (mode == ZOOM) {
				float newDist = spacing(event);
				Debug.trace("ImageActivity - newDist=" + newDist);
				if (newDist > 10f) {
					matrix.set(savedMatrix);
					float scale = newDist / oldDist;
					matrix.postScale(scale, scale, mid.x, mid.y);
				}
			}
			break;
		}

		matrixTurning(matrix, view);
		view.setImageMatrix(matrix);
		return true; // indicate event was handled
	}

	/** Show an event in the LogCat view, for debugging */
	private void dumpEvent(MotionEvent event) {
		String names[] = { "DOWN", "UP", "MOVE", "CANCEL", "OUTSIDE",
				"POINTER_DOWN", "POINTER_UP", "7?", "8?", "9?" };
		StringBuilder sb = new StringBuilder();
		int action = event.getAction();
		int actionCode = action & MotionEvent.ACTION_MASK;
		sb.append("event ACTION_").append(names[actionCode]);
		if (actionCode == MotionEvent.ACTION_POINTER_DOWN
				|| actionCode == MotionEvent.ACTION_POINTER_UP) {
			sb.append("(pid ").append(
					action >> MotionEvent.ACTION_POINTER_ID_SHIFT);
			sb.append(')');
		}
		sb.append('[');
		for (int i = 0; i < event.getPointerCount(); i++) {
			sb.append('#').append(i);
			sb.append("(pid ").append(event.getPointerId(i));
			sb.append(")=").append((int) event.getX(i));
			sb.append(',').append((int) event.getY(i));
			if (i + 1 < event.getPointerCount())
				sb.append(';');
		}
		sb.append(']');
		Debug.trace("ImageActivity - sb.toString() : " + sb.toString());
	}

	/** Determine the space between the first two fingers */
	private float spacing(MotionEvent event) {
		float x = event.getX(0) - event.getX(1);
		float y = event.getY(0) - event.getY(1);
		// SEOJAEHWA: FloatMath class was deprecated in API level 22.
		return (float) Math.sqrt(x * x + y * y);
	}

	/** Calculate the mid point of the first two fingers */
	private void midPoint(PointF point, MotionEvent event) {
		float x = event.getX(0) + event.getX(1);
		float y = event.getY(0) + event.getY(1);
		point.set(x / 2, y / 2);
	}

	private void matrixTurning(Matrix matrix, ImageView view) {
		// 매트릭스 값
		float[] value = new float[9];
		matrix.getValues(value);
		float[] savedValue = new float[9];
		savedMatrix2.getValues(savedValue);

		// 뷰 크기
		int width = view.getWidth();
		int height = view.getHeight();

		// 이미지 크기
		Drawable d = view.getDrawable();
		if (d == null)
			return;
		int imageWidth = d.getIntrinsicWidth();
		int imageHeight = d.getIntrinsicHeight();
		int scaleWidth = (int) (imageWidth * value[0]);
		int scaleHeight = (int) (imageHeight * value[4]);

		// 이미지가 바깥으로 나가지 않도록.
		if (value[2] < width - scaleWidth)
			value[2] = width - scaleWidth;
		if (value[5] < height - scaleHeight)
			value[5] = height - scaleHeight;
		if (value[2] > 0)
			value[2] = 0;
		if (value[5] > 0)
			value[5] = 0;

		// 10배 이상 확대 하지 않도록
		if (value[0] > 10 || value[4] > 10) {
			value[0] = savedValue[0];
			value[4] = savedValue[4];
			value[2] = savedValue[2];
			value[5] = savedValue[5];
		}

		// 화면보다 작게 축소 하지 않도록
		if (imageWidth > width || imageHeight > height) {
			if (scaleWidth < width && scaleHeight < height) {
				int target = WIDTH;
				if (imageWidth < imageHeight)
					target = HEIGHT;

				if (target == WIDTH)
					value[0] = value[4] = (float) width / imageWidth;
				if (target == HEIGHT)
					value[0] = value[4] = (float) height / imageHeight;

				scaleWidth = (int) (imageWidth * value[0]);
				scaleHeight = (int) (imageHeight * value[4]);

				if (scaleWidth > width)
					value[0] = value[4] = (float) width / imageWidth;
				if (scaleHeight > height)
					value[0] = value[4] = (float) height / imageHeight;
			}
		}

		// 원래부터 작은 얘들은 본래 크기보다 작게 하지 않도록
		else {
			if (value[0] < 1)
				value[0] = 1;
			if (value[4] < 1)
				value[4] = 1;
		}

		// 그리고 가운데 위치하도록 한다.
		scaleWidth = (int) (imageWidth * value[0]);
		scaleHeight = (int) (imageHeight * value[4]);
		if (scaleWidth < width) {
			value[2] = (float) width / 2 - (float) scaleWidth / 2;
		}
		if (scaleHeight < height) {
			value[5] = (float) height / 2 - (float) scaleHeight / 2;
		}

		matrix.setValues(value);
		savedMatrix2.set(matrix);
	}
}