<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의글 작성</title>
<!-- Bootstrap CDN -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<link rel="stylesheet" href="resource/css/queboard/queboard.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<script type="text/javascript">
	//state == 1 ? state.innerHtml("답변완료") : state.innerHtml("미완료");
</script>

</head>
<body>
   <div class="wrap">
        <nav>
        <jsp:include page="mypage_header.jsp"></jsp:include>
        </nav>


        <div class="main_wrap">
            <!-- 상단 메뉴 부분 -->
            <div class="cont_wrap">
                <!-- 게시글 리스트 -->
                <div class="cont_sidebar">
                <jsp:include page="mypage_sidebar.jsp"/>
                </div>
                <div class="container queboard">
                    <div class="card shadow">
                       
                        <div class="container" style="margin-top: 5px">
		<div style="margin-top : 0;" class="row">
			<div class="col-sm-3"></div>
			<div class="col-sm-12">
				<div class="card shadow">
				<div class="card-body">
					<form action="questionWrite" method="post">
					<div class="form-group">
						<label for="board_subject">제목</label>
						<input type="text" id="board_subject" name="ques_title" class="form-control"/>
					</div>
					<div class="form-group">
						<label for="board_content">내용</label>
						<textarea id="board_content" name="ques_cont" class="form-control" rows="10" style="resize:none"></textarea>
					</div>
					<div class="form-group">
						<div class="text-right">
							<button type="submit" class="btn btn-primary" >작성하기</button>
						</div>
					</div>
					
					</form>
				</div>
			</div>
                </div>

            </div>
        </div>
         
        </div>
        </div>
        </div>
        </div>

    </div>
    <div class="footer_wrap">
        <footer></footer>

    </div>






</body>
</html>

