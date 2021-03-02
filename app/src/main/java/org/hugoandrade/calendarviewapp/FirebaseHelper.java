package org.hugoandrade.calendarviewapp;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import org.hugoandrade.calendarviewapp.data.Event_firebase;

public class FirebaseHelper {
    private android.app.Activity Activity;
    private OnScheduleListener onScheduleListener;

    public FirebaseHelper(Activity Activity) {
        this.Activity = Activity;
    }

    public void setOnScheduleListener(OnScheduleListener onScheduleListener){ this.onScheduleListener = onScheduleListener; }


    public void Schedule_Delete(final Event_firebase eventFirebase){                                                 // part16: 스토리지의 삭제 (13')
        final String Event_Uid = eventFirebase.getEvent_Uid();
        Log.d("파이어", " Event_Uid 헬퍼 : " + Event_Uid);
        FirebaseFirestore Firebasefirestore = FirebaseFirestore.getInstance();
        Firebasefirestore.collection("SCHEDULE").document(Event_Uid)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        onScheduleListener.onScheduleDelete(eventFirebase);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {        }
                });
    }
}
