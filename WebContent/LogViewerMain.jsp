<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList, com.model.LogFile"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
</head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Log Viewer Console 1.0</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet" href="css/w3.css">
<link rel="stylesheet" href="css/font-awesome.min.css">
<script type="text/javascript" src="js/logviewer.js"></script>
<style>
html, body, h1, h2, h3, h4, h5 {
	font-family: "Raleway", sans-serif
}
</style>

<body>
	<table style="width: 100%" id="Header">
		<tr>
			<td align=center><H1 ALIGN=CENTER>Log Viewer Console</H1></td>
		</tr>
	</table>
	<form method="post" action="LogViewerConsole">

		<table>
			<tr>
				<td align=left>Enter Host Address:</td>
				<td align=left><input type="text" name="url" value="${url}"></td>
			</tr>
			<tr>
				<td align=left>Enter Directory:</td>
				<td align=left><input type="text" name="dir" value="${dir}"></td>
			</tr>

			<c:if test="${not empty requestScope.logList}">
				<tr>
					<td align=left>Select log file:</td>
					<td align=left><select id="logfilelist" name="logfilelist">
							<c:set var="count" value="0" scope="page" />
							<c:forEach items="${logList}" var="log">
								<option id="${count}" value="${log.name}" ${log.selected}>${log.name}</option>
								<c:set var="count" value="${count + 1}" scope="page" />
							</c:forEach>
					</select></td>
				</tr>
			</c:if>
		</table>
		<br>
		<table style="float: left">
			<tr>
				<td align="center"><input type="submit" value="Submit"></td>
			</tr>
		</table>
		<c:if test="${not empty requestScope.logContent}">
			<table style="float: right">
				<tr>
					<td align="center"><input type="button"
						onclick="location.href='DocumentServletController';"
						value="Download" /></td>
				</tr>
			</table>
			<br>
			<br>
			<table style="float: center">
				<tr>
					<td>
						<div
							style="background-color: #000000; margin-left: 100px; width: 1000px; height: 400px; white-space: nowrap; overflow: scroll;">
							<font face="verdana" size="1" color="#0CFE42"> <c:out
									value="${logContent}" escapeXml="false" />
							</font>
						</div>
					</td>
				</tr>
			</table>
		</c:if>
	</form>
</body>
</html>