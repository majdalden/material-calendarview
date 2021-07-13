package com.prolificinteractive.materialcalendarview;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.threeten.bp.DayOfWeek;
import org.threeten.bp.LocalDate;

/**
 * Display a month of {@linkplain DayView}s and
 * seven {@linkplain WeekDayView}s.
 */
@SuppressLint("ViewConstructor")
class MonthView extends CalendarPagerView {

    public MonthView(
            @NonNull final MaterialCalendarView view,
            final CalendarDay month,
            final DayOfWeek firstDayOfWeek,
            final boolean showWeekDays) {
        super(view, month, firstDayOfWeek, showWeekDays,new HashMap<>());
    }

    public MonthView(
            @NonNull final MaterialCalendarView view,
            final CalendarDay month,
            final DayOfWeek firstDayOfWeek,
            final boolean showWeekDays,
            Map<DayOfWeek, Drawable> dayOfWeekDrawableIconBottom) {
        super(view, month, firstDayOfWeek, showWeekDays,dayOfWeekDrawableIconBottom);
    }

    @Override
    protected void buildDayViews(
            final Collection<DayView> dayViews,
            final LocalDate calendar) {
        LocalDate temp = calendar;
        Map<DayOfWeek, Drawable> dayOfWeekDrawableIconBottom = getDayOfWeekDrawableIconBottom();
        for (int r = 0; r < DEFAULT_MAX_WEEKS; r++) {
            for (int i = 0; i < DEFAULT_DAYS_IN_WEEK; i++) {
                DayOfWeek dayOfWeek = temp.getDayOfWeek();
                boolean isHaveDrawableIconBottom = false;
                if (dayOfWeekDrawableIconBottom != null) {
                    isHaveDrawableIconBottom = dayOfWeekDrawableIconBottom.containsKey(dayOfWeek);
                }
                if (isHaveDrawableIconBottom) {
                    Drawable drawable = dayOfWeekDrawableIconBottom.get(dayOfWeek);
                    addDayView(dayViews, temp, drawable);
                } else {
                    addDayView(dayViews, temp);
                }
                temp = temp.plusDays(1);
            }
        }
    }

    public CalendarDay getMonth() {
        return getFirstViewDay();
    }

    @Override
    protected boolean isDayEnabled(final CalendarDay day) {
        return day.getMonth() == getFirstViewDay().getMonth();
    }

    @Override
    protected int getRows() {
        return showWeekDays ? DEFAULT_MAX_WEEKS + DAY_NAMES_ROW : DEFAULT_MAX_WEEKS;
    }

}
