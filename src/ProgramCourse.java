public class ProgramCourse {
    private String courseName, courseCode;
    private short courseCapacity;
    public ProgramCourse(String courseName, String courseCode, short courseCapacity) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.courseCapacity = courseCapacity;
    }
    public String getCourseName() {
        return courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public short getCourseCapacity() {
        return courseCapacity;
    }


}
