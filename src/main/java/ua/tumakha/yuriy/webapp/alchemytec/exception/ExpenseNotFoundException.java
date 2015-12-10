package ua.tumakha.yuriy.webapp.alchemytec.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * @author Yuriy Tumakha
 */
@ResponseStatus(value = NOT_FOUND)// , reason = "This Expense is not found in the system"
public class ExpenseNotFoundException extends RuntimeException {

    public ExpenseNotFoundException(String message) {
        super(message);
    }

}
