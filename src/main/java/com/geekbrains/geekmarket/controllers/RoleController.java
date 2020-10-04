package com.geekbrains.geekmarket.controllers;

import com.geekbrains.geekmarket.entities.Category;
import com.geekbrains.geekmarket.entities.Role;
import com.geekbrains.geekmarket.services.CategoryService;
import com.geekbrains.geekmarket.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/roles")
public class RoleController {
    private RoleService roleService;

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }


    @GetMapping("")
    public String rolesPage(Model model) {
        List<Role> all = roleService.getAllRoles();
        model.addAttribute("roles", all);
        return "roles";
    }

    @GetMapping("/addrole")
    public String addRoleForm(Model model) {

        model.addAttribute("role", new Role());
        return "role";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable(name = "id") Long id) {
        Role role = roleService.getRoleById(id);
        if (role == null) {
            role = new Role();
            role.setId(0L);
        }
        model.addAttribute("role", role);
        model.addAttribute("roles", roleService.getAllRoles());
        return "/edit-role";
    }

    //редактирование текущей категории
    @PostMapping("/edit")
    public String processRoleAddForm(@Valid @ModelAttribute("role") Role role, BindingResult theBindingResult, Model model) {
        if (role.getId() == 0 && roleService.isRoleWithNameExists(role.getName())) {
            theBindingResult.addError(new ObjectError("role.name", "Роль уже существует"));
        }
        //проверка на ошибки
        if (theBindingResult.hasErrors()) {
            model.addAttribute("roles", roleService.getAllRoles());
            return "edit-role";
        }


        roleService.saveRole(role);
        return "redirect:/shop";
    }


}
