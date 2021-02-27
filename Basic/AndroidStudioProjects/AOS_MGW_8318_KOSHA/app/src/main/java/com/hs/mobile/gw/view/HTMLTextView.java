package com.hs.mobile.gw.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LevelListDrawable;
import android.os.AsyncTask;
import androidx.core.content.ContextCompat;

import android.text.Html;
import android.text.Spanned;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.TextView;

import com.hs.mobile.gw.ext.ViewModel;
import com.hs.mobile.gw.hsuco.R;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HTMLTextView extends TextView implements Html.ImageGetter {

    private int index = -1;
    private ArrayList<int[]> imgSizeInfo;

    public HTMLTextView(Context context) {
        super(context);
    }
    public HTMLTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * HTML IMG 태그 TextView 적용
     *
     * @param source
     */
    public void setHtmlText(String source) {
        if (source.indexOf("<img") > -1) {
            index = -1;
            findImgSize(source);
        }
        Spanned spanned = Html.fromHtml(source, this, null);
        this.setText(spanned);
    }

    /**
     * HTML 상 img 태그에 width, height 적용되었을경우 해당 값 추출
     *
     * @param source
     */
    public void findImgSize(String source) {
        String ptStr1 = "(<img[^>]+>)";     // 이미지 태그의 개수를 구하기 위한용도
        String ptStr2 = "(<img[^>]+)(width)([:='\"\\s]*)([0-9]+)([a-z%]*)(['\"\\s;]*)([^>]*)(height)([:='\"\\s]*)([0-9]+)([a-z%]*)([^>]+>)";
        Pattern pt1 = Pattern.compile(ptStr1);
        Matcher m1 = pt1.matcher(source);

        imgSizeInfo = new ArrayList();
        while (m1.find()) {
            Pattern pt2 = Pattern.compile(ptStr2);
            Matcher m2 = pt2.matcher(m1.group());

            if (m2.find()) {
                int[] tempWH = {Integer.parseInt(m2.group(4)), Integer.parseInt(m2.group(10))};
                imgSizeInfo.add(tempWH);
            } else {
                imgSizeInfo.add(null);
            }
        }
    }


    /**
     * ImageGetter 구현
     *
     * @param source
     * @return
     */
    @Override
    public Drawable getDrawable(String source) {
        index++;
        ViewModel.Log("== source :: " + source + "::cnt" + index + ":: imgSizeInfo" + imgSizeInfo.size());
        //if (source.indexOf(HMGWServiceHelper.OpenAPI.SERVER_URL) > -1) {
        LevelListDrawable d = new LevelListDrawable();

        Drawable empty = ContextCompat.getDrawable(getContext(), R.drawable.bul_clip);
        d.addLevel(0, 0, empty);
        d.setBounds(0, 0, empty.getIntrinsicWidth(), empty.getIntrinsicHeight());

        new LoadImage().execute(source, d, imgSizeInfo.get(index));

        return d;
        //}
        //return null;
    }

    /**
     * 디바이스 DPI 에따른 배율 구하기
     *
     * @return
     */
    public double getRatio() {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager mgr = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        mgr.getDefaultDisplay().getMetrics(metrics);

        double r = 1;
        if (metrics.densityDpi >= 640) {
            r = 4;
        } else if (metrics.densityDpi >= 480) {
            r = 3;
        } else if (metrics.densityDpi >= 320) {
            r = 2;
        } else if (metrics.densityDpi >= 240) {
            r = 1.5;
        } else if (metrics.densityDpi >= 160) {
            r = 1;
        } else if (metrics.densityDpi >= 120) {
            r = 0.75;
        }
        return r;
    }

    class LoadImage extends AsyncTask<Object, Void, Map<String, Object>> {

        private LevelListDrawable mDrawable;

        @Override
        protected Map<String, Object> doInBackground(Object... params) {
            String source = (String) params[0];
            mDrawable = (LevelListDrawable) params[1];

            try {
                InputStream is = new URL(source).openStream();
                HashMap<String, Object> result = new HashMap();
                result.put("st", BitmapFactory.decodeStream(is));
                result.put("imgInfo", params[2]);   // img tag width, height 정보
                return result;
            } catch (FileNotFoundException e) {
                ViewModel.Log("== FileNotFoundException : " + e.toString());
            } catch (MalformedURLException e) {
                ViewModel.Log("== MalformedURLException : " + e.toString());
            } catch (IOException e) {
                ViewModel.Log("== IOException : " + e.toString());
            }
            return new HashMap<String, Object>();
        }


        @Override
        protected void onPostExecute(Map<String, Object> map) {
            Bitmap bitmap = (Bitmap) map.get("st");
            int[] imgInfo = null;
            int width = 0;
            int height = 0;

            if (bitmap != null) {
                BitmapDrawable d = new BitmapDrawable(getContext().getResources(), bitmap);

                if (map.get("imgInfo") != null) {
                    double r = getRatio();
                    imgInfo = (int[]) map.get("imgInfo");
                    width = (int) (imgInfo[0] * r);
                    height = (int) (imgInfo[1] * r);
                    ViewModel.Log("## "+imgInfo[0] + "::" +width+":: r "+r, "tkofs_img");
                    ViewModel.Log("##"+imgInfo[1] + "::" +height+":: r "+r, "tkofs_img");
                } else {
                    width = bitmap.getWidth();
                    height = bitmap.getHeight();
                    ViewModel.Log("## ::" +width+":: r ", "tkofs_img");
                    ViewModel.Log("## ::" +height+":: r ", "tkofs_img");
                }

                mDrawable.addLevel(1, 1, d);
                mDrawable.setBounds(0, 0, width, height);
                mDrawable.setLevel(1);

                CharSequence t = getText();
                setText(t);
            }
        }
    }
}
