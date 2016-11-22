package com.ngx20080110.json;

import java.io.File;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;




public class BinaryFile2Json {

	public static void main(String[] args) {
		File pdf = new File("D:\\temp\\BILL161012161254_49895_M.ebill.pdf");
		if (pdf.exists()) {
			try {
				byte[] bytes = FileUtils.readFileToByteArray(pdf);
//				String pdfInString = Base64.getEncoder().encodeToString(bytes);
//				String pdfInString = Base64.encode(bytes);
				String pdfInString = Base64.encodeBase64String(bytes);
				System.out.println(pdfInString);
				
//				FileUtils.writeByteArrayToFile(new File("D:/test.pdf"), Base64.getDecoder().decode(pdfInString));
//				FileUtils.writeByteArrayToFile(new File("D:/test.pdf"), Base64.decode(pdfInString));
//				FileUtils.writeByteArrayToFile(new File("D:/test.pdf"), android.util.Base64.decode(pdfInString, android.util.Base64.DEFAULT));
				FileUtils.writeByteArrayToFile(new File("D:/test.pdf"), Base64.decodeBase64(pdfInString));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			System.out.println("PDF is not exist");;
		}
		
//		File pdfFile = new File("D:\\temp\\BILL161012161254_49895_M.ebill.pdf");
//		Pdf pdf = new Pdf(pdfFile);
//		try {
//			byte[] bytes = FileUtils.readFileToByteArray(pdfFile);
//			String pdfInString = Base64.getEncoder().encodeToString(bytes);
////			System.out.println(pdfInString);
//			pdf.setContent(pdfInString);
//			System.out.println(GsonUtil.object2String(pdf));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

}
