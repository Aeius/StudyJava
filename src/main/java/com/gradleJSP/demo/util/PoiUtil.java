package com.gradleJSP.demo.util;

import com.gradleJSP.demo.entity.People;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class PoiUtil {

    public static void CreateExcelSheet(List<People> list, long rowCount, long colCount){
        // 엑셀 시트 생성
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("people data");

        // count() = row 갯수
        for(int i =0; i < rowCount; i++){
            Row row = sheet.createRow(i);
            People p = list.get(i);
            
            // 결과 값 셀에 입력
            for (int j = 0; j < colCount; j++) {
                Cell cell = row.createCell(j);
                switch (j) {
                    case 0 : cell.setCellValue(p.getNum());break;
                    case 1 : cell.setCellValue(p.getName());break;
                    case 2 : cell.setCellValue(p.getAge());break;
                }
                sheet.setColumnWidth(j, 3000);
            }
        }
        
        // 엑셀 파일 생성
        try {
            File xlsxFile = new File("C:/workspace/gradleJSP/src/main/java/com/gradleJSP/demo/output/people.xls");
            FileOutputStream fos = new FileOutputStream(xlsxFile);
            workbook.write(fos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }
}
