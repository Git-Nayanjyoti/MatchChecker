package com.base;

public class ComparisonData {
	
	private String EmpId,EmpName,Salary,SalaryCreditedByBank,SalaryDeficit,SalarySurpluse;
	
	public ComparisonData(String Id, String Name, String salary, String salaryCreditedByBank, String salaryDeficit, String salarySurpluse) {
		super();
		EmpId = Id;
		EmpName = Name;
		Salary = salary;
		SalaryCreditedByBank = salaryCreditedByBank;
		SalaryDeficit = salaryDeficit;
		SalarySurpluse = salarySurpluse;
	}

	@Override
	public String toString() {
		return  EmpId + "," + EmpName + "," + Salary + "," + SalaryCreditedByBank + "," + SalaryDeficit + "," + SalarySurpluse + "\n";
	}

}
