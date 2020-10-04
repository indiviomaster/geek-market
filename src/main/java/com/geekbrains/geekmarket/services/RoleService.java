package com.geekbrains.geekmarket.services;

import com.geekbrains.geekmarket.entities.Category;
import com.geekbrains.geekmarket.entities.Product;
import com.geekbrains.geekmarket.entities.Role;
import com.geekbrains.geekmarket.repositories.CategoryRepository;
import com.geekbrains.geekmarket.repositories.RoleRepository;
import com.geekbrains.geekmarket.utils.CategoryNotFoundException;
import com.geekbrains.geekmarket.utils.RoleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getAllRoles() {
        return (List<Role>)roleRepository.findAll();
    }

    public Role getRoleById(Long id) {
        Optional<Role> role = roleRepository.findById(id);
        if (role.isPresent()) {
            return role.get();
        }
        return null;
    }

    public void saveRole(Role role) {
        roleRepository.save(role);
    }
    public Role saveOrUpdate(Role role) {
        return roleRepository.save(role);
    }

    public void delete(Long id) {
        Optional<Role> role = roleRepository.findById(id);
        if (!role.isPresent()) {
            throw new RoleNotFoundException("Роль  id = " + id + " не найдена");
        }
        roleRepository.delete(role.get());
    }

    public void addRole(String name){
        Role role = new Role();
        role.setName(name);
        roleRepository.save(role);
    }


    public boolean isRoleWithNameExists(String name) {
    }
}
