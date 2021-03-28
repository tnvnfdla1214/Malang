package bias.hugoandrade.calendarviewapp.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Calendar;

public class Event implements Parcelable{

    private String CALENDAR_UID;
    private String CALENDAR_id;
    private String CALENDAR_Schedule;
    //private int Color;
    //private boolean IsCompleted;

    private Calendar CALENDAR_MarkedDate;
    private Calendar CALENDAR_StartDate;
    private Calendar CALENDAR_EndDate;

    private int CALENDAR_MarkedY;
    private int CALENDAR_MarkedM;
    private int CALENDAR_MarkedD;

    private int CALENDAR_StartY;
    private int CALENDAR_StartM;
    private int CALENDAR_StartD;

    private int CALENDAR_EndY;
    private int CALENDAR_EndM;
    private int CALENDAR_EndD;

    private int CALENDAR_DateCount;

    public Event(){}

    public Event(String CALENDAR_UID, String CALENDAR_id, String CALENDAR_Schedule, Calendar CALENDAR_MarkedDate, Calendar CALENDAR_StartDate, Calendar CALENDAR_EndDate, int CALENDAR_DateCount) {
        this.CALENDAR_UID = CALENDAR_UID;
        this.CALENDAR_id = CALENDAR_id;
        this.CALENDAR_Schedule = CALENDAR_Schedule;
        this.CALENDAR_MarkedDate = CALENDAR_MarkedDate;
        this.CALENDAR_StartDate = CALENDAR_StartDate;
        this.CALENDAR_EndDate = CALENDAR_EndDate;
        this.CALENDAR_DateCount = CALENDAR_DateCount;
    }

    /*
    public Event(String CALENDAR_UID, String CALENDAR_id, String CALENDAR_Schedule, int CALENDAR_FixY, int CALENDAR_FixM, int CALENDAR_FixD,
                 int CALENDAR_StartY, int CALENDAR_StartM, int CALENDAR_StartD, int CALENDAR_EndY, int CALENDAR_EndM, int CALENDAR_EndD, int CALENDAR_DateCount) {
        this.CALENDAR_UID = CALENDAR_UID;
        this.CALENDAR_id = CALENDAR_id;
        this.CALENDAR_Schedule = CALENDAR_Schedule;
        this.CALENDAR_FixY = CALENDAR_FixY;
        this.CALENDAR_FixM = CALENDAR_FixM;
        this.CALENDAR_FixD = CALENDAR_FixD;
        this.CALENDAR_StartY = CALENDAR_StartY;
        this.CALENDAR_StartM = CALENDAR_StartM;
        this.CALENDAR_StartD = CALENDAR_StartD;
        this.CALENDAR_EndY = CALENDAR_EndY;
        this.CALENDAR_EndM = CALENDAR_EndM;
        this.CALENDAR_EndD = CALENDAR_EndD;
        this.CALENDAR_DateCount = CALENDAR_DateCount;
    }
    *
    * */



    protected Event(Parcel in) {
        CALENDAR_UID = in.readString();
        CALENDAR_id = in.readString();
        CALENDAR_Schedule = in.readString();
        CALENDAR_MarkedDate = (Calendar) in.readSerializable();
        CALENDAR_StartDate = (Calendar) in.readSerializable();
        CALENDAR_EndDate = (Calendar) in.readSerializable();
        CALENDAR_DateCount = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(CALENDAR_UID);
        dest.writeString(CALENDAR_id);
        dest.writeString(CALENDAR_Schedule);
        dest.writeSerializable(CALENDAR_MarkedDate);
        dest.writeSerializable(CALENDAR_StartDate);
        dest.writeSerializable(CALENDAR_EndDate);
        dest.writeInt(CALENDAR_DateCount);
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

    public String getCALENDAR_UID() {
        return this.CALENDAR_UID;
    }
    public void setCALENDAR_UID(String CALENDAR_UID) {
        this.CALENDAR_UID = CALENDAR_UID;
    }

    public String getCALENDAR_id() {
        return this.CALENDAR_id;
    }
    public void setCALENDAR_id(String CALENDAR_id) {
        this.CALENDAR_id = CALENDAR_id;
    }

    public String getCALENDAR_Schedule() {
        return this.CALENDAR_Schedule;
    }
    public void setCALENDAR_Schedule(String CALENDAR_Schedule) { this.CALENDAR_Schedule = CALENDAR_Schedule; }

    public int getCALENDAR_DateCount() {
        return this.CALENDAR_DateCount;
    }
    public void setCALENDAR_DateCount(int CALENDAR_DateCount) { this.CALENDAR_DateCount = CALENDAR_DateCount; }

    public Calendar getCALENDAR_MarkedDate() { return this.CALENDAR_MarkedDate; }
    public void setCALENDAR_MarkedDate(Calendar CALENDAR_MarkedDate) { this.CALENDAR_MarkedDate = CALENDAR_MarkedDate; }
    public int getCALENDAR_MarkedY() {
        return this.CALENDAR_MarkedY;
    }
    public void setCALENDAR_MarkedY(int CALENDAR_MarkedY) {
        this.CALENDAR_MarkedY = CALENDAR_MarkedY;
    }
    public int getCALENDAR_MarkedM() {
        return this.CALENDAR_MarkedM;
    }
    public void setCALENDAR_MarkedM(int CALENDAR_MarkedM) {
        this.CALENDAR_MarkedM = CALENDAR_MarkedM;
    }
    public int getCALENDAR_MarkedD() {
        return this.CALENDAR_MarkedD;
    }
    public void setCALENDAR_MarkedD(int CALENDAR_MarkedD) {
        this.CALENDAR_MarkedD = CALENDAR_MarkedD;
    }

    public Calendar getCALENDAR_StartDate() { return this.CALENDAR_StartDate; }
    public void setCALENDAR_StartDate(Calendar CALENDAR_StartDate) { this.CALENDAR_StartDate = CALENDAR_StartDate; }
    public int getCALENDAR_StartY() { return this.CALENDAR_StartY; }
    public void setCALENDAR_StartY(int CALENDAR_StartY) { this.CALENDAR_StartY = CALENDAR_StartY; }
    public int getCALENDAR_StartM() { return this.CALENDAR_StartM; }
    public void setCALENDAR_StartM(int CALENDAR_StartM) { this.CALENDAR_StartM = CALENDAR_StartM; }
    public int getCALENDAR_StartD() { return this.CALENDAR_StartD; }
    public void setCALENDAR_StartD(int CALENDAR_StartD) { this.CALENDAR_StartD = CALENDAR_StartD; }

    public Calendar getCALENDAR_EndDate() { return this.CALENDAR_EndDate; }
    public void setCALENDAR_EndDate(Calendar CALENDAR_EndDate) {this.CALENDAR_EndDate = CALENDAR_EndDate; }
    public int getCALENDAR_EndY() { return this.CALENDAR_EndY; }
    public void setCALENDAR_EndY(int CALENDAR_EndY) { this.CALENDAR_EndY = CALENDAR_EndY; }
    public int getCALENDAR_EndM() { return this.CALENDAR_EndM; }
    public void setCALENDAR_EndM(int CALENDAR_EndM) { this.CALENDAR_EndM = CALENDAR_EndM; }
    public int getCALENDAR_EndD() { return this.CALENDAR_EndD; }
    public void setCALENDAR_EndD(int CALENDAR_EndD) { this.CALENDAR_EndD = CALENDAR_EndD; }

}