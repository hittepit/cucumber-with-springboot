package be.ucm.pocs.springboot.cucumber.steps.config;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class TransactionManagement {
    private final PlatformTransactionManager transactionManager;
    private TransactionStatus transaction;

    public TransactionManagement(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    @Before
    public void start_transaction() {
        transaction = transactionManager.getTransaction(new DefaultTransactionDefinition());
    }

    @After
    public void rollback_transaction() {
        transactionManager.rollback(transaction);
    }
}
