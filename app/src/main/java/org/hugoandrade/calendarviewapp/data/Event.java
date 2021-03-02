package org.hugoandrade.calendarviewapp.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Calendar;

public class Event implements Parcelable{

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

    public Event(String Event_Uid, String id, String title, int mYear, int mMonth, int mDay, int color, boolean isCompleted) {
        this.Event_Uid = Event_Uid;
        mID = id;
        mTitle = title;
        this.mYear = mYear;
        this.mMonth = mMonth;
        this.mDay = mDay;
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
        return this.mYear;
    }

    public void setmYear(int mYear) {
        this.mYear = mYear;
    }

    public int getmMonth() {
        return this.mMonth;
    }

    public void setmMonth(int mMonth) {
        this.mMonth = mMonth;
    }

    public int getmDay() {
        return this.mDay;
    }

    public void setmDay(int mDay) {
        this.mDay = mDay;
    }

    protected Event(Parcel in) {
        Event_Uid = in.readString();
        mID = in.readString();
        mTitle = in.readString();
        mDate = (Calendar) in.readSerializable();
        mColor = in.readInt();
        isCompleted = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Event_Uid);
        dest.writeString(mID);
        dest.writeString(mTitle);
        dest.writeSerializable(mDate);
        dest.writeInt(mColor);
        dest.writeByte((byte) (isCompleted ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Event> CREATOR = new Parcelable.Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };
}