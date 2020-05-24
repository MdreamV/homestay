/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jtw.homestay.web.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author  jml
 * @date    2020-3-4 22:53:57
 * @version V1.0
 * @desc    
 */
public class BaseServlet extends HttpServlet {
   /**
    * 请求方法分发
    * @param request
    * @param response
    * @throws ServletException
    * @throws IOException 
    */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //System.out.println("BaseServlet的service方法被执行了...");
        //1.获取请求路径
        String uri = request.getRequestURI();
        //2.获取方法名
        String methodName = uri.substring(uri.lastIndexOf("/") + 1);
        //System.out.println(methodName);
        try {
            Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this,request,response);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将传入对象序列化为json
     * @param obj
     * @param response
     * @throws IOException
     */
    protected void writeValue(Object obj,HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        ObjectMapper mapper=new ObjectMapper();
        mapper.writeValue(response.getOutputStream(),obj);
    }

    /**
     * 序列化json
     * @param obj
     * @param response
     * @return
     * @throws JsonProcessingException
     */
    protected String writeValueAsString(Object obj,HttpServletResponse response) throws JsonProcessingException {
        response.setContentType("application/json;charset=utf-8");
        ObjectMapper mapper=new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }
}