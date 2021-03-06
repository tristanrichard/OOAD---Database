package controller;

import java.io.IOException;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import daoimpl.MySQLLangDAO;
import daoimpl.MySQLPublisherDAO;
import daoimpl.MySQLRoleDAO;
import daoimpl.MySQLUserPubDAO;
import daoimpl.MySQLUsersLangDAO;
import daointerfaces.DALException;
import daointerfaces.LangIDAO;
import daointerfaces.PublisherIDAO;
import daointerfaces.RoleIDAO;
import daointerfaces.UserPubIDAO;
import daointerfaces.UsersLangIDAO;
import dto.LangDTO;
import dto.PublisherDTO;
import dto.RoleDTO;
import dto.UserPubDTO;
import dto.UsersDTO;
import dto.UsersLangDTO;
import funktionalitet.*;
/**
 * 
 * @author Tristan Richard
 *
 */
public class ControlUserAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IUserLogic userLogic = null;
	private IUserPubLogic userPubLogic = null;
	private UsersDTO user = null;
	private LangIDAO lang = null;
	private PublisherIDAO pub = null;
	private UserPubIDAO userPub = null;
	private UsersLangIDAO userLang = null;
	private RoleIDAO userRole = null;

	public ControlUserAdmin() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext application = request.getSession().getServletContext();
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		// Create logic if not already created.
		userLogic = (UserLogic) application.getAttribute("userLogic");
		if (userLogic == null) {
			try {
				userLogic = new UserLogic();
				application.setAttribute("userLogic", userLogic);
			} catch (DALException e) {
				e.printStackTrace();
				request.setAttribute("error", e.getMessage());
			}
		}
		userPubLogic = (UserPubLogic) application.getAttribute("userPubLogic");
		if (userPubLogic == null) {
			try {
				userPubLogic = new UserPubLogic();
				application.setAttribute("userPubLogic", userPubLogic);
			} catch (DALException e) {
				e.printStackTrace();
				request.setAttribute("error", e.getMessage());
			}
		}
		lang = (LangIDAO) application.getAttribute("language");
		if (lang == null) {
			lang = new MySQLLangDAO();
			application.setAttribute("language", lang);
		}
		userLang = (UsersLangIDAO) application.getAttribute("userlanguage");
		if (userLang == null) {
			userLang = new MySQLUsersLangDAO();
			application.setAttribute("userlanguage", userLang);
		}
		pub = (PublisherIDAO) application.getAttribute("publisher");
		if (pub == null) {
			pub = new MySQLPublisherDAO();
			application.setAttribute("publisher", pub);
		}
		userPub = (UserPubIDAO) application.getAttribute("userPublisher");
		if (userPub == null) {
			userPub = new MySQLUserPubDAO();
			application.setAttribute("userPublisher", userPub);
		}
		userRole = (RoleIDAO) application.getAttribute("role");
		if (userRole == null) {
			userRole = new MySQLRoleDAO();
			application.setAttribute("role", userRole);
		}

		// Create user bean if not already existing.
		user = (UsersDTO) session.getAttribute("user");
		if (user == null) {
			try {
				String email = request.getUserPrincipal().getName();
				user = userLogic.getUser(email);
				session.setAttribute("user", user);
			} catch (DALException e) {
				e.printStackTrace();
				request.setAttribute("error", e.getMessage());
			}
		}

		// Getting the action parameter.
		String action = null;
		action = request.getParameter("action");

		// Get data ready for next action
		// Redirects to createPublisher JSP
		if ("createPublisher".equals(action)) { // Creation of a new operator.
			List<PublisherDTO> pubList;
			List<LangDTO> langList;
			try {
				pubList = pub.getList();
				langList = lang.getList();
				request.setAttribute("pubList", pubList);
				request.setAttribute("langList", langList);
				request.getRequestDispatcher("../WEB-INF/admin/createPublisher.jsp?").forward(request, response); 
			} catch (DALException e) {
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("../WEB-INF/admin/index.jsp?").forward(request, response);
			}
			// Sends the request back to the create operator jsp file if an error is detected.
		} 
		// Publisher form Filled
		else if ("publisherFilled".equals(action)) { 
			try {
				String fName = request.getParameter("newFName");
				String lName = request.getParameter("newLName");
				String email = request.getParameter("newUserEmail");
				String birth = request.getParameter("newUserBirth");
				String sex = request.getParameter("newUserSex");
				String lang = request.getParameter("newUserLang");
				String company = request.getParameter("newPub");
				String role = "game";
				int tsex = Integer.parseInt(sex);
				int tlang = Integer.parseInt(lang);
				int tcompany = Integer.parseInt(company);
				userLogic.createUser(fName, lName, birth, role, email, tsex, tlang);
				userPubLogic.createUserPub(email, tcompany);
				request.setAttribute("message", fName + " was succesfully added to the database");
				request.getRequestDispatcher("../WEB-INF/admin/index.jsp?").forward(request, response);
			}catch (DALException e) {
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("../WEB-INF/admin/createPublisher.jsp?").forward(request, response);
			}
		}
		// Generate a list of publisher users
		else if ("listPublisher".equals(action)) {
			try {
				List<UserPubDTO> userPubList = userPubLogic.getList();
				List<UsersDTO> users = new ArrayList<UsersDTO>();
				List<PublisherDTO> comp = new ArrayList<PublisherDTO>();
				for (UserPubDTO i: userPubList) {
					users.add(userLogic.getUser(i.getEmail()));
					comp.add(userPubLogic.getPub(i.getPid()));
				}
				request.setAttribute("userList", users);
				request.setAttribute("compList", comp);
				request.getRequestDispatcher("../WEB-INF/admin/publisherList.jsp?").forward(request, response);
			} catch (DALException e) {
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("../WEB-INF/admin/index.jsp?").forward(request, response);
			}

		}
		//Generate a list of all users
		else if ("listUsers".equals(action)) {
			try {
				List<UsersDTO> userList = userLogic.getUserList();
				List<String> roles = new ArrayList<String>();
				for (UsersDTO i: userList) {
					roles.add(userRole.get(i.getEmail()).getRole());
				}
				request.setAttribute("roles", roles);
				request.setAttribute("userList", userList);
				request.getRequestDispatcher("../WEB-INF/admin/userList.jsp?").forward(request, response);
			} catch (DALException e) {
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("../WEB-INF/admin/index.jsp?").forward(request, response);
			}
		} 
		// Get data ready for next action
		// Redirect to updateuser.jsp or updatepublisher.jsp
		else if ("updateUser".equals(action)) {
			try {
				String userEmail = request.getParameter("userToUpdate");
				RoleDTO role = userRole.get(userEmail);
				UsersDTO user1 = userLogic.getUser(userEmail);
				UsersLangDTO userLangRow = userLang.get(userEmail);
				List<LangDTO> langu = lang.getList();
				request.setAttribute("role", role);
				request.setAttribute("userLang", userLangRow);
				request.setAttribute("langList", langu);
				request.setAttribute("user1", user1);
				if (role.getRole().equalsIgnoreCase("user") || role.getRole().equalsIgnoreCase("administrator") || role.getRole().equalsIgnoreCase("inactive")) {
					request.getRequestDispatcher("../WEB-INF/admin/updateUser.jsp?").forward(request, response);
				} else {
					UserPubDTO comp = userPub.get(userEmail);
					List<PublisherDTO> pubList = pub.getList();
					request.setAttribute("userPub", comp);
					request.setAttribute("pubList", pubList);
					request.getRequestDispatcher("../WEB-INF/admin/updatePublisher.jsp?").forward(request, response);
				}
			} catch (DALException e) {
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("../WEB-INF/admin/index.jsp?").forward(request, response);
			}
		} 
		// User form has been filled and updates
		else if ("updateOprFilled".equals(action)) {
			// Getting all the details from the filled form.
			try {
				String fName = request.getParameter("newFName");
				String lName = request.getParameter("newLName");
				String oldEmail = request.getParameter("oldEmail");
				String email = request.getParameter("newUserEmail");
				String birth = request.getParameter("newUserBirth");
				String sex = request.getParameter("newUserSex");
				String lang = request.getParameter("newUserLang");
				String role = request.getParameter("newUserRole");
				String pass1 = request.getParameter("updOprPass1");
				String pass2 = request.getParameter("updOprPass2");
				int iSex = Integer.parseInt(sex);
				int iLang = Integer.parseInt(lang);	
				if (role.equalsIgnoreCase("user") || role.equalsIgnoreCase("administrator") || role.equalsIgnoreCase("inactive")) {
					userLogic.updateOprAdmin(fName, lName, birth, oldEmail, email, iSex, iLang, role, pass1, pass2);
					request.setAttribute("message", "User with email: " + email + " successfully updated.");
				} else {
					String publisher = request.getParameter("newPub");
					int iPublisher = Integer.parseInt(publisher);
					userLogic.updatePubAdmin(fName, lName, birth, oldEmail, email, iSex, iLang, role, pass1, pass2, iPublisher);
					request.setAttribute("message", "User with email: " + email + " successfully updated.");
				}
				request.getRequestDispatcher("index.jsp?action=updateUser").forward(request, response);
			} catch (DALException e) {
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("index.jsp?action=updateUser").forward(request, response);
			}
		}
		// Deactivates selected user
		else if ("deactivateUser".equals(action)) {
			String userEmail =request.getParameter("userToDeactivate");
			try {
				userLogic.deactivateUser(userEmail);
				request.setAttribute("message", userEmail + " was succesfully deactivated");
				request.getRequestDispatcher("../WEB-INF/admin/index.jsp?").forward(request, response);
			} catch (DALException e) {
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("../WEB-INF/admin/index.jsp?").forward(request, response);
			}
		} else { 
			request.getRequestDispatcher("../WEB-INF/admin/index.jsp?").forward(request, response);
		}
	}
}