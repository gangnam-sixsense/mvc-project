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

public class Crawling {
	
	public static void main(String[] args) {
		
		// Document에 웹페이지 링크 연결
		final String url = "https://www.seoul.go.kr/seoul/autonomy_sub.do";
		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 가져올 데이터가 있는 부분만
		String table = "div.district.tabcont";
		Elements eles = doc.select(table);
		
		// 구(key), 동(value)를 가진 맵
		Map<String, ArrayList<String>> map = new HashMap<>();
		for(Element str : eles) {
			// 구(list2.get(1)) 가져오기
			Elements gu = str.select("h5");
			ArrayList<String> list = new ArrayList<String>(Arrays.asList(gu.text().split("\\(")));
			ArrayList<String> list2 = new ArrayList<String>(Arrays.asList(list.get(0).split("\\) ")));
			// 동(listD) 가져오기
			Elements dongs = str.select("tbody > tr > td:nth-child(2)");
			ArrayList<String> listD = new ArrayList<>();
			for(Element e : dongs) {
				ArrayList<String> myList = new ArrayList<>(Arrays.asList(e.text().split(", ")));
				for(String dong : myList) {
					ArrayList<String> myList2 = new ArrayList<>(Arrays.asList(dong.split("\\(")));
					if(!listD.contains(myList2.get(0))) {
						listD.add(myList2.get(0));
					}
				}
			}
			// 맵에 구랑 동 넣기
			map.put(list2.get(1), listD);
		}
		// 맵 출력하기 ex) 강남구 [신사동, 압구정동, ... ]
		// 맵이라 순서가 ㄱㄴㄷㄹ 순이 아니에요
		System.out.println("맵 출력하기 : ");
		for(String key : map.keySet()) {
			System.out.println(key + " " + map.get(key));
		}
		System.out.println();
		
		// 만약 사용자한테 보여줄 때 ㄱㄴㄷㄹ 순으로 보여주고 싶다면 Collections.sort
		System.out.println("구 목록 ㄱㄴㄷㄹ순으로 출력하기 : ");
		ArrayList<String> guName = new ArrayList<String>(map.keySet());
		Collections.sort(guName);
		System.out.println(guName);
		System.out.println("동 목록 ㄱㄴㄷㄹ순으로 출력하기 : ");
		ArrayList<String> dongName = new ArrayList<String>(map.get("강남구")); // 강남구 대신 입력받은 값을 넣으면 됨
		Collections.sort(dongName);
		System.out.println(dongName);

	}

}
