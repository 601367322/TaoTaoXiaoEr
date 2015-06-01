package com.ttxr.bean.request_model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class FormFile {

	private byte[] data;
	private InputStream inStream;
	private File file;
	private String filename;
	private String parameterName;
	private String contentType = "application/octet-stream";

	public FormFile(String filename, byte[] data, String parameterName,
			String contentType) {
		this.data = data;
		this.filename = filename;
		this.parameterName = parameterName;
		if (contentType != null)
			this.contentType = contentType;
	}

	public FormFile(String filename, File file, String parameterName,
			String contentType) {
		this.filename = filename;
		this.parameterName = parameterName;
		this.file = file;
		try {
			this.inStream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
		}
		if (contentType != null)
			this.contentType = contentType;
		try {
			this.data = new byte[inStream.available()];
		} catch (Exception e) {
			this.data = new byte[5242880];
		}
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public InputStream getInStream() {
		return inStream;
	}

	public void setInStream(InputStream inStream) {
		this.inStream = inStream;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

}
