// base code for student budget assessment
// Students do not need to use this code in their assessment, fine to junk it and do something different!
//
// Your submission must be a maven project, and must be submitted via Codio, and run in Codio
//
// user can enter in wages and loans and calculate total income
//
// run in Codio 
// To see GUI, run with java and select Box Url from Codio top line menu
//
// Layout - Uses GridBag layout in a straightforward way, every component has a (column, row) position in the UI grid
// Not the prettiest layout, but relatively straightforward
// Students who use IntelliJ or Eclipse may want to use the UI designers in these IDEs , instead of GridBagLayout
package Budget;

// Swing imports
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// class definition
public class BudgetBase extends JPanel  {    // based on Swing JPanel

    // high level UI stuff
    JFrame topLevelFrame;  // top-level JFrame
    GridBagConstraints layoutConstraints = new GridBagConstraints(); // used to control layout

    // widgets which may have listeners and/or values
    private JButton calculateButton;   // Calculate button
    private JButton exitButton;        // Exit button
    private JComboBox wagesList;
    private JComboBox loansList;
    private JComboBox interestList;
    private JComboBox taxList;
    private JComboBox rentList;
    private JComboBox foodList;
    private JComboBox netGainList;
    private JTextField wagesField;     // Wages text field
    private JTextField loansField;     // Loans text field
    private JTextField interestField; // interest text field    
    private JTextField taxField;       // Tax text field
    private JTextField rentField;      // Rent text field
    private JTextField foodField;      // Food text field
    private JTextField totalNetGainField; // Total Income field

    // constructor - create UI  (dont need to change this)
    public BudgetBase(JFrame frame) {
        topLevelFrame = frame; // keep track of top-level frame
        setLayout(new GridBagLayout());  // use GridBag layout
        initComponents();  // initalise components
    }

    // initialise componenents
    // Note that this method is quite long.  Can be shortened by putting Action Listener stuff in a separate method
    // will be generated automatically by IntelliJ, Eclipse, etc
    private void initComponents() { 

        // Top row (0) - "INCOME" label
        JLabel incomeLabel = new JLabel("INCOME");
        addComponent(incomeLabel, 0, 0);
        
        // Top row (0) - "EXPENDITURE" label
        JLabel expenditureLabel = new JLabel("EXPENDITURES"); ///NEW
        addComponent(expenditureLabel, 0, 4);

        // Row 1 - Wages label followed by wages textbox
        JLabel wagesLabel = new JLabel("Wages");
        addComponent(wagesLabel, 1, 0);

        // set up text field for entering wages
        // Could create method to do below (since this is done several times)
        wagesField = new JTextField("", 10);   // blank initially, with 10 columns
        wagesField.setHorizontalAlignment(JTextField.RIGHT) ;    // number is at right end of field
        addComponent(wagesField, 1, 1);   

        /////
        String[] recurrenceOptions = new String[] {"Yearly", "Monthly", "Weekly"};
        // wages dropdown
        wagesList = new JComboBox<>(recurrenceOptions);
        addComponent(wagesList, 1, 2);
        // rent dropdown
        rentList = new JComboBox<>(recurrenceOptions);
        addComponent(rentList, 2, 2);
        // loans dropdown
        loansList = new JComboBox<>(recurrenceOptions);
        addComponent(loansList, 3, 2);
        // food dropdown
        foodList = new JComboBox<>(recurrenceOptions);
        addComponent(foodList, 1, 6);
        // interest dropdown
        interestList = new JComboBox<>(recurrenceOptions);
        addComponent(interestList, 2, 6);
        // tax dropdown
        taxList = new JComboBox<>(recurrenceOptions);
        addComponent(taxList, 3, 6);
        // income dropdown
        netGainList = new JComboBox<>(recurrenceOptions);
        addComponent(netGainList, 4, 2);

        // Row 1 - Rent label followed by rent textbox
        JLabel rentLabel = new JLabel("Rent");
        addComponent(rentLabel, 1, 4);

        // set up text box for entering rent
        rentField = new JTextField("", 10);    // blank initially, with 10 columns
        rentField.setHorizontalAlignment(JTextField.RIGHT);     // number is at right end of field
        addComponent(rentField, 1, 5);

        // Row 2 - Loans label followed by loans textbox
        JLabel loansLabel = new JLabel("Loans");
        addComponent(loansLabel, 2, 0);

        // set up text box for entering loans
        loansField = new JTextField("", 10);   // blank initially, with 10 columns
        loansField.setHorizontalAlignment(JTextField.RIGHT) ;    // number is at right end of field
        addComponent(loansField, 2, 1);

        // Row 2  - Food label followed by food textbox
        JLabel foodLabel = new JLabel("Food");
        addComponent(foodLabel, 2, 4);

        // set up text box for entering food expenditure
        foodField = new JTextField("", 10);    // blank initially, with 10 columns
        foodField.setHorizontalAlignment(JTextField.RIGHT);     // number is at right of field
        addComponent(foodField, 2, 5);

        // Row 3 - interest label followed by interest textbox
        JLabel interestLabel = new JLabel("Investments");
        addComponent(interestLabel, 3, 0);

        // set up text box for entering investments income
        interestField = new JTextField("", 10);
        interestField.setHorizontalAlignment(JTextField.RIGHT);
        addComponent(interestField, 3, 1);

        // Row 3 - Tax label followed by tax textbox
        JLabel taxLabel = new JLabel("Tax");
        addComponent(taxLabel, 3, 4);
        
        // set up text box for entering 
        taxField = new JTextField("", 10);
        taxField.setHorizontalAlignment(JTextField.RIGHT);
        addComponent(taxField, 3, 5);

        // Row 4 - Total Income label followed by total income field
        JLabel totalIncomeLabel = new JLabel("Total Income");
        addComponent(totalIncomeLabel, 4, 0);

        // set up text box for displaying total income.  Users cam view, but cannot directly edit it
        totalNetGainField = new JTextField("0", 10);   // 0 initially, with 10 columns
        totalNetGainField.setHorizontalAlignment(JTextField.RIGHT) ;    // number is at right end of field
        totalNetGainField.setEditable(false);    // user cannot directly edit this field (ie, it is read-only)
        addComponent(totalNetGainField, 4, 1);  

        // Row 6 - Calculate Button
        calculateButton = new JButton("Calculate");
        addComponent(calculateButton, 6, 0);  

        // Row 7 - Exit Button
        exitButton = new JButton("Exit");
        addComponent(exitButton, 7, 0);  

        // set up  listeners (in a separate method)
        initListeners();
    }

    // set up listeners
    // initially just for buttons, can add listeners for text fields
    
    private void initListeners() {

        // exitButton - exit program when pressed
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // calculateButton - call calculateTotalIncome() when pressed
        calculateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateTotalIncome();
            }
        });

        // general action listener which which call calculateTotalIncome() when action is performed
        ActionListener calculateJComboListener = new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e){
                calculateTotalIncome();
            }
        };

        //each jcombobox automatically calls the calculateTotalIncome() when edited
        wagesList.addActionListener(calculateJComboListener);
        loansList.addActionListener(calculateJComboListener);
        interestList.addActionListener(calculateJComboListener);
        taxList.addActionListener(calculateJComboListener);
        rentList.addActionListener(calculateJComboListener);
        foodList.addActionListener(calculateJComboListener);
        netGainList.addActionListener(calculateJComboListener);

        javax.swing.event.DocumentListener calculateFieldListener = new javax.swing.event.DocumentListener(){
            public void changedUpdate(javax.swing.event.DocumentListener e){
                calculateTotalIncome();
            }
            public void removeUpdate(javax.swing.event.DocumentListener e){
                calculateTotalIncome();
            }
            public void insertUpdate(javax.swing.event.DocumentListener e){
                calculateTotalIncome();
            }
        };
    }

    // add a component at specified row and column in UI.  (0,0) is top-left corner
    private void addComponent(Component component, int gridrow, int gridcol) {
        layoutConstraints.fill = GridBagConstraints.HORIZONTAL;   // always use horixontsl filll
        layoutConstraints.gridx = gridcol;
        layoutConstraints.gridy = gridrow;
        add(component, layoutConstraints);

    }

    // update totalIncomeField (eg, when Calculate is pressed)
    // use double to hold numbers, so user can type fractional amounts such as 134.50
    public double calculateTotalIncome() {

        // get values from income text fields.  value is NaN if an error occurs
        double wages = getTextFieldValue(wagesField);
        double loans = getTextFieldValue(loansField);
        double interest = getTextFieldValue(interestField);
        double tax = getTextFieldValue(taxField);
        double rent = getTextFieldValue(rentField);
        double food = getTextFieldValue(foodField);

        // clear total field and return if any value is NaN (error)
        if (Double.isNaN(wages) || Double.isNaN(loans) || Double.isNaN(interest) || Double.isNaN(tax) || Double.isNaN(rent) || Double.isNaN(food)) {
            totalNetGainField.setText("");  // clear total income field
            wages = 0.0;
            return wages;   // exit method and do nothing
        }

        // gather *current/most recent* selection of the JComboBox
        String selectedWageTime = (String)wagesList.getSelectedItem();
        String selectedLoansTime = (String)loansList.getSelectedItem();
        String selectedInterestTime = (String)interestList.getSelectedItem();
        String selectedTaxTime = (String)taxList.getSelectedItem();
        String selectedRentTime = (String)rentList.getSelectedItem();
        String selectedFoodTime = (String)foodList.getSelectedItem();

        wages = returnNetGain(selectedWageTime, wages);
        loans = returnNetGain(selectedLoansTime, loans);
        interest = returnNetGain(selectedInterestTime, interest);
        tax = returnNetGain(selectedTaxTime, tax);
        rent = returnNetGain(selectedRentTime, rent);
        food = returnNetGain(selectedFoodTime, food);

        // otherwise calculate total income and update text field
        double totalIncome = wages + loans + interest - (tax + rent + food);
        totalNetGainField.setText(String.format("%.2f",totalIncome));  // format with 2 digits after the .
        return totalIncome;
    }

    private double returnNetGain(String timePeriod, Double money){
        // always returns money per week
        double weeklyAmount;
        if(timePeriod.equals("Yearly")){           
            weeklyAmount = money/52;
        }
        else if(timePeriod.equals("Monthly")){
            weeklyAmount = (money*12)/52;
        }
        else{ // this condition is if "Weekly" is selected in the dropdown menu
            weeklyAmount = money;
        }

        String currentNetGainTime = (String)netGainList.getSelectedItem();

        if (currentNetGainTime.equals("Yearly")) {
            return weeklyAmount*52;
        }
        else if (currentNetGainTime.equals("Monthly")) {
            return (weeklyAmount*52)/12;
        }
        else{
            return weeklyAmount;
        }        
    }

    // return the value if a text field as a double
    // --return 0 if field is blank
    // --return NaN if field is not a number
    private double getTextFieldValue(JTextField field) {

        // get value as String from field
        String fieldString = field.getText();  // get text from text field

        if (fieldString.isBlank()) {   // if text field is blank, return 0
            return 0;
        }

        else {  // if text field is not blank, parse it into a double
            try {
                return Double.parseDouble(fieldString);  // parse field number into a double
             } catch (java.lang.NumberFormatException ex) {  // catch invalid number exception
                JOptionPane.showMessageDialog(topLevelFrame, "Please enter a valid number");  // show error message
                return Double.NaN;  // return NaN to show that field is not a number
            }
        }
    }


// below is standard code to set up Swing, which students shouldnt need to edit much
    // standard mathod to show UI
    private static void createAndShowGUI() {
 
        //Create and set up the window.
        JFrame frame = new JFrame("Budget Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Create and set up the content pane.
        BudgetBase newContentPane = new BudgetBase(frame);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
 
    // standard main class to set up Swing UI
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(); 
            }
        });
    }


}