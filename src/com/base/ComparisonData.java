package com.base;

import com.opencsv.bean.CsvBindByPosition;

public class ComparisonData {
	
	@CsvBindByPosition(position = 0)
	private String EmpId;
	
	@CsvBindByPosition(position = 1)
	private String EmpName;
	
	@CsvBindByPosition(position = 2)
	private String Salary;
	
	@CsvBindByPosition(position = 3)
	private String SalaryCreditedByBank;
	
	@CsvBindByPosition(position = 4)
	private String SalaryDeficit;

	public String getEmpId() {
		return EmpId;
	}

	public void setEmpId(String empId) {
		this.EmpId = empId;
	}

	public String getEmpName() {
		return EmpName;
	}

	public void setEmpName(String empName) {
		this.EmpName = empName;
	}

	public String getSalary() {
		return Salary;
	}

	public void setSalary(String salary) {
		this.Salary = salary;
	}

	public String getSalaryCreditedByBank() {
		return SalaryCreditedByBank;
	}

	public void setSalaryCreditedByBank(String salaryCreditedByBank) {
		this.SalaryCreditedByBank = salaryCreditedByBank;
	}

	public String getSalaryDeficit() {
		return SalaryDeficit;
	}

	public void setSalaryDeficit(String salaryDeficit) {
		this.SalaryDeficit = salaryDeficit;
	}


}
