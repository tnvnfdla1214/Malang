package bias.hugoandrade.calendarviewapp.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class COUPLE implements Serializable {
    private String COUPLE_UID;
    private int COUPLE_StartY;
    private int COUPLE_StartM;
    private int COUPLE_StartD;
    private int COUPLE_M_BirthY;
    private int COUPLE_M_BirthM;
    private int COUPLE_M_BirthD;
    private int COUPLE_G_BirthY;
    private int COUPLE_G_BirthM;
    private int COUPLE_G_BirthD;
    private String COUPLE_HostUID;
    private String COUPLE_GuestUID;

    public COUPLE(){}

    public COUPLE( String COUPLE_UID,int COUPLE_M_BirthY, int COUPLE_M_BirthM, int COUPLE_M_BirthD,int COUPLE_G_BirthY, int COUPLE_G_BirthM, int COUPLE_G_BirthD, String COUPLE_HostUID, String COUPLE_GuestUID){
        this.COUPLE_UID = COUPLE_UID;
        this.COUPLE_M_BirthY = COUPLE_M_BirthY;
        this.COUPLE_M_BirthM = COUPLE_M_BirthM;
        this.COUPLE_M_BirthD = COUPLE_M_BirthD;
        this.COUPLE_G_BirthY = COUPLE_G_BirthY;
        this.COUPLE_G_BirthM = COUPLE_G_BirthM;
        this.COUPLE_G_BirthD = COUPLE_G_BirthD;
        this.COUPLE_HostUID = COUPLE_HostUID;
        this.COUPLE_GuestUID = COUPLE_GuestUID;
    }

    public Map<String, Object> getCoupleInfo(){
        Map<String, Object> docData = new HashMap<>();
        docData.put("COUPLE_UID", COUPLE_UID);
        docData.put("COUPLE_StartY", COUPLE_StartY);
        docData.put("COUPLE_StartM", COUPLE_StartM);
        docData.put("COUPLE_StartD", COUPLE_StartD);
        docData.put("COUPLE_M_BirthY", COUPLE_M_BirthY);
        docData.put("COUPLE_M_BirthM", COUPLE_M_BirthM);
        docData.put("COUPLE_M_BirthD", COUPLE_M_BirthD);
        docData.put("COUPLE_G_BirthY", COUPLE_G_BirthY);
        docData.put("COUPLE_G_BirthM", COUPLE_G_BirthM);
        docData.put("COUPLE_G_BirthD", COUPLE_G_BirthD);
        docData.put("COUPLE_HostUID", COUPLE_HostUID);
        docData.put("COUPLE_GuestUID", COUPLE_GuestUID);
        return  docData;
    }

    public String getCOUPLE_UID() { return COUPLE_UID; }
    public void setCOUPLE_UID(String COUPLE_UID) { this.COUPLE_UID = COUPLE_UID; }

    public int getCOUPLE_StartY() { return COUPLE_StartY; }
    public void setCOUPLE_StartY(int COUPLE_StartY) { this.COUPLE_StartY = COUPLE_StartY; }

    public int getCOUPLE_StartM() { return COUPLE_StartM; }
    public void setCOUPLE_StartM(int COUPLE_StartM) { this.COUPLE_StartM = COUPLE_StartM; }

    public int getCOUPLE_StartD() { return COUPLE_StartD; }
    public void setCOUPLE_StartD(int COUPLE_StartD) { this.COUPLE_StartD = COUPLE_StartD; }

    public int getCOUPLE_M_BirthY() { return COUPLE_M_BirthY; }
    public void setCOUPLE_M_BirthY(int COUPLE_M_BirthY) { this.COUPLE_M_BirthY = COUPLE_M_BirthY; }

    public int getCOUPLE_M_BirthM() { return COUPLE_M_BirthM; }
    public void setCOUPLE_M_BirthM(int COUPLE_M_BirthM) { this.COUPLE_M_BirthM = COUPLE_M_BirthM; }

    public int getCOUPLE_M_BirthD() { return COUPLE_M_BirthD; }
    public void setCOUPLE_M_BirthD(int COUPLE_M_BirthD) { this.COUPLE_M_BirthD = COUPLE_M_BirthD; }

    public int getCOUPLE_G_BirthY() { return COUPLE_G_BirthY; }
    public void setCOUPLE_G_BirthY(int COUPLE_G_BirthY) { this.COUPLE_G_BirthY = COUPLE_G_BirthY; }

    public int getCOUPLE_G_BirthM() { return COUPLE_G_BirthM; }
    public void setCOUPLE_G_BirthM(int COUPLE_G_BirthM) { this.COUPLE_G_BirthM = COUPLE_G_BirthM; }

    public int getCOUPLE_G_BirthD() { return COUPLE_G_BirthD; }
    public void setCOUPLE_G_BirthD(int COUPLE_G_BirthD) { this.COUPLE_G_BirthD = COUPLE_G_BirthD; }

    public String getCOUPLE_HostUID() { return COUPLE_HostUID; }
    public void setCOUPLE_HostUID(String COUPLE_HostUID) { this.COUPLE_HostUID = COUPLE_HostUID; }

    public String getCOUPLE_GuestUID() { return COUPLE_GuestUID; }
    public void setCOUPLE_GuestUID(String COUPLE_GuestUID) { this.COUPLE_GuestUID = COUPLE_GuestUID; }

}