/*
 * Copyright (c) 2019
 * Author: Naomi Bonnin
 * Assignment: CMIS242 Project 4
 * Last Modified 10/7/19, 6:24 PM
 * Description: This is a simple database program with appropriate CRUD operations
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

/*
This is the main GUI class.  It contains a constructor who's purpose is to create and show the GUI.
It also contains several classes to perform the CRUD operations on the rudimentary database.
In addition, there is an action listener class that is overridden to process the button click.
Finally, the class also contains the main method, which serves as an entry point for the program.
 */
public class GUI implements ActionListener {

    //This is the "Database" that the student records are stored in
    private HashMap<Integer, Student> database = new HashMap<>();

    //These are the choices for the 3 comboboxes used by this program
    private String[] actionOptions = {"Insert", "Delete", "Find", "Update"};
    private String[] gradeOptions = {"A", "B", "C", "D", "F"};
    private String[] creditOptions = {"1", "2", "3", "4", "5", "6"};

    //This is the actual window that will display the GUI content
    private JFrame frame = new JFrame("Student Accounts");

    //This initializes the comboboxes with the appropriate options
    private JComboBox<String> action = new JComboBox<>(actionOptions);
    private JComboBox<String> grade = new JComboBox<>(gradeOptions);
    private JComboBox<String> credits = new JComboBox<>(creditOptions);

    //These are the three text fields that need to be read from
    private JTextField id = new JTextField(5);
    private JTextField name = new JTextField(5);
    private JTextField major = new JTextField(5);

    //Private constructor for the GUI.  It also displays the GUI to the user when called
    private GUI() {

        //Ensures that the program terminates when the window is closed.
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Sets the title of the frame
        frame.setTitle("Student Accounts");

        //Ensures that the window is displayed in the center of the screen
        frame.setLocationRelativeTo(null);

        //Sets the layout to GridBagLayout
        frame.setLayout(new GridBagLayout());

        //Creates a GridBagConstraints object as well as defining a few constant constraints
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.insets = new Insets(5, 10, 5, 10);

        //Adds the label for ID at 0,0
        c.gridx = 0;
        c.gridy = 0;
        frame.add(new JLabel("ID Number: "), c);

        //Adds the label for Name at 0,1
        c.gridy = 1;
        frame.add(new JLabel("Name: "), c);

        //Adds the label for Major at 0,2
        c.gridy = 2;
        frame.add(new JLabel("Major: "), c);

        //Adds the label for Action at 0,3
        c.gridy = 3;
        frame.add(new JLabel("Chose Action: "), c);

        //Adds the Submit button at 0,4
        c.gridy = 4;
        JButton submit = new JButton("Submit");
        frame.add(submit, c);
        submit.addActionListener(this);

        //Adds the ID text field at 1,0
        c.gridx = 1;
        c.gridy = 0;
        frame.add(id, c);

        //Adds the Name text field at 1,1
        c.gridy = 1;

        frame.add(name, c);

        //Adds the Major text field at 1,2
        c.gridy = 2;

        frame.add(major, c);

        //Adds the Action Performed combobox at 1,3
        c.gridy = 3;
        frame.add(action, c);

        //Verifies everything is in the proper location and shows the GUI
        frame.pack();
        frame.setVisible(true);

    }

    //The main method.  It simply creates a new GUI object
    public static void main(String[] args) {
        new GUI();
    }

    //This method overrides the actionPerformed method.  In this case this is done to process the submit request.
    //Note that there is inconsistency as to how each method handles the error message.  This is intentional in an
    //effort to show multiple ways that it could be accomplished.  Ideally, the error messages would be their own class
    //and the logic would be handled in the switch statement.  This is not, however, what the project requirements
    //allow.
    @Override
    public void actionPerformed(ActionEvent e) {

        //Continues the theme of using maps to store the values pulled from the textFields
        HashMap<String, String> values = new HashMap<>();

        //Stores the indicated value in the values HashMap
        values.put("id", id.getText());
        values.put("name", name.getText());
        values.put("major", major.getText());

        //Determines which option was selected in the initial comboBox  Ensures that the value is not null
        String selected = (String) action.getSelectedItem();
        assert selected != null;

        //Creates the returned variable.  This will be used later when the CRUD methods finish running
        int returned;

        //Based of off which CRUD operation was chosen, calls the appropriate method.  No base case is defined as it
        //would be impossible for a user to choose anything except those 4 options.
        switch (selected) {
            case "Insert":
                returned = insert(values); //Should return 0 for a successful insertion
                if (returned == 0) {
                    //Shows the inserted student record
                    JOptionPane.showMessageDialog(frame, database.get(Integer.parseInt(values.get("id"))).toString());
                }
                break;
            case "Delete":
                returned = delete(values); //Should return 0 for a successful deletion
                if (returned == 1) {
                    //Displays the error message
                    JOptionPane.showMessageDialog(frame, "Student was not found");
                }
                break;
            case "Update":
                returned = update(values);  //Should return 0 for a successful update of the student record
                if (returned == 0) {
                    //Shows the student record
                    JOptionPane.showMessageDialog(frame, database.get(Integer.parseInt(values.get("id"))).toString());
                }
                break;
            case "Find":
                returned = find(values);  //Should return 0 for a successful find operation
                if (returned == 1) {
                    //Displays the error message
                    JOptionPane.showMessageDialog(frame, "Student was not found");
                }
                break;
        }


    }

    //The insert method does exactly what it says it does.  It inserts a new student record into the database.
    //It takes one argument, a HashMap containing the student record information.
    //If the student record is successfully created the method will return 0, otherwise the method will return 1
    //indicating that a matching entry was already found within the database.  For simplicity sake, a matching record
    //will be defined as a duplicate student ID.
    private int insert(HashMap<String, String> values) {
        if (database.containsKey(Integer.parseInt(values.get("id")))) { //Displays the error message if key is duplicated
            JOptionPane.showMessageDialog(frame, "Please choose a unique student ID");
            return 1;
        }
        //The actual insertion operation
        database.put(Integer.parseInt(values.get("id")), new Student(values.get("name"), values.get("major")));
        return 0;
    }

    //The delete method does exactly what it says it does.  It deletes the student record associated with the student ID
    //provided in the ID field of the GUI.  It takes one argument, a HashMap containing the student record information.
    //Though it would be easy to simply pass this as int or even a string value, it is done this way so that in the
    //future, additional functionality may be added on such as verification of both name and ID number.  It should
    //return 0 for a successful deletion, and return 1 if the record was not found
    private int delete(HashMap<String, String> values) {
        if (database.containsKey(Integer.parseInt(values.get("id")))) {  //Determines if the key is present
            database.remove(Integer.parseInt(values.get("id")));  //Deletes the student record
            JOptionPane.showMessageDialog(frame, "Student was successfully deleted"); //Success message
            return 0;
        }
        return 1;
    }

    //The update method is slightly counterintuitive.  The name implies that the method will update the student record
    //and while this is not untrue, it does not update the students name or major.  Rather, it updates the student
    //record with a completed course.  It takes one argument which is a HashMap containing the values from the GUI.
    //It should return 0 for a successful update and 1 if the student record is not found.  Note that casting is used
    //Since the combobox.getSelectedItem() returns an object and not a string.
    private int update(HashMap<String, String> values) {
        if (database.containsKey(Integer.parseInt(values.get("id")))) { //Determines if there is a matching record
            JOptionPane.showMessageDialog(frame, grade);  //Prompts the user to chose a letter grade
            values.put("grade", (String) grade.getSelectedItem());  //Adds the letter grade to the value HashMap
            JOptionPane.showMessageDialog(frame, credits); //Prompts the user to chose a credit value
            values.put("credits", (String) credits.getSelectedItem());  //Adds the credit value to the HashMap
            //This is the actual command that updates the database.  First, the command retried the appropriate student
            //record from the database.  Secondly, it calls the courseCompleted method from the student class.
            //The courseCompleted methods, takes two values, a int representing the quality points of the grade
            //and an int representing the number of credits taken.  The method gradeToQuality converts the letter
            //to a quality int.
            database.get(Integer.parseInt(values.get("id")))
                    .courseCompleted(gradeToQuality(values.get("grade")), Integer.parseInt(values.get("credits")));
            return 0;
        } else {
            JOptionPane.showMessageDialog(frame, "Student was not found");  //Displays the error message
            return 1;
        }

    }

    //The find method is pretty simple.  It searches through the database for a matching record and if found,
    //displays the student record in a popup.  It accepts one argument, a HashMap containing all of the values.
    //Again, this is overkill however is allows for features to be added in the future such as name lookup.
    //It will return 0 if the record is found or 1 if no matching record is found.
    private int find(HashMap<String, String> values) {
        if (database.containsKey(Integer.parseInt(values.get("id")))) {  //Determines if a record is in the database
            //Displays the student record in a popup
            JOptionPane.showMessageDialog(frame, database.get(Integer.parseInt(values.get("id"))).toString());
            return 0;
        } else {
            JOptionPane.showMessageDialog(frame, "Student was not found");  //Displays the error message
            return 1;
        }

    }

    //A helper method to convert the letter grade to a quality value.  Takes one argument, the String grade and returns
    //the quality points.  If no match is found (which should be impossible) it will return -1
    private int gradeToQuality(String grade) {
        switch (grade) {
            case "A":
                return 4;
            case "B":
                return 3;
            case "C":
                return 2;
            case "D":
                return 1;
            case "F":
                return 0;
        }
        return -1;
    }
}

