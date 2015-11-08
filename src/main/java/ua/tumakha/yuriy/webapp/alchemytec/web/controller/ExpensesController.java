package ua.tumakha.yuriy.webapp.alchemytec.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.tumakha.yuriy.webapp.alchemytec.domain.Expense;
import ua.tumakha.yuriy.webapp.alchemytec.service.ExpenseService;

import java.util.List;

/**
 * @author Yuriy Tumakha
 */
@RestController
@RequestMapping("/expenses")
public class ExpensesController {

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
    public ResponseEntity<Expense> getExpenseById(@PathVariable("expenseId") Long expenseId) {
        Expense expense = expenseService.findById(expenseId);
        if (expense == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(expense, HttpStatus.OK);
    }

    @RequestMapping(value = "/{expenseId}", method = RequestMethod.PUT)
    public ResponseEntity<Expense> updateExpense(@PathVariable("expenseId") Long expenseId, @RequestBody Expense expense) {
        if (expenseService.findById(expenseId) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        expense.setId(expenseId);
        Expense updatedExpense = expenseService.update(expense);
        return new ResponseEntity<>(updatedExpense, HttpStatus.OK);
    }

    @RequestMapping(value = "/{expenseId}", method = RequestMethod.DELETE)
    public ResponseEntity<Expense> deleteExpense(@PathVariable("expenseId") Long expenseId) {
        Expense expense = expenseService.findById(expenseId);
        if (expense == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        expenseService.delete(expense);
        return new ResponseEntity<>(expense, HttpStatus.OK);
    }

}