package com.parquet.kitaboo.dto;


public class PersonDto {

	public String schemaLoc;
	public String dataFileLoc;
	public String outputFile;
	
	public String getOutputFile() {
		return outputFile;
	}
	public void setOutputFile(String outputFile) {
		this.outputFile = outputFile;
	}
	public PersonDto(String schemaLoc, String dataFileLoc) {
		super();
		this.schemaLoc = schemaLoc;
		this.dataFileLoc = dataFileLoc;
	}
	public String getSchemaLoc() {
		return schemaLoc;
	}
	public void setSchemaLoc(String schemaLoc) {
		this.schemaLoc = schemaLoc;
	}
	public String getDataFileLoc() {
		return dataFileLoc;
	}
	public void setDataFileLoc(String dataFileLoc) {
		this.dataFileLoc = dataFileLoc;
	}
	public PersonDto() {
		super();
	}
	
	
}
