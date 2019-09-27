package com.hujiang.project.zhgd.utils;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hujiang.project.zhgd.zhNode.domain.ZhNode;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * @author DevinLiu
 * 通用excel导入数据库
 */
public class ExcelUtil {

    private final static String excel2003L =".xls";    //2003- 版本的excel
    private final static String excel2007U =".xlsx";   //2007+ 版本的excel


    /**
     * 获取实体对象返回属性名称
     * @param obj 实体对象
     * @return
     * @throws Exception
     */
    public java.lang.reflect.Field[] findEntityAllTypeName(Object obj)throws Exception{

        Class<? extends Object> cls = obj.getClass();

        return cls.getDeclaredFields();
    }

    /**
     * 根据文件选择excel版本
     * @return
     * @throws Exception
     */
    public Workbook chooseWorkbook(MultipartFile file) throws Exception{

        Workbook workbook = null;

        //把MultipartFile转化为File
        CommonsMultipartFile cmf = (CommonsMultipartFile)file;
        DiskFileItem dfi = (DiskFileItem) cmf.getFileItem();
        File fo = dfi.getStoreLocation();

        String filename = file.getOriginalFilename();
        String fileType = (filename.substring(filename.lastIndexOf("."), filename.length())).toLowerCase();

        if(excel2003L.equals(fileType)){
            workbook = new HSSFWorkbook(FileUtils.openInputStream(fo));  //2003-
        }else if(excel2007U.equals(fileType)){
            workbook = new XSSFWorkbook(FileUtils.openInputStream(fo));  //2007+
        }else{
            throw new Exception("解析的文件格式有误！");
        }
        return workbook;
    }


    /**
     * 导入excel
     * @param file 文件
     * @param sheetname 工作簿名称
     * @param obj 实体类
     * @return
     * @throws IOException
     */
    public  List<Object> importExcel(MultipartFile file,String sheetname,Object obj) throws IOException{

        Workbook workbook = null;

        try {
            //读取文件内容
            workbook = this.chooseWorkbook(file);

            //获取工作表
            Sheet sheet = workbook.getSheet(sheetname);

            //获取sheet中第一行行号
            int firstRowNum = sheet.getFirstRowNum();
            //获取sheet中最后一行行号
            int lastRowNum = sheet.getLastRowNum();

            //获取该实体所有定义的属性 返回Field数组
            java.lang.reflect.Field[] entityName = this.findEntityAllTypeName(obj);

            String classname =  obj.getClass().getName();
            Class<?> clazz = Class.forName(classname);

            List<Object> list = new ArrayList<Object>();

            //循环插入数据
            for(int i=firstRowNum+1;i<=lastRowNum;i++){

                Row row = sheet.getRow(i);

                //可以根据该类名生成Java对象
                Object pojo =  clazz.newInstance();

                //除自增编号外，实体字段匹配sheet列
                for(int j = 0;j < entityName.length-3;j++){

                    //获取属性的名字,将属性的首字符大写，方便构造set方法
                    String name = "set"+entityName[j+1].getName().substring(0, 1).toUpperCase().concat(entityName[j+1].getName().substring(1).toLowerCase())+"";
                    //获取属性的类型
                    String type = entityName[j+1].getGenericType().toString();

                    Method m = null;
                    //getMethod只能调用public声明的方法，而getDeclaredMethod基本可以调用任何类型声明的方法
                    m = obj.getClass().getDeclaredMethod(name,entityName[j+1].getType());

                    Cell pname = row.getCell(j);
                    //根据属性类型装入值
                    switch (type) {
                        case "char":
                        case "java.lang.Character":
                        case "class java.lang.String":
                            m.invoke(pojo,getVal(pname));
                            break;
                        case "int":
                        case "class java.lang.Integer":
                            m.invoke(pojo,Integer.valueOf(getVal(pname)));
                            break;
                        case "class java.util.Date":
                            m.invoke(pojo,getVal(pname));
                            break;
                        case "float":
                        case "double":
                        case "java.lang.Double":
                        case "java.lang.Float":
                        case "java.lang.Long":
                        case "java.lang.Short":
                        case "java.math.BigDecimal":
                            m.invoke(pojo,Double.valueOf(getVal(pname)));
                            break;
                        default:
                            break;
                    }
                }
                list.add(pojo);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            workbook.close();
        }
        return null;
    }

    /**
     * 处理类型
     * @param cell
     * @return
     */
    public static String getVal(Cell cell) {
        if (null != cell) {

            switch (cell.getCellType()) {
                case XSSFCell.CELL_TYPE_NUMERIC: // 数字

                    String val = cell.getNumericCellValue()+"";
                    int index = val.indexOf(".");

                    if (DateUtil.isCellDateFormatted(cell)) {
                        Date tempValue = cell.getDateCellValue();
                        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String value = simpleFormat.format(tempValue);
                        return value;
                    }
                    if (Integer.valueOf(val.substring(index+1)) == 0){
                        DecimalFormat df = new DecimalFormat("0");//处理科学计数法
                        return df.format(cell.getNumericCellValue());
                    }
                    return cell.getNumericCellValue()+"";//double
                case XSSFCell.CELL_TYPE_STRING: // 字符串
                    return cell.getStringCellValue() + "";
                case XSSFCell.CELL_TYPE_BOOLEAN: // Boolean
                    return cell.getBooleanCellValue() + "";
                case XSSFCell.CELL_TYPE_FORMULA: // 公式

                    try{
                        if(HSSFDateUtil.isCellDateFormatted(cell)){
                            Date date = cell.getDateCellValue();
                            return (date.getYear() + 1900) + "-" + (date.getMonth() + 1) +"-" + date.getDate();
                        }else{
                            return String.valueOf((int)cell.getNumericCellValue());
                        }
                    }catch (IllegalStateException e) {
                        return  String.valueOf(cell.getRichStringCellValue());
                    }
                case XSSFCell.CELL_TYPE_BLANK: // 空值
                    return "";
                case XSSFCell.CELL_TYPE_ERROR: // 故障
                    return "";
                default:
                    return "未知类型   ";
            }
        } else {
            return "";
        }
    }

    /*
     * 导出数据
     */
    public void exportExcel(String title,String[] rowName,List<Object[]> dataList,OutputStream out) throws Exception {
        try {
            HSSFWorkbook workbook = new HSSFWorkbook(); // 创建工作簿对象
            HSSFSheet sheet = workbook.createSheet(title); // 创建工作表
            //HSSFRow rowm = sheet.createRow(0);  // 产生表格标题行
            //HSSFCell cellTiltle = rowm.createCell(0);   //创建表格标题列
            // sheet样式定义;    getColumnTopStyle();    getStyle()均为自定义方法 --在下面,可扩展
            HSSFCellStyle columnTopStyle = this.getColumnTopStyle(workbook);// 获取列头样式对象
            HSSFCellStyle style = this.getStyle(workbook); // 获取单元格样式对象
            //合并表格标题行，合并列数为列名的长度,第一个0为起始行号，第二个1为终止行号，第三个0为起始列好，第四个参数为终止列号
            sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, (rowName.length - 1)));
            //cellTiltle.setCellStyle(columnTopStyle);    //设置标题行样式
            //cellTiltle.setCellValue(title);     //设置标题行值
            int columnNum = rowName.length;     // 定义所需列数
            HSSFRow rowRowName = sheet.createRow(2); // 在索引2的位置创建行(最顶端的行开始的第二行)
            // 将列头设置到sheet的单元格中
            for (int n = 0; n < columnNum; n++) {
                HSSFCell cellRowName = rowRowName.createCell(n); // 创建列头对应个数的单元格
                cellRowName.setCellType(HSSFCell.CELL_TYPE_STRING); // 设置列头单元格的数据类型
                HSSFRichTextString text = new HSSFRichTextString(rowName[n]);
                cellRowName.setCellValue(text); // 设置列头单元格的值
                cellRowName.setCellStyle(columnTopStyle); // 设置列头单元格样式
            }

            // 将查询出的数据设置到sheet对应的单元格中
            for (int i = 0; i < dataList.size(); i++) {
                Object[] obj = dataList.get(i);   // 遍历每个对象
                HSSFRow row = sheet.createRow(i + 3);   // 创建所需的行数
                for (int j = 0; j < obj.length; j++) {
                    HSSFCell cell = null;   // 设置单元格的数据类型
                    if (j == 0) {
                        cell = row.createCell(j, HSSFCell.CELL_TYPE_NUMERIC);
                        cell.setCellValue(i + 1);
                    } else {
                        cell = row.createCell(j, HSSFCell.CELL_TYPE_STRING);
                        if (!"".equals(obj[j]) && obj[j] != null) {
                            cell.setCellValue(obj[j].toString()); // 设置单元格的值
                        }
                    }
                    cell.setCellStyle(style); // 设置单元格样式
                }
            }

            // 让列宽随着导出的列长自动适应
            for (int colNum = 0; colNum < columnNum; colNum++) {
                int columnWidth = sheet.getColumnWidth(colNum) / 256;
                for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
                    HSSFRow currentRow;
                    // 当前行未被使用过
                    if (sheet.getRow(rowNum) == null) {
                        currentRow = sheet.createRow(rowNum);
                    } else {
                        currentRow = sheet.getRow(rowNum);
                    }
                    /*if (currentRow.getCell(colNum) != null) {
                        HSSFCell currentCell = currentRow.getCell(colNum);
                        if (currentCell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                            int length = currentCell.getStringCellValue()
                                    .getBytes().length;
                            if (columnWidth < length) {
                                columnWidth = length;
                            }
                        }
                    }*/
                }
                if (colNum == 0) {
                    sheet.setColumnWidth(colNum, (columnWidth - 2) * 256);
                } else {
                    sheet.setColumnWidth(colNum, (columnWidth + 4) * 256);
                }
            }
            workbook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * 列头单元格样式
     */
    private HSSFCellStyle getColumnTopStyle(HSSFWorkbook workbook) {

        // 设置字体
        HSSFFont font = workbook.createFont();
        // 设置字体大小
        font.setFontHeightInPoints((short) 11);
        // 字体加粗
        //font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 设置字体名字
        font.setFontName("Courier New");
        // 设置样式;
        HSSFCellStyle style = workbook.createCellStyle();
        //设置背景色
        style.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
       /* // 设置底边框;
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        // 设置底边框颜色;
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        // 设置左边框;
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        // 设置左边框颜色;
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        // 设置右边框;
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        // 设置右边框颜色;
        style.setRightBorderColor(HSSFColor.BLACK.index);
        // 设置顶边框;
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);*/
        // 设置顶边框颜色;
        //style.setTopBorderColor(HSSFColor.BLACK.index);
        // 在样式用应用设置的字体;
        style.setFont(font);
        // 设置自动换行;
        style.setWrapText(false);
        // 设置水平对齐的样式为居中对齐;
        style.setAlignment(HorizontalAlignment.CENTER);
        // 设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        return style;

    }

    /*
     * 列数据信息单元格样式
     */
    private HSSFCellStyle getStyle(HSSFWorkbook workbook) {
        // 设置字体
        HSSFFont font = workbook.createFont();
        // 设置字体大小
        // font.setFontHeightInPoints((short)10);
        // 字体加粗
        // font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 设置字体名字
        font.setFontName("Courier New");
        // 设置样式;
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置底边框;
        //style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        // 设置底边框颜色;
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        // 设置左边框;
        //style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        // 设置左边框颜色;
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        // 设置右边框;
        //style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        // 设置右边框颜色;
        style.setRightBorderColor(HSSFColor.BLACK.index);
        // 设置顶边框;
        //style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        // 设置顶边框颜色;
        style.setTopBorderColor(HSSFColor.BLACK.index);
        // 在样式用应用设置的字体;
        style.setFont(font);
        // 设置自动换行;
        style.setWrapText(false);
        /*// 设置水平对齐的样式为居中对齐;
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);*/
        return style;
    }

    /**
     * 关闭输出流
     * @param os
     */
    private void close(OutputStream os) {
        if (os != null) {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}