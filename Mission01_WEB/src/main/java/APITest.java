import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class APITest {
	public static void main(String args[]) throws IOException {

		// 1. 드라이버 연결
		// 2. connection 객체 생성
		// 3. statement 객체 생성
		// 4. 쿼리 실행
		// 5. 결과 수행(insert경우)
		// 6. 객체 연결 해제
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String url_value = "jdbc:sqlite:C:\\dev_web\\sqlite-tools-win32-x86-3430000\\wifi.db";

		try {
			connection = DriverManager.getConnection(url_value);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 1. url 만들기 위한 StringBuilder
		StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");

		// 2. 오픈 API의 요청 규격에 맞는 파라미터 생성, 발급받은 인증키
		urlBuilder.append("/" + URLEncoder.encode("4b78435972736f6534356e73497157", "UTF-8"));
		urlBuilder.append("/" + URLEncoder.encode("json", "UTF-8"));
		urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo", "UTF-8"));
		urlBuilder.append("/" + URLEncoder.encode("23001", "UTF-8"));
		urlBuilder.append("/" + URLEncoder.encode("24000", "UTF-8"));

		// 3. URL 객체 생성
		URL url = new URL(urlBuilder.toString());

		// 4. 요청하고자 하는 url과 통신하기 위한 Connection 객체 생성
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		// 5. 통신을 위한 메소드 set
		conn.setRequestMethod("GET");
		// 6. 통신을 위한 content-type set
		conn.setRequestProperty("Content-type", "application/json");
		// 7. 통신 응답코드 확인
		System.out.println("Response Code : " + conn.getResponseCode());
		// 8. 전달받은 데이터를 BufferedReader 객체로 저장.
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}

		// 9. 저장된 데이터 라인별로 읽어 StringBuilder 객체로 저장
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}

		// 10. 객체 해제
		rd.close();
		conn.disconnect();

		// 11. 전달받은 데이터 확인
		//System.out.println(sb.toString());

		// JSON 파싱
		// 1. 문자열 형태의 JSOn 파싱하기 위한 JSONParser 객체 생성.
		JSONParser parser = new JSONParser();
		// 2. 문자열을 JSON 형태로 JSONObject 객체에 저장.
		JSONObject obj = null;
		try {
			obj = (JSONObject) parser.parse(sb.toString());

		} catch (ParseException e) {
			e.printStackTrace();
		}
		// 3. 한 번 더 Parsing
		JSONObject second = (JSONObject) obj.get("TbPublicWifiInfo");

		// 4. row 키 값으로 배열을 JSONArray에 가져옴
		JSONArray jArray =  (JSONArray)second.get("row");
		int affected = 0;
		for (int i = 0; i < jArray.size(); i++) {
			WifiList wifiList = new WifiList();

			JSONObject o = (JSONObject)jArray.get(i);
			wifiList.setManageNo((String) o.get("X_SWIFI_MGR_NO"));
			// wifiList.setDist((String)o.get(거리));
			wifiList.setGugun((String) o.get("X_SWIFI_WRDOFC"));
			wifiList.setWifiName((String) o.get("X_SWIFI_MAIN_NM"));
			wifiList.setAddress((String) o.get("X_SWIFI_ADRES1"));
			wifiList.setDetailAddress((String) o.get("X_SWIFI_ADRES2"));
			wifiList.setSetFloor((String) o.get("X_SWIFI_INSTL_FLOOR"));
			wifiList.setSetType((String) o.get("X_SWIFI_INSTL_TY"));
			wifiList.setSetOrgan((String) o.get("X_SWIFI_INSTL_MBY"));
			wifiList.setServiceDivision((String) o.get("X_SWIFI_SVC_SE"));
			wifiList.setNetworkKind((String) o.get("X_SWIFI_CMCWR"));
			wifiList.setSetYear(Integer.parseInt((String)o.get("X_SWIFI_CNSTC_YEAR")));
			wifiList.setInOutdoor((String) o.get("X_SWIFI_INOUT_DOOR"));
			wifiList.setWificonnectEnvirionment((String) o.get("X_SWIFI_REMARS3"));
			wifiList.setX(Double.parseDouble((String)o.get("LAT")));
			wifiList.setY(Double.parseDouble((String)o.get("LNT")));
			wifiList.setWorkDate((String)o.get("WORK_DTTM"));
			
			String sql = "insert into wifi_list (manage_no, dist, gugun, wifi_name, address, detail_address, set_floor, set_type, set_organ, service_division, network_kind, "
					+ "set_year, in_outdoor, wificonnect_environment, x, y, work_date)"
					+ " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			try {
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, wifiList.getManageNo());
				preparedStatement.setDouble(2, wifiList.getDist());
				preparedStatement.setString(3, wifiList.getGugun());
				preparedStatement.setString(4, wifiList.getWifiName());
				preparedStatement.setString(5, wifiList.getAddress());
				preparedStatement.setString(6, wifiList.getDetailAddress());
				preparedStatement.setString(7, wifiList.getSetFloor());
				preparedStatement.setString(8, wifiList.getSetType());
				preparedStatement.setString(9, wifiList.getSetOrgan());
				preparedStatement.setString(10, wifiList.getServiceDivision());
				preparedStatement.setString(11, wifiList.getNetworkKind());
				preparedStatement.setInt(12, wifiList.getSetYear());
				preparedStatement.setString(13, wifiList.getInOutdoor());
				preparedStatement.setString(14, wifiList.getWificonnectEnvirionment());
				preparedStatement.setDouble(15, wifiList.getX());
				preparedStatement.setDouble(16, wifiList.getY());
				preparedStatement.setString(17,wifiList.getWorkDate());
				affected += preparedStatement.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(affected+"개 저장 완료");
		 try {
             if(preparedStatement != null && !preparedStatement.isClosed()) {
                 preparedStatement.close();
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }
         try {
             if(connection != null && !connection.isClosed()) {
                 connection.close();
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }
	}
}
