package entitymodels;
import javax.persistence.*;

@Entity
@Table(name = "Course")
public class Course {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int cId;

    @Column(name = "name")
    private String cName;

    @Column(name = "Instructor")
    private String cInstructorName;

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getcInstructorName() {
		return cInstructorName;
	}

	public void setcInstructorName(String cInstructorName) {
		this.cInstructorName = cInstructorName;
	}

    // Constructors, getters, and setters
    
}
