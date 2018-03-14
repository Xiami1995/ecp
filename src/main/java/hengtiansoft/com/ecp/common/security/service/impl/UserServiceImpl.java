package hengtiansoft.com.ecp.common.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hengtiansoft.com.ecp.common.security.domain.User;
import hengtiansoft.com.ecp.common.security.repository.UserRepository;
import hengtiansoft.com.ecp.common.security.service.UserService;


/**
* Class Name: UserServiceImpl
* Description: TODO
* @author SC
*
*/
@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    UserRepository userRepository;

    @Override
    public User findUserByUserName(String userName) {
        return userRepository.findOne(userName);
    }

}
