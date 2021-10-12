package com.adevinta.poc.advice;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdevintaControllerAdvice {

	@ExceptionHandler(value = IOException.class)
	public String handleIOException(Model map, IOException ioe) {
		map.addAttribute("message", "Fail to upload file data: " + ioe.getMessage());
		return "uploaddata";
	}

	@ExceptionHandler(value = RuntimeException.class)
	public String handleRuntimeException(Model map, RuntimeException rue) {
		map.addAttribute("message", "Fail to upload file data: " + rue.getMessage());
		return "uploaddata";
	}

	@ExceptionHandler(value = ParseException.class)
	public String handleParseException(Model map, ParseException pex) {
		map.addAttribute("message", "Fail to upload file data: " + pex.getMessage());
		return "uploaddata";
	}

	@ExceptionHandler(value = NullPointerException.class)
	public String handleNullPointerException(Model map, NullPointerException npe) {
		map.addAttribute("message", "Fail to upload file data: " + npe.getMessage());
		return "uploaddata";
	}

	@ExceptionHandler(value = NumberFormatException.class)
	public String handleNumberformatException(Model map, NumberFormatException nfe) {
		map.addAttribute("message", "Fail to upload file data: " + nfe.getMessage());
		return "uploaddata";
	}

}
