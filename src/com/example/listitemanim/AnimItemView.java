package com.example.listitemanim;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Scroller;

public class AnimItemView extends RelativeLayout{

	private static final String TAG = "AnimItemView";
	
	private static final int STATUS_NONE = -1;
	private static final int STATUS_CLOSING = 0;
	private static final int STATUS_OPENING = 1;
    private boolean mIsOnce = true;
    
    private Scroller mScroller;
    private int mStatus = STATUS_NONE;
    
    private boolean isOpen = true;
	
	public AnimItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public AnimItemView(Context context) {
		super(context);
		init();
	}
	
	private void init(){
		mScroller = new Scroller(getContext());
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		if (mIsOnce) {
            
            findViewById(R.id.open).setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					open();
				}
			});
            
            findViewById(R.id.close).setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					close();
				}
			});
            
            mIsOnce = false;
        }
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	public void open(){
		Log.e(TAG, "open status="+mStatus+"; mTopHeight="+mTopHeight);
		if(mStatus != STATUS_NONE || mTopHeight <= 0){
			return ;
		}
		if(isOpen){
			return ;
		}
		isOpen = true;
		mStatus = STATUS_OPENING;
		
		mScroller.startScroll(0, 0, 0, mTopHeight, 500);
		requestLayout();
	}
	
	public void close(){
		if(mStatus != STATUS_NONE){
			return ;
		}
		if(!isOpen){
			return ;
		}
		isOpen = false;
		RelativeLayout.LayoutParams p = (LayoutParams) getChildAt(1).getLayoutParams();
		if(p.topMargin <= 0){
			return ;
		}
		mStatus = STATUS_CLOSING;
		Log.e(TAG, "content_height="+getChildAt(1).getHeight());
		Log.e(TAG, "top_margin="+p.topMargin);
		
		mTopHeight = p.topMargin;
		mScroller.startScroll(0, p.topMargin, 0, -p.topMargin, 500);
		requestLayout();
	}
	
	private int mTopHeight;
	
	@Override
	public void computeScroll() {
		if (mScroller.computeScrollOffset()){
			int currY = mScroller.getCurrY();
			Log.e(TAG, "computeScroll_y="+currY+"; item_height="+getLayoutParams().height);
			RelativeLayout.LayoutParams p = (LayoutParams) getChildAt(1).getLayoutParams();
			p.topMargin = currY;
			requestLayout();
		}else{
			mStatus = STATUS_NONE;
		}
			
	}
}
