package utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.LiteDeviceResolver;

public class DetectMobile {

	public static boolean isMobile(HttpServletRequest request){
		LiteDeviceResolver deviceResolver = new  LiteDeviceResolver();
		Device device = deviceResolver.resolveDevice(request);
		return device.isMobile();
	}
}
