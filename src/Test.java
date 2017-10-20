import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class Test extends JPanel {
    /**
	 * 
	 */
	ModelData data;
	JTable table;
	private static final long serialVersionUID = 1L;

	public Test() {
        setBounds(50, 50, 300, 200);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        data = new ModelData();    
        table = new JTable();
        add(new JScrollPane(table));
        setVisible(true);
    }
	
	public boolean addData(String grade, String name, int hours) {
		//data.addData(grade, name, hours);
		Object[] newData = {grade, name, hours};
		data.addRow(newData);
		return true;
	}
	
	public boolean clearData() {
		table.setModel(new ModelData());
		return true;
	}

    public static void main(String[] args) {
        new Test();
    }
}

class ModelData extends DefaultTableModel {
    ArrayList<Data> data = new ArrayList<Data>();
    String colNames[] = { "Grade", "Name", "Hours" };
    Class<?> colClasses[] = { String.class, String.class, Integer.class };

    ModelData() {
        data.add(new Data("A+", "history", 3));
//        data.add(new Data("A+", "science", 3));
//        data.add(new Data("A+", "math", 3));
    }
    
    public boolean addData(String grade, String name, int hours) {
 		data.add(new Data(grade, name, hours));
 		System.out.println(data.size());
 		return true;	
     }
  

    public int getRowCount() {
        return this.data.size();
    }
    
    
    public int getColumnCount() {
        return colNames.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return data.get(rowIndex).getGrade();
        }
        if (columnIndex == 1) {
            return data.get(rowIndex).getName();
        }
        if (columnIndex == 2) {
            return data.get(rowIndex).getHours();
        }
        return null;
    }

    public String getColumnName(int columnIndex) {
        return colNames[columnIndex];
    }

    public Class<?> getColumnClass(int columnIndex) {
        return colClasses[columnIndex];
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            data.get(rowIndex).setGrade((String) aValue);
        }
        if (columnIndex == 1) {
            data.get(rowIndex).setName((String) aValue);
        }
        if (columnIndex == 2) {
            data.get(rowIndex).setHours((int) aValue);
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }
}

class Data {
    String grade;
    String name;
    int hours;

    public Data(String grade, String name, int i) {
        super();
        this.grade = grade;
        this.name = name;
        this.hours = i;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
