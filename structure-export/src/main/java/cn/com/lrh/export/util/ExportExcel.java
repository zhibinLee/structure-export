package cn.com.lrh.export.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;

public class ExportExcel {
	public static final String TABLE_NAME = "table_name";
	public static final String TABLE_COMMENT = "table_comment";
	public static final String COLUMN_LIST = "column_list";

	/**
	  *    创建Excel
	 * @param dataList 数据
	 * @param titles   标题
	 * @param columns  字段名
	 * @throws IOException
	 */
	public static void createExcel(List<Map<String, Object>> dataList, String [] titles, String[] columns ) throws IOException {
		createExcel(dataList, titles, columns, 0, 0);
	}
	
	 
	/**
	  *    创建Excel
	 * @param dataList      数据
	 * @param titles        标题
	 * @param columns       字段名
	 * @param startRow      起始行号
	 * @param startColumn   起始列号
	 * @throws IOException
	 */
    @SuppressWarnings("unchecked")
	public static void createExcel(List<Map<String, Object>> dataList, String [] titles, String[] columns, int startRow, int startColumn) throws IOException {
    	
        HSSFWorkbook wb = new HSSFWorkbook();
       
        HSSFCellStyle titleStyle = createTitleCellStyle(wb);
        HSSFCellStyle fieldStyle = createFieldCellStyle(wb);
        for(Map<String, Object> data :dataList) {
	        HSSFSheet sheet = wb.createSheet(data.get(TABLE_NAME).toString());
	        //在sheet里创建第一行
	        HSSFRow headRow = sheet.createRow(startRow);
	        CellRangeAddress cra = new CellRangeAddress(startRow,startRow,startColumn,titles.length-1+startColumn);
	        sheet.addMergedRegion(cra);
	        headRow.setHeightInPoints(26);
	        HSSFCellStyle headStyle = createHeadCellStyle(wb,cra,sheet);
	        HSSFCell headCell = headRow.createCell(startColumn);
	        headCell.setCellStyle(headStyle);
	        headCell.setCellValue(outputFormat(data.get(TABLE_NAME).toString(),data.get(TABLE_COMMENT).toString()));
	       
	        HSSFRow titleRow = sheet.createRow(headRow.getRowNum()+1);
	        titleRow.setHeightInPoints(18);
	      //在excel表中添加标题
	        for (int i = 0; i < titles.length; i++) {
	            HSSFCell cell = titleRow.createCell(i+startColumn);
	            HSSFRichTextString text = new HSSFRichTextString(titles[i]);
	            cell.setCellValue(text);
	            cell.setCellStyle(titleStyle);
	            sheet.autoSizeColumn((short) i); //调整每一列宽度
	        }
	        List<Map<String, String>> list = (List<Map<String, String>>) data.get(COLUMN_LIST);
	        for (int i=0; i<list.size(); i++) {
	        	HSSFRow fieldRow = sheet.createRow(i+titleRow.getRowNum()+1);
	        	Map<String, String> map = list.get(i);
	        	for (int x = 0; x < columns.length; x++) {
	                fieldRow.setHeightInPoints(15);
	            	HSSFCell cell = fieldRow.createCell(x+startColumn);
	            	cell.setCellStyle(fieldStyle);
	            	cell.setCellValue(map.get(columns[x]) == null?"" : String.valueOf(map.get(columns[x])));
	            }
	        }
	        for (int i = 0; i < titles.length; i++) {
	            sheet.autoSizeColumn((short) i+startColumn);
	            sheet.setColumnWidth(i+startColumn, sheet.getColumnWidth(i+startColumn) * 14 / 10);
	        }
        }
        File dir = new File("e:/temp/");
        if(!dir.exists()) {
        	dir.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(new File(dir, new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()).toString() +".xls"));
        wb.write(out);
        out.close();
    }
    
    public static HSSFCellStyle createHeadCellStyle(HSSFWorkbook workbook,CellRangeAddress cra,Sheet sheet){
        HSSFCellStyle style = workbook.createCellStyle();
        RegionUtil.setBorderBottom(BorderStyle.THIN, cra, sheet);
 		RegionUtil.setBorderLeft(BorderStyle.THIN, cra, sheet);
 		RegionUtil.setBorderRight(BorderStyle.THIN, cra, sheet);
 		RegionUtil.setBorderTop(BorderStyle.THIN, cra, sheet);
 		
 		style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
 		
        style.setWrapText(true);  
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND); 
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);//设置前景色
        //style.setFillBackgroundColor(IndexedColors.GREY_25_PERCENT.index); //设置背景颜色
      
        
        //设置字体格式

        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 11);
        font.setFontName("华文中宋");
        font.setBold(true);
        style.setFont(font);
        return style;
  }
    public static HSSFCellStyle createTitleCellStyle(HSSFWorkbook workbook){
        HSSFCellStyle style = workbook.createCellStyle();
        //设置上下左右四个边框宽度
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        //设置单元格背景色  
        style.setWrapText(true);  
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
        //style.setFillBackgroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        
        //设置字体格式

        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 10);
        font.setFontName("verdana");
        font.setBold(true);
        style.setFont(font);
        return style;
  }
    public static HSSFCellStyle createFieldCellStyle(HSSFWorkbook workbook){
        HSSFCellStyle style = workbook.createCellStyle();
        //设置上下左右四个边框宽度
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        //设置单元格背景色
        
        style.setWrapText(true);  
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
       // style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
       // style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        
        //设置字体格式

        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 9);
        font.setFontName("verdana");
        style.setFont(font);
        return style;
  }
    
    public static String outputFormat(String tableName,String table_comment) {
		int total = 66;
		String info = new StringBuffer(computation(total)).append("\n")
		.append(computation(total,tableName+"("+table_comment+")")).append("\n")
		.append(computation(total)).toString();
		//System.out.println(info);
		info = tableName+"("+table_comment+")";
		return info;
	}

	private static String computation(int total) {
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<total;i++) {
			sb.append("*");
		}
		return sb.toString();
	}
	
	private static String computation(int total,String info) {
		StringBuffer sb = new StringBuffer(computation(5));
		sb.append("   ");
		sb.append(info);
		return sb.toString();
	}
}
