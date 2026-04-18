package pe.frontend.service.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pe.frontend.service.model.User;

@Service
public class UserClientService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String BASE_URL;

    public UserClientService(@Value("${user.service.url}") String userServiceUrl) {
        this.BASE_URL = userServiceUrl + "/users";
    }

    public void register(User user) {
        restTemplate.postForObject(BASE_URL, user, User.class);
    }
}