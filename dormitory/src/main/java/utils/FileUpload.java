package utils;

import java.io.File;
import java.io.IOException;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUpload {

	
	public static String imageUpload(MultipartFile image) throws IllegalStateException, IOException{
		String fileName=System.currentTimeMillis()+""+(int)(Math.random()*100+1);//当前时间加上1到100的随机数拼接的字符串;
		Object md = new SimpleHash("MD5",fileName,image.getOriginalFilename(),2);
		File path = new File(ResourceUtils.getURL("classpath:").getPath());
		if(!path.exists()) path = new File("");
		File upload = new File(path.getAbsolutePath(),"static/img/upload/");
		if(!upload.exists()) upload.mkdirs();
		String fileFullName = md.toString() + ".png";
		System.out.println(fileFullName);
		File newFile = new File(upload.getPath()+File.separator+fileFullName);
		image.transferTo(newFile);
		System.out.println(newFile.getPath());
		return md.toString();
	}
}
