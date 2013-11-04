<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="userList" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="compList" class="java.util.ArrayList" scope="request" />
<%@ page import="dto.UsersDTO"%>
<%@ page import="dto.PublisherDTO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN">
<html>
<head>
<meta http-equiv="Content-type" content="text/html;charset=UTF-8">
<title>User Administration</title>
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
					<hr>
				<li><a href="index.jsp?action=createPublisher">Add
						Publisher</a>
				<li><a href="index.jsp?action=listUsers">List Users</a>
					<hr>
				<li><a href="../login/logout.jsp">Logout</a>
			</ul>
		</div>
		<div id="main">
			<!-- Main content -->
			<h1>Registered publishers</h1>
			<form method="POST" action="index.jsp">
				<!-- This is for creating -->
				<table class="list">
					<%
						for (int i = 0; i < userList.size(); i++) {
							UsersDTO user = (UsersDTO) userList.get(i);
							PublisherDTO comp = (PublisherDTO) compList.get(i);
					%>
					<tr>
						<td><%=user.getFname() + " " + user.getLname()%></td>
						<td><%=comp.getPublisher()%></td>
						<td><a
							href="index.jsp?action=updateUser&amp;userToUpdate=<%=user.getEmail()%>">Edit</a></td>
					</tr>
					<%
						}
					%>
				</table>
			</form>
		</div>
		<div class="footer">Gruppe 8</div>
	</div>
</body>
</html>