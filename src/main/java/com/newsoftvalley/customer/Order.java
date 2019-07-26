package com.newsoftvalley.customer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    private Long id;
    private Long accountId;
    private List<Long> productIds = new ArrayList<>();
    private Date orderDate;

    public Order() {}

    public Order setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Order setAccountId(Long accountId) {
        this.accountId = accountId;
        return this;
    }

    public Long getAccountId() {
        return accountId;
    }

    public Order addProductId(Long productId) {
        this.productIds.add(productId);
        return this;
    }

    public List<Long> getProductIds() {
        return this.productIds;
    }

    public Order setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
        return this;
    }

    public Date getOrderDate() {
        return this.orderDate;
    }
}
