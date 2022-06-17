package com.base;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import com.opencsv.CSVWriter;
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
							.withSkipLines(1).build().parse();

			ClientData = new CsvToBeanBuilder<ClientData>(
					new FileReader("C:\\Users\\NRABHA\\Documents\\MatchChecker\\EmployeeDetails.csv"))
							.withType(ClientData.class).withSkipLines(1).build().parse();

			Writer writer = new FileWriter("Comparison.csv");
			StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
			beanToCsv.write(ClientData);
			writer.close();

			for (int i = 0; i < BankData.size(); i++) {
				System.out.println(BankData.get(i).getSalary() + "\t" + ClientData.get(i).getSalary());

			}

		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
}
