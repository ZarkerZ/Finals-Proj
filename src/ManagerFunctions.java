import javax.swing.*;
import java.awt.*;

public class ManagerFunctions {
    ReaderWriterFunctions readerwriter = new ReaderWriterFunctions();
    CommonItems functions = new CommonItems();
    public JButton submitAddCourse(JTextField name, JTextField code, JTextField capacity, ButtonGroup opt, JPanel titlePanelOne, JPanel titlePanelTwo){
        JButton submit = new JButton("Submit");
        submit.addActionListener(sub ->{
            String courseName = name.getText();
            String courseCode = code.getText();
            Short courseCapacity = Short.parseShort(capacity.getText());
            String labOpt = opt.getSelection().getActionCommand();

            ProgramCourse course;
            if(labOpt.equals("Yes")){
                course = new WithLabSubjects(courseName, courseCode, courseCapacity);
            }
            else{
                course = new ProgramCourse(courseName, courseCode, courseCapacity);
            }
            readerwriter.saveCourse(course, "CourseData.txt");
            JOptionPane.showMessageDialog(null, "Course successfully added!");
            titlePanelOne.setVisible(false);
            titlePanelTwo.setVisible(true);
        });

        return submit;
    }

    public JButton searchByCourseCode(String name, JPanel optionPanel, JFrame mainFrame){
        JButton button = new JButton(name);
        button.addActionListener(e ->{
            JPanel subPanel = new JPanel();
            subPanel.setLayout(null);
            mainFrame.add(subPanel);

            JLabel optionTitle = functions.makeOptionTitle("Search Program Course", 115, 20, 300, 30);
            subPanel.add(optionTitle);

            JLabel courseCodeLBL = functions.makeFieldLabel("Enter Course Code: ", 50, 70, 300, 80);
            subPanel.add(courseCodeLBL);

            JTextField courseCode = new JTextField();
            courseCode.setBounds(180, 100, 160, 25);
            subPanel.add(courseCode);

            JButton submit = searchCourseButton(courseCode);
            submit.setBounds(70,230, 125, 30);
            subPanel.add(submit);

            JButton back = functions.makeBackButton("Back", subPanel, optionPanel, 200, 230, 125, 30);
            subPanel.add(back);

            optionPanel.setVisible(false);
            subPanel.setVisible(true);
        });

        return button;
    }
    public JButton removeProgramCourse(JTextField courseCode){
        JButton remove = new JButton("Remove Course Code");
        String code = courseCode.getText();
        boolean deleted = readerwriter.removeCourse(code, "CourseData.txt");
        remove.addActionListener(e ->{
            if(deleted){
                JOptionPane.showMessageDialog(null,"Course deleted.");
            }
            else{
                JOptionPane.showMessageDialog(null,"Course not found.");
            }
        });

        return remove;
    }

    public JButton showAllProgramCourses(String name){
        JButton button = new JButton(name);
        button.addActionListener(e ->{
            JFrame displayData = functions.makeDisplayFrame("Display Information", 350);
            displayData.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JPanel titlePanel = new JPanel();
            titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.PAGE_AXIS));
            displayData.add(titlePanel,BorderLayout.CENTER);

            JLabel title = functions.makeOptionTitle("Program Course Information", 115, 20, 300, 30);
            titlePanel.add(title);

            JPanel infoPanel = new JPanel();
            infoPanel.setLayout(null);
            titlePanel.add(infoPanel);

            JLabel crsCode = functions.makeFieldLabel("Course Code:", 15, 25, 150, 25);
            infoPanel.add(crsCode);
            JLabel courseName = functions.makeFieldLabel("Course Name:", 185, 25, 150, 25);
            infoPanel.add(courseName);
            JLabel courseCapacity = functions.makeFieldLabel("Course Capacity:", 355, 25, 150, 25);
            infoPanel.add(courseCapacity);
            JLabel labSubject = functions.makeFieldLabel("Lab Subject:", 525, 25, 150, 25);
            infoPanel.add(labSubject);

            readerwriter.displayAllCourses("CourseData.txt", infoPanel);

            displayData.setVisible(true);
        });

        return button;
    }
    public JButton searchCourseButton(JTextField courseCode){
        JButton search = new JButton("Search");
        search.addActionListener(e ->{
            String codeInput = courseCode.getText();
            String dispData = readerwriter.searchCourse("CourseData.txt", codeInput);

            if(dispData != null){
                JFrame displayData = functions.makeDisplayFrame("Display Information", 250);
                displayData.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                JPanel titlePanel = new JPanel();
                titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.PAGE_AXIS));
                displayData.add(titlePanel,BorderLayout.CENTER);

                JLabel title = functions.makeOptionTitle("Program Course Information", 115, 20, 300, 30);
                titlePanel.add(title);

                JPanel infoPanel = new JPanel();
                infoPanel.setLayout(null);
                titlePanel.add(infoPanel);

                JLabel crsCode = functions.makeFieldLabel("Course Code:", 15, 25, 150, 25);
                infoPanel.add(crsCode);
                JLabel courseName = functions.makeFieldLabel("Course Name:", 185, 25, 150, 25);
                infoPanel.add(courseName);
                JLabel courseCapacity = functions.makeFieldLabel("Course Capacity:", 355, 25, 150, 25);
                infoPanel.add(courseCapacity);
                JLabel labSubject = functions.makeFieldLabel("Lab Subject:", 525, 25, 150, 25);
                infoPanel.add(labSubject);

                String[] courseDetails = dispData.split(">");

                short xSpacing = 15;
                for(int index=0; index < courseDetails.length; index++){
                    JLabel info = functions.makeSubLabel(courseDetails[index], xSpacing, 50, 150, 25);
                    xSpacing+= 170;
                    infoPanel.add(info);
                }

                displayData.setVisible(true);
            }
            else{
                JOptionPane.showMessageDialog(null, "Course not found / does not exist");
            }

        });

        return search;
    }

    public JButton submitAddStudent(JTextField firstN, JTextField lastN,
                                    JTextField id, JTextField addr, JTextField program, ButtonGroup yearLvl, JPanel titlePanelOne,
                                    JPanel titlePanelTwo){
        JButton submit = new JButton("Add Student");
        submit.addActionListener(e ->{
            String firstName = firstN.getText(), lastName = lastN.getText(), address = addr.getText(),
                    prog = program.getText();
            int idNum = Integer.parseInt(id.getText()), year = Integer.parseInt(yearLvl.getSelection().getActionCommand());

            Student stud = new Student(firstName, lastName, address, prog, idNum, year);

            readerwriter.saveStudentInfo(stud, "StudentData.txt");
            JOptionPane.showMessageDialog(null, "Student successfully added!");
            titlePanelOne.setVisible(false);
            titlePanelTwo.setVisible(true);
        });

        return submit;
    }

    public JButton removeStudentFromRecords(JTextField id){
        JButton remove = new JButton("Remove from Records");
        int userId = Integer.parseInt(id.getText());
        boolean deleted = readerwriter.removeStudent(userId, "StudentData.txt");
        remove.addActionListener(e ->{
            if(deleted){
                JOptionPane.showMessageDialog(null,"Student removed from records.");
            }
            else{
                JOptionPane.showMessageDialog(null,"Student not found.");
            }
        });

        return remove;
    }

    public JButton searchStudentButton(JTextField id){
        JButton search = new JButton("Search");
        search.addActionListener(e->{
            String userInputID = id.getText();
            String dispData = readerwriter.searchStudent("StudentData.txt", userInputID);

            if(dispData != null){
                JFrame displayData = functions.makeStudentDisplayFrame("Display Information", 350);
                displayData.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                JPanel titlePanel = new JPanel();
                titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.PAGE_AXIS));
                displayData.add(titlePanel,BorderLayout.CENTER);

                JLabel title = functions.makeOptionTitle("Student Information", 115, 20, 300, 30);
                titlePanel.add(title);

                JPanel infoPanel = new JPanel();
                infoPanel.setLayout(null);
                titlePanel.add(infoPanel);

                JLabel idNum = functions.makeFieldLabel("ID Number:", 15, 25, 150, 25);
                infoPanel.add(idNum);
                JLabel firstN = functions.makeFieldLabel("First Name:", 185, 25, 150, 25);
                infoPanel.add(firstN);
                JLabel lastN = functions.makeFieldLabel("Last Name:", 355, 25, 150, 25);
                infoPanel.add(lastN);
                JLabel addr = functions.makeFieldLabel("Address:", 525, 25, 150, 25);
                infoPanel.add(addr);
                JLabel progEnrolled = functions.makeFieldLabel("Program Enrolled:", 695, 25, 150, 25);
                infoPanel.add(progEnrolled);
                JLabel yearLvl = functions.makeFieldLabel("Year Level:", 865, 25, 150, 25);
                infoPanel.add(yearLvl);

                String[] studDetails = dispData.split(">");

                short xSpacing = 15;
                for(int index=0; index < studDetails.length; index++){
                    JLabel info = functions.makeSubLabel(studDetails[index], xSpacing, 50, 150, 25);
                    xSpacing+= 170;
                    infoPanel.add(info);
                }

                displayData.setVisible(true);
            }
            else{
                JOptionPane.showMessageDialog(null, "Student not found / does not exist");
            }
        });

        return search;
    }
    public JButton searchByIDNum(String name, JPanel optionPanel, JFrame mainFrame){
        JButton button = new JButton(name);
        button.addActionListener(e ->{
            JPanel subPanel = new JPanel();
            subPanel.setLayout(null);
            mainFrame.add(subPanel);

            JLabel title = functions.makeOptionTitle("Search Student", 115, 20, 300, 30);
            subPanel.add(title);

            JLabel idNumLBL = functions.makeFieldLabel("Enter ID Number: ", 50, 70, 300, 80);
            subPanel.add(idNumLBL);

            JTextField idNum = new JTextField();
            idNum.setBounds(180, 100, 160, 25);
            subPanel.add(idNum);

            JButton submit = searchStudentButton(idNum);
            submit.setBounds(70,230, 125, 30);
            subPanel.add(submit);

            JButton back = functions.makeBackButton("Back", subPanel, optionPanel, 200, 230, 125, 30);
            subPanel.add(back);

            optionPanel.setVisible(false);
            subPanel.setVisible(true);
        });

        return button;
    }

    public JButton showAllStudents(String name){
        JButton showAll = new JButton(name);
        showAll.addActionListener(e ->{
            JFrame displayData = functions.makeStudentDisplayFrame("Display Information", 350);
            displayData.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JPanel titlePanel = new JPanel();
            titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.PAGE_AXIS));
            displayData.add(titlePanel,BorderLayout.CENTER);

            JLabel title = functions.makeOptionTitle("Search Student", 115, 20, 300, 30);
            titlePanel.add(title);

            JPanel infoPanel = new JPanel();
            infoPanel.setLayout(null);
            titlePanel.add(infoPanel);

            JLabel idNum = functions.makeFieldLabel("ID Number:", 15, 25, 150, 25);
            infoPanel.add(idNum);
            JLabel firstN = functions.makeFieldLabel("First Name:", 185, 25, 150, 25);
            infoPanel.add(firstN);
            JLabel lastN = functions.makeFieldLabel("Last Name:", 355, 25, 150, 25);
            infoPanel.add(lastN);
            JLabel addr = functions.makeFieldLabel("Address:", 525, 25, 150, 25);
            infoPanel.add(addr);
            JLabel progEnrolled = functions.makeFieldLabel("Program Enrolled:", 695, 25, 150, 25);
            infoPanel.add(progEnrolled);
            JLabel yearLvl = functions.makeFieldLabel("Year Level:", 865, 25, 150, 25);
            infoPanel.add(yearLvl);

            readerwriter.displayAllStudents("StudentData.txt", infoPanel);

            displayData.setVisible(true);
        });

        return showAll;
    }

    public JButton updateStudent(String name, JTextField id){
        JButton update = new JButton(name);
        update.addActionListener(e->{
            String idNum = id.getText();
            String toBeChanged = readerwriter.searchStudent("StudentData.txt", idNum);

            if(toBeChanged != null){
                JFrame updateData = new JFrame("Update Student Information");
                updateData.setSize(400,550);
                updateData.setLocationRelativeTo(null);
                JPanel optionPanel = new JPanel();
                optionPanel.setLayout(null);
                updateData.add(optionPanel);

                JLabel optionTitle = functions.makeOptionTitle("Update Info", 115, 20, 300, 30);
                optionPanel.add(optionTitle);

                JLabel firstNameLBL = functions.makeFieldLabel("First Name: ", 70, 70, 300, 80);
                optionPanel.add(firstNameLBL);

                JTextField firstName = new JTextField();
                firstName.setBounds(180, 100, 160, 25);
                optionPanel.add(firstName);

                JLabel lastNameLBL = functions.makeFieldLabel("Last Name: ", 70, 110, 300, 80);
                optionPanel.add(lastNameLBL);

                JTextField lastName = new JTextField();
                lastName.setBounds(180, 140, 160, 25);
                optionPanel.add(lastName);

                JLabel idNumLBL = functions.makeFieldLabel("ID Number: ", 70, 150, 300, 80);
                optionPanel.add(idNumLBL);

                JTextField idNumber = new JTextField();
                idNumber.setBounds(180, 180, 160, 25);
                optionPanel.add(idNumber);

                JLabel addrLBL = functions.makeFieldLabel("Address: ", 70, 190, 300, 80);
                optionPanel.add(addrLBL);

                JTextField addr = new JTextField();
                addr.setBounds(180, 220, 160, 25);
                optionPanel.add(addr);

                JLabel progEnrolledLBL = functions.makeFieldLabel("Program Enrolled: ", 70, 230, 300, 80);
                optionPanel.add(progEnrolledLBL);

                JTextField progEnrolled = new JTextField();
                progEnrolled.setBounds(180, 260, 160, 25);
                optionPanel.add(progEnrolled);

                JLabel yearLvlLBL = functions.makeFieldLabel("Year Level: ", 70, 270, 300, 80);
                optionPanel.add(yearLvlLBL);

                JPanel yearLevelBTNS = new JPanel();
                yearLevelBTNS.setBounds(75, 295, 300, 80);
                JRadioButton yearOne = new JRadioButton("1");
                yearOne.setActionCommand("1");
                JRadioButton yearTwo = new JRadioButton("2");
                yearTwo.setActionCommand("2");
                JRadioButton yearThree = new JRadioButton("3");
                yearThree.setActionCommand("3");
                JRadioButton yearFour = new JRadioButton("4");
                yearFour.setActionCommand("4");
                JRadioButton yearFive = new JRadioButton("5");
                yearFive.setActionCommand("5");
                ButtonGroup yearLVL = new ButtonGroup();
                yearLVL.add(yearOne);
                yearLVL.add(yearTwo);
                yearLVL.add(yearThree);
                yearLVL.add(yearFour);
                yearLVL.add(yearFive);
                yearLevelBTNS.add(yearOne);
                yearLevelBTNS.add(yearTwo);
                yearLevelBTNS.add(yearThree);
                yearLevelBTNS.add(yearFour);
                yearLevelBTNS.add(yearFive);
                optionPanel.add(yearLevelBTNS);

                yearLevelBTNS.setVisible(true);

                JButton submit = new JButton("Update");
                submit.setBounds(70,330, 125, 30);
                optionPanel.add(submit);

                JButton back = new JButton("Back");
                back.setBounds(200, 330, 125, 30);
                optionPanel.add(back);

                submit.addActionListener(e1 ->{
                    String newData =  (idNumber.getText()+"> "+firstName.getText()+"> "+
                            lastName.getText()+"> "+addr.getText()+"> "+progEnrolled.getText()+"> "+yearLVL.getSelection().getActionCommand()+">");
                    boolean changed = readerwriter.updateStudentInfo(newData, "StudentData.txt", id.getText());
                    if(changed){
                        JOptionPane.showMessageDialog(null,"Student information successfully updated!");
                        updateData.dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Unable to update");
                        updateData.dispose();
                    }
                });

                back.addActionListener(e2 ->{
                    updateData.dispose();
                });

                updateData.setVisible(true);
            }
            else{
                JOptionPane.showMessageDialog(null, "Student not found/ does not exist");
            }
        });

        return update;
    }
}
