package com.example.demo_web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@WebServlet(name = "QRcodebuildForJiedao", value = "/qrbjd")
public class QRcodebuildForJiedao extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String mpId = request.getParameter("mpId");
        String xqId = request.getParameter("xqId");
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream(
                                        "c://Engilsh//wuzibao.txt"
                                ), "utf-8"
                        )
                );

        Map<String, String> map = new HashMap<>();
        String line;

        while ((line = reader.readLine()) != null) {
            String[] strs = line.split(",");
            map.put(strs[0], line);

            if (map.size() == Integer.parseInt(mpId)) {
                strs[5] = 2 + "";
                String newline = "";
                for (String str : strs) {
                    newline = newline + str + ",";
                }
                newline = newline+xqId;

                map.put(strs[0], newline);
            }
        }
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream("c://Engilsh//wuzibao.txt"), "utf-8"));
        String lines="";
        for (int i = 1; i <= map.size(); i++) {
            line = map.get(i);
            lines = lines + "\n" + line;
        }
        out.write(lines);
    }
    @Override // 重写父类的函数, 标识作用，让编译器去识别的
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf-8");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "*");

        // 物资包ID
        String mpId = req.getParameter("mpId");
        // 小区ID
        String xqId = req.getParameter("xqId");

        // 绑定的逻辑
        // 第一步：修改物资包对应ID的物资二维码状态
        // 1.把数据取出来
        BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream(
                                        "c://Engilsh//wuzibao.txt"
                                ), "utf-8"
                        )
                );

        Map<String, String> map = new TreeMap<>(); // key: id， value: 这一行的数据
        String line;
        while ((line = reader.readLine()) != null) {
            // lines.add(line);
            String[] strs = line.split(",");
            map.put(strs[0], line); // 原始数据

            if (map.size() == Integer.parseInt(mpId)) {
                // 物质包TXT文件里定义的数据格式：
                // id，名称，数量，价格，供应商，二维码状态(TODO ),小区ID
                // String 二维码状态 = strs[5]; //0:二维码不可以生成，1表示二维码可以生成
                strs[5] = 1 + "";
                // 变更的数据放进去
                String newLine = "";
                for (String str : strs) {
                    newLine = newLine + str + ",";
                }
                newLine = newLine.substring(0,newLine.length()-1);

                map.put(strs[0], newLine); // 变更后的数据, 就把原始的数据给覆盖掉了
            }
        }
        // 2.新数据刷新到txt文件中
        // 写数据的方式有2中：
        // 第一种：就是自己拼接换行符，然后统一写到txt里；
        // 第二种(粗暴)：写一个新的文件里面, 然后把老的删掉，新文件改名成原先的文件名
        BufferedWriter out = new BufferedWriter
                (new OutputStreamWriter(new FileOutputStream("c://Engilsh//wuzibao.txt"), "UTF-8")); // TODO 物资包txt文件的地址
        String lines = "";
                StringBuilder sb=new StringBuilder();
        map.forEach((key,value) -> {
            if ("".equals(sb.toString())) {
                sb.append(value);
            } else {
                sb.append("\n").append(value);
            }
        } );
        lines = sb.toString();

        if (!"".equals(lines)) {
            out.write(lines);
            out.flush();
            out.close();
        }

        // 第二步：物资包跟小区绑定：
        // 参照66行代码

        // 第三步：在前端页面根据状态来进行生成二维码

        resp.getWriter().println("{}");
    }
}