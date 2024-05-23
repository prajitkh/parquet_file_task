package com.parquet.kitaboo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.parquet.kitaboo.dto.InpOutDto;
import com.parquet.kitaboo.dto.PersonDto;
import com.parquet.kitaboo.service.ParquetService;

@RestController
@RequestMapping("/api")
public class ParquetController {

	@Autowired
	private ParquetService parquetService;

	@GetMapping
	public ResponseEntity<?> readToParquet(@RequestParam String inputFile) {
		try {
			parquetService.readParquet(inputFile);
			return new ResponseEntity<>("Reading data to parquet file successful", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			String errorMessage = "An error occurred while reading data to the Parquet file.";
			return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
			// Handle exception appropriately
		}
	}

	@PostMapping
	public ResponseEntity<?> createParquet(@RequestBody PersonDto person) {
		try {
			parquetService.createParquet(person.getSchemaLoc(), person.getDataFileLoc(), person.getOutputFile());
			return new ResponseEntity<>("creating a parquet file and Writing data to parquet file successful",
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			String errorMessage = "An error occurred while creating a parquet file and writing data to the Parquet file.";
			return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
			// Handle exception appropriately
		}
	}

	@PutMapping
	public ResponseEntity<?> updateParquet(@RequestBody InpOutDto io) {
		try {
			parquetService.updateParquetFile(io.getInputFile(), io.getOutputFile(), io.getSchemaLoc());
			return new ResponseEntity<>("updating data to parquet file successful", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			String errorMessage = "An error occurred while updating data to the Parquet file.";
			return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
			// Handle exception appropriately
		}
	}

	@DeleteMapping
	public ResponseEntity<?> deleteParquet(@RequestParam String filePath) {
		try {
			parquetService.deleteParquetFile(filePath);
			return new ResponseEntity<>("deleting parquet file successful", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			String errorMessage = "An error occurred while deleting to the Parquet file.";
			return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
			// Handle exception appropriately
		}
	}
}
