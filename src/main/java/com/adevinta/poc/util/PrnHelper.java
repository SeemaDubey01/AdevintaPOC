package com.adevinta.poc.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.adevinta.poc.model.Customer;


@Component
public class PrnHelper 
 {
	
    public static List<Customer> prnToCustomers(MultipartFile file) throws IOException {
    	
   
        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(
                		file.getInputStream(),
                    Charset.forName("ISO-8859-15")
                ))) {
            List<Customer> customers = br.lines().skip(1).map( l -> {
                try {
                    return createCustomerFromLine(l);
                }catch (RuntimeException | ParseException e) {
                	 throw new RuntimeException(e.getMessage());
                }
            }).filter(Objects::nonNull).collect(Collectors.toList());
            return customers;
        }
    }

    
  
    private static Customer createCustomerFromLine(String line) throws ParseException {
 
        String name = line.substring(0, 16).trim();
        String address = line.substring(16, 38).trim();
        String postcode = line.substring(38, 47).trim();
        String phone = line.substring(47, 62).trim();
        double creditLimit = Integer.parseInt(line.substring(62, 74).trim())/100;
        Date birthday = new SimpleDateFormat("yyyyMMdd").parse(line.substring(74).trim());
        return new Customer(name, address, postcode, phone,  birthday,creditLimit);
    }
    
    
}
