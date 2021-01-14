package fontysin.project.services;

import fontysin.project.controllers.Util;
import fontysin.project.exceptions.ConflictException;
import fontysin.project.entities.model.user.AppUser;
import fontysin.project.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {
    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Iterable<AppUser> getAllUsers(){
        return userRepository.findAll();
    }

    public AppUser getUserByPcn(int pcn) {
        Optional<AppUser> user = userRepository.findByPcn(pcn);

        return user.orElse(null);
    }

    public AppUser createUser(AppUser user){
        user.setPcn(Util.getPcn());
        user.setIsStudent(Util.isStudent());

        if(userRepository.findByPcn(user.getPcn()).isPresent()) {
            throw new ConflictException("A user with this PCN already exists");
        }
        return userRepository.save(user);
    }
    
    public AppUser updateUser(AppUser user){
        return userRepository.save(user);
    }

    public boolean deleteUser(int pcn){
        Optional<AppUser> user = userRepository.findByPcn(pcn);
        if(user.isPresent()) {
            userRepository.delete(user.get());
            return true;
        } else {
            return false;
        }
    }

    public Iterable<AppUser> searchByFirstName(String firstName) {
        Optional<Iterable<AppUser>> found = userRepository.findAllByFirstNameContainsAndPrivacySettings(firstName, 0);
        return found.orElse(new ArrayList<>());
    }
}