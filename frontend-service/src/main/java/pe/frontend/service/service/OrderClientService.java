package pe.frontend.service.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pe.frontend.service.model.OrderRequest;

@Service
public class OrderClientService {

    private final RestTemplate restTemplate = new RestTemplate();

    private final String ORDER_SERVICE_URL = "http://order-service:8080/orders";

    public void createOrder(OrderRequest request) {
        restTemplate.postForObject(ORDER_SERVICE_URL, request, Object.class);
    }
}