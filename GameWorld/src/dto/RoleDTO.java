package dto;
/**
 * 
 * @author Rasmus Hansen, Tristan Richard
 *
 */
public class RoleDTO
{
	private String email;
	private String Role;

	public RoleDTO(String email, String Role)
	{
		this.email = email;
		this.Role = Role;
	}
	public RoleDTO() {}
	
	public String getEmail(){return email;}
	public void setEmail(String email){this.email = email;}

	public String getRole(){return Role;}
	public void setRole(String Role){this.Role = Role;}
}

