package com.atguigu.test;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestPOI {
   /* *//**
     * 读取客户端的数据到数据库
     *//*
    @Test
    public void readExcel() throws IOException {
        //获取excel文件
        XSSFWorkbook workbook=new XSSFWorkbook("e:/abc.xlsx");//abc.xlsx
        XSSFSheet sheet = workbook.getSheetAt(0);//获取具体的清单
        for (Row rows : sheet) {//行
            for (Cell cell : rows) {//列
                cell.setCellType(Cell.CELL_TYPE_STRING);
                System.out.println(cell.getStringCellValue());
            }
        }
       workbook.close();
        *//* System.out.println(sheet);
        XSSFRow rowFirst = sheet.getRow(0);
        XSSFCell cell = rowFirst.getCell(0);
        System.out.println(cell.getStringCellValue());*//*


    }

    //将数据库的数据写入到客户端excel
    @Test
    public void WriteExcel() throws IOException {
        XSSFWorkbook workbook=new XSSFWorkbook();//创建excel文件对象 e:/123.xlsx
        XSSFSheet sheet = workbook.createSheet("尚硅谷");
        XSSFRow rowHeader = sheet.createRow(0);
        rowHeader.createCell(0).setCellValue("编号");
        rowHeader.createCell(1).setCellValue("姓名");
        rowHeader.createCell(2).setCellValue("年龄");

        XSSFRow rowSecode = sheet.createRow(1);
        rowSecode.createCell(0).setCellValue("001");
        rowSecode.createCell(1).setCellValue("张三");
        rowSecode.createCell(2).setCellValue("20");
        workbook.write(new FileOutputStream("e:/123.xlsx"));
        workbook.close();
    }*/
}
