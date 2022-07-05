package tw.com.ourProject.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.hibernate.exception.DataException;

import tw.com.ourProject.model.DishExcelBean;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/*
 * 操作excel
 * */
public class DishExcelUtils {

    public static List<DishExcelBean> excelToShopIdList(InputStream inputStream){
        List<DishExcelBean> list = new ArrayList<>();
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(inputStream);
            inputStream.close();
            //工作表
            Sheet sheet = workbook.getSheetAt(0);
            //總行數
            int rowLength = sheet.getLastRowNum();
            //工作表的列
            Row row = sheet.getRow(0);
            //總列數
            int colLength = row.getLastCellNum();
            //得到指定的單元格
            Cell cell = row.getCell(0);
            for (int i = 1; i <= rowLength; i++) {
                DishExcelBean jiFenExcel = new DishExcelBean();
                row = sheet.getRow(i);
                for (int j = 0; j < colLength; j++) {
                    //列： 0項次 1品項 2組合內容 3菜單照片 4單價 5餐廳編號
                    cell = row.getCell(j);
                    //                    System.out.print(cell + ",");
                    if (cell != null) {
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        String data = cell.getStringCellValue();
                        data = data.trim();
                       
                        if (j == 0) {
                            jiFenExcel.setNum(data);
                        } else if (j == 1) {
                            jiFenExcel.setDishnum(data);
                        }  else if (j == 2) {
                            jiFenExcel.setDishphoto(data);
                        } else if (j == 3) {
                            jiFenExcel.setPrice(Integer.parseInt(data));
                        } else if (j == 4) {
                            jiFenExcel.setRestnum(data);
                        }
                    }
                }
                list.add(jiFenExcel);
            }
        } catch (Exception e) {
        }
        return list;
    }
}