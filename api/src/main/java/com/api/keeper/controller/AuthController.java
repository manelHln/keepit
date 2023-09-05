package com.api.keeper.controller;

import com.api.keeper.service.AuthService;
import com.api.keeper.dto.AuthRequest;
import com.api.keeper.dto.AuthResponse;
import com.api.keeper.dto.RegisterRequest;
import com.api.keeper.dto.UserInfoResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse>  register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse>  authenticate(@RequestBody AuthRequest request){
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @GetMapping("/userinfo")
    public ResponseEntity<UserInfoResponse> getUserInfo(HttpServletRequest request){
        final String authHeader = request.getHeader("Authorization");
        String jwt = authHeader.split(" ")[1].trim();
        return new ResponseEntity<>(authService.getUserInfo(jwt), HttpStatus.OK);
    }
}
