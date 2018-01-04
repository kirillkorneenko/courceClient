package by.bsuir.stock.util;

import by.bsuir.stock.bean.CargosininvoiceEntity;
import by.bsuir.stock.bean.InvoiceEntity;
import by.bsuir.stock.window.windowUser.PrintReport;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class CreateWorkbook {

    private Workbook book;
    private InvoiceEntity invoiceEntity;

    private PrintReport printReport;

    public void createWorkbook() {
        try {

            File file = new File( "classes/by/bsuir/stock/resource/template.xlsx");
            book =(XSSFWorkbook) WorkbookFactory.create(file);

            Sheet sheet = book.getSheet("СчФ");

            //номер
            Row row = sheet.getRow(4);
            row.getCell(12).setCellValue(invoiceEntity.getNumber());
            //дата
            row = sheet.getRow(5);
            row.getCell(12).setCellValue(invoiceEntity.getDate().toString());

            row = sheet.getRow(12);
            row.getCell(3).setCellValue(invoiceEntity.getUsersByIdUser().getSurName());

            row = sheet.getRow(13);
            row.getCell(3).setCellValue(invoiceEntity.getUsersByIdUser().getName());

            row = sheet.getRow(32);
            row.getCell(4).setCellValue(invoiceEntity.getUsersByIdEmployee().getSurName());

            row= sheet.getRow(19);
            CellStyle style=row.getRowStyle();

            int i =20;
            for (CargosininvoiceEntity cargosininvoiceEntity : invoiceEntity.getCargosininvoicesById()) {

                sheet.shiftRows(i,i+15,1);
                sheet.getRow(i+1).setHeight((short) 2);
                row=sheet.createRow(i);


                Cell cell =row.createCell(2);
                CellRangeAddress region = new CellRangeAddress(i,i,2,5);
                sheet.addMergedRegion(region);
                cell.setCellValue(cargosininvoiceEntity.getCargoByIdCargo().getName());

                cell=row.createCell(6);
                region = new CellRangeAddress(i,i,6,8);
                sheet.addMergedRegion(region);
                cell.setCellValue("Шт.");

                cell=row.createCell(9);
                region = new CellRangeAddress(i,i,9,12);
                sheet.addMergedRegion(region);
                cell.setCellValue(1);

                cell=row.createCell(13);
                region = new CellRangeAddress(i,i,13,16);
                sheet.addMergedRegion(region);
                cell.setCellValue(cargosininvoiceEntity.getCargoByIdCargo().getPrice());
                i++;
            }

            book.setForceFormulaRecalculation(true);

            printReport = new PrintReport(book, invoiceEntity);
            printReport.setLocationRelativeTo(null);
            printReport.setVisible(true);



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (EncryptedDocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException e) {
            e.printStackTrace();
        }
    }

    public Workbook getBook() {
        return book;
    }

    public void setBook(Workbook book) {
        this.book = book;
    }

    public InvoiceEntity getInvoiceEntity() {
        return invoiceEntity;
    }

    public void setInvoiceEntity(InvoiceEntity invoiceEntity) {
        this.invoiceEntity = invoiceEntity;
    }

}
