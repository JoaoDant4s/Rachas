package tech.grupo4.java.rachas.service.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import tech.grupo4.java.rachas.service.JwtService;

public class JwtServiceUnitTest {

    private JwtService jwtService;

    @BeforeEach
    public void setUp() {
        jwtService = mock(JwtService.class);
    }

    @Test
    public void testCreateToken() {
        UserDetails userDetails = new User("testUser", "testPassword", Collections.emptyList());
        String expectedToken = "mockedToken";

        when(jwtService.createToken(userDetails)).thenReturn(expectedToken);
        String token = jwtService.createToken(userDetails);

        assertEquals(expectedToken, token);
    }

    @Test
    public void testExtractUsername() {
        String token = "testToken";
        String expectedUsername = "testUser";

        when(jwtService.extractUsername(token)).thenReturn(expectedUsername);

        String extractedUsername = jwtService.extractUsername(token);

        assertEquals(expectedUsername, extractedUsername);
    }

    @Test
    public void testIsTokenValid_ValidToken() {
        String token = "validToken";
        UserDetails userDetails = new User("testUser", "testPassword", Collections.emptyList());

        when(jwtService.isTokenValid(token, userDetails)).thenReturn(true);

        boolean isValid = jwtService.isTokenValid(token, userDetails);

        assertTrue(isValid);
    }

    @Test
    public void testIsTokenValid_ExpiredToken() {
        String token = "expiredToken";

        UserDetails userDetails = new User("testUser", "testPassword", Collections.emptyList());

        when(jwtService.isTokenValid(token, userDetails)).thenReturn(false);

        boolean isValid = jwtService.isTokenValid(token, userDetails);
        assertFalse(isValid);
    }
}
