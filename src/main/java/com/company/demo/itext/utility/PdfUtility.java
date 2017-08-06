package com.company.demo.itext.utility;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@Component
public class PdfUtility {
	
	private static final Logger logger = Logger.getLogger(PdfUtility.class);
	
	@Autowired
	private ConfigurationUtility config;
	
	/**
	 * Create a pdf file with itext 5
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException
	 * @throws DocumentException
	 */
	public Document createPdfDocument(String fileName) throws FileNotFoundException, DocumentException {
		Document document = new Document();
		FileOutputStream fileOutput = new FileOutputStream(fileName);
		PdfWriter.getInstance(document, fileOutput);
		logger.info("Create pdf file in path"+fileOutput);
		return document;
	}
	
	/**
	 * Open document
	 * @param document
	 */
	public void openDocument(Document document) {
		document.open();
		logger.info("Open document");
	}
	
	/**
	 * Close document
	 * @param document
	 */
	public void closeDocument(Document document) {
		document.close();
		logger.info("Close and save document");
	}
	
	/**
	 * Add metadata info to document
	 * @param document
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	public void addMetaDataToPdf(Document document) throws IOException {
		Properties properties = config.loadProperties();
		document.addAuthor(properties.getProperty("pdf_metadata_author"));
		document.addCreator(properties.getProperty("pdf_metadata_creator"));
		document.addTitle(properties.getProperty("pdf_metadata_title"));
		document.addCreationDate();
		logger.info("Add metadata to pdf file");
	}
	
	public void addEmptyLine(Document document, Integer numEmptyLine) throws DocumentException {
		Paragraph paragraph = new Paragraph();
		
		for (int i=0;i<numEmptyLine;i++){
			paragraph.add(new Paragraph());
		}
		document.add(paragraph);
	}

	public ConfigurationUtility getConfig() {
		return config;
	}

}
