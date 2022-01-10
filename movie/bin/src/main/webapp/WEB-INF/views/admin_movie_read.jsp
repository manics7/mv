<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화상세</title>
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
$(function(){
	//메시지 출력 부분
	var msg = "${msg}";
	if(msg != ""){
		alert(msg);
	});
</script>

</head>
<body>
    <div class="wrap">
        <nav>
        <jsp:include page="admin_header.jsp"></jsp:include>
        </nav>


        <div class="main_wrap">
            <!-- 상단 메뉴 부분 -->
            <div class="cont_wrap">
                <!-- 게시글 리스트 -->
                <div class="cont_sidebar">
                <jsp:include page="adminpage_sidebar.jsp"/>
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
						<form action="./movieOfficialInsert" method="post" enctype="multipart/form-data">
						<div class="form-group">
								<label for="board_subject">제목</label> <input type="text"
									id="board_subject" name="movie_nm" class="form-control"
									value="${movie.movie_nm}" disabled="disabled" />
							</div>
						<input type="hidden"
									id="seq" name="movie_seq" class="form-control"
									value="${movie.mv_seq}"  />
							
							<div class="form-group">
								<label for="board_writer_name">개봉일</label> <input type="text"
									id="board_writer_name" name="open_dt"
									class="form-control" value="${movie.open_dt}" disabled="disabled" />
							</div>
							<div class="form-group">
								<label for="board_date">감독</label> <input type="text"
									id="board_date" name="directors" class="form-control"
									value="${movie.directors}" disabled="disabled" />
							</div>
							<div class="form-group">
						<label for="board_content">줄거리</label>
						<textarea id="board_content" name="movie_content" class="form-control" rows="10" style="resize:none"></textarea>
					</div>
						<div class="filebox">
			<!-- 파일 입력 처리 영역 -->
				<label for="file">업로드</label>
				<input type="file" name="files" id="file"
					multiple>
				<input type="text" class="upload-name"
					value="파일선택" readonly>
				<!-- 업로드할 파일이 있으면 1, 없으면 0 -->
				<input type="hidden" id="filecheck"
					value="0" name="fileCheck">
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
								<a href="adminMovieList" class="btn btn-primary">목록보기</a> 
								<input type="submit" value="등록" class="btn btn-info">
								
							</div>
						</div>
					</form>
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