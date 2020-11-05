package fontysin.project.services;

import fontysin.project.model.user.AppUser;
import fontysin.project.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Iterable<AppUser> getAllUsers(){
        Iterable<AppUser> users = userRepository.findAll();

        int size = 0;
        for(AppUser user : users){ size++; }

        if(size==0){
            return null;
        }

        return users;
    }

    public AppUser getUserByPcn(int pcn) {
        Optional<AppUser> user = userRepository.findByPcn(pcn);

        return user.orElse(null);
    }

    public AppUser createUser(AppUser user){
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
}
