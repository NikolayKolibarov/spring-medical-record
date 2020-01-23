package nnk.areas.users.services;

import nnk.areas.users.entities.User;
import nnk.areas.users.models.binding.user.UserRegistrationModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Set;

public interface SecurityUserDetailsService extends UserDetailsService {
    void register(UserRegistrationModel userRegistrationModel);

    public User getUser(Long id);

    public User getAuthenticatedUser();

}
