package bias.hugoandrade.calendarviewapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import bias.hugoandrade.calendarviewapp.data.Event;
import bias.hugoandrade.calendarviewapp.data.CALENDAR;
import bias.hugoandrade.calendarviewapp.helpers.YMDCalendar;
import bias.hugoandrade.calendarviewapp.uihelpers.CalendarDialog;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class CalendarViewWithNotesActivitySDK21 extends AppCompatActivity  {

    private final static int CREATE_EVENT_REQUEST_CODE = 100;
    private final static int MOVE_FROM_LOGIN_REQUEST_CODE = 200;

    private String[] mShortMonths;
    private CalendarView mCalendarView;
    private CalendarDialog mCalendarDialog;
    private final List<Event> mEventList = new ArrayList<>();
    private final List<CALENDAR> mFire_EventList = new ArrayList<>();
    private ListenerRegistration listenerUsers;
    private FirebaseFirestore Firestore= FirebaseFirestore.getInstance();
    private static int shape;
    private List<CALENDAR> sorted_CALENDARList = new ArrayList<>();
    private CalendarView.CalendarPagerAdapter calendarPagerAdapter;

    private List<View> monthview;
    private int sizex;

    public static Intent makeIntent(Context context) { return new Intent(context, CalendarViewWithNotesActivitySDK21.class); }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mShortMonths = new DateFormatSymbols().getShortMonths();
        initializeUI();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //Log.d("끼륙륙","onRestart() 호출 : ");
    }
    @Override
    protected void onResume() {
        super.onResume();
        //Log.d("끼륙륙","onResume() 호출 : ");
    }


    private void initializeUI() {

        setContentView(R.layout.activity_calendar_view_with_notes_sdk_21);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mCalendarView = findViewById(R.id.calendarView);
        mCalendarView.setOnMonthChangedListener(new CalendarView.OnMonthChangedListener() {
            @Override
            public void onMonthChanged(int month, int year) {
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setTitle(mShortMonths[month]);
                    getSupportActionBar().setSubtitle(Integer.toString(year));
                }
            }
        });

        /*로그인 해서 들어왔을 때*/
        From_Login();

        /* 달력에서 일정의 유무에 다른 날짜 클릭 리스너*/
        mCalendarView.setOnItemClickedListener(new CalendarView.OnItemClickListener() {
            @Override
            public void onItemClicked(List<CalendarView.CalendarObject> calendarObjects,
                                      Calendar previousDate,
                                      Calendar selectedDate) {

                if (calendarObjects.size() != 0) {
                    mCalendarDialog.setSelectedDate(selectedDate);
                    mCalendarDialog.show();
                }
                else {
                    if (diffYMD(previousDate, selectedDate) == 0)
                        createEvent(selectedDate);
                }
            }
        });

        /* 달력에서 일정의 유무에 다른 날짜 클릭 리스너*/
        mCalendarView.setOnItemTouchedListener(new CalendarView.OnItemTouchListener() {
            @Override
            public void onItemTouched(String title, int count, Calendar selectedDate, Calendar startDate, String Uid) {

                CollectionReference collectionReference_man = FirebaseFirestore.getInstance().collection("CALENDAR").document("t2hhOAN07xvtQkSQq3BK").collection("CALENDAR_MAN").document("202103").collection("202103");                // 파이어베이스의 posts에서
                collectionReference_man.whereEqualTo("CALENDAR_UID",Uid).get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        CALENDAR CALENDAR = create_event_firebase(document);
                                        Event event = convert_event(CALENDAR);
                                        Drag_Insert_Calendar(event);
                                    }mCalendarDialog.setEventList(mEventList);
                                }
                                FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
                                final DocumentReference Calendar_Docu =firebaseFirestore.collection("CALENDAR").document("t2hhOAN07xvtQkSQq3BK");
                                final DocumentReference Gender_Docu = Calendar_Docu.collection("CALENDAR_MAN").document("202103");
                                final DocumentReference documentReference =Gender_Docu.collection("202103").document(Uid);

                                Calendar endDate = YMDCalendar.toCalendar(new YMDCalendar(selectedDate.get(Calendar.DATE)+count-1,selectedDate.get(Calendar.MONTH), selectedDate.get(Calendar.YEAR)));

                                String id = generateID();
                                CALENDAR EventFirebase = new CALENDAR(
                                        Uid,
                                        id,
                                        title,
                                        startDate.get(Calendar.YEAR),
                                        startDate.get(Calendar.MONTH),
                                        startDate.get(Calendar.DATE),
                                        endDate.get(Calendar.YEAR),
                                        endDate.get(Calendar.MONTH),
                                        endDate.get(Calendar.DATE),
                                        startDate.get(Calendar.YEAR),
                                        startDate.get(Calendar.MONTH),
                                        startDate.get(Calendar.DATE),
                                        count
                                );

                                Event mOriginalEventFirebase = new Event(
                                        Uid,
                                        id,
                                        title,
                                        YMDCalendar.toCalendar(new YMDCalendar(startDate.get(Calendar.DATE),startDate.get(Calendar.MONTH),startDate.get(Calendar.YEAR))),
                                        YMDCalendar.toCalendar(new YMDCalendar(startDate.get(Calendar.DATE),startDate.get(Calendar.MONTH),startDate.get(Calendar.YEAR))),
                                        YMDCalendar.toCalendar(new YMDCalendar(endDate.get(Calendar.DATE),endDate.get(Calendar.MONTH),endDate.get(Calendar.YEAR))),
                                        count
                                );

                                storeUpload(documentReference, EventFirebase);
                                Insert_Calendar(mOriginalEventFirebase);
                                mCalendarDialog.setEventList(mEventList);
                            }
                        });
            }
        });

        /////////*일정 나열*/
        listenerUsers = Firestore.collection("CALENDAR").document("t2hhOAN07xvtQkSQq3BK").collection("CALENDAR_GIRL").document("202103").collection("202103")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value,
                                        @Nullable FirebaseFirestoreException e) {
                        if (e != null) { return; }
                        Toast.makeText(getApplicationContext(), "새로운 일정이 왔다", Toast.LENGTH_LONG).show();
                        int check = 0;
                        CALENDAR new_eventF = null;
                        for (QueryDocumentSnapshot doc : value) {
                            CALENDAR CALENDAR = create_event_firebase(doc);//민규가 만든 파이어베이스에서 정보 받아온걸로 이벤트 파이어베이스모델에 넣는 함수
                            for(int i=0;i<mEventList.size();i++){
                                if(mEventList.get(i).getCALENDAR_UID().equals(CALENDAR.getCALENDAR_UID())){
                                    break;
                                }
                                if(i == mEventList.size()-1){
                                    check = 1;
                                    new_eventF = CALENDAR;
                                }
                            }
                            break;
                        }
                        if(new_eventF != null){
                            Insert_Calendar(convert_event(new_eventF));
                            mCalendarDialog.setEventList(mEventList);
                            Log.d("asddd","eventeventeventeventeventeventevent : ");
                            Toast.makeText(getApplicationContext(), "새로운 일정이 완료되었었다", Toast.LENGTH_LONG).show();
                        }
                    }
                });

        /* 상단바 */
        if (getSupportActionBar() != null) {
            int month = mCalendarView.getCurrentDate().get(Calendar.MONTH);
            int year = mCalendarView.getCurrentDate().get(Calendar.YEAR);
            getSupportActionBar().setTitle(mShortMonths[month]);
            getSupportActionBar().setSubtitle(Integer.toString(year));
        }

        /* 일정 추가 버튼*/
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createEvent(mCalendarView.getSelectedDate());
            }
        });

        /* 다이얼로그 내부의 클릭리스너*/
        mCalendarDialog = CalendarDialog.Builder.instance(this)
                .setEventList(mEventList)
                .setOnItemClickListener(new CalendarDialog.OnCalendarDialogListener() {
                    @Override
                    public void onEventClick(Event event) {
                        onEventSelected(event);
                    }

                    @Override
                    public void onCreateEvent(Calendar calendar) {
                        createEvent(calendar);
                    }
                    @Override
                    public void onRightClick(Event event){ Drag_Insert_Calendar(event); mCalendarDialog.setEventList(mEventList);}
                })
                .create();
    }

    private static String generateID() {
        return Long.toString(System.currentTimeMillis());
    }

    private void storeUpload(DocumentReference documentReference, final CALENDAR mOriginalEventFirebase) {
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

    private void onEventSelected(Event event) {
        Activity context = CalendarViewWithNotesActivitySDK21.this;
        Intent intent = Create_Schadule.Revise_Schadle_Intent(context, event);
        startActivityForResult(intent, CREATE_EVENT_REQUEST_CODE);
        overridePendingTransition( R.anim.slide_in_up, R.anim.stay );
    }

    private void createEvent(Calendar selectedDate) {
        Activity context = CalendarViewWithNotesActivitySDK21.this;
        Intent intent = Create_Schadule.Create_Schadle_Intent(context, selectedDate);
        startActivityForResult(intent, CREATE_EVENT_REQUEST_CODE);
        overridePendingTransition( R.anim.slide_in_up, R.anim.stay );
    }

    private void DragcreateEvent(String title, int count, Calendar selectedDate, String Uid) {
        Activity context = CalendarViewWithNotesActivitySDK21.this;
        Intent intent = DragCreate_Schedule.DragCreate_Schedle_Intent(context, selectedDate);
        intent.putExtra("DragTitle", title);
        intent.putExtra("DragCount", count);
        intent.putExtra("DragEndDate", selectedDate);
        intent.putExtra("DragUid", Uid);
        startActivityForResult(intent, CREATE_EVENT_REQUEST_CODE);
        overridePendingTransition( R.anim.slide_in_up, R.anim.stay );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar_calendar_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_today: {
                mCalendarView.setSelectedDate(Calendar.getInstance());
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    /* 다른 액티비티에서 행동의 결과에 따른*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CREATE_EVENT_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                int Create_action = Create_Schadule.extractActionFromIntent(data);
                Event Create_event = Create_Schadule.extractEventFromIntent(data);

                Log.d("asdasdasd","event : " + Create_event);
                switch (Create_action) {
                    case Create_Schadule.ACTION_CREATE: {
                        Insert_Calendar(Create_event);
                        mCalendarDialog.setEventList(mEventList);
                        break;
                    }

                    case Create_Schadule.ACTION_EDIT: {
//                        Event oldEvent = null;
//                        for (Event e : mEventList) {
//                            if (Objects.equals(event.getID(), e.getID())) {
//                                oldEvent = e;
//                                break;
//                            }
//                        }
//                        if (oldEvent != null) {
//                            mEventList.remove(oldEvent);
//                            mEventList.add(event);
//
//                            mCalendarView.removeCalendarObjectByID(parseCalendarObject(oldEvent));
//                            mCalendarView.addCalendarObject(parseCalendarObject(event));
//                            mCalendarDialog = CalendarDialog.Builder.instance(this)
//                                    .setEventList(mEventList)
//                                    .setOnItemClickListener(new CalendarDialog.OnCalendarDialogListener() {
//                                        @Override
//                                        public void onEventClick(Event event) {
//                                            onEventSelected(event);
//                                        }
//
//                                        @Override
//                                        public void onCreateEvent(Calendar calendar) {
//                                            createEvent(calendar);
//                                        }
//                                    })
//                                    .create();
//                        }
                        break;
                    }
//                    case CreateEventActivity.ACTION_DELETE: {
//                        ArrayList<Event> oldEvent = new ArrayList<>();
//                        for (Event e : mEventList) {
//                            if (Objects.equals(event.getCALENDAR_UID(), e.getCALENDAR_UID())) {
//                                oldEvent.add(e);
//                            }
//                        }
//                        int count = oldEvent.size();
//                        for(int i = 0; i < count ; i++){
//                            mEventList.remove(oldEvent.get(i));
//                            mCalendarView.removeCalendarObjectByID(parseCalendarObject(oldEvent.get(i)));
//                        }
//                        mCalendarDialog.setEventList(mEventList);
//
//                        break;
//                    }
                }
            }else if (resultCode == DragCreate_Schedule.RESULT_DRAG) {
                int Drag_action = DragCreate_Schedule.extractActionFromIntent(data);
                Event Drag_event = DragCreate_Schedule.extractEventFromIntent(data);
                switch (Drag_action) {
                    case DragCreate_Schedule.ACTION_DRAG: {
                        Insert_Calendar(Drag_event);
                        mCalendarDialog.setEventList(mEventList);
                        break;
                    }
                }
            }
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /* 클릭한 날짜와 이전의 날짜를 비교*/
    public static int diffYMD(Calendar date1, Calendar date2) {
        if (date1.get(Calendar.YEAR) == date2.get(Calendar.YEAR) &&
                date1.get(Calendar.MONTH) == date2.get(Calendar.MONTH) &&
                date1.get(Calendar.DAY_OF_MONTH) == date2.get(Calendar.DAY_OF_MONTH))
            return 0;

        return date1.before(date2) ? -1 : 1;
    }

    /* 여기서 일정의 데이터를 object로 바꿔서
    171번째 줄  mCalendarView.addCalendarObject(parseCalendarObject(event));
    로 달력에 반영*/
    private static CalendarView.CalendarObject parseCalendarObject(Event event) {
        return new CalendarView.CalendarObject(
                event.getCALENDAR_id(),
                event.getCALENDAR_MarkedDate(),
                event.getCALENDAR_StartDate(),
                event.getCALENDAR_EndDate(),
                event.getCALENDAR_UID(),
                shape,
                event.getCALENDAR_DateCount());
    }

    //Event_firebase 를 생성하는 함수
    CALENDAR create_event_firebase(QueryDocumentSnapshot doc){
        CALENDAR e_firebase = new CALENDAR(
                doc.getData().get("CALENDAR_UID").toString(),
                doc.getData().get("CALENDAR_id").toString(),
                doc.getData().get("CALENDAR_Schedule").toString(),
                YMDCalendar.toCalendar(new YMDCalendar(Integer.parseInt(doc.getData().get("CALENDAR_StartD").toString()),
                        Integer.parseInt(doc.getData().get("CALENDAR_StartM").toString()),
                        Integer.parseInt(doc.getData().get("CALENDAR_StartY").toString())
                )),
                YMDCalendar.toCalendar(new YMDCalendar(Integer.parseInt(doc.getData().get("CALENDAR_EndD").toString()),
                        Integer.parseInt(doc.getData().get("CALENDAR_EndM").toString()),
                        Integer.parseInt(doc.getData().get("CALENDAR_EndY").toString())
                )),
                YMDCalendar.toCalendar(new YMDCalendar(Integer.parseInt(doc.getData().get("CALENDAR_FixD").toString()),
                        Integer.parseInt(doc.getData().get("CALENDAR_FixM").toString()),
                        Integer.parseInt(doc.getData().get("CALENDAR_FixY").toString())
                )),
                Integer.parseInt(doc.getData().get("CALENDAR_DateCount").toString())
        );
        return e_firebase;
    }

    //코드상에 사용하는 event로 변환하는 함수(Event_firebase -> Event)
    Event convert_event(CALENDAR CALENDAR){
        Event C_event = new Event(CALENDAR.getCALENDAR_UID(), CALENDAR.getCALENDAR_id(), CALENDAR.getCALENDAR_Schedule(),
                CALENDAR.getCALENDAR_StartDate(), CALENDAR.getCALENDAR_StartDate(), CALENDAR.getCALENDAR_EndDate(), CALENDAR.getCALENDAR_DateCount());
        return C_event;
    }

    //받은 day를 Event_firebase에 date에 넣은 후 convert_event로 변환 해준다.
    Event day_convert_event(CALENDAR CALENDAR, Calendar day, Calendar startday){
        Calendar date = YMDCalendar.toCalendar(new YMDCalendar(day.get(Calendar.DATE), day.get(Calendar.MONTH), day.get(Calendar.YEAR)));
        CALENDAR f_event=new CALENDAR(CALENDAR.getCALENDAR_UID(), CALENDAR.getCALENDAR_id(), CALENDAR.getCALENDAR_Schedule(),
                startday, CALENDAR.getCALENDAR_EndDate(),startday, CALENDAR.getCALENDAR_DateCount());
        Event c_event = new Event(); //초기화
        c_event =convert_event(f_event);   //민규가 만든 event_firebase -> evnet 바꾸는 함수
        c_event.setCALENDAR_MarkedDate(date);
        return c_event;
    }

    Event event_convert_event(Event event, Calendar day, Calendar startday){
        Calendar date = YMDCalendar.toCalendar(new YMDCalendar(day.get(Calendar.DATE), day.get(Calendar.MONTH), day.get(Calendar.YEAR)));
        Event f_event=new Event(event.getCALENDAR_UID(),event.getCALENDAR_id(),event.getCALENDAR_Schedule(),
                startday,startday,event.getCALENDAR_EndDate(), event.getCALENDAR_DateCount());
        f_event.setCALENDAR_MarkedDate(date);
        return f_event;
    }

    void Insert_Calendar(Event event){
        Calendar day = event.getCALENDAR_StartDate();
        Calendar endday = event.getCALENDAR_EndDate();
        Calendar startday = event.getCALENDAR_MarkedDate();
        if(day.get(Calendar.DATE) == (endday.get(Calendar.DATE)) && day.get(Calendar.MONTH) == (endday.get(Calendar.MONTH))){
            shape = 0;
            mEventList.add(event);
            mCalendarView.addCalendarObject(parseCalendarObject(event));
        } else{
            int count = 0;
            while (!day.after(endday)){
                if(day.get(Calendar.DATE) == (endday.get(Calendar.DATE))){    //끝에날
                    shape = 2;
                } else{   //중긴
                    if(count == 0){
                        shape = 1;
                    }else{
                        shape = 3;
                    }
                }
                count++;
                Event c_event = new Event(); //초기화
                c_event = event_convert_event(event,day,startday); //변경된 day를 넣어주는 함수
                mEventList.add(c_event);
                mCalendarView.addCalendarObject(parseCalendarObject(c_event));
                day.add(Calendar.DATE,1);
            }day.add(Calendar.DATE,-count);
        }
    }

    public void Drag_Insert_Calendar(Event event){
        Log.d("삭제","11111111111111111 : ");
        Calendar startday = event.getCALENDAR_StartDate();
        Calendar endday = event.getCALENDAR_EndDate();
        Calendar markedday = event.getCALENDAR_MarkedDate();
        if(startday.get(Calendar.DATE) == (endday.get(Calendar.DATE)) && startday.get(Calendar.MONTH) == (endday.get(Calendar.MONTH))){
            shape = 0;
            mEventList.remove(event);
            mCalendarView.removeCalendarObjectByID(parseCalendarObject(event));
            //mCalendarDialog.setEventList(mEventList);
        } else{
            ArrayList<Event> oldEvent = new ArrayList<>();
            for (Event e : mEventList) {
                if (Objects.equals(event.getCALENDAR_UID(), e.getCALENDAR_UID())) {
                    oldEvent.add(e);
                }
            }
            for(int i = 0; i < oldEvent.size() ; i++){
                mEventList.remove(oldEvent.get(i));
                mCalendarView.removeCalendarObjectByID(parseCalendarObject(oldEvent.get(i)));
            }
        }
    }

    void From_Login(){
        int logincheck = getIntent().getIntExtra("MOVE_FROM_LOGIN_REQUEST_CODE",0);
        //if(MOVE_FROM_LOGIN_REQUEST_CODE == logincheck){
        CollectionReference collectionReference_man = FirebaseFirestore.getInstance().collection("CALENDAR").document("t2hhOAN07xvtQkSQq3BK").collection("CALENDAR_MAN").document("202103").collection("202103");                // 파이어베이스의 posts에서
        collectionReference_man.orderBy("CALENDAR_StartD", Query.Direction.ASCENDING).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                CALENDAR CALENDAR = create_event_firebase(document);
                                sorted_CALENDARList.add(CALENDAR);
                            }
                            Collections.sort(sorted_CALENDARList);
                            for(int i = 0; i< sorted_CALENDARList.size() ; i++){
                                Insert_Calendar(convert_event(sorted_CALENDARList.get(i)));
                            }
                            mCalendarDialog.setEventList(mEventList);
                        }
                    }
                });
        CollectionReference collectionReference_girl = FirebaseFirestore.getInstance().collection("CALENDAR").document("t2hhOAN07xvtQkSQq3BK").collection("CALENDAR_GIRL").document("202103").collection("202103");                // 파이어베이스의 posts에서
        collectionReference_girl.orderBy("CALENDAR_StartD", Query.Direction.ASCENDING).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                CALENDAR CALENDAR = create_event_firebase(document);
                                sorted_CALENDARList.add(CALENDAR);
                            }
                            Collections.sort(sorted_CALENDARList);
                            for(int i = 0; i< sorted_CALENDARList.size() ; i++){
                                Insert_Calendar(convert_event(sorted_CALENDARList.get(i)));
                            }
                            mCalendarDialog.setEventList(mEventList);
                        }
                    }
                });
        logincheck = 0;
        // }
    }

}