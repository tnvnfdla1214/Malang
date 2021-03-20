package bias.hugoandrade.calendarviewapp.data;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class USER implements Serializable {
    private String USER_Name;
    private String USER_Gender;
    private String USER_NickName;
    private Calendar USER_BirthDay;
    private String USER_CoupleUID;
    private String USER_UID;
    private int USER_Level;
    private String USER_ProfileImage;

    public USER(){ }

    public Map<String, Object> getUSERInfo(){
        Map<String, Object> docData = new HashMap<>();
        docData.put("USER_Name", USER_Name);
        docData.put("USER_Gender", USER_Gender);
        docData.put("USER_NickName", USER_NickName);
        docData.put("USER_BirthDay", USER_BirthDay);
        docData.put("USER_CoupleUID", USER_CoupleUID);
        docData.put("USER_UID", USER_UID);
        docData.put("USER_Level", USER_Level);
        docData.put("USER_ProfileImage", USER_ProfileImage);
        return  docData;
    }
}
