package bias.hugoandrade.calendarviewapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
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
    private USER O_user = new USER();


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
//        O_Uid = intent.getStringExtra("O_Uid");
//        getUserModel(O_Uid);



        if(EXTRA_MESSAGE == 0){
            couple_host.setText(user.getUSER_NickName());
            couple_guest.setText(O_user.getUSER_NickName());

        }
        else if(EXTRA_MESSAGE == 1){
            couple_host.setText(O_user.getUSER_NickName());
            couple_guest.setText(user.getUSER_NickName());
        }

        app_start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
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
                            O_user = document.toObject(USER.class);
                        }
                    }
                }
            }
        });
        return;
    }
}
