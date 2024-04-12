package com.example.demo.util;

import com.iceteasoftwarebe.constant.ApiConstants;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 07/07/2021 - LinhLH: Create new
 *
 * @author LinhLH
 */
@Slf4j
public class HttpUtil {
	public static String getClientIP(HttpServletRequest request) {
		String xfHeader = request.getHeader(ApiConstants.HttpHeaders.X_FORWARDED_FOR);
		
	    if (xfHeader == null){
	        return request.getRemoteAddr();
	    }
	    
	    return xfHeader.split(",")[0];
	}
	
	public static String getHostAddress() {
		String hostAddress = StringPool.LOCALHOST;

		try {
			hostAddress = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			log.warn("The host name could not be determined, using `localhost` as fallback");
		}

		return hostAddress;
	}
}
