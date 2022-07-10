package form;

import modal.Employee;
import service.AuthService;
import service.EmployeeService;
import shared.utils.CurrentUserHolder;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class credentialForm {

    public JFrame credentialFrame;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private Employee employee;
    private EmployeeService employeeService;
    private AuthService authService;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    credentialForm window = new credentialForm();
                    window.credentialFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public credentialForm() {
        initialize();
        initializeDependency();
    }

    private void initializeDependency() {
        this.employeeService = new EmployeeService();
        this.authService = new AuthService();
    }

    public credentialForm(Employee employee) {
        this.employee = employee;
        initialize();
        initializeDependency();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
    	credentialFrame = new JFrame();
    	credentialFrame.getContentPane().setBackground(new Color(112, 128, 144));
    	credentialFrame.setBounds(100, 100, 490, 350);
    	credentialFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	credentialFrame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel(employee != null ? "Employee : " + employee.getEmp_name() : "Login");
        lblNewLabel.setForeground(new Color(0, 0, 139));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel.setBounds(132, 0, 200, 78);
        credentialFrame.getContentPane().add(lblNewLabel);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBackground(new Color(0, 0, 0));
        lblUsername.setForeground(new Color(0, 0, 139));
        lblUsername.setHorizontalAlignment(SwingConstants.LEFT);
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblUsername.setBounds(52, 89, 85, 29);
        credentialFrame.getContentPane().add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtUsername.setColumns(10);
        txtUsername.setBounds(147, 89, 247, 29);
        credentialFrame.getContentPane().add(txtUsername);

        JLabel lblNewLabel_1 = new JLabel("Password");
        lblNewLabel_1.setForeground(new Color(0, 0, 139));
        lblNewLabel_1.setBackground(new Color(0, 0, 0));
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_1.setBounds(52, 163, 67, 20);
        credentialFrame.getContentPane().add(lblNewLabel_1);
        
        txtPassword = new JPasswordField();
        txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtPassword.setBounds(147, 159, 247, 29);
        credentialFrame.getContentPane().add(txtPassword);

        JButton btnLogin = new JButton(employee != null ? "Create Account" : "Login");
        btnLogin.setForeground(new Color(0, 0, 139));
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (null != employee) {
                    employee.setUsername(txtUsername.getText());
                    employee.setPassword(String.valueOf(txtPassword.getPassword()));

                    if (!employee.getUsername().isEmpty() && !employee.getPassword().isEmpty()) {
                        employeeService.updateEmployee(String.valueOf(employee.getEmp_id()), employee);
                        credentialFrame.setVisible(false);
                        EmployeeForm employeeForm = new EmployeeForm();
                        employeeForm.frmEmployee.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Fill required fields");
                    }
                } else {
                    String username = txtUsername.getText();
                    String password = String.valueOf(txtPassword.getPassword());

                    if (!username.isEmpty() && !password.isEmpty()) {
                        String loggedInUserId = authService.login(username, password);
                        System.out.println("hello");
                        if(!loggedInUserId.isEmpty()) {
                            CurrentUserHolder.setLoggedInUser(employeeService.findEmployeeById(loggedInUserId));
                            System.out.println("==========");
                            JOptionPane.showMessageDialog(null, "Successfully Login");
                            credentialFrame.setVisible(false);
                            
                            
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Enter required Fields");
                    }
                }

            }
        });
        btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnLogin.setBounds(147, 212, 184, 40);
        credentialFrame.getContentPane().add(btnLogin);
        
        
    }
}
