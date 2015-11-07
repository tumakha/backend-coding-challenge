package ua.tumakha.yuriy.webapp.alchemytec.domain;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class Expense {

    private Long id;

    /**
     */
    @NotNull
    //@DateTimeFormat(style = "M-")
    private Date date;

    /**
     */
    private Float amount;

    /**
     */
    @NotNull
    private String reason;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

}