package com.example.demo_web;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "JuminServlet", value = "/jm")
public class JuminServet extends HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");

        BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream(
                                        new File("c:\\Engilsh\\shipin.txt")
                                )
                        )
                );

        List<String> lines = new ArrayList<>();
        String line ;
        while((line = reader.readLine()) != null) {
            lines.add(line);
        }
        String data="{\"地址\":上海市徐汇区虹桥路1号,\"名称\":港汇恒隆广场,\"户数\":11145\"}";
        response.getWriter().println(data);
    }
}



