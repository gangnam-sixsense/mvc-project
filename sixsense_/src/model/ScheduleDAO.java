package model;

import java.util.ArrayList;

public class ScheduleDAO {
	private ArrayList<ScheduleVO> svo;
	private int PK;

	public ScheduleDAO() {
		PK=101;
		svo=new ArrayList<ScheduleVO>(); //생성자에서 초기화하는 것이아니라로그인을 하면 그떄 초기화 하면 되는 거 아님?

	}

	public void memberDeleteScheduleAll(MemberVO vo) { // 
		for(int i = 0; i < svo.size(); i++) {
			if(vo.getID().equals(svo.get(i).getID())) { // 동일 아이디 체크
				svo.remove(i--); // 연속으로 같은 아이디일때 일정삭제를 위한 i--
			}
		}
	}
	
	//C
	public boolean isInputSchedule(ScheduleVO vo) { //일정 추가. 날짜 메모입력 받고 PK는 여기서 설정. 날씨는 안 받음.
		try {
			ScheduleVO data = new ScheduleVO(); 
			data = vo;
			data.setpNum(PK++); //PK
			svo.add(data);
			return true;
		}catch (Exception e) {

			return false;
		}
	}
	//R
	public ArrayList<ScheduleVO> loadSchedules(MemberVO vo){ //회원의 일정 전체 목록 출력.
		if(vo == null) { //관리자모드일때 사용.
			return svo; //전체 목록 출력.   
		}
		ArrayList<ScheduleVO> exSvo = new ArrayList<>();// 임시 배열리스트.
		for(int i=0; i<svo.size(); i++) { //리스트 size만큼 돌아서.
			if(svo.get(i).getID()/*FK*/.equals(vo.getID()/*PK*/)) { //아이디가 같은 것을 찾는다.
				exSvo.add(svo.get(i)); //같으면 임시 배열리스트에 저장.
			}
		}
		return exSvo; //id가 같은 목록만 출력.
	}
	//R
	public ScheduleVO loadSchedule(MemberVO vo) { //PK번호 입력받아서 하나의 목록만 출력.
		for(int i=0; i<svo.size(); i++) { //리스트 size만큼 돌아서.
			if(svo.get(i).getID().equals(vo.getID())) { //pk값이 동일하다면.
				return svo.get(i); // 그 값을 리턴해준다.
			}
		}
		return null; //PK가 없다면 null을 반환한다.
	}

	//U
	public boolean isModifySchedule(ScheduleVO vo) { //일정 변경. 
		for(int i=0; i<svo.size(); i++) { //리스트 size만큼 돌아서.
			if(svo.get(i).getpNum()==vo.getpNum()) { //PK값이 맞다면.
				if(vo.getMemo() != null) { //메모가 null이 아니라면.
					svo.get(i).setMemo(vo.getMemo()); //메모가 변경된다.
					return true; //true 리턴해준다.
				}else {
					svo.get(i).setYear(vo.getYear()); //년을 바꿔주고.
					svo.get(i).setMonth(vo.getMonth());  // 달을 바꿔주고.
					svo.get(i).setDay(vo.getDay()); //일을 바꿔준다.
					svo.get(i).setLowTemperature(vo.getLowTemperature());
					svo.get(i).setHighTemperature(vo.getHighTemperature());
					svo.get(i).setAmRainfall(vo.getAmRainfall());
					svo.get(i).setPmRainfall(vo.getPmRainfall());
					return true; //true를 리턴해준다.
				}
			}
		}
		return false; //false를 리턴해준다.
	}

	//D
	public boolean isDeleteSchedule(ScheduleVO vo) { //삭제하기.
		for(int i=0; i<svo.size(); i++) { //리스트 size만큼 돌아서.
			if(svo.get(i).getpNum()==vo.getpNum()) { //PK값이 맞다면.
				svo.remove(i); //PK값에 해당하는 객체를 삭제.
				return true;//true를 리턴해준다.
			}
		}
		return false; //false를 리턴해준다.
	}
}