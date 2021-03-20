package bias.hugoandrade.calendarviewapp;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import bias.hugoandrade.calendarviewapp.data.Event;

public class FirebaseHelper {
    private android.app.Activity Activity;
    private OnScheduleListener onScheduleListener;

    public FirebaseHelper(Activity Activity) {
        this.Activity = Activity;
    }

    public void setOnScheduleListener(OnScheduleListener onScheduleListener){ this.onScheduleListener = onScheduleListener; }


    public void Schedule_Delete(final Event event){                                                 // part16: 스토리지의 삭제 (13')
        final String Event_Uid = event.getCALENDAR_UID();
        Log.d("파이어", " Event_Uid 헬퍼 : " + Event_Uid);
        FirebaseFirestore Firebasefirestore = FirebaseFirestore.getInstance();
        Firebasefirestore.collection("SCHEDULE").document(Event_Uid)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        onScheduleListener.onScheduleDelete(event);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {        }
                });
    }
}
