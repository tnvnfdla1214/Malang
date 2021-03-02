package org.hugoandrade.calendarviewapp;

import org.hugoandrade.calendarviewapp.data.Event;
import org.hugoandrade.calendarviewapp.data.Event_firebase;

//삭제를 할 때에 FirebaseHelper와 연결되어 사용되는 Interface이다.

public interface OnScheduleListener {
    void onScheduleDelete(Event event);
//    void oncommentDelete(Market_CommentModel market_commentModel);
//    void oncommunityDelete(CommunityModel communityModel);
//    void oncommnitycommentDelete(Community_CommentModel community_commentModel);
    public void onModify();

}
