package com.base;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import jdk.dynalink.linker.ConversionComparator.Comparison;

public class ReadExcelFile{
	@SuppressWarnings("unchecked")
	public static void main(String[] args)
			throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		List<BankData> BankData;
		List<ClientData> ClientData;

		try {
			BankData = new CsvToBeanBuilder<BankData>(
					new FileReader("C:\\Users\\NRABHA\\Documents\\MatchChecker\\Bank.csv")).withType(BankData.class)
							.withSkipLines(1).build().parse();

			ClientData = new CsvToBeanBuilder<ClientData>(
					new FileReader("C:\\Users\\NRABHA\\Documents\\MatchChecker\\EmployeeDetails.csv"))
							.withType(ClientData.class).withSkipLines(1).build().parse();

		
			// Comparison.csv Header

			List<ComparisonData> compData = new ArrayList<ComparisonData>();
			Writer writer = new FileWriter("ComparisonData.csv");
			StatefulBeanToCsv sbc = new StatefulBeanToCsvBuilder(writer).withSeparator(CSVWriter.DEFAULT_SEPARATOR).build();
		
//			BufferedWriter bw = new BufferedWriter(writer);

			for (int i = 0; i < ClientData.size(); i++) {
				int tempSalary = 0;
				if (Integer.parseInt(ClientData.get(i).getSalary()) > Integer.parseInt(BankData.get(i).getSalary())) {
					tempSalary = Integer.parseInt(ClientData.get(i).getSalary())
							- Integer.parseInt(BankData.get(i).getSalary());
				} else if (Integer.parseInt(ClientData.get(i).getSalary()) < Integer
						.parseInt(BankData.get(i).getSalary())) {
					tempSalary = -Integer.parseInt(ClientData.get(i).getSalary())
							+ Integer.parseInt(BankData.get(i).getSalary());
				}
				
				compData.add(new ComparisonData(
						BankData.get(i).getEmp_id(),
						BankData.get(i).getEmp_name(),
						ClientData.get(i).getSalary(),
						BankData.get(i).getSalary(),
						Integer.toString(tempSalary)
						));
				
				
				

			}
			sbc.write(compData);
			System.out.println(compData.toString());
			//Create Deficit and surpluse
			
			@SuppressWarnings("rawtypes")
			ColumnPositionMappingStrategy mappingStrategy = new ColumnPositionMappingStrategy();
			mappingStrategy.setType(ComparisonData.class);
			
			String[] columns = new String[] { "EmpId", "EmpName", "Salary", "SalaryCreditedByBank", "SalaryDeficit" };
			mappingStrategy.setColumnMapping(columns);


			
			
			
			//Creating StatefulBeanToCsv object
//			@SuppressWarnings("rawtypes")
//			StatefulBeanToCsvBuilder builder = new StatefulBeanToCsvBuilder(writer);
//			@SuppressWarnings("rawtypes")
//			StatefulBeanToCsv beanWriter = builder.withMappingStrategy(mappingStrategy).build();
//			beanWriter.write(compData);

//			beanWriter.write(compData);
//			StatefulBeanToCsv beantoCsv = new StatefulBeanToCsvBuilder(writer).build();
//			beantoCsv.write(compData);
			writer.close();
			
			System.out.println("Comparison Data Created in Comparison.csv file");

		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
		}

	}
}
