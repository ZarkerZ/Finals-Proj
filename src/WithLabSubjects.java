public class WithLabSubjects extends ProgramCourse{
    private String labSubj;

    public WithLabSubjects(String courseName, String courseCode, short courseCapacity){
        super(courseName, courseCode, courseCapacity);
        labSubj = courseName+"Lab";
    }

    public String getLabSubj() {
        return labSubj;
    }
}
