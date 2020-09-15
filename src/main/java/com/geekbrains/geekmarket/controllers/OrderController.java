package com.geekbrains.geekmarket.controllers;

import com.geekbrains.geekmarket.services.ShoppingCartService;
import com.geekbrains.geekmarket.utils.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/order")
public class OrderController {

    private ShoppingCartService shoppingCartService;

    @Autowired
    public void setShoppingCartService(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping("/fill")
    public String orderPage(Model model, HttpSession httpSession) {
        ShoppingCart order = shoppingCartService.getCurrentCart(httpSession);
        model.addAttribute("order", order);
        return "order";
    }


}
