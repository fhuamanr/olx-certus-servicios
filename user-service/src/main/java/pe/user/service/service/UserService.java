package pe.user.service.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import pe.user.service.entity.User;
import pe.user.service.repository.UserRepository;

@Service
public class UserService {

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

        // 🔥 reglas de negocio
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

    // 🔥 PRO: búsqueda por email
    public User getByEmail(String email) {
        return repository.findByEmail(email);
    }
}
