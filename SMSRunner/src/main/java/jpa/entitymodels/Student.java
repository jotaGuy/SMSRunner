package jpa.entitymodels;

import javax.persistence.*;

@Entity
@Table(name = "Student")
public class Student {
    @Id
    @Column(name = "email", length = 50, nullable = false) // Updated column name and attributes
    private String email;

    @Column(name = "name", length = 50, nullable = false) // Updated column name and attributes
    private String name;

    @Column(name = "password", length = 50, nullable = false) // Updated column name and attributes
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", foreignKey = @ForeignKey(name = "fk_student_course"))
    private Course course;

    // Constructors
    public Student() {
        // Default constructor
    }

    public Student(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    // Getters and setters
    public String getsEmail() {
        return email;
    }

    public void setsEmail(String email) {
        this.email = email;
    }

    public String getsName() {
        return name;
    }

    public void setsName(String name) {
        this.name = name;
    }

    public String getsPass() {
        return password;
    }

    public void setsPass(String password) {
        this.password = password; 
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
