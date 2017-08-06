package com.company.demo.itext.example;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.company.demo.itext.bean.PersonBean;
import com.company.demo.itext.utility.PdfUtility;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;

@Component
public class PdfExample {
	
	private static final Logger logger = Logger.getLogger(PdfExample.class);

	private static final Font headerFont = new Font(FontFamily.HELVETICA, 25 ,Font.BOLD, BaseColor.RED);
	
	@Autowired
	private PdfUtility pdfUtility;
	
	
	public void createSimplePdf() throws DocumentException, IOException {
		Properties properties = pdfUtility.getConfig().loadProperties();
		Document document = pdfUtility.createPdfDocument(properties.getProperty("simple_pdf_path"));
		pdfUtility.openDocument(document);
		addHeaderToPdf(document, properties, "simple_pdf_title");
		pdfUtility.closeDocument(document);
	}
	
	public void createPdfWithPersonInfo(PersonBean person) throws IOException, DocumentException {
		Properties properties = pdfUtility.getConfig().loadProperties();
		Document document = pdfUtility.createPdfDocument(properties.getProperty("simple_pdf_person_info_path"));
		pdfUtility.openDocument(document);
		addHeaderToPdf(document, properties, "simple_pdf_title");
		addInfoPerson(document, properties, person);
		pdfUtility.closeDocument(document);
	}
	
	private void addHeaderToPdf(Document document, Properties properties , String headerTitle) throws DocumentException {
		Paragraph header = new Paragraph(properties.getProperty(headerTitle), headerFont);
		header.setAlignment(Element.ALIGN_CENTER);
		document.add(header);
		logger.info("Add header to pdf file");
	}
	
	private void addInfoPerson(Document document, Properties properties, PersonBean person) throws DocumentException {
		Paragraph firstNamePar = new Paragraph(properties.getProperty("simple_pdf_person_info_firstname")+" "+person.getFirstName());
		document.add(firstNamePar);
		pdfUtility.addEmptyLine(document, 1);
		Paragraph lastNamePar = new Paragraph(properties.getProperty("simple_pdf_person_info_lastname")+" "+person.getLastName());
		document.add(lastNamePar);
		pdfUtility.addEmptyLine(document, 1);
		logger.info("Add  personal data info to pdf file");
	}
	
	
}
