package com.example.demo_web;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "GongyingshangXiaoquServlet", value = "/gysxq")
public class GongyinshangXiaoquServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");

        BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream(
                                        new File("c:\\Engilsh\\wuzibao.txt")
                                ), "utf-8"
                        )
                );

        String jsonList = "";
        List<String> lines = new ArrayList<>();
        String line ;
        while((line = reader.readLine()) != null) {
            lines.add(line);

            String[] str = line.split(",");
            String id = str[0];
            String 名称 = str[1];
            String 数量 = str[2];
            String 价格 = str[3];
            String 供应商 = str[4];
            String 二维码状态 = str[5];

            String jsonStr = "{" +
                    "\"id\": \"" + id + "\"," +
                    "\"名称\": \"" + 名称 + "\"," +
                    "\"数量\": \"" + 数量 + "\"," +
                    "\"价格\": \"" + 价格 + "\"," +
                    "\"供应商\": \"" + 供应商 + "\"," +
                    "\"二维码状态\": \"" + 二维码状态 + "\"" +
                    "}";
            jsonList = jsonList + jsonStr + ",";
        }

        jsonList = "[" + jsonList.substring(0, jsonList.length() -1) + "]";
        response.getWriter().println(jsonList);
    }
}
