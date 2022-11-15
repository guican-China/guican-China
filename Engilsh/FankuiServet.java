package com.example.demo_web;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "FankuiServet", value = "/fk")
public class FankuiServet extends HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");

        String name = request.getParameter("name");
        String age = request.getParameter("age");
        String tel = request.getParameter("tel");
        String email = request.getParameter("email");
        String mp_id = request.getParameter("mp_id");
        String advice = request.getParameter("advice");

        String line = name + "," + age + "," + tel + "," + email + "," + mp_id + "," + advice;

        BufferedWriter writer = new BufferedWriter(
                new FileWriter("c:\\Engilsh\\fankui.txt", true)
        );
        writer.append(line);
        writer.newLine();
        writer.flush();

        response.getWriter().println("{\"message\":\"ok\"}");
    }
}
