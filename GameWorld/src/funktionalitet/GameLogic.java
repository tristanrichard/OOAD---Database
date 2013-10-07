package funktionalitet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import connector.Connector;
import daoimpl.MySQLGameDAO;
import daoimpl.MySQLGameGenreDAO;
import daoimpl.MySQLGameLangDAO;
import daoimpl.MySQLGameOSDAO;
import daoimpl.MySQLGamePubDAO;
import daoimpl.MySQLUserPubDAO;
import daoimpl.MySQLUsersGamesDAO;
import daointerfaces.DALException;
import daointerfaces.GameGenreIDAO;
import daointerfaces.GameIDAO;
import daointerfaces.GameLangIDAO;
import daointerfaces.GameOSIDAO;
import daointerfaces.GamePubIDAO;
import daointerfaces.UserPubIDAO;
import daointerfaces.UsersGamesIDAO;
import dto.GameDTO;
import dto.GameGenreDTO;
import dto.GameLangDTO;
import dto.GameOSDTO;
import dto.GamePubDTO;
import dto.UserPubDTO;
import dto.UsersGamesDTO;

public class GameLogic implements IGameLogic {

	private GameIDAO ga;
	private UserPubIDAO gaPub;
	private GameLangIDAO gaLa;
	private GameGenreIDAO gaGen;
	private GameOSIDAO gaOs;
	private GameDTO gameDTO;
	private GamePubDTO row;
	private GamePubIDAO myGames;

	public GameLogic() throws DALException {
		try {
			new Connector();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ga = new MySQLGameDAO();
		gaLa = new MySQLGameLangDAO();
		gaGen = new MySQLGameGenreDAO();
		gaOs = new MySQLGameOSDAO();
		myGames = new MySQLGamePubDAO();
		gaPub = new MySQLUserPubDAO();

	}
	public void createGame(String title, String release, String url, String[] genre, String[] language, String[] os, String email, String dev, String pub) throws DALException {
		try{
			ga.getByTitle(title);
		}catch (DALException e){
			String passedUrl = processUrl(url);
			GameDTO newGame = new GameDTO(0, title, release, passedUrl);
			ga.create(newGame);
			gameDTO = ga.getByTitle(title);
			int Gid = gameDTO.getGid();
			UserPubDTO pu = gaPub.get(email);
			int Pid = pu.getPid();
			row = new GamePubDTO(Pid, Gid);
			myGames.create(row);
			for (String i : genre) {
				int j = Integer.parseInt(i);
				GameGenreDTO temp = new GameGenreDTO(Gid,j);
				gaGen.create(temp);
			}
			for (String i : language) {
				int j = Integer.parseInt(i);
				GameLangDTO temp = new GameLangDTO(Gid,j);
				gaLa.create(temp);
			}
			for (String i : os) {
				int j = Integer.parseInt(i);
				GameOSDTO temp = new GameOSDTO(Gid,j);
				gaOs.create(temp);
			}
			return;
		}
		throw new DALException(title + " already exists in our database!");
	}
	public void editGame(String title, String release, String url, String[] genre, String[] language, String[] os)
			throws DALException {
		// TODO Auto-generated method stub

	}
	public List<GameDTO> listGames(String email) throws DALException {
		List<GamePubDTO> ourGames = new ArrayList<GamePubDTO>();
		List<GameDTO> games = new ArrayList<GameDTO>();
		UserPubDTO pub = gaPub.get(email);
		int Pid = pub.getPid();
		try 
		{
			ourGames = myGames.getList(Pid);
			for (int i= 0; i< ourGames.size(); i++)
			{
				int a = ourGames.get(i).getGid();
				games.add(ga.getById(a));
			}
			return games;
		} catch (DALException e){ throw new DALException("You haven't registrered any games yet. Please do so.");}
	}
	private String processUrl(String url) throws DALException {
		String tempUrl = null;
		Pattern pURL = Pattern.compile("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
		Matcher m = pURL.matcher(url);
		if (m.find()) {
			tempUrl = url;
		} else {
			throw new DALException("Invalid email Url.");
		}
		return tempUrl;
	}

}
