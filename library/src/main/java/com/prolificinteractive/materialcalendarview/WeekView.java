package com.prolificinteractive.materialcalendarview;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.threeten.bp.DayOfWeek;
import org.threeten.bp.LocalDate;

/**
 * Display a week of {@linkplain DayView}s and
 * seven {@linkplain WeekDayView}s.
 */
@SuppressLint("ViewConstructor")
class WeekView extends CalendarPagerView {

    public WeekView(
            @NonNull final MaterialCalendarView view,
            final CalendarDay firstViewDay,
            final DayOfWeek firstDayOfWeek,
            final boolean showWeekDays) {
        super(view, firstViewDay, firstDayOfWeek, showWeekDays);
    }

    public WeekView(
            @NonNull final MaterialCalendarView view,
            final CalendarDay firstViewDay,
            final DayOfWeek firstDayOfWeek,
            final boolean showWeekDays,
            Map<DayOfWeek, Drawable> dayOfWeekDrawableIconBottom) {
        super(view, firstViewDay, firstDayOfWeek, showWeekDays,dayOfWeekDrawableIconBottom);
    }

    @Override
    protected void buildDayViews(
            final Collection<DayView> dayViews,
            final LocalDate calendar) {
        LocalDate temp = calendar;
        Map<DayOfWeek, Drawable> dayOfWeekDrawableIconBottom = getDayOfWeekDrawableIconBottom();
        for (int i = 0; i < DEFAULT_DAYS_IN_WEEK; i++) {
            DayOfWeek dayOfWeek = temp.getDayOfWeek();
            boolean isHaveDrawableIconBottom = false;
            if (dayOfWeekDrawableIconBottom != null){
                isHaveDrawableIconBottom =  dayOfWeekDrawableIconBottom.containsKey(dayOfWeek);
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

    @Override
    protected boolean isDayEnabled(CalendarDay day) {
        return true;
    }

    @Override
    protected int getRows() {
        return showWeekDays ? DAY_NAMES_ROW + 1 : 1;
    }
}
