package com.adevinta.poc.util;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileExtension {
	
   public String getFileExtension(MultipartFile file) {
	   String extension = "";
	   String fileName = file.getOriginalFilename();
	   int index = fileName.lastIndexOf('.');
	      if(index > 0) {
	        extension = fileName.substring(index + 1);
	      }
	      return extension;
   }
}
