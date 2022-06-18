package com.base;

public class ComparisonData {
	
	private String EmpId,EmpName,Salary,SalaryCreditedByBank,SalaryDeficit;
	
	public ComparisonData(String Id, String Name, String salary, String salaryCreditedByBank, String salaryDeficit) {
		super();
		EmpId = Id;
		EmpName = Name;
		Salary = salary;
		SalaryCreditedByBank = salaryCreditedByBank;
		SalaryDeficit = salaryDeficit;
	}

	@Override
	public String toString() {
		return "CompData [EmpId=" + EmpId + ", EmpName=" + EmpName + ", Salary=" + Salary + ", SalaryCreditedByBank=" + SalaryCreditedByBank + ",SalaryDeficit=" + SalaryDeficit + "]";
	}

}
