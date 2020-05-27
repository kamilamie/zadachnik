package ru.itis.zadachnik.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewProblemForm {
    private String problemText;
    private String progrLang;
    private String commLang;
    private String difficulty;
    private String topics;
}
