package com.example.demo_web;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "AllfankuiServlet", value = "/allfk")
public class AllfankuiServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");

        BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream(
                                        new File("c:\\Engilsh\\fankui.txt")
                                ), "utf-8"
                        )
                );

        String jsonList = "";
        List<String> lines = new ArrayList<>();
        String line ;
        while((line = reader.readLine()) != null) {
            lines.add(line);

            String[] str=line.split(",");
            if (str.length != 6)continue;
            String 姓名 = str[0];
            String 年龄 = str[1];
            String 电话 = str[2];
            String 邮箱 = str[3];
            String 物资包ID = str[4];
            String 建议 = str[5];

            String jsonStr = "{" +
                    "\"姓名\": \"" + 姓名 + "\"," +
                    "\"年龄\": \"" + 年龄 + "\"," +
                    "\"电话\": \"" + 电话 + "\"," +
                    "\"邮箱\": \"" + 邮箱 + "\"," +
                    "\"物资包ID\": \"" + 物资包ID + "\"," +
                    "\"建议\": \"" + 建议 + "\"" +
                    "}";
            jsonList = jsonList + jsonStr + ",";
        }

        jsonList = "[" + jsonList.substring(0, jsonList.length() -1) + "]";
        response.getWriter().println(jsonList);
    }
}
