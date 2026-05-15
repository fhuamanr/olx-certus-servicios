package pe.frontend.service.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



import pe.frontend.service.model.Product;

@Service
public class ProductClientService {

    private final RestTemplate restTemplate;
    private final String baseUrl;

    public ProductClientService(RestTemplate restTemplate,
                                @Value("${marketplace.api.base-url}") String apiBaseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = apiBaseUrl + "/products";
    }

    public List<Product> getAllProducts() {
        return Arrays.asList(
            restTemplate.getForObject(baseUrl, Product[].class)
        );
    }

    public void createProduct(Product product) {
        restTemplate.postForObject(baseUrl, product, Product.class);
    }
    
    public Product getProductById(Long id) {
        return restTemplate.getForObject(baseUrl + "/" + id, Product.class);
    }
}
