package com.example.demo_web;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "ShipinForGongyinshangServlet", value = "/wzbdysp")
public class WuzibaoDuiyingShipinServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");

        String ID = request.getParameter("ID");
        BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream(
                                        new File("c:\\Engilsh\\shipin.txt")
                                ), "utf-8"
                        )
                );

        String jsonList = "";
        List<String> lines = new ArrayList<>();
        String line ;

        while((line = reader.readLine()) != null) {
            lines.add(line);
            String[] str = line.split(",");
            String mpId=str[5];
            if (!ID.equals(mpId))continue;

            String 种类 = str[0];
            String 数量 = str[1];
            String 生产日期 = str[2];
            String 保质期 = str[3];
            String 供应商 = str[4];
            String mp_id = str[5];

            String jsonStr = "{" +
                    "\"种类\": \"" + 种类 + "\"," +
                    "\"数量\": \"" + 数量 + "\"," +
                    "\"生产日期\": \"" + 生产日期 + "\"," +
                    "\"保质期\": \"" + 保质期 + "\"," +
                    "\"供应商\": \"" + 供应商 + "\"," +
                    "\"物资包ID\": \"" + mp_id + "\"" +
                    "}";
            jsonList = jsonList + jsonStr + ",";
        }

        jsonList = "[" + jsonList.substring(0, jsonList.length() -1) + "]";
        response.getWriter().println(jsonList);
    }
}
