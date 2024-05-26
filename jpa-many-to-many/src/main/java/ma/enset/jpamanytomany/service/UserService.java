package ma.enset.jpamanytomany.service;

import jakarta.transaction.Transactional;
import ma.enset.jpamanytomany.entities.Role;
import ma.enset.jpamanytomany.entities.User;
import org.springframework.stereotype.Service;


public interface UserService {
    User addNewUser(User user);
    Role addNewRole(Role role);
    User findUserByUserName(String username);
    Role findRoleByRoleName(String roleName);
    void addRoleToUser(String username, String roleName);
    User authenticate(String userName, String password);

}
