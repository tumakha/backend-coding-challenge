package ua.tumakha.yuriy.webapp.alchemytec.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
    public Expense save(@RequestBody Expense expense) {
        return expenseService.save(expense);
    }

}