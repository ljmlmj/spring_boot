<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

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

	<table class="table">
			<tr>
                            <th>Employee ID</th>
                            <td>${employee.employeeId}</td>
                        </tr>
                        <tr>
                            <th>First Name</th>
                            <td>${employee.firstName}</td>
                        </tr>
                        <tr>
                            <th>Last Name</th>
                            <td>${employee.lastName}</td>
                        </tr>
                        <tr>
                            <th>Email</th>
                            <td>${employee.email}</td>
                        </tr>
                        <tr>
                            <th>Phone Number</th>
                            <td>${employee.phoneNumber}</td>
                        </tr>
                        <tr>
                            <th>Hire Date</th>
                            <td>${employee.hireDate}</td>
                        </tr>
                        <tr>
                            <th>Job ID</th>
                            <td>${employee.jobId}</td>
                        </tr>
                        <tr>
                            <th>Salary</th>
                            <td>${employee.salary}</td>
                        </tr>
                        <tr>
                            <th>Commission Pct</th>
                            <td>${employee.commissionPct}</td>
                        </tr>
                        <tr>
                            <th>Manager ID</th>
                            <td>${employee.managerId}</td>
                        </tr>
                        <tr>
                            <th>Department ID</th>
                            <td>${employee.departmentId}</td>
                        </tr>
                        <tr>
                        	<td colspan="2" style="text-align:center">
							<input type="button" id="btnModify" class='btn btn-info' onclick="location.href='<c:url value="/emp/modify?id=${employee.employeeId }" />'" value="수정" />
	                        <input type="button" id="btnList" class='btn btn-info' onclick="location.href='<c:url value="/emp/list" />'" value="목록" />							
							<input type="button" id="btnDelete" class='btn btn-info' onclick="location.href='<c:url value="/emp/delete" />'" value="삭제" />
							</td>

                        </tr>
	</table>
</body>
</html>
