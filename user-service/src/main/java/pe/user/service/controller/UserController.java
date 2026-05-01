package pe.user.service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import pe.user.service.dto.RegisterRequest;
import pe.user.service.entity.LoginRequest;
import pe.user.service.entity.LoginResponse;
import pe.user.service.entity.User;
import pe.user.service.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @Operation(summary = "Listar usuarios")
    @GetMapping
    public List<User> getAll() {
        return service.getAll();
    }

    @Operation(summary = "Obtener usuario por ID")
    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @Operation(summary = "Crear usuario")
    @PostMapping
    public User create(@RequestBody RegisterRequest request) {
        return service.register(request);
    }

    @Operation(summary = "Actualizar usuario")
    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {
        return service.update(id, user);
    }

    @Operation(summary = "Eliminar usuario")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
    
    @Operation(summary = "Login de usuario")
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(service.login(request));
    }
}
