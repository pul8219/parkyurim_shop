package com.shop;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class JsonCreateTest {
    public static void main(String[] args){
        // JSONObject 선언(전체)
        JSONObject jsonObject = new JSONObject();

        // 학생의 정보가 담길 JSONArray 선언
        JSONArray studentArray = new JSONArray();
        // 학생 한 명의 정보가 담길 JSONObject 선언
        JSONObject studentInfo = new JSONObject();

        // 정보 입력
        studentInfo.put("name", "이요셉");
        studentInfo.put("part", "상경대학 경영학과");
        studentInfo.put("position", "학부생 5학년");
        // Array에 입력
        studentArray.add(studentInfo);

        studentInfo = new JSONObject();
        studentInfo.put("name", "박지혜");
        studentInfo.put("part", "공과대학 소프트웨어학과");
        studentInfo.put("position", "학부생 3학년");
        studentArray.add(studentInfo);

        studentInfo = new JSONObject();
        studentInfo.put("name", "이유빈");
        studentInfo.put("part", "공과대학 소프트웨어학과");
        studentInfo.put("position", "학부생 3학년");
        studentArray.add(studentInfo);

        studentInfo = new JSONObject();
        studentInfo.put("name", "박유림");
        studentInfo.put("part", "공과대학 소프트웨어학과");
        studentInfo.put("position", "학부생 3학년");
        studentArray.add(studentInfo);

        studentInfo = new JSONObject();
        studentInfo.put("name", "황준일");
        studentInfo.put("part", "공과대학 소프트웨어학과");
        studentInfo.put("position", "학부생 3학년");
        studentArray.add(studentInfo);

        studentInfo = new JSONObject();
        studentInfo.put("name", "박은영");
        studentInfo.put("part", "공과대학 소프트웨어학과");
        studentInfo.put("position", "학부생 3학년");
        studentArray.add(studentInfo);

        studentInfo = new JSONObject();
        studentInfo.put("name", "고태율");
        studentInfo.put("part", "예술대학 커뮤니케이션디자인학과");
        studentInfo.put("position", "학부생 4학년");
        studentArray.add(studentInfo);

        // 전체의 JSONObject에 "student"란 이름으로 studentArray라는 JSONArray의 정보를 입력
        jsonObject.put("student", studentArray);


        // 지도자의 정보가 담길 JSONArray 선언
        JSONArray leaderArray = new JSONArray();
        // 지도자 한 명의 정보가 담길 JSONObject 선언
        JSONObject leaderInfo = new JSONObject();

        // 정보 입력
        leaderInfo.put("name", "박용범");
        leaderInfo.put("part", "공과대학 소프트웨어학과");
        leaderInfo.put("position", "교수");
        leaderArray.add(leaderInfo);

        leaderInfo = new JSONObject();
        leaderInfo.put("name", "이승민");
        leaderInfo.put("part", "대학원 컴퓨터학과");
        leaderInfo.put("position", "박사과정 대학원생");
        leaderArray.add(leaderInfo);

        // 전체의 JSONObject에 "leader"란 이름으로 leaderArray라는 JSONArray의 정보를 입력
        jsonObject.put("leader", leaderArray);

        try{
            FileWriter file = new FileWriter("C:\\Users\\박유림\\Desktop\\소프트웨어\\연구실\\충남치매센터프로젝트\\jsonFile\\nif.json");
            file.write(jsonObject.toJSONString());
            file.flush();
            file.close();
        }
        catch(IOException e){
            e.printStackTrace();

        }

        System.out.println(jsonObject);





    }
}
