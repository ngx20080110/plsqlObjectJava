package com.ngx20080110.json;

import java.io.File;

public class Pdf {
	private String fileName;
	private String content;
	
	public Pdf(File file) {
		if (file != null && file.exists()) {
			fileName = file.getAbsolutePath();
		}
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "Pdf [fileName=" + fileName + ", content=" + content + "]";
	}
	
}
