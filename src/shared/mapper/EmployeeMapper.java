package shared.mapper;

import java.sql.ResultSet;

import modal.Employee;

public class EmployeeMapper {
	public Employee mapToEmployee(Employee employee, ResultSet rs) {
        try {
            employee.setEmp_id(rs.getInt("emp_id"));
            employee.setEmp_name(rs.getString("emp_name"));
            employee.setEmp_phone(rs.getString("emp_phone"));
            employee.setEmp_email(rs.getString("emp_email"));
            employee.setEmp_address(rs.getString("emp_address"));
            employee.setEmp_gender(rs.getString("emp_gender"));
            employee.setUsername(rs.getString("username"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employee;
    }
}
