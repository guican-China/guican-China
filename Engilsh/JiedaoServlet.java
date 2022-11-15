package com.example.demo_web;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "JiedaoServlet", value = "/jd")
public class JiedaoServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");

        BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream(
                                        new File("c:\\Engilsh\\xiaoqu.txt")
                                ), "utf-8"
                        )
                );

        String jsonList = "";
        List<String> lines = new ArrayList<>();
        String line ;
        while((line = reader.readLine()) != null) {
            lines.add(line);

            String[] str = line.split(",");
            String xq_id = str[0];
            String 地址 = str[1];
            String 名称 = str[2];
            String 户数 = str[3];

            String jsonStr = "{" +
                    "\"xq_id\": \"" + xq_id + "\"," +
                    "\"地址\": \"" + 地址 + "\"," +
                    "\"名称\": \"" + 名称 + "\"," +
                    "\"户数\": \"" + 户数 + "\"" +
                    "}";
            jsonList = jsonList + jsonStr + ",";
        }

        jsonList = "[" + jsonList.substring(0, jsonList.length() -1) + "]";
        response.getWriter().println(jsonList);
    }
}
