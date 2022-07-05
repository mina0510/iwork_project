package tw.com.ourProject.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import tw.com.ourProject.model.RestExcelBean;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/*
 * 操作excel
 * */
public class RestExcelUtils {

    public static List<RestExcelBean> excelToShopIdList(InputStream inputStream) throws Exception {
        List<RestExcelBean> list = new ArrayList<>();
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(inputStream);
            inputStream.close();
            //工作表對象
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
                RestExcelBean jiFenExcel = new RestExcelBean();
                row = sheet.getRow(i);
                for (int j = 0; j < colLength; j++) {
                    //列： 0項次	1餐廳名稱	2餐廳電話 3餐廳地址
                    cell = row.getCell(j);
                    if (cell != null) {
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        String data = cell.getStringCellValue();
                        data = data.trim();
                        if (j == 0) {
                            jiFenExcel.setNum(data);
                        } else if (j == 1) {
                            jiFenExcel.setRestNum(data);
                        } else if (j == 2) {
                            jiFenExcel.setRestTel(data);
                        } else if (j == 3) {
                            jiFenExcel.setRestAddr(data);
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