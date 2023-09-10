package br.com.rafael.accessviewerjava.repository;

import br.com.rafael.accessviewerjava.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    //estas são variáveis que vão retornar boleano, vamos utiliza-las no service

    boolean existsUserByCpf(final String cpf);

    boolean existsUserByEmail(final String email);

}
