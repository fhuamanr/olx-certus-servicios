package pe.user.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.user.service.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

}
