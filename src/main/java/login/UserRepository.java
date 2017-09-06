package login;

import org.springframework.data.jpa.repository.JpaRepository;

/**

 * Created by finawei on 9/4/17.
 */
public interface UserRepository extends JpaRepository<User,Long> {

}