package ua.tumakha.yuriy.webapp.alchemytec.service;

import ua.tumakha.yuriy.webapp.alchemytec.domain.Expense;

import java.util.List;

/**
 * @author Yuriy Tumakha
 */
public interface ExpenseService {

    List<Expense> findAll();

    Expense create(Expense expense);

    Expense update(Expense expense);

    Expense findById(Long expenseId);

    void delete(Expense expense);

}