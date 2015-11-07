package ua.tumakha.yuriy.webapp.alchemytec.web.controller;

import org.springframework.web.bind.annotation.*;
import ua.tumakha.yuriy.webapp.alchemytec.domain.Expense;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpensesController {

    @RequestMapping(method = RequestMethod.GET)
    public List<Expense> getAll() {
        List<Expense> expenses = new ArrayList<>();
        Expense expense = new Expense();
        expense.setId(13L);
        expense.setDate(new Date());
        expense.setAmount(99.99f);
        expense.setReason("Why?");
        expenses.add(expense);
        expense = new Expense();
        expense.setId(14L);
        expense.setDate(new Date());
        expense.setAmount(499.99f);
        expense.setReason("Why not?");
        expenses.add(expense);
        return expenses;
    }

}