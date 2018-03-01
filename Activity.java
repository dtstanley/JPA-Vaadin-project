package hello;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name = "activity")
@NamedQueries({
	@NamedQuery(name="Activity.findAll", query="SELECT a FROM Activity a"),
//	@NamedQuery(name="User.findByName", query="SELECT u FROM User u WHERE u.user_last_name =:user_last_name"),
})

public class Activity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int idactivity;

	@Column(name="activity_date")
	private String activity_date;

	@Column(name="activity_location")
	private String activity_location;

	@Column(name="activity_theme")
	private String activity_theme;
	
	@Column(name="goal_history_idgoal")
	private String goal_history_idgoal;
	
	@Column(name="step")
	private String step;
	
	@Column(name="category_idcategory")
	private String category_idcategory;

	public Activity() {
	}
	
	//Should idactivity be an int?
	public Activity( String idactivity, String activity_date, String activity_location, String activity_theme, String goal_history_idgoal, String step, String category_idcategory) {
	}


	public int getIdactivity() {
		return idactivity;
	}

	public void setIdactivity(int idactivity) {
		this.idactivity = idactivity;
	}

	public String getActivity_date() {
		return activity_date;
	}

	public void setActivity_date(String activity_date) {
		this.activity_date = activity_date;
	}

	public String getActivity_location() {
		return activity_location;
	}

	public void setActivity_location(String activity_location) {
		this.activity_location = activity_location;
	}

	public String getActivity_theme() {
		return activity_theme;
	}

	public void setActivity_theme(String activity_theme) {
		this.activity_theme = activity_theme;
	}

	public String getGoal_history_idgoal() {
		return goal_history_idgoal;
	}

	public void setGoal_history_idgoal(String goal_history_idgoal) {
		this.goal_history_idgoal = goal_history_idgoal;
	}

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	public String getCategory_idcategory() {
		return category_idcategory;
	}

	public void setCategory_idcategory(String category_idcategory) {
		this.category_idcategory = category_idcategory;
	}
	
	@Override
	public String toString()
	{
		return String.format("ID activity: %s activity_date: %s activity_location: %s activity_theme:%s goal_history_idgoal:%s step:%s category_idcategory:%s", idactivity, activity_date, activity_location, activity_theme, goal_history_idgoal, step, category_idcategory);
	}

}