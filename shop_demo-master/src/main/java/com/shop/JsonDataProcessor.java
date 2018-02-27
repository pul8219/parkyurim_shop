package com.shop;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Set;

public class JsonDataProcessor {

    HttpServletRequest request;
    String pathString;

    JsonDataProcessor(String pathString){ // 생성자
        this.pathString = "C:\\Users\\박유림\\IdeaProjects\\shop_demo-master\\src\\main\\webapp" + pathString;
    }

    public JSONArray getInfo() throws IOException, ParseException {
        // Reading json file
        String path = pathString;
        BufferedReader jsonReader = new BufferedReader(new InputStreamReader(new FileInputStream(path),"UTF-8"));

        // Parsing json file
        JSONParser parser = new JSONParser();
        JSONArray list = (JSONArray)parser.parse(jsonReader); // json 파일의 시작이 array라서 이렇게 코드를 짠 것!
        JSONObject obj = ((JSONObject) list.get(0));
        return (JSONArray) obj.get("data");
        // list = (JSONArray) ((JSONObject)list.get(0)).get("data");
    }





    // nif.json파일을 읽어 문자열을 파싱하고 변수 sql에 쿼리문을 담는 메소드
    public void getNifInfo() throws IOException, ParseException {
        // Reading json file
        String path = request.getSession().getServletContext().getRealPath(pathString);
        BufferedReader jsonReader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));

        // Parsing json file
        JSONParser parser = new JSONParser(); // JSONParser인 parser 선언
        JSONObject obj = (JSONObject)parser.parse(jsonReader), partObj; // json전체 파일을 읽어 담는 obj와 이후 세부 오브젝트를 담기 위한 partObj선언
        JSONArray list; //JSONArray 형태로 list 선언

        //몇 개의 obj가 넘어오는지 확인하기 위한 프린트 문장
        System.out.println("Object 갯수: " + obj.size());
        //Key set 리스트 출력해보기
        Set key = obj.keySet();
        //Iterator 설정
        Iterator<String> iter = key.iterator();

        String sql=""; // 쿼리문 담기 위한 sql 초기화
        while(iter.hasNext()){ // 다음 항목으로 이동할 데이터가 있을 때까지 반복(hasNext 메소드 사용)
            String keyname = iter.next(); // key를 받아 keyname에 String으로 저장
            list = (JSONArray) obj.get(keyname); // keyname 접근하여 배열 받아와 JSONArray 형식에 저장

            for(int i=0,len=list.size(); i<len; i++) {
                partObj = (JSONObject) list.get(i); // list 배열에서 첫번째 인덱스를 통해 오브젝트를 받아옴
                sql += "INSERT INTO " + keyname + " SET ";
                sql += "name='" + partObj.get("name") + "'";
                sql += ", part='" + partObj.get("part") + "'";
                sql += ", position='" + partObj.get("position") + "'\n";
            }
        }
        System.out.println(sql);

    }
}
