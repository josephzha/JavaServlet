package com.newsoftvalley.customer;

public class Account {
  private String name;
  private String email;
  private Integer age;
  private String address;
  private Integer[] creditCards;
  private Long id;

  public String getName() {
    return name;
  }

  public Account setName(String name) {
    this.name = name;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public Account setEmail(String email) {
    this.email = email;
    return this;
  }

  public Integer getAge() {
    return age;
  }

  public Account setAge(Integer age) {
    this.age = age;
    return this;
  }

  public String getAddress() {
    return address;
  }

  public Account setAddress(String address) {
    this.address = address;
    return this;
  }

  public Integer[] getCreditCards() {
    return creditCards;
  }

  public Account setCreditCards(Integer[] creditCards) {
    this.creditCards = creditCards;
    return this;
  }

  public Long getId() {
    return id;
  }

  public Account setId(Long id) {
    this.id = id;
    return this;
  }
}
