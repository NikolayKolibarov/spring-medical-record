package nnk.areas.users.services;

import nnk.areas.users.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import nnk.areas.users.models.binding.user.UserRegistrationModel;

import java.util.Set;

public interface SecurityUserDetailsService extends UserDetailsService {
    void register(UserRegistrationModel userRegistrationModel);

    public Set<User> getPatients();

    public User getUser(Long id);

}
