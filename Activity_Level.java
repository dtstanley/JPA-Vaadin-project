package hello;


import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name = "Activity_Level")
@NamedQueries({
	@NamedQuery(name="Activity_Level.findAll", query="SELECT al FROM Activity_Level al"),
//	@NamedQuery(name="User.findByName", query="SELECT u FROM User u WHERE u.user_last_name =:user_last_name"),
})

public class Activity_Level implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int idactivity_level;

	@Column(name="beginner")
	private String beginner;

	@Column(name="intermediate")
	private String intermediate;

	@Column(name="advanced")
	private String advanced;
	
	@Column(name="pro")
	private String pro;
	
	
	
	public int getIdactivity_level() {
		return idactivity_level;
	}

	public void setIdactivity_level(int idactivity_level) {
		this.idactivity_level = idactivity_level;
	}

	public String getBeginner() {
		return beginner;
	}

	public void setBeginner(String beginner) {
		this.beginner = beginner;
	}

	public String getIntermediate() {
		return intermediate;
	}

	public void setIntermediate(String intermediate) {
		this.intermediate = intermediate;
	}

	public String getAdvanced() {
		return advanced;
	}

	public void setAdvanced(String advanced) {
		this.advanced = advanced;
	}

	public String getPro() {
		return pro;
	}

	public void setPro(String pro) {
		this.pro = pro;
	}

	public Activity_Level() {
	}
	
	//Should idactivity_level be an int?
	public Activity_Level( String idactivity_level, String beginner, String intermediate, String advanced, String pro) {
	}

	@Override
	public String toString()
	{
		return String.format("ID activity level: %s beginner: %s intermediate: %s advanced:%s pro:%s", idactivity_level, beginner, intermediate, advanced, pro);
	}

}