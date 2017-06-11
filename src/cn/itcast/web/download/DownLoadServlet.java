package cn.itcast.web.download;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.utils.DownLoadUtils;

public class DownLoadServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 编写代码
		// User existUser = (User)request.getSession().getAttribute("existUser");
		// if(existUser==null){
		// //登陆
		// }else{
		// if(existUser.getType.equals("白金用户")){
		// // 下载代码
		// }else{
		// // 跳转到 充钱 页面...
		// }
		//
		// }
		ServletContext context = super.getServletContext();// 容器上下文对象
		String path = context.getRealPath("/mm/美女.gif");// 下载 附件 文件显示的名称 乱码
		//
		File file = new File(path);// 需要下载文件
		// 工具类 需要参数说明 下载目标文件对象File ,response 流的输出 ... ServletContext 获取mime类型
		DownLoadUtils.downLoadMyFile(file, response, context.getMimeType(file.getName()), request.getHeader("user-agent"));
		// // 浏览器 下载: 附件形式 下载口诀 一个流 两个头 attachement 附件 Content-Disposition 下载附件框头
		// if (file.exists()) {
		// // 下载 代码 固定性!
		// String filename = file.getName();
		// // 1: 头 设置响应头 response.setHeader("Content-Disposition","attachment"); http refresh
		// response.setHeader("Content-Disposition", "attachment;filename=" + filename);
		// // 服务器告知浏览器 附件形式弹窗
		// // 2: 第二个头 文件类型 aa.jpg ---> mime类型 image/jpeg 根据扩展名 找到对应mime 类型
		// String mimeType = context.getMimeType(filename);// api 作用: 根据文件扩展名 得出对应Mime type
		// response.setContentType(mimeType);// 第二个头
		//
		// // 3: 下载 服务器 文件内容 以 io 形式 输出给浏览器 ... 浏览器接受输出流 ...通过 本身 附件框完成下载!
		// // 输入 输出 参照物.. 当前系统 当前代码 java 虚拟
		// InputStream in = new FileInputStream(file);// 输入流 将文件内容 读取当前系统
		// // 输入流获取数据 通过输出流 将文件数据 发送 浏览器 ...
		// int len = 0;// 字节流的拷贝
		// byte bytes[] = new byte[1024 * 4];
		// while ((len = in.read(bytes)) != -1) {
		// response.getOutputStream().write(bytes, 0, len);
		// }
		// in.close();
		// } else {
		// throw new RuntimeException("文件不存在....");
		// }

	}

}
