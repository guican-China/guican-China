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
//id:1,名称:测试物资包,数量:10,价格:1000,供应商:京东,二维码状态:1,二维码:null
        String data="{\"id\":1,\"名称\":测试物资包,\"数量\":10,\"价格\":1000,\"供应商\":京东,\"二维码状态\":1,\"二维码\":null}";
        response.getWriter().println(data);
    }
}
