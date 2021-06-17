# Malang
## 1. App</br>
#### 연인들의 연인 맺기와 일정을 공유할 수 있는 어플리케이션</br>


## 말랑 이거 </br>
## 2. Explanation</br>
### Ⅰ chat(패키지)</br>
#### 1. activity (패키지)
 1. ChatActivity : 여러 변수를 다른 액티비티에서 받아오고 여러 프레그먼트를 담고 있다. </br>
  ◇ 주요 변수  </br>
  currentUser_Uid (현재 자신의 UID), To_User_Uid (상대방 UID), ChatRoomListModel_RoomUid(채팅방 UID), MarketModel_Market_Uid(마켓 UID)</br>
  ◆ 주요 기능   </br>
  ⓐ ChatFragment의 위치를 선정해 주고 주요변수를 넘겨준다. </br>
  ⓑ currentUser_Uid와 해당 마켓의 MarketModel_Host_Uid에 따라 다른 프레그먼트를 띄워준다. </br>
 2. SelectUserActivity : 그룹채팅방을 할 때 사용하는 액티비티 (현재는 사용하지 않음, 나중에 사용할지 모르므로 남겨 놓은 상태) </br>

#### 2. common (패키지)
 1. phoroview(패키지) : 사진 확대시 사용하는 패키지 (현재는 사용하지 않음, 나중에 사용할지 모르므로 남겨 놓은 상태이다.)  </br>
 2. MyAppGlideModule : Firebase Storage with Glide를 이용하여 ImageView에 이미지 띄우기위해 사용하는 자바 파일 </br>

#### 3. fragment (패키지)
 1. ChatFragment : 실제 채팅에 관련된 모든 기능을 담당하고 있다.  </br>
  ◇ 주요 변수  </br>
  currentUser_Uid (현재 자신의 UID), To_User_Uid (상대방 UID), ChatRoomListModel_RoomUid (채팅방 UID), MarketModel_Market_Uid (마켓 UID)</br>
  ◆ 주요 기능   </br>
  ⓐ 메세지 (글, 사진)을 파이어베이스를 통하여 상대방에게 보내준다. </br>
  ⓑ 상대방과 이전까지 했던 채팅 기록을 리스트 형태로 띄워준다. </br> 
 2. GroupUserFragment : 그룹채팅에 사용하는 프레그먼트 (현재는 사용하고 있지 않음) </br>
 3. Guest_Chat_MarketInfoFragment : 해당 마켓의 게스트용 마켓의 정보가 보여지는 프레그먼트</br>
  ◇ 주요 변수  </br>
  MarketModel_Market_Uid (마켓 UID) </br>
  ◆ 주요 기능   </br>
  ⓐ 마켓의 정보를 파이어베이스에서 가져와 정보를 띄워준다.</br>
 4. Host_Chat_MarketInfoFragment  : 해당 마켓의 호스트용 마켓의 정보가 보여지는 프레그먼트이고 해당 마켓의 예약과 거래완료기능이 있음</br>
  ◇ 주요 변수  </br>
  MarketModel_Market_Uid(마켓 UID), To_User_Uid(상대방 UID), currentUser_Uid (현재 자신의 UID) </br>
  ◆ 주요 기능   </br>
  ⓐ 마켓의 정보를 파이어베이스에서 가져와 정보를 띄워준다.  </br>
  ⓑ 해당 마켓의 예약과 거래완료를 설정할수있다.  </br>
 5. Nonepost_chat_MarketInfoFragment : 해당 마켓의 정보가 없을 때 띄우는 프레그먼트 </br>
  ◇ 주요 변수  </br>
  MarketModel_Market_Uid(마켓 UID)</br>
  ◆ 주요 기능   </br>
  ⓐ 마켓의 정보가 없을 때 빈 프레그먼트를 띄워준다.</br>


### Ⅱ community (패키지)</br>
#### 1. activity (패키지)
 1. CommunityActivity : 커뮤니티 페이지의 선택된 게시물의 상세 정보 액티비티 </br>
  ◇ 주요 변수  </br>
  Communitymodel (커뮤니티 모델), Usermodel (유저 모델), community_CommentModel(커뮤니티 댓글 모델)</br>
  ◆ 주요 기능   </br>
  ⓐ 해당 게시물의 상세정보와 해당 게시물의 작성자의 정보가 입력 되어있다.</br>
  ⓑ 좋아요라는 게시물의 관심도가 실시간으로 표시되며 해당 게시물에 입장한 게스트들은 '좋아요'를 누를 수 있다.</br>
  ⓒ 게시물 하단부에 댓글을 달 수 있다.</br>
 2. EnlargeCommunityImageActivity : CommunityActivity에서 이미지가 있다면 클릭하여 큰 Image의 뷰페이저 형식으로 볼 수 있는 액티비티 </br>
  ◆ 주요 기능   </br>
  ⓐ CommunityActivity에서 이미지가 있다면 클릭하여 큰 Image의 뷰페이저 형식으로 볼 수 있음</br>
 3. ModifyCommunityActivity  : 게시물의 수정을 위한 액티비티</br>
  ◇ 주요 변수  </br>
  Communitymodel</br>
  ◆ 주요 기능   </br>
  ⓐ 게시물에서 수정을 눌렀을 때 실행되는 액티비티가 실행되며 게시물 수정 액티비티로 이동된다.</br>
 4. SearchCommunityActivity : 검색을 실행하려 하고 검색하고자 하는 단어를 입력 받는 액티비티</br>
  ◇ 주요 변수  </br>
  Communitymodel</br>
  ◆ 주요 기능   </br>
  ⓐ 커뮤니티 페이지에서 검색을 실행하려 하고 검색하고자 하는 단어를 입력 받는 액티비티이며 단어를 입력한 후 버튼을 누르면 SearchCommunityResultActivity로 넘어가게 된다</br>
 5. SearchCommunityResultActivity : SearchCommunityActivity에서 버튼을 눌러 넘어 온 액티비티</br>
  ◆ 주요 기능   </br>
  ⓐ SearchCommunityResultFragment에서 Fragment를 이용하여 결과물을 출력한다. <-> SearchCommunityResultAdapter와 연결된다.</br>
  
#### 2. adapter (패키지)
 1. CommunityAdapter : CommunityFragment와 연결된 어댑터 </br>
  ◆ 주요 기능   </br>
  ⓐ onBindViewHolder로 카드뷰에 게시물의 정보들을 담는 역할을 한다.</br>
 2. CommunityViewPagerAdapter : 뷰페이져에서 이미지리스트의 string으로 저장된 이미지들을 imageList에 넣어서 CommunityActivity에서 슬라이드하여 이미지들을 볼 수 있게 해준다</br>
 3. SearchCommunityResultAdapter : SearchResultFragment와 연결된 어댑터</br>
  ◆ 주요 기능   </br>
  ⓐ onBindViewHolder로 카드뷰에 게시물의 정보들을 담는 역할을 한다.</br>
  
#### 3. fragment (패키지)
 1. SearchCommunityResultFragment : 검색 결과 액티비티에서의 결과를 담고 있는 프레그먼트</br>
  ◇ 주요 변수  </br>
  CommunityModel</br>
  ◆ 주요 기능   </br>
  ⓐ SearchCommunityActivity (abc 검색) -> SearchCommunityResultActivity (abc 가져옴) (abc 전해줌) -> SearchCommunityResultFragment (abc 가져옴)</br>
   
   
### Ⅲ Main (패키지)</br>
#### 1. activity (패키지)
 1. BasicActivity : 액티비티 생성시 틀이 되는 액티비티 </br>
  ◆ 주요 기능   </br>
  ⓐ AppCompatActivity를 기본으로 부가적인 기능을 추가하여 BasicActivity으로 만들었으며 다른 액티비티에서 이를 호출하여 사용한다.</br>
  ⓑ public class 쓰려는 액티비티 (extends AppCompatActivity) --> (extends BasicActivity)</br>
 2. HostModelActivity : 사용자의 정보를 보여주는 액티비티 </br>
 3. LoginActivity  : 로그인 액티비티</br>
  ◆ 주요 기능   </br>
  ⓐ 구글 로그인 버튼 선택시 구글 Auth를 통하여 로그인을 입력 받는다. 그 후 메인으로 넘겨준다.</br>
  ⓑ 카카오 로그인 선택시 카카오 측으로부터 아이디를 받아온다. 그 후 그 아이디와 미리 설정해 놓은 비밀 번호로 파이어베이스 로컬 아이디 Auth를 만들어 준다. 그 후 메인으로 넘겨준다.</br>
  ⓒ 이후 앱을 다시 실행 하였을때 로그인을 자동으로 확인하여 로그인액티비티를 지나치지 않고 바로 메인으로 넘어가게 된다.</br>
  ⓓ 처음 로그인(해당 아이디가 지금 막 생성되지 않았더라면)이 아니라면 해당 정보에 토큰을 확인하여 만약 토큰이 다르다면 새로 토큰을 받아와 유저정보를 업데이트 해준다.</br>
 4. MainActivity : 메인 액티비티</br>
  ◆ 주요 기능   </br>
  ⓐ 어플리케이션이 시작되면 처음 들르는 액티비티로 유저의 로그인 상태 정보를 판별하며, 하단 네비게이션의 각 속성들을 선언, 정의 해주는 액티비티</br>
  ⓑ 하단 네비게이션 : 마켓 페이지, 커뮤니티 페이지, 글쓰기 페이지, 채팅방 목록 페이지, 내정보 페이지</br>
  ⓒ 유저의 정보가 있는지를 확인하고 만약 없다면 InitActivity로 넘겨 준다.</br>
 5. MemberInitActivity : 이용자 정보를 입력받는 액티비티</br>
  ◆ 주요 기능   </br>
  ⓐ 사용자의 정보를 입력 받는다.</br>
  ⓑ 해당 액티비티에서 입력받은 정보들은 파이어스토어 USER라는 문서에 해당 유저의 UID를 통에 입력하게 된다.</br>
 6. MyInfo_WritenPostActivity : 내가 적은 게시물 목록 액티비티</br>
  ◇ 주요 변수  </br>
  currentUser_Uid</br>
  ◆ 주요 기능   </br>
  ⓐ My_Writen_Market_Fragment와 My_Writen_Community_Fragment를 담고 있다. </br>
 7. MyInfoPostActivity : 내정보 페이지에 예약중,거래완료,나에게 작성된 리뷰 프레그먼트를 담고 있다.</br>
  ◇ 주요 변수  </br>
  CurrentUid(현재 자신의 UID), Info(선택한 것의 넘버)</br>
  ◆ 주요 기능   </br>
  ⓐ 선택에 따라 프레그먼트를 다른 것을 보여준다. </br>
 8. ReviewActivity : 리뷰 액티비티</br>
  ◇ 주요 변수  </br>
  Usermodel(유저 모델), Reviewmodel(리뷰 모델)</br>
  ◆ 주요 기능   </br>
  ⓐ 거래 완료후 거래중이 였던 상대방의 사용자에 리뷰를 다는 액티비티 </br>
  ⓑ 친절함, 정확함, 완벽함, 불쾌감 중에 상태방의 상태를 선택한다. </br>
  ⓒ 후기작성 선택이 가능하다. 후기를 선택했다면 WriteReviewActivity로 이동된다. </br>
		ReviewActivity에서 불쾌감을 누를시 자동적으로 WriteReviewActivity로 이동된다. </br>
 9. UpdateInfoActivity  : 회원정보를 바꾸는 액티비티</br>
  ◇ 주요 변수  </br>
  Usermodel(유저 모델)</br>
  ◆ 주요 기능   </br>
  ⓐ MyInfoFragment에서 회원의 정보를 바꾸어 준다. </br>
 10. WriteCommunityActivity ; 커뮤니티 게시물 작성 액티비티 </br>
  ◇ 주요 변수  </br>
  Communitymodel(커뮤니티 모델)</br>
  ◆ 주요 기능   </br>
  ⓐ 글쓰기 중 커뮤니티페이지에 올라갈 게시물을 작성하는 액티비티이다. </br>
 11. WriteMarketActivity ; 커뮤니티 게시물 작성 액티비티</br>
  ◇ 주요 변수  </br>
  Marketmodel(마켓 모델)</br>
  ◆ 주요 기능   </br>
  ⓐ 글쓰기 중 마켓페이지에 올라갈 게시물을 작성하는 액티비티이다. </br>
 12. WriteReviewActivity : 리뷰에 후기를 작성하는 액티비티 </br>
  ◆ 주요 기능   </br>
  ⓐ 이용자는 ReviewActivity 에서 후기를 작성을 원할시 현 액티비티로 이동되어 작성할 수 있다.  </br>
  ⓑ ReviewActivity에서 불쾌감을 누를시 자동적으로 후기를 입력받게 한다.  </br>
  
#### 2. bottombar (패키지)
 1. ChatroomList_BottombarFragment : 채팅방 목록 페이지 프레그먼트 </br>
  ◇ 주요 변수  </br>
   chatRoomListModel(채팅 목록 모델)</br>
  ◆ 주요 기능   </br>
  ⓐ 현 이용자의 지금까지의 채팅을 하였었던 정보들을 파이어스토어 ROOMS에서 불러 와 Adapter에 배치 시켜 준다.</br>
  ⓑ 오른쪽 스와이핑을 통해 방을 나갈 수 도 있다.</br>
  ⓒ 해당 채팅방을 선택시 해당 채팅정보를 ChatActivity로 넘겨주며 이동하게 된다.</br>
 2. Community_BottombarFragment : 커뮤니티 페이지 프레그먼트</br>
  ◇ 주요 변수  </br>
  CommunityModel(커뮤니티 모델)</br>
  ◆ 주요 기능   </br>
  ⓐ 커뮤니티에 현재 올라와져 있는 모든 게시물을 파이어 스토어 COMMUNITY문서에서 정보를 불러와  Adapter에 배치시킨다.</br>
  ⓑ 핫 게시물을 통해 좋아요 개수에 맞추어 필터링을 시켜준다.</br>
  ⓒ 원하는 게시물의 카드뷰를 누를시 해당정보를 CommunityModel를 CommunityActivity로 넘겨주며 이동하게 된다.</br>
 3. Market_BottombarFragment: 마켓 페이지 프레그먼트</br>
  ◇ 주요 변수  </br>
  MarketModel(마켓 모델)</br>
  ◆ 주요 기능   </br>
  ⓐ 커뮤니티에 현재 올라와져 있는 모든 게시물을 파이어 스토어 MARKET문서에서 정보를 불러와  Adapter에 배치시킨다.</br>
  ⓑ 해당 카테고리별로 필터링이 가능하다.</br>
  ⓒ 핫 게시물을 통해 좋아요 개수에 맞추어 필터링을 시켜준다.</br>
  ⓓ 원하는 게시물의 카드뷰를 누를시 해당정보를 MarketModel를 MarketActivity로 넘겨주며 이동하게 된다.</br>
 4. MyInfo_BottombarFragment : 내정보 페이지 프레그먼트</br>
  ◇ 주요 변수  </br>
  CurrentUid(현재 자신의 UID)</br>
  ◆ 주요 기능   </br>
  ⓐ 현재 이용자의 정보를 띄워준다.</br>
  ⓑ 거래중,거래완료,내가 쓴 게시물를 볼수 있다.</br>
  ⓒ 현재 나의 리뷰의 상태를 알 수 있다.</br>
  ⓓ 공지사항, 자주 묻는질문, 앱설정, 로그아웃을 이용할 수 있다.(현재 공란)</br>
 
#### 3. common (패키지)
 1. OnPostListener (패키지) : 삭제를 할 때에 FirebaseHelper와 연결되어 사용되는 Interface</br>
 2. BackPressEditText : EditText에 사용하는 widget</br>
 3. OFirebaseHelper : 파이어베이스에서 파이어스토어,파이어스토리지의 MARKET,COMMUNITY,ROOMS삭제에 관여한다.</br>
 4. FirestoreAdapter : 파이어스토어 Data를 RecyclerView를 연결해줌</br>
 5. GlobalApplication : 카카오 로그인시 토큰받아오게 해줌</br>
 6. OItemTouchHelperCallback : ChatroomList_BottombarFragment 에서 오른쪽 스와이핑시 삭제라는 아이템이 나오고 클릭시 Adapter에서 삭제가 가능하게 함</br>
 7. ItemTouchHelperListener : ItemTouchHelperCallback의 함수를 불러오는 리스너</br>
 8. Loding_Dialog : 로딩중 다이어로그</br>
 9. Logout_Dialog : 로그아웃 다이어로그</br>
 10. MyFirebaseMessagingService : 파이어베이스 클라우드 메세징</br>
 11. ReviewResultAdapter : SearchResultFragment와 연결된 어댑터이다. onBindViewHolder로 카드뷰에 검색된 게시물의 정보들을 담는 역할을 한다. !검색된 결과의 게시물 나열!</br>
 12. SendNotification : 파이어베이스 클라우드 메세징</br>
 13. ThumbnailImageView : MainFragment에서 이미지를 썸네일로써 한장만 보여주고 2장 이상일경우 "더보기"로 구현</br>
 14. Util : BaseActivity와 같은 개념으로 여러군데에서 사용하는 특정 함수들을 모아서 선언해 놓은 Class이다,  가장 많이 사용되는 것은 토스트를 생성하는 메소드이지만, isStorageUrl에서 파이어베이스의 스토리지 경로를 저장한 부분이 중요한 곳이다.</br>
 15. Deal_Complete_Post_Fragment : 나와 거래가 되었던 포스트의 정보를 보여주는 프레그먼트</br>
  ◇ 주요 변수  </br>
   CurrentUid(현재 이용자 UID), MarketModel(마켓 모델)</br>
  ◆ 주요 기능   </br>
  ⓐ MyInfo_BottombarFragment에서 거래 완료라는 버튼을 클릭시 MyInfoPostActivity에 Deal_Complete_Post_Fragment 로 띄워진다.</br>
  ⓑ 파이어베이스 CurrentUid의 Market_dealList를 차례로 받아와 MARKETS에서 불러온다.</br>
 16. My_Writen_Community_Fragment: 나가 작성한 커뮤니티의 정보를 보여주는 프레그먼트</br>
  ◇ 주요 변수  </br>
   CurrentUid(현재 이용자 UID), CommunityModel(마켓 모델)</br>
  ◆ 주요 기능   </br>
  ⓐ MyInfo_BottombarFragment에서 거래 완료라는 버튼을 클릭시 MyInfoPostActivity에 My_Writen_Community_Fragment 로 띄워진다.</br>
  ⓑ 파이어베이스 COMMUNITY문서에서 CommunityModel_Host_Uid가 CurrentUid와 같고 CommunityModel_DateOfManufacture는 최신순으로 콜렉션 해온다.</br>
 17. My_Writen_Market_Fragment: 나가 작성한 마켓의 정보를 보여주는 프레그먼트</br>
  ◇ 주요 변수  </br>
  CurrentUid(현재 이용자 UID), MarketModel(마켓 모델)</br>
  ◆ 주요 기능   </br>
  ⓐ MyInfo_BottombarFragment에서 거래 완료라는 버튼을 클릭시 MyInfoPostActivity에 My_Writen_Market_Fragment 로 띄워진다.</br>
  ⓑ 파이어베이스 MARKET문서에서 MarketModel_Host_Uid가 CurrentUid와 같고 MarketModel_DateOfManufacture는 최신순으로 콜렉션 해온다.</br>
 18. Proceeding_Post_Fragment : 내가 거래중인 마켓의 정보를 보여주는 프레그먼트</br>
  ◇ 주요 변수  </br>
  CurrentUid(현재 이용자 UID), MarketModel(마켓 모델)</br>
  ◆ 주요 기능   </br>
  ⓐ MyInfo_BottombarFragment에서 진행중이라는 버튼을 클릭시 MyInfoPostActivity에 Proceeding_Post_Fragment 로 띄워진다.</br>
  ⓑ 파이어베이스 CurrentUid의 Market_reservationList를 차례로 받아와 MARKETS에서 불러온다.</br>
 19. To_Review_Writen_Fragment : 나에게 작성된 리뷰 프레그먼트</br>
  ◇ 주요 변수  </br>
  CurrentUid(현재 이용자 UID), ReviewModel(리뷰 모델)</br>
  ◆ 주요 기능   </br>
  ⓐ MyInfo_BottombarFragment에서 나에게 작성된 리뷰이라는 버튼을 클릭시 MyInfoPostActivity에 To_Review_Writen_Fragment띄워진다.</br>
  ⓑ USERS에서 CurrentUid중 REVIEW를 최신순으로 불러온다.</br>


### Ⅳ Market (패키지)</br>
#### 1. activity (패키지)
 1. EnlargeImageActivity : MarketActivity에서 이미지가 있다면 클릭하여 큰 Image의 뷰페이저 형식으로 볼 수 있음</br>
 2. GalleryActivity : 이미지를 단일 선택하고, 사용자의 프로필 사진의 선택을 할 때에 실행되는 액티비티이다. 초기화 버튼 존재</br>
  ◆ 주요 기능   </br>
  ⓐ MemberInitActivity, UpdateInfoActivity에서 사진을 고를 때에 사용된다. <-> GalleryAdapter와 연결된다.</br>
 3. MarketActivity: 커뮤니티 페이지의 선택된 게시물의 상세 정보 액티비티</br>
  ◇ 주요 변수  </br>
   Marketmodel (마켓 모델), Usermodel (유저 모델), Comment_MarketModel(커뮤니티 댓글 모델)</br>
  ◆ 주요 기능   </br>
  ⓐ 해당 게시물의 상세정보와 해당 게시물의 작성자의 정보가 입력 되어있다.</br>
  ⓑ 좋아요라는 게시물의 관심도가 실시간으로 표시되며 해당 게시물에 입장한 게스트들은 좋아요를 누를 수 있다.</br>
  ⓒ 해당 게시물의 작성자와 채팅하기 버튼을 통하여 채팅이 가능하다.</br>
 4. ModifyMarketActivity: 게시물의 수정을 위한 액티비티</br>
  ◇ 주요 변수  </br>
  MarketAmodel</br>
  ◆ 주요 기능   </br>
  ⓐ 게시물에서 수정을 눌렀을 때 실행되는 액티비티가 실행되며 게시물 수정 액티비티로 이동된다.</br>
 5. SearchMarketActivity : 검색을 실행하려 하고 검색하고자 하는 단어를 입력 받는 액티비티</br>
  ◇ 주요 변수  </br>
  MarketAmodel</br>
  ◆ 주요 기능   </br>
  ⓐ 마켓 페이지에서 검색을 실행하려 하고 검색하고자 하는 단어를 입력 받는 액티비티이며 단어를 입력한 후 버튼을 누르면 SearchMarketResultActivity로 넘어가게 된다</br>
 6. SearchMarketResultActivity : SearchMarketActivity에서 버튼을 눌러 넘어 온 액티비티</br>
  ◆ 주요 기능   </br>
  ⓐ  SearchMarketResultFragment에서 Fragment를 이용하여 결과물을 출력한다. <-> SearchMarketResultAdapter와 연결된다.</br>
 
#### 2. adapter (패키지)
 1. GalleryAdapter : GalleryActivity와 연결되어 사용되는 어댑터</br>
 2. MarketAdapter : MarketFragment와 연결된 어댑터</br>
  ◆ 주요 기능   </br>
  ⓐ onBindViewHolder로 카드뷰에 게시물의 정보들을 담는 역할을 한다</br>
 3. ViewPagerAdapter : 뷰페이져에서 이미지리스트의 string으로 저장된 이미지들을 imageList에 넣어서 MarketActivity에서 슬라이드하여 이미지들을 볼 수 있게 해준다</br>
 4. SearchMarketResultAdapter : SearchResultFragment와 연결된 어댑터 </br>
  ◆ 주요 기능   </br>
  ⓐ onBindViewHolder로 카드뷰에 검색된 게시물의 정보들을 담는 역할을 한다. 검색된 결과의 게시물 나열할때 사용</br>
  
#### 3. fragment (패키지)
 1. Market_Borrow_Fragment : SearchResultActivity에서 카테고리가 '대여하기'인  Market 나열 Fragment</br>
  ◇ 주요 변수  </br>
  Marketmodel(마켓 모델)</br>
  ◆ 주요 기능   </br>
  ⓐ SearchResultActivity에서 Info = 3을 가지고 이동된 Fragment : 카테고리가 '대여하기'인  Market 나열 Fragment</br>
 2. Market_Food_Fragment: SearchResultActivity에서 카테고리가 '음식교환'인  Market 나열 Fragment</br>
  ◇ 주요 변수  </br>
  Marketmodel(마켓 모델)</br>
  ◆ 주요 기능   </br>
  ⓐ SearchResultActivity에서 Info = 1을 가지고 이동된 Fragment : 카테고리가 '음식교환'인  Market 나열 Fragment</br>
 3. Market_Quest_Fragment: SearchResultActivity에서 카테고리가 '퀘스트'인  Market 나열 Fragment</br>
  ◇ 주요 변수  </br>
  Marketmodel(마켓 모델)</br>
  ◆ 주요 기능   </br>
  ⓐ SearchResultActivity에서 Info 4을 가지고 이동된 Fragment : 카테고리가 '퀘스트'인  Market 나열 Fragment</br>
 4. Market_Thing_Fragment: SearchResultActivity에서 카테고리가 '물건교환'인  Market 나열 Fragment</br>
  ◇ 주요 변수  </br>
  Marketmodel(마켓 모델)</br>
  ◆ 주요 기능   </br>
  ⓐ SearchResultActivity에서 Info = 2을 가지고 이동된 Fragment : 카테고리가 '물건교환'인  Market 나열 Fragment</br>
 5. SearchResultFragment : 검색된  Market 나열 Fragment</br>
  ◇ 주요 변수  </br>
  Marketmodel(마켓 모델)</br>
  ◆ 주요 기능   </br>
  ⓐ SearchResultActivity에서 Info = 0을 가지고 이동된 Fragment</br>
  
  
### Ⅴ model (패키지)</br>
#### 1. chat
 1. ChatimageModel : 이미지 이름과 파일 모델</br>
 2. ChatRoomListModel : 채팅목록 리스트의 모델</br>
 3. MessageModel : 파이어스토어 MESSAGE 문서 모델</br>
  ◇ 주요 변수  </br>
   Marketmodel (마켓 모델), Usermodel (유저 모델), Comment_MarketModel(커뮤니티 댓글 모델)</br>
  ◆ 주요 기능   </br>
  ⓐ 해당 게시물의 상세정보와 해당 게시물의 작성자의 정보가 입력 되어있다.</br>
  ⓑ 좋아요라는 게시물의 관심도가 실시간으로 표시되며 해당 게시물에 입장한 게스트들은 좋아요를 누를 수 있다.</br>
  ⓒ 해당 게시물의 작성자와 채팅하기 버튼을 통하여 채팅이 가능하다.</br>
 4. NotificationModel : 사용x</br>
 5. RoomModel : 파이어스토어 ROOMS 문서 모델</br>

#### 2. community
 1. Community_CommentModel : 커뮤니티 파이어스토어 COMMENT 문서 모델</br>
 2. CommunityModel : 파이어스토어 COMMUNITY 문서 모델</br>
 
#### 3. market
 1. Market_CommentModel : 마켓 파이어스토어 COMMENT 문서 모델</br>
 2. MarketModel : 파이어스토어 MARKET 문서 모델</br>

#### 4. photo
 1. DirectoryModel : 사진 디렉토리의 모델</br>
 2. PhotoModel : 사진 모델</br>
 
#### 5. user
 1. ReviewModel : 파이어스토어 REVIEW 모델</br>
 2. UserModel : 파이어스토어 USERS 모델</br>
  
  
### Ⅵ photo(패키지)</br>
#### 1. activity (패키지)
 1. PhotoPickerActivity : 사진을 다중 선택하는 이벤트의 최초로 도달하는 액티비티</br>
  ◆ 주요 기능   </br>
  ⓐ 주된 기능은 앨범, 카메라,스토리지에 대한 접근 및 카메라 실행 / 사진 각 장마다의 setOnClickListener (ImagePagerFragment) / 접근한 경로의 이미지들을 배열하는 것 (PhotoPickerFragment) 이렇게 3가지이다.</br>
  ⓑ PhotoPickerActivity → (PhotoPickerFragment) → (PhotoGridAdapter)</br>
     └ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ→ (ImagePagerFragment)  → (PhotoPagerAdapter)</br>
     
#### 2. adapter (패키지)
 1. PhotoGridAdapter : PhotoPickerFragment와 연결되어 제일 첫번째에는 카메라 기능을 연결할 버튼을 배치하고 그 다음부터는 이미지들을 나열한다.</br>
 2. PhotoPagerAdapter : ImagePagerFragment와 연결되어 있고, 이미지를 크게 보기 위한 데에 필요한 Adapter이다.</br>
 3. PopupDirectoryListAdapter : PhotoPickerFragment에서 나타낸 하단부에 이미지들의 디렉토리를 고를 수 있다. 다른 경로의 이미지들을 선택할 수 있게 해준다.</br>
 4. SelectableAdapter :  RecyclerView에 Utill처럼 PhotoModel package에서만 사용 할 기능을 추가하여 쓰기 위해 만든 추상 클래스</br>

#### 3. common (패키지)
 1. event(패키지)</br>
  ◆ 주요 기능   </br>
  ⓐ 1. OnItemCheckListener : 이미지들이 나열되어 있는 PhotoPickerFragment와 연결되어 있는 PhotoGridAdapter에서 사진들이 체크되었을 때 발생하는 이벤트를 interface로 선언한 것</br>
  ⓑ 2. OnPhotoClickListener : 이미지들이 나열되어 있는 PhotoPickerFragment와 연결되어 있는 PhotoGridAdapter에서 사진이 클릭 되었을 때 발생하는 이벤트를 interface로 선언한 것 </br> 
  ⓒ 3. Selectable : 이미지를 선택 할 수 있는지 아닌지를 구별하는 interface</br> 
 2. widget(패키지)</br>
  ◆ 주요 기능   </br>
  ⓐ 1. SquareItemLayout : PhotoGridAdapter를 이용하여 이미지들을 나열 할 때에 이미지의 규격을 설정한 것</br>
  ⓑ 2.TouchImageView : PhotoPagerAdapter에서 나타낸 이미지 한장을 나타내는 화면에서 여러가지 기능들을 구현 할 수 있도록 만들어 놓은 것이다. 더블탭으로 이미지 확대 축소헤서 보기, 이미지 화면의 폭에 맞추기, 이미지의 높이가 더 크면 스크롤로 볼 수 있게 하기</br> 
 3. ImageCaptureManager :  PhotoPickerFragment에 나열되어 있는 이미지, 카메라 버튼 중에 카메라 버튼을 누르면 실행되는 이벤트</br>
 4. ImagePagerFragment: PhotoPickerFragment,PhotoGridAdapter로 나열한 이미지들 중에 이미지 하나를 클릭하였을 때 확대 보기를 하기위한 Fragment, ImagePagerFragment 역시 PhotoPickerActivity 와 연결되어 있지만, 사진이 클릭하는 이벤트가 발생했을 때 Intne</br>
 5. PhotoPickerFragment : PhotoPickerActivity와 연결된 Fragment로 앨범의 이미지들을 나열하는 기능</br>
 6. PhotoUtil : 형태는 조금 다르나 다른 package에서 쓰이는 Utill과 기능</br>

  
  
## 3. KeyWord</br>
### 1) NavigationBar</br>
#### Main의 하단바 구성으로 사용하였다. </br>(Main.java : 67~83) </br>
### 2) Fragment</br>
#### Main에서 선언하고, List와 추가버튼등이 동적으로 추가되거나 하나의 독립된 채로 실행된다. </br>(Main.java : 60~63 / Fragment1.java, Fragment2.java) </br>
### 3) AlarmReceiver</br>
#### Main에서 diaryNotification 메서드로 소통하여, 시간의 비교로 팝업을 띄우는 역할을 한다.</br>(Main.java :85~108 / AlarmReceiver.java) </br>
### 4) Room</br>
#### 데이터베이스의 역할을 한다. </br> 기존의 Sql문을 쓰기 번거로웠던, 단점을 극복하고 간편하게 만들었으며, Sql을 직접 쓸 수 있는 등 강력한 기능을 지원하는 개념이다.</br>(MemoDatalist.java (테이블 구성) / MemoDataDataBase.java / MemoDao.java (Sql문) ) </br>
### 5) Service</br>
#### 여기다가 </br> 쓰면됨</br>(참고코드) </br>
### 6) Handler</br>
#### 여기다가 </br> 쓰면됨</br>(참고코드) </br>



## 5. FeedBack </br>
#### 피드백이나 기능의 개선사항에 대한 의견은 likppi100@naver.com 혹은 tnvnfdla12@gmail.com 으로 보내주시면 감사하겠습니다.</br>

