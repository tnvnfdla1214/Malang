package bias.hugoandrade.calendarviewapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import bias.hugoandrade.calendarviewapp.data.Event;
import bias.hugoandrade.calendarviewapp.data.Event_firebase;
import bias.hugoandrade.calendarviewapp.helpers.YMDCalendar;
import bias.hugoandrade.calendarviewapp.utils.ColorUtils;

public class DragCreate_Schedule  extends AppCompatActivity {

    //21로 보내주는 변수
    public static final int ACTION_DRAG = 1;
    public static final int RESULT_DRAG = 1000;

    private static final String INTENT_DRAG_CALENDAR = "DragEndDate";
    private static final String INTENT_START_CALENDAR = "DragStartDate";
    private static final String INTENT_EXTRA_EVENT = "intent_extra_event";
    private static final String INTENT_EXTRA_ACTION = "intent_extra_action";

    private ImageView schadule_cancel_button;
    private ImageView schadule_check_button;
    private EditText event_Schadule;
    private TextView start_date;
    private TextView end_date;

    private Calendar Start_Calendar;
    private Calendar End_Calendar;
    private InputMethodManager imm;
    private int mColor;
    private String Schedule;
    private int count;
    private String Draged_Uid;

    private final static SimpleDateFormat dateFormat
            = new SimpleDateFormat("EEEE, MM월dd일    HH:mm", Locale.getDefault());

    private static final int SET_DATE_AND_TIME_REQUEST_CODE = 200;
    private static final int SET_FINAL_DATE_AND_TIME_REQUEST_CODE = 300;

    Event event;

    private String Schedlue_Uid;

    //스케쥴 만들때 쓰는 함수
    public static Intent DragCreate_Schedle_Intent(Context context, @NonNull Calendar calendar) {
        return new Intent(context, DragCreate_Schedule.class).putExtra(INTENT_START_CALENDAR, calendar);
    }

    public static Calendar extractStartCalendarFromIntent(Intent data) {
        return (Calendar) data.getSerializableExtra(INTENT_START_CALENDAR);
    }
    public static Calendar extractEndCalendarFromIntent(Intent data) {
        return (Calendar) data.getSerializableExtra(INTENT_DRAG_CALENDAR);
    }
    public static Event extractEventFromIntent(Intent intent) {
        return intent.getParcelableExtra(INTENT_EXTRA_EVENT);
    }

    public static int extractActionFromIntent(Intent intent) {
        return intent.getIntExtra(INTENT_EXTRA_ACTION, 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_schedule);
        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        count = getIntent().getIntExtra("DragCount",0)-1;
        Draged_Uid = getIntent().getStringExtra("DragUid");
        Schedule = getIntent().getStringExtra("DragTitle");

        initializeUI();

        Draged_S();
        event_Schadule.setText(Schedule);


    }
    private void  initializeUI(){
        setContentView(R.layout.activity_create_schedule);

        schadule_cancel_button = findViewById(R.id.schadule_cancel_button);
        schadule_check_button = findViewById(R.id.schadule_check_button);
        event_Schadule = findViewById(R.id.event_Schadule);
        start_date = findViewById(R.id.start_date);
        end_date = findViewById(R.id.end_date);

        View back_view = findViewById(R.id.back_view);
        back_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        schadule_cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        schadule_check_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
                imm.hideSoftInputFromWindow(event_Schadule.getWindowToken(), 0);
            }
        });
    }

    private void Draged_S(){
        Start_Calendar = extractStartCalendarFromIntent(getIntent());
        End_Calendar = extractEndCalendarFromIntent(getIntent());
        End_Calendar.add(Calendar.DATE,count);


        event = null;

        //시작 날짜 기입 및 클릭 시 날짜를 선택할 수 있게 된다.
        start_date.setText(dateFormat.format(Start_Calendar.getTime()));
        start_date.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View v) {
                Activity context = DragCreate_Schedule.this;
                Intent intent = SelectDateAndTimeActivity.makeIntent(context, Start_Calendar);

                startActivityForResult(intent,
                        SET_DATE_AND_TIME_REQUEST_CODE,
                        ActivityOptions.makeSceneTransitionAnimation(context).toBundle());
            }
        });

        //끝 날짜 기입 및 클릭 시 날짜를 선택할 수 있게 된다.
        end_date.setText(dateFormat.format(End_Calendar.getTime()));
        end_date.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View v) {
                Activity context = DragCreate_Schedule.this;
                Intent intent = SelectDateAndTimeActivity.makeIntent(context, End_Calendar);

                startActivityForResult(intent,
                        SET_FINAL_DATE_AND_TIME_REQUEST_CODE,
                        ActivityOptions.makeSceneTransitionAnimation(context).toBundle());
            }
        });

        //남자면 블루 여자면 핑크 색 정보 넣기 아직 없으니까 나중에 넣기
    }

    /* 선택된 날짜시간 받아옴 */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SET_DATE_AND_TIME_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Start_Calendar = SelectDateAndTimeActivity.extractCalendarFromIntent(data);
                start_date.setText(dateFormat.format(Start_Calendar.getTime()));
            }
            return;
        }
        if (requestCode == SET_FINAL_DATE_AND_TIME_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                End_Calendar = SelectDateAndTimeActivity.extractCalendarFromIntent(data);
                end_date.setText(dateFormat.format(End_Calendar.getTime()));
            }
            return;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    /*일정 작성 버튼*/
    private void save() {

        //Event 정보가 있을경우는 수정 정보를 메인으로 넘겨주고 없을경우 생성 정보를 21에 넘겨준다.
        int action = ACTION_DRAG;
        String id = generateID();
        Schedule = event_Schadule.getText().toString().trim();

        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

        final DocumentReference Calendar_Docu =firebaseFirestore.collection("CALENDAR").document("t2hhOAN07xvtQkSQq3BK");

        final DocumentReference Gender_Docu = Calendar_Docu.collection("CALENDAR_MAN").document("202103");

        //String calendar_Id= Gender_Docu.collection("202103").document().getId();

        final DocumentReference documentReference =Gender_Docu.collection("202103").document(Draged_Uid);

        mColor = ColorUtils.mColors[0];  //이거로 남자 여자 구분하기
        boolean mIsCompleteCheckBox = false;  //이거 뺴야 함

        int count = Date_Count(Start_Calendar,End_Calendar);

        Event_firebase EventFirebase = new Event_firebase(
                Draged_Uid,
                id,
                Schedule.isEmpty() ? null : Schedule,
                Start_Calendar.get(Calendar.YEAR),
                Start_Calendar.get(Calendar.MONTH),
                Start_Calendar.get(Calendar.DATE),
                End_Calendar.get(Calendar.YEAR),
                End_Calendar.get(Calendar.MONTH),
                End_Calendar.get(Calendar.DATE),
                Start_Calendar.get(Calendar.YEAR),
                Start_Calendar.get(Calendar.MONTH),
                Start_Calendar.get(Calendar.DATE),
                count
        );

        Event mOriginalEventFirebase = new Event(
                Draged_Uid,
                id,
                Schedule.isEmpty() ? null : Schedule,
                YMDCalendar.toCalendar(new YMDCalendar(Start_Calendar.get(Calendar.DATE),Start_Calendar.get(Calendar.MONTH),Start_Calendar.get(Calendar.YEAR))),
                YMDCalendar.toCalendar(new YMDCalendar(Start_Calendar.get(Calendar.DATE),Start_Calendar.get(Calendar.MONTH),Start_Calendar.get(Calendar.YEAR))),
                YMDCalendar.toCalendar(new YMDCalendar(End_Calendar.get(Calendar.DATE),End_Calendar.get(Calendar.MONTH),End_Calendar.get(Calendar.YEAR))),
                count
        );

        storeUpload(documentReference, EventFirebase);
        setResult(RESULT_DRAG, new Intent()
                .putExtra(INTENT_EXTRA_ACTION, action)
                .putExtra(INTENT_EXTRA_EVENT, mOriginalEventFirebase));
        finish();
    }

    /*/*등록 함수*/
    private void storeUpload(DocumentReference documentReference, final Event_firebase mOriginalEventFirebase) {
        documentReference.set(mOriginalEventFirebase.getScheduleInfo())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
            }
        });
    }

    //현재 시간을 string 형으로 받아옴
    private static String generateID() {
        return Long.toString(System.currentTimeMillis());
    }

    Integer Date_Count(Calendar Start_Calendar, Calendar End_Calendar){
        int count = 0;
        if(!(Start_Calendar.get(Calendar.DATE) == (End_Calendar.get(Calendar.DATE)))){
            while (!Start_Calendar.after(End_Calendar)) {
                count++;
                Start_Calendar.add(Calendar.DATE, 1);
            }Start_Calendar.add(Calendar.DATE, -count);
        }else{
            count = count +1;
        }

        return count;
    }
}