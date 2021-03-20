package bias.hugoandrade.calendarviewapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;

import bias.hugoandrade.calendarviewapp.data.COUPLE;
import bias.hugoandrade.calendarviewapp.data.Event;
import bias.hugoandrade.calendarviewapp.data.Event_firebase;
import bias.hugoandrade.calendarviewapp.data.USER;

/* 뒤로가기는 파이어베이스에서 삭제했어야함 설계 잘못함*/
public class COUPLE_ListenerActivity extends AppCompatActivity {

    Button couple_connect_button;
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private FirebaseUser CurrentUser;
    private String CurrentUid;
    USER userModel = new USER();
    private String COUPLE_HostUID ;
    COUPLE couple =null;
    private ListenerRegistration listenerUsers;
    String couple_Id;
    private final static int MOVE_FROM_LOGIN_REQUEST_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_couple_listener);

        CurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        CurrentUid =CurrentUser.getUid();
        getUserModel(CurrentUid);

        COUPLE_HostUID = userModel.getUSER_UID();


        firebaseFirestore.collection("COUPLE")
                .whereEqualTo("COUPLE_HostUID", CurrentUid)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                couple_Id = document.getId();
                            }
                        } else {
                            couple_Id = firebaseFirestore.collection("COUPLE").document().getId();
                        }
                    }
                });




        couple_connect_button.findViewById(R.id.couple_connect_button);
        couple_connect_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DocumentReference documentReference =firebaseFirestore.collection("COUPLE").document(couple_Id);
                if(userModel.getUSER_Gender()==0){ //여자
                    couple = new COUPLE(
                            couple_Id,
                            0,
                            0,
                            0,
                            userModel.getUSER_BirthY(),
                            userModel.getUSER_BirthM(),
                            userModel.getUSER_BirthD(),
                            COUPLE_HostUID,
                            "0"
                    );
                }
                else{ //남자
                    couple = new COUPLE(
                            couple_Id,
                            userModel.getUSER_BirthY(),
                            userModel.getUSER_BirthM(),
                            userModel.getUSER_BirthD(),
                            0,
                            0,
                            0,
                            COUPLE_HostUID,
                            "0"
                    );
                }
                storeUpload(documentReference, couple);

            }
        });

        firebaseFirestore.collection("COUPLE")
                .whereNotEqualTo("COUPLE_GuestUID", "0")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value,
                                        @Nullable FirebaseFirestoreException e) {
                        Intent intent = new Intent(getApplicationContext(), CalendarViewWithNotesActivitySDK21.class);
                        intent.putExtra("MOVE_FROM_LOGIN_REQUEST_CODE", MOVE_FROM_LOGIN_REQUEST_CODE);
                        startActivity(intent);
                    }
                });
    }

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
}