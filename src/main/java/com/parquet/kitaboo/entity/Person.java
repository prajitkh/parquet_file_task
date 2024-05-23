//package com.parquet.kitaboo.entity;
//
//
//import org.springframework.lang.Nullable;
//
//
//public class Person {
//
//	@Nullable
//    private String name;
//    @Nullable
//    private Integer age;
//    
//    public Person() {
//        // Default constructor required for Avro serialization
//    }
//
//    public Person(String name, Integer age) {
//        this.name = name;
//        this.age = age;
//    }
//
//    // Getters and setters
//    @Nullable
//    public String getName() {
//        return name;
//    }
//
//    public void setName(@Nullable String name) {
//        this.name = name;
//    }
//
//    @Nullable
//    public Integer getAge() {
//        return age;
//    }
//
//    public void setAge(@Nullable Integer age) {
//        this.age = age;
//    }
//
//    // Method to convert Person object to Avro GenericRecord
//   
//
//}
