package org.hugoandrade.calendarviewapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.hugoandrade.calendarviewapp.data.Event;
import org.hugoandrade.calendarviewapp.data.Event_firebase;
import org.hugoandrade.calendarviewapp.helpers.YMDCalendar;
import org.hugoandrade.calendarviewapp.uihelpers.CalendarDialog;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class CalendarViewWithNotesActivitySDK21 extends AppCompatActivity  {

    private final static int CREATE_EVENT_REQUEST_CODE = 100;

    private String[] mShortMonths;
    private CalendarView mCalendarView;
    private CalendarDialog mCalendarDialog;

    private final List<Event> mEventList = new ArrayList<>();
    private final List<Event_firebase> mFire_EventList = new ArrayList<>();

    private ListenerRegistration listenerUsers;
    private FirebaseFirestore Firestore= FirebaseFirestore.getInstance();


    public static Intent makeIntent(Context context) {
        return new Intent(context, CalendarViewWithNotesActivitySDK21.class);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mShortMonths = new DateFormatSymbols().getShortMonths();

        initializeUI();

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



/////////*일정 나열*/
        listenerUsers = Firestore.collection("SCHEDULE")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value,
                                        @Nullable FirebaseFirestoreException e) {
                        if (e != null) { return; }

                        for (QueryDocumentSnapshot doc : value) {
                            Event_firebase event_firebase = create_event_firebase(doc);//민규가 만든 파이어베이스에서 정보 받아온걸로 이벤트 파이어베이스모델에 넣는 함수

                            //석규가 만든 중복되는거없애는거는 아직 안만듬
                            //하루짜리 날짜 생성
                            if(event_firebase.getDate().equals(event_firebase.getFinalDate())){
                                Event c_event = new Event(); //초기화
                                c_event =convert_event(event_firebase);   //민규가 만든 event_firebase -> evnet 바꾸는 함수
                                mEventList.add(c_event);
                                mCalendarView.addCalendarObject(parseCalendarObject(c_event));
                                //캘린더뷰에 모양 뭔지 보내주어야함 -> 0
                            }
                            /* 2일 이상 짜리 = 일정 n개만큼 만들어줌 */
                            else{
                                Calendar startday = event_firebase.getDate();
                                Calendar endday = event_firebase.getFinalDate();
                                Calendar day = Calendar.getInstance();
                                day = startday;
                                while (!startday.after(endday)){
                                    if(day.equals(startday)){
                                        //캘린더뷰에 모양 뭔지 보내주어야함 -> 1
                                    }
                                    else if(day.equals(endday)){
                                        //캘린더뷰에 모양 뭔지 보내주어야함 -> 2
                                    }
                                    else{
                                        //캘린더뷰에 모양 뭔지 보내주어야함 -> 3
                                    }
                                        Event c_event = new Event(); //초기화
                                        c_event=day_convert_event(event_firebase,day); //변경된 day를 넣어주는 함수
                                        mEventList.add(c_event);
                                        mCalendarView.addCalendarObject(parseCalendarObject(c_event));
                                    day.add(Calendar.DATE,1);
                                }
                            }
                        }
                        mCalendarDialog.setEventList(mEventList);
                    }
                });


        for (Event e : mEventList) {
            mCalendarView.addCalendarObject(parseCalendarObject(e));
        }

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
                })
                .create();
    }

    private void onEventSelected(Event_firebase event_firebase) {
        Activity context = CalendarViewWithNotesActivitySDK21.this;
        Intent intent = CreateEventActivity.makeIntent(context, event_firebase);

        startActivityForResult(intent, CREATE_EVENT_REQUEST_CODE);
        overridePendingTransition( R.anim.slide_in_up, R.anim.stay );
    }

    private void createEvent(Calendar selectedDate) {
        Activity context = CalendarViewWithNotesActivitySDK21.this;
        Intent intent = CreateEventActivity.makeIntent(context, selectedDate);

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
                int action = CreateEventActivity.extractActionFromIntent(data);
                Event_firebase event_firebase = CreateEventActivity.extractEventFromIntent(data);

                switch (action) {
                    case CreateEventActivity.ACTION_CREATE: {
//                        mEventList.add(event);
//                        mCalendarView.addCalendarObject(parseCalendarObject(event));
//                        mCalendarDialog.setEventList(mEventList);
                        break;
                    }
                    case CreateEventActivity.ACTION_EDIT: {
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
                    case CreateEventActivity.ACTION_DELETE: {
                        Event oldEvent = null;
                        for (Event e : mEventList) {
                            if (Objects.equals(event_firebase.getmID(), e.getmID())) {
                                oldEvent = e;
                                break;
                            }
                        }
                        if (oldEvent != null) {
                            mEventList.remove(oldEvent);
                            mCalendarView.removeCalendarObjectByID(parseCalendarObject(oldEvent));

                            mCalendarDialog.setEventList(mEventList);
                        }
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
        int a = event.isCompleted() ? Color.TRANSPARENT : Color.RED;
        return new CalendarView.CalendarObject(
                event.getmID(),
                event.getmDate(),
                event.getmColor(),
                event.isCompleted() ? Color.TRANSPARENT : Color.RED,
                event.getEvent_Uid());

    }
    //Event_firebase 를 생성하는 함수
    Event_firebase create_event_firebase(QueryDocumentSnapshot doc){
        Event_firebase e_firebase = new Event_firebase(
                doc.getData().get("ScheduleModel_Uid").toString(),
                doc.getData().get("ScheduleModel_Id").toString(),
                doc.getData().get("ScheduleModel_Title").toString(),
                YMDCalendar.toCalendar(new YMDCalendar(Integer.parseInt(doc.getData().get("ScheduleModel_Day").toString()),
                        Integer.parseInt(doc.getData().get("ScheduleModel_Month").toString()),
                        Integer.parseInt(doc.getData().get("ScheduleModel_Year").toString())
                )),
                YMDCalendar.toCalendar(new YMDCalendar(Integer.parseInt(doc.getData().get("ScheduleModel_Final_Day").toString()),
                        Integer.parseInt(doc.getData().get("ScheduleModel_Final_Month").toString()),
                        Integer.parseInt(doc.getData().get("ScheduleModel_Final_Year").toString())
                )),
                Integer.parseInt(doc.getData().get("ScheduleModel_Color").toString()),
                Boolean.parseBoolean(doc.getData().get("ScheduleModel_isCompleted").toString())
        );
        return e_firebase;
    }

    //코드상에 사용하는 event로 변환하는 함수(Event_firebase -> Event)
    Event convert_event(Event_firebase event_firebase){
        Event C_event = new Event(event_firebase.getEvent_Uid(),event_firebase.getID(),event_firebase.getTitle(),
                                    event_firebase.getDate(),event_firebase.getColor(),event_firebase.isCompleted());
        return C_event;
    }


    //받은 day를 Event_firebase에 date에 넣은 후 convert_event로 변환 해준다.
    Event day_convert_event(Event_firebase event_firebase, Calendar day){
        Calendar date = YMDCalendar.toCalendar(new YMDCalendar(day.get(Calendar.DATE), day.get(Calendar.MONTH), day.get(Calendar.YEAR)));
        Event_firebase f_event=new Event_firebase(event_firebase.getEvent_Uid(),event_firebase.getID(),event_firebase.getTitle(),
                                        date,event_firebase.getFinalDate(),
                                        event_firebase.getColor(),event_firebase.isCompleted());
        Event c_event = new Event(); //초기화
        c_event =convert_event(event_firebase);   //민규가 만든 event_firebase -> evnet 바꾸는 함수

        return c_event;
    }



}