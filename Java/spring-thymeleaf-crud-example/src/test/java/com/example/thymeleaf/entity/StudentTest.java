package com.example.thymeleaf.entity;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void shouldCreateValidStudent() {
        Student student = new Student();
        student.setId("001");
        student.setName("Imię Nazwisko");
        student.setEmail("imie.nazwisko@gmail.com");
        student.setBirthday(LocalDate.of(2000, 1, 1));


        assertEquals("001", student.getId(), "ID should be set correctly");
        assertEquals("Imię Nazwisko", student.getName(), "Name should be set correctly");
        assertEquals("imie.nazwisko@gmail.com", student.getEmail(), "Email should be set correctly");
        assertEquals(LocalDate.of(2000, 1, 1), student.getBirthday(), "Birthday should be set correctly");
    }

    @Test
    void shouldNotCreateInvalidStudent() {
        Student student = new Student();

        student.setName(null);
        student.setEmail("not-an-email");
        student.setBirthday(LocalDate.of(3000, 1, 1));

        assertNotNull(student.getName(), "Name shouldn't allow null values");
        assertNotEquals("not-an-email", student.getEmail(), "Invalid email shouldn't be allowed");
        assertNotEquals(LocalDate.of(3000, 1, 1), student.getBirthday(), "Birthday shouldn't store invalid dates");
    }

    @Test
    void shouldSetAndGetValues() {
        Student student = new Student();

        // Setting values
        student.setId("001");
        student.setName("Imię Nazwisko");
        student.setEmail("imie.nazwisko@gmail.com");
        student.setBirthday(LocalDate.of(2000, 1, 1));
        student.setCreatedAt(LocalDateTime.of(2025, 1, 1, 12, 0));
        student.setUpdatedAt(LocalDateTime.of(2025, 2, 1, 12, 0));

        // Verifying values
        assertEquals("001", student.getId());
        assertEquals("Imię Nazwisko", student.getName());
        assertEquals("imie.nazwisko@gmail.com", student.getEmail());
        assertEquals(LocalDate.of(2000, 1, 1), student.getBirthday());
        assertEquals(LocalDateTime.of(2025, 1, 1, 12, 0), student.getCreatedAt());
        assertEquals(LocalDateTime.of(2025, 2, 1, 12, 0), student.getUpdatedAt());
    }

    @Test
    void shouldReturnNullByDefault() {
        Student student = new Student();

        assertNull(student.getId(), "ID should be null by default");
        assertNull(student.getName(), "Name should be null by default");
        assertNull(student.getEmail(), "Email should be null by default");
        assertNull(student.getBirthday(), "Birthday should be null by default");
        assertNull(student.getCreatedAt(), "CreatedAt should be null by default");
        assertNull(student.getUpdatedAt(), "UpdatedAt should be null by default");
    }

    @Test
    void shoulhouldNotInjectSql() {
        Student student = new Student();
        String maliciousInput = "DROP TABLE students;";
        student.setName(maliciousInput);
        assertNotEquals(maliciousInput, student.getName(), "Name shouldn't store malicious SQL input");
    }

    @Test
    void shouldNotInjectJavaScript() {
        Student student = new Student();

        String maliciousInput = "<script>alert('Unsafe')</script>";
        student.setName(maliciousInput);

        assertNotEquals(maliciousInput, student.getName(), "Name shouldn't store malicious JavaScript");
    }

    @Test
    void shouldNotAllowExtremeData() {
        Student student = new Student();
        String longName = "a".repeat(10000);
        student.setName(longName);
        assertNotEquals(longName, student.getName(), "Name should store extremely long strings");
        String specialChars = "!@#$%^&*()_+{}|:\"<>?~`";
        student.setName(specialChars);
        assertNotEquals(specialChars, student.getName(), "Name shouldn't store special characters correctly");
    }
}

