package pl.w65154.helpdesk.service;

import pl.w65154.helpdesk.entity.Role;
import pl.w65154.helpdesk.entity.User;
import pl.w65154.helpdesk.form.UserForm;
import pl.w65154.helpdesk.repository.RoleRepository;
import pl.w65154.helpdesk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    protected UserRepository userRepository;

    @Autowired
    SecurityService securityService;

    @Autowired
    protected RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public Page<User> search(String query, Pageable pageable) {
        return userRepository.search(query, pageable);
    }

    public User save(UserForm form) {
        Set<Long> rolesId = form.getRoles();

        User user = new User();
        user.setUsername(form.getUsername());
        user.setFirstName(form.getFirstName());
        user.setLastName(form.getLastName());
        user.setEmail(form.getEmail());

        for (Long roleId : rolesId) {
            user.addRole(roleRepository.findOne(roleId));
        }
        user.setPassword(passwordEncoder.encode(form.getPassword()));
        userRepository.save(user);
        return user;
    }

    public User saveNewUser(UserForm form) {
        Set<Long> roleSet = new HashSet<>();
        roleSet.add(roleRepository.findOneByName("ROLE_USER").getId());
        form.setRoles(roleSet);
        return save(form);
    }

    public Page<Role> searchRole(String search, Pageable pageable) {
        search = "%" + search + "%";
        return roleRepository.findAllByNameLike(search, pageable);
    }

    public User getCurrentUser() {
        String username = securityService.findLoggedInUsername();
        return userRepository.findByUsername(username);
    }

    public void save(User entity) {
        userRepository.save(entity);
    }

    public void saveModifiedUser(UserForm formData, User entity) {
        entity.setUsername(formData.getUsername());
        entity.setFirstName(formData.getFirstName());
        entity.setLastName(formData.getLastName());
        entity.setEmail(formData.getEmail());
        entity.setPassword(passwordEncoder.encode(formData.getPassword()));
        userRepository.save(entity);
    }
}
