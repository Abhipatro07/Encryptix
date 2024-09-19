import java.awt.*;
import java.awt.event.*;

public class StudentGradeCalculatorAWT extends Frame implements ActionListener {
    
    private Label subjectLabel, totalMarksLabel, averageLabel, gradeLabel, statusLabel;
    private TextField subjectCountField, marksFields[];
    private Button submitButton;
    private Panel marksPanel;
    private int numSubjects;

    public StudentGradeCalculatorAWT() {
        setLayout(new FlowLayout());

        
        subjectLabel = new Label("Enter number of subjects: ");
        add(subjectLabel);

        subjectCountField = new TextField(5);
        add(subjectCountField);

        submitButton = new Button("Submit");
        add(submitButton);
        
        
        statusLabel = new Label("");
        add(statusLabel);

        submitButton.addActionListener(this);

        setTitle("Student Grade Calculator");
        setSize(400, 400);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            if (marksPanel != null) {
                remove(marksPanel);  
            }

            try {
               
                numSubjects = Integer.parseInt(subjectCountField.getText());
                if (numSubjects <= 0) {
                    statusLabel.setText("Please enter a valid number of subjects.");
                    return;
                }
                
              
                marksPanel = new Panel(new GridLayout(numSubjects + 1, 2, 10, 10));
                marksFields = new TextField[numSubjects];

                for (int i = 0; i < numSubjects; i++) {
                    marksPanel.add(new Label("Marks for subject " + (i + 1) + " (0 - 100) " + ":"));
                    marksFields[i] = new TextField(5);
                    marksPanel.add(marksFields[i]);
                }

                Button calculateButton = new Button("Calculate Result");
                marksPanel.add(calculateButton);
                calculateButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        calculateResult();
                    }
                });

                add(marksPanel);
                validate();
                repaint();
                
            } catch (NumberFormatException ex) {
                statusLabel.setText("Please enter a valid number for subjects.");
            }
        }
    }

    private void calculateResult() {
        int totalMarks = 0;

       
        for (int i = 0; i < numSubjects; i++) {
            try {
                int marks = Integer.parseInt(marksFields[i].getText());
                if (marks < 0 || marks > 100) {
                    statusLabel.setText("Please enter valid marks (0-100) for subject " + (i + 1) + ".");
                    return;
                }
                totalMarks += marks;
            } catch (NumberFormatException e) {
                statusLabel.setText("Please enter valid marks for subject " + (i + 1) + ".");
                return;
            }
        }


        double averagePercentage = totalMarks / (double) numSubjects;
        char grade;
        if (averagePercentage >= 90) {
            grade = 'A';
        } else if (averagePercentage >= 80) {
            grade = 'B';
        } else if (averagePercentage >= 70) {
            grade = 'C';
        } else if (averagePercentage >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }


        if (totalMarksLabel != null) {
            remove(totalMarksLabel);
            remove(averageLabel);
            remove(gradeLabel);
        }

        totalMarksLabel = new Label("Total Marks: " + totalMarks + " / " + (numSubjects * 100));
        averageLabel = new Label("Average Percentage: " + averagePercentage + "%");
        gradeLabel = new Label("Grade: " + grade);

        add(totalMarksLabel);
        add(averageLabel);
        add(gradeLabel);
        
        validate();
        repaint();
    }

    public static void main(String[] args) {
        new StudentGradeCalculatorAWT();
    }
}
