<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MemberQuesReplyRead</title>
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
                        <!--
    
    <div>
        <h4>회원정보로 검색한 작성 글</h4>
        ${mbLIst}
    </div>
    -->
    <div class="container" style="margin-top: 5px">
		<div style="margin-top : 0;" class="row">
			<div class="col-sm-3"></div>
			<div class="col-sm-12">
				<div class="card shadow">
					<div class="card-body">
							<div class="form-group">
								<label for="board_subject">제목</label> <input type="text"
									id="board_subject" name="board_subject" class="form-control"
									value="${readqrDto.ques_reply_title}" disabled="disabled" />
							</div>
							
							<div class="form-group">
								<label for="board_date">작성날짜</label> <input type="text"
									id="board_date" name="board_date" class="form-control"
									value="<fmt:formatDate value="${readqrDto.ques_reply_date}"
											pattern="yyyy-MM-dd"/>" disabled="disabled" />
							</div>

							<div class="form-group">
								<label for="board_content">내용</label>
								<textarea id="board_content" name="board_content"
									class="form-control" rows="10" style="resize: none"
									disabled="disabled">${readqrDto.ques_reply_cont}</textarea>
							</div>

							<!--
							<div class="form-group">
								<label for="board_file">첨부 이미지</label> <img src="image/logo.png"
									width="100%" />
							</div>
							  -->

						<!-- 
				<div class="form-group">
						<label for="board_writer_name">작성자</label>
						<input type="text" id="board_writer_name" name="board_writer_name" class="form-control" value="홍길동" disabled="disabled"/>
					</div>
					<div class="form-group">
						<label for="board_date">작성날짜</label>
						<input type="text" id="board_date" name="board_date" class="form-control" value="2018-7-20" disabled="disabled"/>
					</div>
					<div class="form-group">
						<label for="board_subject">제목</label>
						<input type="text" id="board_subject" name="board_subject" class="form-control" value="제목입니다" disabled="disabled"/>
					</div>
					<div class="form-group">
						<label for="board_content">내용</label>
						<textarea id="board_content" name="board_content" class="form-control" rows="10" style="resize:none" disabled="disabled">본문입니다</textarea>
					</div>
					<div class="form-group">
						<label for="board_file">첨부 이미지</label>
						<img src="image/logo.png" width="100%"/>						
					</div>
				 -->

						<div class="form-group">
							<div class="text-right">
								<a href="questionFrm" class="btn btn-primary">목록보기</a> 
								
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-3"></div>
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