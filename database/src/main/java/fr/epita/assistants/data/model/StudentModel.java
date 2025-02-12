package fr.epita.assistants.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "student_model")
public class StudentModel {
    @Id
    public long id;
    public String name;
    public long course_id;
}
