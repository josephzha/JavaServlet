package com.newsoftvalley.customer;

import java.util.HashMap;
import java.util.Map;

public class AccountResource {

  private Map<Long, Account> _records = new HashMap<>();
  private long _curId = 0;

  public Account get(Long id) {
    return _records.get(id);
  }

  public Long create(Account account) {
    long curId = _curId;
    account.setId(curId);
    _records.put(curId, account);
    _curId++;
    return curId;
  }

  public void delete(Long id) {
    _records.remove(id);
  }

  public void update(Long id, Account account) {
    account.setId(id);
    _records.put(id, account);
  }
}
