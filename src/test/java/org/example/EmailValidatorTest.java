package org.example;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class EmailValidatorTest {

    @Test
    public void ensureThatEmailValidatorReturnsTrueForValidEmail() {
        assertTrue(EmailValidator.isValidEmail("aeliseev+1-2%3.goodone@gmail.com"));
    }

    @Test
    void emailValidator_CorrectEmailSubDomain_ReturnsTrue() {
        assertTrue(EmailValidator.isValidEmail("aeliseev@analytics.gmail.com"));
    }

    @Test
    void emailValidator_InvalidEmailNoTld_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail("aeliseev@gmail"));
    }

    @Test
    void emailValidator_InvalidEmailDoubleDot_ReturnsFalse() {
        assertTrue(EmailValidator.isValidEmail("aeliseev..some@gmail.com"));
        assertFalse(EmailValidator.isValidEmail("aeliseev..some@gmail..com"));
    }

    @Test
    void emailValidator_InvalidEmailNoUsername_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail("@gmail.com"));
    }

    @Test
    void emailValidator_EmptyString_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail(""));
    }

    @Test
    void emailValidator_NullEmail_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail(null));
    }

}
