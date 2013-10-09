<%@include file="header.jsp"%>
<jsp:useBean id="userList" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="gameUrl" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="idList" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="roles" class="java.util.ArrayList" scope="request" />
<%@ page import="dto.UsersDTO" %>
<h1>User List</h1>
<form method="POST" action="index.jsp">
	<!-- This is for creating -->
	<table class="list">
		<% for (int i = 0; i< userList.size(); i++) {
		UsersDTO user = (UsersDTO) userList.get(i); %>
		<tr><td> <a href="index.jsp?action=updateUser&amp;userToUpdate=<%= user.getEmail() %>"><%= user.getFname()+" "+user.getLname() %></a></td><td><%= user.getEmail() %></td>
		<td><% if (!roles.get(i).toString().equalsIgnoreCase("inactive") && !roles.get(i).toString().equalsIgnoreCase("inactivePub")){ %>
		<a href="index.jsp?action=deactivateUser&amp;userToDeactivate=<%= user.getEmail() %>">Deactivate</a><%} else out.print("Deactivated");%>
		</tr></td>
		<%}%>

	</table>
</form>
<%@include file="footer.jsp"%>