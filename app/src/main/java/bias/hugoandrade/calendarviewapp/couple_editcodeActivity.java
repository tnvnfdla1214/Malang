package bias.hugoandrade.calendarviewapp;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

import bias.hugoandrade.calendarviewapp.data.COUPLE;
import bias.hugoandrade.calendarviewapp.data.USER;

/* 커플 get해오고, 나머지거 추가한다음에 set해야함 */
public class couple_editcodeActivity extends AppCompatActivity {

    private EditText Couple_Uid_EditText;
    private Button nextBtn,prev_Btn;

    private USER user = new USER();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_couple_editcode);

        Intent intent = getIntent();
        user = (USER) intent.getSerializableExtra("user");

        Couple_Uid_EditText = findViewById(R.id.Couple_Uid_EditText);
        nextBtn = findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(onClickListener);
        prev_Btn = findViewById(R.id.prev_Btn);
        prev_Btn.setOnClickListener(onClickListener);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.nextBtn:
                    final String Couple_Uid = Couple_Uid_EditText.getText().toString();
                    if(Couple_Uid.equals("")){ //아마 파베에서는 "" 아무것도 없으면 오류나는듯
                        Toast.makeText(getApplicationContext(), "커플코드를 입력해주세요!!", Toast.LENGTH_LONG).show();
                    }
                    else{
                        final DocumentReference docRef_Couple = FirebaseFirestore.getInstance().collection("COUPLE").document(Couple_Uid);
                        docRef_Couple.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                                DocumentSnapshot documentSnapshot = task.getResult();
                                COUPLE couple = new COUPLE();
                                couple = documentSnapshot.toObject(COUPLE.class);
                                if(couple != null){
                                    couple.setCOUPLE_GuestUID(user.getUSER_UID());
                                    if(user.getUSER_Gender() == 0){
                                        couple.setCOUPLE_G_BirthY(user.getUSER_BirthY());
                                        couple.setCOUPLE_G_BirthM(user.getUSER_BirthM());
                                        couple.setCOUPLE_G_BirthD(user.getUSER_BirthD());

                                    }else{
                                        couple.setCOUPLE_M_BirthY(user.getUSER_BirthY());
                                        couple.setCOUPLE_M_BirthM(user.getUSER_BirthM());
                                        couple.setCOUPLE_M_BirthD(user.getUSER_BirthD());
                                    }
                                    storeUpload(docRef_Couple, couple);
                                    Intent intent = new Intent(getApplicationContext(), couple_finishActivity.class);
                                    intent.putExtra("user",user);
                                    intent.putExtra("EXTRA_MESSAGE",0);
                                    String O_Uid = couple.getCOUPLE_HostUID();
                                    intent.putExtra("O_Uid",O_Uid);
                                    startActivity(intent);
                                    finish();

                                }else{
                                    Toast.makeText(getApplicationContext(), "커플코드를 다시한번 확인해주세요!", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }

                    break;

                case  R.id.prev_Btn:
                    onBackPressed();
                    break;
            }
        }
    };

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