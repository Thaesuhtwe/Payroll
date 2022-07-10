package form;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import modal.Department;
import service.DepartmentService;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.awt.event.ActionEvent;

public class DepartmentForm {

	private JFrame frmDepartment;
	private JTextField txtdeptname;
	private DepartmentService deptService;
	private Department dept;
	private JTable tbldept;
	private DefaultTableModel dtm=new DefaultTableModel();
	private List<Department> deptList=new ArrayList();
	private List<Department> filtereddeptList=new ArrayList();
	private JTextField txtSearch;
	private JTextField txtdeptSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DepartmentForm window = new DepartmentForm();
					window.frmDepartment.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	
	public DepartmentForm() {
		initialize();
		initializeDependency();
		this.setTableDesign();
		this.loadAllDepartment(Optional.empty());
	}

	private void initializeDependency() {
		// TODO Auto-generated method stub
		this.deptService=new DepartmentService();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	
	private void setTableDesign() {
		dtm.addColumn("DepartmentID");
		dtm.addColumn("DepartmentName");
		this.tbldept.setModel(dtm);
	}
	
	private void loadAllDepartment(Optional<List<Department>> optionalDepartments) {
		this.dtm = (DefaultTableModel) this.tbldept.getModel();
		this.dtm.getDataVector().removeAllElements();
		this.dtm.fireTableDataChanged();
		
		this.deptList=this.deptService.findAllDepartments();
		this.filtereddeptList=optionalDepartments.orElseGet(()->this.deptList)
				.stream().collect(Collectors.toList());
	
            
			filtereddeptList.forEach(e-> {
            	Object[] row =new Object [2]; 
            			row[0]=e.getDept_id();
            			row[1]=e.getDept_name();
            	
            	dtm.addRow(row);
            });
			this.tbldept.setModel(dtm);
       
	}
	
	private void resetFormData() {
		txtdeptname.setText("");
		
	}
	
	private void initialize() {
		frmDepartment = new JFrame();
		frmDepartment.getContentPane().setBackground(new Color(112, 128, 144));
		frmDepartment.setBounds(100, 100, 468, 700);
		frmDepartment.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDepartment.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Department");
		lblNewLabel.setForeground(new Color(0, 0, 139));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(157, 30, 129, 25);
		frmDepartment.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(44, 80, 363, 113);
		frmDepartment.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblDeptName = new JLabel("Dept Name");
		lblDeptName.setForeground(new Color(0, 0, 139));
		lblDeptName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDeptName.setBounds(29, 22, 75, 25);
		panel.add(lblDeptName);
		
		txtdeptname = new JTextField();
		txtdeptname.setBounds(147, 26, 171, 25);
		panel.add(txtdeptname);
		txtdeptname.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Department dept=new Department();
				if(!txtdeptname.getText().isEmpty()) {
					dept.setDept_name(txtdeptname.getText());
					deptService.createDepartment(dept);
					loadAllDepartment(Optional.empty());
					resetFormData();
				}else {
					JOptionPane.showMessageDialog(frmDepartment,"Enter required field");
				}
			}
		});
		btnSave.setForeground(new Color(0, 0, 139));
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSave.setBounds(15, 79, 89, 23);
		panel.add(btnSave);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dept.setDept_name(txtdeptname.getText());
				deptService.updateDepartment(String.valueOf(dept.getDept_id()), dept);
				resetFormData();
				loadAllDepartment(Optional.empty());
			}
		});
		btnEdit.setForeground(new Color(0, 0, 139));
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnEdit.setBounds(137, 79, 89, 23);
		panel.add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!txtdeptname.getText().isEmpty()) {
					deptService.deleteDepartment(String.valueOf(dept.getDept_id()));
					resetFormData();
					loadAllDepartment(Optional.empty());
				}
			}
		});
		btnDelete.setForeground(new Color(0, 0, 139));
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnDelete.setBounds(253, 79, 89, 23);
		panel.add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 301, 369, 327);
		frmDepartment.getContentPane().add(scrollPane);
		
		tbldept = new JTable();
		tbldept.setFont(new Font("Tahoma",Font.PLAIN,15));
		scrollPane.setViewportView(tbldept);
		
		txtdeptSearch = new JTextField();
		txtdeptSearch.setBounds(278, 251, 129, 25);
		frmDepartment.getContentPane().add(txtdeptSearch);
		txtdeptSearch.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String keyword=txtdeptSearch.getText();
				loadAllDepartment(
						Optional.of(deptList.stream()
								.filter(dept->dept.getDept_name().toLowerCase()
								.startsWith(keyword.toLowerCase()))
								.collect(Collectors.toList()))
								);
			}
		});
		btnSearch.setForeground(new Color(0, 0, 139));
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSearch.setBounds(168, 251, 89, 23);
		frmDepartment.getContentPane().add(btnSearch);
		
		this.tbldept.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!tbldept.getSelectionModel().isSelectionEmpty()) {

					String id = tbldept.getValueAt(tbldept.getSelectedRow(), 0).toString();

					dept = deptService.finddepartmentById(id);

					txtdeptname.setText(dept.getDept_name());
					

				}
			}
		});
	}
}
