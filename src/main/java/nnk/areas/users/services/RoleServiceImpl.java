package nnk.areas.users.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import nnk.areas.users.entities.Role;
import nnk.areas.users.repositories.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findByName(String name) {
        return this.roleRepository.findByName(name);
    }

    public Role getUserRole() {
        Role role = this.findByName("ROLE_USER");

        if(role == null){
            role = new Role("ROLE_USER");
            this.roleRepository.save(role);
        }

        return role;
    }
}
