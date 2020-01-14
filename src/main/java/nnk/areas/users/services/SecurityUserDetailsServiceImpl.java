package nnk.areas.users.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import nnk.areas.users.entities.Role;
import nnk.areas.users.entities.User;
import nnk.areas.users.models.binding.user.UserRegistrationModel;
import nnk.areas.users.repositories.UserRepository;

@Service
@Transactional
public class SecurityUserDetailsServiceImpl implements SecurityUserDetailsService {
    private final UserRepository userRepository;

    private final RoleServiceImpl roleService;
    private final ModelMapper modelMapper;


    @Autowired
    public SecurityUserDetailsServiceImpl(UserRepository userRepository, RoleServiceImpl roleService, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
    }

    public void register(UserRegistrationModel userRegistrationModel) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        User user = new User();

        this.modelMapper.map(userRegistrationModel, user);
        user.setPassword(bCryptPasswordEncoder.encode(userRegistrationModel.getPassword()));

        Role userRole = this.roleService.getUserRole();

        user.addRole(userRole);

        this.userRepository.save(user);
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("Invalid User");
        }

        return user;

    }
}
