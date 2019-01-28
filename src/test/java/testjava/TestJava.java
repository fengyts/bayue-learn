package testjava;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class TestJava {
	
	public static String bytesToHexStr(byte[] byteArray) {
	    if (byteArray == null){
	        return null;
	    }
	    char[] hexArray = "0123456789ABCDEF".toCharArray();
	    char[] hexChars = new char[byteArray.length * 2];
	    for (int j = 0; j < byteArray.length; j++) {
	        int v = byteArray[j] & 0xFF;
	        hexChars[j * 2] = hexArray[v >>> 4];
	        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
	    }
	    return new String(hexChars);
	}
	
	private static String bytesToHexStr1(byte[] src) {  
        StringBuilder builder = new StringBuilder();  
        if (src == null || src.length <= 0) {  
            return null;  
        }  
        String hv;  
        for (int i = 0; i < src.length; i++) {  
            // 以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式，并转换为大写  
            hv = Integer.toHexString(src[i] & 0xFF).toUpperCase();  
            if (hv.length() < 2) {  
                builder.append(0);  
            }  
            builder.append(hv);  
        }  
//      System.out.println(builder.toString());  
        return builder.toString();  
    }  
	
	public void test() {
		try {
//			File file = new File("src/main/resources/testfile/test.txt");
//			File file1 = new File("src/main/resources/testfile/test1.txt");
//			InputStream is = new FileInputStream(file1);
//			byte[] b = new byte[4];
//			int read = is.read(b);
//			String hexStr = bytesToHexStr(b);
//			String hexStr1 = bytesToHexStr1(b);
//			System.out.println(hexStr);
//			System.out.println(hexStr1);
//			String str = new String(b);
//			System.out.println(str);
//			
//			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public static void main(String[] args) {
		TestJava test = new TestJava();
		AtomicInteger ai = new AtomicInteger(0);
	}

}
