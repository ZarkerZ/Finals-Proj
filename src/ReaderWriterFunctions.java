import javax.swing.*;
import java.io.*;

public class ReaderWriterFunctions {
    CommonItems functions = new CommonItems();
    public void saveCourse(ProgramCourse course, String path){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));
            writer.write(course.getCourseCode()+"> ");
            writer.write(course.getCourseName()+"> ");
            writer.write(course.getCourseCapacity()+"> ");
            if (course instanceof WithLabSubjects) {
                writer.write(((WithLabSubjects) course).getLabSubj()+"> ");
            }
            else{
                writer.write("No Lab Subject ");
            }
            writer.newLine();
            writer.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public String searchCourse(String path, String userInput){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;

            while((line = reader.readLine()) != null){
                String[] courseCode = line.split(">");
                if (courseCode.length > 0 && courseCode[0].equals(userInput)) {
                    return line;
                }
            }

            reader.close();
        }
        catch(IOException e){
                e.printStackTrace();
        }

        return null;
    }

    public boolean removeCourse(String courseCode, String path){
        boolean deleted = false;
        File current = new File(path);
        File temp = new File("tempCourseInfo.txt");

        try{
            BufferedReader reader = new BufferedReader(new FileReader(current));
            BufferedWriter writer = new BufferedWriter(new FileWriter(temp));

            String line;

            while((line=reader.readLine()) != null){
                String[] code = line.split(">");
                if (!(code.length > 0 && code[0].equals(courseCode))){
                    writer.write(line+System.getProperty("line.separator"));
                }
                else{
                    deleted = true;
                }
            }

            writer.close();
            reader.close();

            if (!current.delete()) {
                JOptionPane.showMessageDialog(null,"Could not delete the original file");
                return deleted;
            }

            if (!temp.renameTo(current)) {
                JOptionPane.showMessageDialog(null,"Could not rename the temp file");
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }

        return deleted;
    }
    public void displayAllCourses(String path, JPanel infoPanel){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;

            short ySpacing = 50;
            while((line= reader.readLine()) != null){
                String[] data = line.split(">");
                short xSpacing = 15;
                for(int index=0; index < data.length; index++){
                    JLabel info = functions.makeSubLabel(data[index], xSpacing, ySpacing, 150, 25);
                    xSpacing+= 170;
                    infoPanel.add(info);
                }
                ySpacing += 15;
            }

            reader.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void saveStudentInfo(Student stud, String path){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));
            writer.write(stud.getIdNum()+"> ");
            writer.write(stud.getFirstName()+"> ");
            writer.write(stud.getLastName()+"> ");
            writer.write(stud.getAddress()+"> ");
            writer.write(stud.getProgram()+"> ");
            writer.write(stud.getYearLvl()+"> ");

            writer.newLine();
            writer.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public boolean removeStudent(int userID, String path){
        boolean deleted = false;
        File current = new File(path);
        File temp = new File("tempStudentInfo.txt");

        try{
            BufferedReader reader = new BufferedReader(new FileReader(current));
            BufferedWriter writer = new BufferedWriter(new FileWriter(temp));

            String line;

            while((line=reader.readLine()) != null){
                String[] idNumber = line.split(">");
                if (!(idNumber.length > 0 && Integer.parseInt(idNumber[0]) == (userID))) {
                    writer.write(line+System.getProperty("line.separator"));
                }
                else{
                    deleted = true;
                }
            }

            writer.close();
            reader.close();

            if (!current.delete()) {
                JOptionPane.showMessageDialog(null,"Could not delete the original file");
                return deleted;
            }

            if (!temp.renameTo(current)) {
                JOptionPane.showMessageDialog(null,"Could not rename the temp file");
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }

        return deleted;
    }

    public void displayAllStudents(String path, JPanel infoPanel){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;

            short ySpacing = 50;
            while((line= reader.readLine()) != null){
                String[] data = line.split(">");
                short xSpacing = 15;
                for(int index=0; index < data.length; index++){
                    JLabel info = functions.makeSubLabel(data[index], xSpacing, ySpacing, 150, 25);
                    xSpacing+= 170;
                    infoPanel.add(info);
                }
                ySpacing += 15;
            }

            reader.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public String searchStudent(String path, String userID){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;

            while((line = reader.readLine()) != null){
                String[] idNum = line.split(">");
                if (idNum.length > 0 && idNum[0].equals(userID)){
                    return line;
                }
            }

            reader.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }

        return null;
    }

    public boolean updateStudentInfo(String newData,String path, String idNum){
        boolean updated = false;
        File current = new File(path);
        File temp = new File("tempDataForm.txt");

        try{
            BufferedReader reader = new BufferedReader(new FileReader(current));
            BufferedWriter writer = new BufferedWriter(new FileWriter(temp));

            String line;

            while((line = reader.readLine()) != null){
                String[] currNum = line.split(">");
                if(!(currNum.length > 0 && currNum[0].equals(idNum))){
                    writer.write(line + System.getProperty("line.separator"));
                }
                else{
                    writer.write(newData + System.getProperty("line.separator"));
                    updated = true;
                }
            }

            writer.close();
            reader.close();

            if (!current.delete()) {
                JOptionPane.showMessageDialog(null,"Could not delete the original file");
                return updated;
            }

            if (!temp.renameTo(current)) {
                JOptionPane.showMessageDialog(null,"Could not rename the temp file");
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return updated;
    }
}
