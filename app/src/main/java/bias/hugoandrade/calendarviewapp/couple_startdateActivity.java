package bias.hugoandrade.calendarviewapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import bias.hugoandrade.calendarviewapp.data.USER;

public class couple_startdateActivity  extends AppCompatActivity {

    private USER user = new USER();

    private int COUPLE_StartY;
    private int COUPLE_StartM;
    private int COUPLE_StartD;

    Button nextBtn,prebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_couple_startdate);

        Intent intent = getIntent();
        user = (USER) intent.getSerializableExtra("user");

        DatePicker StartDay_Picker = findViewById(R.id.StartDay_Picker);
        StartDay_Picker.init(2021, 0, 1, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                COUPLE_StartY = year;
                COUPLE_StartM = monthOfYear;
                COUPLE_StartD = dayOfMonth;
            }
        });
        nextBtn = findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(onClickListener);
        prebtn = findViewById(R.id.prebtn);
        prebtn.setOnClickListener(onClickListener);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.nextBtn:
                    Intent intent = new Intent(getApplicationContext(), couple_codecreateActivity.class);
                    intent.putExtra("user",user);
                    intent.putExtra("COUPLE_StartY",COUPLE_StartY);
                    intent.putExtra("COUPLE_StartM",COUPLE_StartM);
                    intent.putExtra("COUPLE_StartD",COUPLE_StartD);
                    startActivity(intent);
                    finish();
                    break;

                case R.id.prebtn:
                    onBackPressed();
                    break;
            }
        }
    };
}
