package org.hugoandrade.calendarviewapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
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
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import org.hugoandrade.calendarviewapp.data.Event;
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

    private ListenerRegistration listenerUsers;
    private FirebaseFirestore Firestore= FirebaseFirestore.getInstance();

    private long mShakeTime;
    private int mShakeCount = 0;
    private static final int SHAKE_SKIP_TIME = 500;
    private static final float SKAKE_THRESHOLD_GRAVITY = 2.7F;

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private FrameLayout frameLayout;

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
        frameLayout = mCalendarView.findViewById(R.id.day_item_1_2);
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


                                Event event = new Event(
                                        doc.getData().get("ScheduleModel_Id").toString(),
                                        doc.getData().get("ScheduleModel_Title").toString(),
                                        YMDCalendar.toCalendar(new YMDCalendar(Integer.parseInt(doc.getData().get("ScheduleModel_Day").toString()),
                                                Integer.parseInt(doc.getData().get("ScheduleModel_Month").toString()),
                                                Integer.parseInt(doc.getData().get("ScheduleModel_Year").toString())
                                        )),
                                        Integer.parseInt(doc.getData().get("ScheduleModel_Color").toString()),
                                        Boolean.getBoolean(doc.getData().get("ScheduleModel_isCompleted").toString())
                                );
                                Log.d("민규", " event : " + event);
                                Log.d("민규", " event.getTitle() : " + event.getID());
                                Log.d("민규", " event.getTitle() : " + event.getTitle());
                                Log.d("민규", " event.getTitle() : " + event.getDate());
                                Log.d("민규", " event.getTitle() : " + event.getColor());

                                //mEventList.add(event);
                                //
                                Event oldEvent = null;
                                for (Event ef : mEventList) {
                                    if (Objects.equals(event.getID(), ef.getID())) {
                                        oldEvent = ef;
                                        break;
                                    }
                                }
                                if (oldEvent == null) {
                                    Log.d("석규", " mEventList2 : " + mEventList);
                                    //mEventList = new ArrayList<>();
                                    mEventList.add(event);
                                    Log.d("석규", " mEventList3 : " + mEventList);
                                    mCalendarView.addCalendarObject(parseCalendarObject(event));

                                }


                                //mCalendarView.addCalendarObject(parseCalendarObject(event));
                                //mEventList.add(event);
                            //mEventList.add(event);
//                            for (Event ev : mEventList) {
//                                mCalendarView.addCalendarObject(parseCalendarObject(ev));
//                            }
                            //EventList.put(1, doc.toObject(Event.class));
                            //Log.d("민규", " EventList : " + EventList);
                        }

//                        for(int i = 0; i< mEventList.size();i++){
//                            Log.d("민규", " mEventList :  " + mEventList.get(i));
//                        }
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

    private void onEventSelected(Event event) {
        Activity context = CalendarViewWithNotesActivitySDK21.this;
        Intent intent = CreateEventActivity.makeIntent(context, event);

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
                Event event = CreateEventActivity.extractEventFromIntent(data);

                switch (action) {
                    case CreateEventActivity.ACTION_CREATE: {
//                        mEventList.add(event);
//                        mCalendarView.addCalendarObject(parseCalendarObject(event));
//                        mCalendarDialog.setEventList(mEventList);
                        break;
                    }
                    case CreateEventActivity.ACTION_EDIT: {
                        Event oldEvent = null;
                        for (Event e : mEventList) {
                            if (Objects.equals(event.getID(), e.getID())) {
                                oldEvent = e;
                                break;
                            }
                        }
                        if (oldEvent != null) {
                            mEventList.remove(oldEvent);
                            mEventList.add(event);

                            mCalendarView.removeCalendarObjectByID(parseCalendarObject(oldEvent));
                            mCalendarView.addCalendarObject(parseCalendarObject(event));
                            mCalendarDialog.setEventList(mEventList);
                        }
                        break;
                    }
                    case CreateEventActivity.ACTION_DELETE: {
                        Event oldEvent = null;
                        for (Event e : mEventList) {
                            if (Objects.equals(event.getID(), e.getID())) {
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
        return new CalendarView.CalendarObject(
                event.getID(),
                event.getDate(),
                event.getColor(),
                event.isCompleted() ? Color.TRANSPARENT : Color.RED);
    }

}
