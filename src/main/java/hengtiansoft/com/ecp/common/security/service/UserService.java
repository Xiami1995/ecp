package hengtiansoft.com.ecp.common.security.service;

import hengtiansoft.com.ecp.common.security.domain.User;


/**
* Class Name: UserService
* Description: TODO
* @author SC
*
*/
public interface UserService {
    
    User findUserByUserName(String userName);

}
