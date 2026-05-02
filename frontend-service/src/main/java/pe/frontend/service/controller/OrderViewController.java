package pe.frontend.service.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.frontend.service.model.LoginResponse;
import pe.frontend.service.model.OrderRequest;
import pe.frontend.service.service.OrderClientService;

@Controller
@RequestMapping("/orders")
public class OrderViewController {

    private final OrderClientService orderClientService;

    public OrderViewController(OrderClientService orderClientService) {
        this.orderClientService = orderClientService;
    }

    @PostMapping("/request")
    public String createOrder(@RequestParam Long productId,
                              HttpSession session,
                              RedirectAttributes redirectAttributes) {

        LoginResponse loggedUser = (LoginResponse) session.getAttribute("loggedUser");

        if (loggedUser == null) {
            redirectAttributes.addFlashAttribute("error", "Debes iniciar sesión para enviar una solicitud.");
            return "redirect:/login";
        }

        OrderRequest request = new OrderRequest();
        request.setProductId(productId);
        request.setBuyerId(loggedUser.getUserId());
        request.setPaymentMethod("A coordinar");
        request.setDeliveryMethod("A coordinar");
        request.setMeetingPoint("A coordinar");

        try {
            orderClientService.createOrder(request);
            redirectAttributes.addFlashAttribute("success", "Solicitud enviada al vendedor correctamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "No se pudo crear la solicitud. Verifica que el producto esté disponible.");
        }

        return "redirect:/products/" + productId;
    }
}