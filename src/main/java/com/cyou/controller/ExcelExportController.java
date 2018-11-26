package com.cyou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qibaichao on 2018/10/19.
 */
@Controller
@RequestMapping("/excelExport")
public class ExcelExportController extends BaseController {

    @RequestMapping("/export")
    public void export(HttpServletResponse response) {
        String filename = "test";
        try {
            InputStream input = new FileInputStream("F:\\20180810可能有问题债权.xlsx");
            exportExcelFile(filename + ".xls", response, input);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    /**
     * 导出excel文件
     *
     * @param filename
     * @param response
     * @param input
     */
    private void exportExcelFile(String filename, HttpServletResponse response, InputStream input) {
        response.setContentType("application/octet-stream;charset=UTF-8");
        try {
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + java.net.URLEncoder.encode(filename, "UTF-8"));
            // 客户端不缓存
            response.setHeader("Pragma", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
            OutputStream output = response.getOutputStream();
            byte[] b = new byte[input.available()];
            int bytesRead;
            while ((bytesRead = input.read(b)) != -1) {
                output.write(b, 0, bytesRead);
//                throw new NotActiveException();
            }
            output.flush();
//            output.close();
        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}
