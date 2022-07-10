package modal;

import java.awt.Toolkit;
import java.sql.Date;

public abstract class PersonalInfo {
	private int emp_id;

	private String emp_name;

	private String emp_phone;

	private String emp_email;

	private String emp_address;
	
	private String emp_gender;

	public PersonalInfo() {
	}

	public PersonalInfo(int emp_id, String emp_name, String emp_phone, String emp_email, String emp_address,
			String emp_gender) {
		super();
		this.emp_id = emp_id;
		this.emp_name = emp_name;
		this.emp_phone = emp_phone;
		this.emp_email = emp_email;
		this.emp_address = emp_address;
		this.emp_gender = emp_gender;
		
	}

	public int getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

	public String getEmp_phone() {
		return emp_phone;
	}

	public void setEmp_phone(String emp_phone) {
		this.emp_phone = emp_phone;
	}

	public String getEmp_email() {
		return emp_email;
	}

	public void setEmp_email(String emp_email) {
		this.emp_email = emp_email;
	}

	public String getEmp_address() {
		return emp_address;
	}

	public void setEmp_address(String emp_address) {
		this.emp_address = emp_address;
	}

	public String getEmp_gender() {
		return emp_gender;
	}

	public void setEmp_gender(String emp_gender) {
		this.emp_gender = emp_gender;
	}

	

	

	
}

