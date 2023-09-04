import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class APITest {
	static {
		// 1. 드라이버 연결
				//2. connection 객체 생성
				//3. statement 객체 생성
				//4. 쿼리 실행
				//5. 결과 수행(insert경우)
				//6. 객체 연결 해제
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String url_value = "jdbc:sqlite:C:\\dev_web\\sqlite-tools-win32-x86-3430000wifi.db";
		
		try {
			connection = DriverManager.getConnection(url_value);
			
			
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	public static void main(String args[]) throws IOException {
		//1. url 만들기 위한 StringBuilder
		StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");
		
		//2. 오픈 API의 요청 규격에 맞는 파라미터 생성, 발급받은 인증키
		urlBuilder.append("/" + URLEncoder.encode("4b78435972736f6534356e73497157","UTF-8"));
		urlBuilder.append("/" + URLEncoder.encode("json","UTF-8"));
		urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo", "UTF-8"));
		urlBuilder.append("/" + URLEncoder.encode("1","UTF-8"));
		urlBuilder.append("/" + URLEncoder.encode("3","UTF-8"));

			//3. URL 객체 생성
			URL url = new URL(urlBuilder.toString()); 
			
			//4. 요청하고자 하는 url과 통신하기 위한 Connection 객체 생성
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			
			//5. 통신을 위한 메소드 set
			conn.setRequestMethod("GET");
			//6. 통신을 위한 content-type set
			conn.setRequestProperty("Content-type", "application/json");
			//7. 통신 응답코드 확인
			System.out.println("Response Code : " + conn.getResponseCode());
			//8. 전달받은 데이터를 BufferedReader 객체로 저장.
			BufferedReader rd;
			if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else {
				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}
			
			//9. 저장된 데이터 라인별로 읽어 StringBuilder 객체로 저장
			StringBuilder sb = new StringBuilder();
			String line;
			while((line = rd.readLine()) != null) {
				sb.append(line);
			}
			
			//10. 객체 해제
			rd.close();
			conn.disconnect();
			
			//11. 전달받은 데이터 확인
			System.out.println(sb.toString());
			
			//JSON 파싱
			// 1. 문자열 형태의 JSOn 파싱하기 위한 JSONParser 객체 생성.
			JSONParser parser = new JSONParser();
			//2. 문자열을 JSON 형태로 JSONObject 객체에 저장.
			JSONObject obj = null;
			try {
				obj = (JSONObject)parser.parse(sb.toString());
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
			//3. 한 번 더 Parsing
			JSONObject second = (JSONObject) obj.get("TbPublicWifiInfo");
			
			// 4. row 키 값으로 배열을 JSONArray에 가져옴
			JSONArray jArray = second.getJSONArray("row");
			
			for (int i = 0; i < jArray.length(); i++) {
				
				JSONObject o = jArray.getJSONObject(i);
				
			}
	}
}
