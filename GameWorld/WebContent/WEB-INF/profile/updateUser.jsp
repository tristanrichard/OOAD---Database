<jsp:useBean id="user1" class="dto.UsersDTO" scope="request" />
<jsp:useBean id="role" class="dto.RoleDTO" scope="request" />
<jsp:useBean id="userLang" class="dto.UsersLangDTO" scope="request" />
<jsp:useBean id="langList" class="java.util.ArrayList" scope="request" />
<%@ page import="dto.LangDTO"%>
<%@ page import="dto.UsersDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN">
<!-- **
 * 
 * @author Tristan Richard
 *
 * -->
<html>
<head>
<meta http-equiv="Content-type" content="text/html;charset=UTF-8">
<title>My Profile</title>
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
				<a class="btn btn-primary1 btn-large" href="../login/logout.jsp">Logout</a>
			</div>
			<div class="span9">
				<!-- Main content -->
				<h2>My Profile</h2>
				<p>From here you can update your profile</p>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="span13">
			<h1>Update User</h1>
			<script>
				function confirmComplete() {
					var answer = confirm("Are you sure you deactivate your profile?");
					if (answer == true) {
						window.location.href = "index.jsp?action=deactivateUser";
					} else {
						return false;
					}
				}
			</script>
			<form method="POST" action="index.jsp">
				<!-- This is for Updating -->
				<table>
					<tr>
						<td>First Name:</td>
						<td><input type="text" name="newFName" required="required"
							value="<%=user1.getFname()%>" onclick="this.select()"></td>
					</tr>
					<tr>
						<td>Last Name:</td>
						<td><input type="text" name="newLName" required="required"
							value="<%=user1.getLname()%>" onclick="this.select()"></td>
					</tr>
					<tr>
						<td>email:</td>
						<td><input type="email" name="newUserEmail"
							required="required" value="<%=user1.getEmail()%>"
							onclick="this.select()"></td>
					</tr>
					<tr>
						<td>Date of Birth:</td>
						<td><input type="text" name="newUserBirth"
							required="required" value="<%=user1.getDob()%>"
							onclick="this.select()"></td>
					</tr>
					<tr>
						<td>Sex:</td>
						<td><select name="newUserSex">
								<option value=1
									<%if (user1.getSex()) {
				out.print("selected");
			}%>>Male</option>
								<option value=0
									<%if (!user1.getSex()) {
				out.print("selected");
			}%>>Female</option>
						</select></td>
						<td>Language:</td>
						<td><select name="newUserLang">
								<%
									for (int i = 0; i < langList.size(); i++) {
										LangDTO l = (LangDTO) langList.get(i);
								%>
								<option value=<%=l.getLangid()%>
									<%if (l.getLangid() == userLang.getLangid()) {
					out.print("selected");
				}%>><%=l.getLang()%></option>
								<%
									}
								%>
						</select></td>
					</tr>
					<tr>
						<td>Old Password:</td>
						<td><input type="password" name="oldPass" required="required"
							value="" onclick="this.select()"></td>
					</tr>
					<tr>
						<td>New Password:</td>
						<td><input type="password" name="updOprPass1"
							required="required" value="" onclick="this.select()"></td>
					</tr>
					<tr>
						<td>New Password Again:</td>
						<td><input type="password" name="updOprPass2"
							required="required" value="" onclick="this.select()"></td>
						<td></td>
					</tr>
					<tr>
						<td colspan="2" align="right">
						<input type="hidden" name="action" value="updateOprFilled"> 
						<input class="btn btn-primary1 btn-large" type="submit" value="Update Profile"></td>
						<td colspan="4" align="right">
						<input class="btn btn-primary1 btn-large" type="button" value="Deactivate Profile" onclick="{return confirmComplete();}"></td>
					</tr>
				</table>
			</form>
			<p>The password needs to contain between 7 and 8 characters of at
				least three of the following four categories: small letters ('a' -
				'z'), capital letters ('A' - 'Z'), digits ('0' - '9') and any of the
				following special characters: ('.', '-', '_', '+', '!', '?', '=').</p>
		</div>
		<div class="footer">Gruppe 8</div>
	</div>
</body>
</html>