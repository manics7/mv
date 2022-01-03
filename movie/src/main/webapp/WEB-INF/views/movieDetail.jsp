<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>theater_detail</title>
</head>

<body>

		<jsp:include page="header.jsp"></jsp:include>
	
	<div>
		<div>
			<div>
			</div>
		</div>
		<div>
			<div>
				<div id="menu">
					기본 정보 | 상영 영화관 | 관람평
				</div>
				<div id="info">
					${mvOfficial.directors }
					${mvOfficial.actors }
					${mvOfficial.content }
				</div>
				<div id="영화관">
				</div>
				<div id="review">
				</div>
			</div>
		</div>
	</div>
	
		<jsp:include page="footer.jsp"></jsp:include>
</body>

</html>