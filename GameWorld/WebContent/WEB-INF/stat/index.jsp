<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="gameList" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="langList" class="java.util.ArrayList" scope="request" />
<%@ page import="dto.LangDTO"%>
<%@ page import="dto.GameDTO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN">
<!-- **
 * 
 * @author Mikkel Barfred, Tristan Richard
 *
 * -->
<html>
<head>
<meta http-equiv="Content-type" content="text/html;charset=UTF-8">
<title>Statistics</title>
<link rel="stylesheet" href="../css/style.css">
<style> .span2{height:700px;} .span9{height:700px;}</style>
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
				<a class="btn btn-primary1 btn-large" href="../index.jsp?">Home</a>
				<a class="btn btn-primary1 btn-large" href="../login/logout.jsp">Logout</a>
			</div>
			<div class="span9">
				<!-- Main content -->
				<h2>Statistics</h2>
			<p>From here you can generate statistics!</p>
			<form method="POST" action="index.jsp">
			<table>
			<tr><td>Game</td><td>Sex</td><td>Language</td><td>From</td><td>To</td></tr>
			<tr>
			<td>
			<select name="statGame" style="width:150px">
								<option value=null>Any</option>
								<%
									for (int i = 0; i < gameList.size(); i++) {
										GameDTO g = (GameDTO) gameList.get(i);
								%>
								<option value=<%=g.getGid()%>><%=g.getGname()%></option>
								<%
									}
								%>
			</select></td>
			<td>
			<select name="statSex" style="width:80px">
								<option value=null>Any</option>
								<option value=1>Male</option>
								<option value=0>Female</option>
			</select>
			</td>
			<td>
			<select name="statLang" style="width:100px">
								<option value=null>Any</option>
								<%
									for (int i = 0; i < langList.size(); i++) {
										LangDTO l = (LangDTO) langList.get(i);
								%>
								<option value=<%=l.getLangid()%>><%=l.getLang()%></option>
								<%
									}
								%>
			</select>
			<td>
				<select name="statMin" style="width:75px">
								<option value=null>Any</option>
								<%
									for (int i = 0; i < 100; i++) {
								%>
								<option value=<%=i%>><%=i%></option>
								<%
									}
								%>
				</select>
			<td>
				<select name="statMax" style="width:75px">
								<option value=null>Any</option>
								<%
									for (int i = 0; i < 100; i++) {
								%>
								<option value=<%=i%>><%=i%></option>
								<%
									}
								%>
				</select>
			</td>
			<td colspan="2" align="right">
			<input type="hidden" name="action" value="countPlayers">
			<input class="btn btn-primary1 btn-large" type="submit" value="Get number of players"></td>
			</tr>
			</table>
			</form>
			
			<form method="POST" action="index.jsp">
			<table>
			<tr><td>Number</td></tr>
			<tr>			
			<td><select name="popular" style="width:75px">
								<%
									for (int i = 1; i < 11; i++) {
								%>
								<option value=<%=i%>><%=i%></option>
								<%
									}
								%>
				</select>
			</td>
			<td colspan="6" align="right">
			<input type="hidden" name="action" value="rankGames">
			<input class="btn btn-primary1 btn-large" type="submit" value="Most popular games">
			</td>
			</tr>
			</table>
			</form>
			
			
			<form method="POST" action="index.jsp">
			<table>
			<tr><td>Number</td></tr>
			<tr>			
			<td><select name="sexPopular" style="width:75px">
								<%
									for (int i = 1; i < 6; i++) {
								%>
								<option value=<%=i%>><%=i%></option>
								<%
									}
								%>
				</select>
			</td>
			<td colspan="6" align="right">
			<input type="hidden" name="action" value="rankSex">
			<input class="btn btn-primary1 btn-large" type="submit" value="Most popular games by sex">
			</td>
			</tr>
			</table>
			</form>
			
			
			<form method="POST" action="index.jsp">
			<table>
			<tr><td>Number</td><td>Sex</td><td>Language</td><td>From</td><td>To</td></tr>
			<tr>			
			<td><select name="popular" style="width:75px">
								<%
									for (int i = 1; i < 11; i++) {
								%>
								<option value=<%=i%>><%=i%></option>
								<%
									}
								%>
			</select></td>
			<td>
			<select name="statSex" style="width:80px">
								<option value=null>Any</option>
								<option value=1>Male</option>
								<option value=0>Female</option>
				</select>
				</td>
			<td><select name="statLang" style="width:100px">
								<option value=null>Any</option>
								<%
									for (int i = 0; i < langList.size(); i++) {
										LangDTO l = (LangDTO) langList.get(i);
								%>
								<option value=<%=l.getLangid()%>><%=l.getLang()%></option>
								<%
									}
								%>
			</select>
			</td>
			<td>
				<select name="statMin" style="width:75px">
								<option value=null>Any</option>
								<%
									for (int i = 0; i < 100; i++) {
								%>
								<option value=<%=i%>><%=i%></option>
								<%
									}
								%>
				</select>
			</td>
			<td>
				<select name="statMax" style="width:75px">
								<option value=null>Any</option>
								<%
									for (int i = 0; i < 100; i++) {
								%>
								<option value=<%=i%>><%=i%></option>
								<%
									}
								%>
				</select>
			</td>
			<td colspan="6" align="right">
			<input type="hidden" name="action" value="rankCountry">
			<input class="btn btn-primary1 btn-large" type="submit" value="Most popular by country">
			</td>
			</tr>
			</table>
			</form>
			<form method="POST" action="index.jsp">
			<table>
			<tr><td>Number</td></tr>
						
			<tr><td><select name="genrePopular" style="width:75px">
								<%
									for (int i = 1; i < 9; i++) {
								%>
								<option value=<%=i%>><%=i%></option>
								<%
									}
								%>
				</select>
			</td>
			<td colspan="6" align="right">
			<input type="hidden" name="action" value="rankGenre">
			<input class="btn btn-primary1 btn-large" type="submit" value="Most popular games by Genre">
			</td>
			</tr>
			</table>
			</form>	
			<form method="POST" action="index.jsp">
			<table>
			<tr><td>Number</td></tr>
						
			<tr><td><select name="genresexPopular" style="width:75px">
								<%
									for (int i = 1; i < 7; i++) {
								%>
								<option value=<%=i%>><%=i%></option>
								<%
									}
								%>
				</select>
			</td>
			<td colspan="6" align="right">
			<input type="hidden" name="action" value="rankGenreSex">
			<input class="btn btn-primary1 btn-large" type="submit" value="Most popular games by Genre and Sex">
			</td>
			</tr>
			</table>
			</form>
			
			</div>
		</div>
	</div>
	<%Boolean condition = (Boolean)request.getAttribute("graphBars");
	if(condition){ %><jsp:include page="graphBars.jsp"/><%}%>
	<%Boolean condition2 = (Boolean)request.getAttribute("graphSevBars");
	if(condition2){ %><jsp:include page="graphSevBars.jsp"/><%}%>
	<%Boolean condition3 = (Boolean)request.getAttribute("graphSevLines");
	if(condition3){ %><jsp:include page="graphSevLines.jsp"/><%}%>


	<div class="footer">Gruppe 8</div>
</body>
</html>
