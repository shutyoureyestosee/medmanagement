package com.application.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.application.payload.ApiResponse;
import com.application.payload.UserDto;
import com.application.service.UserService;
import com.application.serviceImpl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserServiceImpl userService;

    @Test
    public void testCreateUser() {
        UserDto userDto = new UserDto();
        userDto.setName("user123");
        userDto.setPassword("password");
        ResponseEntity<UserDto> responseEntity = userController.createUser(userDto);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertThat(responseEntity.getBody().getName()).isEqualTo("user123");
    }

    @Test
    public void testUpdateUser() {
        UserDto userDto = new UserDto();
        userDto.setName("user123");
        userDto.setPassword("password");
        ResponseEntity<UserDto> responseEntity = userController.updateUser(userDto, 1);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertThat(responseEntity.getBody().getName()).isEqualTo("user123");
    }

    @Test
    public void testDeleteUser() {
        ResponseEntity<ApiResponse> responseEntity = userController.deleteUser(1);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertThat(responseEntity.getBody().getMessage()).isEqualTo("User deleted Successfully");
    }

    @Test
    public void testGetAllUsers() {
        UserDto user1 = new UserDto();
        user1.setName("user1");
        user1.setPassword("password1");
        UserDto user2 = new UserDto();
        user2.setName("user2");
        user2.setPassword("password2");
        List<UserDto> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        ResponseEntity<List<UserDto>> responseEntity = userController.getAllUsers();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertThat(responseEntity.getBody()).isEqualTo(userList);
    }

    @Test
    public void testGetSingleUser() {
        UserDto userDto = new UserDto();
        userDto.setName("user123");
        userDto.setPassword("password");
        ResponseEntity<UserDto> responseEntity = userController.getSingleUser(1);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertThat(responseEntity.getBody().getName()).isEqualTo("user123");
    }
}