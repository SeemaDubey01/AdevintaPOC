package com.adevinta.poc.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import com.adevinta.poc.model.Customer;
@Component
public class CSVHelper {

	public static List<Customer> csvToCustomers(InputStream is) throws NumberFormatException, ParseException, IOException {
		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				CSVParser csvParser = new CSVParser(fileReader,
						CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

			List<Customer> customers = new ArrayList<Customer>();

			Iterable<CSVRecord> csvRecords = csvParser.getRecords();

			for (CSVRecord csvRecord : csvRecords) {
				Customer tutorial = new Customer(csvRecord.get("Name"), csvRecord.get("Address"),
						csvRecord.get("PostCode"), csvRecord.get("Phone"),
						new SimpleDateFormat("dd/MM/yyyy").parse(csvRecord.get("Birthday")),
						Double.parseDouble(csvRecord.get("Credit Limit")));

				customers.add(tutorial);
			}

			return customers;
		} 
	}
}
