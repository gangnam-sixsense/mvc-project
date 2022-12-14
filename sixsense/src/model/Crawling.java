package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Crawling {

	private Map<String, ArrayList<String>> map = new HashMap<>(); // 멤버변수 map
	// 강남구 : [신사동, 압구정동, ... ]
	// 강동구 : [강일동, 상일동, ...]
	// ... 와 같이 키값은 구, 밸류값은 동리스트로 이루어져 있는 맵을 저장할 공간

	public Crawling(){ // 생성자
		map = getMap();
		// 크롤링 객체를 만드는 순간 getMap() 메서드를 호출하여 반환값을 멤버변수 map에 저장한다
	}

	public Map<String, ArrayList<String>> getMap () { // 크롤링 한 map을 리턴할 메서드
		final String url = "https://www.seoul.go.kr/seoul/autonomy_sub.do";
		// 서울 특별시 자치구별 동 현황 사이트 주소 url
		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();
			// 해당 url의 페이지 정보를 문서 객체에 저장
		} catch (IOException e) {
			e.printStackTrace();
		}
		String div = "div.district.tabcont";
		// 크롤링할 정보가 들어있는 div 쿼리셀렉터
		Elements eles = doc.select(div);
		// div 목록
		Map<String, ArrayList<String>> map = new HashMap<>();
		// 리턴해줄 map 생성
		for(Element str : eles) {
			Elements gu = str.select("h5");
			// 구
			ArrayList<String> list = new ArrayList<String>(Arrays.asList(gu.text().split("\\(")));
			// '(' 기준으로 스플릿 
			// ex) '1) 강남구', '江南區) - 행정동', '行政洞) 22,법정동', '法定洞) 14'  
			ArrayList<String> list2 = new ArrayList<String>(Arrays.asList(list.get(0).split("\\) ")));
			// 위에서 ( 기준으로 스플릿한 것 중 첫번째의 값을
			// ex) '1) 강남구'
			// ') ' 기준으로 스플릿
			// ex) '1', '강남구'
			Elements dongs = str.select("tbody > tr > td:nth-child(2)");
			// tbody > tr > td:nth-child(2) 목록
			ArrayList<String> listD = new ArrayList<>(); // 동 리스트
			for(Element e : dongs) {
				ArrayList<String> myList = new ArrayList<>(Arrays.asList(e.text().split(", ")));
				// ', ' 기준으로 스플릿
				// ex) '신사동(新沙洞)', '압구정동(狎鷗亭洞)'
				for(String dong : myList) {
					ArrayList<String> myList2 = new ArrayList<>(Arrays.asList(dong.split("\\(")));
					// '(' 기준으로 스플릿
					// '신사동', '新沙洞)'  
					if(!listD.contains(myList2.get(0))) {
						listD.add(myList2.get(0));
						// 위의 값 중 첫번째 값을
						// ex) '신사동'
						// 동 리스트에 저장
					}
				} 
			}
			map.put(list2.get(1), listD);
			// 맵에 구와 동리스트를 저장
		}
		return map;
		// 맵 리턴
	}
	public ArrayList<String> getGu() { // 구 리스트를 리턴하는 메서드
		ArrayList<String> guList = new ArrayList<String>(map.keySet());
		Collections.sort(guList);
		return guList;
		// 맵의 키값들을 뽑아서 배열리스트에 저장 후 소트해서 리턴
	}
	public ArrayList<String> getDong(String gu){ // 동 리스트를 리턴하는 메서드
		ArrayList<String> dongList = new ArrayList<String>(map.get(gu));
		Collections.sort(dongList);
		return dongList;
		// 인자로 받은 구와 키값이 같은 밸류 배열리스트를 소트해서 리턴
	}
	public void setWeather(DAO2 dao2, VO2 vo2) { // 날씨를 크롤링하는 메서드
		LocalDate now = LocalDate.now();
		// 오늘 날짜 now
		LocalDate when = LocalDate.of(vo2.getYear(), vo2.getMonth(), vo2.getDay());
		// 인자로 받은 날짜 when
		long d = ChronoUnit.DAYS.between(now, when);
		// 오늘 날짜와 인자로 받은 날짜의 차이 d
		String url = getSelect(vo2.getDong());
		// 인자로 받은 동 정보를 getSelect 메서드에 넣어서 크롤링할 url을 리턴받아온다
		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();
			// 해당 url의 페이지 정보를 문서 객체에 저장
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 크롤링
		Elements lowestTemp = doc.select("#main_pack > section.sc_new.cs_weather_new._cs_weather > div._tab_flicking > div.content_wrap > div.content_area > div.inner > div > div.list_box._weekly_weather > ul > li:nth-child("+ String.valueOf(d+1) +") > div > div.cell_temperature > span > span.lowest");
		Elements highestTemp = doc.select("#main_pack > section.sc_new.cs_weather_new._cs_weather > div._tab_flicking > div.content_wrap > div.content_area > div.inner > div > div.list_box._weekly_weather > ul > li:nth-child("+ String.valueOf(d+1) +") > div > div.cell_temperature > span > span.highest");
		Elements lowestPrec = doc.select("#main_pack > section.sc_new.cs_weather_new._cs_weather > div._tab_flicking > div.content_wrap > div.content_area > div.inner > div > div.list_box._weekly_weather > ul > li:nth-child(1) > div > div.cell_weather > span:nth-child(" + String.valueOf(d+1) + ") > span > span");
		Elements highestPrec = doc.select("#main_pack > section.sc_new.cs_weather_new._cs_weather > div._tab_flicking > div.content_wrap > div.content_area > div.inner > div > div.list_box._weekly_weather > ul > li:nth-child(1) > div > div.cell_weather > span:nth-child(" + String.valueOf(d+1) + ") > span > span");
		vo2.setLowestTemp(lowestTemp.text()); // 최저기온
		vo2.setHighestTemp(highestTemp.text()); // 최대기온
		vo2.setLowestPrec(lowestPrec.text()); // 최저강수량
		vo2.setHighestPrec(highestPrec.text()); // 최대강수량 세팅
		dao2.plusList(vo2); // 인자로 받은 객체의 날씨를 세팅하고 나서 DAO클래스의 plusList의 인자로 보낸다	
	}
	public String getSelect(String dong) { // 위치에 따른 기상예보 url을 리턴하는 메서드
		return "https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query="+dong+"+기상예보";
	}

}
