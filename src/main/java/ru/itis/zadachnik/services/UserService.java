package ru.itis.zadachnik.services;

import ru.itis.zadachnik.enums.Role;
import ru.itis.zadachnik.forms.UserRegisterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.zadachnik.models.Group;
import ru.itis.zadachnik.models.User;
import ru.itis.zadachnik.repositories.GroupsRepository;
import ru.itis.zadachnik.repositories.UsersRepository;
import ru.itis.zadachnik.security.details.UserDetailsImpl;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private GroupsRepository groupsRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<User> getUserByLogin(String login) {
        return userRepository.findOneByLogin(login);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findOneById(id);
    }

    public void signUp(UserRegisterForm form) {
        String hashPassword = passwordEncoder.encode(form.getPassword());
        Group group = groupsRepository.findGroupByName(form.getGroup()).orElse(null);
        User user = User.builder()
                .name(form.getName())
                .surname(form.getSurname())
                .login(form.getLogin())
                .password(hashPassword)
                .group(group)
                .role(Role.STUDENT)
                .build();
        userRepository.save(user);
    }

    public Optional<User> getCurrentUser(Authentication authentication) {
        if (authentication != null) {
            Long currentUserId = ((UserDetailsImpl) authentication.getPrincipal()).getUser().getId();
            return getUserById(currentUserId);
        }
        return Optional.empty();
    }

    public boolean loginIsUnique(String login) {
        return !userRepository.findOneByLogin(login).isPresent();
    }

    private User findById(List<User> users, Long id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    /*public void editProfile(UserEditForm form, User currentUser) {
        if (!form.getFirstName().equals(""))
            currentUser.setFirstName(form.getFirstName());
        else
            currentUser.setFirstName(null);

        if (!form.getLastName().equals(""))
            currentUser.setLastName(form.getLastName());
        else
            currentUser.setLastName(null);

        if (!form.getLogin().equals(""))
            currentUser.setLogin(form.getLogin());
        else
            currentUser.setLogin(null);

        if (!form.getEmail().equals(""))
            currentUser.setEmail(form.getEmail());
        else
            currentUser.setEmail(null);
        if (!form.getCity().equals(""))
            currentUser.setCity(form.getCity());
        else
            currentUser.setCity(null);

        if (!form.getFile().isEmpty()) {
            String path = fileDownloader.upload(form.getFile(), currentUser.getLogin()).orElseThrow(IllegalArgumentException::new);
            currentUser.setPhoto_path(path);
        }

        userRepository.save(currentUser);

    }

    public void changePassword(ChangePasswordForm form, User currentUser) {
        currentUser.setHashPassword(passwordEncoder.encode(form.getNewPassword()));
        userRepository.save(currentUser);
    }*/
}
