package kg.megacom.minitinder.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kg.megacom.minitinder.models.dto.AuthRequest;
import kg.megacom.minitinder.models.enums.Language;
import kg.megacom.minitinder.services.AccountService;
import kg.megacom.minitinder.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
@Api(tags = "Авторизация")
public class AuthController {

    private final AuthService authService;

    @ApiOperation("Логин")
    @PostMapping("login")
    ResponseEntity<?> login(@RequestBody AuthRequest request, @RequestParam Language language) {
        return ResponseEntity.ok(authService.auth(request,language));
    }
}
