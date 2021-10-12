package com.adevinta.poc.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.adevinta.poc.model.Customer;
import com.adevinta.poc.repository.AdevintaPocRepo;
import com.adevinta.poc.util.CSVHelper;
import com.adevinta.poc.util.PrnHelper;

@Service
public class AdevintaPocService {
	@Autowired
	AdevintaPocRepo repository;

	public void saveCSV(MultipartFile file) throws NumberFormatException, ParseException, IOException {
			List<Customer> customers = CSVHelper.csvToCustomers(file.getInputStream());
			repository.saveAll(customers);
	}

	public void savePRN(MultipartFile file) throws IOException {
			List<Customer> customers = PrnHelper.prnToCustomers(file);
			repository.saveAll(customers);
	}

	public List<Customer> getAllCustomers() {
		return repository.findAll();
	}

	public List<Customer> getCustomerbyName(String name) {
		return repository.findAllByName(name);
	}
}