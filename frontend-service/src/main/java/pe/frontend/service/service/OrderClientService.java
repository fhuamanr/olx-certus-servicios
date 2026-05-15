package pe.frontend.service.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pe.frontend.service.model.OrderRequest;

@Service
public class OrderClientService {

    private final RestTemplate restTemplate;

    private final String orderServiceUrl;

    public OrderClientService(RestTemplate restTemplate,
                              @Value("${marketplace.api.base-url}") String apiBaseUrl) {
        this.restTemplate = restTemplate;
        this.orderServiceUrl = apiBaseUrl + "/orders";
    }

    public void createOrder(OrderRequest request) {
        restTemplate.postForObject(orderServiceUrl, request, Object.class);
    }
}
