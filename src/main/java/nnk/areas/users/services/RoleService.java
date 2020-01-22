package nnk.areas.users.services;

import nnk.areas.users.entities.Role;

public interface RoleService {
    Role findByName(String name);
}
