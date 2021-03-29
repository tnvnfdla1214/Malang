package bias.hugoandrade.calendarviewapp;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import bias.hugoandrade.calendarviewapp.data.COUPLE;
import bias.hugoandrade.calendarviewapp.data.USER;

/* 뒤로가기는 파이어베이스에서 삭제했어야함 설계 잘못함*/
public class couple_codecreateActivity extends AppCompatActivity {

    Button copy_code;
    Button prev_btn;
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    USER user = new USER();
    COUPLE couple =null;

    TextView t1,t2,t3,t4;


    private final static int MOVE_FROM_LOGIN_REQUEST_CODE = 200;

    String couple_UId;
    TextView cople_uid_text;
    DocumentReference docRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_couple_codecreate);

        cople_uid_text = findViewById(R.id.cople_uid_text);
        copy_code = findViewById(R.id.copy_code);
        copy_code.setOnClickListener(onClickListener);
        prev_btn = findViewById(R.id.prev_btn);
        prev_btn.setOnClickListener(onClickListener);

        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        t3 = findViewById(R.id.t3);
        t4 = findViewById(R.id.t4);

        Intent intent = getIntent();
        user = (USER) intent.getSerializableExtra("user");
        couple_UId = intent.getStringExtra("couple_UId");


        docRef = firebaseFirestore.collection("COUPLE").document(couple_UId);
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {

                if (e != null) {
                    Log.w("asdasd", "asdasdasd");
                    return;
                }
                else if (snapshot != null && snapshot.exists()) {
                    String ab = snapshot.getString("COUPLE_GuestUID");
                    if(!ab.equals("0")){
                        Intent intent = new Intent(getApplicationContext(), couple_finishActivity.class);
                        intent.putExtra("user",user);
                        intent.putExtra("EXTRA_MESSAGE",1);
                        intent.putExtra("O_Uid",ab);
                        intent.putExtra("couple_UId",couple_UId);
                        startActivity(intent);
                        finish();
                    }
                }

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        cople_uid_text.setText(couple_UId);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.copy_code:
                    ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("couple_UId", couple_UId);
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(getApplicationContext(), "복사되었습니다!", Toast.LENGTH_LONG).show();
                    t1.setText("연인이 코드를 입력하길");
                    t2.setText("기다리는 중입니다.....");
                    t3.setText("연인이 코드를 입력하면");
                    t4.setText("앱을 사용할 수 있어요!");



                    break;

                case R.id.prev_btn:

                    firebaseFirestore.collection("COUPLE").document(couple_UId)
                            .delete()
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                }
                            });

                    onBackPressed();
                    break;
            }
        }
    };

}