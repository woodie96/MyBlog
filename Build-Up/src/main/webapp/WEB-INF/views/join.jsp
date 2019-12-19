<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>

<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link href="<c:url value="/resources/joinForm.css"/>"rel="stylesheet">


<script type="text/javascript" src="<c:url value="/resource/jquery-3.4.1.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/Join.js"/>"></script>

<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

	<form id="form" action="joinConfirm" method="post">
	<h1>Sign Up</h1>
		<input type="hidden" name="authority" value="ROLE_MEMBER">
		  <label>
		    <p class="label-txt">���̵�</p>
		    <input type="text" name="userid" id="userid" class="input">
		    <div class="line-box">
		      <div class="line" id="idchbox"></div>
		    </div>
		  </label>
		
		  <label>
		    <p class="label-txt">����� �̸�</p>
		    <input type="text" name="username" id="username" class="input">
		    <div class="line-box">
		      <div class="line"></div>
		    </div>
		  </label>
		  <label>
		    <p class="label-txt">����</p>
		    <input type="text" name="nickname" id="nickname" class="input" placeholder="*������ �����ϰ� �������ּ���">
		    <div class="line-box">
		      <div class="line"></div>
		    </div>
		  </label>
		  <label>
		    <p class="label-txt">�н�����</p>
		    <input type="password" name="userpw" id="userpw" class="input">
		    <div class="line-box">
		      <div class="line"></div>
		    </div>
		  </label>
		  
		  <label>
		    <p class="label-txt">�н����� Ȯ��</p>
		    <input type="password" name="userpwch" id="userpwch" class="input">
		    <div class="line-box">
		      <div class="line"></div>
		    </div>
		  </label>
		  
		  <label>
		    <p class="label-txt">�̸���</p>
		    <input type="email" name="email" id="email" class="input">
		    <div class="line-box">
		      <div class="line"></div>
		    </div>
		  </label>
		  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token }">
		  <button type="submit" id="submit" class="btn btn-info btn-md">ȸ������</button>
	</form>
</body>
</html>