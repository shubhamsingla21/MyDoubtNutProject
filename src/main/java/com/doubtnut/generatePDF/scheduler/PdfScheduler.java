package com.doubtnut.generatePDF.scheduler;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.doubtnut.generatePDF.entity.QuestionsEntity;
import com.doubtnut.generatePDF.service.PDFGeneratorService;

@Component
public class PdfScheduler {

	@Autowired
	PDFGeneratorService pdfGeneratorService;
	
	@Value("${app.username}")
	 private String username;
	
	@Value("${app.password}")
	 private String password;
	
	@Value("${app.filePath}")
	 private String filePath;
	
	@Scheduled(fixedRate = 5000)
	public void checkMapForExpiry() {
		
	for(Map.Entry<String, QuestionsEntity> entry:PDFGeneratorService.map.entrySet())
	{
		if(LocalDateTime.now().minusMinutes(5).compareTo(entry.getValue().getTimeAtCreation())>=0)
		{
			//System.out.println("line 33"+" "+username+" "+password);
			pdfGeneratorService.writeIntoPDF(entry.getValue(),username,password,filePath);
			PDFGeneratorService.map.remove(entry.getKey());
		}
	}
	
		
	}
}
