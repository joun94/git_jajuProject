package member.address;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.stereotype.Component;

import lombok.Data;


@Component
@Data
public class GpsToAddress {
	double latitude;
	double longitude;
	String regionAddress;
	private String member_sido, member_sigungu, member_address, member_detailAddr;
	
	public void setGps(double latitude, double longitude) throws Exception {
		this.latitude = latitude;
		this.longitude = longitude;
		this.regionAddress = getRegionAddress(getJSONData(getApiAddress()));
	}
	
	private String getApiAddress() {
		String apiURL = "https://dapi.kakao.com/v2/local/geo/coord2regioncode.json?x=" + longitude + "&y=" + latitude;
		return apiURL;
	}
	
	private String getJSONData(String apiURL) throws Exception {
		String jsonString = new String();
		String buf;
		
		URL url = new URL(apiURL);
		URLConnection conn = url.openConnection();
		
		String auth = "KakaoAK 7bf779386c83627b301831686a5c8857";
		conn.setRequestProperty("X-Reqyested-With", "curl");
		conn.setRequestProperty("Authorization", auth);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(
				conn.getInputStream(), "UTF-8"));
		while ((buf = br.readLine()) != null) {
			jsonString += buf;
		}
		return jsonString;
	}
	
	private String getRegionAddress(String jsonString) {
		JSONObject jObj = (JSONObject) JSONValue.parse(jsonString);
		JSONArray jArray = (JSONArray) jObj.get("documents");
		jObj = (JSONObject) jArray.get(0);
		
		member_sido = (String) jObj.get("region_1depth_name");
		member_sigungu = (String) jObj.get("region_2depth_name");
		member_detailAddr = (String) jObj.get("region_3depth_name");
		member_address = (String) jObj.get("address_name");
		
		return (String) jObj.toJSONString();
	}
}
