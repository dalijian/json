//package com.lijian.test.poi;
//
//import java.io.FileOutputStream;
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.poi.ss.usermodel.BorderStyle;
//import org.apache.poi.ss.usermodel.CellStyle;
//import org.apache.poi.ss.usermodel.Chart;
//import org.apache.poi.ss.usermodel.ClientAnchor;
//import org.apache.poi.ss.usermodel.FillPatternType;
//import org.apache.poi.ss.usermodel.HorizontalAlignment;
//import org.apache.poi.ss.usermodel.IndexedColors;
//import org.apache.poi.ss.util.CellRangeAddress;
//import org.apache.poi.xssf.streaming.SXSSFDrawing;
//import org.apache.poi.xssf.streaming.SXSSFSheet;
//import org.apache.poi.xssf.streaming.SXSSFWorkbook;
//import org.apache.poi.xssf.usermodel.XSSFChart;
//import org.openxmlformats.schemas.drawingml.x2006.chart.CTAreaChart;
//import org.openxmlformats.schemas.drawingml.x2006.chart.CTAreaSer;
//import org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource;
//import org.openxmlformats.schemas.drawingml.x2006.chart.CTBarChart;
//import org.openxmlformats.schemas.drawingml.x2006.chart.CTBarSer;
//import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
//import org.openxmlformats.schemas.drawingml.x2006.chart.CTCatAx;
//import org.openxmlformats.schemas.drawingml.x2006.chart.CTChart;
//import org.openxmlformats.schemas.drawingml.x2006.chart.CTDLbls;
//import org.openxmlformats.schemas.drawingml.x2006.chart.CTLegend;
//import org.openxmlformats.schemas.drawingml.x2006.chart.CTLineChart;
//import org.openxmlformats.schemas.drawingml.x2006.chart.CTLineSer;
//import org.openxmlformats.schemas.drawingml.x2006.chart.CTMarker;
//import org.openxmlformats.schemas.drawingml.x2006.chart.CTMarkerStyle;
//import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumDataSource;
//import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumRef;
//import org.openxmlformats.schemas.drawingml.x2006.chart.CTPie3DChart;
//import org.openxmlformats.schemas.drawingml.x2006.chart.CTPieChart;
//import org.openxmlformats.schemas.drawingml.x2006.chart.CTPieSer;
//import org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea;
//import org.openxmlformats.schemas.drawingml.x2006.chart.CTScaling;
//import org.openxmlformats.schemas.drawingml.x2006.chart.CTSerTx;
//import org.openxmlformats.schemas.drawingml.x2006.chart.CTStrRef;
//import org.openxmlformats.schemas.drawingml.x2006.chart.CTValAx;
//import org.openxmlformats.schemas.drawingml.x2006.chart.STAxPos;
//import org.openxmlformats.schemas.drawingml.x2006.chart.STBarDir;
//import org.openxmlformats.schemas.drawingml.x2006.chart.STBarGrouping;
//import org.openxmlformats.schemas.drawingml.x2006.chart.STCrossBetween;
//import org.openxmlformats.schemas.drawingml.x2006.chart.STCrosses;
//import org.openxmlformats.schemas.drawingml.x2006.chart.STLblAlgn;
//import org.openxmlformats.schemas.drawingml.x2006.chart.STTickMark;
//import org.openxmlformats.schemas.drawingml.x2006.chart.STBarGrouping.Enum;
//import org.openxmlformats.schemas.drawingml.x2006.chart.STDispBlanksAs;
//import org.openxmlformats.schemas.drawingml.x2006.chart.STGrouping;
//import org.openxmlformats.schemas.drawingml.x2006.chart.STLegendPos;
//import org.openxmlformats.schemas.drawingml.x2006.chart.STMarkerStyle;
//import org.openxmlformats.schemas.drawingml.x2006.chart.STOrientation;
//import org.openxmlformats.schemas.drawingml.x2006.chart.STTickLblPos;
//
//import com.jsfund.crm.common.bean.BaseFormMap;
//
//public class ExcelChartUtil {
//
//public static void main(String[] args) throws Exception {
//	boolean result=false;
//	List<BaseFormMap> dataList=new ArrayList<BaseFormMap>();//数据
//	List<String> fldNameArr = new ArrayList<String>();// 字段名
//	List<String> titleArr = new ArrayList<String>();// 标题
//	BaseFormMap baseFormMap=new BaseFormMap();
//	List<String> showtailArr=new ArrayList<String>();
//	List<String> ispercentArr=new ArrayList<String>();
//	baseFormMap.put("value1", "股票");
//	baseFormMap.put("value2", new BigDecimal(new java.util.Random().nextDouble()));
//	baseFormMap.put("value3", new BigDecimal(new java.util.Random().nextDouble()));
//	baseFormMap.put("value4", new BigDecimal(new java.util.Random().nextDouble()));
//
//	BaseFormMap baseFormMap1=new BaseFormMap();
//	baseFormMap1.put("value1", "货币型基金");
//	baseFormMap1.put("value2", new BigDecimal(new java.util.Random().nextDouble()));
//	baseFormMap1.put("value3", new BigDecimal(new java.util.Random().nextDouble()));
//	baseFormMap1.put("value4", new BigDecimal(new java.util.Random().nextDouble()));
//
//	BaseFormMap baseFormMap2=new BaseFormMap();
//	baseFormMap2.put("value1", "可转债");
//	baseFormMap2.put("value2", new BigDecimal(new java.util.Random().nextDouble()));
//	baseFormMap2.put("value3", new BigDecimal(new java.util.Random().nextDouble()));
//	baseFormMap2.put("value4", new BigDecimal(new java.util.Random().nextDouble()));
//
//	BaseFormMap baseFormMap3=new BaseFormMap();
//	baseFormMap3.put("value1", "买入返售");
//	baseFormMap3.put("value2", new BigDecimal(new java.util.Random().nextDouble()));
//	baseFormMap3.put("value3", new BigDecimal(new java.util.Random().nextDouble()));
//	baseFormMap3.put("value4", new BigDecimal(new java.util.Random().nextDouble()));
//
//	BaseFormMap baseFormMap4=new BaseFormMap();
//	baseFormMap4.put("value1", "通知存款");
//	baseFormMap4.put("value2", new BigDecimal(new java.util.Random().nextDouble()));
//	baseFormMap4.put("value3", new BigDecimal(new java.util.Random().nextDouble()));
//	baseFormMap4.put("value4", new BigDecimal(new java.util.Random().nextDouble()));
//
//	BaseFormMap baseFormMap5=new BaseFormMap();
//	baseFormMap5.put("value1", "当月累计");
//	baseFormMap5.put("value2", new BigDecimal(new java.util.Random().nextDouble()));
//	baseFormMap5.put("value3", new BigDecimal(new java.util.Random().nextDouble()));
//	baseFormMap5.put("value4", new BigDecimal(new java.util.Random().nextDouble()));
//
//	fldNameArr.add("value1");
//	fldNameArr.add("value2");
//	fldNameArr.add("value3");
//	fldNameArr.add("value4");
//
//	titleArr.add("类型");
//	titleArr.add("买入");
//	titleArr.add("卖出");
//	titleArr.add("分红");
//
//	showtailArr.add("0");
//	showtailArr.add("2");
//	showtailArr.add("2");
//	showtailArr.add("2");
//
//	ispercentArr.add("0");
//	ispercentArr.add("1");
//	ispercentArr.add("1");
//	ispercentArr.add("1");
//
//	dataList.add(baseFormMap);
//	dataList.add(baseFormMap1);
//	dataList.add(baseFormMap2);
//	dataList.add(baseFormMap3);
//	dataList.add(baseFormMap4);
//	dataList.add(baseFormMap5);
//
//
//	SXSSFWorkbook wb = new SXSSFWorkbook();
//	SXSSFSheet sheet = wb.createSheet("Sheet1");
//
//	result=createChart(wb,sheet,10,"bar",STBarGrouping.STACKED,false,false,dataList, fldNameArr, titleArr,showtailArr,ispercentArr);
//
//	result=createChart(wb,sheet,10+dataList.size()+12,"bar",STBarGrouping.CLUSTERED,true,true,dataList, fldNameArr, titleArr,showtailArr,ispercentArr);
//
//
//
//
//	//System.out.println(ctChart);
//    System.out.println(result);
//
//    FileOutputStream fileOut = new FileOutputStream("D://BarChart.xlsx");
//    wb.write(fileOut);
//    fileOut.close();
//}
//
///**
// * @Description: 创建Excel数据
// * @param wb:工作薄
// * @param sheet：wb.createSheet();
// * @param sheetName：sheet名称
// * @param dataList
// * @param fldNameArr
// * @param titleArr
// * @param showtailArr
// * @param ispercentArr
// * @param position:从第几行开始(0：就是第一行)
// * @return boolean
// */
//public static boolean refreshChartExcel(SXSSFWorkbook wb,SXSSFSheet sheet,
//		List<BaseFormMap> dataList,List<String> fldNameArr,List<String> titleArr,
//		List<String> showtailArr,List<String> ispercentArr,int position) {
//	boolean result = true;
//	//样式准备
//	CellStyle style = wb.createCellStyle();
//    style.setFillForegroundColor(IndexedColors.ROYAL_BLUE.getIndex());
//    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//    style.setBorderBottom(BorderStyle.THIN); //下边框
//    style.setBorderLeft(BorderStyle.THIN);//左边框
//    style.setBorderTop(BorderStyle.THIN);//上边框
//    style.setBorderRight(BorderStyle.THIN);//右边框
//    style.setAlignment(HorizontalAlignment.CENTER);
//
//	CellStyle style1 = wb.createCellStyle();
//    style1.setBorderBottom(BorderStyle.THIN); //下边框
//    style1.setBorderLeft(BorderStyle.THIN);//左边框
//    style1.setBorderTop(BorderStyle.THIN);//上边框
//    style1.setBorderRight(BorderStyle.THIN);//右边框
//    style1.setAlignment(HorizontalAlignment.CENTER);
//
//    CellStyle cellStyle = wb.createCellStyle();
//    cellStyle.setBorderTop(BorderStyle.THIN);//上边框
//    cellStyle.setBorderBottom(BorderStyle.THIN); //下边框
//    cellStyle.setBorderLeft(BorderStyle.THIN);//左边框
//    cellStyle.setBorderRight(BorderStyle.THIN);//右边框
//    cellStyle.setAlignment(HorizontalAlignment.CENTER);//水平对齐方式
//    //cellStyle.setVerticalAlignment(VerticalAlignment.TOP);//垂直对齐方式
//
//	//根据数据创建excel第一行标题行
//	for (int i = 0; i < titleArr.size(); i++) {
//		if(sheet.getRow(position)==null){
//			sheet.createRow(position).createCell(i).setCellValue(titleArr.get(i)==null?"":titleArr.get(i));
//
//		}else{
//			sheet.getRow(position).createCell(i).setCellValue(titleArr.get(i)==null?"":titleArr.get(i));
//		}
//		//标题行创建背景颜色
//		sheet.getRow(position).getCell(i).setCellStyle(style);
//	}
//
//	//遍历数据行
//	for (int i = 0; i < dataList.size(); i++) {
//		BaseFormMap baseFormMap = dataList.get(i);//数据行
//		//fldNameArr字段属性
//		for (int j = 0; j < fldNameArr.size(); j++) {
//			if(sheet.getRow(position+i+1)==null){
//				if(j==0){
//					try {
//						sheet.createRow(position+i+1).createCell(j).setCellValue(baseFormMap.getStr(fldNameArr.get(j))==null?"":baseFormMap.getStr(fldNameArr.get(j)));
//					} catch (Exception e) {
//						if(baseFormMap.getStr(fldNameArr.get(j))==null){
//							sheet.createRow(position+i+1).createCell(j).setCellValue("");
//						}else{
//							sheet.createRow(position+i+1).createCell(j).setCellValue(baseFormMap.getDate(fldNameArr.get(j)));
//						}
//					}
//				}
//				//标题行创建背景颜色
//				sheet.getRow(position+i+1).getCell(j).setCellStyle(style1);
//			}else{
//				BigDecimal b=baseFormMap.getBigDecimal(fldNameArr.get(j));
//				double value=0d;
//				if(b!=null){
//					value=b.doubleValue();
//				}
//				if(value==0){
//					sheet.getRow(position+i+1).createCell(j);
//				}else{
//					sheet.getRow(position+i+1).createCell(j).setCellValue(baseFormMap.getBigDecimal(fldNameArr.get(j)).doubleValue());
//				}
//				if("1".equals(ispercentArr.get(j))){//是否设置百分比
//					// 设置Y轴的数字为百分比样式显示
//				    StringBuilder sb=new StringBuilder();
//
//				    if("0".equals(showtailArr.get(j))){//保留几位小数
//				    	sb.append("0");
//				    	if("1".equals(ispercentArr.get(j))){//是否百分比
//				    		sb.append("%");
//				    	}
//				    }else{
//				    	sb.append("0.");
//				    	for(int k=0;k<Integer.parseInt(showtailArr.get(j));k++){
//				    		sb.append("0");
//				    	}
//				    	if("1".equals(ispercentArr.get(j))){//是否百分比
//				    		sb.append("%");
//				    	}
//				    }
//				    cellStyle.setDataFormat(wb.createDataFormat().getFormat(sb.toString()));
//		            sheet.getRow(position+i+1).getCell(j).setCellStyle(cellStyle);
//				}else{
//					//是否设置百分比
//					// 设置Y轴的数字为百分比样式显示
//				    StringBuilder sb=new StringBuilder();
//
//				    if("0".equals(showtailArr.get(j))){//保留几位小数
//				    	sb.append("0");
//				    }else{
//				    	sb.append("0.");
//				    	for(int k=0;k<Integer.parseInt(showtailArr.get(j));k++){
//				    		sb.append("0");
//				    	}
//				    }
//		            cellStyle.setDataFormat(wb.createDataFormat().getFormat(sb.toString()));
//		            sheet.getRow(position+i+1).getCell(j).setCellStyle(cellStyle);
//				}
//			}
//		}
//
//	}
//	return result;
//}
//
///**
// * @Description:创建动态图
// * @param wb
// * @param sheet
// * @param sheetName
// * @param curRow:当前行号
// * @param type:图类型
// * @param group:柱状图类型 @see STBarGrouping
// * @param isLegend:是否添加图注
// * @param isvalAxis:是否添加Y左轴
// * @param dataList:数据
// * @param fldNameArr:属性
// * @param titleArr:标题
// * @param showtailArr:保留几位小数
// * @param ispercentArr:是否百分比
// * @return
// */
//public static boolean createChart(SXSSFWorkbook wb,SXSSFSheet sheet, int curRow,String type, Enum group,boolean isLegend,boolean isvalAxis, List<BaseFormMap> dataList, List<String> fldNameArr, List<String> titleArr,List<String> showtailArr,List<String> ispercentArr) {
//	boolean result=false;
//	String sheetName=sheet.getSheetName();
//	//动态表sheet刷新
//	result=refreshChartExcel(wb, sheet,dataList, fldNameArr, titleArr, showtailArr, ispercentArr, curRow);
//
//	//创建一个画布
//	SXSSFDrawing drawing = sheet.createDrawingPatriarch();
//
//    //前四个默认0，[0,5]：从0列5行开始;[6,20]:宽度6，20向下扩展到20行
//    //默认宽度(14-8)*12
//	ClientAnchor anchor =null;
//	if(dataList.size()<10){
//		anchor=drawing.createAnchor(0, 0, 0, 0, 0, curRow+dataList.size()+1, 6, curRow+dataList.size()+12);
//	}else{
//		anchor=drawing.createAnchor(0, 0, 0, 0, 0, curRow+dataList.size()+1, (int)Math.round(dataList.size()*0.5), curRow+dataList.size()+12);
//	}
//
//
//    //创建一个chart对象
//    Chart chart = drawing.createChart(anchor);
//    CTChart ctChart = ((XSSFChart)chart).getCTChart();
//    CTPlotArea ctPlotArea = ctChart.getPlotArea();
//    if("bar".equals(type)){
//         CTBarChart ctBarChart = ctPlotArea.addNewBarChart();
//         CTBoolean ctBoolean=ctBarChart.addNewVaryColors();
//         ctBarChart.getVaryColors().setVal(true);
//
//         //设置类型
//         ctBarChart.addNewGrouping().setVal(group);
//         ctBoolean.setVal(true);
//         ctBarChart.addNewBarDir().setVal(STBarDir.COL);
//
//         //是否添加左侧坐标轴
//         ctChart.addNewDispBlanksAs().setVal(STDispBlanksAs.ZERO);
//         ctChart.addNewShowDLblsOverMax().setVal(true);
//
//         //设置这两个参数是为了在STACKED模式下生成堆积模式；(standard)标准模式时需要将这两行去掉
//         if("stacked".equals(group.toString())||"percentStacked".equals(group.toString())){
//         	 ctBarChart.addNewGapWidth().setVal(150);
//              ctBarChart.addNewOverlap().setVal((byte)100);
//         }
//
//         //创建序列,并且设置选中区域
//         for (int i = 0; i < fldNameArr.size()-1; i++) {
//         	CTBarSer ctBarSer = ctBarChart.addNewSer();
//         	CTSerTx ctSerTx = ctBarSer.addNewTx();
//         	//图例区
//         	CTStrRef ctStrRef = ctSerTx.addNewStrRef();
//         	String legendDataRange = new CellRangeAddress(curRow,curRow, i+1, i+1).formatAsString(sheetName, true);
//         	ctStrRef.setF(legendDataRange);
//             ctBarSer.addNewIdx().setVal(i);
//
//             //横坐标区
//             CTAxDataSource cttAxDataSource = ctBarSer.addNewCat();
//             ctStrRef = cttAxDataSource.addNewStrRef();
// 	       	String axisDataRange = new CellRangeAddress(curRow+1, curRow+dataList.size(), 0, 0)
// 			.formatAsString(sheetName, true);
//             ctStrRef.setF(axisDataRange);
//
//             //数据区域
//             CTNumDataSource ctNumDataSource = ctBarSer.addNewVal();
//             CTNumRef ctNumRef = ctNumDataSource.addNewNumRef();
//         	String numDataRange = new CellRangeAddress(curRow+1, curRow+dataList.size(), i+1, i+1)
// 			.formatAsString(sheetName, true);
//             ctNumRef.setF(numDataRange);
//
//             ctBarSer.addNewSpPr().addNewLn().addNewSolidFill().addNewSrgbClr().setVal(new byte[] {0,0,0});
//
//             //设置负轴颜色不是白色
//             ctBarSer.addNewInvertIfNegative().setVal(false);
//             //设置标签格式
//             ctBoolean.setVal(false);
//             CTDLbls newDLbls = ctBarSer.addNewDLbls();
//             newDLbls.setShowLegendKey(ctBoolean);
//             ctBoolean.setVal(true);
//             newDLbls.setShowVal(ctBoolean);
//             ctBoolean.setVal(false);
//             newDLbls.setShowCatName(ctBoolean);
//             newDLbls.setShowSerName(ctBoolean);
//             newDLbls.setShowPercent(ctBoolean);
//             newDLbls.setShowBubbleSize(ctBoolean);
//             newDLbls.setShowLeaderLines(ctBoolean);
//         }
//
//         //telling the BarChart that it has axes and giving them Ids
//         ctBarChart.addNewAxId().setVal(123456);
//         ctBarChart.addNewAxId().setVal(123457);
//
//         //cat axis
//         CTCatAx ctCatAx = ctPlotArea.addNewCatAx();
//         ctCatAx.addNewAxId().setVal(123456); //id of the cat axis
//         CTScaling ctScaling = ctCatAx.addNewScaling();
//         ctScaling.addNewOrientation().setVal(STOrientation.MIN_MAX);
//         ctCatAx.addNewAxPos().setVal(STAxPos.B);
//         ctCatAx.addNewCrossAx().setVal(123457); //id of the val axis
//         ctCatAx.addNewTickLblPos().setVal(STTickLblPos.NEXT_TO);
//
//         //val axis
//         CTValAx ctValAx = ctPlotArea.addNewValAx();
//         ctValAx.addNewAxId().setVal(123457); //id of the val axis
//         ctScaling = ctValAx.addNewScaling();
//         ctScaling.addNewOrientation().setVal(STOrientation.MIN_MAX);
//         //设置位置
//         ctValAx.addNewAxPos().setVal(STAxPos.L);
//         ctValAx.addNewCrossAx().setVal(123456); //id of the cat axis
//         ctValAx.addNewTickLblPos().setVal(STTickLblPos.NEXT_TO);
//
//         //是否删除主左边轴
//         if(isvalAxis){
//         	ctValAx.addNewDelete().setVal(false);
//         }else{
//         	ctValAx.addNewDelete().setVal(true);
//         }
//
//         //是否删除横坐标
//         ctCatAx.addNewDelete().setVal(false);
//
//         //legend图注
//         if(isLegend){
//             CTLegend ctLegend = ctChart.addNewLegend();
//             ctLegend.addNewLegendPos().setVal(STLegendPos.B);
//             ctLegend.addNewOverlay().setVal(false);
//         }
//    }else if("line".equals(type)){
//    	CTLineChart ctLineChart = ctPlotArea.addNewLineChart();
//        CTBoolean ctBoolean = ctLineChart.addNewVaryColors();
//        ctLineChart.addNewGrouping().setVal(STGrouping.STANDARD);
//
//        //创建序列,并且设置选中区域
//        for (int i = 0; i < fldNameArr.size()-1; i++) {
//        	CTLineSer ctLineSer = ctLineChart.addNewSer();
//        	CTSerTx ctSerTx = ctLineSer.addNewTx();
//        	//图例区
//        	CTStrRef ctStrRef = ctSerTx.addNewStrRef();
//        	String legendDataRange = new CellRangeAddress(curRow,curRow, i+1, i+1).formatAsString(sheetName, true);
//         	ctStrRef.setF(legendDataRange);
//        	ctStrRef.setF(legendDataRange);
//            ctLineSer.addNewIdx().setVal(i);
//
//            //横坐标区
//            CTAxDataSource cttAxDataSource = ctLineSer.addNewCat();
//            ctStrRef = cttAxDataSource.addNewStrRef();
//        	String axisDataRange = new CellRangeAddress(curRow+1, curRow+dataList.size(), 0, 0)
// 			.formatAsString(sheetName, true);
//            ctStrRef.setF(axisDataRange);
//
//            //数据区域
//            CTNumDataSource ctNumDataSource = ctLineSer.addNewVal();
//            CTNumRef ctNumRef = ctNumDataSource.addNewNumRef();
//            String numDataRange = new CellRangeAddress(curRow+1, curRow+dataList.size(), i+1, i+1)
// 			.formatAsString(sheetName, true);
//            ctNumRef.setF(numDataRange);
//
//            //设置标签格式
//            ctBoolean.setVal(false);
//            CTDLbls newDLbls = ctLineSer.addNewDLbls();
//            newDLbls.setShowLegendKey(ctBoolean);
//            ctBoolean.setVal(true);
//            newDLbls.setShowVal(ctBoolean);
//            ctBoolean.setVal(false);
//            newDLbls.setShowCatName(ctBoolean);
//            newDLbls.setShowSerName(ctBoolean);
//            newDLbls.setShowPercent(ctBoolean);
//            newDLbls.setShowBubbleSize(ctBoolean);
//            newDLbls.setShowLeaderLines(ctBoolean);
//
//            //是否是平滑曲线
//            CTBoolean addNewSmooth = ctLineSer.addNewSmooth();
//            addNewSmooth.setVal(false);
//
//            //是否是堆积曲线
//            CTMarker addNewMarker = ctLineSer.addNewMarker();
//            CTMarkerStyle addNewSymbol = addNewMarker.addNewSymbol();
//            addNewSymbol.setVal(STMarkerStyle.NONE);
//        }
//        //telling the BarChart that it has axes and giving them Ids
//        ctLineChart.addNewAxId().setVal(123456);
//        ctLineChart.addNewAxId().setVal(123457);
//
//        //cat axis
//        CTCatAx ctCatAx = ctPlotArea.addNewCatAx();
//        ctCatAx.addNewAxId().setVal(123456); //id of the cat axis
//        CTScaling ctScaling = ctCatAx.addNewScaling();
//        ctScaling.addNewOrientation().setVal(STOrientation.MIN_MAX);
//        ctCatAx.addNewAxPos().setVal(STAxPos.B);
//        ctCatAx.addNewCrossAx().setVal(123457); //id of the val axis
//        ctCatAx.addNewTickLblPos().setVal(STTickLblPos.NEXT_TO);
//
//        //val axis
//        CTValAx ctValAx = ctPlotArea.addNewValAx();
//        ctValAx.addNewAxId().setVal(123457); //id of the val axis
//        ctScaling = ctValAx.addNewScaling();
//        ctScaling.addNewOrientation().setVal(STOrientation.MIN_MAX);
//        ctValAx.addNewAxPos().setVal(STAxPos.L);
//        ctValAx.addNewCrossAx().setVal(123456); //id of the cat axis
//        ctValAx.addNewTickLblPos().setVal(STTickLblPos.NEXT_TO);
//
//        //是否删除主左边轴
//        if(isvalAxis){
//        	ctValAx.addNewDelete().setVal(false);
//        }else{
//        	ctValAx.addNewDelete().setVal(true);
//        }
//
//        //是否删除横坐标
//        ctCatAx.addNewDelete().setVal(false);
//
//        //legend图注
//        if(isLegend){
//            CTLegend ctLegend = ctChart.addNewLegend();
//            ctLegend.addNewLegendPos().setVal(STLegendPos.B);
//            ctLegend.addNewOverlay().setVal(false);
//        }
//    }else if("bar-line-2".equals(type)){
//        CTBarChart ctBarChart = ctPlotArea.addNewBarChart();
//        CTBoolean ctBoolean=ctBarChart.addNewVaryColors();
//        ctBarChart.getVaryColors().setVal(true);
//
//        //设置类型
//        ctBarChart.addNewGrouping().setVal(STBarGrouping.CLUSTERED);
//        ctBoolean.setVal(true);
//        ctBarChart.addNewBarDir().setVal(STBarDir.COL);
//
//        //是否添加左侧坐标轴
//        ctChart.addNewDispBlanksAs().setVal(STDispBlanksAs.ZERO);
//        ctChart.addNewShowDLblsOverMax().setVal(true);
//
//        //创建序列,并且设置选中区域
//        for (int i = 0; i < fldNameArr.size()-2; i++) {
//        	CTBarSer ctBarSer = ctBarChart.addNewSer();
//        	CTSerTx ctSerTx = ctBarSer.addNewTx();
//        	//图例区
//        	CTStrRef ctStrRef = ctSerTx.addNewStrRef();
//        	String legendDataRange = new CellRangeAddress(curRow,curRow, i+1, i+1).formatAsString(sheetName, true);
//        	ctStrRef.setF(legendDataRange);
//            ctBarSer.addNewIdx().setVal(i);
//
//            //横坐标区
//            CTAxDataSource cttAxDataSource = ctBarSer.addNewCat();
//            ctStrRef = cttAxDataSource.addNewStrRef();
//	       	String axisDataRange = new CellRangeAddress(curRow+1, curRow+dataList.size(), 0, 0)
//			.formatAsString(sheetName, true);
//            ctStrRef.setF(axisDataRange);
//
//            //数据区域
//            CTNumDataSource ctNumDataSource = ctBarSer.addNewVal();
//            CTNumRef ctNumRef = ctNumDataSource.addNewNumRef();
//        	String numDataRange = new CellRangeAddress(curRow+1, curRow+dataList.size(), i+1, i+1)
//			.formatAsString(sheetName, true);
//            ctNumRef.setF(numDataRange);
//
//            ctBarSer.addNewSpPr().addNewLn().addNewSolidFill().addNewSrgbClr().setVal(new byte[] {0,0,0});
//            //设置负轴颜色不是白色
//            ctBarSer.addNewInvertIfNegative().setVal(false);
//            //设置标签格式
//            ctBoolean.setVal(false);
//            CTDLbls newDLbls = ctBarSer.addNewDLbls();
//            newDLbls.setShowLegendKey(ctBoolean);
//            ctBoolean.setVal(true);
//            newDLbls.setShowVal(ctBoolean);
//            ctBoolean.setVal(false);
//            newDLbls.setShowCatName(ctBoolean);
//            newDLbls.setShowSerName(ctBoolean);
//            newDLbls.setShowPercent(ctBoolean);
//            newDLbls.setShowBubbleSize(ctBoolean);
//            newDLbls.setShowLeaderLines(ctBoolean);
//        }
//
//        //telling the BarChart that it has axes and giving them Ids
//        ctBarChart.addNewAxId().setVal(123456);
//        ctBarChart.addNewAxId().setVal(123457);
//
//        //telling the BarChart that it has axes and giving them Ids
//        ctBarChart.addNewAxId().setVal(123456);
//        ctBarChart.addNewAxId().setVal(123457);
//
//        //cat axis
//        CTCatAx ctCatAx = ctPlotArea.addNewCatAx();
//        ctCatAx.addNewAxId().setVal(123456); //id of the cat axis
//        CTScaling ctScaling = ctCatAx.addNewScaling();
//        ctScaling.addNewOrientation().setVal(STOrientation.MIN_MAX);
//        ctCatAx.addNewAxPos().setVal(STAxPos.B);
//        ctCatAx.addNewCrossAx().setVal(123457); //id of the val axis
//        ctCatAx.addNewTickLblPos().setVal(STTickLblPos.NEXT_TO);
//
//        //val axis
//        CTValAx ctValAx = ctPlotArea.addNewValAx();
//        ctValAx.addNewAxId().setVal(123457); //id of the val axis
//        ctScaling = ctValAx.addNewScaling();
//        ctScaling.addNewOrientation().setVal(STOrientation.MIN_MAX);
//        ctValAx.addNewAxPos().setVal(STAxPos.L);
//        ctValAx.addNewCrossAx().setVal(123456); //id of the cat axis
//        ctValAx.addNewTickLblPos().setVal(STTickLblPos.NEXT_TO);
//
//        //是否删除主左边轴
//        if(isvalAxis){
//        	ctValAx.addNewDelete().setVal(false);
//        }else{
//        	ctValAx.addNewDelete().setVal(true);
//        }
//
//        //是否删除横坐标
//        ctCatAx.addNewDelete().setVal(false);
//
//        //legend图注
//        if(isLegend){
//            CTLegend ctLegend = ctChart.addNewLegend();
//            ctLegend.addNewLegendPos().setVal(STLegendPos.B);
//            ctLegend.addNewOverlay().setVal(false);
//        }
//
//
//        CTLineChart ctLineChart = ctPlotArea.addNewLineChart();
//        ctLineChart.addNewGrouping().setVal(STGrouping.STANDARD);
//
//        //创建序列,并且设置选中区域
//        for (int i =1; i < fldNameArr.size()-1; i++) {
//        	CTLineSer ctLineSer = ctLineChart.addNewSer();
//        	CTSerTx ctSerTx = ctLineSer.addNewTx();
//        	//图例区
//        	CTStrRef ctStrRef = ctSerTx.addNewStrRef();
//        	String legendDataRange = new CellRangeAddress(curRow,curRow, i+1, i+1).formatAsString(sheetName, true);
//         	ctStrRef.setF(legendDataRange);
//        	ctStrRef.setF(legendDataRange);
//            ctLineSer.addNewIdx().setVal(i);
//
//            //横坐标区
//            CTAxDataSource cttAxDataSource = ctLineSer.addNewCat();
//            ctStrRef = cttAxDataSource.addNewStrRef();
//        	String axisDataRange = new CellRangeAddress(curRow+1, curRow+dataList.size(), 0, 0)
// 			.formatAsString(sheetName, true);
//            ctStrRef.setF(axisDataRange);
//
//            //数据区域
//            CTNumDataSource ctNumDataSource = ctLineSer.addNewVal();
//            CTNumRef ctNumRef = ctNumDataSource.addNewNumRef();
//            String numDataRange = new CellRangeAddress(curRow+1, curRow+dataList.size(), i+1, i+1)
// 			.formatAsString(sheetName, true);
//            ctNumRef.setF(numDataRange);
//
//            //是否是平滑曲线
//            CTBoolean addNewSmooth = ctLineSer.addNewSmooth();
//            addNewSmooth.setVal(false);
//
//            //是否是堆积曲线
//            CTMarker addNewMarker = ctLineSer.addNewMarker();
//            CTMarkerStyle addNewSymbol = addNewMarker.addNewSymbol();
//            addNewSymbol.setVal(STMarkerStyle.NONE);
//        }
//
//        //telling the BarChart that it has axes and giving them Ids
//        //TODO:写死是否有影响？
//        ctLineChart.addNewAxId().setVal(1234567);
//        ctLineChart.addNewAxId().setVal(1234578);
//
//        //cat axis
//        CTCatAx ctCatAxline = ctPlotArea.addNewCatAx();
//        ctCatAxline.addNewAxId().setVal(1234567); //id of the cat axis
//        CTScaling ctScalingline = ctCatAxline.addNewScaling();
//        ctScalingline.addNewOrientation().setVal(STOrientation.MIN_MAX);
//        ctCatAxline.addNewDelete().setVal(true);
//        ctCatAxline.addNewAxPos().setVal(STAxPos.B);
//        ctCatAxline.addNewMajorTickMark().setVal(STTickMark.OUT);
//        ctCatAxline.addNewMinorTickMark().setVal(STTickMark.NONE);
//        ctCatAxline.addNewAuto().setVal(true);
//        ctCatAxline.addNewLblAlgn().setVal(STLblAlgn.CTR);
//        ctCatAxline.addNewLblOffset().setVal(100);
//        ctCatAxline.addNewNoMultiLvlLbl().setVal(false);
//        ctCatAxline.addNewCrossAx().setVal(1234578); //id of the val axis
//        ctCatAxline.addNewTickLblPos().setVal(STTickLblPos.NEXT_TO);
//
//        //val axis
//        CTValAx ctValAxline = ctPlotArea.addNewValAx();
//        ctValAxline.addNewAxId().setVal(1234578); //id of the val axis
//        ctScalingline = ctValAxline.addNewScaling();
//        ctScalingline.addNewOrientation().setVal(STOrientation.MIN_MAX);
//        //Y轴右侧坐标true删除，false保留
//        ctValAxline.addNewDelete().setVal(false);
//        ctValAxline.addNewAxPos().setVal(STAxPos.R);
//        ctValAxline.addNewMajorTickMark().setVal(STTickMark.OUT);
//        ctValAxline.addNewMinorTickMark().setVal(STTickMark.NONE);
//        ctValAxline.addNewCrosses().setVal(STCrosses.MAX);
//        ctValAxline.addNewCrossBetween().setVal(STCrossBetween.BETWEEN);
//        ctValAxline.addNewCrossAx().setVal(1234567); //id of the cat axis
//        ctValAxline.addNewTickLblPos().setVal(STTickLblPos.NEXT_TO);
//   }else if("bar-line-4".equals(type)){
//        CTBarChart ctBarChart = ctPlotArea.addNewBarChart();
//        CTBoolean ctBoolean=ctBarChart.addNewVaryColors();
//        ctBarChart.getVaryColors().setVal(true);
//
//        //设置类型
//        ctBarChart.addNewGrouping().setVal(STBarGrouping.CLUSTERED);
//        ctBoolean.setVal(true);
//        ctBarChart.addNewBarDir().setVal(STBarDir.COL);
//
//        //是否添加左侧坐标轴
//        ctChart.addNewDispBlanksAs().setVal(STDispBlanksAs.ZERO);
//        ctChart.addNewShowDLblsOverMax().setVal(true);
//
//        //创建序列,并且设置选中区域
//        for (int i = 0; i < fldNameArr.size()-1-2; i++) {
//        	CTBarSer ctBarSer = ctBarChart.addNewSer();
//        	CTSerTx ctSerTx = ctBarSer.addNewTx();
//        	//图例区
//        	CTStrRef ctStrRef = ctSerTx.addNewStrRef();
//        	String legendDataRange = new CellRangeAddress(curRow,curRow, i+1, i+1).formatAsString(sheetName, true);
//        	ctStrRef.setF(legendDataRange);
//            ctBarSer.addNewIdx().setVal(i);
//
//            //横坐标区
//            CTAxDataSource cttAxDataSource = ctBarSer.addNewCat();
//            ctStrRef = cttAxDataSource.addNewStrRef();
//	       	String axisDataRange = new CellRangeAddress(curRow+1, curRow+dataList.size(), 0, 0)
//			.formatAsString(sheetName, true);
//            ctStrRef.setF(axisDataRange);
//
//            //数据区域
//            CTNumDataSource ctNumDataSource = ctBarSer.addNewVal();
//            CTNumRef ctNumRef = ctNumDataSource.addNewNumRef();
//        	String numDataRange = new CellRangeAddress(curRow+1, curRow+dataList.size(), i+1, i+1)
//			.formatAsString(sheetName, true);
//            ctNumRef.setF(numDataRange);
//
//            ctBarSer.addNewSpPr().addNewLn().addNewSolidFill().addNewSrgbClr().setVal(new byte[] {0,0,0});
//            //设置负轴颜色不是白色
//            ctBarSer.addNewInvertIfNegative().setVal(false);
//            //设置标签格式
//            ctBoolean.setVal(false);
//            CTDLbls newDLbls = ctBarSer.addNewDLbls();
//            newDLbls.setShowLegendKey(ctBoolean);
//            ctBoolean.setVal(true);
//            newDLbls.setShowVal(ctBoolean);
//            ctBoolean.setVal(false);
//            newDLbls.setShowCatName(ctBoolean);
//            newDLbls.setShowSerName(ctBoolean);
//            newDLbls.setShowPercent(ctBoolean);
//            newDLbls.setShowBubbleSize(ctBoolean);
//            newDLbls.setShowLeaderLines(ctBoolean);
//        }
//
//        //telling the BarChart that it has axes and giving them Ids
//        ctBarChart.addNewAxId().setVal(123456);
//        ctBarChart.addNewAxId().setVal(123457);
//
//        //telling the BarChart that it has axes and giving them Ids
//        ctBarChart.addNewAxId().setVal(123456);
//        ctBarChart.addNewAxId().setVal(123457);
//
//        //cat axis
//        CTCatAx ctCatAx = ctPlotArea.addNewCatAx();
//        ctCatAx.addNewAxId().setVal(123456); //id of the cat axis
//        CTScaling ctScaling = ctCatAx.addNewScaling();
//        ctScaling.addNewOrientation().setVal(STOrientation.MIN_MAX);
//        ctCatAx.addNewAxPos().setVal(STAxPos.B);
//        ctCatAx.addNewCrossAx().setVal(123457); //id of the val axis
//        ctCatAx.addNewTickLblPos().setVal(STTickLblPos.NEXT_TO);
//
//        //val axis
//        CTValAx ctValAx = ctPlotArea.addNewValAx();
//        ctValAx.addNewAxId().setVal(123457); //id of the val axis
//        ctScaling = ctValAx.addNewScaling();
//        ctScaling.addNewOrientation().setVal(STOrientation.MIN_MAX);
//        ctValAx.addNewAxPos().setVal(STAxPos.L);
//        ctValAx.addNewCrossAx().setVal(123456); //id of the cat axis
//        ctValAx.addNewTickLblPos().setVal(STTickLblPos.NEXT_TO);
//
//        //是否删除主左边轴
//        if(isvalAxis){
//        	ctValAx.addNewDelete().setVal(false);
//        }else{
//        	ctValAx.addNewDelete().setVal(true);
//        }
//
//        //是否删除横坐标
//        ctCatAx.addNewDelete().setVal(false);
//
//        //legend图注
//        if(isLegend){
//            CTLegend ctLegend = ctChart.addNewLegend();
//            ctLegend.addNewLegendPos().setVal(STLegendPos.B);
//            ctLegend.addNewOverlay().setVal(false);
//        }
//
//
//        CTLineChart ctLineChart = ctPlotArea.addNewLineChart();
//        ctLineChart.addNewGrouping().setVal(STGrouping.STANDARD);
//
//        //创建序列,并且设置选中区域
//        for (int i = 2; i < fldNameArr.size()-1; i++) {
//        	CTLineSer ctLineSer = ctLineChart.addNewSer();
//        	CTSerTx ctSerTx = ctLineSer.addNewTx();
//        	//图例区
//        	CTStrRef ctStrRef = ctSerTx.addNewStrRef();
//        	String legendDataRange = new CellRangeAddress(curRow,curRow, i+1, i+1).formatAsString(sheetName, true);
//         	ctStrRef.setF(legendDataRange);
//        	ctStrRef.setF(legendDataRange);
//            ctLineSer.addNewIdx().setVal(i);
//
//            //横坐标区
//            CTAxDataSource cttAxDataSource = ctLineSer.addNewCat();
//            ctStrRef = cttAxDataSource.addNewStrRef();
//        	String axisDataRange = new CellRangeAddress(curRow+1, curRow+dataList.size(), 0, 0)
// 			.formatAsString(sheetName, true);
//            ctStrRef.setF(axisDataRange);
//
//            //数据区域
//            CTNumDataSource ctNumDataSource = ctLineSer.addNewVal();
//            CTNumRef ctNumRef = ctNumDataSource.addNewNumRef();
//            String numDataRange = new CellRangeAddress(curRow+1, curRow+dataList.size(), i+1, i+1)
// 			.formatAsString(sheetName, true);
//            ctNumRef.setF(numDataRange);
//
//            //是否是平滑曲线
//            CTBoolean addNewSmooth = ctLineSer.addNewSmooth();
//            addNewSmooth.setVal(false);
//
//            //是否是堆积曲线
//            CTMarker addNewMarker = ctLineSer.addNewMarker();
//            CTMarkerStyle addNewSymbol = addNewMarker.addNewSymbol();
//            addNewSymbol.setVal(STMarkerStyle.NONE);
//        }
//
//        //telling the BarChart that it has axes and giving them Ids
//        //TODO:写死是否有影响？
//        ctLineChart.addNewAxId().setVal(1234567);
//        ctLineChart.addNewAxId().setVal(1234578);
//
//        //cat axis
//        CTCatAx ctCatAxline = ctPlotArea.addNewCatAx();
//        ctCatAxline.addNewAxId().setVal(1234567); //id of the cat axis
//        CTScaling ctScalingline = ctCatAxline.addNewScaling();
//        ctScalingline.addNewOrientation().setVal(STOrientation.MIN_MAX);
//        ctCatAxline.addNewDelete().setVal(true);
//        ctCatAxline.addNewAxPos().setVal(STAxPos.B);
//        ctCatAxline.addNewMajorTickMark().setVal(STTickMark.OUT);
//        ctCatAxline.addNewMinorTickMark().setVal(STTickMark.NONE);
//        ctCatAxline.addNewAuto().setVal(true);
//        ctCatAxline.addNewLblAlgn().setVal(STLblAlgn.CTR);
//        ctCatAxline.addNewLblOffset().setVal(100);
//        ctCatAxline.addNewNoMultiLvlLbl().setVal(false);
//        ctCatAxline.addNewCrossAx().setVal(1234578); //id of the val axis
//        ctCatAxline.addNewTickLblPos().setVal(STTickLblPos.NEXT_TO);
//
//        //val axis
//        CTValAx ctValAxline = ctPlotArea.addNewValAx();
//        ctValAxline.addNewAxId().setVal(1234578); //id of the val axis
//        ctScalingline = ctValAxline.addNewScaling();
//        ctScalingline.addNewOrientation().setVal(STOrientation.MIN_MAX);
//        ctValAxline.addNewDelete().setVal(false);
//        ctValAxline.addNewAxPos().setVal(STAxPos.R);
//        ctValAxline.addNewMajorTickMark().setVal(STTickMark.OUT);
//        ctValAxline.addNewMinorTickMark().setVal(STTickMark.NONE);
//        ctValAxline.addNewCrosses().setVal(STCrosses.MAX);
//        ctValAxline.addNewCrossBetween().setVal(STCrossBetween.BETWEEN);
//        ctValAxline.addNewCrossAx().setVal(1234567); //id of the cat axis
//        ctValAxline.addNewTickLblPos().setVal(STTickLblPos.NEXT_TO);
//   }else if("pie".equals(type)){//pie
//	   CTPieChart ctPieChart = ctPlotArea.addNewPieChart();
//       CTBoolean ctBoolean = ctPieChart.addNewVaryColors();
//
//       //创建序列,并且设置选中区域
//       for (int i = 0; i < fldNameArr.size()-1; i++) {
//       	CTPieSer ctPieSer = ctPieChart.addNewSer();
//       	CTSerTx ctSerTx = ctPieSer.addNewTx();
//       	//图例区
//       	CTStrRef ctStrRef = ctSerTx.addNewStrRef();
//       	String legendDataRange = new CellRangeAddress(curRow,curRow, i+1, i+1).formatAsString(sheetName, true);
//       	ctStrRef.setF(legendDataRange);
//           ctPieSer.addNewIdx().setVal(i);
//
//           //横坐标区
//           CTAxDataSource cttAxDataSource = ctPieSer.addNewCat();
//           ctStrRef = cttAxDataSource.addNewStrRef();
//           String axisDataRange = new CellRangeAddress(curRow+1, curRow+dataList.size(), 0, 0)
//		.formatAsString(sheetName, true);
//           ctStrRef.setF(axisDataRange);
//
//           //数据区域
//           CTNumDataSource ctNumDataSource = ctPieSer.addNewVal();
//           CTNumRef ctNumRef = ctNumDataSource.addNewNumRef();
//           String numDataRange = new CellRangeAddress(curRow+1, curRow+dataList.size(), i+1, i+1)
//		.formatAsString(sheetName, true);
//           ctNumRef.setF(numDataRange);
//
//           ctPieSer.addNewSpPr().addNewLn().addNewSolidFill().addNewSrgbClr().setVal(new byte[] {0,0,0});
//
//           //设置标签格式
//           ctBoolean.setVal(true);
//       }
//       //legend图注
//       CTLegend ctLegend = ctChart.addNewLegend();
//       ctLegend.addNewLegendPos().setVal(STLegendPos.B);
//       ctLegend.addNewOverlay().setVal(true);
//   }else if("pie3D".equals(type)){//pie3D
//	   CTPie3DChart ctPie3DChart = ctPlotArea.addNewPie3DChart();
//       CTBoolean ctBoolean = ctPie3DChart.addNewVaryColors();
//
//       //创建序列,并且设置选中区域
//       for (int i = 0; i < fldNameArr.size()-1; i++) {
//       	CTPieSer ctPieSer = ctPie3DChart.addNewSer();
//       	CTSerTx ctSerTx = ctPieSer.addNewTx();
//       	//图例区
//       	CTStrRef ctStrRef = ctSerTx.addNewStrRef();
//       	String legendDataRange = new CellRangeAddress(curRow,curRow, i+1, i+1).formatAsString(sheetName, true);
//       	ctStrRef.setF(legendDataRange);
//           ctPieSer.addNewIdx().setVal(i);
//
//           //横坐标区
//           CTAxDataSource cttAxDataSource = ctPieSer.addNewCat();
//           ctStrRef = cttAxDataSource.addNewStrRef();
//           String axisDataRange = new CellRangeAddress(curRow+1, curRow+dataList.size(), 0, 0)
//		.formatAsString(sheetName, true);
//           ctStrRef.setF(axisDataRange);
//
//           //数据区域
//           CTNumDataSource ctNumDataSource = ctPieSer.addNewVal();
//           CTNumRef ctNumRef = ctNumDataSource.addNewNumRef();
//           String numDataRange = new CellRangeAddress(curRow+1, curRow+dataList.size(), i+1, i+1)
//		.formatAsString(sheetName, true);
//           ctNumRef.setF(numDataRange);
//
//           ctPieSer.addNewSpPr().addNewLn().addNewSolidFill().addNewSrgbClr().setVal(new byte[] {0,0,0});
//
//           //设置标签格式
//           ctBoolean.setVal(true);
//       }
//       //legend图注
//       CTLegend ctLegend = ctChart.addNewLegend();
//       ctLegend.addNewLegendPos().setVal(STLegendPos.B);
//       ctLegend.addNewOverlay().setVal(true);
//   }else{//area
//	   CTAreaChart ctAreaChart = ctPlotArea.addNewAreaChart();
//       CTBoolean ctBoolean = ctAreaChart.addNewVaryColors();
//       ctAreaChart.addNewGrouping().setVal(STGrouping.STANDARD);
//
//       //创建序列,并且设置选中区域
//       for (int i = 0; i < fldNameArr.size()-1; i++) {
//       	CTAreaSer ctAreaSer = ctAreaChart.addNewSer();
//       	CTSerTx ctSerTx = ctAreaSer.addNewTx();
//       	//图例区
//       	CTStrRef ctStrRef = ctSerTx.addNewStrRef();
//       	String legendDataRange = new CellRangeAddress(curRow,curRow, i+1, i+1).formatAsString(sheetName, true);
//        	ctStrRef.setF(legendDataRange);
//       	ctStrRef.setF(legendDataRange);
//           ctAreaSer.addNewIdx().setVal(i);
//
//           //横坐标区
//           CTAxDataSource cttAxDataSource = ctAreaSer.addNewCat();
//           ctStrRef = cttAxDataSource.addNewStrRef();
//       	String axisDataRange = new CellRangeAddress(curRow+1, curRow+dataList.size(), 0, 0)
//			.formatAsString(sheetName, true);
//           ctStrRef.setF(axisDataRange);
//
//           //数据区域
//           CTNumDataSource ctNumDataSource = ctAreaSer.addNewVal();
//           CTNumRef ctNumRef = ctNumDataSource.addNewNumRef();
//           String numDataRange = new CellRangeAddress(curRow+1, curRow+dataList.size(), i+1, i+1)
//			.formatAsString(sheetName, true);
//           ctNumRef.setF(numDataRange);
//
//           //设置标签格式
//           ctBoolean.setVal(false);
//           CTDLbls newDLbls = ctAreaSer.addNewDLbls();
//           newDLbls.setShowLegendKey(ctBoolean);
//           ctBoolean.setVal(true);
//           newDLbls.setShowVal(ctBoolean);
//           ctBoolean.setVal(false);
//           newDLbls.setShowCatName(ctBoolean);
//           newDLbls.setShowSerName(ctBoolean);
//           newDLbls.setShowPercent(ctBoolean);
//           newDLbls.setShowBubbleSize(ctBoolean);
//           newDLbls.setShowLeaderLines(ctBoolean);
//
//      /*     //是否是平滑曲线
//           CTBoolean addNewSmooth = ctAreaSer.addNewSmooth();
//           addNewSmooth.setVal(false);
//
//           //是否是堆积曲线
//           CTMarker addNewMarker = ctAreaSer.addNewMarker();
//           CTMarkerStyle addNewSymbol = addNewMarker.addNewSymbol();
//           addNewSymbol.setVal(STMarkerStyle.NONE);*/
//       }
//       //telling the BarChart that it has axes and giving them Ids
//       ctAreaChart.addNewAxId().setVal(123456);
//       ctAreaChart.addNewAxId().setVal(123457);
//
//       //cat axis
//       CTCatAx ctCatAx = ctPlotArea.addNewCatAx();
//       ctCatAx.addNewAxId().setVal(123456); //id of the cat axis
//       CTScaling ctScaling = ctCatAx.addNewScaling();
//       ctScaling.addNewOrientation().setVal(STOrientation.MIN_MAX);
//       ctCatAx.addNewAxPos().setVal(STAxPos.B);
//       ctCatAx.addNewCrossAx().setVal(123457); //id of the val axis
//       ctCatAx.addNewTickLblPos().setVal(STTickLblPos.NEXT_TO);
//
//       //val axis
//       CTValAx ctValAx = ctPlotArea.addNewValAx();
//       ctValAx.addNewAxId().setVal(123457); //id of the val axis
//       ctScaling = ctValAx.addNewScaling();
//       ctScaling.addNewOrientation().setVal(STOrientation.MIN_MAX);
//       ctValAx.addNewAxPos().setVal(STAxPos.L);
//       ctValAx.addNewCrossAx().setVal(123456); //id of the cat axis
//       ctValAx.addNewTickLblPos().setVal(STTickLblPos.NEXT_TO);
//
//       //是否删除主左边轴
//       if(isvalAxis){
//       	ctValAx.addNewDelete().setVal(false);
//       }else{
//       	ctValAx.addNewDelete().setVal(true);
//       }
//
//       //是否删除横坐标
//       ctCatAx.addNewDelete().setVal(false);
//
//       //legend图注
//       if(isLegend){
//           CTLegend ctLegend = ctChart.addNewLegend();
//           ctLegend.addNewLegendPos().setVal(STLegendPos.B);
//           ctLegend.addNewOverlay().setVal(false);
//       }
//
//
//   }
//	return result;
//}
//1
//2
//3
//4
//5
//6
//7
//8
//9
//10
//11
//12
//13
//14
//15
//16
//17
//18
//19
//20
//21
//22
//23
//24
//25
//26
//27
//28
//29
//30
//31
//32
//33
//34
//35
//36
//37
//38
//39
//40
//41
//42
//43
//44
//45
//46
//47
//48
//49
//50
//51
//52
//53
//54
//55
//56
//57
//58
//59
//60
//61
//62
//63
//64
//65
//66
//67
//68
//69
//70
//71
//72
//73
//74
//75
//76
//77
//78
//79
//80
//81
//82
//83
//84
//85
//86
//87
//88
//89
//90
//91
//92
//93
//94
//95
//96
//97
//98
//99
//100
//101
//102
//103
//104
//105
//106
//107
//108
//109
//110
//111
//112
//113
//114
//115
//116
//117
//118
//119
//120
//121
//122
//123
//124
//125
//126
//127
//128
//129
//130
//131
//132
//133
//134
//135
//136
//137
//138
//139
//140
//141
//142
//143
//144
//145
//146
//147
//148
//149
//150
//151
//152
//153
//154
//155
//156
//157
//158
//159
//160
//161
//162
//163
//164
//165
//166
//167
//168
//169
//170
//171
//172
//173
//174
//175
//176
//177
//178
//179
//180
//181
//182
//183
//184
//185
//186
//187
//188
//189
//190
//191
//192
//193
//194
//195
//196
//197
//198
//199
//200
//201
//202
//203
//204
//205
//206
//207
//208
//209
//210
//211
//212
//213
//214
//215
//216
//217
//218
//219
//220
//221
//222
//223
//224
//225
//226
//227
//228
//229
//230
//231
//232
//233
//234
//235
//236
//237
//238
//239
//240
//241
//242
//243
//244
//245
//246
//247
//248
//249
//250
//251
//252
//253
//254
//255
//256
//257
//258
//259
//260
//261
//262
//263
//264
//265
//266
//267
//268
//269
//270
//271
//272
//273
//274
//275
//276
//277
//278
//279
//280
//281
//282
//283
//284
//285
//286
//287
//288
//289
//290
//291
//292
//293
//294
//295
//296
//297
//298
//299
//300
//301
//302
//303
//304
//305
//306
//307
//308
//309
//310
//311
//312
//313
//314
//315
//316
//317
//318
//319
//320
//321
//322
//323
//324
//325
//326
//327
//328
//329
//330
//331
//332
//333
//334
//335
//336
//337
//338
//339
//340
//341
//342
//343
//344
//345
//346
//347
//348
//349
//350
//351
//352
//353
//354
//355
//356
//357
//358
//359
//360
//361
//362
//363
//364
//365
//366
//367
//368
//369
//370
//371
//372
//373
//374
//375
//376
//377
//378
//379
//380
//381
//382
//383
//384
//385
//386
//387
//388
//389
//390
//391
//392
//393
//394
//395
//396
//397
//398
//399
//400
//401
//402
//403
//404
//405
//406
//407
//408
//409
//410
//411
//412
//413
//414
//415
//416
//417
//418
//419
//420
//421
//422
//423
//424
//425
//426
//427
//428
//429
//430
//431
//432
//433
//434
//435
//436
//437
//438
//439
//440
//441
//442
//443
//444
//445
//446
//447
//448
//449
//450
//451
//452
//453
//454
//455
//456
//457
//458
//459
//460
//461
//462
//463
//464
//465
//466
//467
//468
//469
//470
//471
//472
//473
//474
//475
//476
//477
//478
//479
//480
//481
//482
//483
//484
//485
//486
//487
//488
//489
//490
//491
//492
//493
//494
//495
//496
//497
//498
//499
//500
//501
//502
//503
//504
//505
//506
//507
//508
//509
//510
//511
//512
//513
//514
//515
//516
//517
//518
//519
//520
//521
//522
//523
//524
//525
//526
//527
//528
//529
//530
//531
//532
//533
//534
//535
//536
//537
//538
//539
//540
//541
//542
//543
//544
//545
//546
//547
//548
//549
//550
//551
//552
//553
//554
//555
//556
//557
//558
//559
//560
//561
//562
//563
//564
//565
//566
//567
//568
//569
//570
//571
//572
//573
//574
//575
//576
//577
//578
//579
//580
//581
//582
//583
//584
//585
//586
//587
//588
//589
//590
//591
//592
//593
//594
//595
//596
//597
//598
//599
//600
//601
//602
//603
//604
//605
//606
//607
//608
//609
//610
//611
//612
//613
//614
//615
//616
//617
//618
//619
//620
//621
//622
//623
//624
//625
//626
//627
//628
//629
//630
//631
//632
//633
//634
//635
//636
//637
//638
//639
//640
//641
//642
//643
//644
//645
//646
//647
//648
//649
//650
//651
//652
//653
//654
//655
//656
//657
//658
//659
//660
//661
//662
//663
//664
//665
//666
//667
//668
//669
//670
//671
//672
//673
//674
//675
//676
//677
//678
//679
//680
//681
//682
//683
//684
//685
//686
//687
//688
//689
//690
//691
//692
//693
//694
//695
//696
//697
//698
//699
//700
//701
//702
//703
//704
//705
//706
//707
//708
//709
//710
//711
//712
//713
//714
//715
//716
//717
//718
//719
//720
//721
//722
//723
//724
//725
//726
//727
//728
//729
//730
//731
//732
//733
//734
//735
//736
//737
//738
//739
//740
//741
//742
//743
//744
//745
//746
//747
//748
//749
//750
//751
//752
//753
//754
//755
//756
//757
//758
//759
//760
//761
//762
//763
//764
//765
//766
//767
//768
//769
//770
//771
//772
//773
//774
//775
//776
//777
//778
//779
//780
//781
//782
//783
//784
//785
//786
//787
//788
//789
//790
//791
//792
//793
//794
//795
//796
//797
//798
//799
//800
//801
//802
//803
//804
//805
//806
//807
//808
//809
//810
//811
//812
//813
//814
//815
//816
//817
//818
//819
//820
//821
//822
//823
//824
//825
//826
//827
//828
//829
//830
//831
//832
//833
//834
//835
//836
//837
//838
//839
//840
//841
//842
//843
//844
//845
//846
//847
//848
//849
//850
//851
//852
//853
//854
//855
//856
//857
//858
//859
//860
//861
//862
//863
//864
//865
//866
//867
//868
//869
//870
//871
//872
//873
//874
//875
//876
//877
//878
//879
//880
//881
//882
//883
//884
//885
//886
//887
//888
//889
//890
//891
//892
//893
//894
//895
//896
//897
//898
//899
//900
//901
//902
//903
//904
//905
//906
//907
//908
//909
//910
//911
//912
//913
//914
//915
//916
//917
//918
//919
//920
//921
//922
//923
//924
//925
//926
//927
//928
//929
//930
//931
//932
//933
//934
//935
//936
//937
//938
//939
//940
//941
//942
//943
//944
//945
//946
//947
//948
//949
//950
//951
//952
//953
//954
//955
//956
//957
//958
//959
//}
//
//
//————————————————
//版权声明：本文为CSDN博主「wangxiaoyingWXY」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
//原文链接：https://blog.csdn.net/wangxiaoyingWXY/article/details/83218341