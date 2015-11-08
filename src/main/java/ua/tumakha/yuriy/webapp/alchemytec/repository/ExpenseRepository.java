package ua.tumakha.yuriy.webapp.alchemytec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.tumakha.yuriy.webapp.alchemytec.domain.Expense;

/**
 * @author Yuriy Tumakha
 */
@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

}