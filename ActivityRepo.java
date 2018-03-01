package hello;


import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepo extends JpaRepository<Activity, String> {

//	    List<User> findAll(int idactivity);
		Collection<Activity> findActivityByIdactivity(int idactivity);
//	    User findOne(long idactivity);
//		List<User> findByLastNameStartsWithIgnoreCase(String user_last_name);
//		Object findByLastName(String string);

	//	User findOne(long l);
	}
