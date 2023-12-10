public class Student {
    private String firstName, lastName, address, program;
    private int idNum, yearLvl;

    public Student(String firstName, String lastName, String address, String program, int idNum, int yearLvl) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.program = program;
        this.idNum = idNum;
        this.yearLvl = yearLvl;
    }
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getProgram() {
        return program;
    }

    public int getIdNum() {
        return idNum;
    }

    public int getYearLvl() {
        return yearLvl;
    }
}
