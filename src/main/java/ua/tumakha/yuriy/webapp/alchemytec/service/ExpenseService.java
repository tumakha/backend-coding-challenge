package ua.tumakha.yuriy.webapp.alchemytec.service;

import ua.tumakha.yuriy.webapp.alchemytec.domain.Expense;

import java.util.List;

/**
 * @author Yuriy Tumakha
 */
public interface ExpenseService {

    public List<Expense> findAll();

    public Expense save(Expense expense);

}