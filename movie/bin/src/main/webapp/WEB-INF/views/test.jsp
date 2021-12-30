<<<<<<< HEAD
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
<h1>${name }</h1>
<div>JSP List Test</div> 
<c:forEach var="item" items="${list}" varStatus="idx"> 
	${idx.index}, ${item} <br />
 </c:forEach>

</body>
=======
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
<h1>${name }</h1>
<div>JSP List Test</div> 
<c:forEach var="item" items="${list}" varStatus="idx"> 
	${idx.index}, ${item} <br />
 </c:forEach>

</body>
>>>>>>> branch 'master' of https://github.com/manics7/mv.git
</html>