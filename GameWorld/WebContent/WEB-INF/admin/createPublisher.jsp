<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="langList" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="pubList" class="java.util.ArrayList" scope="request" />
<%@ page import="dto.LangDTO"%>
<%@ page import="dto.PublisherDTO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN">
<!-- **
 * 
 * @author Tristan Richard, Thordur Birgisson
 *
 * -->
<html>
<head>
<meta http-equiv="Content-type" content="text/html;charset=UTF-8">
<title>User Administration</title>
<link rel="stylesheet" href="../css/style.css">

</head>

<body>
	<div class="container">
		<h1 id = "headerh1">GameWorld</h1>
		<div id="messagecontainer">
			<div class="error">${error}</div>
			<div class="message">${message}</div>
		</div>
		<div class="row">
			<div class="span2">
				<a class="btn btn-primary1 btn-large" href="index.jsp?">Back</a> 
				<a class="btn btn-primary1 btn-large" href="index.jsp?action=listPublisher">List Publisher</a> 
				<a class="btn btn-primary1 btn-large" href="index.jsp?action=listUsers">List Users</a> 
				<a class="btn btn-primary1 btn-large" href="../login/logout.jsp">Logout</a>
			</div>
			<div class="span9">
				<!-- Main content -->
				<h2>User administration</h2>
				<p></p>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="span13">
				<h1>Add Publisher</h1>
				<form method="POST" action="index.jsp">
					<!-- This is for creating -->
					<table>
						<tr>
							<td>First Name:</td>
							<td><input type="text" name="newFName" required
								value="<%if (request.getParameter("newFName") != null)
				out.print(request.getParameter("newFName"));%>"
								onclick="this.select()"></td>
							<td>Last Name:</td>
							<td><input type="text" name="newLName" required
								value="<%if (request.getParameter("newLName") != null)
				out.print(request.getParameter("newLName"));%>"
								onclick="this.select()"></td>
						</tr>
						<tr>
							<td>Email:</td>
							<td><input type="email" name="newUserEmail"
								value="<%if (request.getParameter("newUserEmail") != null)
				out.print(request.getParameter("newUserEmail"));%>"
								onclick="this.select()"></td>
						</tr>
						<td>Date of Birth:</td>
						<td><input type="text" name="newUserBirth"
							value="<%if (request.getParameter("newUserBirth") != null)
				out.print(request.getParameter("newUserBirth"));
			else {%>DD-MM-YYYY <%}%>"
							onclick="this.select()"></td>
						<td class="expl"></td>
						</tr>
						<tr>
							<td>Sex:</td>
							<td><select name="newUserSex">
									<option value=1>Male</option>
									<option value=0>Female</option>
							</select></td>
							<td>Language:</td>
							<td><select name="newUserLang">
									<%
										for (int i = 0; i < langList.size(); i++) {
											LangDTO l = (LangDTO) langList.get(i);
									%>
									<option value=<%=l.getLangid()%>><%=l.getLang()%></option>
									<%
										}
									%>
							</select></td>
							<td>Company:</td>
							<td><select name="newPub" required>
									<%
										for (int i = 0; i < pubList.size(); i++) {
											PublisherDTO p = (PublisherDTO) pubList.get(i);
									%>
									<option value=<%=p.getPid()%>><%=p.getPublisher()%></option>
									<%
										}
									%>
							</select></td>
						</tr>
						<tr>
							<td colspan="2" align="right"><input type="hidden"
								name="action" value="publisherFilled"><input
								class="btn btn-primary1 btn-large" type="submit" value="Create Publisher"></td>
							<td></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div class="footer">Gruppe 8</div>
	</div>
</body>
</html>