package hello;


	import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

	public interface UserRepo extends JpaRepository<User, String> {

//	    List<User> findAll(int iduser);
	    
//	    User findOne(long iduser);
//		List<User> findByLastNameStartsWithIgnoreCase(String user_last_name);
//		Object findByLastName(String string);

	//	User findOne(long l);
	}
