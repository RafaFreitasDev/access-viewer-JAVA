package br.com.rafael.accessviewerjava.repository;

import br.com.rafael.accessviewerjava.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {
    //metodo utilizado no login
    UserDetails findByEmail(String email);

    //estas são variáveis que vão retornar boleano, vamos utiliza-las no service

    boolean existsUserByCpf(final String cpf);

    boolean existsUserByEmail(final String email);

}
