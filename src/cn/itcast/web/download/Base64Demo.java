package cn.itcast.web.download;

import java.net.URLEncoder;

import sun.misc.BASE64Encoder;

import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;

public class Base64Demo {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BASE64Encoder encoder = new BASE64Encoder();
		String s = "美女.gif"; // 576O5aWzLmdpZg== base64编码之后字符串
		String encode = encoder.encode(s.getBytes("utf-8"));
		System.out.println(encode);

		String string = URLEncoder.encode(s, "utf-8");
		System.out.println(string);// %E7%BE%8E%E5%A5%B3.gif

		// 获取火狐Base64编码 头 和 尾巴 s 字符串 B base64
		String text = MimeUtility.encodeText(s, "utf-8", "B");
		System.out.println(text);

	}

}
