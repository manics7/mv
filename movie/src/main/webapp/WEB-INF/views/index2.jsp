<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="resource/css/home.css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>index test</h1>

<a href="">인덱스2 </a>
	<h3>application(ServltContext) 객체 테스트</h3>
	<hr>
	1. 서블릿 컨테이너 이름:<%=application.getServerInfo() %><br>
	2. 서블릿 API버젼: <%=application.getMajorVersion() %>.<%=application.getMinorVersion() %><br>
	3. 현재 Context( = 현재 웹 어플리케이션, 제품, TomTest)의 실제 경로(서버의 디스크 경로): 
	<%=application.getRealPath("") %><br>
	
	<%
		// application.log("기록할 메세지"); --> 필요한 정보 기록
		String addr=request.getRemoteAddr(); // 접속 클라이언트의 ip정보
		String method=request.getMethod(); // 요청방식
		String protocol= request.getProtocol();// HTTP요청 버전: 1.0 1.1
		String uri=request.getRequestURI();
		
		String info=">>접속한 클라이언트 정보<< protocol: "+protocol+", 요청방식: "+method+", 접속IP: "+addr+", 경로: "+uri;
		System.out.println(info); // 서버 콘솔
		out.print(info); // 브라우저 출력
		application.log(info);
	%>
</body>
</html>