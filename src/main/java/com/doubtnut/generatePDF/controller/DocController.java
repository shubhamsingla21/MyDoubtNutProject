package com.doubtnut.generatePDF.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.doubtnut.generatePDF.entity.QuestionsEntity;
import com.doubtnut.generatePDF.service.PDFGeneratorService;

@RestController
@RequestMapping("/question")
public class DocController {

	@Autowired
	PDFGeneratorService pdfGeneratorService;
	//ShubhamSingla 
	
	@PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String createDocument(@RequestBody QuestionsEntity json,@RequestParam String userId) {
		return pdfGeneratorService.convertJsonToPdf(json,userId);
	}

}
