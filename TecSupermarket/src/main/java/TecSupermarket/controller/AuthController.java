package TecSupermarket.controller;

import TecSupermarket.dto.response.AuthCheckResponse;
import TecSupermarket.dto.request.LoginUserRequest;
import TecSupermarket.dto.request.RegisterUserRequest;
import TecSupermarket.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginUserRequest loginUserRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body("Wrong credentials");
        }
        try {
            String jwt = authService.authenticate(loginUserRequest.getEmail(), loginUserRequest.getPassword());
            return ResponseEntity.ok(jwt);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterUserRequest registerUserRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body("Check fields");
        }
        try {
            authService.registerUser(registerUserRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("Registered");
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/check-auth")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<AuthCheckResponse> checkAuth(Authentication auth) {
        return ResponseEntity.ok(
                new AuthCheckResponse(
                        auth.getName(),
                        auth.getAuthorities().iterator().next().getAuthority()
                )
        );
    }

    @GetMapping("/check-auth/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AuthCheckResponse> checkAuthAdmin(Authentication auth) {
        return ResponseEntity.ok(
                new AuthCheckResponse(
                        auth.getName(),
                        auth.getAuthorities().iterator().next().getAuthority()
                )
        );
    }

    @GetMapping("/check-auth/user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<AuthCheckResponse> checkAuthUser(Authentication auth){
        return ResponseEntity.ok(
                new AuthCheckResponse(
                        auth.getName(),
                        auth.getAuthorities().iterator().next().getAuthority()
                )
        );
    }
}