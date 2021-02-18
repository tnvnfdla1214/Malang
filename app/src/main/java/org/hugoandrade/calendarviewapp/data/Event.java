package org.hugoandrade.calendarviewapp.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Hugo Andrade on 25/03/2018.
 */

/* 여기에 getter setter 박고 파베랑 연동하면 쓸수 있지 않을까? */
public class Event implements Parcelable {

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




    public Map<String, Object> getScheduleInfo(){
        Map<String, Object> docData = new HashMap<>();
        docData.put("ScheduleModel_Uid", Event_Uid);
        docData.put("ScheduleModel_Title", mTitle);
        docData.put("ScheduleModel_Year", mYear);
        docData.put("ScheduleModel_Month", mMonth);
        docData.put("ScheduleModel_Day", mDay);
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

    public String getEvent_Uid() {
        return Event_Uid;
    }

    public void setEvent_Uid(String event_Uid) {
        Event_Uid = event_Uid;
    }
}
