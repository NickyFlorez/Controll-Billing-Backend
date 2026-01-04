package com.nicky.controlBilling.infrastructure.controller.user;

import com.nicky.controlBilling.domain.use_case.user.LoginUserUseCase;
import com.nicky.controlBilling.domain.use_case.user.RegisterUserUseCase;
import com.nicky.controlBilling.infrastructure.controller.dto.request.LoginUserDto;
import com.nicky.controlBilling.infrastructure.controller.dto.request.RegisterUserDto;
import com.nicky.controlBilling.infrastructure.controller.dto.response.ApiResponse;
import com.nicky.controlBilling.infrastructure.controller.dto.response.TokenResponse;
import com.nicky.controlBilling.infrastructure.controller.dto.response.UserResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor

@RestController
@RequestMapping("users")
public class UserController {

    private final RegisterUserUseCase registerUserUseCase;
    private final LoginUserUseCase loginUserUseCase;

    @PostMapping("/register")
    public ResponseEntity<@NonNull ApiResponse> registerUser(@Valid @RequestBody RegisterUserDto dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        new ApiResponse(
                                HttpStatus.CREATED.name(),
                                UserResponse.toResponse(this.registerUserUseCase.execute(dto.fullName(), dto.email(), dto.password(), dto.role())),
                                "Register user"
                        )
                );

    }

    @PostMapping("/login")
    public ResponseEntity<@NonNull ApiResponse> loginUser(@Valid @RequestBody LoginUserDto dto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        new ApiResponse(
                                HttpStatus.OK.name(),
                                new TokenResponse(
                                        this.loginUserUseCase.execute(dto.email(), dto.password())
                                ),
                                "Login user"
                        )
                );

    }
}
