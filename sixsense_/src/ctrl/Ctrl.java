package ctrl;

import model.Crawling;
import model.MemberDAO;
import model.MemberVO;
import model.ScheduleDAO;
import model.ScheduleVO;
import view.View;

// 컨트롤은 모델과 뷰를 멤버변수로 가짐
public class Ctrl { 
   View view;
   MemberDAO mdao;
   ScheduleDAO sdao;
   Crawling crawling;

   public Ctrl() { // 생성자
      // DAO를 다 만들고나서 크롤링만드는것 맞는것
      // 이 두개는 개별적이고 컨트롤에서 조합해야함

      view=new View(); // 객체화하기
      mdao=new MemberDAO(); // 객체화하기
      sdao=new ScheduleDAO(); // 객체화하기
      crawling =new Crawling(); // 크롤링 객체 가지고 있어야함

   }

   // 앱 시작
   public void startApp() {
      // 뷰에서 메인메뉴 출력가져오기
      // 받은 액션에따라

      while(true){ // 메인메뉴 반복
         int act=view.printStart();

         if(act==1){ // 로그인
            MemberVO mvo = new MemberVO(); // 로그인정보 확인할 일회성 mvo 객체화
            mvo.setID(view.getId()); // 입력받은 id 세팅
            mvo.setPw(view.getPw()); // 입력받은 pw 세팅
            mvo = mdao.hasMember(mvo); // 해당하는 기존 mvo 반환
            if(mvo==null) {
               view.loginFalse(); // 로그인 실패
               continue;
            }
            view.loginTure(mvo);// 로그인성공 출력문구에 해당이름을 넣기위한 인자 mvo
            // 현재 로그인한 사람 == mvo

            // 관리자모드
            if(mvo.getID().equals("sixsense")) {// id가 "sixsense"라면 관리자모드
               while(true){
                  act=view.adminMenu(); // 관리자 메뉴출력 선택값 act반환

                  if(act==1){// 1. 출력
                     // sdao에서 반환된 목록 리스트를 뷰에게 출력 요청
                     view.adminselectAll(sdao.loadSchedules(null));
                  }
                  else if(act==2){ // 2. 일정삭제
                     ScheduleVO svo=new ScheduleVO();
                     // 일정전체목록을 view로 넘겨서 전체출력 후 true반환
                     if(view.adminselectAll(sdao.loadSchedules(null))) { 
                        // 일정 전체 목록을 뷰에게 넘겨주고 삭제할일정 입력받고 svo에 세팅
                        // sdao의 삭제메서드로 svo넘겨줌
                        svo.setpNum(view.deleteNum(sdao.loadSchedules(null)));   
                        if(sdao.isDeleteSchedule(svo)) { // 삭제 성공한다면
                           view.scheduleDeletTrue(); // 성공멘트출력
                        }
                        else {
                           view.scheduleDeletFalse(); // 실패멘트출력
                        }
                     }
                  }
                  else if(act==3) { // 3. 로그아웃
                     // 뷰에게 로그아웃 멘트 호출
                     view.logOut();
                     break; // 관리자모드 반복문 종료
                  }
               }
            }
            else { // 회원로그인
               while(true){ 
                  // 로그인성공시
                  act=view.printMenu();
                  if(act==1){ // 1. 일정추가
                     ScheduleVO svo=new ScheduleVO(); // 모델에 인자로 넘길 svo 객체화
                     svo.setYear(view.year()); // 년도 입력받아서 세팅
                     svo.setMonth(view.mon(svo.getYear())); // 월 입력받아서 세팅
                     svo.setDay(view.day(svo.getYear(),svo.getMonth())); // 일 입력받아서 세팅

                     svo.setID(mvo.getID()); // 회원ID를 일정vo에 FK로 세팅
                     svo.setMemo(view.diary()); // 일정추가

                     // 크롤링에서 구리스트 반환 - 뷰에서 출력, 선택, 세팅
                     // 세팅된 구를 크롤링 동에 넘겨주고 - 동 리스트 반환 - 뷰에서 출력, 선택 - 선택된 동 세팅
                     svo.setGu(view.gu(crawling.getGu())); // 구 저장 
                     svo.setDong(view.dong(crawling.getDong(svo.getGu())));
                     // 스케줄리스트를 가지고있는 객체과 일정세팅된 객체 크롤링으로 보냄 -크롤링에서 dao로 전달
                     crawling.setWeather(sdao ,svo); // 세팅된 객체를 크롤링으로 보냄
                     view.scheduleTrue(); // 일정등록 성공 
                  }

                  else if(act==2){ //2.전체 일정 출력
                     // 로그인한 mvo넘겨주고 해당 회원의 일정리스트를 뷰에게 전달후 출력 
                     view.selectAll(sdao.loadSchedules(mvo));
                  }
                  else if(act==3){ //3.일정 변경
                     // 로그인한 mvo넘겨주고 해당 회원의 일정리스트를 뷰에게 전달후 출력 
                     if(view.selectAll(sdao.loadSchedules(mvo))) {
                        // sdao에서 회원의 일정리스트를 뷰에게 전달 - 변경할 일정을 sdao에 전달 - 변경 
                        if(sdao.isModifySchedule(view.changeDiary(sdao.loadSchedules(mvo)))) { // 변경성공한다면
                           view.scheduleChangeTrue(); // 성공멘트출력
                        }
                        else {
                           view.scheduleChangeFrue();; // 실패멘트출력
                        }
                     }
                  }

                  else if(act==4){ //4.일정 삭제

                     ScheduleVO svo=new ScheduleVO(); // 모델에 인자로 넘길 svo 객체화
                     // 로그인한 mvo넘겨주고 해당 회원의 일정리스트를 뷰에게 전달후 출력 
                     if(view.selectAll(sdao.loadSchedules(mvo))) {
                        // sdao에서 회원의 일정리스트를 뷰에게 전달 - 삭제할 일정pk 세팅
                        svo.setpNum(view.deleteNum(sdao.loadSchedules(mvo)));
                        //                  System.out.println(view.deleteNum(sdao.loadSchedules(mvo)));
                        if(sdao.isDeleteSchedule(svo)) { // 해당 일정 삭제 성공한다면
                           view.scheduleDeletTrue(); // 성공멘트출력
                        }
                        else {
                           view.scheduleDeletFalse(); // 실패멘트출력
                        }
                     }
                  }
                  else if(act==5) { // 5. 회원변경
                     if(mdao.isModifyMember(view.updateMember(mvo))) {
                        view.memberChangeTrue();
                        break;
                     }
                     view.memberChangeFalse();

                  }
                  else if(act==6) { // 5. 회원탈퇴
                     if(view.getCheck(mvo)) {
                        if(mdao.isDeleteMember(mvo)) {
                           sdao.memberDeleteScheduleAll(mvo);
                           view.memberDeletTrue();
                           break;
                        }
                     } 
                     view.memberDeletFalse();
                  }
                  else{
                     break; // 로그인반복문 탈출하고 메인메뉴로
                  }
               }
            }
         }

         else if(act==2){ // 회원가입
            MemberVO mvo=new MemberVO(); // 회원가입할 일회성 mvo 객체화
            mvo.setID(view.getId()); // 입력받은 id 세팅
            mvo.setPw(view.getPw()); // 입력받은 pw 세팅
            mvo.setName(view.getName()); // 입력받은 이름 세팅
            if(mdao.isInputMember(mvo)) { // 세팅된 mvo를 넘겨주고 회원가입 성공한다면
               view.joinTure(); // 성공멘트출력
            }
            else {
               view.idFalse(); // 실패한다면 아이디 중복 멘트 출력
            }
         }
         else{ // 프로그램 종료
            view.powerOff(); 
            return;
         }

      }
   }
}