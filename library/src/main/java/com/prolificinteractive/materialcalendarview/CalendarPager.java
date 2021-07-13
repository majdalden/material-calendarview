package com.prolificinteractive.materialcalendarview;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Custom ViewPager that allows swiping to be disabled.
 */
class CalendarPager extends ViewPager {

  private boolean pagingEnabled = true;

  public CalendarPager(@NonNull final Context context) {
    super(context);
  }

  public CalendarPager(@NonNull final Context context, @Nullable final AttributeSet attrs) {
    super(context, attrs);
  }

  /**
   * enable disable viewpager scroll
   *
   * @param pagingEnabled false to disable paging, true for paging (default)
   */
  public void setPagingEnabled(boolean pagingEnabled) {
    this.pagingEnabled = pagingEnabled;
  }

  /**
   * @return is this viewpager allowed to page
   */
  public boolean isPagingEnabled() {
    return pagingEnabled;
  }

  @Override
  public boolean onInterceptTouchEvent(MotionEvent ev) {
    return pagingEnabled && super.onInterceptTouchEvent(ev);
  }

  @Override
  public boolean onTouchEvent(MotionEvent ev) {
    return pagingEnabled && super.onTouchEvent(ev);
  }

  @Override
  public boolean canScrollVertically(int direction) {
    /**
     * disables scrolling vertically when paging disabled, fixes scrolling
     * for nested {@link ViewPager}
     */
    return pagingEnabled && super.canScrollVertically(direction);
  }

  @Override
  public boolean canScrollHorizontally(int direction) {
    /**
     * disables scrolling horizontally when paging disabled, fixes scrolling
     * for nested {@link ViewPager}
     */
    return pagingEnabled && super.canScrollHorizontally(direction);
  }

  @Override
  public void onRtlPropertiesChanged(int layoutDirection) {
    super.onRtlPropertiesChanged(layoutDirection);
    if (layoutDirection == View.LAYOUT_DIRECTION_RTL) {
      setRotationY(180f);
    }
  }

  @Override
  public void onViewAdded(View child) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
      if (getLayoutDirection() == View.LAYOUT_DIRECTION_RTL) {
        child.setRotationY(180);
      }
    }
    super.onViewAdded(child);
  }
}
