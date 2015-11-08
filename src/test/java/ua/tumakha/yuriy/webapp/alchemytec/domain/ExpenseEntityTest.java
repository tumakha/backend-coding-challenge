package ua.tumakha.yuriy.webapp.alchemytec.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ua.tumakha.yuriy.webapp.alchemytec.RepositoryConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Yuriy Tumakha
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RepositoryConfiguration.class})
@TestPropertySource("classpath:application-test.properties")
@Transactional
public class ExpenseEntityTest {

    private static final Float TEST_AMOUNT = 49.99f;
    private static final Date TEST_DATE = new Date();
    private static final String TEST_REASON = "Test Reason 1 - JUnit";

    @PersistenceContext
    protected EntityManager entityManager;

    @Test
    public void testEntity() {
        Expense expense = new Expense();
        expense.setAmount(TEST_AMOUNT);
        expense.setVat(TEST_AMOUNT * 0.2f);
        expense.setDate(TEST_DATE);
        expense.setReason(TEST_REASON);
        entityManager.persist(expense);
        entityManager.flush();
        assertNotNull("IDs should be not null", expense.getId());

        Expense expense1 = entityManager.find(Expense.class, expense.getId());
        assertEquals("IDs should be equal", expense.getId(), expense1.getId());
        assertEquals("Amounts should be equal", TEST_AMOUNT, expense1.getAmount());
        assertEquals("Dates should be equal", TEST_DATE, expense1.getDate());
        assertEquals("Reasons should be equal", TEST_REASON, expense1.getReason());
    }

}