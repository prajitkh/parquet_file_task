package com.parquet.kitaboo.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.avro.Schema;
import org.apache.avro.Schema.Parser;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.hadoop.conf.Configuration;
import org.apache.parquet.avro.AvroParquetReader;
import org.apache.parquet.avro.AvroParquetWriter;
import org.apache.parquet.hadoop.ParquetReader;
import org.apache.parquet.hadoop.ParquetWriter;
import org.apache.parquet.hadoop.metadata.CompressionCodecName;
import org.springframework.stereotype.Service;

@Service
public class ParquetService {

	public void createParquet(String schemaLoc, String dataFileLoc, String outputFile) throws IOException {

		String schemaStr = Files.readString(Path.of(schemaLoc));
		Schema schema = new Schema.Parser().parse(schemaStr);
		String jsonDataString = Files.readString(Path.of(dataFileLoc));
		DecoderFactory df = new DecoderFactory();
		Decoder dc = df.jsonDecoder(schema, jsonDataString);
		DatumReader<GenericRecord> reader = new SpecificDatumReader<>(schema);
		GenericRecord person = reader.read(null, dc);
		org.apache.hadoop.fs.Path outputFilePath = new org.apache.hadoop.fs.Path(outputFile);
		ParquetWriter<GenericRecord> writer = new AvroParquetWriter<>(outputFilePath, schema);
		writer.write(person);

		writer.close();
	}

	public void readParquet(String outputFile) throws IOException {
		try {
			org.apache.hadoop.fs.Path outputPath = new org.apache.hadoop.fs.Path(outputFile);
			ParquetReader<GenericRecord> reader = new AvroParquetReader<>(outputPath);
			GenericRecord gr = null;
			while ((gr = reader.read()) != null) {
				System.out.println(gr.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateParquetFile(String inputFile, String outputFile, String schemaLoc) throws IOException {
		// Create configuration
		Configuration configuration = new Configuration();
		configuration.set("fs.file.impl", org.apache.hadoop.fs.LocalFileSystem.class.getName());

		Parser parser = new Parser();
		Schema schema = parser.parse(new File(schemaLoc));
		// Open Parquet reader
		org.apache.hadoop.fs.Path inputPath = new org.apache.hadoop.fs.Path(inputFile);
		ParquetReader<GenericRecord> reader = AvroParquetReader.<GenericRecord>builder(inputPath)
				.withConf(configuration).build();

		// Open Parquet writer
		org.apache.hadoop.fs.Path outputPath = new org.apache.hadoop.fs.Path(outputFile);
		ParquetWriter<GenericRecord> writer = AvroParquetWriter.<GenericRecord>builder(outputPath).withSchema(schema) // Update
																														// with
																														// your
																														// schema
				.withCompressionCodec(CompressionCodecName.SNAPPY).withConf(configuration).build();

		try {
			// Read and update each record
			GenericRecord record;
			while ((record = reader.read()) != null) {
				// Modify the record as needed
				// For example, update a field:
				record.put("name", "updatedValue");

				// Write the modified record to the output file
				writer.write(record);
			}
		} finally {
			// Close reader and writer
			reader.close();
			writer.close();
		}
	}

	public void deleteParquetFile(String filePath) throws IOException {
		Path fileToDelete = Paths.get(filePath);

		// Check if the file exists
		if (Files.exists(fileToDelete)) {
			// Delete the file
			Files.delete(fileToDelete);
			System.out.println("Parquet file deleted successfully.");

		} else {

			System.out.println("Parquet file does not exist at the specified location.");

		}
	}

}
