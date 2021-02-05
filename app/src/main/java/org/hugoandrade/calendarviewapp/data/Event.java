package org.hugoandrade.calendarviewapp.data;

import android.os.Parcel;
import android.os.Parcelable;

import org.hugoandrade.calendarviewlib.helpers.YMDCalendar;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Hugo Andrade on 25/03/2018.
 */

/* 여기에 getter setter 박고 파베랑 연동하면 쓸수 있지 않을까? */
public class Event implements Parcelable {

    private String mID;
    private String mTitle;
    private Calendar mDate;
    private int mColor;
    private boolean isCompleted;

    public Event(String id, String title, Calendar date, int color, boolean isCompleted) {
        mID = id;
        mTitle = title;
        mDate = date;
        mColor = color;
        this.isCompleted = isCompleted;
    }


    public Map<String, Object> getScheduleInfo(){
        Map<String, Object> docData = new HashMap<>();
        docData.put("ScheduleModel_Title", mTitle);
        docData.put("ScheduleModel_Date", mDate);
        docData.put("ScheduleModel_Color", mColor);
        docData.put("ScheduleModel_Id", mID);
        docData.put("ScheduleModel_isCompleted", isCompleted);
        return  docData;
    }


    public String getID() {
        return mID;
    }

    public String getTitle() {
        return mTitle;
    }

    public Calendar getDate() {
        return mDate;
    }

    public int getColor() {
        return mColor;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    protected Event(Parcel in) {
        mID = in.readString();
        mTitle = in.readString();
        mColor = in.readInt();
        mDate = (Calendar) in.readSerializable();
        isCompleted = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mID);
        dest.writeString(mTitle);
        dest.writeInt(mColor);
        dest.writeSerializable(mDate);
        dest.writeByte((byte) (isCompleted ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Event> CREATOR = new Creator<Event>() {
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
