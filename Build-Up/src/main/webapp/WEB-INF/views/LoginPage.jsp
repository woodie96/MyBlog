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
                    
                    <!-- Form �±� -->
                    
                        <form id="login-form" class="form" action="<c:url value="/login"/>" method="post">
                            <h3 class="text-center text-info">Login</h3>
                            <div class="form-group">
                                <label for="username" class="text-info">���̵�</label><br>
                                <input type="text" name="username" id="username" class="form-control">
                            </div>
                            <div class="form-group">
                                <label for="password" class="text-info">�н�����</label><br>
                                <input type="password" name="password" id="password" class="form-control">
                            </div>
                            <br>
                            <c:if test="${error == -1 }">
                            	<div style="color: red;">���̵� �Ǵ� ��й�ȣ�� Ȯ�����ּ���</div>
                            </c:if>
                            <br>
                            <div class="form-group">
                                <input type="submit" name="submit" class="btn btn-info btn-md" value="�α���">                               
                            </div>
                            <div id="register-link" class="text-right">
                                <br>
                                <a href="join" class="text-info">ȸ������</a>  
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
