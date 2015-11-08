package ua.tumakha.yuriy.webapp.alchemytec.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.tumakha.yuriy.webapp.alchemytec.domain.Expense;
import ua.tumakha.yuriy.webapp.alchemytec.repository.ExpenseRepository;
import ua.tumakha.yuriy.webapp.alchemytec.service.ExpenseService;

import java.util.List;

/**
 * @author Yuriy Tumakha
 */
@Service("expenseService")
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    ExpenseRepository expenseRepository;

    public List<Expense> findAll() {
        return expenseRepository.findAll();
    }

    public Expense create(Expense expense) {
        expense.setId(null);
        return save(expense);
    }

    public Expense update(Expense expense) {
        return save(expense);
    }

    public Expense findById(Long expenseId) {
        return expenseRepository.findOne(expenseId);
    }

    public void delete(Expense expense) {
        expenseRepository.delete(expense.getId());
    }

    private Expense save(Expense expense) {
        expense.setVat(expense.getAmount() * 0.2f);
        return expenseRepository.save(expense);
    }

}