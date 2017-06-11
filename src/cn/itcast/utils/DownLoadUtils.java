package cn.itcast.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import sun.misc.BASE64Encoder;

public class DownLoadUtils {
	public static void downLoadMyFile(File desFile, HttpServletResponse response, String mimeType, String browserType) {
		// 浏览器 下载: 附件形式 下载口诀 一个流 两个头 attachement 附件 Content-Disposition 下载附件框头
		try {
			if (desFile.exists()) {
				// 下载 代码 固定性!
				String filename = desFile.getName();// 文件名称含有中文信息 ...附件名乱码
				// chrome firefox ie 附件框处理中文文件名内在机制不同 浏览器软件内核决定 附件解码方式...
				// chrome ie 一个阵营 附件默认 urlencode 解码 %%%%% 要求 服务器必须按照支持中文 urlecoder 编码
				// chrome ie 自动按照urldecode 解码
				// filename = URLEncoder.encode(filename, "utf-8");// chrome ie 中文乱码解决!
				// ff 另一个阵营 Base64 编码解码 要求服务器 输出附件名称 必须按照Base64Encoder 类 Myeclipse 编码
				// filename = new BASE64Encoder().encode(filename.getBytes("utf-8"));
				// filename 编码数据主体
				// filename = "=?utf-8?B?" + filename + "?=";// 完成获取火狐编码
				// filename = MimeUtility.encodeText(filename, "utf-8", "B");
				// 如果获取浏览器类型 进行对应代码处理

				if (browserType.contains("Firefox")) {
					filename = new BASE64Encoder().encode(filename.getBytes("utf-8"));
					filename = "=?utf-8?B?" + filename + "?=";// 完成获取火狐编码
				} else {
					filename = URLEncoder.encode(filename, "utf-8");// chrome ie 中文乱码解决!
				}
				// 1: 头 设置响应头 response.setHeader("Content-Disposition","attachment"); http refresh
				response.setHeader("Content-Disposition", "attachment;filename=" + filename);
				// 服务器告知浏览器 附件形式弹窗
				// 2: 第二个头 文件类型 aa.jpg ---> mime类型 image/jpeg 根据扩展名 找到对应mime 类型
				response.setContentType(mimeType);// 第二个头

				// 3: 下载 服务器 文件内容 以 io 形式 输出给浏览器 ... 浏览器接受输出流 ...通过 本身 附件框完成下载!
				// 输入 输出 参照物.. 当前系统 当前代码 java 虚拟
				InputStream in = new FileInputStream(desFile);// 输入流 将文件内容 读取当前系统
				// 输入流获取数据 通过输出流 将文件数据 发送 浏览器 ...
				int len = 0;// 字节流的拷贝
				byte bytes[] = new byte[1024 * 4];
				while ((len = in.read(bytes)) != -1) {
					response.getOutputStream().write(bytes, 0, len);
				}
				in.close();
			} else {
				throw new RuntimeException("文件不存在....");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
