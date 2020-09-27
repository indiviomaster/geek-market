package com.geekbrains.geekmarket.controllers;

import com.geekbrains.geekmarket.entities.Category;
import com.geekbrains.geekmarket.entities.Order;
import com.geekbrains.geekmarket.services.OrderService;
import com.geekbrains.geekmarket.services.ProductService;
import com.geekbrains.geekmarket.services.ShoppingCartService;
import com.geekbrains.geekmarket.utils.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/order")
public class OrderController {

    private ProductService productService;
    private OrderService orderService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    private ShoppingCartService shoppingCartService;

    @Autowired
    public void setShoppingCartService(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping("/fill")
    public String orderPage(Model model, HttpSession httpSession) {
        ShoppingCart cart = shoppingCartService.getCurrentCart(httpSession);
        model.addAttribute("cart", cart);
        return "order";
    }

    /*@PostMapping("/accept")
    public Category addOrder(@RequestBody Order order) {
        order.setId(0L);
        order = orderService.saveOrUpdate(order);
        return order;
    }*/
}
