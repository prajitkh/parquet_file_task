package com.parquet.kitaboo.dto;

public class InpOutDto {

	String inputFile;
	String outputFile;
	String schemaLoc;
	public String getSchemaLoc() {
		return schemaLoc;
	}
	public void setSchemaLoc(String schemaLoc) {
		this.schemaLoc = schemaLoc;
	}
	public String getInputFile() {
		return inputFile;
	}
	public void setInputFile(String inputFile) {
		this.inputFile = inputFile;
	}
	public String getOutputFile() {
		return outputFile;
	}
	public void setOutputFile(String outputFile) {
		this.outputFile = outputFile;
	}
	
}
