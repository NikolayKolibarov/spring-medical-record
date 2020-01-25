package nnk.areas.users.services;

import nnk.areas.users.entities.User;
import nnk.areas.users.models.binding.user.PatientAddModel;
import nnk.areas.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class DoctorService {
    private final UserRepository userRepository;

    @Autowired
    public DoctorService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<User> getPatients() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepository.findByEmail(username);

        return user.getPatients();
    }

    public void patientAdd(PatientAddModel patientAddModel) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User doctor = userRepository.findByEmail(username);
        User patient = userRepository.findById(patientAddModel.getPatient());

        doctor.getPatients().add(patient);

        this.userRepository.save(doctor);

        System.out.println("NANI");

    }

}
