package com.geekbrains.geekmarket;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class    AppLogModule {



    @Before("execution(public void  com.geekbrains.geekmarket.services.ProductService.*(..))")
    public void aopSMethod() {
        System.out.println("**AOP сработал метод из сервиса**");
    }

    @Before("execution(public void  com.geekbrains.geekmarket.entities.User.setUserName())")
    public void aopSetName(JoinPoint joinPoint) {
        System.out.println("***AOP сработал метод пользователя***");
        System.out.println("устанавливаемое имя");
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("был вызван метод: " + methodSignature);
        Object[] args = joinPoint.getArgs();
        if (args.length > 0) {
            System.out.println("Аргументы:");
            for (Object o : args) {
                System.out.println(o);
            }
        }
    }


    @Before("execution(public void  com.geekbrains.geekmarket.entities.User.*(..))")
    public void aopUserMethod() {
        System.out.println("***AOP сработал метод пользователя***");
    }

    @After("execution(public void  com.geekbrains.geekmarket.services.ShoppingCartService.addToCart(..))")
    public void aopAddToCart(JoinPoint joinPoint) {
        System.out.println("****AOP Добавлен товар в корзину*****");

            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            System.out.println("был вызван метод: " + methodSignature);
            Object[] args = joinPoint.getArgs();
            if (args.length > 0) {
                System.out.println("Аргументы:");
                for (Object o : args) {
                    System.out.println(o);
                }
            }

    }



    @After("execution(public void  com.geekbrains.geekmarket.services.ShoppingCartService.*(..))")
    public void aopCartMeth() {
        System.out.println("****AOP метод корзины*****");
    }
    @After("execution(public void  com.geekbrains.geekmarket.services.ShoppingCartService.getCurrentCart())")
    public void aopSelCart() {
        System.out.println("****AOP выбрана текущая корзина корзина*****");
    }
    @After("execution(public void  com.geekbrains.geekmarket.entities.OrderItem.*(..))") // pointcut expression
    public void aopOrderCart() {
        System.out.println("****AOP Order Item*****");
    }



}
