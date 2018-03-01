package hello;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name = "user")
@NamedQueries({
	@NamedQuery(name="User.findAll", query="SELECT u FROM User u"),
//	@NamedQuery(name="User.findByName", query="SELECT u FROM User u WHERE u.user_last_name =:user_last_name"),
})

public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int iduser;

	@Column(name="user_email")
	private String user_email;

	@Column(name="user_first_name")
	private String user_first_name;

	@Column(name="user_last_name")
	private String user_last_name;

	public User() {
	}
	
	public User( String iduser, String user_first_name, String user_last_name, String user_email) {
	}

	public int getiduser() {
		return this.iduser;
	}

	public void setiduser(int iduser) {
		this.iduser = iduser;
	}

	public String getuser_email() {
		return this.user_email;
	}

	public void setuser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getuser_first_name() {
		return this.user_first_name;
	}

	public void setuser_first_name(String user_first_name) {
		this.user_first_name = user_first_name;
	}

	public String getuser_last_name() {
		return this.user_last_name;
	}

	public void setuser_last_name(String user_last_name) {
		this.user_last_name = user_last_name;
	}
	
	@Override
	public String toString()
	{
		return String.format("ID User: %s user_first_name: %s user_last_name: %s user_email:%s", iduser, user_first_name, user_last_name, user_email);
	}

}