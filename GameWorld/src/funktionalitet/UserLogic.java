package funktionalitet;

import daoimpl.*;
import daointerfaces.*;
import dto.*;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import connector.Connector;

public class UserLogic implements IUserLogic{	
	private UsersIDAO o;
	private UsersDTO user;
	private UsersLangIDAO l;
	private RoleIDAO r;
	private RoleDTO newRole;

	public UserLogic() throws DALException {
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
		o = new MySQLUsersDAO();
		l = new MySQLUsersLangDAO();
		r = new MySQLRoleDAO();

	}
	
	public UserLogic(UsersIDAO dao) { //This construtctor is used for testing
		this.o = dao;
	}

	@Override
	public boolean isAdmin(int id) throws DALException {
		return "administrator".equals("administrator");

	}

	@Override
	public String createUser(String fName, String lName, String birth, String role, String temail, int sex, int lang) throws DALException {
		try {
			o.get(temail);
			}catch(DALException e)
			{
				String DOB = processBirth(birth);
				String email = processEmail(temail);
				Boolean bSex = getSex(sex);
				String newPass;
				do {
					newPass = createPass();
				} while (!controlPassword(newPass));
				if (fName == "" || lName == "")
					throw new DALException("Please enter your full name!");
				UsersDTO newUser = new UsersDTO(fName, lName, DOB, newPass, email, bSex);
				o.create(newUser);
				UsersLangDTO langdto = new UsersLangDTO(email,lang);
				l.create(langdto);
				RoleDTO ro = new RoleDTO(email, role);
				r.create(ro);
				return newPass;
			}
		throw new DALException("User already exists!");
		
	}

	@Override
	public void updateOpr(String fName, String lName, String birth, String email, int sex, int lang, String oldPassword, String newPassword, String newPassword2) throws DALException {
		try {
			user = o.get(email);
		} catch (DALException e) {
			throw new DALException("The specified user does not exist.");
		}
		if (!(user.getPass().equals(oldPassword))) { // eventuelt exception handling
			throw new DALException("Old password is not correct.");
		}
		RoleDTO role = r.get(email);
		updateOprAdmin(fName, lName, birth, email, sex, lang, role.getRole(), newPassword, newPassword2);
	}

	@Override
	public void updateOprAdmin(String fName, String lName, String birth, String email, int sex, int lang, String role, String newPassword, String newPassword2) throws DALException {
		try {
			user = o.get(email);
		}
		catch (DALException e) {
			throw new DALException("The specified user does not exist.");
		}
		if (newPassword.equals("")) {
			newPassword = user.getPass();
			newPassword2 = user.getPass();
		}
		if (newPassword.equals(newPassword2)) {
			if (controlPassword(newPassword)) {
				email = processEmail(email);
				birth = processBirth(birth);
				Boolean bSex = getSex(sex);
				UsersDTO user = new UsersDTO(fName, lName, birth, newPassword, email, bSex);
				o.update(user);
				newRole = new RoleDTO(email,role);
				r.update(newRole);
			} else {
				throw new DALException("The new password is invalid.");
			}
		} else {
			throw new DALException("The two passwords do not match.");
		}
	}

	@Override
	public void deleteOpr(int currentUser, int oprID) throws DALException {
		String email = null;
		if (isAdmin(currentUser) && !isAdmin(oprID)) {
			UsersDTO opr = o.get(email);
		//	opr.setRole("inaktiv");
		//	o.update(opr);
		} else {
			throw new DALException("Operator could not be deleted.");
		}
	}

	@Override
	public List<UsersDTO> getUserList() throws DALException {
		return o.getList();
	}

	@Override
	public UsersDTO getUser(String email) throws DALException {
		return o.get(email);
	}

	private String createPass() {
		String Pass = PassGenerator.getRandomString(8);
		return Pass;
	}

	private String processBirth(String birth) throws DALException {
		String tempbirth = null;
		Pattern pBirth = Pattern.compile("\\d{2}-\\d{2}-\\d{4}");
		Matcher m = pBirth.matcher(birth);
		if (m.matches()) {
			tempbirth = birth;
		} else {
			throw new DALException("Invalid date of birth.");
		}
		return tempbirth;
	}
	private String processEmail(String email) throws DALException {
		String tempEmail = null;
		Pattern pEmail = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher m = pEmail.matcher(email);
		if (m.find()) {
			tempEmail = email;
		} else {
			throw new DALException("Invalid email Address.");
		}
		return tempEmail;
	}

	private boolean controlPassword(String password) {
		Pattern pPassword = Pattern.compile("(?:(?:(?=.*[A-Z])(?=.*[\\d])(?=.*[.\\-_\\+!=\\?]))|(?:(?=.*[a-z])(?=.*[\\d])(?=.*[.\\-_\\+!=\\?]))|(?:(?=.*[a-z])(?=.*[A-Z])(?=.*[.\\-_\\+!=\\?]))|(?:(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])))^.{7,8}$");
		Matcher m = pPassword.matcher(password);
		return m.matches();
	}

	private String initGen(String oprName) throws DALException {
		Pattern pInitials = Pattern.compile("(?=^.{2,20}$)^(\\w)\\w*(?: \\w*)* (\\w)\\w*$");
		Pattern pChars = Pattern.compile("[^\\w ]");
		String ini;
		Matcher m;
		m = pChars.matcher(oprName);
		if (m.find()) { throw new DALException("Name can only contain a-z, A-Z and spaces"); }
		if (oprName.length() > 20 || oprName.length() < 2) { throw new DALException("Name must be between 2 - 20 characters in length."); }
		m = pInitials.matcher(oprName);
		if (m.matches()) {
			ini = m.group(1) + m.group(2);
		} else {
			throw new DALException("Name needs to be two seperate words.");
		}
		return ini;
	}
	private boolean getSex(int sex){
		Boolean bSex = false;
		if (sex == 1)
			bSex = true;
		return bSex;
	}




}