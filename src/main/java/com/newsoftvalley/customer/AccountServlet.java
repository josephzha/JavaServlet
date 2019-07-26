package com.newsoftvalley.customer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccountServlet extends HttpServlet {

  private ObjectMapper _objectMapper = new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

  private  AccountResource _accountResource = new AccountResource();

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    // entry point of your code

    String method = req.getMethod();

    Long id = null;
    if (req.getPathInfo() != null) {
      try {
        id = Long.parseLong(req.getPathInfo().substring(1));
      } catch (NumberFormatException e) {
        resp.getWriter().append("id must be LONG type\n");
        return;
      }
    }

    Account account = null;
    final int length = req.getContentLength();
    if (length > 0) {
      byte[] buf = new byte[length];
      ServletInputStream servletInputStream = req.getInputStream();
      servletInputStream.read(buf);
      String inputString = new String(buf);
      try {
        account = _objectMapper.readValue(inputString, Account.class);
      } catch (IOException e) {
        resp.getWriter().append("error parsing --data\n" + e.getMessage() + "\n");
        return;
      }
    }


    if (method.toUpperCase().equals("GET")) {
      if (id != null && account == null) {
        Account entity = _accountResource.get(id);
        if (entity == null) {
          resp.getWriter().append("404 NOT FOUND\n");
        } else {
          resp.getWriter().append(_objectMapper.writeValueAsString(entity) + "\n");
        }
      } else {
        resp.getWriter().append("GET requires Id and no Entity\n");
      }
    } else if (method.toUpperCase().equals("POST")) {
      if (id == null && account != null) {
        Long createdId = _accountResource.create(account);
        resp.getWriter().append("Created with id:" + createdId + "\n");
      } else {
        resp.getWriter().append("POST requires Entity and no Id\n");
      }
    } else if (method.toUpperCase().equals("PUT")) {
      if (id != null && account != null) {
        _accountResource.update(id, account);
      } else {
        resp.getWriter().append("PUT requires both Entity and Id\n");
      }
    } else if (method.toUpperCase().equals("DELETE")) {
      if (id != null && account == null) {
        _accountResource.delete(id);
      } else {
        resp.getWriter().append("DELETE requires Id and no Entity\n");
      }
    } else {
      resp.getWriter().append("Only GET/POST/PUT/DELETE is supported\n");
    }
  }

}
