package org.hugoandrade.calendarviewapp.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Calendar;

public class Event implements Parcelable{

    private String Event_Uid;
    private String Id;
    private String Title;
    private int Color;
    private boolean IsCompleted;

    private Calendar Marked_Date;
    private Calendar Start_Date;
    private Calendar End_Date;

    private int Marked_Year;
    private int Marked_Month;
    private int Marked_Day;

    private int Start_Year;
    private int Start_Month;
    private int Start_Day;

    private int End_Year;
    private int End_Month;
    private int End_Day;

    public Event(){}

    public Event(String Event_Uid, String Id, String Title, Calendar Marked_Date, Calendar Start_Date, Calendar End_Date, int Color, boolean IsCompleted) {
        this.Event_Uid = Event_Uid;
        this.Id = Id;
        this.Title = Title;
        this.Marked_Date = Marked_Date;
        this.Start_Date = Start_Date;
        this.End_Date = End_Date;
        this.Color = Color;
        this.IsCompleted = IsCompleted;
    }

    public Event(String Event_Uid, String Id, String Title, int Marked_Year, int Marked_Month, int Marked_Day,
                 int Start_Year, int Start_Month, int Start_Day, int End_Year, int End_Month, int End_Day, int Color, boolean IsCompleted) {
        this.Event_Uid = Event_Uid;
        this.Id = Id;
        this.Title = Title;
        this.Marked_Year = Marked_Year;
        this.Marked_Month = Marked_Month;
        this.Marked_Day = Marked_Day;
        this.Start_Year = Start_Year;
        this.Start_Month = Start_Month;
        this.Start_Day = Start_Day;
        this.End_Year = End_Year;
        this.End_Month = End_Month;
        this.End_Day = End_Day;
        this.Color = Color;
        this.IsCompleted = IsCompleted;
    }

    protected Event(Parcel in) {
        Event_Uid = in.readString();
        Id = in.readString();
        Title = in.readString();
        Marked_Date = (Calendar) in.readSerializable();
        Start_Date = (Calendar) in.readSerializable();
        End_Date = (Calendar) in.readSerializable();
        Color = in.readInt();
        IsCompleted = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Event_Uid);
        dest.writeString(Id);
        dest.writeString(Title);
        dest.writeSerializable(Marked_Date);
        dest.writeSerializable(Start_Date);
        dest.writeSerializable(End_Date);
        dest.writeInt(Color);
        dest.writeByte((byte) (IsCompleted ? 1 : 0));
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
    public String getEvent_Uid() {
        return this.Event_Uid;
    }
    public void setEvent_Uid(String Event_Uid) {
        this.Event_Uid = Event_Uid;
    }

    public String getId() {
        return this.Id;
    }
    public void setId(String Id) {
        this.Id = Id;
    }

    public String getTitle() {
        return this.Title;
    }
    public void setTitle(String Title) {
        this.Title = Title;
    }

    public int getColor() {
        return this.Color;
    }
    public void setColor(int Color) {
        this.Color = Color;
    }

    public boolean isCompleted() {
        return this.IsCompleted;
    }
    public void setCompleted(boolean IsCompleted) { this.IsCompleted = IsCompleted;    }

    public Calendar getMarked_Date() { return this.Marked_Date; }
    public void setMarked_Date(Calendar Marked_Date) { this.Marked_Date = Marked_Date; }
    public int getMarked_Year() {
        return this.Marked_Year;
    }
    public void setMarked_Year(int Marked_Year) {
        this.Marked_Year = Marked_Year;
    }
    public int getMarked_Month() {
        return this.Marked_Month;
    }
    public void setMarked_Month(int Marked_Month) {
        this.Marked_Month = Marked_Month;
    }
    public int getMarked_Day() {
        return this.Marked_Day;
    }
    public void setMarked_Day(int Marked_Day) {
        this.Marked_Day = Marked_Day;
    }

    public Calendar getStart_Date() { return this.Start_Date; }
    public void setStart_Date(Calendar Start_Date) { this.Start_Date = Start_Date; }
    public int getStart_Year() { return this.Start_Year; }
    public void setStart_Year(int Start_Year) { this.Start_Year = Start_Year; }
    public int getStart_Month() { return this.Start_Month; }
    public void setStart_Month(int Start_Month) { this.Start_Month = Start_Month; }
    public int getStart_Day() { return this.Start_Day; }
    public void setStart_Day(int Start_Day) { this.Start_Day = Start_Day; }

    public Calendar getEnd_Date() { return this.End_Date; }
    public void setEnd_Date(Calendar End_Date) {this.End_Date = End_Date; }
    public int getEnd_Year() { return this.End_Year; }
    public void setEnd_Year(int End_Year) { this.End_Year = End_Year; }
    public int getEnd_Month() { return this.End_Month; }
    public void setEnd_Month(int End_Month) { this.End_Month = End_Month; }
    public int getEnd_Day() { return this.End_Day; }
    public void setEnd_Day(int End_Day) { this.End_Day = End_Day; }


}