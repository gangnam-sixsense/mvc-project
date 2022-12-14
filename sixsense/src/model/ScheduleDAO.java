package model;

import java.util.ArrayList;

public class ScheduleDAO {
	private ArrayList<ScheduleVO> svo;
	private int PK;

	public ScheduleDAO() {
		PK=101;
		svo=new ArrayList<ScheduleVO>(); //생성자에서 초기화하는 것이아니라로그인을 하면 그떄 초기화 하면 되는 거 아님?

	}

	//C
	public boolean isInputSchedule(ScheduleVO vo) { //일정 추가. 날짜 메모입력 받고 PK는 여기서 설정. 날씨는 안 받음.
		try {
		ScheduleVO data = new ScheduleVO(); //?
		data = vo;
		data.setpNum(PK++); //PK
		//data.setDate(vo.getDate());//날짜
		//data.setMemo(vo.getMemo());//메모  PK만 받으면됨
		//data.setWeather(vo.getWeather()); //크롤링에서 값을 넘겨받아야한다.
		svo.add(data);
		System.out.println("로그: 일정이 추가되었습니다.");
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
	public ScheduleVO loadSchedule(ScheduleVO vo) { //PK번호 입력받아서 하나의 목록만 출력.
		for(int i=0; i<svo.size(); i++) { //리스트 size만큼 돌아서.
			if(svo.get(i).getpNum()==vo.getpNum()) { //pk값이 동일하다면.
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
					System.out.println("로그: 메모가 변경되었습니다."); //개발자만이 볼 수 있는 로그.
					return true; //true 리턴해준다.
				}else if(vo.getYear() != 0) { //년이 0이 아니라면.
					if(vo.getMonth() !=0) { //달이 0이 아니라면.
						if(vo.getDay() !=0) { //일이 0이 아니라면.
							svo.get(i).setYear(vo.getYear()); //년을 바꿔주고.
							svo.get(i).setMonth(vo.getMonth());  // 달을 바꿔주고.
							svo.get(i).setDay(vo.getDay()); //일을 바꿔준다.
							System.out.println("로그: 날짜가 변경되었습니다."); //개발자만이 볼 수 있는 로그.
							return true; //true를 리턴해준다.
						}
					}
				}
			}
		}
		System.out.println("로그 : pk번호가 다릅니다."); //개발자만이 볼 수 있는 로그.
		return false; //false를 리턴해준다.
	}
	//D
	public boolean isDeleteSchedule(ScheduleVO vo) { //삭제하기.
		for(int i=0; i<svo.size(); i++) { //리스트 size만큼 돌아서.
			if(svo.get(i).getpNum()==vo.getpNum()) { //PK값이 맞다면.
				svo.remove(i); //PK값에 해당하는 객체를 삭제.
				System.out.println("로그: 일정이 삭제되었습니다.");
				return true;//true를 리턴해준다.
			}
		}
		return false; //false를 리턴해준다.
	}
}