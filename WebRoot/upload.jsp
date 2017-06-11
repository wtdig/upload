<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
  <h2>上传资源</h2>
               <!-- 上传而言  代码 比较固定    页面3点要求 必须
                  1:  要求 必须提供 表单  而且提交方式必须Post  
                  2: input type = "file" 组件  弹出窗口 选择文件
                  3: 上传数据的Mime类型修改 支持多媒体上传
                -->
       <form action="${pageContext.request.contextPath }/uploadServlet" method="post" enctype="multipart/form-data">
         用户名<input type="text" name="name"><br>
         资源上传:<input type="file" name="upload"><br>
         <input type="submit" value="上传">
     </form>
     
</body>
</html>