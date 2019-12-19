<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>

  <link href="<c:url value="/vendor/bootstrap/css/bootstrap.min.css"/>" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="<c:url value="/css/blog-home.css"/>" rel="stylesheet">
  
  <script src="<c:url value="/vendor/jquery/jquery.min.js"/>"></script>
  
  <script src="<c:url value="/vendor/bootstrap/js/bootstrap.bundle.min.js"/>"></script>
  
  <script type="text/javascript" src="<c:url value="/resource/jquery-3.4.1.min.js"/>"></script>
  
  <script type="text/javascript" src="<c:url value="/js/PostWrite.js"/>"></script>
  
</head>
<body>

<!-------------------- 메뉴바 -------------------->
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
      <a class="navbar-brand" href="<c:url value="/"/>">Woodie's Blog</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
      
        <ul class="navbar-nav ml-auto">
        
	          <li class="nav-item active">
	            <a class="nav-link" href="<c:url value="/"/>">Home
	              <span class="sr-only">(current)</span>
	            </a>
	          </li>
	          
	          <li class="nav-item">
	            <a class="nav-link" href="<c:url value="/AboutPage"/>">About</a>
	          </li>
	          
	          <li class="nav-item">
				<sec:authentication property="principal" var="userInfo"/>
	           	<c:if test="${pageContext.request.userPrincipal != null}" >
	           		<a class="nav-link" id="logoutBtn" href="#">${userInfo.member.nickname}님 환영합니다.</a>
	           		<form action="<c:url value="/logout"/>" method="post" id="logout">
	           			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
	           		</form>
	           	</c:if>
	          </li>
          
        </ul>
        
      </div>
    </div>
  </nav>
<br><br>
	<!-------------------- 글 작성 폼 -------------------->
	<div class="container">
		<h1 class="my-4">Post Write View <small>page</small> </h1>
		
			<div class="container">
			
				<form action="PostWrite" method="post" id="form" enctype="multipart/form-data" accept-charset="UTF-8">
					
					<div class="form-group">
						<label class="control-label">제목</label>
						<input type="text" class="form-control" name="btitle" id="btitle" placeholder="제목을 입력해주세요.">
					</div>
					
					<div class="form-group">
						<label class="control-label">작성자</label>
						
						<input type="text" class="form-control" name="nickname" value="<sec:authentication property="principal.member.nickname"/>" readonly="readonly">
						<!-- 유저 이름 -->
						<input type="hidden" name="username" value="${pageContext.request.userPrincipal.name}">
					</div>
					
					<div class="form-group">

	                    <label class="control-label">첨부파일</label><br>
	                    <input type="file" class="img-fluid rounded" id="upfile" name="upfile"  multiple="multiple" accept=".jpeg, .jpg, .png, .gif">

               		</div>
					
					<div class="form-group">
						<label class="control-label">내용</label>
						<textarea class="form-control" name="bcontent" id="bcontent" rows="10" placeholder="내용을 입력해주세요"></textarea>
					</div>
					
					<!-- form 전송에 csrf가 없으면 전송이 되지않음 -->
					<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token}">
					
					<input type="submit" value="저장하기" class="btn btn-primary"> &nbsp;&nbsp;
					<a href="<c:url value="/"/>" class="btn btn-primary">목록으로</a>
				</form>
				
			
			</div>
	</div>

</body>
</html>