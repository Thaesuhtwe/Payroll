package form;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

import form.credentialForm;
import modal.Employee;
import service.EmployeeService;

import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JCalendar;

public class EmployeeForm {

	public JFrame frmEmployee;
	private JTextField txtname;
	private JTextField txtphone;
	private JTextField txtemail;
	private JTextField txtaddress;
	private JTextField txtgender;
	private JTable tblEmployee;
	private DefaultTableModel dtm = new DefaultTableModel();
	private JTextField txtSearch;
	private Employee employee;
	private EmployeeService employeeService;
	private List<Employee> originalEmployeeList = new ArrayList<>();
	private List<Employee> employeelist=new ArrayList();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeForm window = new EmployeeForm();
					window.frmEmployee.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EmployeeForm() {
		initialize();
		initializeDependency();
		this.setTableDesign();
		this.loadAllEmployees(Optional.empty());
	}
	 private void initializeDependency() {
	        this.employeeService = new EmployeeService();
	    }
	 private void setTableDesign() {
	       	dtm.addColumn("Name");
	        dtm.addColumn("Phone");
	        dtm.addColumn("Email");
	        dtm.addColumn("Address");
	        dtm.addColumn("Gender");
	        this.tblEmployee.setModel(dtm);
	    }
	 private void resetFormData() {	
	        txtname.setText("");
	        txtphone.setText("");
	        txtemail.setText("");
	        txtaddress.setText("");
	        txtgender.setText("");
	    }
	 private void loadAllEmployees(Optional<List<Employee>> optionalEmployees) {
	        this.dtm = (DefaultTableModel) this.tblEmployee.getModel();
	        this.dtm.getDataVector().removeAllElements();
	        this.dtm.fireTableDataChanged();
	        this.originalEmployeeList = this.employeeService.findAllEmployees();
	        List<Employee> employeeList = optionalEmployees.orElseGet(() -> originalEmployeeList);
	        employeeList.forEach(e -> {
	            Object[] row = new Object[7];
	            row[0] = e.getEmp_id();
	            row[1] = e.getEmp_name();
	            row[2] = e.getEmp_phone();
	            row[3] = e.getEmp_email();
	            row[4] = e.getEmp_address();
	            row[5] = e.getEmp_gender();
	            
	            dtm.addRow(row);
	        });
	        this.tblEmployee.setModel(dtm);
	    }
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEmployee = new JFrame();
		frmEmployee.getContentPane().setBackground(new Color(112, 128, 144));
		frmEmployee.setTitle("Employee");
		frmEmployee.setBounds(100, 100, 996,788);
		frmEmployee.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEmployee.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(230, 230, 250));
		panel.setBounds(91, 79, 795, 297);
		frmEmployee.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(29, 35, 46, 14);
		panel.add(lblNewLabel);
		
		txtname = new JTextField();
		txtname.setBounds(134, 29, 203, 30);
		panel.add(txtname);
		txtname.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Phone");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(431, 35, 46, 14);
		panel.add(lblNewLabel_1);
		
		txtphone = new JTextField();
		txtphone.setColumns(10);
		txtphone.setBounds(540, 29, 223, 30);
		panel.add(txtphone);
		
		JLabel lblNewLabel_2 = new JLabel("E-mail");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(29, 89, 46, 14);
		panel.add(lblNewLabel_2);
		
		txtemail = new JTextField();
		
		txtemail.setColumns(10);
		txtemail.setBounds(134, 83, 203, 30);
		panel.add(txtemail);
		
		JLabel lblNewLabel_3 = new JLabel("Address");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(431, 89, 52, 14);
		panel.add(lblNewLabel_3);
		
		txtaddress = new JTextField();
		txtaddress.setColumns(10);
		txtaddress.setBounds(540, 83, 223, 30);
		panel.add(txtaddress);
		
		JLabel lblNewLabel_4 = new JLabel("Gender");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(29, 140, 67, 14);
		panel.add(lblNewLabel_4);
		
		txtgender = new JTextField();
		txtgender.setColumns(10);
		txtgender.setBounds(134, 134, 203, 30);
		panel.add(txtgender);
		
		JButton btnsave = new JButton("Save");
		btnsave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
				if(!txtname.getText().isEmpty()&& !txtphone.getText().isEmpty()&& !txtemail.getText().isEmpty() && !txtaddress.getText().isEmpty()&& !txtgender.getText().isEmpty()) {
					if(txtphone.getText().matches("[0-9]+") && txtname.getText().matches("[A-Za-z]+") && txtaddress.getText().trim().matches("[A-Za-z]+")&&txtgender.getText().trim().matches("[A-Za-z]+")) {
						Employee employee=new Employee();
						employee.setEmp_name(txtname.getText());
						employee.setEmp_phone(txtphone.getText());
						employee.setEmp_email(txtemail.getText());
						employee.setEmp_address(txtaddress.getText());
						employee.setEmp_gender(txtgender.getText());
						employeeService.createEmployee(employee);
						loadAllEmployees(Optional.empty());
						resetFormData();
					}else if(!txtphone.getText().matches("[0-9]+")) {
						JOptionPane.showMessageDialog(frmEmployee, "Please enter digit");
						return;
					}
					else{
						resetFormData();
						JOptionPane.showMessageDialog(frmEmployee, "Please enter character");
						return;
					}
					
					
				}
				else {
					JOptionPane.showMessageDialog(frmEmployee, "Enter required field");
				}
			}
					
			
		});
		btnsave.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnsave.setBounds(29, 242, 89, 23);
		panel.add(btnsave);
		
		
		
		
		
		
		JButton btncreateAcc = new JButton("Create Account");
		btncreateAcc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (null != employee) {
                    frmEmployee.setVisible(false);
                    credentialForm cForm = new credentialForm(employee);
                    cForm.credentialFrame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Choose Employee");
                }
			}
		});
		btncreateAcc.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btncreateAcc.setBounds(603, 242, 160, 23);
		panel.add(btncreateAcc);
		
		JButton btndelete = new JButton("Delete");
		btndelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (null != employee) {
                    employeeService.deleteEmployee(String.valueOf(employee.getEmp_id()));
                    resetFormData();
                    loadAllEmployees(Optional.empty());
                    employee = null;
                } else {
                    JOptionPane.showMessageDialog(null, "Choose Employee");
                }

			}
		});
		btndelete.setBounds(260, 243, 89, 23);
		panel.add(btndelete);
		
		JButton btnupdate = new JButton("Edit");
		btnupdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

                    employee.setEmp_name(txtname.getText());
                    employee.setEmp_phone(txtphone.getText());
                    employee.setEmp_email(txtemail.getText());
                    employee.setEmp_address(txtaddress.getText());
                    employee.setEmp_gender(txtgender.getText());
             

                    if (!employee.getEmp_name().isEmpty() &&
                            !employee.getEmp_phone().isEmpty() &&
                            !employee.getEmp_email().isEmpty() &&
                            !employee.getEmp_address().isEmpty()&&
                            !employee.getEmp_gender().isEmpty())
                   {

                    	 employeeService.updateEmployee(String.valueOf(employee.getEmp_id()), employee);
                         resetFormData();
                         loadAllEmployees(Optional.empty());
                         employee = null;

                    } else {
                        JOptionPane.showMessageDialog(null, "Enter Required Field");
                    }
                
			}
		});
		btnupdate.setBounds(148, 243, 89, 23);
		panel.add(btnupdate);
		
		
		
		JLabel lblNewLabel_5 = new JLabel("Employee");
		lblNewLabel_5.setBackground(new Color(0, 0, 139));
		lblNewLabel_5.setForeground(new Color(0, 0, 139));
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_5.setBounds(91, 30, 155, 25);
		frmEmployee.getContentPane().add(lblNewLabel_5);
		
		JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(91, 464, 795, 274);
        frmEmployee.getContentPane().add(scrollPane);

        tblEmployee = new JTable();
        tblEmployee.setFont(new Font("Tahoma", Font.PLAIN, 15));
        scrollPane.setViewportView(tblEmployee);
        
        this.tblEmployee.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            if (!tblEmployee.getSelectionModel().isSelectionEmpty()) {
            	String id = tblEmployee.getValueAt(tblEmployee.getSelectedRow(), 0).toString();
                employee = employeeService.findEmployeeById(id);
                txtname.setText(employee.getEmp_name());
                txtphone.setText(employee.getEmp_phone());
                txtemail.setText(employee.getEmp_email());
                txtaddress.setText(employee.getEmp_address());
                txtgender.setText(employee.getEmp_gender());
            }
        });
        JButton btnSearch = new JButton("Search");
        btnSearch.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String keyword=txtSearch.getText();
        		  loadAllEmployees(Optional.of(originalEmployeeList.stream().filter(employee -> employee.getEmp_name().toLowerCase(Locale.ROOT)
                          .startsWith(keyword.toLowerCase(Locale.ROOT))).collect(Collectors.toList())));
        		  
        	}
        });
        btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnSearch.setBounds(800, 404, 89, 25);
        frmEmployee.getContentPane().add(btnSearch);
        
        txtSearch = new JTextField();
        txtSearch.setBounds(594, 400, 184, 31);
        frmEmployee.getContentPane().add(txtSearch);
        txtSearch.setColumns(10);
	}
}
