package model;

import java.util.ArrayList;

public class MemberDAO {
	private ArrayList<MemberVO> mvo; // MemberVO를 담고있는 배열.

	public MemberDAO(){ 
		mvo=new ArrayList<MemberVO>();
		mvo.add(new MemberVO("sixsense","1234","완벽")); //관리자 정보.
	}
	// C
	public boolean isInputMember(MemberVO vo) { //회원가입.
		for(int i=0; i<mvo.size(); i++) { //리스트 size만큼 돌아서 
			if(mvo.get(i).getID().equals(vo.getID())) { //id가 이미 저장된 VO에 있으면 false
				System.out.println("로그: 아이디 중복이에요.");//개발자만 볼 수 있는 로그
				return false; // false를 리턴해준다.//???
			}
		}
		MemberVO data=new MemberVO();  //일회용.
		data.setID(vo.getID()); // MemberVO의 아이디를 설정해준다.
		data.setName(vo.getName()); //MemberVO의 이름을 설정해준다.
		data.setPw(vo.getPw()); //MemberVO의 비밀번호를 설정해준다.
		mvo.add(vo);
		System.out.println("로그: 회원가입 성공!"); // 개발자만 볼 수 있는 로그.
		return true; //같은 아이디가 존재하지 않다면 true 반환 
		//ㄴ???
	}
	//R
	public MemberVO hasMember(MemberVO vo) {// 로그인 로직
		for(int i=0;i<mvo.size();i++) { //mvo사이즈만큼 돌아서
			if(mvo.get(i).getID().equals(vo.getID())) { //아이디가 있다면
				if(mvo.get(i).getPw().equals(vo.getPw())) { //비밀번호가 있다면
					System.out.println("로그 : 로그인 성공"); //개발자만 볼 수 있는 로그
					return mvo.get(i); //그 값을 반환한다.
				}
				System.out.println("로그 : 비밀번호가 일치하지 않습니다.");//개발자만 볼 수 있는 로그
			}
			System.out.println("로그 : 아이디가 일치하지 않습니다.");//개발자만 볼 수 있는 로그
		}
		return null; //null반환
	}
	//U
	public boolean isModifyMember(MemberVO vo) { //정보변경.
		for(int i=0; i<mvo.size(); i++) { //리스트 size만큼 돌아서.
			if(mvo.get(i).getID().equals(vo.getID())) { //id가 있으면.
				if(vo.getPw()!=null) { //비밀번호가 null이 아니라면.
					mvo.get(i).setPw(vo.getPw()); //비밀번호를 변경한다.
					System.out.println("로그: 비밀번호 변경 성공."); //개발자만 볼 수 있는 로그.
					return true;  //true를 반환한다.
				}else if(vo.getName()!=null) { // 이름이 null이 아니라면.
					mvo.get(i).setName(vo.getName()); //이름을 변경한다.
					System.out.println("로그: 이름 변경 성공."); //개발자만 볼 수 있는 로그.
					return true; //true를 반환한다.
				}
			}
		}
		System.out.println("로그: 아이디가 없습니다."); //개발자만 볼 수 있는 로그.
		return false; //id가 없다면 false를 반환한다.
	}
	//D
	public boolean isDeleteMember(MemberVO vo) { // 회원 탈퇴.
		for(int i=0; i<mvo.size(); i++) { //리스트size만큼 돌아서.
			if(mvo.get(i).getPw().equals(vo.getPw())) { //비밀번호가 맞다면 if문 실행
				mvo.remove(i);  //삭제.
				return true; //true 반환
			}
		}
		return false; // 같은 아이디가 존재하지 않다면 false 반환
	}
}