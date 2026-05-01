package pe.user.service.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pe.user.service.dto.RegisterRequest;
import pe.user.service.entity.LoginRequest;
import pe.user.service.entity.LoginResponse;
import pe.user.service.entity.User;
import pe.user.service.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getAll() {
        return repository.findAll();
    }

    public User getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public User save(User user) {
    	
    	if (repository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
    	user.setPassword(passwordEncoder.encode(user.getPassword()));
    	user.setEnabled(true);
        user.setRole("CUSTOMER");
        user.setStatus("ACTIVE");
        user.setCreatedAt(LocalDateTime.now());
        
        return repository.save(user);
    }

    public User update(Long id, User user) {
        return repository.findById(id).map(existing -> {

            existing.setName(user.getName());
            existing.setEmail(user.getEmail());
            existing.setPhone(user.getPhone());

            return repository.save(existing);

        }).orElse(null);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

       
    public LoginResponse login(LoginRequest request) {
        User user = repository.findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        if (!user.getEnabled()) {
            throw new RuntimeException("User disabled");
        }

        return new LoginResponse(
            user.getId(),
            user.getName(),
            user.getEmail(),
            user.getRole()
        );
    }
    
    public User register(RegisterRequest request) {

        if (repository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhone(request.getPhone());

        // valores por defecto
        user.setRole("CUSTOMER");
        user.setEnabled(true);

        return repository.save(user);
    }
}
