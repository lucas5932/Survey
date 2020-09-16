<%@page import="svy.surveyDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<table border = "1">
	<tr>
		<th>번호</th>
		<th>이름</th>
		<th>소속 회사</th>
		<th>이메일 주소</th>
		<th>과정 만족도</th>
		<th>관심분야</th>
		<th>정보 발송 방법</th>
		<th>정보 발송 동의</th>
		<th>삭제</th>
		<th>수정</th>
	</tr>
	<c:forEach var = "svy" items="${lists}">
		<tr>
			<td>${svy.no }</td>
			<td>${svy.name }</td>
			<td>${svy.company }</td>
			<td>${svy.email }</td>
			<td>${svy.satisfaction }</td>
			<td>${svy.part }</td>
			<td>${svy.howto }</td>
			<td>
				<c:if test="${svy.agree == 1 }">동의함</c:if>
				<c:if test="${svy.agree == 0 }">동의 거부</c:if>
			</td>
			<td><a href="delete.sv?no=${svy.no }">삭제</a></td>
			<td><a href="updateForm.sv?no=${svy.no }">수정</a></td>
		</tr>
	</c:forEach>

</table>
<a href="Ex02_SurveyInputForm.jsp">삽입</a>