package com.lachesis.windranger.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

/**
 * poi excel 工具类
 * @author rxz
 *
 * @param <T>
 */
public class ExcelUtil<T> {
	

	/**
	 * 导出excel表格
	 * @param title sheet名称
	 * @param headers 表格标题栏
	 * @param dataset 表格体
	 * @param pattern 时间格式，如：yyyy-MM-dd
	 * @return
	 */
	public HSSFWorkbook exportExcel(String title, Map<String, String> headersMap, List<T> dataset, String pattern) {
		// 1.声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 2.生成一个表格
		HSSFSheet sheet = workbook.createSheet(title);
		// 设置表格默认列宽度为20个字节
		sheet.setDefaultColumnWidth(20);
		
		// 3.生成标题样式
		HSSFCellStyle styleHead = generateStyleHead(workbook);
		// 4.生成表体样式
		HSSFCellStyle styleBody = generateStyleBody(workbook);
		// 5.生成表头
		generateHead(headersMap, sheet, styleHead);
		// 6.生成表体
		generateBody(headersMap, dataset, pattern, sheet,styleBody);
		return workbook;
	}

	private HSSFCellStyle generateStyleBody(HSSFWorkbook workbook) {
		HSSFCellStyle styleBody = workbook.createCellStyle();
		styleBody.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
		styleBody.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		styleBody.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		styleBody.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		styleBody.setBorderRight(HSSFCellStyle.BORDER_THIN);
		styleBody.setBorderTop(HSSFCellStyle.BORDER_THIN);
		styleBody.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		styleBody.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 生成表体字体
		HSSFFont fontBody = workbook.createFont();
		fontBody.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		// 把字体应用到当前的样式
		styleBody.setFont(fontBody);
		return styleBody;
	}

	private HSSFCellStyle generateStyleHead(HSSFWorkbook workbook) {
		HSSFCellStyle styleHead = workbook.createCellStyle();
		// 设置样式
		styleHead.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
		styleHead.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		styleHead.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		styleHead.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		styleHead.setBorderRight(HSSFCellStyle.BORDER_THIN);
		styleHead.setBorderTop(HSSFCellStyle.BORDER_THIN);
		styleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 生成标题字体
		HSSFFont fontHead = workbook.createFont();
		fontHead.setColor(HSSFColor.VIOLET.index);
		fontHead.setFontHeightInPoints((short) 12);
		fontHead.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把字体应用到当前的样式
		styleHead.setFont(fontHead);
		return styleHead;
	}
	
	/**
	 *  为工作表生成内容
	 * @param headersMap
	 * @param dataset
	 * @param pattern
	 * @param sheet
	 */
	private void generateBody(Map<String, String> headersMap, List<T> dataset, String pattern, HSSFSheet sheet, HSSFCellStyle styleBody) {
		HSSFRow row;
		// 遍历集合数据，产生数据行
		Iterator<T> it = dataset.iterator();
		int index = 0;
		try {
			while (it.hasNext()) {
				index++;
				row = sheet.createRow(index);
				T t = (T) it.next();
				// 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
				Field[] fields = t.getClass().getDeclaredFields();
				Field[] superFields = t.getClass().getSuperclass().getDeclaredFields();
				//父类属性要合并
				fields=unionFileds(fields,superFields);
				Object[] keys = headersMap.keySet().toArray();
				String[] headerFields = new String[keys.length];// 下标对应属性，调整顺序
				for (int k = 0; k < keys.length; k++) {
					String temp = keys[k].toString();
					int xuHao = Integer.valueOf(temp.split("-")[0]);
					headerFields[xuHao] = temp.split("-")[1];
				}
				for (int i = 0; i < headerFields.length; i++) {
					HSSFCell cell = row.createCell(i);
					String headerField = headerFields[i];
					for (Field field : fields) {
						String fieldName = field.getName();
						if (fieldName.equals(headerField)) {
							String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase()
									+ fieldName.substring(1);
							Method method = t.getClass().getMethod(getMethodName, new Class[] {});
							Object value = method.invoke(t, new Object[] {});
							if (value != null) {
								cell.setCellStyle(styleBody);
								if (value instanceof Date) {
									Date date = (Date) value;
									SimpleDateFormat sdf = new SimpleDateFormat(pattern);
									String textValue = sdf.format(date);
									cell.setCellValue(textValue);
								} else {
									cell.setCellValue(value.toString());
								}
							}

						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 产生表格标题行
	 * @param headersMap
	 * @param sheet
	 * @param styleHead
	 */
	private void generateHead(Map<String, String> headersMap, HSSFSheet sheet, HSSFCellStyle styleHead) {
		HSSFRow row = sheet.createRow(0);
		Set<String> headerKeys = headersMap.keySet();
		int index=0;
		for (String headerKey : headerKeys) {
			HSSFCell cell = row.createCell(index);
			cell.setCellStyle(styleHead);
			String cellValue = headersMap.get(headerKey);
			cell.setCellValue(cellValue);
			index++;
		}
	}
	
	/**
	 * 合并field数组
	 * @param fields
	 * @param superFields
	 * @return
	 */
	private Field[] unionFileds(Field[] fields, Field[] superFields) {
		Field[] unionFileds=new Field[fields.length+superFields.length];
		for(int i=0;i<fields.length;i++){
			unionFileds[i] = fields[i];
	    }        
	    for(int i=0;i<superFields.length;i++){
	    	unionFileds[fields.length+i]=superFields[i];
	    }
		return unionFileds;
	}
	

}
