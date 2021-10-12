package com.adevinta.poc.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.adevinta.poc.model.Customer;
import com.adevinta.poc.service.AdevintaPocService;
import com.adevinta.poc.util.FileExtension;

@Controller
public class AdevintaPocController {
	@Autowired
	AdevintaPocService fileService;
	
	@Autowired
	FileExtension fileExtension;
	
	@GetMapping({"/", "/home"})
	private String showForm() {
		return "index";
	}
	
	@GetMapping("/uploaddata")
	public String getupload() {
		return "uploaddata";
	}
	
	@PostMapping("/uploaddata")
	  public String uploadFile(@RequestParam("file") MultipartFile file
			  ,Model model) throws NumberFormatException, ParseException, IOException {
	    String message = "";
        String extension = fileExtension.getFileExtension(file);
	    if (extension.equalsIgnoreCase("csv")) {
	        fileService.saveCSV(file);
	        message = "File successfully uploaded: " + file.getOriginalFilename();
	        model.addAttribute("message",message);
	        return "uploaddata";
	      
	    }
        
        else if(extension.equalsIgnoreCase("prn")) {
        	fileService.savePRN(file);
        	model.addAttribute("message","File successfully uploaded: " + file.getOriginalFilename());
        	return "uploaddata";
	    }
        else {
        	model.addAttribute("message", "Could not upload the file, only select .csv or .prn files");
        	return "uploaddata";	
        }
	    
	  }
	
	@GetMapping("/showall")
	public String showCustomerList(Model model) {
		List <Customer> customers =  fileService.getAllCustomers();
		model.addAttribute("customers", customers);
		return "showall";
	}
	
	@GetMapping("/searchdata")
	public String searchCustomerDetail(String cname, Model model) {
		List<Customer> customers = fileService.getCustomerbyName(cname);
		if(!customers.isEmpty() ) {
			model.addAttribute("customers", customers);
		} else {
			if (null != cname)
				model.addAttribute("message", "No records found for this customer");
		}
		return "searchdata";
	}
	
	

}
