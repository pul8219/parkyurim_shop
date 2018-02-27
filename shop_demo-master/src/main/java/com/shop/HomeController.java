package com.shop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.jrockit.jfr.StringConstantPool;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.io.*;
import java.sql.*;


@Controller
public class HomeController {

    // Test용으로 만든 nif.json파일 파싱하는 메소드
    @RequestMapping(value={"/getNif"})
        public void getNif(HttpServletRequest request) throws IOException, ParseException {

        JsonDataProcessor jdp = new JsonDataProcessor("/resources/json/nif.json");
        jdp.getNifInfo();
    }


    static public void main(String[] args) throws Exception, IOException, ParseException, ClassNotFoundException, SQLException {

        HomeController controller = new HomeController();
        controller.getProduct(); // getProduct 메소드 실행
        controller.getMember(); // getMember 메소드 실행
        controller.getReserve(); // getReserve 메소드 실행

    }


    @RequestMapping(value={"/getProduct"})
    public void getProduct() throws Exception, IOException, ParseException, ClassNotFoundException, SQLException {

        JsonDataProcessor jdp = new JsonDataProcessor("/resources/json/data.json");
        JSONArray list = jdp.getInfo();
        JSONObject obj;

        /* DB 연결 */
        String
                driver = "org.mariadb.jdbc.Driver",
                url    = "jdbc:mariadb://127.0.0.1/shop?useUnicode=true&characterEncoding=utf8",
                uId    = "root",
                uPwd   = "pass";
        Connection con;
        PreparedStatement pstmt;
        Class.forName(driver);
        con = DriverManager.getConnection(url, uId, uPwd);

        try{
            String sql = "INSERT INTO data (product,price,img,longinfo,shortinfo,quantity,category,hit) VALUES(?,?,?,?,?,?,?,?);";
            for(int i=0, len=list.size(); i<len; i++){
                obj = (JSONObject) list.get(i);
                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, obj.get("product").toString());
                pstmt.setString(2, obj.get("price").toString());
                pstmt.setString(3, obj.get("img").toString());
                pstmt.setString(4, obj.get("longinfo").toString());
                pstmt.setString(5, obj.get("shortinfo").toString());
                pstmt.setString(6, obj.get("quantity").toString());
                pstmt.setString(7, obj.get("category").toString());
                pstmt.setString(8, obj.get("hit").toString());

                pstmt.executeUpdate(); // 쿼리 실행
                System.out.println(sql); // 쿼리 제대로 담겼는지 테스트
                pstmt.close(); // PreparedStatement close
            }
            con.close(); // DB close
        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }
    }


    @RequestMapping(value = {"/getMember"})
    public void getMember() throws Exception, IOException, ParseException, ClassNotFoundException, SQLException {

        JsonDataProcessor jdp = new JsonDataProcessor("/resources/json/member.json");
        JSONArray list = jdp.getInfo();
        JSONObject obj;

        /* DB 연결 */
        String
                driver = "org.mariadb.jdbc.Driver",
                url = "jdbc:mariadb://127.0.0.1/shop?useUnicode=true&characterEncoding=utf-8",
                uId = "root",
                uPwd = "pass";
        Connection con;
        PreparedStatement pstmt;
        Class.forName(driver);
        con = DriverManager.getConnection(url, uId, uPwd);

        try {
            String sql = "INSERT INTO member (name,id,pw,age,area,level) VALUES(?,?,?,?,?,?);";
            for (int i=0, len = list.size(); i<len; i++){
                obj = (JSONObject) list.get(i);
                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, obj.get("name").toString());
                pstmt.setString(2, obj.get("id").toString());
                pstmt.setString(3, obj.get("pw").toString());
                pstmt.setString(4, obj.get("age").toString());
                pstmt.setString(5, obj.get("area").toString());
                pstmt.setString(6, obj.get("level").toString());

                pstmt.executeUpdate(); // 쿼리 실행
                System.out.println(sql); // 쿼리 제대로 담겼는지 테스트
                pstmt.close(); //PreparedStatement close
            }
            con.close(); // DB close
        }
        catch(Exception e){
                System.err.println(e.getMessage());
        }

    }

    @RequestMapping(value = {"/getReserve"})
    public void getReserve() throws Exception, IOException, ParseException, ClassNotFoundException, SQLException {
        JsonDataProcessor jdp = new JsonDataProcessor("/resources/json/reserve.json");
        JSONArray list = jdp.getInfo();
        JSONObject obj;

        /* DB 연결 */
        String
                driver = "org.mariadb.jdbc.Driver",
                url = "jdbc:mariadb://127.0.0.1/shop?useUnicode=true&characterEncoding=utf-8",
                uId = "root",
                uPwd = "pass";
        Connection con;
        PreparedStatement pstmt;
        Class.forName(driver);
        con = DriverManager.getConnection(url, uId, uPwd);

        try {
            String sql = "INSERT INTO reserve (name,product,state,rdate,quantity,address,price,saleprice) VALUES(?,?,?,?,?,?,?,?);";
            for (int i=0, len = list.size(); i<len; i++){
                obj = (JSONObject) list.get(i);
                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, obj.get("name").toString());
                pstmt.setString(2, obj.get("product").toString());
                pstmt.setString(3, obj.get("state").toString());
                pstmt.setString(4, obj.get("rdate").toString());
                pstmt.setString(5, obj.get("quantity").toString());
                pstmt.setString(6, obj.get("address").toString());
                pstmt.setString(7, obj.get("price").toString());
                pstmt.setString(8, obj.get("saleprice").toString());

                pstmt.executeUpdate(); // 쿼리 실행
                System.out.println(sql); // 쿼리 제대로 담겼는지 테스트
                pstmt.close(); //PreparedStatement close
            }
            con.close(); // DB close
        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }

    }


    @RequestMapping(value={"/main","/"})
    public String main() throws IOException {
        return "main";
    }

    @RequestMapping(value="/member/login")
    public String member_login() throws IOException {

        return "/member/login";
    }

    @RequestMapping(value="/member/logout")
    public void member_logout(HttpServletResponse response) throws IOException {
        response.setHeader("content-type","text/html;charset=utf8");
        response.getWriter().print("<script>alert('로그아웃 되었습니다.'); location.replace('/');</script>");
    }

    @RequestMapping(value="/menu/list")
    public String menu_list() throws IOException {

        return "/menu/list";
    }

    @RequestMapping(value="/menu/view")
    public String menu_view() throws IOException {

        return "/menu/view";
    }

    @RequestMapping(value="/menu/buy")
    public String menu_buy() throws IOException {

        return "/menu/buy";
    }
}
