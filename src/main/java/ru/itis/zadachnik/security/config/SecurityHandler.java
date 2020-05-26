package ru.itis.zadachnik.security.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import ru.itis.zadachnik.enums.Role;
import ru.itis.zadachnik.security.details.UserDetailsImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SecurityHandler implements AuthenticationSuccessHandler {

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        Role role =  ((UserDetailsImpl)authentication.getPrincipal()).getUser().getRole();
        if (role.equals(Role.ADMIN)) {
            response.sendRedirect("/admin");
        } else response.sendRedirect("/problems");
    }
}