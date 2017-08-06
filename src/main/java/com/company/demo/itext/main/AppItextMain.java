package com.company.demo.itext.main;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.company.demo.itext.bean.PersonBean;
import com.company.demo.itext.example.PdfExample;
import com.itextpdf.text.DocumentException;

public class AppItextMain {
	
	private static final Logger logger = Logger.getLogger(AppItextMain.class);
	
	private static PersonBean getPersonInfo() {
		PersonBean personBean = new PersonBean();
		personBean.setFirstName("Homer");
		personBean.setLastName("Simpson");
		return personBean;
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
		PdfExample pdfExample = (PdfExample) context.getBean(PdfExample.class);
		try {
			pdfExample.createSimplePdf();
			//Pdf with person detail
			logger.info("---------------------");
			pdfExample.createPdfWithPersonInfo(getPersonInfo());
		} catch (DocumentException e) {
			logger.error("Cannot retrieve docuemnt file");
		} catch (IOException e) {
			logger.error("Error in parsing file");
		}

	}

}
