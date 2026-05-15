package pe.frontend.service.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pe.frontend.service.model.Category;

@Service
public class CategoryClientService {

    private final RestTemplate restTemplate;
    private final String baseUrl;

    public CategoryClientService(RestTemplate restTemplate,
                                 @Value("${marketplace.api.base-url}") String apiBaseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = apiBaseUrl + "/categories";
    }

    public Category[] getAll() {
        return restTemplate.getForObject(baseUrl, Category[].class);
    }

    public void save(Category category) {
        restTemplate.postForObject(baseUrl, category, Category.class);
    }
}
