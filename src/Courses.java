import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.Integer;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/* Assumptions
 * grades: A, A+ are 4.0, A- = 3.7, etc.. (follows UVA grading scheme)
 * current GPA is based only on previous and current courses
 * only current and future courses empty courses will be considered for calculating what the grade needs to be
 * (you can't change the past)
 *
 */

public class Courses {
	private JFrame frame;
	private JLabel labelPrevious;
	private JLabel labelCurrent;
	private JLabel labelFuture;
	private JPanel panelPrevious; 
	private JPanel panelFuture;
	private JPanel addPrevious;
	private JPanel addFuture;
	private JComboBox previousHours;
	private JComboBox futureHours;
	private JComboBox previousGrade;
	private JComboBox futureGrade;
	private JComboBox currentHours;
	private JComboBox currentGrade;
	private JTextField previousName;
	private JTextField futureName;
	private JTextField currentName;
	private JTable previousTable;
	private JTable currentTable;
	private JTable futureTable;
	private JButton addPreviousButton;
	private JButton addCurrentButton;
	private JButton addFutureButton;
	private JButton cancelAddPrevious;
	private JButton cancelAddCurrent;
	private JButton cancelAddFuture;
	private JButton submitPrevious;
	private JButton submitCurrent;
	private JButton submitFuture;
	private ActionListener addPreviousListener;
	private JPanel panelCurrent;
	private JPanel addCurrent;
	private JButton removeAllPrevious;
	private JButton removeAllCurrent;
	private JButton removeAllFuture;
	private ActionListener removeAllListener;
	private DefaultTableModel previousTableModel;
	private DefaultTableModel currentTableModel;
	private DefaultTableModel futureTableModel;
	private JButton removePreviousSelected;
	private JButton removeCurrentSelected;
	private JButton removeFutureSelected;
	private JButton add15Previous;
	private JButton add15Current;
	private JButton add15Future;
	private JPanel panelContainer;
	private JPanel panelSummary;
	private JLabel labelSummary;
	private JLabel currentGPA;
	private JButton updateGPA;
	private JTextField targetGPA;
	private JButton submitTarget;
	private JLabel tooFewCourses;
	private JLabel extraCourses;
	public Courses() {
		frame = new JFrame();		
		JPanel panelContainer = new JPanel(new GridLayout(2, 2));
		frame.setTitle("Courses");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// label
		labelPrevious = new JLabel("Previous Courses");
		labelCurrent = new JLabel("Current Courses");
		labelFuture = new JLabel("Future Courses");
		labelSummary = new JLabel("Summary");
		
		//panel
		panelPrevious = new JPanel();
		panelPrevious.setLayout(new BoxLayout(panelPrevious, BoxLayout.Y_AXIS));
		panelCurrent = new JPanel();
		panelCurrent.setLayout(new BoxLayout(panelCurrent, BoxLayout.Y_AXIS));
		panelFuture = new JPanel();
		panelFuture.setLayout(new BoxLayout(panelFuture, BoxLayout.Y_AXIS));
		panelSummary= new JPanel();
		panelSummary.setLayout(new BoxLayout(panelSummary, BoxLayout.Y_AXIS));
		
		// add panels
		addPrevious = new JPanel();
		addPrevious.setLayout(new BoxLayout(addPrevious, BoxLayout.Y_AXIS));
		addCurrent = new JPanel();
		addCurrent.setLayout(new BoxLayout(addCurrent, BoxLayout.Y_AXIS));
		addFuture = new JPanel();
		addFuture.setLayout(new BoxLayout(addFuture, BoxLayout.Y_AXIS));

		// building the tables
		String[] columnNames = {"grade","name","hours"};
		Object[][] data = {{}};
		DefaultTableModel previousTableModel = new DefaultTableModel(data,columnNames);
		DefaultTableModel currentModel = new DefaultTableModel(data, columnNames);
		DefaultTableModel futureModel = new DefaultTableModel(data, columnNames);
		
        JTable previousTable = new JTable(previousTableModel);
        JTable currentTable = new JTable(currentModel);
        JTable futureTable = new JTable(futureModel);
        
        JScrollPane previousPane = new JScrollPane(previousTable);
        JScrollPane currentPane = new JScrollPane(currentTable);
        JScrollPane futurePane = new JScrollPane(futureTable);
        
        
        // 15 credit buttons
        JButton add15Previous = new JButton("Add 15 hrs");
        add15Previous.addActionListener(new ActionListener() {
        		@Override
        		public void actionPerformed(ActionEvent e) {
        			Object[] newRowData = {"", "", 3};
        			for (int i = 0; i < 5; i++) {
        				previousTableModel.addRow(newRowData);
        			}
        		}
        });
        
        JButton add15Current = new JButton("Add 15 hrs");
        add15Current.addActionListener(new ActionListener() {
        		@Override
        		public void actionPerformed(ActionEvent e) {
        			Object[] newRowData = {"", "", 3};
        			for (int i = 0; i < 5; i++) {
        				currentModel.addRow(newRowData);
        			}
        		}
        });
        JButton add15Future = new JButton("Add 15 hrs");
        add15Future.addActionListener(new ActionListener() {
        		@Override
        		public void actionPerformed(ActionEvent e) {
        			Object[] newRowData = {"", "", 3};
        			for (int i = 0; i < 5; i++) {
        				futureModel.addRow(newRowData);
        			}
        		}
        });
        
        
        // selections
		String[] grades = { "A", "B", "C", "D", "F", "N/A" };
		String[] hours = {"1", "2", "3", "4", "5"};
		previousHours = new JComboBox(hours);
		currentHours = new JComboBox(hours);
		futureHours = new JComboBox(hours);
		
		previousGrade = new JComboBox(grades);
		currentGrade = new JComboBox(grades);
		futureGrade = new JComboBox(grades);
		
		previousName = new JTextField("Name");
		currentName = new JTextField("Name");
		futureName = new JTextField("Name");
		
		// add a current
		addCurrentButton = new JButton("Add current class");
		addCurrentButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addCurrent.setVisible(true);
			}
		});
		addPreviousButton = new JButton("Add a previous class");	
		addFutureButton = new JButton("Add a future class");
		
		addFutureButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addFuture.setVisible(true);
			}
		});
		
		addPreviousListener = new HandleAddPrevious();
		addPreviousButton.addActionListener(addPreviousListener);
		
		// cancel the add
		cancelAddPrevious = new JButton("Cancel");
		cancelAddPrevious.setVisible(addPrevious.isVisible());
		cancelAddPrevious.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addPrevious.setVisible(false);
			}
		});
		
		cancelAddCurrent = new JButton("Cancel");
		cancelAddCurrent.setVisible(addCurrent.isVisible());
		cancelAddCurrent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addCurrent.setVisible(false);
			}
		});
		
		cancelAddFuture = new JButton("Cancel");
		cancelAddFuture.setVisible(addFuture.isVisible());
		cancelAddFuture.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addFuture.setVisible(false);
			}
		});
		
		// remove all 
		removeAllPrevious = new JButton("Remove All");
		removeAllPrevious.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				previousTableModel.setRowCount(0);
				//reset GPA
			}
		});
		removeAllCurrent = new JButton("Remove All");
		removeAllCurrent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentModel.setRowCount(0);
				currentGPA.setText("Current GPA:" + getGPA(previousTableModel, currentModel));
			}
		});
		removeAllFuture = new JButton("Remove All");
		removeAllFuture.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				futureModel.setRowCount(0);
				currentGPA.setText("Current GPA:" + getGPA(previousTableModel, currentModel));
			}
		});
		
		// remove selected
		removePreviousSelected = new JButton("Remove Selected");
		removePreviousSelected.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (previousTable.getSelectedRow() != -1) {
					previousTableModel.removeRow(previousTable.getSelectedRow());
					currentGPA.setText("Current GPA:" + getGPA(previousTableModel, currentModel));
			}}
		});

		removeCurrentSelected = new JButton("Remove Selected");
		removeCurrentSelected.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (currentTable.getSelectedRow() != -1) {
					currentModel.removeRow(currentTable.getSelectedRow());
					currentGPA.setText("Current GPA:" + getGPA(previousTableModel, currentModel));
			}}
		});
		
		removeFutureSelected = new JButton("Remove Selected");
		removeFutureSelected.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (futureTable.getSelectedRow() != -1) {
					futureModel.removeRow(futureTable.getSelectedRow());
					currentGPA.setText("Current GPA:" + getGPA(previousTableModel, currentModel));
			}}
		});


		// submitting
		submitPrevious = new JButton("Submit");
		submitCurrent = new JButton("Submit");
		submitFuture = new JButton("Submit");
		
		submitPrevious.addActionListener( new ActionListener() {
			@Override
			 public void actionPerformed(ActionEvent e) {
				String hours = previousHours.getSelectedItem().toString();
				Object[] newRowData = {previousGrade.getSelectedItem().toString(), previousName.getText(), Integer.parseInt(hours)};
				previousTableModel.addRow(newRowData);
				previousHours.setSelectedItem("1");
				previousGrade.setSelectedItem("A");
				previousName.setText("Name");
				currentGPA.setText("Current GPA:" + getGPA(previousTableModel, currentModel));
				addPrevious.setVisible(false);
			}
		});
		submitCurrent.addActionListener(new ActionListener() {
			@Override
			 public void actionPerformed(ActionEvent e) {
				String hours = currentHours.getSelectedItem().toString();
				Object[] newRowData = {currentGrade.getSelectedItem().toString(), currentName.getText(), Integer.parseInt(hours)};
				currentModel.addRow(newRowData);
				currentHours.setSelectedItem("1");
				currentGrade.setSelectedItem("A");
				currentName.setText("Name");
				currentGPA.setText("Current GPA:" + getGPA(previousTableModel, currentModel));
				addCurrent.setVisible(false);
			}
		});
		submitFuture.addActionListener(new ActionListener() {
			@Override
			 public void actionPerformed(ActionEvent e) {
				String hours = futureHours.getSelectedItem().toString();
				Object[] newRowData = {futureGrade.getSelectedItem().toString(), futureName.getText(), Integer.parseInt(hours)};
				futureModel.addRow(newRowData);
				futureHours.setSelectedItem("1");
				futureGrade.setSelectedItem("A");
				futureName.setText("Name");
				currentGPA.setText("Current GPA:" + getGPA(previousTableModel, currentModel));
				addFuture.setVisible(false);
			}
		});
		
		// add buttons to panels
		addPrevious.add(previousHours);
		addPrevious.add(previousGrade);
		addPrevious.add(previousName);
		addPrevious.add(submitPrevious);
		addPrevious.add(add15Previous);
		addPrevious.setVisible(false);
		
		addCurrent.add(currentHours);
		addCurrent.add(currentGrade);
		addCurrent.add(currentName);
		addCurrent.add(submitCurrent);
		addCurrent.add(add15Current);
		addCurrent.setVisible(false);
		
		addFuture.add(futureHours);
		addFuture.add(futureGrade);
		addFuture.add(futureName);
		addFuture.add(submitFuture);
		addFuture.add(add15Future);
		addFuture.setVisible(false);
		
		// add labels
		panelPrevious.add(labelPrevious);
		panelCurrent.add(labelCurrent);
		panelFuture.add(labelFuture);
		
		// add tables
		panelPrevious.add(previousPane);
		panelCurrent.add(currentPane);
		panelFuture.add(futurePane);
		
		panelCurrent.add(addCurrentButton);
		panelPrevious.add(addPreviousButton);
		panelFuture.add(addFutureButton);
		
		panelPrevious.add(removePreviousSelected);
		panelCurrent.add(removeCurrentSelected);
		panelFuture.add(removeFutureSelected);
		
		panelPrevious.add(cancelAddPrevious);
		panelCurrent.add(cancelAddCurrent);
		panelFuture.add(cancelAddFuture);
		
		panelPrevious.add(removeAllPrevious);
		panelCurrent.add(removeAllCurrent);
		panelFuture.add(removeAllFuture);
		
		panelPrevious.add(addPrevious);
		panelCurrent.add(addCurrent);
		panelFuture.add(addFuture);
		
		// summary
		updateGPA = new JButton("recalculate");
		updateGPA.addActionListener(new ActionListener() {
			@Override
			 public void actionPerformed(ActionEvent e) {
				currentGPA.setText("Current GPA:" + getGPA(previousTableModel, currentModel));
			}
			
		});
	
		currentGPA = new JLabel("Current GPA:" + getGPA(previousTableModel, currentModel));
		targetGPA = new JTextField("GPA");
		targetGPA.setMaximumSize(new Dimension(300,20));
		submitTarget = new JButton("submit target GPA");
		submitTarget.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Double targetInput = Double.parseDouble(targetGPA.getText());
				Double currentGPA = getGPA(previousTableModel, currentModel);
				Double currentGradePoints = getGradePoints(previousTableModel, currentModel);
				
				int numBlankUnits = getNumBlankClasses(currentModel, futureModel);
				double requiredGradePoints = currentGPA * currentGradePoints + targetInput * numBlankUnits - currentGradePoints;
				
				System.out.println(requiredGradePoints + numBlankUnits);
				double requiredGPA = requiredGradePoints / numBlankUnits ;
				fillEmptyClasses(requiredGPA, currentModel, futureModel);
				if (requiredGPA > 4.0) {
					tooFewCourses.setVisible(true);
					extraCourses.setVisible(false);
				} else if (requiredGPA < 2.0) {
					extraCourses.setVisible(true);
					tooFewCourses.setVisible(false);
				}
			}
		});
		
		tooFewCourses = new JLabel("Consider adding more classes");
		extraCourses = new JLabel("You may not need to take this many");
		tooFewCourses.setVisible(false);
		extraCourses.setVisible(false);
		panelSummary.add(labelSummary);
		panelSummary.add(currentGPA);
		panelSummary.add(updateGPA);
		panelSummary.add(targetGPA);
		panelSummary.add(submitTarget);
		panelSummary.add(tooFewCourses);
		panelSummary.add(extraCourses);
		panelContainer.add(panelPrevious, 0, 0);
		panelContainer.add(panelCurrent, 1, 0);
		panelContainer.add(panelFuture, 1, 0);
		panelContainer.add(panelSummary, 1, 1);
		frame.add(panelContainer);
		frame.pack();
		frame.setVisible(true);
		
	}
	
	private class HandleAddPrevious implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			addPrevious.setVisible(true);
		}
	}
	
	public double getGPA(DefaultTableModel previousTableModel, DefaultTableModel currentTableModel) {
		int totalHours = 0;
		double cumulativePoints = 0.0;
		for(int i = 1; i < previousTableModel.getRowCount(); i++ ) {
			totalHours = totalHours + (Integer) previousTableModel.getValueAt(i, 2);
			if (previousTableModel.getValueAt(i,  0) == "A") {
				cumulativePoints += (Integer) previousTableModel.getValueAt(i, 2) * 4.0;
			} else if (previousTableModel.getValueAt(i,  0) == "B") {
				cumulativePoints +=  (Integer) previousTableModel.getValueAt(i, 2) * 3.0;
			} else if (previousTableModel.getValueAt(i, 0) == "C") {
				cumulativePoints += (Integer) previousTableModel.getValueAt(i, 2) * 2.0;
			} else if (previousTableModel.getValueAt(i, 0) == "D") {
				cumulativePoints += (Integer) previousTableModel.getValueAt(i, 2) * 1.0;
			}
		}
		
		for (int j = 1; j < currentTableModel.getRowCount(); j++) {
			totalHours = totalHours + (Integer) currentTableModel.getValueAt(j, 2);
			if (currentTableModel.getValueAt(j,  0) == "A") {
				cumulativePoints += (Integer) currentTableModel.getValueAt(j, 2) * 4.0;
			} else if (currentTableModel.getValueAt(j,  0) == "B") {
				cumulativePoints += (Integer) currentTableModel.getValueAt(j, 2) * 3.0;
			} else if (currentTableModel.getValueAt(j, 0) == "C") {
				cumulativePoints += (Integer) currentTableModel.getValueAt(j, 2) * 2.0;
			} else if (currentTableModel.getValueAt(j, 0) == "D") {
				cumulativePoints += (Integer) currentTableModel.getValueAt(j, 2) * 1.0;
			}
		}
		
//		for (int i = 1; i <= futureTableModel.getRowCount(); i++) {
//			totalHours = totalHours + (Integer) futureTableModel.getValueAt(i, 2);
//		}
		
		Double GPA = cumulativePoints / totalHours;
		if (GPA.isNaN()) return 0.0;
		return cumulativePoints / totalHours;		
	}
	
	public int getNumBlankClasses(DefaultTableModel currentModel, DefaultTableModel futureModel) {
		int numBlankUnits = 0;
		for (int i = 0; i < currentModel.getRowCount(); i++ ) {
			if (currentModel.getValueAt(i,  0) == "N/A") {
				numBlankUnits = numBlankUnits + (Integer) currentModel.getValueAt(i, 2);
			}
		}
		for (int j = 0; j < futureModel.getRowCount(); j++) {
			if (futureModel.getValueAt(j, 0) == "N/A") {
				numBlankUnits = numBlankUnits + (Integer) futureModel.getValueAt(j, 2);
			}
		}
		return numBlankUnits;
	}
	
	public double getGradePoints(DefaultTableModel previousTableModel, DefaultTableModel currentTableModel) {
		double cumulativePoints = 0.0;
		for(int i = 0; i < previousTableModel.getRowCount(); i++ ) {
			if (previousTableModel.getValueAt(i,  0) == "A") {
				cumulativePoints += 4.0;
			} else if (previousTableModel.getValueAt(i,  0) == "B") {
				cumulativePoints += 3.0;
			} else if (previousTableModel.getValueAt(i, 0) == "C") {
				cumulativePoints += 2.0;
			} else if (previousTableModel.getValueAt(i, 0) == "D") {
				cumulativePoints += 1.0;
			}
		}
		
		for (int j = 1; j < currentTableModel.getRowCount(); j++) {
			if (currentTableModel.getValueAt(j,  0) == "A") {
				cumulativePoints += 4.0;
			} else if (currentTableModel.getValueAt(j,  0) == "B") {
				cumulativePoints += 3.0;
			} else if (currentTableModel.getValueAt(j, 0) == "C") {
				cumulativePoints += 2.0;
			} else if (currentTableModel.getValueAt(j, 0) == "D") {
				cumulativePoints += 1.0;
			}
		}
		return cumulativePoints;
	}
	
	public void fillEmptyClasses(Double GPA, DefaultTableModel currentModel, DefaultTableModel futureModel) {
		for (int j = 1; j < currentModel.getRowCount(); j++) {
			if (currentModel.getValueAt(j,  0) == "N/A") {
				currentModel.setValueAt(GPA, j, 0);
			}
		}
		for (int i = 1; i < futureModel.getRowCount(); i++) {
			if (futureModel.getValueAt(i,  0) == "N/A") {
				System.out.println("resetting "+ GPA);
				futureModel.setValueAt(GPA, i, 0);
			}
		}
	}
	
	public static void main(String[] args) {
		new Courses();
	}
	
}
