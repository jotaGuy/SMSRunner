package jpa.entitymodels;

import javax.persistence.*;

@Entity
@Table(name = "Course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "instructor")
    private String instructor;

    // Constructors, getters, and setters

    public int getcId() {
        return id;
    }

    public void setcId(int id) {
        this.id = id;
    }

    public String getcName() {
        return name;
    }

    public void setcName(String name) {
        this.name = name;
    }

    public String getcInstructorName() {
        return instructor;
    }

    public void setcInstructorName(String instructor) {
        this.instructor = instructor;
    }

    // Constructors
    public Course() {
        // Default constructor
    }

    public Course(int id, String name, String instructor) {
        this.id = id;
        this.name = name;
        this.instructor = instructor;
    }

    // Getters and setters
}
