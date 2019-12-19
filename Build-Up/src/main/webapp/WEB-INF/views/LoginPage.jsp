<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>

	<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	
	<link href="<c:url value="/resources/loginForm.css"/>" rel="stylesheet">

<meta charset="EUC-KR">
	<title>Home</title>
</head>
<body>
	<div id="login">
        <h3 class="text-center text-white pt-5">Welcome to Woodie's blog</h3>
        <div class="container">
            <div id="login-row" class="row justify-content-center align-items-center">
                <div id="login-column" class="col-md-6">
                    <div id="login-box" class="col-md-12">
                    
                    <!-- Form 태그 -->
                    
                        <form id="login-form" class="form" action="<c:url value="/login"/>" method="post">
                            <h3 class="text-center text-info">Login</h3>
                            <div class="form-group">
                                <label for="username" class="text-info">아이디</label><br>
                                <input type="text" name="username" id="username" class="form-control">
                            </div>
                            <div class="form-group">
                                <label for="password" class="text-info">패스워드</label><br>
                                <input type="password" name="password" id="password" class="form-control">
                            </div>
                            <br>
                            <c:if test="${error == -1 }">
                            	<div style="color: red;">아이디 또는 비밀번호를 확인해주세요</div>
                            </c:if>
                            <br>
                            <div class="form-group">
                                <input type="submit" name="submit" class="btn btn-info btn-md" value="로그인">                               
                            </div>
                            <div id="register-link" class="text-right">
                                <br>
                                <a href="join" class="text-info">회원가입</a>  
                            </div>
                            <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token}">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</body>
</html>
