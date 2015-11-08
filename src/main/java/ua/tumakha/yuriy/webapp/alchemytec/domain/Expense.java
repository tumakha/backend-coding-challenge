package ua.tumakha.yuriy.webapp.alchemytec.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author Yuriy Tumakha
 */
@Entity
@Table(name = "EXPENSE")
public class Expense {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    /**
     */
    @NotNull
    //@DateTimeFormat(style = "M-")
    @Column(nullable = false)
    private Date date;

    /**
     */
    @NotNull
    @Column(nullable = false)
    private Float amount;

    /**
     */
    @NotNull
    @Column(nullable = false)
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