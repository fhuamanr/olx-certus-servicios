package pe.frontend.service.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pe.frontend.service.model.Product;

@Service
public class ProductClientService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String BASE_URL = "http://product-service:8080/products";

    public Product[] getAllProducts() {
        return restTemplate.getForObject(BASE_URL, Product[].class);
    }

    public void createProduct(Product product) {
        restTemplate.postForObject(BASE_URL, product, Product.class);
    }
}