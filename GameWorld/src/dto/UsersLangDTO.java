package dto;
/**
 * 
 * @author Rasmus Hansen, Tristan Richard
 *
 */
public class UsersLangDTO
{
	private String email;
	private int Langid;

	public UsersLangDTO(String email, int Langid)
	{
		this.email = email;
		this.Langid = Langid;
	}
	public UsersLangDTO() {
		super();
	}

	public String getEmail(){return email;}
	public void setEmail(String email){this.email = email;}

	public int getLangid(){return Langid;}
	public void setLangid(int Langid){this.Langid = Langid;}
}

