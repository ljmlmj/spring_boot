<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<!-- Inline Style Sheet 인라인 스타일 시트 -->
	<style>
		table, th, td {
			border: 1px solid black;
	</style>
</head>

<body>

<h1 class="mt-4">Member Details</h1>

	<h1 style="text-align:center;">수정</h1>

	<div>
		<form:form modelAttribute="employee"
				   method="POST"
		  		   action="${pageContext.request.contextPath}/emp/modify">
			<table>
				<colgroup>
					<col width="10%" />
					<col width="90%" />
				</colgroup>
				<tbody>
					<tr>
						<th align="center">firstName</th>
						<td>
							<form:input path="firstName" maxlength="20" size="25" />
						</td>
					</tr>
					<tr>
						<th align="center">lastName</th>
						<td>
							<form:input path="lastName" maxlength="20" size="25" />
						</td>
					</tr>
					<tr>
						<th align="center">email</th>
						<td>
							<form:input path="email" maxlength="20" size="25" />
						</td>
					</tr>
					<tr>
						<th align="center">phoneNumber</th>
						<td>
							<form:input path="phoneNumber" maxlength="20" size="25" />
						</td>
					</tr>			
					<tr>
						<th align="center">jobId</th>
						<td>
							<form:input path="jobId" maxlength="20" size="25" />
						</td>
					</tr>
					<tr>
						<th align="center">salary</th>
						<td>
							<form:input type="number" path="salary" />
						</td>
					</tr>	
					<tr>
						<th align="center">commissionPct</th>
						<td>
							<form:input path="commissionPct" maxlength="20" size="25" />
						</td>
					</tr>					
					<tr>
						<th align="center">managerId</th>
						<td>
							<form:input path="managerId" maxlength="20" size="25" />
						</td>
					</tr>					
					<tr>
						<th align="center">departmentId</th>
						<td>
							<form:select path="departmentId" items="${employee.departmentList}" 
								itemValue="departmentId" itemLabel="departmentName" />
						</td>
					</tr>					
				</tbody>
			</table>
			
			<p class="btn_align">
				<input type="submit" value="저장" />
				<input type="button" value="목록" onclick="location.href='${pageContext.request.contextPath}/emp/list'" />
			</p>
		</form:form>	
	</div>	
</body>
</html>
