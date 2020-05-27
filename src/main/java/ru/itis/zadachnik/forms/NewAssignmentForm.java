package ru.itis.zadachnik.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewAssignmentForm {
    private String problemsIds;
    private String title;
    private String group;
    private String student;
    private String deadline;
}
