package org.hugoandrade.calendarviewapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.hugoandrade.calendarviewapp.data.Event;
import org.hugoandrade.calendarviewapp.helpers.YMDCalendar;
import org.hugoandrade.calendarviewapp.utils.ColorUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/* 일정 작성 액티비티*/
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class CreateEventActivity extends AppCompatActivity {

    public static final int ACTION_DELETE = 1;
    public static final int ACTION_EDIT = 2;
    public static final int ACTION_CREATE = 3;

    private static final String INTENT_EXTRA_ACTION = "intent_extra_action";
    private static final String INTENT_EXTRA_EVENT = "intent_extra_event";
    private static final String INTENT_EXTRA_CALENDAR = "intent_extra_calendar";

    private static final int SET_DATE_AND_TIME_REQUEST_CODE = 200;

    private final static SimpleDateFormat dateFormat
            = new SimpleDateFormat("EEEE, dd/MM    HH:mm", Locale.getDefault());

    private Event mOriginalEvent;


    private Calendar mCalendar;
    private String mTitle;
    private boolean mIsComplete;
    private int mColor;
    private String mUid;

    private boolean isViewMode = true;

    private EditText mTitleView;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch mIsCompleteCheckBox;
    private TextView mDateTextView;
    private CardView mColorCardView;
    private View mHeader;

    private FirebaseHelper Firebasehelper;
    private InputMethodManager imm;

    public static Intent makeIntent(Context context, @NonNull Calendar calendar) {
        return new Intent(context, CreateEventActivity.class).putExtra(INTENT_EXTRA_CALENDAR, calendar);
    }

    public static Intent makeIntent(Context context, @NonNull Event event) {
        return new Intent(context, CreateEventActivity.class).putExtra(INTENT_EXTRA_EVENT, event);
    }

    public static Event extractEventFromIntent(Intent intent) {
        return intent.getParcelableExtra(INTENT_EXTRA_EVENT);
    }

    public static int extractActionFromIntent(Intent intent) {
        return intent.getIntExtra(INTENT_EXTRA_ACTION, 0);
    }

    public static Calendar extractCalendarFromIntent(Intent intent) {
        return (Calendar) intent.getSerializableExtra(INTENT_EXTRA_CALENDAR);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        setResult(RESULT_CANCELED);

        extractDataFromIntentAndInitialize();

        initializeUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_event, menu);

        return true;
    }

    /* 수정일 때 상단의 삭제 버튼 */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete: {
                delete();
                return true;
            }
            case android.R.id.home: {
                onBackPressed();
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    /* 일정 작성창 들어왔을 때 날짜선택부분 초기설정
    *   처음 일정 작성일 때와 일정 수정일 때 설정값이 다르다.
    *   (== 일정 작성과 수정이 같은 액티비티를 쓴다.)*/
    private void extractDataFromIntentAndInitialize() {

        mOriginalEvent = extractEventFromIntent(getIntent());

        if (mOriginalEvent == null) {
            mCalendar = extractCalendarFromIntent(getIntent());
            if (mCalendar == null)
                mCalendar = Calendar.getInstance();
            mCalendar.set(Calendar.HOUR_OF_DAY, 8);
            mCalendar.set(Calendar.MINUTE, 0);
            mCalendar.set(Calendar.SECOND, 0);
            mCalendar.set(Calendar.MILLISECOND, 0);
            mColor = ColorUtils.mColors[0];
            mTitle = "";
            mIsComplete = false;
            isViewMode = false;
        }
        else {
            mUid = mOriginalEvent.getEvent_Uid();
            mCalendar = mOriginalEvent.getDate();
            mColor = mOriginalEvent.getColor();
            mTitle = mOriginalEvent.getTitle();
            mIsComplete = mOriginalEvent.isCompleted();
            isViewMode = true;
        }
    }

    /* 실질적인 화면 구성*/
    private void initializeUI() {
        setContentView(R.layout.activity_create_event);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        mHeader = findViewById(R.id.ll_header);
        mHeader.setVisibility(View.VISIBLE);

        Firebasehelper = new FirebaseHelper(this);
        Firebasehelper.setOnScheduleListener(onPostListener);

        setupToolbar();


        View tvSave = mHeader.findViewById(R.id.tv_save);
        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
                imm.hideSoftInputFromWindow(mTitleView.getWindowToken(), 0);
            }
        });

        View tvCancel = mHeader.findViewById(R.id.tv_cancel);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();

                if (mOriginalEvent == null)
                    overridePendingTransition(R.anim.stay, R.anim.slide_out_down);
            }
        });

        /*날짜 클릭하면 날짜 선택할 수 있게 한다.*/
        mDateTextView = findViewById(R.id.tv_date);
        mDateTextView.setText(dateFormat.format(mCalendar.getTime()));
        mDateTextView.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View v) {
                Activity context = CreateEventActivity.this;
                Intent intent = SelectDateAndTimeActivity.makeIntent(context, mCalendar);

                startActivityForResult(intent,
                        SET_DATE_AND_TIME_REQUEST_CODE,
                        ActivityOptions.makeSceneTransitionAnimation(context).toBundle());
            }
        });

        /* 클릭하면, 일정의 색 선택가능*/
        mColorCardView = findViewById(R.id.cardView_event_color);
        mColorCardView.setCardBackgroundColor(mColor);
        mColorCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SelectColorDialog.Builder.instance(CreateEventActivity.this)
                        .setSelectedColor(mColor)
                        .setOnColorSelectedListener(new SelectColorDialog.OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(int color) {
                                mColor = color;
                                mColorCardView.setCardBackgroundColor(mColor);
                            }
                        })
                        .create()
                        .show();
            }
        });

        /*일정 제목 기입*/
        mTitleView = findViewById(R.id.et_event_title);
        mTitleView.setText(mTitle);
        mIsCompleteCheckBox = findViewById(R.id.checkbox_completed);
        mIsCompleteCheckBox.setChecked(mIsComplete);

        if (isViewMode) {
            mIsCompleteCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    setupEditMode();
                    mIsCompleteCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if(mIsComplete = false){
                                mIsComplete = true;
                                mIsCompleteCheckBox.setChecked(mIsComplete);
                            }else {
                                mIsComplete = false;
                                mIsCompleteCheckBox.setChecked(mIsComplete);
                            }
                        }
                    });
                }
            });
            mTitleView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    setupEditMode();
                    mTitleView.setOnFocusChangeListener(null);
                }
            });
        }
        else {
            setupEditMode();
        }
    }

    /* 상단의 툴바 설정 스위치 같은 것 참이면 설정하고 거짓으로 설정*/
    private void setupEditMode() {
        if (isViewMode) {
            isViewMode = false;
            setupToolbar();
        }
    }

    /* 상단의 툴바 설정*/
    private void setupToolbar() {
        if (getSupportActionBar() != null) {
            if (isViewMode)
                getSupportActionBar().show();
            else
                getSupportActionBar().hide();
        }

        if (mHeader != null) {
            mHeader.setVisibility(isViewMode? View.GONE : View.VISIBLE);
        }
    }

    /*삭제*/
    private void delete() {
        Log.e(getClass().getSimpleName(), "delete");
        Firebasehelper.Schedule_Delete(mOriginalEvent);
        setResult(RESULT_OK, new Intent()
                .putExtra(INTENT_EXTRA_ACTION, ACTION_DELETE)
                .putExtra(INTENT_EXTRA_EVENT, mOriginalEvent));
        finish();
        overridePendingTransition(R.anim.stay, R.anim.slide_out_down);

    }

    OnScheduleListener onPostListener = new OnScheduleListener() {
        @Override
        public void onScheduleDelete(Event event) { }
        @Override
        public void onModify() { Log.e("로그 ","수정 성공"); }
    };
    /*일정 작성 버튼*/
/////* 여기서 mcalendar 변수 로그로 찾아다가 파베에다가 같은 형식으로 넣고 빼서 읽게하면 될수도...??*/
    private void save() {

        int action = mOriginalEvent != null ? ACTION_EDIT : ACTION_CREATE;
        String id = mOriginalEvent != null ? mOriginalEvent.getID() : generateID();
        String rawTitle = mTitleView.getText().toString().trim();

        YMDCalendar ymdCalendar = new YMDCalendar(mCalendar.get(Calendar.YEAR),
                mCalendar.get(Calendar.MONTH)+1,
                mCalendar.get(Calendar.DATE));
        Calendar calendar = YMDCalendar.toCalendar(ymdCalendar);

        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

        String calendar_Id = mOriginalEvent != null ? mUid : firebaseFirestore.collection("SCHEDULE").document().getId();
        final DocumentReference documentReference =firebaseFirestore.collection("SCHEDULE").document(calendar_Id);

        mOriginalEvent = new Event(
                calendar_Id,
                id,
                rawTitle.isEmpty() ? null : rawTitle,
                mCalendar.get(Calendar.YEAR),
                mCalendar.get(Calendar.MONTH),
                mCalendar.get(Calendar.DATE),
                mColor,
                mIsCompleteCheckBox.isChecked()
        );
        storeUpload(documentReference, mOriginalEvent);
/////////* 저장 버튼 눌렸을 때 파이어베이스에 넣는다.*/




        setResult(RESULT_OK, new Intent()
                .putExtra(INTENT_EXTRA_ACTION, action)
                .putExtra(INTENT_EXTRA_EVENT, mOriginalEvent));
        finish();

        if (action == ACTION_CREATE)
            Log.d("파이어cc", " 111111111 : ");
            overridePendingTransition(R.anim.stay, R.anim.slide_out_down);
        if (action == ACTION_EDIT)
            Log.d("파이어cc", " 2222222222 : ");
            overridePendingTransition(R.anim.stay, R.anim.slide_out_down);
    }

    /*/*등록 함수*/
    private void storeUpload(DocumentReference documentReference, final Event mOriginalEvent) {
        documentReference.set(mOriginalEvent.getScheduleInfo())
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

    /* 선택된 날짜시간 받아옴 */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SET_DATE_AND_TIME_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                mCalendar = SelectDateAndTimeActivity.extractCalendarFromIntent(data);
                mDateTextView.setText(dateFormat.format(mCalendar.getTime()));

                setupEditMode();
            }
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private static String generateID() {
        return Long.toString(System.currentTimeMillis());
    }
}
