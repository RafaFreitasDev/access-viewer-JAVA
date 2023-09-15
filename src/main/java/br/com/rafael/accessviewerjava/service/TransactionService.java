package br.com.rafael.accessviewerjava.service;


import br.com.rafael.accessviewerjava.dto.CreateTransactionDto;
import br.com.rafael.accessviewerjava.exception.AppException;
import br.com.rafael.accessviewerjava.model.Transaction;
import br.com.rafael.accessviewerjava.model.User;
import br.com.rafael.accessviewerjava.model.UserType;
import br.com.rafael.accessviewerjava.repository.TransactionRepository;
import br.com.rafael.accessviewerjava.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class TransactionService {
    //link com o repository
    private final TransactionRepository transactionRepository;

    //como precisa passar um usuário na hora da criação, precisamos do UserRepository
    private final UserRepository userRepository;

    public TransactionService(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    //com o Exception trocado por AppException, podem ser removidos os "throws Exception"
    public Transaction createTransaction(final CreateTransactionDto transactionData) {

        //pegando pagador e pagado
        final User foundPayer = userRepository.findById(transactionData.getPayer_id()).orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));

        final User foundPayee = userRepository.findById(transactionData.getPayee_id()).orElseThrow(() -> new AppException("User not found",HttpStatus.NOT_FOUND));

        //usuario vendedo(SELLER) não pode fazer transferencia
        //variavel == "algumaCoisa", o java pede para substituir por Object.equal(variavel, "algumaCoisa")
        if (Objects.equals(foundPayer.getType(), UserType.SELLER)) {
            throw new AppException("invalidUserType", HttpStatus.FORBIDDEN);
        }

        //pegando carteira de cada um
        final float payerCurrentBalance = foundPayer.getBalance();
        final float payeeCurrentBalance = foundPayee.getBalance();

        //verifica se existe saldo na conta para transferencia
        final float transactionValue = transactionData.getValue();

        if (payerCurrentBalance < transactionValue) {
            throw new AppException("balanceNotSufficient", HttpStatus.FORBIDDEN);
        }

        //ja que criamos a variável "transactionValue" vamos substituíla em "transactionData.getValue()"
        //setando novas carteiras
        foundPayer.setBalance(payerCurrentBalance - transactionValue);
        foundPayee.setBalance(payeeCurrentBalance + transactionValue);

        //criando dado na tabela Transaction
        final Transaction newTransaction = new Transaction(foundPayer, foundPayee, transactionValue);

        userRepository.save(foundPayer);
        userRepository.save(foundPayee);

        return transactionRepository.save(newTransaction);

    }

    public Transaction retrieveTransaction(final long id) {
        return transactionRepository.findById(id).orElseThrow(() -> new AppException("Transaction not found", HttpStatus.NOT_FOUND));
    }

}
