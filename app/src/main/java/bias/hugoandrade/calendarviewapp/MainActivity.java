package bias.hugoandrade.calendarviewapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import bias.hugoandrade.calendarviewapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* 여기서는 별거 없이 안드로이드 버전만 체크한다 생각하면 될 듯*/
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setUKLocale(this);

        // 로그인 상태, 회원정보 등록 상태, 리뷰 미작성 여부 상태를 결정하는 함수
        Check_User_State();

        initializeUI();
    }

    private void Check_User_State() {

        // 현재 유저에 대한 파이어베이스 유저 정보
        final FirebaseUser CurrentUser = FirebaseAuth.getInstance().getCurrentUser();

        // if : 현재 파이어베이스 유저 정보가 없다면
        // else : 현재 파이어베이스 유저 정보가 있음
        if (CurrentUser == null) {
            myStartActivity(LoginActivity.class);
        } else {

            // CurruntUser에서 CurruntUser_Uid를 가져와 documentReference
            final String CurruntUser_Uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            final DocumentReference documentReference = FirebaseFirestore.getInstance().collection("USER").document(CurruntUser_Uid);

            documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document != null) {
                            // else : CurruntUser는 있으나 CurruntUser_Uid를 Uid로 가지는 document가 존재 X
                            if (document.exists()) {

                            } else {
                                myStartActivity(MemberInitActivity.class);
                            }
                        }
                    }
                }
            });
        }
    }

    private void initializeUI() {

        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView rvOptions = findViewById(R.id.rv_options);
        rvOptions.setHasFixedSize(true);
        rvOptions.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        OptionsAdapter adapter = new OptionsAdapter(
                new Option("CalendarView with notes", "Add notes to the calendar view")
        );
        adapter.setOnClickListener(new OptionsAdapter.OnClickListener() {
            @Override
            public void onClick(int position) {
                Context context = MainActivity.this;
                switch (position) {
                    case 0:

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            startActivity(CalendarViewWithNotesActivitySDK21.makeIntent(context));
                        }
                        else {
                            //startActivity(CalendarViewWithNotesActivity.makeIntent(context));
                        }
                        break;
                }
            }
        });
        rvOptions.setAdapter(adapter);
    }

    private static void setUKLocale(Context context) {
        Locale.setDefault(Locale.UK);
        Resources res = context.getResources();
        Configuration config = new Configuration(res.getConfiguration());
        config.setLocale(Locale.UK);
        res.updateConfiguration(config, res.getDisplayMetrics());
    }

    static class OptionsAdapter extends RecyclerView.Adapter<OptionsAdapter.ViewHolder> {

        final Option[] mOptions;
        OnClickListener mListener;

        OptionsAdapter(Option... options) {
            mOptions = options;
        }

        @Override
        public OptionsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater vi = LayoutInflater.from(parent.getContext());
            return new ViewHolder(vi.inflate(R.layout.list_item_option, parent, false));
        }

        @Override
        public void onBindViewHolder(OptionsAdapter.ViewHolder holder, int position) {
            Option option = mOptions[holder.getAdapterPosition()];

            holder.tvTitle.setText(option.getTitle());
            holder.tvDescription.setText(option.getDescription());
        }

        @Override
        public int getItemCount() {
            return mOptions.length;
        }

        void setOnClickListener(OnClickListener listener) {
            mListener = listener;
        }

        interface OnClickListener {
            void onClick(int position);
        }

        class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            TextView tvTitle;
            TextView tvDescription;

            ViewHolder(View itemView) {
                super(itemView);

                itemView.setOnClickListener(this);

                tvTitle = itemView.findViewById(R.id.tv_title);
                tvDescription = itemView.findViewById(R.id.tv_description);
            }

            @Override
            public void onClick(View v) {
                if (mListener != null)
                    mListener.onClick(getAdapterPosition());
            }
        }
    }

    static class Option {

        String mTitle;
        String mDescription;

        Option(String title, String description) {
            mTitle = title;
            mDescription = description;
        }

        String getTitle() {
            return mTitle;
        }

        String getDescription() {
            return mDescription;
        }
    }

    private void myStartActivity(Class c) {
        Intent intent = new Intent(this, c);
        startActivityForResult(intent, 1);
    }
}
