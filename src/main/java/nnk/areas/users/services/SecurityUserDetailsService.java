package nnk.areas.users.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import nnk.areas.users.models.binding.user.UserRegistrationModel;

public interface SecurityUserDetailsService extends UserDetailsService {
    void register(UserRegistrationModel userRegistrationModel);
}
