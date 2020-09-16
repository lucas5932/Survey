<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
Ex02_surveyUpdate.jsp <br><br>

<!-- insert.sv => svy\SurveyServlet.java -->
<form action="update.sv" method="post"> 
	 과정 만족도 설문<br>
	 아래 항목을 입력해 주세요.<P>
	
	 <b>개인 정보 :</b><br>
	 <input type="hidden" name="no" value="${sdto.no }"><br>
	이름 　　　　<input type="text" name="name" value="${sdto.name }"><br>
	소속 회사　  <input type="text" name="company" value="${sdto.company }"><br>
	이메일 주소　<input type="text" name="email" value="${sdto.email }"><br>
	<p>
	<b>본 교육 과정을 수강하고 과정에 만족도를 선택해 주세요.</b><br>
	<% String[] sat = {"매우만족","만족","보통","불만족","매우불만족"}; 
		pageContext.setAttribute("sat",sat);
	%>
	<c:forEach var="i" begin="0" end="${fn:length(sat) -1}" step="1">
		<c:if test="${sdto.satisfaction == sat[i]}">
			<input type="radio" name="satisfaction" value="${sat[i]}" checked="checked" >${sat[i]}
		</c:if>
		<c:if test="${sdto.satisfaction != sat[i]}">
			<input type="radio" name="satisfaction" value="${sat[i]}">${sat[i]}
		</c:if>
	</c:forEach>
	
	<p>
	<%
		String[] part = {"JAVA","Spring","UML","JDBC","서블릿","JSP"};
		pageContext.setAttribute("part",part);
	%>
	<b>관심있는 분야는 무엇입니까?</b><br>
	<c:forEach var="i" begin="0" end="${fn:length(part) -1 }" step="1" varStatus="status">
		<c:if test="${fn:contains(sdto.part,part[i])}">
			<input type="checkbox" name="part" value="${part[i]}" checked="checked">${part[i]}
		</c:if> 
		<c:if test="${not fn:contains(sdto.part,part[i])}">
			<input type="checkbox" name="part" value="${part[i]}">${part[i]}
		</c:if>	
		<c:if test="${(i+1)%3 == 0 }"><br></c:if>	
	</c:forEach>
	<p>
	정보 발송 방법 
	<%
		String[] how = {"email","우편"};
		pageContext.setAttribute("how",how);
	%>
	<select name="howto">
	<c:forEach var="i" begin="0" end="${fn:length(how)-1 }" step="1">
		<c:if test="${sdto.howto == how[i]}">
			<option value="${how[i]}" selected>${how[i]}
		</c:if>
		<c:if test="${sdto.howto != how[i]}">
			<option value="${how[i]}">${how[i]}
		</c:if>
	</c:forEach>
	</select>
	
	<p>
	<c:choose>
		<c:when test="${sdto.agree == 1 }">
			<input type="checkbox" name="agree" value="1" checked> 
		</c:when>
		<c:otherwise>
			<input type="checkbox" name="agree" value="1"> 
		</c:otherwise>
	</c:choose>
	정보 발송에 동의 합니다.<br>
	<input type="submit" value="전송">
</form>
