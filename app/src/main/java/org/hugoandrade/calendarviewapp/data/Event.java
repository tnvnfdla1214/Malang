package org.hugoandrade.calendarviewapp.data;

import android.os.Parcelable;

import java.util.Calendar;

public class Event{

    private String Event_Uid;
    private String mID;
    private String mTitle;
    private Calendar mDate;
    private int mColor;

    private boolean isCompleted;

    private int mYear;
    private int mMonth;
    private int mDay;

    public Event(){}

    public Event(String Event_Uid, String id, String title, Calendar date, int color, boolean isCompleted) {
        this.Event_Uid = Event_Uid;
        mID = id;
        mTitle = title;
        mDate = date;
        mColor = color;
        this.isCompleted = isCompleted;
    }

    public String getEvent_Uid() {
        return Event_Uid;
    }

    public void setEvent_Uid(String event_Uid) {
        Event_Uid = event_Uid;
    }

    public String getmID() {
        return mID;
    }

    public void setmID(String mID) {
        this.mID = mID;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public Calendar getmDate() {
        return mDate;
    }

    public void setmDate(Calendar mDate) {
        this.mDate = mDate;
    }

    public int getmColor() {
        return mColor;
    }

    public void setmColor(int mColor) {
        this.mColor = mColor;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public int getmYear() {
        return mYear;
    }

    public void setmYear(int mYear) {
        this.mYear = mYear;
    }

    public int getmMonth() {
        return mMonth;
    }

    public void setmMonth(int mMonth) {
        this.mMonth = mMonth;
    }

    public int getmDay() {
        return mDay;
    }

    public void setmDay(int mDay) {
        this.mDay = mDay;
    }
}