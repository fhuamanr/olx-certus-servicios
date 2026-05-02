package pe.frontend.service.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pe.frontend.service.model.LoginRequest;
import pe.frontend.service.model.LoginResponse;
import pe.frontend.service.model.User;

@Service
public class UserClientService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String BASE_URL = "http://user-service:8080/users";

    public void register(User user) {
        restTemplate.postForObject(BASE_URL, user, User.class);
    }

    public LoginResponse login(LoginRequest request) {
        return restTemplate.postForObject(
                BASE_URL + "/login",
                request,
                LoginResponse.class
        );
    }
}