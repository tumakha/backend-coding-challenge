package ua.tumakha.yuriy.webapp.alchemytec.service.impl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.tumakha.yuriy.webapp.alchemytec.domain.Expense;
import ua.tumakha.yuriy.webapp.alchemytec.repository.ExpenseRepository;
import ua.tumakha.yuriy.webapp.alchemytec.service.ExpenseService;

import java.util.List;

/**
 * @author Yuriy Tumakha
 */
@Service("expenseService")
public class ExpenseServiceImpl implements ExpenseService {

    private static final Logger logger = LogManager.getLogger(ExpenseServiceImpl.class);

    @Autowired
    ExpenseRepository expenseRepository;

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Expense> findAll() {
        logger.info("Find all");
        return expenseRepository.findAll();
    }

    @Transactional
    public Expense create(Expense expense) {
        expense.setId(null);
        logger.info("Create expense " + expense);
        return save(expense);
    }

    @Transactional
    public Expense update(Expense expense) {
        logger.info("Update expense " + expense);
        return save(expense);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Expense findById(Long expenseId) {
        logger.info("Find expense by ID = " + expenseId);
        return expenseRepository.findOne(expenseId);
    }

    @Transactional
    public void delete(Expense expense) {
        logger.info("Delete expense " + expense);
        expenseRepository.delete(expense.getId());
    }

    private Expense save(Expense expense) {
        expense.setVat(expense.getAmount() * 0.2f);
        return expenseRepository.save(expense);
    }

}