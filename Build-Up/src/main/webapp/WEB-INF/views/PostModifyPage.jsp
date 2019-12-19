<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PostModifyPage</title>

    <!-- Bootstrap core CSS -->
  <link href="<c:url value="/vendor/bootstrap/css/bootstrap.min.css"/>" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="<c:url value="/css/blog-home.css"/>" rel="stylesheet">
  
  <script src="<c:url value="/vendor/jquery/jquery.min.js"/>"></script>
  
  <script src="<c:url value="/vendor/bootstrap/js/bootstrap.bundle.min.js"/>"></script>
  
  <script type="text/javascript" src="<c:url value="/resource/jquery-3.4.1.min.js"/>"></script>
  
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
	<br>
		<h1 class="my-4">Post Modify
		
			<small>Page</small>
		
		</h1>
			<div class="container">
			
				<form action="ModifyPost" method="post">
				
					<input type="hidden" name="bnum" value="${modify.bnum}">
					<div class="form-group">
					
						<label class="control-label">제목</label>
						<input class="form-control" type="text" name="btitle" value="${modify.btitle}">
						
					</div>
					
					<div class="form-group">
					
						<label class="control-label">작성자</label>
						<input class="form-control" type="text" name="nickname" value="${modify.nickname}" readonly="readonly">
						
					</div>
					
					<div class="form-group">
					
						<label class="control-label">작성일자</label>
						<input class="form-control" type="text" name="bdate" value="${modify.bdate}" readonly="readonly">
						
					</div>
					
					<div class="form-group">
					
						<label class="control-label">내용</label>
						<textarea class="form-control"name="bcontent" rows="8">${modify.bcontent}</textarea>
						
					</div>
					<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token}">
					<input type="submit" class="btn btn-primary" value="저장하기">
					<a href="<c:url value="/"/>" class="btn btn-primary">목록으로</a>
				</form>
				
			<!-- container -->
			</div>
	</div>

</body>
</html>