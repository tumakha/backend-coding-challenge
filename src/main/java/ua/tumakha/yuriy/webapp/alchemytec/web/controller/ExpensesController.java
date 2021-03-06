package ua.tumakha.yuriy.webapp.alchemytec.web.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.tumakha.yuriy.webapp.alchemytec.domain.Expense;
import ua.tumakha.yuriy.webapp.alchemytec.exception.ExpenseNotFoundException;
import ua.tumakha.yuriy.webapp.alchemytec.service.ExpenseService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author Yuriy Tumakha
 */
@RestController
@RequestMapping(value = "/expenses", produces = {APPLICATION_JSON_VALUE})
public class ExpensesController {

    private static final Logger logger = LogManager.getLogger(ExpensesController.class);

    @Autowired
    ExpenseService expenseService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Expense> findAll() {
        return expenseService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Expense> createExpense(@RequestBody Expense expense) {
        Expense createdExpense = expenseService.create(expense);
        return new ResponseEntity<>(createdExpense, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{expenseId}", method = RequestMethod.GET)
    public ResponseEntity<Expense> findExpenseById(@PathVariable("expenseId") Long expenseId) {
        Expense expense = expenseService.findById(expenseId);
        assertExpense(expenseId, expense);
        return new ResponseEntity<>(expense, HttpStatus.OK);
    }

    @RequestMapping(value = "/{expenseId}", method = RequestMethod.PUT)
    public ResponseEntity<Expense> updateExpense(@PathVariable("expenseId") Long expenseId, @RequestBody Expense expense) {
        assertExpense(expenseId, expenseService.findById(expenseId));
        expense.setId(expenseId);
        Expense updatedExpense = expenseService.update(expense);
        return new ResponseEntity<>(updatedExpense, HttpStatus.OK);
    }

    @RequestMapping(value = "/{expenseId}", method = RequestMethod.DELETE)
    public ResponseEntity<Expense> deleteExpense(@PathVariable("expenseId") Long expenseId) {
        Expense expense = expenseService.findById(expenseId);
        assertExpense(expenseId, expense);
        expenseService.delete(expense);
        return new ResponseEntity<>(expense, HttpStatus.OK);
    }

    private void assertExpense(Long expenseId, Expense expense) {
        if (expense == null) {
            String message = "Not found expense by ID = " + expenseId;
            logger.warn(message);
            throw new ExpenseNotFoundException(message);
        }
    }

}