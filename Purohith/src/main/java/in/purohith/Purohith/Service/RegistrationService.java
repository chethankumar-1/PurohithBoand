package in.purohith.Purohith.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import in.purohith.Purohith.Model.RegistrationModel;
import in.purohith.Purohith.Repository.RegisterRepository;

@Service
public class RegistrationService {

    @Autowired
    private RegisterRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String register(RegistrationModel user){

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        repository.save(user);

        return "User Registered Successfully";
    }
}