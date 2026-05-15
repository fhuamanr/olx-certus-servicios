package pe.frontend.service.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pe.frontend.service.model.LoginRequest;
import pe.frontend.service.model.LoginResponse;
import pe.frontend.service.model.User;

@Service
public class UserClientService {

    private final RestTemplate restTemplate;
    private final String baseUrl;

    public UserClientService(RestTemplate restTemplate,
                             @Value("${marketplace.api.base-url}") String apiBaseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = apiBaseUrl + "/users";
    }

    public void register(User user) {
        restTemplate.postForObject(baseUrl, user, User.class);
    }

    public LoginResponse login(LoginRequest request) {
        return restTemplate.postForObject(
                baseUrl + "/login",
                request,
                LoginResponse.class
        );
    }
}
