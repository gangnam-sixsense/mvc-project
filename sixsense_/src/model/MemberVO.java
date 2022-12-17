package model;


public class MemberVO {
	private String ID; //PK
	private String pw; //비밀번호.
	private String name; //이름.
	//ArrayList<ScheduleVO> sArrayList ;
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public MemberVO() {	} //기본생성자
	public MemberVO(String ID, String pw, String name) { //관리자 샘플데이터를 위해 작성.
		this.ID=ID;
		this.pw=pw;
		this.name=name;
	}

}