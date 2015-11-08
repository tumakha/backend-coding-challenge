package ua.tumakha.yuriy.webapp.alchemytec.web.controller;

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import ua.tumakha.yuriy.webapp.alchemytec.WebApplication;
import ua.tumakha.yuriy.webapp.alchemytec.domain.Expense;
import ua.tumakha.yuriy.webapp.alchemytec.service.ExpenseService;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import java.util.Date;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * @author Yuriy Tumakha
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WebApplication.class)
@WebAppConfiguration
@TestPropertySource("classpath:application-test.properties")
@Transactional
public class ExpensesControllerITest {

    private static final String jsonContentType = MediaType.APPLICATION_JSON.toString();
    private static final String EXPENSES = "/expenses";
    private static final Date NOW = new Date();
    private static final int PROPERTIES_COUNT = 5;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    ExpenseService expenseService;

    @Before
    public void setup() throws Exception {
        MockMvc mockMvc = webAppContextSetup(webApplicationContext).build();
        RestAssuredMockMvc.mockMvc(mockMvc);

        expenseService.create(buildExpense(1));
        expenseService.create(buildExpense(2));
    }

    @Test
    public void testFindAll() {
        given().
        when().
            get(EXPENSES).
        then().
            statusCode(HttpServletResponse.SC_OK).
            contentType(jsonContentType).
            body("size()", equalTo(2)).
            body("[0].amount", equalTo(100.0f)).
            body("[1].amount", equalTo(200.0f));
    }

    @Test
    public void testFindExpenseById() {
        Long id = findOneExpense().getId();
        given().
        when().
            get(EXPENSES + "/" + id).
        then().
            statusCode(HttpServletResponse.SC_OK).
            contentType(jsonContentType).
            body("size()", equalTo(PROPERTIES_COUNT)).
            body("id", equalTo(id.intValue())).
            body("date", equalTo(NOW.getTime())).
            body("amount", equalTo(100.0f)).
            body("vat", equalTo(20.0f)).
            body("reason", equalTo("Reason 1"));
    }

    @Test
    public void testFindExpenseUnknownId() {
        Long id = 123456789L;
        given().
        when().
            get(EXPENSES + "/" + id).
        then().
            statusCode(HttpServletResponse.SC_NOT_FOUND);
    }

    @Test
    public void testCreateExpense() {
        Expense expense = findOneExpense();
        expense.setId(null);
        expense.setReason("New reason");

        given().
            contentType(jsonContentType).
            body(expense).
        when().
            post(EXPENSES).
        then().
            statusCode(HttpServletResponse.SC_CREATED).
            contentType(jsonContentType).
            body("size()", equalTo(PROPERTIES_COUNT)).
            body("id", notNullValue()).
            body("date", equalTo(expense.getDate().getTime())).
            body("amount", equalTo(expense.getAmount())).
            body("vat", equalTo(expense.getVat())).
            body("reason", equalTo(expense.getReason()));
    }

    @Test
    public void testUpdateExpense() {
        Expense expense = findOneExpense();
        expense.setReason("Secret reason");

        given().
            contentType(jsonContentType).
            body(expense).
        when().
            put(EXPENSES + "/" + expense.getId()).
        then().
            statusCode(HttpServletResponse.SC_OK).
            contentType(jsonContentType).
            body("size()", equalTo(PROPERTIES_COUNT)).
            body("id", equalTo(expense.getId().intValue())).
            body("date", equalTo(expense.getDate().getTime())).
            body("amount", equalTo(expense.getAmount())).
            body("vat", equalTo(expense.getVat())).
            body("reason", equalTo(expense.getReason()));
    }

    @Test
    public void testUpdateUnknownId() {
        Expense expense = findOneExpense();
        Long id = 123456789L;

        given().
            contentType(jsonContentType).
            body(expense).
        when().
            put(EXPENSES + "/" + id).
        then().
            statusCode(HttpServletResponse.SC_NOT_FOUND);
    }

    @Test
    public void testDeleteExpense() {
        Expense expense = findOneExpense();

        given().
            contentType(jsonContentType).
        when().
            delete(EXPENSES + "/" + expense.getId()).
        then().
            statusCode(HttpServletResponse.SC_OK).
            contentType(jsonContentType).
            body("size()", equalTo(PROPERTIES_COUNT)).
            body("id", equalTo(expense.getId().intValue())).
            body("date", equalTo(expense.getDate().getTime())).
            body("amount", equalTo(expense.getAmount())).
            body("vat", equalTo(expense.getVat())).
            body("reason", equalTo(expense.getReason()));

        Expense expense1 =  expenseService.findById(expense.getId());
        assertNull("Expense still exists after deletion", expense1);
    }

    @Test
    public void testDeleteUnknownId() {
        Long id = 123456789L;
        given().
        when().
            delete(EXPENSES + "/" + id).
        then().
            statusCode(HttpServletResponse.SC_NOT_FOUND);
    }

    @Test
    public void failPutToExpenses() {
        given().
        when().
            put(EXPENSES).
        then().
            statusCode(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    @Test
    public void failDeleteToExpenses() {
        given().
        when().
            delete(EXPENSES).
        then().
            statusCode(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    private Expense buildExpense(int num) {
        Expense expense = new Expense();
        expense.setAmount(num * 100f);
        expense.setDate(NOW);
        expense.setReason("Reason " + num);
        return expense;
    }

    private Expense findOneExpense() {
        return expenseService.findAll().get(0);
    }

}