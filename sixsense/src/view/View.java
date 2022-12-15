package view;

import java.util.ArrayList;
import java.util.Scanner;

import model.MemberVO;

public class View {

	//----------------------------------------------------------------
	//메인메뉴 출력 로직

	public Scanner sc ;
	public View(){
		sc = new Scanner(System.in);
	}

	public int printStart(){
		int act;
		while(true){
			try{
				System.out.println("메인 출력");
				System.out.println("1. 로그인");
				System.out.println("2. 회원가입");
				System.out.println("3. 종료");
				act = sc.nextInt();
			}catch(Exception e){
				sc.nextLine();
				System.out.println("정수 입력");
				continue;
			}
			if(1<=act && act <=3){
				break;
			}
			System.out.println("범위 확인");
		}
		return act;
	}


	//-----------------------------------------------------------
	// 프로그램 종료문구 로직
	public void powerOff() {
		for(int i=0;i<3;i++) {
			System.out.println(" . ");
		}
		System.out.println("프로그램 종료");
	}

	//-----------------------------------------------------------------
	//로그아웃 종료문구 로직
	public void logOut() {
		System.out.println("로그아웃 ");
	}

	//-------------------------------------------------------
	//로그인 id 입력 로직
	public String getId() {
		System.out.print("아이디 입력 >> ");
		String id=sc.next();
		return id;
	}

	//-------------------------------------------------------------------------
	//로그인  pw 입력 로직
	public String getPw() {
		System.out.print("비밀번호 입력 >> ");
		String pw=sc.next();
		// 비밀번호 재입력 (유효성 검사) 추가하기
		return pw;
	}

	//---------------------------------------------------------
	//로그인 name 입력 로직
	public String getName() {
		System.out.print("이름입력 : ");
		String pw=sc.next();
		return pw;
	}

	//////////////////////////////////////
	// 회원가입 로그인 성공
	public void joinTure() {
		System.out.println("회원가입 성공되었습니다.");
		System.out.println("로그인 해주세요~");
	}

	//////////////////////////////////////////////////////
	// 회원가입 중복시 로직
	public void idFalse() {
		System.out.println("중복된 아이디 입니다.");
	}

	//---------------------------------------------------------------------------------------------
	//로그인 실패시 로직
	public void loginFalse() {
		System.out.println("로그인 실패...");
		System.out.println("아이디 or 비밀번호를 다시 확인해보세요!");
	}

	//---------------------------------------------------------------------------------------------
	//로그인 성공 로직
	public void loginTure(MemberVO vo) {
		System.out.println(get.Name+"님 로그인에 성공하셨습니다.");
	}

	//------------------------------------------------------------------------------------
	//로그인후 회원 메뉴 출력로직  
	public int printMenu() {  //로그인후 회원 메뉴 출력
		int act;
		while(true) {
			try {
				System.out.println();
				System.out.println("====  회원 메뉴 ====");
				System.out.println("1. 일정추가");
				System.out.println("2. 일정출력");
				System.out.println("3. 일정변경");
				System.out.println("4. 일정삭제");
				System.out.println("5. 회원탈퇴");
				System.out.println("6. 로그아웃");
				System.out.print("   :::   ");
				act = sc.nextInt();
			} catch(Exception e) {
				sc.nextLine(); //에러값을 먹어준다
				System.out.println("정수를 다시 입력해주세요");
				continue;
			}
			if(1<=act && act <=6) {
				break;
			}
			System.out.println("범위를 확인하고 다시 입력해주세요");
		}
		return act;
	}

	///////////////////////////////////////////////////////////////////////////////////
	// 일정 구 입력 로직
	public String gu(ArrayList<gu>datas) {
		String gu;
		boolean flag=false;
		System.out.println("구를 입력해주세요");
		System.out.print("입력 : ");
		//유효성검사
		while(true) {
			gu=sc.next();
			for(int i=0;i<=datas;i++) {
				if(datas[i].getGu().equals(gu)) {
					flag=true;
					break;
				}	
			}
			if(flag=true) {
				break;
			}
		}
		return gu;
	}

	///////////////////////////////////////////////////////////////////////////////////
	// 일정 구 입력 로직
	public String dong(ArrayList<dong>datas) {
		String dong;
		boolean flag=false;
		System.out.println("동을 입력해주세요");
		System.out.print("입력 : ");
		//유효성검사
		while(true) {
			dong=sc.next();
			for(int i=0;i<=datas.lenth();i++) {
				if(datas[i].getDong().equals(dong)) {
					flag=true;
					break;
				}	
			}
			if(flag=true) {
				break;
			}
		}
		return dong;
	}

	///////////////////////////////////////////////////////////
	/// 일정 월 입력 로직
	public int mon() {
		int mon;
		System.out.println("날짜을 입력해주세요");
		System.out.print("입력 : ");
		while(true) {
			try {
				mon=sc.nextInt();
			}catch (Exception e) {
				System.out.println("정확한 일자를 입력해주세요.");
				continue;
			}
			if(mon>1 || mon>12 ) {
				System.out.println("1~12 사이를 입력해주세요.");
				continue;				
			}
			break;
		}
		return mon;
	}

	///////////////////////////////////////////////////////////////////////////////////
	// 일정 일 입력 로직
	public int day(int mon) {
		int day;
		System.out.println("월을 입력해주세요");
		System.out.print("입력 : ");
		while(true) {
			try {
				day=sc.nextInt();
			}catch (Exception e) {
				System.out.println("정확한 일자를 입력해주세요.");
				continue;
			}
			if(mon==1 || mon==3|| mon==5 || mon==7 || mon==8 || mon==10 || mon==12  ) {
				if(day<1 || day>31) {
					System.out.println("정확한 일자를 입력해주세요.");
					continue;
				}
				else {
					break;
				}
			}
			else if(mon==4 || mon==6 || mon==9 || mon==11) {
				if(day<1 || day>30) {
					System.out.println("정확한 일자를 입력해주세요.");
					continue;
				}
				else {
					break;
				}
			}
			else if(mon==2) {
				if(day<1 || day>29) {
					System.out.println("정확한 일자를 입력해주세요.");
					continue;
				}
				else {
					break;
				}
			}
		}
		return day;
	}

	////////////////////////////////////////////////////
	/// 일정 입력 로직
	public String diary() {
		System.out.println("일정을 입력해 주세요");
		System.out.print("입력");
		String dia= sc.next();
		return dia;
	}

	///////////////////////////////////////////////////////////////////////////////
	/// 일정 출력 로직
	public int changeNum() {
		int num;
		System.out.println("출력하실 일정을 입력해주세요");
		System.out.print("입력 : ");
		while(true) {
			try {
				num = sc.nextInt();
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
			break;
		}
		return num;
	}

	/////////////////////////////////////////////////////////////////////////////////
	//// 일정 전체 출력 로직
	public void selectAll(ArrayList<MemberVO/**/>datas) {
		if(datas==null) {
			/*전체 출력*/
			for(int i=0;i<=datas.length();i++) {
				//i. 월/일 어디어디어디어디 일정 날씨
				System.out.println((i+1)+". "+datas[i].getDate+datas[i].getKu+datas[i].getDong);
			}

		}
		else {
			/*선택 출력*/
			for(int i=0;i<=datas.length();i++) {
				//i. 월/일 어디어디어디어디 일정 날씨
				System.out.println((i+1)+". "+datas[i].getDate+datas[i].getKu+datas[i].getDong);
			}
		}
	}

	///////////////////////////////////////////////////////////////////////////////
	//// 변경할 일정 변경 로직
	public <ScheduleVO/**/> changeDiary(ArrayList<MemberVO/**/>datas) {

		System.out.println("변경하실 일정번호를 입력해주세요");
		System.out.print("입력 : ");
		int num;
		while(true) {
			int len;			
			len=datas.lenth();
			try {
				num = sc.nextInt();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("번호를 다시한번 확인해주세요.");
				continue;
			}
			if(num<1 || num>len) {
				System.out.println("번호를 다시한번 확인해주세요.");
				continue;
			}
			break;
		}

		//실질적인 데이터의 pk값 대입
		int pk=datas[num-1].getNum();

		System.out.println("변경하실 일정을 입력해 주세요");
		String dia=sc.next();

		//MemberVO vo=new MemberVO();
		//객체에 pk번호 세트
		//vo.setNum(pk);
		//객체에 새로운 일정세트
		//vo.setDiary(dia);
		return vo;
	}

	////////////////////////////////////////////////////////////
	/// 일정 삭제 로직
	public int deletNum(ArrayList<MemberVO/**/>datas) {
		System.out.println("삭제하실 일정번호를 입력해주세요");
		System.out.print("입력 : ");
		int num;
		int len=datas.lenth();
		while(true) {
			try {
				num = sc.nextInt();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("번호를 다시한번 확인해주세요.");				
				continue;
			}
			if(num<1 || num>len) {
				System.out.println("번호를 다시한번 확인해주세요.");
				continue;
			}			
			break;
		}
		//실질적인 데이터의 pk값 대입
		int pk=datas[num-1].getNum();

		return pk;

	}
	public boolean deletDiary() {
		System.out.println("정말삭제 하시겠습니까?[Y/N]");
		String ans=sc.next();
		if(ans.equals("Y")) {
			return true;
		}
		return false;
	}

	//-----------------------------------------------------------------------------------------------------------
	// 회원 탈퇴 로직
	public boolean getCheck(MemberVO vo) {
		System.out.println(vo.getName()+"님, 정말 탈퇴하시겠습니까? [Y/N]");
		String ans=sc.next();
		if(ans.equals("Y")) {
			return true;
		}
		return false;
	}
	
	//--------------------------------------------------------------------------------------------
	// 성공
	public void printTrue() {
		System.out.println("성공");
	}

	//----------------------------------------------------------------------------------------
	// 실패
	public void printFalse() {
		System.out.println("실패하였습니다.");
	}
	//---------------------------------------------------------------------------------
	// 관리자 메뉴
	public int adminMenu() {  //로그인후 회원 메뉴 출력
		int act;
		while(true) {
			try {
				System.out.println();
				System.out.println("==== 관리자 메뉴 ====");
				System.out.println("1. 일정출력");
				System.out.println("2. 일정삭제");
				System.out.println("3. 로그아웃");
				System.out.print("   :::   ");
				act = sc.nextInt();
			} catch(Exception e) {
				sc.nextLine(); //에러값을 먹어준다
				System.out.println("정수를 다시 입력해주세요");
				continue;
			}
			if(1<=act && act <=3) {
				break;
			}
			System.out.println("범위를 확인하고 다시 입력해주세요");
		}
		return act;
	}
	
	
	
}