package com.oscar.usuario.controller;

import com.oscar.usuario.dto.UserDto;
import com.oscar.usuario.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/User")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class UserController {

    private IUserService userService;
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Get a user by their email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @GetMapping("/{userEmail}")
    public ResponseEntity<UserDto> getUserByEmail( @PathVariable("userEmail") String userEmail) {
        return ResponseEntity.ok(userService.getUserByEmail(userEmail));
    }

    @Operation(summary = "Add a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created", content = @Content),
            @ApiResponse(responseCode = "409", description = "User already exists", content = @Content)
    })
    @PostMapping("/save")
    public ResponseEntity<UserDto> saveUser(  @RequestBody UserDto userDto) {
        userService.saveUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Update an existing user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @PutMapping("/update")
    public ResponseEntity<Void> updateUser( @RequestBody UserDto userDto) {
        userService.updateUser(userDto);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update an existing user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @DeleteMapping("/delete/{userEmail}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userEmail") String userEmail) {
        userService.deleteUser(userEmail);
        return ResponseEntity.noContent().build();
    }
}
