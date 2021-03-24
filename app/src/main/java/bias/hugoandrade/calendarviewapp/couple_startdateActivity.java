package bias.hugoandrade.calendarviewapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import bias.hugoandrade.calendarviewapp.data.COUPLE;
import bias.hugoandrade.calendarviewapp.data.USER;

public class couple_startdateActivity  extends AppCompatActivity {

    private USER user = new USER();

    private int COUPLE_StartY;
    private int COUPLE_StartM;
    private int COUPLE_StartD;

    Button nextBtn,prebtn;

    String couple_UId;
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    COUPLE couple =null;

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

                    couple_UId = firebaseFirestore.collection("COUPLE").document().getId();
                    final DocumentReference documentReference =firebaseFirestore.collection("COUPLE").document(couple_UId);
                    if(user.getUSER_Gender()==0){ //여자
                        couple = new COUPLE(
                                couple_UId, COUPLE_StartY, COUPLE_StartM, COUPLE_StartD,
                                0, 0, 0,
                                user.getUSER_BirthY(), user.getUSER_BirthM(), user.getUSER_BirthD(),
                                user.getUSER_UID(), "0"
                        );
                    }
                    else{ //남자
                        couple = new COUPLE(
                                couple_UId, COUPLE_StartY, COUPLE_StartM, COUPLE_StartD,
                                user.getUSER_BirthY(), user.getUSER_BirthM(), user.getUSER_BirthD(),
                                0, 0, 0,
                                user.getUSER_UID(), "0"
                        );
                    }
                    storeUpload(documentReference, couple);


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

    /*/*등록 함수*/
    private void storeUpload(DocumentReference documentReference, final COUPLE couple) {
        documentReference.set(couple.getCoupleInfo())
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
}
