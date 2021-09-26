package com.dima.spring.entity;


import org.springframework.validation.annotation.Validated;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name="customer")
@Validated
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private Long Id;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="User_Id")
    @Valid
    private User UserId;

    @Column(name="Credit_Amount")
    @NotNull
    private BigDecimal CreditAmount;

    @Max(value = 30,message = "not more 30")
    @Column(name="Credit_Term")
    @NotNull
    private Integer CreditTerm;

    @Column(name="Mortgage_Object")
    @NotBlank
    private String MortgageObject;

    @Column(name="Total_Purchase_Value")
    @NotNull
    private BigDecimal TotalPurchaseValue;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="Seller_Id")
    @Valid
    private Seller SellerId;

    public Customer(){}

    public Customer(BigDecimal credit_amount, Integer credit_term, String mortgage_object, BigDecimal total_purchase_value) {
        CreditAmount = credit_amount;
        CreditTerm=credit_term;
        MortgageObject = mortgage_object;
        TotalPurchaseValue = total_purchase_value;
    }


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public User getUserId() {
        return UserId;
    }

    public void setUserId(User userId) {
        UserId = userId;
    }

    public BigDecimal getCreditAmount() {
        return CreditAmount;
    }

    public void setCreditAmount(BigDecimal creditAmount) {
        CreditAmount = creditAmount;
    }

    public Integer getCreditTerm() {
        return CreditTerm;
    }

    public void setCreditTerm(Integer creditTerm) {
        CreditTerm = creditTerm;
    }

    public String getMortgageObject() {
        return MortgageObject;
    }

    public void setMortgageObject(String mortgageObject) {
        MortgageObject = mortgageObject;
    }

    public BigDecimal getTotalPurchaseValue() {
        return TotalPurchaseValue;
    }

    public void setTotalPurchaseValue(BigDecimal totalPurchaseValue) {
        TotalPurchaseValue = totalPurchaseValue;
    }

    public Seller getSellerId() {
        return SellerId;
    }

    public void setSellerId(Seller sellerId) {
        SellerId = sellerId;
    }
}
