package edu.uci.ics.tippers.common.util;

import edu.uci.ics.tippers.common.constants.Constants;
import org.apache.http.client.methods.HttpPost;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

// edu.uci.ics.tippers.scaler.data.Helper function
public class Helper {

	public static String getFileFromQuery(int query) {
		return String.format(Constants.QUERY_FILE_FORMAT, query);
	}

	public static String getFileFromQueryWithVersion(int query, int version) {
		return String.format(Constants.QUERY_FILE_VER_FORMAT, query, version);
	}
	// Increase timestamp for each observation based on observation speed
	public String increaseTimestamp(String startTime, int obsSpeed) {
		Timestamp ts = Timestamp.valueOf(startTime);
		long time = ts.getTime();
		time += (float)24*3600*1000/obsSpeed + 0.5*1000; // < 0.5s round down and > 0.5s round up
		
		ts.setTime(time);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timeStr  = dateFormat.format(ts);
		return timeStr;
	}	
		
	// The simulated sensor list does not include original sensor name
	public List<String> scaleSensorIds(List<String> sensorIds, int scaleNum, String simulatedName) {
		List<String> scaledSensorIds = new ArrayList<String>();
		int oriSensorSize = sensorIds.size();
		int simulatedSize = oriSensorSize * scaleNum;
		
		for (int i = 0; i < oriSensorSize; ++i) {
			scaledSensorIds.add(sensorIds.get(i));
		}
		
		for (int i = 1; i <= simulatedSize - oriSensorSize; ++i) {
			scaledSensorIds.add(simulatedName + i);
		}
		
		return scaledSensorIds;
	}	
	
	// Increase timestamp for each observation based on observation speed
	public String increaseTime(String startTime, int obsSpeed) {
		Timestamp ts = Timestamp.valueOf(startTime);
		long time = ts.getTime();
		time += (float)24*3600*1000/obsSpeed + 0.5*1000; // < 0.5s round down and > 0.5s round up
		
		ts.setTime(time);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timeStr  = dateFormat.format(ts);
		return timeStr;
	}
	
	// Add days to timestamp string
	public String timeAddDays(String timestamp, int days) {
		// convert string to long
		Timestamp ts = Timestamp.valueOf(timestamp);
		long time = ts.getTime();
		time += 24L*3600L*1000L*days;
		
		// convert long to string
		ts.setTime(time);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String string  = dateFormat.format(ts);
		return string;
	}

	public static String createAuthentication(String user, String password) {
		String encoding = Base64.getEncoder().encodeToString(String.format("%s:%s", user, password).getBytes());
		return encoding;
	}
}
