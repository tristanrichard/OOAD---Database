package daoimpl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector.Connector;
import dto.RankDTO;
import dto.UsersLangDTO;
import daointerfaces.DALException;
import daointerfaces.StatIDAO;



public class MySQLStatDAO implements StatIDAO
{
	public List<RankDTO> rankGames(int maxResults) throws DALException
	{
		ArrayList<RankDTO> results = new ArrayList<RankDTO>();
		ResultSet rs = Connector.doQuery("SELECT Gname, Antal FROM Game NATURAL JOIN (SELECT COUNT(*) AS Antal, Gid FROM UsersGames GROUP BY Gid) AS C ORDER BY Antal DESC;");
		try
		{
			for(int i = 0; i < maxResults && rs.next(); i++)
				results.add(new RankDTO(rs.getString(1), rs.getInt(2)));
		} catch (SQLException e){throw new DALException(e);}
		return results;
	}
	public List<RankDTO> rankGamesG(int maxResults) throws DALException
	{
		ArrayList<RankDTO> results = new ArrayList<RankDTO>();
		ResultSet rs = Connector.doQuery("SELECT Gname, Antal FROM Game NATURAL JOIN (SELECT COUNT(*) AS Antal, Gid FROM UsersGames Natural Join Users where sex = '0' GROUP BY Gid) AS C ORDER BY Antal DESC;");
		try
		{
			for(int i = 0; i < maxResults && rs.next(); i++)
				results.add(new RankDTO(rs.getString(1), rs.getInt(2)));
		} catch (SQLException e){throw new DALException(e);}
		return results;
	}
	public List<RankDTO> rankGamesM(int maxResults) throws DALException
	{
		ArrayList<RankDTO> results = new ArrayList<RankDTO>();
		ResultSet rs = Connector.doQuery("SELECT Gname, Antal FROM Game NATURAL JOIN (SELECT COUNT(*) AS Antal, Gid FROM UsersGames Natural Join Users where sex = '1' GROUP BY Gid) AS C ORDER BY Antal DESC;");
		try
		{
			for(int i = 0; i < maxResults && rs.next(); i++)
				results.add(new RankDTO(rs.getString(1), rs.getInt(2)));
		} catch (SQLException e){throw new DALException(e);}
		return results;
	}
	public int countPlayers(int game, int sex, int language, String minDOB, String maxDOB) throws DALException
	{

		String query = "SELECT COUNT(*) FROM Users u JOIN Role r ON (u.Email = r.Email)" +
			(language == -1 ? "" : " JOIN UsersLang ul ON (ul.email = u.email)") + 
			" WHERE r.Role != 'game'" +
			(language == -1 ? "" : " AND ul.Langid = '" + language + "'") + 
			(minDOB == null ? "" : " AND u.DOB >= '" + minDOB + "'") +
			(maxDOB == null ? "" : " AND u.DOB <= '" + maxDOB + "'") +
			(sex == -1 ? "" : " AND u.Sex = '" + sex + "'") +
			(game == -1 ? "" : " AND EXISTS (SELECT * FROM UsersGames ug WHERE (ug.email = u.email) AND ug.Gid = '" + game + "')") + 
			";";
		ResultSet rs = Connector.doQuery(query);
		try
		{
			rs.last();
			return rs.getInt(1);
		} catch(SQLException e){throw new DALException(e);}
	}

	
}
