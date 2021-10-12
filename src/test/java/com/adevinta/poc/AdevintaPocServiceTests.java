package com.adevinta.poc;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.adevinta.poc.model.Customer;
import com.adevinta.poc.repository.AdevintaPocRepo;
import com.adevinta.poc.service.AdevintaPocService;
import com.adevinta.poc.util.CSVHelper;
import com.adevinta.poc.util.PrnHelper;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(AdevintaPocService.class)
public class AdevintaPocServiceTests {
	
	 @MockBean
	 private AdevintaPocService service;
	 
	 @MockBean
	 private AdevintaPocRepo repository;
	 

	 
	 @Test
	 public void testsaveCSVDataService() throws Exception{
		 MockMultipartFile multipartFile = new MockMultipartFile("file", "test.csv",
			      "UTF-8", "Adevinta POC for CSV upload".getBytes());
		 List<Customer> customerCSVList = new ArrayList<Customer>();
		 Customer customerFromCSV = new Customer("TestService", "tesaddress", "1111xx", "123456", new Date(), 2334.55);
		 customerCSVList = CSVHelper.csvToCustomers(multipartFile.getInputStream());
		 customerCSVList.add(customerFromCSV);
		 assertEquals(customerCSVList.size(), 1);
		 
	 }
	 
	 @Test
	 public void testsavePRNDataService() throws Exception{
		 MockMultipartFile multipartFile = new MockMultipartFile("file", "test.prn",
			      "UTF-8", "Adevinta POC for PRN upload".getBytes());
		 List<Customer> customerPRNList = new ArrayList<Customer>();
		 Customer customerFromPRN = new Customer("TestService", "tesaddress", "1111xx", "123456", new Date(), 2334.55);
		 customerPRNList = PrnHelper.prnToCustomers(multipartFile);
		 customerPRNList.add(customerFromPRN);
		 assertEquals(customerPRNList.size(), 1);
		 
	 }
	 

		 
	 }
	 


