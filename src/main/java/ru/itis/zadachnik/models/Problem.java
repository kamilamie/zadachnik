package ru.itis.zadachnik.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.zadachnik.enums.CommunicationLanguage;
import ru.itis.zadachnik.enums.ProgrammingLanguage;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Problem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ProgrammingLanguage progrLanguage;

    @Enumerated(EnumType.STRING)
    private CommunicationLanguage commLanguage;

    private String text;

    @OneToMany(mappedBy = "problem")
    private List<Solution> solutions;

    @ManyToMany
    @JoinTable(
            name = "topic_problem",
            joinColumns = @JoinColumn(name = "problem_id"),
            inverseJoinColumns = @JoinColumn(name = "topic_id"))
    private List<Topic> topics;

}
