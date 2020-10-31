package com.geekbrains.geekmarket;

import com.geekbrains.geekmarket.repositories.UserRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MyTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MockMvc mockMvc;

    @org.junit.Test
    public void tryProductPage() throws Exception {
        mockMvc.perform(get("/shop"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Каталог товаров")));
    }

    @org.junit.Test
    public void  springProductInList() throws Exception {
        mockMvc.perform(get("/shop"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Телевизор Samsung UE40NU7170U")));
    }

    @org.junit.Test
    public void  springTestProductFields() throws Exception {
    //при добавленом разрешении .antMatchers("/products/**").permitAll() в SecurityConfig
        mockMvc.perform(get("/products/edit/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("input type=\"text\" class=\"form-control\" placeholder=\"\" id=\"title\" name=\"title\"")))
                .andExpect(content().string(containsString("input type=\"text\" class=\"form-control\" rows=\"3\" placeholder=\"\" id=\"shortDescription\" name=\"shortDescription\"")));
    }



    @org.junit.Test
    public void securityAccessDeniedTest() throws Exception {
        mockMvc.perform(get("/admin"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @org.junit.Test
    public void testHomePage() throws Exception {
        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andDo(print());
    }

    //почему-то пишет что такого пользователя не существует In customAuthenticationSuccessHandler NullPointerException
    @org.junit.Test
    public void loginTest() throws Exception {
        mockMvc.perform(formLogin("/authenticateTheUser").user("alex").password("123"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(content().string(containsString("Выйти")));
    }



}
