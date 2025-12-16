package TecSupermarket.controller;

import TecSupermarket.dto.LoginUserDTO;
import TecSupermarket.dto.RegisterUserDTO;
import TecSupermarket.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public ResponseEntity<String> login(@Valid @RequestBody LoginUserDTO loginUserDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body("Revise sus credenciales");
        }
        try {
            String jwt = authService.authenticate(loginUserDto.getEmail(), loginUserDto.getPassword());
            return ResponseEntity.ok(jwt);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterUserDTO registerUserDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body("Revise los campos");
        }
        try {
            authService.registerUser(registerUserDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Registrado");
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/check-auth")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<String> checkAuth(){
        return ResponseEntity.ok().body("Autenticado");
    }
}
