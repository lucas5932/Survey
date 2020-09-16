<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
Ex02_surveyInputForm.jsp <br><br>

<!-- insert.sv => svy\SurveyServlet.java -->
<%
	application.setAttribute("send", false);
%>
<form action="insert.sv" method="post"> 
	 과정 만족도 설문<br>
	 아래 항목을 입력해 주세요.<P>
	
	 <b>개인 정보 :</b><br>
	이름 　　　　<input type="text" name="name" value="홍길동"><br>
	소속 회사　  <input type="text" name="company" value="갑을"><br>
	이메일 주소　<input type="text" name="email" value="dd@cc.com"><br>
	<p>
	<b>본 교육 과정을 수강하고 과정에 만족도를 선택해 주세요.</b><br>
	<input type="radio" name = "satisfaction" value="매우만족" checked="checked">매우 만족 
	<input type="radio" name = "satisfaction" value="만족">만족
	<input type="radio" name = "satisfaction" value="보통">보통
	<input type="radio" name = "satisfaction" value="불만족">불만족
	<input type="radio" name = "satisfaction" value="매우불만족">매우불만족
	<p>
	<b>관심있는 분야는 무엇입니까?</b><br>
	<input type="checkbox" name = "part" value="JAVA">JAVA
	<input type="checkbox" name = "part" value="Spring">Spring
	<input type="checkbox" name = "part" value="UML" checked="checked">UML<br>
	<input type="checkbox" name = "part" value="JDBC">JDBC
	<input type="checkbox" name = "part" value="서블릿">서블릿
	<input type="checkbox" name = "part" value="JSP" checked="checked">JSP<br>
	<p>
	정보 발송 방법 <select name="howto">
		<option value="email">email
		<option value="우편" selected="selected">우편
		</select>
	
	<p>
	<input type="checkbox" name="agree" checked="checked" value="1"> 정보 발송에 동의합니다.<br>
	<input type="submit" value="전송">
	
</form>
