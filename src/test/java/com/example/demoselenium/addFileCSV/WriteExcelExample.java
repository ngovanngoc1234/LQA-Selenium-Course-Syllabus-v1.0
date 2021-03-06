package com.example.demoselenium.addFileCSV;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;

public class WriteExcelExample implements Serializable {
    public static final int COLUMN_INDEX_ID = 0;
    public static final int COLUMN_INDEX_EXAMPLE = 1;
    public static final int COLUMN_INDEX_FOOD = 2;
    public static final int COLUMN_INDEX_SIZE = 3;
    public static final int COLUMN_INDEX_QUANTITY = 4;
    public static final int COLUMN_INDEX_WEIGHT = 5;
    public static final int COLUMN_INDEX_TEMPERATURE = 6;
    public static final int COLUMN_INDEX_SMELL = 7;
    public static final int COLUMN_INDEX_CONSTITUTIVE = 8;
    public static final int COLUMN_INDEX_OFFER = 9;
    public static final int COLUMN_INDEX_PROMOTION = 10;
    public static final int COLUMN_INDEX_CARTS = 11;
    public static final int COLUMN_INDEX_OTHER = 12;
    public static final int COLUMN_INDEX_STATUS = 13;
    public static final int COLUMN_INDEX_WORKER_NAME = 14;

    private static CellStyle cellStyleFormatNumber = null;


    public void writeExcel(List<ReadCSV> readCSVS, String excelFilePath) throws IOException {
        // Create Workbook
        Workbook workbook = getWorkbook(excelFilePath);

        // Create sheet
        Sheet sheet = workbook.createSheet("sheep3"); // Create sheet with sheet name

        int rowIndex = 0;

        // Write header
        writeHeader(sheet, rowIndex);

        // Write data
        rowIndex++;
        for (ReadCSV ReadCSV : readCSVS) {
            // Create row
            Row row = sheet.createRow(rowIndex);
            // Write data on row
            writeBook(ReadCSV, row);
            rowIndex++;
        }

        // Write footer
//        writeFooter(sheet, rowIndex);

        // Auto resize column witdth
        int numberOfColumn = sheet.getRow(0).getPhysicalNumberOfCells();
        autosizeColumn(sheet, numberOfColumn);

        // Create file excel
        createOutputFile(workbook, excelFilePath);
        System.out.println("Done!!!");
    }

    // Create dummy data

    // Create workbook
    private static Workbook getWorkbook(String excelFilePath) throws IOException {
        Workbook workbook = null;

        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook();
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook();
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }

        return workbook;
    }

    // Write header with format
    private static void writeHeader(Sheet sheet, int rowIndex) {
        // create CellStyle
        CellStyle cellStyle = createStyleForHeader(sheet);

        // Create row
        Row row = sheet.createRow(rowIndex);

        // Create cells
        Cell cell = row.createCell(COLUMN_INDEX_ID);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Id");

        cell = row.createCell(COLUMN_INDEX_EXAMPLE);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("example");

        cell = row.createCell(COLUMN_INDEX_FOOD);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("M??n ??n/ N?????c u???ng");

        cell = row.createCell(COLUMN_INDEX_SIZE);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("K??ch th?????c");

        cell = row.createCell(COLUMN_INDEX_QUANTITY);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("S??? l?????ng");

        cell = row.createCell(COLUMN_INDEX_WEIGHT);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Tr???ng l?????ng/ Kh???i l?????ng");

        cell = row.createCell(COLUMN_INDEX_TEMPERATURE);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Nhi???t ?????");

        cell = row.createCell(COLUMN_INDEX_SMELL);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("H????ng v???");

        cell = row.createCell(COLUMN_INDEX_CONSTITUTIVE);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("C???u th??nh");

        cell = row.createCell(COLUMN_INDEX_OFFER);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(" ????? xu???t");

        cell = row.createCell(COLUMN_INDEX_PROMOTION);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Khuy???n m??i");

        cell = row.createCell(COLUMN_INDEX_CARTS);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("C???a h??ng/G??i mang ??i");

        cell = row.createCell(COLUMN_INDEX_OTHER);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Kh??c");

        cell = row.createCell(COLUMN_INDEX_STATUS);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Status Non_workable");

        cell = row.createCell(COLUMN_INDEX_WORKER_NAME);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("worker Name");


    }

    // Write data
    private static void writeBook(ReadCSV ReadCSV, Row row) {
        if (cellStyleFormatNumber == null) {
            // Format number
            short format = (short) BuiltinFormats.getBuiltinFormat("#,##0");
            // DataFormat df = workbook.createDataFormat();
            // short format = df.getFormat("#,##0");

            //Create CellStyle
            Workbook workbook = row.getSheet().getWorkbook();
            cellStyleFormatNumber = workbook.createCellStyle();
            cellStyleFormatNumber.setDataFormat(format);
        }

        Cell cell = row.createCell(COLUMN_INDEX_ID);
        cell.setCellValue(ReadCSV.getID());

        cell = row.createCell(COLUMN_INDEX_EXAMPLE);
        cell.setCellValue(ReadCSV.getExample());

        cell = row.createCell(COLUMN_INDEX_FOOD);
        cell.setCellValue(ReadCSV.getFood());

        cell = row.createCell(COLUMN_INDEX_SIZE);
        cell.setCellValue(ReadCSV.getSize());

        cell = row.createCell(COLUMN_INDEX_QUANTITY);
        cell.setCellValue(ReadCSV.getQuantity());

        cell = row.createCell(COLUMN_INDEX_WEIGHT);
        cell.setCellValue(ReadCSV.getWeight());

        cell = row.createCell(COLUMN_INDEX_TEMPERATURE);
        cell.setCellValue(ReadCSV.getTemperatureC());

        cell = row.createCell(COLUMN_INDEX_SMELL);
        cell.setCellValue(ReadCSV.getSmell());

        cell = row.createCell(COLUMN_INDEX_CONSTITUTIVE);
        cell.setCellValue(ReadCSV.getConstitutive());

        cell = row.createCell(COLUMN_INDEX_OFFER);
        cell.setCellValue(ReadCSV.getOffer());

        cell = row.createCell(COLUMN_INDEX_PROMOTION);
        cell.setCellValue(ReadCSV.getPromotion());

        cell = row.createCell(COLUMN_INDEX_CARTS);
        cell.setCellValue(ReadCSV.getCartShipper());

        cell = row.createCell(COLUMN_INDEX_OTHER);
        cell.setCellValue(ReadCSV.getOther());

        cell = row.createCell(COLUMN_INDEX_STATUS);
        cell.setCellValue(ReadCSV.getStatus());

        cell = row.createCell(COLUMN_INDEX_WORKER_NAME);
        cell.setCellValue(ReadCSV.getWorkerName());
    }

    // Create CellStyle for header
    private static CellStyle createStyleForHeader(Sheet sheet) {
        // Create font
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 14); // font size
        font.setColor(IndexedColors.WHITE.getIndex()); // text color

        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        return cellStyle;
    }

    // Auto resize column width
    private static void autosizeColumn(Sheet sheet, int lastColumn) {
        for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }
    }

    // Create output file
    private static void createOutputFile(Workbook workbook, String excelFilePath) throws IOException {
        try (OutputStream os = new FileOutputStream(excelFilePath)) {
            workbook.write(os);
        }
    }

}
