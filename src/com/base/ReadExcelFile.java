package com.base;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class ReadExcelFile {
	@SuppressWarnings("unchecked")
	public static void main(String[] args)
			throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		List<BankData> BankData;
		List<ClientData> ClientData;
		List<ComparisonData> ComparisonData;
		
		try {
			BankData = new CsvToBeanBuilder<BankData>(
					new FileReader("C:\\Users\\NRABHA\\Documents\\MatchChecker\\Bank.csv")).withType(BankData.class)
							.withSkipLines(0).build().parse();

			ClientData = new CsvToBeanBuilder<ClientData>(
					new FileReader("C:\\Users\\NRABHA\\Documents\\MatchChecker\\EmployeeDetails.csv"))
							.withType(ClientData.class).withSkipLines(0).build().parse();

			ComparisonData = new CsvToBeanBuilder<ComparisonData>(
					new FileReader("C:\\Users\\NRABHA\\eclipse-workspace\\MatchChecker\\Comparison.csv"))
							.withType(ComparisonData.class).withSkipLines(0).build().parse();

			
			System.out.println(ClientData.get(0).getEmpId());
			System.out.println(ClientData.size());
			System.out.println(ComparisonData.size());
			//Comparison.csv Header
			ComparisonData.get(0).setEmpId("Emp_Id");
			ComparisonData.get(0).setEmpName("Emp_Name");
			ComparisonData.get(0).setSalary("Salary");
			ComparisonData.get(0).setSalaryCreditedByBank("Salary_Credited");
			ComparisonData.get(0).setSalaryDeficit("Salary_Deficit");
			

			for (int i = 1; i <= ClientData.size() ; i++) {
				ComparisonData.get(i).setEmpId(BankData.get(i).getEmp_id());
				ComparisonData.get(i).setEmpName(BankData.get(i).getEmp_name());
				ComparisonData.get(i).setSalary(ClientData.get(i).getSalary());
				ComparisonData.get(i).setSalaryCreditedByBank(BankData.get(i-1).getSalary());
				int tempSalary = 0;
				if(Integer.parseInt(ClientData.get(i).getSalary()) > Integer.parseInt(BankData.get(i).getSalary())) {
					tempSalary = Integer.parseInt(ClientData.get(i).getSalary()) - Integer.parseInt(BankData.get(i).getSalary());
				}else if(Integer.parseInt(ClientData.get(i).getSalary()) < Integer.parseInt(BankData.get(i).getSalary())) {
					tempSalary = - Integer.parseInt(ClientData.get(i-1).getSalary()) + Integer.parseInt(BankData.get(i).getSalary());
				}
				ComparisonData.get(i).setSalaryDeficit(Integer.toString(tempSalary));
				tempSalary = 0;

			}
			for(ComparisonData item : ComparisonData) {
				System.out.println(item.getEmpId());
			}

			Writer writer = new FileWriter("Comparison.csv");
			@SuppressWarnings("rawtypes")
			StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
			beanToCsv.write(ComparisonData);
			writer.close();

		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Comparison Data Created in Comparison.csv file");
		}

	}
}
