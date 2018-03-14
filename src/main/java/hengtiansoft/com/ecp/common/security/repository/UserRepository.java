package hengtiansoft.com.ecp.common.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hengtiansoft.com.ecp.common.security.domain.User;


/**
* Class Name: UserRepository
* Description: TODO
* @author SC
*
*/
public interface UserRepository extends JpaRepository<User, String> {

}
