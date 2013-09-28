<%@include file="header.jsp"%>
<h1>Create User</h1>
<form method="POST" action="index.jsp">
	<!-- This is for creating -->
	<table>
		<tr>
			<td>First Name:</td>
			<td><input type="text" name="newFName" required
				value="<% if (request.getParameter("newFName") != null)
				out.print(request.getParameter("newFName"));%>"
				onclick="this.select()"></td>
			<td>Last Name:</td>
			<td><input type="text" name="newLName" required
				value="<% if (request.getParameter("newLName") != null)
				out.print(request.getParameter("newLName"));%>"
				onclick="this.select()"></td>
		</tr>
	<tr>
			<td>Email:</td>
			<td><input type="email" name="newUserEmail"
				value="<% if (request.getParameter("newUserEmail") != null)
				out.print(request.getParameter("newUserEmail"));%>"
				onclick="this.select()"></td>
			<td class="expl">name@mail.com.</td>
		</tr>
			<td>Date of Birth:</td>
			<td><input type="text" name="newUserBirth"
				value="<% if (request.getParameter("newUserBirth") != null)
				out.print(request.getParameter("newUserBirth"));
			else {%>DD-MM-YYYY <%}%>"
				onclick="this.select()"></td>
			<td class="expl"></td>
		</tr>
		<tr>
			<td>Sex:</td>
			<td><select name="newUserSex">
					<option value= 1 >Male</option>
					<option value= 0 >Female</option>
			</select></td>
			<td>
		</tr>
		<tr>
			<td colspan="2" align="right"><input type="hidden" name="action"
				value="userFilled"><input type="submit"
				value="Create Operator"></td>
			<td></td>
		</tr>
	</table>
</form>
<%@include file="footer.jsp"%>