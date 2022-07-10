package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import config.dbconfig;
import modal.Employee;
import modal.UserRole;
import shared.mapper.EmployeeMapper;


public class EmployeeService {

	    private final EmployeeMapper employeeMapper;
	    private final dbconfig dbConfig;

	    public EmployeeService() {
	        this.employeeMapper = new EmployeeMapper();
	        this.dbConfig = new dbconfig();
	    }

	    public void createEmployee(Employee employee) {
	        try {

	            PreparedStatement ps = this.dbConfig.getConnection()
	                    .prepareStatement("INSERT INTO employee (emp_name, emp_phone, emp_email, emp_address, emp_gender) VALUES (?, ?, ?, ?, ?)");

	            ps.setString(1, employee.getEmp_name());
	            ps.setString(2, employee.getEmp_phone());
	            ps.setString(3, employee.getEmp_email());
	            ps.setString(4, employee.getEmp_address());
	            ps.setString(5, employee.getEmp_gender());
	       
	            ps.executeUpdate();
	            ps.close();

	        } catch (Exception e) {
	            if (e instanceof SQLException) {
	                JOptionPane.showMessageDialog(null, "Already Exists");
	            }
	        }
	    }

		public void updateEmployee(String id, Employee employee) {
	        try {
	            PreparedStatement ps = this.dbConfig.getConnection()
	                    .prepareStatement("UPDATE employee SET emp_name=?, emp_phone=?, emp_email=?, emp_address=?, emp_gender=?, username=?, password=? WHERE emp_id=?");

	            ps.setString(1, employee.getEmp_name());
	            ps.setString(2, employee.getEmp_phone());
	            ps.setString(3, employee.getEmp_email());
	            ps.setString(4, employee.getEmp_address());
	            ps.setString(5, employee.getEmp_gender());
	            ps.setString(6, employee.getUsername());
	            ps.setString(7, employee.getPassword());
	            ps.setString(8, id);

	            ps.executeUpdate();
	            ps.close();
	        } catch (Exception e) {
	        	if (e instanceof SQLException)
	                JOptionPane.showMessageDialog(null, "Already Exists");
	            else e.printStackTrace();
	        }
	    }

		public void deleteEmployee(String id) {
			  try {
			        PreparedStatement ps = this.dbConfig.getConnection()
			                .prepareStatement("DELETE FROM employee WHERE emp_id=?");
			        ps.setString(1, id);
			        ps.executeUpdate();
			        ps.close();
			    } catch (Exception e) {

			    	e.printStackTrace();
			    }
		}
		public List<Employee> findAllEmployees() {

	        List<Employee> employeeList = new ArrayList<>();
	        try (Statement st = this.dbConfig.getConnection().createStatement()) {

	            String query = "SELECT * FROM employee";

	            ResultSet rs = st.executeQuery(query);

	            while (rs.next()) {
	                Employee employee = new Employee();
	                employeeList.add(this.employeeMapper.mapToEmployee(employee, rs));
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return employeeList;

	    }

	    public Employee findEmployeeById(String id) {
	        Employee employee = new Employee();

	        try (Statement st = this.dbConfig.getConnection().createStatement()) {

	            String query = "SELECT * FROM employee WHERE emp_id = " + id + ";";

	            ResultSet rs = st.executeQuery(query);

	            while (rs.next()) {
	                this.employeeMapper.mapToEmployee(employee, rs);
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return employee;
	    }


}
