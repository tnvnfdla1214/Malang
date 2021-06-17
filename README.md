# Malang
## 1. App</br>
#### 연인들의 연인 맺기와 일정을 공유할 수 있는 어플리케이션</br>
#### Firebase - Malang</br>

## 2. Inteface
1) 로그인 화면</br>
<img src="https://user-images.githubusercontent.com/54509842/122374715-944fb980-cf9d-11eb-96e8-e3a7122924fe.jpg" width="20%"></img>
<img src="https://user-images.githubusercontent.com/54509842/122375504-3d96af80-cf9e-11eb-864d-f60a2501f2eb.jpg" width="20%"></img>
#### 1. 구글 로그인/ 카카오 로그인 기능 </br>
#### 2. 자동로그인 기능 </br>

2) 회원정보 화면</br>
<img src="https://user-images.githubusercontent.com/54509842/122375655-63bc4f80-cf9e-11eb-9c70-0fdc0d0671ca.jpg" width="20%"></img>
<img src="https://user-images.githubusercontent.com/54509842/122374715-944fb980-cf9d-11eb-96e8-e3a7122924fe.jpg" width="20%"></img>
<img src="https://user-images.githubusercontent.com/54509842/122374715-944fb980-cf9d-11eb-96e8-e3a7122924fe.jpg" width="20%"></img>
<img src="https://user-images.githubusercontent.com/54509842/122375285-0aecb700-cf9e-11eb-9c7e-212ae29ec158.jpg" width="20%"></img>
#### 1. 회원정보의 입력은 사진과 닉네임과 생년월일과 학교를 받는 화면이다 </br>
#### 2. 닉네임을 입력 받지 않을시 이메일의 앞부분으로 생성한다. </br>
#### 3. 사진을 선택 시 선택한 사진이 미리보기로 보여진다. </br>

3) 커플 매칭 화면</br>
<img src="https://user-images.githubusercontent.com/48902047/122349509-4a5bd900-cf87-11eb-9c38-48a2e6dc713b.jpg" width="20%"></img>
#### 유저들의 마켓에 올린 게시물이 실시간으로 보여진다. </br>

4) 일정 화면</br>
<img src="https://user-images.githubusercontent.com/48902047/122349509-4a5bd900-cf87-11eb-9c38-48a2e6dc713b.jpg" width="20%"></img>
<img src="https://user-images.githubusercontent.com/48902047/122349752-81ca8580-cf87-11eb-9d93-d7aaf99b7aa8.jpg" width="20%"></img>
<img src="https://user-images.githubusercontent.com/48902047/122349982-c1916d00-cf87-11eb-8af3-acab22e02f75.jpg" width="20%"></img>
#### 1. 상단 오른쪽 돋보기 버튼을 누르면 검색창으로 검색창으로 이동된다. </br>
#### 2. 단어를 검색시 게시물의 제목의 글자를 찾아 검색결과의 리스트를 보여준다. </br>
#### 3. 하단 카테고리버튼을 클릭시 해당 카테고리리스트를 보여준다. </br>

5) 일정 등록 화면</br>
<img src="https://user-images.githubusercontent.com/48902047/122350743-72980780-cf88-11eb-84f8-c1ab0e283d8e.jpg" width="20%"></img>
<img src="https://user-images.githubusercontent.com/48902047/122350881-93f8f380-cf88-11eb-8639-46ec51158392.jpg" width="20%"></img>
#### 1. 유저가 올린 게시물을 클릭시 해당 게시물의 상세 정보를 보여준다. </br>
#### 2. 게시물 창에는 게시물을 올린 유저의 사진을 5장까지 스와이핑까지 가능하다. </br>
#### 3. 좋아요, 신고하기 기능도 있다. </br>
#### 4. 게시물의 작성자와 채팅을 원할시 하단부에 있는 채팅하기를 누르면 채팅창으로 이동된다. </br>

6) 일정 확인 화면</br>
<img src="https://user-images.githubusercontent.com/48902047/122353036-a1af7880-cf8a-11eb-9957-f0c3d4e7b89d.jpg" width="20%"></img>
<img src="https://user-images.githubusercontent.com/48902047/122351223-e803d800-cf88-11eb-8205-ced76367b2d4.jpg" width="20%"></img>
#### 1. 게시자의 프로필사진을 클릭시 게시자의 회원정보 창이 나온다. </br>
#### 2. 게시자 회원정보 창에는 게시자의 정수와 리뷰가 나온다. </br>

7) 일정 수정 & 삭제 화면</br>
<img src="https://user-images.githubusercontent.com/48902047/122351748-6b252e00-cf89-11eb-8198-cc2e545ff515.jpg" width="20%"></img>
#### 1. 회원들이 올린 커뮤니티 리스트가 올라온다. </br>



## 3. Explanation</br>

### Ⅰ Calendar(패키지)</br>

#### 1. view (패키지)
 1. CalendarViewWithNotesActivitySDK21 : 커스텀한 달력의 구현 Activity가 있는 패키지이다. </br>
  ◇ 주요 변수  </br>
  mShortMonths (현재 월), mCalendarView (커스텀한 달력), mCalendarDialog(날짜 클릭 시 생성되는 다이얼로그), mEventList(불러들인 월의 일정 리스트), user(현재 사용자 모델), CurrentUid(현재 사용자의 Uid), CurrentUser(현재 사용자 정보), Check_Month_Visited_List(불러들인 달의 리스트)</br>
  ◆ 주요 기능   </br>
  ⓐ CalendarView에서 구현한 커스텀 달력을 띄워준다. 날짜를 클릭하면 해당 날짜 다이얼로그를 생성한다.</br>
  ⓑ 일정이 표시되는 상황 </br>
   -처음 켜졌을 때 : CollectionReference로 파이어베이스에서 불러온다.</br>
   -어플 사용중에 일정이 추가 되었을 때 : OnActivityResult(내가 추가하였을 때) / Listener(상대방이 추가했을 때)를 이용하여 불러온다.</br>
   -다른 월로 이동하였을 때 : 이동한 월에 대한 일정을 Listener를 이용하여 불러온다.</br>
  ⓒ 일정 추가 버튼으로 일정을 추가할 수 있다. </br>
     
 2. CalendarView : 어플리케이션에 필요한 커스텀한 달력을 구현한 view이다. </br>
 ◇ 특징  </br>
 여러가지 작은 view들이 모여 하나의 커다란 calewndarview라는 커스텀 뷰를 구성하고 있다.</br>
  ◆ 주요 기능   </br>
  ⓐ 여러가지 리스너가 있다. (달이 이동함을 감지하는 리스너, 날짜가 클릭됨을 감지하는 리스너 등) </br>
  ⓑ 이전 달 다음달에 표시되어야 할 월을 구해준다. </br>
  ⓒ 총 7X6의 42개의 날짜 칸을 해당 달은 어디서부터 어디까지인지 계산하고, 날짜를 표기해준다.</br>
  ⓓ 해당 월의 일정의 정보와 누가 작성한 것인지를 구분하여 일정바 색을 달리해준다.</br>
  ⓔ 날짜 다이얼로그에서 드래그해 온 일정이 어느 날자에 추가 될것인지 드래그 된 위치를 감지하여 알려준다.</br>
  ⓕ 달력을 구성하는 선의 색상 및 속성을 선언한다.</br>
  
  3. CalendarDialog : 달력에서 날짜를 클릭하여 생성된 해당 날짜의 다이얼로그이다. </br>
  ◆ 주요 기능   </br>
  ⓐ 뷰페이저를 이용해 이전과 다음 날짜의 다이얼로그를 오고 갈 수 있다.</br>
  ⓑ 일정을 오른족에서 왼쪽으로 스와이핑하면 삭제할 수 있다. </br>
  ⓒ 일정을 롱클릭하여 드래그할 수 있으며, 드래그하면 다이얼로그가 닫히고, 달력에 원하는 날짜에 드롭할 수 있다. 그러면 해당 날짜를 시작으로 일정이 이동된다. </br>

#### 2. activity (패키지)
 1. Create_Schadule : CalendarViewWithNotesActivitySDK21에서 일정을 추가하는 버튼을 클릭하였을 때 혹은 일정을 수정할 때에 실행되는 activity이다.</br>
  ◇ 주요 변수  </br>
  Start_Calendar(시작 날짜), End_Calendar(끝 날짜)</br>
  ◆ 주요 기능   </br>
  ⓐ 일정을 추가할 때면 일정이 비어있고, 날짜는 오늘 날짜로 설정되어 있다.</br>
  ⓑ 일정을 수정할 때면 선택한 일정의 정보가 입력되어 있다.</br>
  ⓒ 날짜를 클릭하면 소형 달력이 나와 날짜를 선택할 수 있다.</br>
  ⓓ 생성된 일정은 파이어스토어의 CALENDAR 컬렉션에 저장된다.</br>
 
 2. DragCreate_Schedule : CalendarDialog에서 일정을 롱클릭하여 드래그로 날짜에 드롭했을 때 실행되는 액티비티이다. </br>
 ◇ 주요 변수  </br>
  Start_Calendar(시작 날짜), End_Calendar(끝 날짜)</br>
  ◆ 주요 기능   </br>
  ⓐ Create_Schadule에서 일정을 수정할 때처럼 가져온 일정의 정보가 설정되어 있다.</br>
  ⓑ 원한다면 날짜나 일정의 내용을 수정하여 추가할 수 있다. </br>

3. SelectDateAndTimeActivity : 일정을 추가할 때에 날짜와 시간을 선택할 수 있는 Picker가 있는 activity이다. </br>
  ◆ 주요 기능   </br>
  ⓐ 상단에는 날짜를 하단에는 시간을 선택할 수 있는 Picker가 있다.</br>
    
#### 3. helpers (패키지)
 1. FrameLinearLayout : 달력에서 날짜를 구성하는 하나하나의 직사각형의 모양 틀 view </br>

 2. ItemTouchHelperCallback : 날짜 다이얼로그에서 일정을 오른쪽에서 왼쪽으로 스와이핑 했을 때</br>
 
 3. ItemTouchHelperListener : ItemTouchHelperCallback에서 이루어지는 스와이핑과 버튼의 클릭을 감지하는 리스너이다. </br>
 
 4. SelectedTextView : 날짜가 클릭되었을 때 테두리가 점선이 그어지는 효과를 줄 수 있는 view이다.</br>
 
 5. YMDCalendar : 캘린더의 값을 Year Month Day 3가지의 int형 데이터로 다룰 때에 사용하는 모델이다. </br>

 #### 4. uihelpers (패키지)
 1. NumberPicker : 날짜를 선택하고 시간을 선택할 때에 시간 분의 선택을 위한 숫자 선택을 위한 view </br>


### Ⅱ Login (패키지)</br>
#### 1. couple (패키지)
 1. couple_askActivity : 사용자 정보를 입력하고 난 후 커플을 맺기 위한 activity이다. </br>
  ◇ 특징  </br>
 코드를 보내주는 사람 / 코드를 받아서 입력하는 사람으로 나뉜다.</br>
  ◆ 주요 기능   </br>
  ⓐ 코드를 보내주는 사람 / 코드를 받아서 입력하는 사람으로 나뉜다.</br>
  ⓑ 코드를 보내주는 사람 > '커플코드가 뭔지 몰라요' 클릭</br>
  ⓒ 코드를 받아서 입력하는 사람 > '커플코드가 있어요' 클릭</br>
  
 2. couple_startdateActivity : 커플코드를 보내주는 사람이 연인의 연애 시작일을 입력하는 activity이다. </br>
  ◆ 주요 기능   </br>
  ⓐ 날짜를 입력하고 다음 버튼을 누르는 순간 파이어 스토어에 COUPLE 컬렉션에 반쪽짜리 커플이 생성된다.</br>
  ⓑ 남자와 여자일 때 각각 상대방의 생년월일은 0년 0월 0일로 초기화 시켜 커플 데이터를 생성한다.</br>
  
 3. couple_codecreateActivity  : couple_startdateActivity에서 날짜를 입력하고 넘어오면 커플 코드를 발급 받을 수 있다. </br>
  ◆ 주요 기능   </br>
  ⓐ 코드복사를 하여 클립보드에 커플 코드를 복사하여 저장 할 수 있다.</br>
  ⓑ 코드복사를 하면 상대방이 코드를 입력할 때까지 대기한다. (토스트 메시지로 복사가 완료되었음을 알수 있다.)</br>
  
 4. couple_editcodeActivity : 커플코드를 받아서 입력하는 사람이 받은 코드를 입력하는 activity이다. </br>
  ◆ 주요 기능   </br>
  ⓐ 복사한 코드를 입력하여 다음을 누르면 couple_startdateActivity에서 생성된 반쪽짜리 커플에 코드를 받아 입력한 사람의 데이터가 입력되며 커플이 맺어진다.</br>
  
 5. couple_finishActivity : 앱을 이용하기 이전에 커플이 맺어졌음을 알려주는 activity이다.</br>
  ◆ 주요 기능   </br>
  ⓐ 커플 코드를 보낸 사람은 상대방이 입력을 완료하면 이동하게 된다.</br>
  ⓑ 커플 코드를 받아 입력한 사람은 입력을 완료하면 이동하게 된다.</br>
  
  
#### 2. activity (패키지)
 1. LoginActivity : 어플의 시작화면이며, 로그인하는 activity이다. </br>
  ◆ 주요 기능   </br>
  ⓐ 카카오 로그인과 구글 로그인이 있으며, 이미 로그인 정보가 있다면, 바로 메인 달력 화면으로 넘어가게 된다.</br>
 2. MemberInitActivity : 로그인 후 사용자의 정보를 받는 activity이다.</br>
  ◆ 주요 기능   </br>
  ⓐ 이름, 성별, 생일, 프로필 사진, 닉네임 등을 입력 받는다.</br>
  ⓑ 사용자의 정보는 파이어스토어의 USER 컬렉션에 저장된다.</br>
  ⓒ 프로필 사진의 url은 파이어스토어에 이미지 자체는 파이어스토어 Storage에 저장된다.</br>
 3. GalleryActivity : 프로필 사진의 선택을 위해 앨범으로 접근하는 activity이다.</br>
  ◆ 주요 기능   </br>
  ⓐ 앨범 접근에 대한 권한을 요청 받은 후 앨범에 접근할 수 있다.</br>
  ⓑ GalleryAdapter로 앨범의 사진을 나열 받는다.</br>
  
  
#### 3. helper (패키지)
 1. GalleryAdapter : 앨범 내의 사진들의 나열하는 adapter이다.</br>
  ◆ 주요 기능   </br>
  ⓐ item_gallery로 하나하나의 이미지들을 리사이클러 뷰로 나열한다.</br>
  
  2. GlobalApplication : 카카오 로그인을 위한 java 파일이다.</br>
  ◆ 주요 기능   </br>
  ⓐ 여러가지 에러를 잡는 조건문이 있다.</br>
   
   
### Ⅲ Data (패키지)</br>
#### 1. 모델
 1. USER : 사용자의 정보를 다루는 USER 모델 </br>
  ◆ 주요 기능   </br>
  ⓐ 사용자의 이름, 성별, 닉네임, 생일 년월일, 커플Uid, 유저의 Uid, 레벨, 프로필 이미지 url</br>
 
 2. COUPLE : 커플의 정보를 다루며, 이어주는 COUPLE 모델 </br>
  ◆ 주요 기능   </br>
  ⓐ 커플의 Uid, 연애 시작 , 닉네임, 생일 년월일, 커플Uid, 레벨, 프로필 이미지 url</br>
 
 3. CALENDAR  : 로그인 액티비티</br>
  ◆ 주요 기능   </br>
  ⓐ 일정 Uid, Event Uid, 일정 내용, 일정 시작 날짜, 일정 종료 날짜, 일정 fix 날짜, 일정 일수</br>
  ⓑ 파이어베이스에 저장되는 CALENDAR 컬렉션을 다루는 모델이다.</br>

 4. Event : 메인 액티비티</br>
  ◆ 주요 기능   </br>
  ⓐ 일정 Uid, Event Uid, 일정 내용, 일정 시작 날짜, 일정 종료 날짜, 일정 fix 날짜, 일정 일수</br>
  ⓑ Calendarview에서 일정을 다루기 위해서 CALLENDAR 모델을 담은 모델이다.</br>
  

### Ⅳ Util (패키지)</br>
1. BasicActivity  </br>
 ◆ 주요 기능   </br>
  ⓐ toolbar를 어플리케이션의 코드 전반에서 모두 똑같은 코드를 사용하므로 Util 패키지로 빼내어서 사용한다. </br>
  
2. Util </br>
  ◆ 주요 기능   </br>
  ⓐ BasicActivity와 비슷한 기능을 하며, 앨범의 경로를 찾아갈 때에 공통적으로 사용한다. </br>
  ⓐ 파이어스토어에 Storage에 이미지를 저장할 때에 Storage의 주소를 빼내어 사용한다. </br>


## 4. KeyWord</br>
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

