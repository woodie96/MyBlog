<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  
  
  <!-- Bootstrap core CSS -->
  <link href="<c:url value="/vendor/bootstrap/css/bootstrap.min.css"/>" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="<c:url value="/css/blog-post.css"/>" rel="stylesheet">

	 <!-- Bootstrap core JavaScript -->
  <script src="<c:url value="/vendor/jquery/jquery.min.js"/>"></script>
  <script src="<c:url value="/vendor/bootstrap/js/bootstrap.bundle.min.js"/>"></script>
  
  <!-- 글 수정 버튼 -->
  <script type="text/javascript" src="<c:url value="/resource/jquery-3.4.1.min.js"/>"></script>
  <script type="text/javascript" src="<c:url value="/js/ListView.js"/>">
  
  	window.onload = function(){
  		
		$('#logoutBtn').on('click',function(){
			
			var test = confirm('로그아웃 하시겠습니까?');
			
			if(test === true){
				$('#logout').submit();
				return true;
			}else{
				return false;
			}
			
		}); 
  		
  	}
  
  </script>

</head>
<body>

<!-- Navigation -->
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
            <a class="nav-link" href="#">About</a>
          </li>

          <!-- 로그인 버튼 부분 -->
          <li class="nav-item">
	         <sec:authentication property="principal" var="userID"/>
	          <c:choose>
	          	<c:when test="${pageContext.request.userPrincipal == null}">
	          		 <a class="nav-link" href="<c:url value="/LoginPage"/>">로그인</a>
	          	</c:when>
	       
	          	<c:when test="${pageContext.request.userPrincipal != null}"> <!-- 이 부분에 로그아웃 넣어도 될듯. -->
	          		 <a class="nav-link" href="#" id="logoutBtn">${userID.member.nickname}님 환영합니다.</a>
	          		 <form action="<c:url value="/logout"/>" method="post" id="logout">
	          		 	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
	          		 </form>
	          	</c:when>
	          </c:choose>
           </li>
        </ul>
      </div>
    </div>
  </nav>
  

		  <!-- Page Content -->
		  <div class="container">
		
		    <div class="row">
		
		      <!-- Post Content Column -->
		      <div class="col-lg-8">
				
		        <h1 class="my-4">Post 
		          <small>View Page</small>
		        </h1>
				
		        <!-- Title -->
		        <h1 class="mt-4">${post.btitle}</h1> <!--  권한이 유저지만 게시물을 볼때 게시물 작성자 이름이 나와야 하기때문에 post.~ -->
				
				<hr>
				
		        <!-- Author -->
		        <p>작성자:&nbsp; ${post.nickname}</p>
		
		        <hr>
		
		        <!-- Date/Time -->
		        <p>작성 일자:&nbsp; ${post.bdate}</p>
		
		        <hr>
		
		        <!-- Preview Image -->
		        <img class="img-fluid rounded" src="<spring:url value="/upload/${post.postimage}"/>" style=" width:900px; height: 300px;">
		        <!-- <input type="file" class="img-fluid rounded" src="http://placehold.it/900x300">  -->
				
				<hr>
				
				<p>첨부파일:&nbsp; <a href="<c:url value="/download?fileName=${post.bimage}"/>"> ${post.bimage}</a></p>
				
		        <hr>
				
				
		        <!-- Post Content -->
		         <textarea class="form-control" rows="15" placeholder="글을 입력해주세요" name="bcontent" readonly="readonly"> ${post.bcontent}</textarea><br>
		     	
		     	<sec:authorize access="hasRole('ROLE_ADMIN')">
		     	
		     		<a href="PostModifyPage?bnum=${post.bnum}" class="btn btn-primary">글 수정</a>&nbsp;
		     		
		     		<a class="btn btn-primary" id="delBtn" href="Delpost?bnum=${post.bnum}">글 삭제</a>
		     		
				</sec:authorize>
				
				
		
		<!-- 포스트와 댓글 경계선 -->
               
        <hr>
        
        <!-- 댓글 작성 폼 -->
        
        <div class="card my-4">
          <h5 class="card-header">Comment </h5>
          <div class="card-body">
            
           <sec:authentication property="principal" var="user"/> <!--  시큐리티 로그인하면 나오는 값을 var user에 저장-->
           
           <!-- 댓글 데이터 전송 전 사용자  체크 -->
	           <c:choose>
	           	<c:when test="${pageContext.request.userPrincipal == null }"> <!-- 로그인을 안했다면 nickname = noUser가 됌 -->
	           		<input type="hidden" id="rnickname" name="nickname" value="noUser">	
	           	</c:when>
	           	
	           	<c:when test="${pageContext.request.userPrincipal != null }"> <!-- 로그인을 했다면 nickname 값은 제대로 전달됌 -->
	           		<!-- 사용자 권한이 체크되어야 하기때문에 null이 아니면 사용자 이름 및 닉네임 가져옴 -->
	           		<input type="hidden" id="rnickname" name="nickname" value="${user.member.nickname}">
	           		<input type="hidden" id="rusername" name="username" value="${pageContext.request.userPrincipal.name}">
	           	</c:when>
	           </c:choose>
	           
	           	<!-- 게시물 번호를 같이 저장 -->         
	            <input type="hidden" id="rnum" name="rnum" value="${post.breply}">
	              <div class="form-group">
	                <textarea class="form-control" id="rcomment" name="rcomment" rows="3" placeholder="로그인 후 댓글을 남겨주세요!"></textarea>
	              </div>
	              <button type="submit" id="replybutton" class="btn btn-primary">저장</button>
	            
           </div>
        </div>
		
		<!-- 댓글 부분 -->
       
        <c:forEach items="${reply}" var="reply"> <!-- 컨트롤러에서 전달받은 게시물을 for문으로 돌려 var reply에 저장 -->
        <div class="media mb-4">
          <div class="media-body">
            <h5 class="mt-0">${reply.nickname}</h5> <!-- 작성된 댓글에 nickname을 보여줌 -->
            ${reply.rcomment}<br><br> <!-- 작성된 댓글에 comment 보여줌 -->
            
           <!-- 대댓글  더 보기 버튼--> 
           	<button class="btn btn-info btn-sm" id="more" style="font-size: 15px;">답글보기</button><br> 
			
			<!-- 대댓글 내용 -->
            	<div class="reply" style="display: none;" >
            	
            		<c:forEach items="${reComment}" var="reco">
            			
            			<c:if test="${reco.cnum == reply.cnum}">
		            		<hr>
		            		<h5 class="mt-0">└ ${reco.nickname}</h5>
		            		&ensp;&ensp;${reco.recomment}
            			</c:if>
            		
            		</c:forEach>
            		
            		<br>
            		<hr>
            		<!-- 대댓글 작성 폼 -->
            			<sec:authentication property="principal" var="user"/> <!--  시큐리티 로그인하면 나오는 값을 var user에 저장-->
            			<!-- 로그인 한 유저와 안한 유저를 구분하기 위한 방법 / 다르게하면 오류뜸 -->
            			<c:choose>
            				
            				<c:when test="${pageContext.request.userPrincipal == null }">
            					<!-- 로그인 정보를 체크해 로그인 상태가 아니면 아이디 값에 노 로그인을 줌 -->
            					<input type="hidden" id="replynickname" value="noLogin">		
            				</c:when>
            				
            				<c:when test="${pageContext.request.userPrincipal != null }">
            				
            					<!-- 로그인 상태 체크해서 로그인 상태라면 nickname 가져옴. -->
            					<input type="hidden" id="replynickname" value="${user.member.nickname}">
            				</c:when>
            			
            			</c:choose>
            			
            			<input type="hidden" id="replynum" value="${reply.cnum}">
            			<input type="hidden" id="replypostnum" value="${post.breply}">
            			<input type="hidden" id="replyname" value="${pageContext.request.userPrincipal.name}"> 
            			<textarea  class="form-control" id="replycomment" rows="2" placeholder="로그인 후 작성해주세요."></textarea><br>
            			<button type="submit" id="recommentButton" class="btn btn-primary btn-sm">저장</button>
            	</div>	
            	

            <hr>
          </div>
        </div>
        </c:forEach>
      </div>
      
      <!-- Sidebar Widgets Column -->
      <div class="col-md-4">

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
            You can put anything you want inside of these side widgets. They are easy to use, and feature the new Bootstrap 4 card containers!
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



</body>
</html>