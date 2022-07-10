package shared.mapper;

import java.sql.ResultSet;

import modal.Department;

public class DepartmentMapper {
	public Department mapToDepartment(Department department, ResultSet rs) {
        try {
            department.setDept_id(rs.getInt("dept_id"));
            department.setDept_name(rs.getString("dept_name"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return department;
    }
}
