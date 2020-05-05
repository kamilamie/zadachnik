package ru.itis.zadachnik.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.zadachnik.enums.*;

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

    @Enumerated(EnumType.STRING)
    private Source source;

    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    private String text;

    @OneToMany(mappedBy = "problem")
    private List<Solution> solutions;

    @ElementCollection(targetClass=ProblemTopic.class)
    @Enumerated(EnumType.STRING) // Possibly optional (I'm not sure) but defaults to ORDINAL.
    @CollectionTable(name="problem_topic")
    @Column(name="topic") // Column name in person_interest
    private List<ProblemTopic> topics;

}
