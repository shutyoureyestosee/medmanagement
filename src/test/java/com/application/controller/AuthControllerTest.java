package com.application.controller;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.security.Principal;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.web.WebAppConfiguration;

import com.application.config.JwtTokenHelper;
import com.application.exception.ApiException;
import com.application.model.User;
import com.application.payload.JwtAuthRequest;
import com.application.payload.JwtAuthResponse;
import com.application.payload.UserDto;
import com.application.repository.UserRepository;
import com.application.service.UserService;

@SpringJUnitConfig
@WebAppConfiguration
@SpringBootTest
public class AuthControllerTest {

    @Mock
    private JwtTokenHelper jwtTokenHelper;

    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserService userService;
    
    @InjectMocks
    private AuthController authController;
    
    @Mock
    private UserRepository userRepo;
    
    static ResponseEntity<JwtAuthResponse> response;

    @Test
    void testCreateToken() throws Exception {
        // Arrange
        JwtAuthRequest request = new JwtAuthRequest();
        request.setUsername("testuser");
        request.setPassword("testpassword");
        UserDetails userDetails = Mockito.mock(UserDetails.class);
        User user = Mockito.mock(User.class);
        Mockito.when(userDetailsService.loadUserByUsername(request.getUsername())).thenReturn(userDetails);
        Mockito.when(userDetails.getUsername()).thenReturn(request.getUsername());
        Mockito.when(jwtTokenHelper.generateToken(userDetails)).thenReturn("testtoken");
        Mockito.when(userService.registerNewUser(Mockito.any(UserDto.class))).thenReturn(Mockito.mock(UserDto.class));

        // Act
        try {
        response = authController.createToken(request);
        } catch (Exception e) {
        	System.out.println(e);
        }
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("testtoken", response.getBody().getToken());
    }

    @Test
    void testCreateTokenWithInvalidCredentials() {
        // Arrange
        JwtAuthRequest request = new JwtAuthRequest();
        request.setUsername("testuser");
        request.setPassword("testpassword");
        Mockito.when(authenticationManager.authenticate(Mockito.any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(BadCredentialsException.class);

        // Act and Assert
        assertThrows(ApiException.class, () -> authController.createToken(request));
    }

    @Test
    void testRegisterUser() {
        // Arrange
        UserDto userDto = new UserDto();
        Mockito.when(userService.registerNewUser(Mockito.any(UserDto.class))).thenReturn(userDto);

        // Act
        ResponseEntity<UserDto> response = authController.registerUser(userDto);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(userDto, response.getBody());
    }

    @Test
    void testGetUser() {
        // Arrange
        Principal principal = Mockito.mock(Principal.class);
        User user = new User();
        user.setEmail("testuser@test.com");
        
        Mockito.when(userRepo.findByEmail(Mockito.anyString())).thenReturn(Optional.of(user));

        // Act
        ResponseEntity<UserDto> response = authController.getUser(principal);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("testuser@test.com", response.getBody().getEmail());
    }

}
