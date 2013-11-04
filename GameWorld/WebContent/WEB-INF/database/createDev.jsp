<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="langList" class="java.util.ArrayList" scope="request" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN">
<html>
<head>
<meta http-equiv="Content-type" content="text/html;charset=UTF-8">
<title>Developer Administration</title>
<link rel="stylesheet" href="../css/style1.css">

</head>

<body>
	<div id="messagecontainer">
			<div class="error">${error}</div>
			<div class="message">${message}</div>
	</div>

	<div id="container">

		<div id="navigation">
			<!-- Site navigation menu -->
			<ul class="navbar">
				<li><a href="index.jsp?">Back</a>
				<li><a href="index.jsp?action=listDev">List Developer</a>
				<hr>
				<li><a href="../login/logout.jsp">Logout</a>
			</ul>
		</div>
		<div id="main">
			<!-- Main content -->
			<h1>Create Developer</h1>
			<form method="POST" action="index.jsp">
				<!-- This is for creating -->
				<table>
					<tr>
						<td>Developer:</td>
						<td><input type="text" name="newDev" required
							value="<%if (request.getParameter("newDev") != null)
				out.print(request.getParameter("newDev"));%>"
							onclick="this.select()"></td>
					</tr>
					<tr>
						<td>Country:</td>
						<td><input type="text" name="newFName" required
				value="<% if (request.getParameter("newCon") != null)
				out.print(request.getParameter("newCon"));%>"
				onclick="this.select()"></td>
					</tr>
					<tr>
						<td colspan="2" align="right"><input type="hidden"
							name="action" value="devFilled"><input type="submit"
							value="Create Developer"></td>
						<td></td>
					</tr>
				</table>
			</form>
		</div>
		<div class="footer">Gruppe 8</div>
	</div>
</body>
</html>