package br.com.rafael.accessviewerjava.service;

import br.com.rafael.accessviewerjava.dto.CreateDepositDto;
import br.com.rafael.accessviewerjava.dto.UserDto;
import br.com.rafael.accessviewerjava.exceptions.AppException;
import br.com.rafael.accessviewerjava.model.User;
import br.com.rafael.accessviewerjava.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private void checkEmailAndCpf(final UserDto userData) {
        if (userRepository.existsUserByCpf(userData.getCpf())) {
            throw new AppException("cpfAlreadyInUse", HttpStatus.CONFLICT);
        }

        if (userRepository.existsUserByEmail(userData.getEmail())) {
            throw new AppException("emailAlreadyInUse", HttpStatus.CONFLICT);
        }
    }
    public User createUser (final UserDto userData){

//        checkEmailAndCpf(userData);

        final User newUser = new User(
                                    userData.getName(),
                                    userData.getCpf(),
                                    userData.getEmail(),
                                    userData.getPassword(),
                                    userData.getType()
                                );

        return userRepository.save(newUser);
    }

    public List<User> readUsers(){

        return userRepository.findAll();
    }
                                          //(throws Exception) isso é necessário pelo "new Exception
    public User retrieveUser(final long id) {

                                          //esse tipo de tratamento de erro fecha o app, vamos modifica-lo
        return userRepository.findById(id).orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));

    }

    public User updateUser(final UserDto userData, final long id) {

        final User foundUser = userRepository.findById(id).orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));

        foundUser.setName(userData.getName());
        foundUser.setCpf(userData.getCpf());
        foundUser.setEmail(userData.getEmail());
        foundUser.setPassword(userData.getPassword());
        foundUser.setType(userData.getType());

        return userRepository.save(foundUser);

    }

    public void deleteUser(final long id) {

        final User foundUser = userRepository.findById(id).orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));

        userRepository.delete(foundUser);

    }

    public User createDeposit(final CreateDepositDto depositData, final long id) {

        final User foundUser = userRepository.findById(id).orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));

        final float currentBalance = foundUser.getBalance();

        foundUser.setBalance(currentBalance + depositData.getValue());

        return userRepository.save(foundUser);

    }

}
