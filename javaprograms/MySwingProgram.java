import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

class MySwingProgram extends JFrame implements ActionListener {
    ArrayList<String> records = new ArrayList<>();
    int operator = -1;
    PrintWriter pw;

    JLabel l1 = new JLabel("Enter name");
    JLabel l2 = new JLabel("Enter age");
    JLabel l3 = new JLabel("Enter salary");

    JTextField t1 = new JTextField(20);
    JTextField t2 = new JTextField(20);
    JTextField t3 = new JTextField(20);

    JButton b1 = new JButton("Save");
    JButton b2 = new JButton("First");
    JButton b3 = new JButton("Last");
    JButton b4 = new JButton("Next");
    JButton b5 = new JButton("Previous");
    JButton b6 = new JButton("Delete");
    JButton b7 = new JButton("Update");
    JButton b8 = new JButton("Search");

    MySwingProgram() {
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close app when window closes
        // make sure the file writer is closed when the window goes away
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                if (pw != null) {
                    pw.close();
                }
            }
        });
        setVisible(true);
        setLayout(new FlowLayout());

        add(l1); add(t1);
        add(l2); add(t2);
        add(l3); add(t3);
        add(b1); add(b2); add(b3); add(b4);
        add(b5); add(b6); add(b7); add(b8);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);
        b8.addActionListener(this);

       
        try {
            BufferedReader br = new BufferedReader(new FileReader("data.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                records.add(line);
            }
            br.close();
            if (!records.isEmpty()) {
                operator = 0;
                showRecord(operator);
            }
            pw = new PrintWriter(new FileWriter("data.txt", true));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void actionPerformed(ActionEvent e) {
        JButton o = (JButton) e.getSource();

        try {
            String name = t1.getText();
            String age = t2.getText();
            String salary = t3.getText();

            if (o == b1) { // Save
                // build record and persist
                String rec = name + " " + age + " " + salary;
                pw.println(rec);
                pw.flush();

                // keep in-memory list in sync so navigation works immediately
                records.add(rec);
                operator = records.size() - 1;

                // clear fields so user can enter next entry
                clear();
            } else if (o == b2) { // First
               if (records != null && !records.isEmpty()) {
                    operator = 0;
                    showRecord(operator);
                }
            } else if (o == b3) { // Last
                if (records!=null && !records.isEmpty()) {
                    operator = records.size() - 1;
                    showRecord(operator);
                }
            } else if (o == b4) { // Next
                if (operator < records.size() - 1) {
                    operator++;
                    showRecord(operator);
                }
            } else if (o == b5) { // Previous
                if (operator > 0) {
                    operator--;
                    showRecord(operator);
                }
            } else if (o == b6) { // Delete
                if (!records.isEmpty() && operator >= 0) {    
                    records.remove(operator);
                    saveAllRecords(); 
                    if (records.isEmpty()) {
                        clear();
                        operator = -1;
                    } else if (operator >= records.size()) {
                        operator = records.size() - 1;
                        showRecord(operator);
                    } else {
                        showRecord(operator);
                    }
                }
            } else if (o == b7) { // Update
                if (!records.isEmpty() && operator >= 0) {
                    String rec = name + " " + age + " " + salary;
                    records.set(operator, rec);
                    saveAllRecords();
                    showRecord(operator);
                }
            } else if (o == b8) { // Search
                // look for a record whose first token matches the name field
                if (name.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter a name to search.");
                } else {
                    boolean found = false;
                    for (int i = 0; i < records.size(); i++) {
                        String rec = records.get(i);
                        StringTokenizer st = new StringTokenizer(rec);
                        if (st.hasMoreTokens()) {
                            String n = st.nextToken();
                            if (n.equalsIgnoreCase(name.trim())) {
                                operator = i;
                                showRecord(operator);
                                found = true;
                                break;
                            }
                        }
                    }
                    if (!found) {
                        JOptionPane.showMessageDialog(this, "Name not found.");
                    }
                }
            }
            

        } catch (Exception e1) {
            System.out.println(e1);
        }
    }

    void showRecord(int data) {
        if (data >= 0 && data < records.size()) {
            String rec = records.get(data);
            StringTokenizer st = new StringTokenizer(rec);
            t1.setText(st.hasMoreTokens() ? st.nextToken() : "");
            t2.setText(st.hasMoreTokens() ? st.nextToken() : "");
            t3.setText(st.hasMoreTokens() ? st.nextToken() : "");
        }
    }

    void clear() {
        t1.setText("");
        t2.setText("");
        t3.setText("");
    }

    void saveAllRecords() {
        try {
            PrintWriter pw2 = new PrintWriter(new FileWriter("data.txt"));
            for (String rec : records) {
                pw2.println(rec);
            }
            pw2.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String args[]) {
        new MySwingProgram();
    }
}