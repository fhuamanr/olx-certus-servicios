package pe.frontend.service.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pe.frontend.service.model.Category;

@Service
public class CategoryClientService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String BASE_URL = "http://product-service:8080/categories";

    public Category[] getAll() {
        return restTemplate.getForObject(BASE_URL, Category[].class);
    }

    public void save(Category category) {
        restTemplate.postForObject(BASE_URL, category, Category.class);
    }
}