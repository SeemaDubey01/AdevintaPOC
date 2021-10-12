package com.adevinta.poc;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.adevinta.poc.controller.AdevintaPocController;
import com.adevinta.poc.model.Customer;
import com.adevinta.poc.service.AdevintaPocService;
import com.adevinta.poc.util.FileExtension;
@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(AdevintaPocController.class)
public class AdevintaPocControllerTests {
	
	 @Autowired
	 private MockMvc mockMvc;
	 
	 @MockBean
	 private AdevintaPocService service;
	 
	 @MockBean
	 private FileExtension extension;

	
	 @Test
	  public void testCSVFileUpload() throws Exception {
		 MockMultipartFile multipartFile = new MockMultipartFile("file", "test.csv",
			      "UTF-8", "Adevinta POC for CSV upload".getBytes());
		 when(extension.getFileExtension(multipartFile)).thenReturn("csv");
		 this.mockMvc.perform(fileUpload("/uploaddata").file(multipartFile))
		   .andExpect(status().isOk())
		   .andExpect(model().attribute("message", "File successfully uploaded: "+multipartFile.getOriginalFilename()))
		   .andExpect(view().name("uploaddata"));
	  }
	 @Test
	  public void testPRNFileUpload() throws Exception {
		 MockMultipartFile multipartFile = new MockMultipartFile("file", "test.prn",
			      "ISO-8859-15", "Adevinta POC for PRN upload".getBytes());
		 when(extension.getFileExtension(multipartFile)).thenReturn("prn");
		 this.mockMvc.perform(fileUpload("/uploaddata").file(multipartFile))
		   .andExpect(status().isOk())
		   .andExpect(model().attribute("message", "File successfully uploaded: "+multipartFile.getOriginalFilename()))
		   .andExpect(view().name("uploaddata"));
	  }
	 @Test
	  public void testOtherFileUpload() throws Exception {
		 MockMultipartFile multipartFile = new MockMultipartFile("file", "test.txt",
			      "plain/text", "Adevinta POC for PRN upload".getBytes());
		 when(extension.getFileExtension(multipartFile)).thenReturn("txt");
		 this.mockMvc.perform(fileUpload("/uploaddata").file(multipartFile))
		   .andExpect(status().isOk())
		   .andExpect(model().attribute("message", "Could not upload the file, only select .csv or .prn files"))
		   .andExpect(view().name("uploaddata"));
	  }
	 @Test
	  public void testFindAllCustomers() throws Exception {
		 List<Customer> allCustomers = new ArrayList<Customer>();
		 Customer customer1 = new Customer("Tes1", "tesaddress", "1111xx", "123456", new Date(), 2334.55);
		 Customer customer2 = new Customer("Tes2", "tesaddress", "1111xx", "212134", new Date(), 3342.55);
		 allCustomers.add(customer1);
		 allCustomers.add(customer2);
		 when(service.getAllCustomers()).thenReturn(allCustomers);
		 this.mockMvc.perform(get("/showall"))
		   .andExpect(status().isOk())
		   .andExpect(view().name("showall"));
	  }
	 @Test
	  public void testFindCustomerbyName() throws Exception {
		 List<Customer> allCustomers = new ArrayList<Customer>();
		 Customer customer = new Customer("TestSearchName", "tesaddress", "1111xx", "123456", new Date(), 2334.55);
		 allCustomers.add(customer);
		 when(service.getCustomerbyName(customer.getName())).thenReturn(allCustomers);
		 this.mockMvc.perform(get("/searchdata"))
		   .andExpect(status().isOk())
		   .andExpect(view().name("searchdata"));
	  }
	 
	}
