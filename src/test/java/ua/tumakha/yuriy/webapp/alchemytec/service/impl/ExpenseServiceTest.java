package ua.tumakha.yuriy.webapp.alchemytec.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ua.tumakha.yuriy.webapp.alchemytec.RepositoryConfiguration;
import ua.tumakha.yuriy.webapp.alchemytec.domain.Expense;
import ua.tumakha.yuriy.webapp.alchemytec.service.ExpenseService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Yuriy Tumakha
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RepositoryConfiguration.class, ExpenseServiceImpl.class})
@TestPropertySource("classpath:application-test.properties")
@Transactional
public class ExpenseServiceTest {

    @Autowired
    ExpenseService expenseService;

    @PersistenceContext
    protected EntityManager entityManager;

    @Before
    public void initTestData() {
        entityManager.persist(buildExpense(111.33f, new Date(), "Reason 1"));
        entityManager.persist(buildExpense(49.99f, new Date(), "Reason 2"));
        entityManager.flush();
    }

    @Test
    public void testFind() {
        Expense expense = findOneExpense();

        Expense expense1 = expenseService.findById(expense.getId());
        assertEquals("IDs should be equal", expense.getId(), expense1.getId());
        assertEquals("Amounts should be equal", expense.getAmount(), expense1.getAmount());
        assertEquals("VATs should be equal", expense.getVat(), expense1.getVat());
        assertEquals("Dates should be equal", expense.getDate(), expense1.getDate());
        assertEquals("Reasons should be equal", expense.getReason(), expense1.getReason());
    }

    @Test
    public void testCreate() {
        Expense expense = buildExpense(999.99f, new Date(), "Reason 3");
        expenseService.create(expense);

        Expense expense1 = expenseService.findById(expense.getId());
        assertEquals("IDs should be equal", expense.getId(), expense1.getId());
        assertEquals("Reasons should be equal", "Reason 3", expense1.getReason());
    }

    @Test
    public void testUpdate() {
        Expense expense = findOneExpense();
        assertNotEquals("Reasons should be equal", "New Reason", expense.getReason());
        expense.setReason("New Reason");
        expenseService.update(expense);

        Expense expense1 = expenseService.findById(expense.getId());
        assertEquals("IDs should be equal", expense.getId(), expense1.getId());
        assertEquals("Reasons should be equal", "New Reason", expense1.getReason());
    }

    @Test
    public void testDelete() {
        Expense expense = findOneExpense();

        Expense expense1 = expenseService.findById(expense.getId());
        assertNotNull("Expense not exist", expense1);
        expenseService.delete(expense1);

        expense1 = expenseService.findById(expense.getId());
        assertNull("Expense still exists after deletion", expense1);
    }

    private Expense buildExpense(Float amount, Date date, String reason) {
        Expense expense = new Expense();
        expense.setAmount(amount);
        expense.setVat(amount * 0.2f);
        expense.setDate(date);
        expense.setReason(reason);
        return expense;
    }

    private Expense findOneExpense() {
        List<Expense> expenses = expenseService.findAll();
        assertEquals("Expenses count should be equal 2", 2, expenses.size());
        return expenses.get(0);
    }

}