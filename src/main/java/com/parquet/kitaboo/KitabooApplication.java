package com.parquet.kitaboo;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.parquet.kitaboo.service.ParquetService;

@SpringBootApplication
public class KitabooApplication {

	public static void main(String[] args) {
		SpringApplication.run(KitabooApplication.class, args);
		

}
}
