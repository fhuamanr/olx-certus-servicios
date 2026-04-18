package pe.frontend.service.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pe.frontend.service.model.Product;

@Service
public class ProductClientService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String BASE_URL;

    public ProductClientService(@Value("${product.service.url}") String productServiceUrl) {
        this.BASE_URL = productServiceUrl + "/products";
    }

    public List<Product> getAllProducts() {
        return Arrays.asList(
            restTemplate.getForObject(BASE_URL, Product[].class)
        );
    }

    public void createProduct(Product product) {
        restTemplate.postForObject(BASE_URL, product, Product.class);
    }

    public Product getProductById(Long id) {
        return restTemplate.getForObject(BASE_URL + "/" + id, Product.class);
    }
}