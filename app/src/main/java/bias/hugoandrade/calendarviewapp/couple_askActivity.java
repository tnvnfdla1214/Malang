package bias.hugoandrade.calendarviewapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import bias.hugoandrade.calendarviewapp.data.COUPLE;
import bias.hugoandrade.calendarviewapp.data.USER;

public class couple_askActivity extends AppCompatActivity {

    Button be_code,not_be_code;
    private String CurrentUid;
    private FirebaseUser CurrentUser;

    private USER user = new USER();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupleask);

        be_code = findViewById(R.id.be_code);
        be_code.setOnClickListener(onClickListener);
        not_be_code = findViewById(R.id.not_be_code);
        not_be_code.setOnClickListener(onClickListener);

        CurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        CurrentUid =CurrentUser.getUid();
        getUserModel(CurrentUid);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.be_code:
                    Intent intent = new Intent(getApplicationContext(), couple_editcodeActivity.class);
                    intent.putExtra("user",user);
                    Log.d("민규123123","user : " + user.getUSER_UID());
                    startActivity(intent);
                    break;

                case R.id.not_be_code:
                    Intent intent2 = new Intent(getApplicationContext(), couple_startdateActivity .class);
                    intent2.putExtra("user",user);
                    Log.d("민규123123","user : " + user.getUSER_UID());
                    startActivity(intent2);
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
                            USER test = new USER();
                            test =  document.toObject(USER.class);
                            user = new USER(test.getUSER_Name(),test.getUSER_Gender(),test.getUSER_NickName(),test.getUSER_BirthY()
                                    ,test.getUSER_BirthM(),test.getUSER_BirthD(),test.getUSER_CoupleUID(),test.getUSER_UID(),test.getUSER_Level());
                        }
                    }
                }
            }
        });
        return;
    }

}
