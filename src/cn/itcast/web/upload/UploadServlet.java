package cn.itcast.web.upload;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.itcast.utils.UploadUtils;

public class UploadServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 编写代码
		// request.setCharacterEncoding("utf-8");
		// 上传request 解析 .... 参照官方文档
		// Check that we have a file upload request 是否是一个有效上传请求 简称 enctype类型 如果不是 multipart/form-data 返回值 false
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (!isMultipart) {
			throw new RuntimeException("您不是一个有效的请求....");
		}
		// 上传请求 ...
		// Create a factory for disk-based file items 获取 生成核心解析类的工厂
		FileItemFactory factory = new DiskFileItemFactory();

		// Create a new file upload handler 创建核心解析类
		ServletFileUpload upload = new ServletFileUpload(factory);

		// 上传文件乱码
		upload.setHeaderEncoding("utf-8");// 只对上传文件名称有效
		// 上传而言: 大小限定 ... long 表示字节
		upload.setFileSizeMax(1024 * 1024 * 2);// 表示这是单个文件大小 2M
		upload.setSizeMax(1024 * 1024 * 5);// 设置上文件总大小 5M

		// Parse the request 解析 request 提交的数据 ...表单请求数据 封装到List集合里面
		try {
			List<FileItem> items = upload.parseRequest(request);
			// 集合里面存放 表单所有数据 普通文本 上传文件 都封装到List 集合中
			// FileItem 表示集合中每一个数据对象 既可以表示 普通文本 也可以表示一个上传文件对象
			for (FileItem item : items) {
				if (item.isFormField()) {
					// 普通文本 用户名 密码 爱好 简介 .....
					String name = item.getFieldName();// 表单 name 属性值
					String value = item.getString();// 表示 用户输入数据...哈哈哈
					String string = item.getString("utf-8");// 处理 用户输入普通文本乱码信息
					System.out.println("fileName = " + name + "----value =" + value + "  name 2 =" + string);
				} else {
					// 上传文件
					String fieldName = item.getFieldName();// name 对应属性值 upload
					String fileName = item.getName();// 文件真实名称 存在盘符 字符串 切割...
					// 抽取工具类 解决盘符问题
					fileName = UploadUtils.subFileName(fileName);
					// fileName 文件唯一 UUID .txt..jpg
					fileName = UploadUtils.generateRandonFileName(fileName);
					// uuid...名称
					String contentType = item.getContentType();// 文件上类型.jpg --->image/jpeg
					long size = item.getSize();// 文件大小
					System.out.println("上传  文件fieldName = " + fieldName);
					System.out.println("上传  文件名称 = " + fileName);
					System.out.println("上传  文件类型 = " + contentType);// mime 网络传输类型 .mp3 .html --->text/html jpg : image/jpeg

					// 文件写入服务器上 细节 态度
					// InputStream in = item.getInputStream();
					// 获取 apache 提供 write
					// 上传服务器绝对路径 e:
					// ServletContext 可以获取服务器绝对路径
					//String realPath = super.getServletContext().getRealPath("/mm" + UploadUtils.generateRandomDir(fileName));
					String realPath = "C:\\wt\\upload";
					// E:/xxxx/webapps/day16_upload/mm
					File file = new File(realPath);
					if (!file.exists()) {
						file.mkdirs();// 创建mm目录 make directorys
					}
					item.write(new File(file, fileName));// 上传完成!
					// 上传结束之后....临时文件删除
					item.delete();// 删除临时文件.
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
