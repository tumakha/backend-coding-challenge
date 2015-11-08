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
    public List<Expense> getAll() {
        return expenseService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Expense> save(@RequestBody Expense expense) {
        Expense createdExpense = expenseService.save(expense);
        return new ResponseEntity<>(createdExpense, HttpStatus.CREATED);
    }

}