package ru.itis.zadachnik.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.zadachnik.models.Group;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegisterForm {
    private String name;
    private String surname;
    private String login;
    private String password;
    private String group;
    private String role = "STUDENT";
}
