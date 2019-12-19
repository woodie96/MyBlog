<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <!-- Custom fonts for this template-->

    <!-- Bootstrap core CSS -->
  <link href="<c:url value="/vendor/bootstrap/css/bootstrap.min.css"/>" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="<c:url value="/css/blog-home.css"/>" rel="stylesheet">
  
  <script src="<c:url value="/vendor/jquery/jquery.min.js"/>"></script>
  
  <script src="<c:url value="/vendor/bootstrap/js/bootstrap.bundle.min.js"/>"></script>
  
  <script type="text/javascript" src="<c:url value="/resource/jquery-3.4.1.min.js"/>"></script>
  <script type="text/javascript" src="<c:url value="/js/Boardlist.js"/>"></script>
  
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
            <a class="nav-link" href="<c:url value="AboutPage"/>">About</a>
          </li>
          
          <!-- 로그인 버튼 부분 -->
          <li class="nav-item">
	         <sec:authentication property="principal" var="userID"/>
	        
	          <c:choose>
	          	<c:when test="${pageContext.request.userPrincipal == null}">
	          		 <a class="nav-link" href="LoginPage">로그인</a>
	          	</c:when>
	          	
	          	<c:when test="${pageContext.request.userPrincipal != null}">
	          		 <a class="nav-link" id="logoutbtn" href="#">${userID.member.nickname} 님 환영합니다.</a>
	          		 <form id="logout" action="<c:url value="/logout"/>" method="post">
	          		 	<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token}">
	          		 	<!-- submit은 스크립트에서 됌 -->
	          		 </form>
	          	</c:when>
	          </c:choose>
          </li>
        </ul>
      </div>
    </div>
  </nav>

  <!-------------------- Post -------------------->
  <div class="container">

    <div class="row">

      <!-- Blog Entries Column -->
      <div class="col-md-8">
		<br><br>
        <h1 class="my-4">Woodie's
          <small>Develop World</small>
        </h1>
        
        <!-- 게시물 보여주는 부분 -->
        <c:choose>
	        <c:when test="${ empty searchValue }"> <!-- 검색어 기준으로 검색어가 없으면 보여짐 -->
	        	<c:forEach items="${list}" var="dto">
			        <!-- Blog Post -->
			        <div class="card mb-4">
			          <img class="card-img-top" src="<spring:url value="/upload/${dto.postimage}"/>" alt="Card image cap" style="height: 350px;">
			          <div class="card-body">
			            <h2 class="card-title">${dto.btitle}</h2> <!-- 글 제목 -->
			            <c:set var="bcontent" value="${dto.bcontent }"></c:set>
			            <p class="card-text"> ${fn:substring(bcontent,0,50)}...</p>
			            <a href="Listview?bnum=${dto.bnum}" class="btn btn-primary">Read More &rarr;</a> <!-- 누르면 페이지 이동 -->
			          </div>
			          <div class="card-footer text-muted">
			            ${dto.bdate} 작성 완료
			          </div>
			        </div>
		        </c:forEach>
			</c:when>
			
			<c:when test="${!empty searchValue}"> <!-- 검색어 키워드가 존재하면 당연히 검색어 기준으로 보여줌 -->
	        	<c:forEach items="${searchValue}" var="dto">
			        <!-- Blog Post -->
			        <div class="card mb-4">
			          <img class="card-img-top" src="<spring:url value="/upload/${dto.postimage}"/>" alt="Card image cap" style="height: 350px;">			    	    
			          <div class="card-body">		       
			            <h2 class="card-title">${dto.btitle}</h2> <!-- 글 제목 -->
			            <c:set var="bcontent" value="${dto.bcontent }"></c:set>
			            <p class="card-text"> ${fn:substring(bcontent,0,50)}...</p>
			            <a href="Listview?bnum=${dto.bnum}" class="btn btn-primary">Read More &rarr;</a> <!-- 누르면 페이지 이동 -->
			          </div>
			          <div class="card-footer text-muted">
			            ${dto.bdate} 작성 완료
			          </div>
			        </div>
		        </c:forEach>
			</c:when>
		</c:choose>
		
		<!-------------------- 어드민일때만 글쓰기 가능 -------------------->
		<sec:authorize access="hasRole('ROLE_ADMIN')">
        	<a href="<c:url value="/PostWritePage"/>" class="btn btn-primary" style="margin-left: 630px;">글 쓰기 &rarr;</a><!-- 누르면 페이지 이동 -->
        </sec:authorize>
		
		<br>
		
        <!-------------------- 페이징 -------------------->
        
        <ul class="pagination justify-content-center mb-4">
        
          <li class="page-item">
          
          	  <!-- 시작 페이지가 2 이상이여야지만 이전버튼 활성화 -->
	          <c:if test="${startpage > 1 }">
	            <a class="page-link" href="<c:url value="/?pageNum=${startpage - 1}"/>">&larr; prev</a>
	          </c:if>
	          
          </li>
		      
		      <c:forEach begin="${startpage}" end="${endpage}" var="i">
			      <li class="page-item">
				     <a class="page-link" href="<c:url value="/?pageNum=${i}"/>">${i}</a>
			      </li>
		      </c:forEach>
		      
          <li class="page-item">
          
              <!-- 끝 페이지가 realEnd보다 작아야지만 활성화 게시물이 더 있어야지만 활성화 됌 -->
          	  <c:if test="${endpage < realEnd}">
            	  <a class="page-link" href="<c:url value="/?pageNum=${endpage + 1}"/>">end &rarr;</a>
              </c:if>
          
          </li>
        </ul>
        
      </div>
	 

        
      <!-------------------- 사이드 바 검색 카테고리 등 -------------------->
      
      <div class="col-md-4">
		<br><br>
		
        <!-- Search Widget -->
        <form action="search" method="post">
	        <div class="card my-4">
	          <h5 class="card-header">Search</h5>
	          <div class="card-body">
	            <div class="input-group">          
	              <input type="text" class="form-control" name="SearchValue" placeholder="제목으로 검색해주세요.">            
	              <span class="input-group-btn">
	                &ensp;&ensp;<input class="btn btn-secondary" type="submit" value="검색">
	              </span>
	            </div>
	          </div>
	        </div>
        </form>

        <!-- Categories Widget -->
        <div class="card my-4">
          <h5 class="card-header">Categories</h5>
          <div class="card-body">
            <div class="row">
              <div class="col-lg-6">
                <ul class="list-unstyled mb-0">
                  <li>
                    <a href="#">Web Design</a>
                  </li>
                  <li>
                    <a href="#">HTML</a>
                  </li>
                  <li>
                    <a href="#">JAVA</a>
                  </li>
                </ul>
              </div>
              <div class="col-lg-6">
                <ul class="list-unstyled mb-0">
                  <li>
                    <a href="#">JavaScript</a>
                  </li>
                  <li>
                    <a href="#">Jsp/Servlet</a>
                  </li>
                  <li>
                    <a href="#">SpringFramework</a>
                  </li>
                </ul>
              </div>
            </div>
          </div>
        </div>

        <!-- Side Widget -->
        <div class="card my-4">
          <h5 class="card-header">Side Widget</h5>
          <div class="card-body">
          	Study, Develop, hobby ... <br>
          </div>
        </div>
        

      </div>

    </div>
    <!-- /.row -->

  </div>
  <!-- /.container -->

  <!-- Footer -->
  <footer class="py-5 bg-dark">
    <div class="container">
      <p class="m-0 text-center text-white">Copyright &copy; Your Website 2019</p>
    </div>
    <!-- /.container -->
  </footer>
</body>
</html>