package pl.w65154.helpdesk.init;

import pl.w65154.helpdesk.entity.Role;
import pl.w65154.helpdesk.entity.User;
import pl.w65154.helpdesk.repository.RoleRepository;
import pl.w65154.helpdesk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;

@Component
@Order(10)
public class UserInitializer implements DataLoader {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public void load() {
        if (userRepository.count() == 0 && roleRepository.count() == 0) {
            Role roleUser = new Role("ROLE_USER");
            Role roleHr = new Role("ROLE_HR");
            Role roleHelpdesk = new Role("ROLE_HELPDESK");
            roleRepository.save(new HashSet<>(Arrays.asList(roleUser, roleHr, roleHelpdesk)));

            User userAdmin = new User();
            userAdmin.setUsername("admin");
            userAdmin.setFirstName("Krzysztof");
            userAdmin.setLastName("Rygalik");
            userAdmin.setEmail("admin@helpdesk.com");
            userAdmin.setPassword(encoder.encode("admin"));
            userAdmin.setRoles(new HashSet<>(Arrays.asList(roleUser, roleHr, roleHelpdesk)));
            userRepository.save(userAdmin);

            User user = new User();
            user.setUsername("tester");
            user.setFirstName("Jan");
            user.setLastName("Kowalski");
            user.setEmail("tester@helpdesk.com");
            user.setRoles(new HashSet<>(Arrays.asList(roleUser)));
            user.setPassword(encoder.encode("tester"));
            userRepository.save(user);

            User userHelpdesk = new User();
            userHelpdesk.setUsername("pl/w65154/helpdesk");
            userHelpdesk.setFirstName("Mateush");
            userHelpdesk.setLastName("Morawietsky");
            userHelpdesk.setEmail("premier@helpdesk.com");
            userHelpdesk.setRoles(new HashSet<>(Arrays.asList(roleUser, roleHelpdesk)));
            userHelpdesk.setPassword(encoder.encode("pl/w65154/helpdesk"));
            userRepository.save(userHelpdesk);
        }
    }
}
