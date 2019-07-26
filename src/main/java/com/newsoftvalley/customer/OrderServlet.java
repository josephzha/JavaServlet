package com.newsoftvalley.customer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderServlet extends HttpServlet{
    private ObjectMapper _objectMapper = new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    OrderResource orderResource = new OrderResource();
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();
        Long id = null;
        if(req.getPathInfo()!=null){
            try {
                id = Long.parseLong(req.getPathInfo().substring(1));
            }
            catch(Exception e){
                resp.getWriter().append("id must be LONG type\n");
            }
        }
        Order order = null;
        final int length = req.getContentLength();
        if(length>0){
            byte[] buf = new byte[length];
            ServletInputStream servletInputStream = req.getInputStream();
            servletInputStream.read(buf);
            String inputString = new String(buf);
            try {
                order = _objectMapper.readValue(inputString, Order.class);

            }
            catch(IOException e){
                resp.getWriter().append("error parsing --data "+e.getMessage()+"\n");
                return;
            }

        }
        if(method.toUpperCase().equals("GET")){
            if(id!=null&&order == null){
                Order Entity = orderResource.get(id);
                if(Entity == null){
                    resp.getWriter().append("404 NOT FOUND\n");
                }else{
                    resp.getWriter().append(_objectMapper.writeValueAsString(Entity));
                }
            }else{
                resp.getWriter().append("GET requires id and no Entity");
            }
        }else if(method.toUpperCase().equals("PUT")){
            if(id !=null && order!=null){
                orderResource.update(id,order);
            }else{
                resp.getWriter().append("PUT requires id and Entity");
            }

        }else if(method.toUpperCase().equals("POST")){
            if(id ==null && order !=null){
                long returnID = orderResource.create(order);
                resp.getWriter().append("created with id: "+returnID+"\n");
            }else{
                resp.getWriter().append("POST requires Entity and no id");
            }

        }else if(method.toUpperCase().equals("DELETE")){
            if(id!=null&&order==null){
                orderResource.delete(id);
            }else{
                resp.getWriter().append("DELETE requires id and no Entity");
            }

        }else{
            resp.getWriter().append("only GET POST PUT DELETE is supported");
        }
    }

}
