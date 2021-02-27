//package com.hs.mobile.gw.view;
//
//import java.util.Timer;
//import java.util.TimerTask;
//
//import android.content.Context;
//import android.content.res.TypedArray;
//import android.graphics.Rect;
//import android.util.AttributeSet;
//import android.view.GestureDetector;
//import android.view.LayoutInflater;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.FrameLayout;
//import android.widget.Scroller;
//
//import com.hs.mobile.gw.MainActivity;
//import com.hs.mobile.gw.MainModel;
//import com.hs.mobile.gw.hsuco.R;
//import com.hs.mobile.gw.util.AndroidUtils;
//import com.hs.mobile.gw.util.Debug;
//
//public class MainContentView extends ViewGroup {
//
//	public static boolean isSwipeInProgress;
//	/**
//	 * Spacing will be calculated as offset from right bound of view.
//	 */
//	public static final int SPACING_RIGHT_OFFSET = 0;
//	/**
//	 * Spacing will be calculated as right bound of actions bar container.
//	 */
//	public static final int SPACING_ACTIONS_WIDTH = 1;
//
//	private int left_boundary;
//
//	private final ContentScrollController mContentScrollController;
//	private final GestureDetector mGestureDetector;
//
//	private int mSpacingType = SPACING_RIGHT_OFFSET;
//	/**
//	 * Value of spacing to use.
//	 */
//	private int mSpacing;
//
//	/**
//	 * Indicates whether swiping is enabled or not.
//	 */
//	private boolean isSwipingEnabled = true;
//
//	private final FrameLayout viewActionsContainer;
//	private final FrameLayout viewContentContainer;
//
//	private final Rect mContentHitRect = new Rect();
//
//	public MainContentView(Context context) {
//		this(context, null);
//	}
//
//	public MainContentView(Context context, AttributeSet attrs) {
//		this(context, attrs, 0);
//
//		// 장비 해상도 별로 Left Menu Size 설정
//		int maxWidth = AndroidUtils.getMaxWidth(context);
//
//		if (MainModel.getInstance().isTablet()) {
//			left_boundary = 370;
//		} else {
//			left_boundary = (int) (maxWidth * 0.85);
//		}
//
//		Debug.trace("MainContentView - left_boundary[" + left_boundary + "] maxWidth[" + maxWidth + "]");
//	}
//
//	public MainContentView(Context context, AttributeSet attrs, int defStyle) {
//		super(context, attrs, defStyle);
//
//		setClipChildren(false);
//		setClipToPadding(false);
//
//		// reading attributes
//		final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.MainContentView);
//		mSpacingType = a.getInteger(R.styleable.MainContentView_spacing_type, SPACING_RIGHT_OFFSET);
//
//		final int spacingDefault = context.getResources().getDimensionPixelSize(R.dimen.detault_maincontentview_spacing);
//		mSpacing = a.getDimensionPixelSize(R.styleable.MainContentView_spacing, spacingDefault);
//
//		final int actionsLayout = a.getResourceId(R.styleable.MainContentView_actions_layout, 0);
//		final int contentLayout;
//
//		if (MainModel.getInstance().isTablet()) { // 테블릿이면 오른쪽 컨텐츠 레이아웃을 테블릿용으로
//			contentLayout = a.getResourceId(R.styleable.MainContentView_content_layout_tablet, 0);
//		} else {
//			contentLayout = a.getResourceId(R.styleable.MainContentView_content_layout, 0);
//		}
//
//		a.recycle();
//
//		Debug.trace("MainContentView - spacing type: " + mSpacingType);
//		Debug.trace("MainContentView - spacing value: " + mSpacing);
//		Debug.trace("MainContentView - actions layout id: " + actionsLayout);
//		Debug.trace("MainContentView - content layout id: " + contentLayout);
//
//		mContentScrollController = new ContentScrollController(new Scroller(context));
//		mGestureDetector = new GestureDetector(context, mContentScrollController);
//		mGestureDetector.setIsLongpressEnabled(false);
//
//		final LayoutInflater inflater = LayoutInflater.from(context);
//
//		viewActionsContainer = new FrameLayout(context);
//		if (actionsLayout != 0)
//			inflater.inflate(actionsLayout, viewActionsContainer, true);
//		addView(viewActionsContainer, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
//
//		viewContentContainer = new FrameLayout(context);
//		viewContentContainer.setId(R.id.viewContentContainer);
//		if (contentLayout != 0) {
//			inflater.inflate(contentLayout, viewContentContainer, true);
//		}
//
//		addView(viewContentContainer, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
//	}
//
//	public FrameLayout getActionsContainer() {
//		return viewActionsContainer;
//	}
//
//	public FrameLayout getContentContainer() {
//		return viewContentContainer;
//	}
//
//	// 현재 Left Menu 호출 여부
//	public boolean isActionsShown() {
//		return !mContentScrollController.isContentShown();
//	}
//
//	// Left Menu 호출(Right WebView 숨기기)
//	public void showActions() {
//		if (!this.isActionsShown()) {
//			mContentScrollController.hideContent();
//		}
//	}
//
//	public boolean isContentShown() {
//		return mContentScrollController.isContentShown();
//	}
//
//	// Right WebView 호출 (Left Menu 숨기기)
//	public void showContent() {
//		if (this.isActionsShown()) {
//			mContentScrollController.showContent();
//		}
//	}
//
//	public boolean isSwipingEnabled() {
//		return isSwipingEnabled;
//	}
//
//	public void setSwipingEnabled(boolean enabled) {
//		isSwipingEnabled = enabled;
//	}
//
//	@Override
//	public boolean onTouchEvent(MotionEvent event) {
//		// return true always as far we should handle touch event for swiping
//		return true;
//	}
//
//	@Override
//	public boolean dispatchTouchEvent(MotionEvent ev) {
//		if (!isSwipingEnabled)
//			return super.dispatchTouchEvent(ev);
//
//		final int action = ev.getAction();
//		float touchX = ev.getX();
//
//		/*
//		 * MainActivity.getAdditionalLeftBoundary() : 테블릿이고 2단 메뉴를 보여주는 메뉴의 경우
//		 * 2단 메뉴는 컨트롤이 가능하도록 하기위해 left_boundary 를 조정
//		 */
//		if (isActionsShown() && action == MotionEvent.ACTION_DOWN && touchX > left_boundary + MainActivity.getAdditionalLeftBoundary() + 1) {
//			showContent();
//			return false;
//		}
//
//		// if current touch event should be handled
//		if (mContentScrollController.isHandled() && action == MotionEvent.ACTION_UP) {
//			mContentScrollController.onUp(ev);
//			return false;
//		}
//
//		if (mGestureDetector.onTouchEvent(ev) || mContentScrollController.isHandled()) {
//			clearPressedState(this);
//			return false;
//		}
//
//		if (mContentHitRect.contains((int) ev.getX(), (int) ev.getY()))
//			return true;
//
//		return super.dispatchTouchEvent(ev);
//	}
//
//	@Override
//	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//		final int width = MeasureSpec.getSize(widthMeasureSpec);
//		final int height = MeasureSpec.getSize(heightMeasureSpec);
//
//		final int childrenCount = getChildCount();
//		for (int i = 0; i < childrenCount; ++i) {
//			final View v = getChildAt(i);
//
//			// Left Menu 일 경우
//			if (v == viewActionsContainer) {
//				// setting size of actions according to spacing parameters
//				if (mSpacingType == SPACING_ACTIONS_WIDTH) {
//					viewActionsContainer.measure(MeasureSpec.makeMeasureSpec(left_boundary, MeasureSpec.EXACTLY), heightMeasureSpec);
//				} else {
//					// all other situations are handled as SPACING_RIGHT_OFFSET
//					// viewActionsContainer.measure(MeasureSpec.makeMeasureSpec(
//					// width - mSpacing, MeasureSpec.EXACTLY),
//					// heightMeasureSpec);
//
//					viewActionsContainer.measure(MeasureSpec.makeMeasureSpec(left_boundary, MeasureSpec.EXACTLY), heightMeasureSpec);
//				}
//			}
//			// Right Contents 일 경우
//			else {
//				v.measure(widthMeasureSpec, heightMeasureSpec);
//			}
//		}
//
//		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//	}
//
//	@Override
//	protected void onLayout(boolean changed, int l, int t, int r, int b) {
//		// putting every child view to top-left corner
//		final int childrenCount = getChildCount();
//		for (int i = 0; i < childrenCount; ++i) {
//			final View v = getChildAt(i);
//			v.layout(l, t, l + v.getMeasuredWidth(), t + v.getMeasuredHeight());
//		}
//
//		mContentScrollController.init();
//	}
//
//	/**
//	 * Clears pressed state for all views hierarchy starting from parent view.
//	 * 
//	 * @param parent
//	 *            - parent view
//	 * @return true is press state was cleared
//	 */
//	private static boolean clearPressedState(ViewGroup parent) {
//		if (parent.isPressed()) {
//			parent.setPressed(false);
//			return true;
//		}
//
//		final int count = parent.getChildCount();
//		for (int i = 0; i < count; ++i) {
//			final View v = parent.getChildAt(i);
//			if (v.isPressed()) {
//				v.setPressed(false);
//				return true;
//			}
//
//			if (!(v instanceof ViewGroup))
//				continue;
//
//			final ViewGroup vg = (ViewGroup) v;
//			if (clearPressedState(vg))
//				return true;
//		}
//		return false;
//	}
//
//	/**
//	 * Used to handle scrolling events and scroll content container on top of
//	 * actions one.
//	 */
//	private class ContentScrollController implements GestureDetector.OnGestureListener, Runnable {
//		/**
//		 * Used to auto-scroll to closest bound on touch up event.
//		 */
//		private final Scroller mScroller;
//
//		// using Boolean object to initialize while first scroll event
//		private Boolean mHandleEvent = null;
//
//		private int mLastFlingX = 0;
//
//		/**
//		 * Indicates whether we need initialize position of view after measuring
//		 * is finished. </br>Can be <code>null</code> if there is no any
//		 * requirements for position was set. In this case we will have content
//		 * shown.
//		 */
//		private Boolean isInitiallyShown = null;
//
//		public ContentScrollController(Scroller scroller) {
//			mScroller = scroller;
//		}
//
//		/**
//		 * Initializes visibility of content after views measuring is finished.
//		 */
//		public void init() {
//			if (isInitiallyShown == null)
//				return;
//
//			if (isInitiallyShown)
//				showContent();
//			else
//				hideContent();
//
//			isInitiallyShown = null;
//		}
//
//		/**
//		 * Returns handling lock value. It indicates whether all events should
//		 * be marked as handled.
//		 * 
//		 * @return
//		 */
//		public boolean isHandled() {
//			return mHandleEvent != null && mHandleEvent;
//		}
//
//		float webviewActionStartWithX = 0;
//		float toolbarActionStartWithX = 0;
//
//		@Override
//		public boolean onDown(MotionEvent e) {
//			webviewActionStartWithX = MainActivity.webView.getScrollX();
//			toolbarActionStartWithX = MainActivity.mToolbarContainer.getScrollX();
//			mHandleEvent = null;
//			reset();
//			return false;
//		}
//
//		public boolean onUp(MotionEvent e) {
//			if (!isHandled())
//				return false;
//
//			mHandleEvent = null;
//			completeScrolling();
//			return true;
//		}
//
//		@Override
//		public boolean onSingleTapUp(MotionEvent e) {
//			return false;
//		}
//
//		@Override
//		public void onShowPress(MotionEvent e) {
//			// No-op
//		}
//
//		// 터치 스크롤 시 최초 호출
//		@Override
//		public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
//			/*
//			 * [ 스크롤 이벤트를 그냥 넘기는 조건 ](웹에서 스크롤링 하게하는 조건)
//			 * 
//			 * 1) 웹뷰의 스크롤 시작이 왼쪽 끝이 아니었거나 2) 웹뷰의 현재 스크롤 위치가 왼쪽 끝이 아니거나 3) 움직임이
//			 * 왼쪽 이고 메인메뉴로가는 Swipe 가 진행중이지 않거나
//			 * 
//			 * 4) 툴바의 스크롤이 왼쪽 끝이 아니거나 5) 툴바의 현재 위치가 왼쪽끝이 아니면
//			 */
//			// System.out.println(webviewActionStartWithX+":"+MainActivity.webView.getScrollX()+":"+distanceX+":"+viewContentContainer.getScrollX());
//
//			if (webviewActionStartWithX != 0 || MainActivity.webView.getScrollX() != 0 || MainActivity.customWebView.getScrollX() != 0
//					|| (distanceX > 0 && viewContentContainer.getScrollX() == 0) || toolbarActionStartWithX != 0
//					|| MainActivity.mToolbarContainer.getScrollX() != 0) {
//				return false;
//			}
//
//			if (MainActivity.mToolbarContainer.getScrollX() != 0) {
//				return false;
//			}
//
//			// if there is first scroll event after touch down
//			if (mHandleEvent == null) {
//				if (Math.abs(distanceX) < Math.abs(distanceY)) {
//					// if first event is more scroll by Y axis than X one
//					// ignore all events until event up
//					mHandleEvent = Boolean.FALSE;
//					return mHandleEvent;
//				} else {
//					// handle all events of scrolling by X axis
//					mHandleEvent = Boolean.TRUE;
//					scrollBy((int) distanceX);
//				}
//			} else if (mHandleEvent) {
//				// it is not first event we should handle as scrolling by X axis
//				scrollBy((int) distanceX);
//			}
//			isSwipeInProgress = true;
//			return mHandleEvent;
//		}
//
//		@Override
//		public void onLongPress(MotionEvent e) {
//			// No-op
//		}
//
//		@Override
//		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//			// does not work because onDown() method returns false always
//			return false;
//		}
//
//		/**
//		 * Scrolling content view according by given value.
//		 * 
//		 * @param dx
//		 */
//		private void scrollBy(int dx) {
//			final int x = viewContentContainer.getScrollX();
//
//			Debug.trace("MainContentView - scroll from " + x + " by " + dx);
//
//			// final int rightBound = getRightBound();
//			final int rightBound = left_boundary;
//
//			final int scrollBy;
//			if (dx < 0) { // scrolling right
//				if (x + dx < -rightBound)
//					scrollBy = -rightBound - x;
//				else
//					scrollBy = dx;
//			} else { // scrolling left
//				// don't scroll if we are at left bound
//				if (x == 0)
//					return;
//
//				if (x + dx > 0)
//					scrollBy = -x;
//				else
//					scrollBy = dx;
//			}
//
//			viewContentContainer.scrollBy(scrollBy, 0);
//		}
//
//		// Left Menu 가 호출되어있는지 여부
//		public boolean isContentShown() {
//			final int x;
//			if (!mScroller.isFinished())
//				x = mScroller.getFinalX();
//			else
//				x = viewContentContainer.getScrollX();
//
//			return x == 0;
//		}
//
//		// Left Menu 호출(Right WebView 숨기기)
//		public void hideContent() {
//			if (viewContentContainer.getMeasuredWidth() == 0 || viewContentContainer.getMeasuredHeight() == 0) {
//				isInitiallyShown = Boolean.FALSE;
//				return;
//			}
//
//			final int startX = viewContentContainer.getScrollX();
//			final int dx;
//
//			if (mSpacingType == SPACING_ACTIONS_WIDTH) {
//				// dx = mSpacing + startX;
//				dx = left_boundary;
//			} else { // all other situations are handled as SPACING_RIGHT_OFFSET
//				// Left Menu Size 결정
//				// dx = getWidth() - mSpacing + startX;
//				dx = left_boundary;
//			}
//
//			fling(startX, dx);
//		}
//
//		// Right WebView 호출 (Left Menu 숨기기)
//		public void showContent() {
//			if (viewContentContainer.getMeasuredWidth() == 0 || viewContentContainer.getMeasuredHeight() == 0) {
//				isInitiallyShown = Boolean.TRUE;
//				return;
//			}
//
//			final int startX = viewContentContainer.getScrollX();
//			final int dx = startX;
//
//			fling(startX, dx);
//		}
//
//		// 터치 스크롤링 완료후 처리
//		private void completeScrolling() {
//			int startX = viewContentContainer.getScrollX();
//
//			// final int rightBound = getRightBound();
//			final int rightBound = left_boundary;
//			final int middle = -rightBound / 3;
//			final int dx;
//
//			// 터치 스크롤링이 (1/3)까지 못갈 경우 (원복)
//			if (startX > middle) {
//				dx = startX;
//			} else {
//				if (mSpacingType == SPACING_ACTIONS_WIDTH) {
//					// dx = mSpacing + startX;
//					dx = left_boundary + startX;
//					// 시작점 부터 350px 까지 이동
//				} else {
//					// dx = getWidth() - mSpacing + startX;
//					dx = left_boundary + startX;
//				}
//			}
//
//			fling(startX, dx);
//		}
//
//		// 메뉴 이동 / 스크롤링 후 화면 움직임 처리
//		private void fling(int startX, int dx) {
//			if (dx == 0)
//				return;
//
//			mScroller.startScroll(startX, 0, dx, 0);
//
//			Debug.trace("MainContentView - starting fling at " + startX + " for " + dx);
//
//			mLastFlingX = startX;
//			viewContentContainer.post(this);
//			Timer t = new Timer();
//			t.schedule(new TimerTask() {
//				@Override
//				public void run() {
//					isSwipeInProgress = false;
//				}
//			}, 500);
//		}
//
//		/**
//		 * Processes auto-scrolling to bound which is closer to current
//		 * position.
//		 */
//		@Override
//		public void run() {
//			if (mScroller.isFinished()) {
//				Debug.trace("MainContentView - scroller is finished, done with fling");
//				return;
//			}
//
//			final boolean more = mScroller.computeScrollOffset();
//			final int x = mScroller.getCurrX();
//			final int diff = mLastFlingX - x;
//			if (diff != 0) {
//				viewContentContainer.scrollBy(diff, 0);
//				mLastFlingX = x;
//			}
//
//			if (more) {
//				viewContentContainer.post(this);
//			}
//		}
//
//		/**
//		 * Resets scroller controller. Stops flinging on current position.
//		 */
//		public void reset() {
//			if (!mScroller.isFinished()) {
//				mScroller.forceFinished(true);
//			}
//		}
//
//		/**
//		 * Returns right bound (limit) for scroller.
//		 * 
//		 * @return right bound (limit) for scroller.
//		 */
//		private int getRightBound() {
//			if (mSpacingType == SPACING_ACTIONS_WIDTH) {
//				return mSpacing;
//			} else { // all other situations are handled as SPACING_RIGHT_OFFSET
//				return getWidth() - mSpacing;
//			}
//		}
//	};
//}
