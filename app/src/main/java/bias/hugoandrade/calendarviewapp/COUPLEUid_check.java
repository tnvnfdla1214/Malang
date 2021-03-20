package bias.hugoandrade.calendarviewapp;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;

import java.util.ArrayList;

import bias.hugoandrade.calendarviewapp.data.COUPLE;
import bias.hugoandrade.calendarviewapp.data.USER;

/* 커플 get해오고, 나머지거 추가한다음에 set해야함 */
public class COUPLEUid_check extends AppCompatActivity {

    private EditText Couple_Uid_EditText;
    private Button nextBtn;
    private Button coupleBtn;
    private FirebaseUser CurrentUser;
    private String CurrentUid;

    private int COUPLE_StartY;
    private int COUPLE_StartM;
    private int COUPLE_StartD;
    private USER userModel = new USER();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupleuid_check);

        CurrentUser= FirebaseAuth.getInstance().getCurrentUser();

        Couple_Uid_EditText = findViewById(R.id.Couple_Uid);
        nextBtn = findViewById(R.id.nextBtn);
        coupleBtn = findViewById(R.id.coupleBtn);

        nextBtn.setOnClickListener(onClickListener);
        coupleBtn.setOnClickListener(onClickListener);

        CurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        CurrentUid =CurrentUser.getUid();
        getUserModel(CurrentUid);

        DatePicker StartDay_Picker = findViewById(R.id.StartDay_Picker);
        StartDay_Picker.init(2021, 0, 1, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                COUPLE_StartY = year;
                COUPLE_StartM = monthOfYear;
                COUPLE_StartD = dayOfMonth;
            }
        });

        ListenerRegistration registration;

        final String Couple_Uid = Couple_Uid_EditText.getText().toString();
        final DocumentReference docRef_Couple = FirebaseFirestore.getInstance().collection("COUPLE").document(Couple_Uid);
        docRef_Couple.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot documentSnapshot = task.getResult();
                COUPLE couple = new COUPLE();
                couple = documentSnapshot.toObject(COUPLE.class);
                if(couple != null){
                    couple.setCOUPLE_StartY(COUPLE_StartY);
                    couple.setCOUPLE_StartM(COUPLE_StartM);
                    couple.setCOUPLE_StartD(COUPLE_StartD);
                    couple.setCOUPLE_GuestUID(CurrentUser.getUid());
                    if(userModel.getUSER_Gender() == 0){
                        couple.setCOUPLE_G_BirthY(userModel.getUSER_BirthY());
                        couple.setCOUPLE_G_BirthM(userModel.getUSER_BirthM());
                        couple.setCOUPLE_G_BirthD(userModel.getUSER_BirthD());
                        /*
                        couple = new COUPLE(
                                Couple_Uid,
                                userModel.getUSER_BirthY(),
                                userModel.getUSER_BirthM(),
                                userModel.getUSER_BirthD(),
                                0,
                                0,
                                0,
                                COUPLE_HostUID,
                                "0"
                        );
                        * */


                    }else{
                        couple.setCOUPLE_M_BirthY(userModel.getUSER_BirthY());
                        couple.setCOUPLE_M_BirthM(userModel.getUSER_BirthM());
                        couple.setCOUPLE_M_BirthD(userModel.getUSER_BirthD());

                    }
                    storeUpload(docRef_Couple, couple);
                }else{
                    Toast.makeText(getApplicationContext(), "그런 커플은 없습니다", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.nextBtn:
                    Intent intent = new Intent(getApplicationContext(), COUPLE_ListenerActivity.class);
                    startActivity(intent);
                    finish();
                    break;

                case R.id.coupleBtn:
                    Intent intented = new Intent(getApplicationContext(), COUPLE_ListenerActivity.class);
                    startActivity(intented);
                    finish();
                    break;

            }
        }
    };
    public void getUserModel(String CurrentUid){
        final DocumentReference documentReference = FirebaseFirestore.getInstance().collection("USER").document(CurrentUid);
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null) {
                        if (document.exists()) {  //데이터의 존재여부
                            userModel = document.toObject(USER.class);
                        }
                    }
                }
            }
        });
        return;
    }
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