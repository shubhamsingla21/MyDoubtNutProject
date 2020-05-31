package com.doubtnut.generatePDF.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.stereotype.Service;

import com.doubtnut.generatePDF.constants.ResponseConstants;
import com.doubtnut.generatePDF.entity.QuestionsEntity;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

@Service
public class PDFGeneratorService {

	public static ConcurrentHashMap<String, QuestionsEntity> map = new ConcurrentHashMap<>();

	public String convertJsonToPdf(QuestionsEntity json, String userId) {

		json.setTimeAtCreation(LocalDateTime.now());

		map.put(userId, json);

		return ResponseConstants.success;

	}

	public void writeIntoPDF(QuestionsEntity json, String username, String password,String filepath) {

		StringBuilder questions = new StringBuilder("");
		for (int i = 0; i < json.getQuestions().size(); i++) {
			questions = questions.append(json.getQuestions().get(i)).append("\n");
		}
		StringBuilder dest = new StringBuilder(filepath);
		PdfDocument pdf = null;
		try {
			pdf = new PdfDocument(new PdfWriter(dest.toString()));

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Document document = new Document(pdf);
		document.add(new Paragraph(questions.toString()));
		document.close();

		System.out.println("Awesome PDF just got created.");
		sendToGMail(dest, json.getMailId(), username, password);

	}

	public void sendToGMail(StringBuilder dest, String mailId, String username, String password) {

		String from = username;

		String host = "smtp.gmail.com";

		Properties properties = System.getProperties();

		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication(username, password);

			}

		});

		try {

			MimeMessage message = new MimeMessage(session);

			message.setFrom(new InternetAddress(from));

			message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailId));

			message.setSubject("Pdf Related to Question Asked");

			Multipart multipart = new MimeMultipart();

			MimeBodyPart attachmentPart = new MimeBodyPart();

			MimeBodyPart textPart = new MimeBodyPart();

			try {

				File f = new File(dest.toString());

				attachmentPart.attachFile(f);
				textPart.setText("Hi,PFA");
				multipart.addBodyPart(textPart);
				multipart.addBodyPart(attachmentPart);

			} catch (IOException e) {

				e.printStackTrace();

			}

			message.setContent(multipart);

			System.out.println("sending...");
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}

	}

}
