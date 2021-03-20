package bias.hugoandrade.calendarviewapp.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class USER implements Serializable {
    private String USER_Name;
    private int USER_Gender;
    private String USER_NickName;
    private int USER_BirthY;
    private int USER_BirthM;
    private int USER_BirthD;
    private String USER_CoupleUID;
    private String USER_UID;
    private int USER_Level;
    private String USER_ProfileImage;

    public USER(String USER_Name, int USER_Gender, String USER_NickName, int USER_BirthY , int USER_BirthM , int USER_BirthD,
                String USER_CoupleUID, String USER_UID, int USER_Level){     // part5 : 생성자 초기화 (7')
        this.USER_Name = USER_Name;
        this.USER_Gender = USER_Gender;
        this.USER_NickName = USER_NickName;
        this.USER_BirthY = USER_BirthY;
        this.USER_BirthM = USER_BirthM;    // + : 사용자 리스트 수정 (날짜 정보 추가)
        this.USER_BirthD = USER_BirthD;
        this.USER_CoupleUID = USER_CoupleUID;
        this.USER_UID = USER_UID;
        this.USER_Level = USER_Level;
    }
    public USER(String USER_Name, int USER_Gender, String USER_NickName, int USER_BirthY , int USER_BirthM , int USER_BirthD,
                String USER_CoupleUID, String USER_UID, int USER_Level, String USER_ProfileImage){     // part5 : 생성자 초기화 (7')
        this.USER_Name = USER_Name;
        this.USER_Gender = USER_Gender;
        this.USER_NickName = USER_NickName;
        this.USER_BirthY = USER_BirthY;
        this.USER_BirthM = USER_BirthM;    // + : 사용자 리스트 수정 (날짜 정보 추가)
        this.USER_BirthD = USER_BirthD;
        this.USER_CoupleUID = USER_CoupleUID;
        this.USER_UID = USER_UID;
        this.USER_Level = USER_Level;
        this.USER_ProfileImage = USER_ProfileImage;
    }

    public Map<String, Object> getUSERInfo(){
        Map<String, Object> docData = new HashMap<>();
        docData.put("USER_Name", USER_Name);
        docData.put("USER_Gender", USER_Gender);
        docData.put("USER_NickName", USER_NickName);
        docData.put("USER_BirthY", USER_BirthY);
        docData.put("USER_BirthM", USER_BirthM);
        docData.put("USER_BirthD", USER_BirthD);
        docData.put("USER_CoupleUID", USER_CoupleUID);
        docData.put("USER_UID", USER_UID);
        docData.put("USER_Level", USER_Level);
        docData.put("USER_ProfileImage", USER_ProfileImage);
        return  docData;
    }
    public USER(){ }

    public String getUSER_Name() { return USER_Name; }
    public void setUSER_Name(String USER_Name) { this.USER_Name = USER_Name; }

    public int getUSER_Gender() { return USER_Gender; }
    public void setUSER_Gender(int USER_Gender) { this.USER_Gender = USER_Gender; }

    public String getUSER_NickName() { return USER_NickName; }
    public void setUSER_NickName(String USER_NickName) { this.USER_NickName = USER_NickName; }

    public int getUSER_BirthY() { return USER_BirthY; }
    public void setUSER_BirthY(int USER_BirthY) { this.USER_BirthY = USER_BirthY; }

    public int getUSER_BirthM() { return USER_BirthM; }
    public void setUSER_BirthM(int USER_BirthM) { this.USER_BirthM = USER_BirthM; }

    public int getUSER_BirthD() { return USER_BirthD; }
    public void setUSER_BirthD(int USER_BirthD) { this.USER_BirthD = USER_BirthD; }

    public String getUSER_CoupleUID() { return USER_CoupleUID; }
    public void setUSER_CoupleUID(String USER_CoupleUID) { this.USER_CoupleUID = USER_CoupleUID; }

    public String getUSER_UID() { return USER_UID; }
    public void setUSER_UID(String USER_UID) { this.USER_UID = USER_UID; }

    public int getUSER_Level() { return USER_Level; }
    public void setUSER_Level(int USER_Level) { this.USER_Level = USER_Level; }

    public String getUSER_ProfileImage() { return USER_ProfileImage; }
    public void setUSER_ProfileImage(String USER_ProfileImage) { this.USER_ProfileImage = USER_ProfileImage; }




}
