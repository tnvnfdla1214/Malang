package bias.hugoandrade.calendarviewapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import bias.hugoandrade.calendarviewapp.data.USER;

public class couple_finishActivity extends AppCompatActivity {

    Button app_start_button;
    TextView couple_host,couple_guest;

    private USER user = new USER();
    int EXTRA_MESSAGE=-1;

    String O_Uid =null;
    String couple_UId =null;
    private USER O_user = new USER();
    private USER _user = new USER();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_couplefinish);

        couple_host =findViewById(R.id.couple_host);
        couple_guest =findViewById(R.id.couple_guest);
        app_start_button = findViewById(R.id.app_start_button);

        Intent intent = getIntent();
        user = (USER) intent.getSerializableExtra("user");
        EXTRA_MESSAGE = intent.getIntExtra("EXTRA_MESSAGE",-1);
        O_Uid = intent.getStringExtra("O_Uid");
        couple_UId = intent.getStringExtra("couple_UId");
        user.setUSER_CoupleUID(couple_UId);
        Log.d("끼륙륙","O_Uid : " + O_Uid);
        getUserModel(O_Uid);

        app_start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void getUserModel(String O_Uid){
        final DocumentReference documentReference = FirebaseFirestore.getInstance().collection("USER").document(O_Uid);
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null) {
                        if (document.exists()) {  //데이터의 존재여부
                            O_user = document.toObject(USER.class);
                            if(EXTRA_MESSAGE == 0){
                                couple_host.setText(O_user.getUSER_NickName());
                                couple_guest.setText(user.getUSER_NickName());

                            }
                            else if(EXTRA_MESSAGE == 1){
                                couple_host.setText(user.getUSER_NickName());
                                couple_guest.setText(O_user.getUSER_NickName());
                            }
                            setCoupleUid_In_User(user.getUSER_UID());
                        }
                    }
                }
            }
        });
        return;
    }

    public void setCoupleUid_In_User(String CurrentUid) {
        final DocumentReference documentReference = FirebaseFirestore.getInstance().collection("USER").document(CurrentUid);
        documentReference.set(user.getUSERInfo())
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
