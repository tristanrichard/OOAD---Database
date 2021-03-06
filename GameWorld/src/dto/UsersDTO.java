package dto;
/**
 * 
 * @author Rasmus Hansen, Tristan Richard
 *
 */
public class UsersDTO
{
	private String Fname;
	private String Lname;
	private String DOB;
	private String pass;
	private String email;
	private boolean sex;

	public UsersDTO(String Fname, String Lname, String DOB, String pass, String email, Boolean sex)
	{
		this.Fname = Fname;
		this.Lname = Lname;
		this.DOB = DOB;
		this.pass = pass;
		this.email = email;
		this.sex = sex;
	}
	public UsersDTO() {
		super();
	}

	public String getFname(){return Fname;}
	public void setFname(String Fname){this.Fname = Fname;}

	public String getLname(){return Lname;}
	public void setLname(String Lname){this.Lname = Lname;}

	public String getDob(){return DOB;}
	public void setDob(String DOB){this.DOB = DOB;}

	public String getPass(){return pass;}
	public void setPass(String pass){this.pass = pass;}

	public String getEmail(){return email;}
	public void setEmail(String email){this.email = email;}
	
	public Boolean getSex(){return sex;}
	public void setSex(Boolean sex){this.sex = sex;}
}

