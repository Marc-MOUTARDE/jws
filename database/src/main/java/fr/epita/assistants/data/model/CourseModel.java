package fr.epita.assistants.data.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "course_model")
public class CourseModel {
    @Id
    @JoinColumn(name = "course_id")
    public long id;
    public String name;

    @ElementCollection @CollectionTable(
            name = "course_model_tags",
            joinColumns=@JoinColumn(name = "course_id", referencedColumnName = "id")
    )
    public List<String> tag;
}