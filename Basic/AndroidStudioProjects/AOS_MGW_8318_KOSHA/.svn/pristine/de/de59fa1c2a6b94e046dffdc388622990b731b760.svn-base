package com.hs.mobile.gw.view;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.hs.mobile.gw.hsuco.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * A generic, customizable Android ListView implementation that has 'Pull to Refresh' functionality.
 * <p/>
 * This ListView can be used in place of the normal Android android.widget.ListView class.
 * <p/>
 * Users of this class should implement OnRefreshListener and call setOnRefreshListener(..)
 * to get notified on refresh events. The using class should call onRefreshComplete() when
 * refreshing is finished.
 * <p/>
 * The using class can call setRefreshing() to set the state explicitly to refreshing. This
 * is useful when you want to show the spinner and 'Refreshing' text when the
 * refresh was not triggered by 'Pull to Refresh', for example on start.
 * <p/>
 * For more information, visit the project page:
 * https://github.com/erikwt/PullToRefresh-ListView
 *
 * @author Erik Wallentinsen <dev+ptr@erikw.eu>
 * @version 1.0.0
 */
public class PullToRefreshListView extends ListView{

    private static final float PULL_RESISTANCE                 = 1.7f;
    private static final int   BOUNCE_ANIMATION_DURATION       = 300;
    private static final int   BOUNCE_ANIMATION_DELAY          = 1;
    private static final float BOUNCE_OVERSHOOT_TENSION        = 1.0f;
    private static final int   ROTATE_ARROW_ANIMATION_DURATION = 200;

    private static enum State{
    	RESET_HEADER,
        PULL_TO_REFRESH,
        RELEASE_TO_REFRESH,
        REFRESHING
    }

    /**
     * Interface to implement when you want to get notified of 'pull to refresh'
     * events.
     * Call setOnRefreshListener(..) to activate an OnRefreshListener.
     */
    public interface OnRefreshListener{

        /**
         * Method to be called when a refresh is requested
         */
        public void onRefresh();
    }

    private static int measuredHeaderHeight;

    private boolean scrollbarEnabled;
    private boolean bounceBackHeader;
    private boolean lockScrollWhileRefreshing;
    private boolean showLastUpdatedText;
    private String  pullToRefreshText;
    private String  releaseToRefreshText;
    private String  refreshingText;
    private String  lastUpdatedText;
    private SimpleDateFormat lastUpdatedDateFormat = new SimpleDateFormat("dd/MM HH:mm");

    private float                   previousY;
    private int                     headerPadding;
    private boolean                 hasResetHeader;
    private long                    lastUpdated = -1;
    private State                   state;
    private LinearLayout            headerContainer;    
    private RelativeLayout          header;
    private LinearLayout            footerContainer;
    private RelativeLayout          footer;
    private RotateAnimation         flipAnimation;
    private RotateAnimation         reverseFlipAnimation;
    private ImageView               image;
    private ProgressBar             spinner;
    private TextView                text;
    private TextView                lastUpdatedTextView;
    private OnItemClickListener     onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;
    private OnRefreshListener       onRefreshListener;
    public String getLastUpdatedText(){
    	return lastUpdatedText;
    }
    public PullToRefreshListView(Context context){
        super(context);
        init();
    }

    public PullToRefreshListView(Context context, AttributeSet attrs){
        super(context, attrs);
        init();
    }

    public PullToRefreshListView(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
        init();
    }
/*
    @Override
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener){
        this.onItemLongClickListener = onItemLongClickListener;
    }*/

    /**
     * Activate an OnRefreshListener to get notified on 'pull to refresh'
     * events.
     *
     * @param onRefreshListener The OnRefreshListener to get notified
     */
    public void setOnRefreshListener(OnRefreshListener onRefreshListener){
        this.onRefreshListener = onRefreshListener;
    }

    /**
     * @return If the list is in 'Refreshing' state
     */
    public boolean isRefreshing(){
        return state == State.REFRESHING;
    }

    /**
     * Default is false. When lockScrollWhileRefreshing is set to true, the list
     * cannot scroll when in 'refreshing' mode. It's 'locked' on refreshing.
     *
     * @param lockScrollWhileRefreshing
     */
    public void setLockScrollWhileRefreshing(boolean lockScrollWhileRefreshing){
        this.lockScrollWhileRefreshing = lockScrollWhileRefreshing;
    }

    /**
     * Default is false. Show the last-updated date/time in the 'Pull ro Refresh'
     * header. See 'setLastUpdatedDateFormat' to set the date/time formatting.
     *
     * @param showLastUpdatedText
     */
    public void setShowLastUpdatedText(boolean showLastUpdatedText){
        this.showLastUpdatedText = showLastUpdatedText;
        if(!showLastUpdatedText) lastUpdatedTextView.setVisibility(View.GONE);
    }

    /**
     * Default: "dd/MM HH:mm". Set the format in which the last-updated
     * date/time is shown. Meaningless if 'showLastUpdatedText == false (default)'.
     * See 'setShowLastUpdatedText'.
     *
     * @param lastUpdatedDateFormat
     */
    public void setLastUpdatedDateFormat(SimpleDateFormat lastUpdatedDateFormat){
        this.lastUpdatedDateFormat = lastUpdatedDateFormat;
    }

    /**
     * Explicitly set the state to refreshing. This
     * is useful when you want to show the spinner and 'Refreshing' text when
     * the refresh was not triggered by 'pull to refresh', for example on start.
     */
    public void setRefreshing(){
        state = State.REFRESHING;
        scrollTo(0, 0);
        setUiRefreshing();
        setHeaderPadding(0);
    }

    /**
     * Set the state back to 'pull to refresh'. Call this method when refreshing
     * the data is finished.
     */
    public void onRefreshComplete(){
        state = State.PULL_TO_REFRESH;
        resetHeader();
        lastUpdated = System.currentTimeMillis();
    }

    /**
     * Change the label text on state 'Pull to Refresh'
     *
     * @param pullToRefreshText Text
     */
    public void setTextPullToRefresh(String pullToRefreshText){
        this.pullToRefreshText = pullToRefreshText;
        if(state == State.PULL_TO_REFRESH){
            text.setText(pullToRefreshText);
        }
    }

    /**
     * Change the label text on state 'Release to Refresh'
     *
     * @param releaseToRefreshText Text
     */
    public void setTextReleaseToRefresh(String releaseToRefreshText){
        this.releaseToRefreshText = releaseToRefreshText;
        if(state == State.RELEASE_TO_REFRESH){
            text.setText(releaseToRefreshText);
        }
    }

    /**
     * Change the label text on state 'Refreshing'
     *
     * @param refreshingText Text
     */
    public void setTextRefreshing(String refreshingText){
        this.refreshingText = refreshingText;
        if(state == State.REFRESHING){
            text.setText(refreshingText);
        }
    }

    public void showFooter(boolean flag){
    	int footerCnt = this.getFooterViewsCount();
    	if(flag && footerCnt == 0){
    		addFooterView(footerContainer);
    	}else if(!flag && footerCnt == 1){
    		removeFooterView(footerContainer);
    	}
    }
    int footerHeight = 0;
    private void init(){ 
        setVerticalFadingEdgeEnabled(false);

        headerContainer = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.ptr_header, null);        
        footerContainer = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.ptr_footer, null);
        header = (RelativeLayout) headerContainer.findViewById(R.id.ptr_id_header);
        footer = (RelativeLayout) footerContainer.findViewById(R.id.ptr_id_footer);
        footerHeight = footer.getHeight();
        
        text = (TextView) header.findViewById(R.id.ptr_id_text);
        lastUpdatedTextView = (TextView) header.findViewById(R.id.ptr_id_last_updated);
        image = (ImageView) header.findViewById(R.id.ptr_id_image);
        spinner = (ProgressBar) header.findViewById(R.id.ptr_id_spinner);

        pullToRefreshText = getContext().getString(R.string.ptr_pull_to_refresh);
        releaseToRefreshText = getContext().getString(R.string.ptr_release_to_refresh);
        refreshingText = getContext().getString(R.string.ptr_refreshing);
        lastUpdatedText = getContext().getString(R.string.ptr_last_updated);

        flipAnimation = new RotateAnimation(0, -180, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        flipAnimation.setInterpolator(new LinearInterpolator());
        flipAnimation.setDuration(ROTATE_ARROW_ANIMATION_DURATION);
        flipAnimation.setFillAfter(true);

        reverseFlipAnimation = new RotateAnimation(-180, 0, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        reverseFlipAnimation.setInterpolator(new LinearInterpolator());
        reverseFlipAnimation.setDuration(ROTATE_ARROW_ANIMATION_DURATION);
        reverseFlipAnimation.setFillAfter(true);
        
        addHeaderView(headerContainer);
        // Galaxy 계열에서는 이 부분이 있어도 문제가 없지만, LGE G3.cat6에서는 index outof bounds 에러를 생성함.
//        addFooterView(footerContainer);//푸터 임시 보류
        setState(State.PULL_TO_REFRESH);
        scrollbarEnabled = isVerticalScrollBarEnabled();

        ViewTreeObserver vto = header.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new PTROnGlobalLayoutListener());
        /*
        super.setOnItemClickListener(new PTROnItemClickListener());
        super.setOnItemLongClickListener(new PTROnItemLongClickListener());*/
    }

    private void setHeaderPadding(int padding){
    	if(padding > (header.getHeight()-50)){
    		padding = header.getHeight()-50; // 그만 당겨지게
    	}
        headerPadding = padding;

        MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) header.getLayoutParams();
        mlp.setMargins(0, Math.round(padding), 0, 0);
        header.setLayoutParams(mlp);
    }
    
    public boolean isStartedWithPulldown = true;
    public boolean isFirstMoving = true;
    public boolean isScrolling = false;
    
    private float startY;
    
    @Override
    public boolean onTouchEvent(MotionEvent event){
    	//System.out.println("onTouchEvent state:"+state);
        if(lockScrollWhileRefreshing
                && (state == State.REFRESHING || getAnimation() != null && !getAnimation().hasEnded())){
            return true;
        }

        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
            	isFirstMoving = true;
                if(getFirstVisiblePosition() == 0) previousY = event.getY();
                else previousY = -1;
                startY = event.getRawY();
                break;

            case MotionEvent.ACTION_UP:            	
            	isScrolling = false;
                if(previousY != -1 && (state == State.RELEASE_TO_REFRESH || getFirstVisiblePosition() == 0)){
                    switch(state){
                        case RELEASE_TO_REFRESH:
                            setState(State.REFRESHING);
                            bounceBackHeader();

                            break;

                        case PULL_TO_REFRESH:                                                        
                            if(headerPadding > -header.getHeight()){// 헤더가 보여진상태이면 return
                            	resetHeader();
                            }
                            break;
                    }
                }
                break;

            case MotionEvent.ACTION_MOVE:
            	float RawY = event.getRawY();
            	float scrollByY = RawY - startY;
            	if (scrollByY > 50)
            		isScrolling = true;
            	
                if(previousY != -1){
                    float y = event.getY();
                    float diff = y - previousY;
                    if(isFirstMoving){ // 스크롤 시작이고,
                		if(diff > 0){// 풀다운으로 시작했으면
                			isStartedWithPulldown = true;
                		}else{//풀다운으로 시작한게 아니면
                			isStartedWithPulldown = false;
                		}
                		isFirstMoving = false;
                	}
                    if(diff > 0) {
                    	diff /= PULL_RESISTANCE;
                    	previousY = y;     
                    }else{
                    	if(!isStartedWithPulldown){//풀다운으로 시작한게 아니면, 풀다운리프레쉬 이벤트를 발생하지 않게 하기 위해
                    		previousY = -1;
                    	}else{
                    		previousY = y;
                    	}
                    }
                                   
                    int newHeaderPadding = Math.max(Math.round(headerPadding + diff), -header.getHeight());

                    if(newHeaderPadding != headerPadding && state != State.REFRESHING){
                        setHeaderPadding(newHeaderPadding);

                        if(state == State.PULL_TO_REFRESH && headerPadding > 0){
                            setState(State.RELEASE_TO_REFRESH);

                            image.clearAnimation();
                            image.startAnimation(flipAnimation);
                        }else if(state == State.RELEASE_TO_REFRESH && headerPadding < 0){
                            setState(State.PULL_TO_REFRESH);

                            image.clearAnimation();
                            image.startAnimation(reverseFlipAnimation);
                        }

                        return true;
                    }
                }

                break;
        }

        return super.onTouchEvent(event);
    }

    private void bounceBackHeader(){
        int yTranslate = state == State.REFRESHING ?
                header.getHeight() - headerContainer.getHeight() :
                -headerContainer.getHeight() - headerContainer.getTop();

        TranslateAnimation bounceAnimation = new TranslateAnimation(
                TranslateAnimation.ABSOLUTE, 0,
                TranslateAnimation.ABSOLUTE, 0,
                TranslateAnimation.ABSOLUTE, 0,
                TranslateAnimation.ABSOLUTE, yTranslate);

        bounceAnimation.setDuration(BOUNCE_ANIMATION_DURATION);
        bounceAnimation.setFillEnabled(true);
        bounceAnimation.setFillAfter(false);
        bounceAnimation.setFillBefore(true);
        bounceAnimation.setInterpolator(new OvershootInterpolator(BOUNCE_OVERSHOOT_TENSION));
        bounceAnimation.setAnimationListener(new HeaderAnimationListener(yTranslate));

        startAnimation(bounceAnimation);
    }
    
    //헤더가 리셋중인가?
    public boolean isResettingHeader(){
    	return state == State.RESET_HEADER;
    }
    
    private void resetHeader(){
    	if(headerPadding > -(header.getHeight()-20)){//헤더가 20px 이상 내려졌을경우에만 헤더 리셋중 상태로 바꿈
    		setState(State.RESET_HEADER);
    	}    	
        if(getFirstVisiblePosition() > 0){
            setHeaderPadding(-header.getHeight());
            setState(State.PULL_TO_REFRESH);
            return;
        }

        if(getAnimation() != null && !getAnimation().hasEnded()){
            bounceBackHeader = true;
        }else{
            bounceBackHeader();
        }
    }

    private void setUiRefreshing(){
        spinner.setVisibility(View.VISIBLE);
        image.clearAnimation();
        image.setVisibility(View.INVISIBLE);
        text.setText(refreshingText);
    }

    private void setState(State state){
        this.state = state;
        switch(state){
            case PULL_TO_REFRESH:
                spinner.setVisibility(View.INVISIBLE);
                image.setVisibility(View.VISIBLE);
                text.setText(pullToRefreshText);

                if(showLastUpdatedText && lastUpdated != -1){
                    lastUpdatedTextView.setVisibility(View.VISIBLE);
                    lastUpdatedTextView.setText(String.format(lastUpdatedText, lastUpdatedDateFormat.format(new Date(lastUpdated))));
                }

                break;

            case RELEASE_TO_REFRESH:
                spinner.setVisibility(View.INVISIBLE);
                image.setVisibility(View.VISIBLE);
                text.setText(releaseToRefreshText);
                break;

            case REFRESHING:
                setUiRefreshing();

                lastUpdated = System.currentTimeMillis();
                if(onRefreshListener == null){
                    setState(State.PULL_TO_REFRESH);
                }else{
                    onRefreshListener.onRefresh();
                }

                break;
        }
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt){
        super.onScrollChanged(l, t, oldl, oldt);

        if(!hasResetHeader){
            if(measuredHeaderHeight > 0 && state != State.REFRESHING){
                setHeaderPadding(-measuredHeaderHeight);
            }

            hasResetHeader = true;
        }
    }

    private class HeaderAnimationListener implements AnimationListener{

        private int height, translation;
        private State stateAtAnimationStart;

        public HeaderAnimationListener(int translation){
            this.translation = translation;
        }

        @Override
        public void onAnimationStart(Animation animation){
            stateAtAnimationStart = state;

            android.view.ViewGroup.LayoutParams lp = getLayoutParams();
            height = lp.height;
            lp.height = getHeight() - translation;
            setLayoutParams(lp);

            if(scrollbarEnabled){
                setVerticalScrollBarEnabled(false);
            }
        }

        @Override
        public void onAnimationEnd(Animation animation){
            setHeaderPadding(stateAtAnimationStart == State.REFRESHING ? 0 : -measuredHeaderHeight - headerContainer.getTop());
            setSelection(0);

            android.view.ViewGroup.LayoutParams lp = getLayoutParams();
            lp.height = height;
            setLayoutParams(lp);

            if(scrollbarEnabled){
                setVerticalScrollBarEnabled(true);
            }

            if(bounceBackHeader){
                bounceBackHeader = false;

                postDelayed(new Runnable(){

                    @Override
                    public void run(){
                        resetHeader();
                    }
                }, BOUNCE_ANIMATION_DELAY);
            }else if(stateAtAnimationStart != State.REFRESHING){
                setState(State.PULL_TO_REFRESH);
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation){}
    }

    private class PTROnGlobalLayoutListener implements OnGlobalLayoutListener{

        @Override
        public void onGlobalLayout(){
            int initialHeaderHeight = header.getHeight();

            if(initialHeaderHeight > 0){
                measuredHeaderHeight = initialHeaderHeight;

                if(measuredHeaderHeight > 0 && state != State.REFRESHING){
                    setHeaderPadding(-measuredHeaderHeight);
                    requestLayout();
                }
            }

            getViewTreeObserver().removeGlobalOnLayoutListener(this);
        }
    }

    /*private class PTROnItemClickListener implements OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id){
            hasResetHeader = false;

            if(onItemClickListener != null && state == State.PULL_TO_REFRESH){
                // Passing up onItemClick. Correct position with the number of header views
                onItemClickListener.onItemClick(adapterView, view, position - getHeaderViewsCount(), id);
            }
        }
    }

    private class PTROnItemLongClickListener implements OnItemLongClickListener{

        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id){
            hasResetHeader = false;

            if(onItemLongClickListener != null && state == State.PULL_TO_REFRESH){
                // Passing up onItemLongClick. Correct position with the number of header views
                return onItemLongClickListener.onItemLongClick(adapterView, view, position - getHeaderViewsCount(), id);
            }

            return false;
        }
    }*/
}
