import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Main{
    private static JFrame mainFrame;
    private static JPanel mainPanel, optionPanel, subPanel, bottomPanel, spacerPanel;
    private static JLabel optionTitle;
    private static JButton submit, back;

    public static void main(String[] args){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        ManagerFunctions functions = new ManagerFunctions();
        CommonItems items = new CommonItems();

        mainFrame = new JFrame("Student and Course Manager");
        mainFrame.setSize(400,400);
        mainFrame.setResizable(true);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        mainPanel = new JPanel();
        spacerPanel = new JPanel();
        bottomPanel = new JPanel();

        JLabel mainTitle = new JLabel("Student and Course Manager");
        mainTitle.setFont(new Font("Sans Serif", Font.BOLD, 20));
        mainPanel.add(mainTitle);

        mainPanel.setLayout(new GridLayout(0,2, 20, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 100, 100));

        JButton createProgButton = new JButton("Create Program Course");
        mainPanel.add(createProgButton);
        createProgButton.addActionListener(e -> {
            optionPanel = new JPanel();
            optionPanel.setLayout(null);
            mainFrame.add(optionPanel);

            optionTitle = items.makeOptionTitle("Create Program Course", 115, 20, 300, 30);
            optionPanel.add(optionTitle);

            JLabel courseNameLBL = items.makeFieldLabel("Course Name: ", 70, 70, 300, 80);
            optionPanel.add(courseNameLBL);

            JTextField courseName = new JTextField();
            courseName.setBounds(180, 100, 160, 25);
            optionPanel.add(courseName);

            JLabel courseCodeLBL = items.makeFieldLabel("Course Code: ",70, 110, 300, 80);
            optionPanel.add(courseCodeLBL);

            JTextField courseCode = new JTextField();
            courseCode.setBounds(180, 140, 160, 25);
            optionPanel.add(courseCode);

            JLabel courseCapacityLBL = items.makeFieldLabel("Course Capacity (40 max): ",70, 150, 300, 80);
            optionPanel.add(courseCapacityLBL);

            JTextField courseCapacity = new JTextField();
            courseCapacity.setBounds(180, 180, 160, 25);
            optionPanel.add(courseCapacity);

            JLabel labSubjLBL = items.makeFieldLabel("Has Lab Hours: ", 70, 190, 300, 80);
            optionPanel.add(labSubjLBL);

            JPanel labSubjBTN = new JPanel();
            labSubjBTN.setBounds(75, 215, 300, 80);
            JRadioButton yesOpt = new JRadioButton("Yes");
            yesOpt.setActionCommand("Yes");
            JRadioButton noOpt = new JRadioButton("No");
            noOpt.setActionCommand("No");
            ButtonGroup labSubjOpt = new ButtonGroup();
            labSubjOpt.add(yesOpt);
            labSubjOpt.add(noOpt);
            labSubjBTN.add(yesOpt);
            labSubjBTN.add(noOpt);
            optionPanel.add(labSubjBTN);

            labSubjBTN.setVisible(true);

            submit = functions.submitAddCourse(courseName, courseCode, courseCapacity, labSubjOpt, optionPanel, mainPanel);
            submit.setBounds(70,250, 125, 30);
            optionPanel.add(submit);
            optionPanel.setComponentZOrder(submit, 0);

            back = items.makeBackButton("Back", optionPanel, mainPanel, 200, 250, 125, 30);
            optionPanel.add(back);
            optionPanel.setComponentZOrder(back, 0);

            mainPanel.setVisible(false);
            bottomPanel.setVisible(false);
            optionPanel.setVisible(true);
        });

        JButton removeProgButton = new JButton("Remove Program Course");
        mainPanel.add(removeProgButton);
        removeProgButton.addActionListener(e ->{
            optionPanel = new JPanel();
            optionPanel.setLayout(null);
            mainFrame.add(optionPanel);

            optionTitle = items.makeOptionTitle("Remove Program Course", 115, 20, 300, 30);
            optionPanel.add(optionTitle);

            JLabel courseCodeLBL = items.makeFieldLabel("Enter Course Code: ", 50, 70, 300, 80);
            optionPanel.add(courseCodeLBL);

            JTextField courseCode = new JTextField();
            courseCode.setBounds(180, 100, 160, 25);
            optionPanel.add(courseCode);

            submit = new JButton("Submit");
            submit.setBounds(70,230, 125, 30);
            optionPanel.add(submit);

            back = items.makeBackButton("Back",optionPanel,mainPanel,200, 230, 125, 30);
            optionPanel.add(back);

            mainPanel.setVisible(false);
            bottomPanel.setVisible(false);
            optionPanel.setVisible(true);

            submit.addActionListener(e1 ->{
                subPanel = new JPanel();
                subPanel.setLayout(null);
                mainFrame.add(subPanel);

                JButton removeCourseBTN = functions.removeProgramCourse(courseCode);
                removeCourseBTN.setBounds(90,80, 220, 30);
                subPanel.add(removeCourseBTN);

                JButton removeFromCourseBTN = new JButton("Remove From Program Course");
                removeFromCourseBTN.setBounds(90,120, 220, 30);
                subPanel.add(removeFromCourseBTN);

                JButton subBack = items.makeBackButton("Back",subPanel,optionPanel,200, 230, 125, 30);
                subPanel.add(subBack);

                optionPanel.setVisible(false);
                bottomPanel.setVisible(false);
                subPanel.setVisible(true);
            });
        });

        JButton searchProgButton = new JButton("Search and Display Program Course");
        mainPanel.add(searchProgButton);
        searchProgButton.addActionListener(e -> {
            optionPanel = new JPanel();
            optionPanel.setLayout(null);
            mainFrame.add(optionPanel);

            optionTitle = items.makeOptionTitle("Search Program Course", 115, 20, 300, 30);
            optionPanel.add(optionTitle);

            JButton showAllBTN = functions.showAllProgramCourses("Show All Program Courses");
            showAllBTN.setBounds(90,80, 220, 30);
            optionPanel.add(showAllBTN);

            JButton byCourseCodeBTN = functions.searchByCourseCode("Search By Course Code", optionPanel, mainFrame);
            byCourseCodeBTN.setBounds(90,120, 220, 30);
            optionPanel.add(byCourseCodeBTN);

            back = items.makeBackButton("Back", optionPanel, mainPanel, 200, 230, 125, 30);
            optionPanel.add(back);

            mainPanel.setVisible(false);
            bottomPanel.setVisible(false);
            optionPanel.setVisible(true);
        });

        JButton addStudentButton = new JButton("Add Student");
        mainPanel.add(addStudentButton);
        addStudentButton.addActionListener(e -> {
            mainFrame.setSize(400, 550);
            optionPanel = new JPanel();
            optionPanel.setLayout(null);
            mainFrame.add(optionPanel);

            optionTitle = items.makeOptionTitle("Add Student", 115, 20, 300, 30);
            optionPanel.add(optionTitle);

            JLabel firstNameLBL = items.makeFieldLabel("First Name: ", 70, 70, 300, 80);
            optionPanel.add(firstNameLBL);

            JTextField firstName = new JTextField();
            firstName.setBounds(180, 100, 160, 25);
            optionPanel.add(firstName);

            JLabel lastNameLBL = items.makeFieldLabel("Last Name: ", 70, 110, 300, 80);
            optionPanel.add(lastNameLBL);

            JTextField lastName = new JTextField();
            lastName.setBounds(180, 140, 160, 25);
            optionPanel.add(lastName);

            JLabel idNumLBL = items.makeFieldLabel("ID Number: ", 70, 150, 300, 80);
            optionPanel.add(idNumLBL);

            JTextField idNum = new JTextField();
            idNum.setBounds(180, 180, 160, 25);
            optionPanel.add(idNum);

            JLabel addrLBL = items.makeFieldLabel("Address: ", 70, 190, 300, 80);
            optionPanel.add(addrLBL);

            JTextField addr = new JTextField();
            addr.setBounds(180, 220, 160, 25);
            optionPanel.add(addr);

            JLabel progEnrolledLBL = items.makeFieldLabel("Program Enrolled: ", 70, 230, 300, 80);
            optionPanel.add(progEnrolledLBL);

            JTextField progEnrolled = new JTextField();
            progEnrolled.setBounds(180, 260, 160, 25);
            optionPanel.add(progEnrolled);

            JLabel yearLvlLBL = items.makeFieldLabel("Year Level: ", 70, 270, 300, 80);
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

            submit = functions.submitAddStudent(firstName, lastName, idNum, addr, progEnrolled, yearLVL, optionPanel, mainPanel);
            submit.setBounds(70,330, 125, 30);
            optionPanel.add(submit);

            back = items.makeBackButton("Back", optionPanel,mainPanel,200, 330, 125, 30);
            optionPanel.add(back);

            mainPanel.setVisible(false);
            bottomPanel.setVisible(false);
            optionPanel.setVisible(true);

            back.addActionListener(e1 -> mainFrame.setSize(400,400));
        });

        JButton removeStudentButton = new JButton("Remove Student");
        mainPanel.add(removeStudentButton);
        removeStudentButton.addActionListener(e -> {
            optionPanel = new JPanel();
            optionPanel.setLayout(null);
            mainFrame.add(optionPanel);

            optionTitle = items.makeOptionTitle("Remove a Student", 115, 20, 300, 30);
            optionPanel.add(optionTitle);

            JLabel idNumLBL = items.makeFieldLabel("Enter ID Number: ", 50, 70, 300, 80);
            optionPanel.add(idNumLBL);

            JTextField idNum = new JTextField();
            idNum.setBounds(180, 100, 160, 25);
            optionPanel.add(idNum);

            submit = new JButton("Submit");
            submit.setBounds(70,230, 125, 30);
            optionPanel.add(submit);

            back = items.makeBackButton("Back",optionPanel,mainPanel,200, 230, 125, 30);
            optionPanel.add(back);

            mainPanel.setVisible(false);
            bottomPanel.setVisible(false);
            optionPanel.setVisible(true);

            submit.addActionListener(e1->{
                subPanel = new JPanel();
                subPanel.setLayout(null);
                mainFrame.add(subPanel);

                JButton removeFromRecordsBTN = functions.removeStudentFromRecords(idNum);
                removeFromRecordsBTN.setBounds(90,80, 220, 30);
                subPanel.add(removeFromRecordsBTN);

                JButton removeFromCourseBTN = new JButton("Remove From Program Course");
                removeFromCourseBTN.setBounds(90,120, 220, 30);
                subPanel.add(removeFromCourseBTN);

                JButton subBack = items.makeBackButton("Back",subPanel,optionPanel,200, 230, 125, 30);
                subPanel.add(subBack);

                optionPanel.setVisible(false);
                bottomPanel.setVisible(false);
                subPanel.setVisible(true);
            });
        });

        JButton updateStudentButton = new JButton("Update Student Information");
        mainPanel.add(updateStudentButton);
        updateStudentButton.addActionListener(e -> {
            optionPanel = new JPanel();
            optionPanel.setLayout(null);
            mainFrame.add(optionPanel);

            optionTitle = items.makeOptionTitle("Update Student Information", 115, 20, 300, 30);
            optionPanel.add(optionTitle);

            JLabel idNumLBL = items.makeFieldLabel("Enter ID Number: ", 50, 70, 300, 80);
            optionPanel.add(idNumLBL);

            JTextField idNum = new JTextField();
            idNum.setBounds(180, 100, 160, 25);
            optionPanel.add(idNum);

            submit = functions.updateStudent("Search", idNum);
            submit.setBounds(70,230, 125, 30);
            optionPanel.add(submit);

            back = items.makeBackButton("Back", optionPanel, mainPanel, 200, 230, 125, 30);
            optionPanel.add(back);

            mainPanel.setVisible(false);
            bottomPanel.setVisible(false);
            optionPanel.setVisible(true);
        });

        JButton enrollButton = new JButton("Enroll Student");
        mainPanel.add(enrollButton);
        enrollButton.addActionListener(e -> {
            optionPanel = new JPanel();
            optionPanel.setLayout(null);
            mainFrame.add(optionPanel);

            optionTitle = items.makeOptionTitle("Enroll Student", 115, 20, 300, 30);
            optionPanel.add(optionTitle);

            JLabel idNumLBL = items.makeFieldLabel("Enter ID Number: ", 50, 70, 300, 80);
            optionPanel.add(idNumLBL);

            JTextField idNum = new JTextField();
            idNum.setBounds(180, 100, 160, 25);
            optionPanel.add(idNum);

            JLabel chooseCourse = items.makeFieldLabel("Choose the course:", 50, 135, 300, 80);
            optionPanel.add(chooseCourse);

            JComboBox<String> courseList = new JComboBox<>();
            courseList.setBounds(180, 165, 160, 25);
            optionPanel.add(courseList);

            JButton enrollBTN = new JButton("Enroll");
            enrollBTN.setBounds(70,230, 125, 30);
            optionPanel.add(enrollBTN);

            back = items.makeBackButton("Back", optionPanel, mainPanel, 200, 230, 125, 30);
            optionPanel.add(back);

            mainPanel.setVisible(false);
            bottomPanel.setVisible(false);
            optionPanel.setVisible(true);
        });

        JButton searchStudentButton = new JButton("Search Student");
        mainPanel.add(searchStudentButton);
        searchStudentButton.addActionListener(e -> {
            optionPanel = new JPanel();
            optionPanel.setLayout(null);
            mainFrame.add(optionPanel);

            optionTitle = items.makeOptionTitle("Search Student", 115, 20, 300, 30);
            optionPanel.add(optionTitle);

            JButton showAllBTN = functions.showAllStudents("Show All Students");
            showAllBTN.setBounds(90,80, 220, 30);
            optionPanel.add(showAllBTN);

            JButton byStudIDBTN = functions.searchByIDNum("Search by ID",optionPanel, mainFrame);
            byStudIDBTN.setBounds(90,120, 220, 30);
            optionPanel.add(byStudIDBTN);

            back = items.makeBackButton("Back", optionPanel, mainPanel, 200, 230, 125, 30);
            optionPanel.add(back);

            mainPanel.setVisible(false);
            bottomPanel.setVisible(false);
            optionPanel.setVisible(true);
        });

        JButton exitProgramButton = new JButton("Exit Program");
        bottomPanel.add(exitProgramButton, BorderLayout.SOUTH);
        exitProgramButton.addActionListener(e -> System.exit(0));

        mainFrame.add(mainPanel, BorderLayout.NORTH);
        mainFrame.add(bottomPanel, BorderLayout.SOUTH);
        mainFrame.setVisible(true);
    }
}