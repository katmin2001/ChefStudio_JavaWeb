package com.devpro.javaweb.controller.administrator;

import com.devpro.javaweb.controller.BaseController;
import com.devpro.javaweb.dto.SalesData;
import com.devpro.javaweb.dto.SalesDataByCategories;
import com.devpro.javaweb.model.Categories;
import com.devpro.javaweb.services.CategoriesService;
import com.devpro.javaweb.services.SalesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SalesController extends BaseController {
    @Autowired
    private SalesService salesService;
    @Autowired
    private CategoriesService categoriesService;

    @ModelAttribute("categories")
    public List<Categories> getAllCategories() {
        return categoriesService.findAll();
    }
    @RequestMapping(value = { "admin/revenue-by-month/{year}" }, method = RequestMethod.GET)
    public String getRevenueByMonth(final Model model,
                             final HttpServletRequest request,
                             final HttpServletResponse response,
                             @PathVariable("year") String year
                             ) throws IOException {
        // tính toán doanh thu của từng tháng trong năm và lưu vào mảng data
        model.addAttribute("disYear",year);
        List<String> yearList = salesService.Year();
        model.addAttribute("yearList",yearList);
        List<SalesData> salesData = salesService.Sales(year);
        BigDecimal[] dataSales = new BigDecimal[12];
        for (int i = 0; i<12; i++){
            dataSales[i] = BigDecimal.valueOf(0);
        }
        for(SalesData sales :salesData){
            dataSales[sales.getMonth()-1] = sales.getSales();
        }
//        for(SalesData sales :salesData){
//            System.out.println(sales.getMonth() +" "+sales.getSales());
//        }
//        for(int i =0; i<12; i++){
//            System.out.print(dataSales[i]+" ");
//        }
//        String[] dataSales = {"10000", "20000", "15000", "30000", "30000", "30000", "30000", "30000", "30000", "30000", "30000", "30000"};
        ObjectMapper mapper = new ObjectMapper();
        String JsOn = mapper.writeValueAsString(dataSales);
        // truyền dữ liệu vào model attribute "data"
        model.addAttribute("dataSales", JsOn);

        return "administrator/chartByMonth";
    }
    @RequestMapping(value = { "admin/revenue-by-year" }, method = RequestMethod.GET)
    public String getRevenueByYear(final Model model,
                             final HttpServletRequest request,
                             final HttpServletResponse response
    ) throws IOException {
        // tính toán doanh thu của  năm và lưu vào mảng data
        List<SalesData> salesData = salesService.SalesByYear();
        List<BigDecimal> dataSales = new ArrayList<>();
        List<Integer> year = new ArrayList<>();
        for(SalesData sales :salesData){
            year.add(sales.getMonth());
            dataSales.add(sales.getSales());
        }
        ObjectMapper mapper = new ObjectMapper();
        String salesJSON = mapper.writeValueAsString(dataSales);
        String yearJSON = mapper.writeValueAsString(year);
        // truyền dữ liệu vào model attribute "data"
        model.addAttribute("year", yearJSON);
        model.addAttribute("dataSalesByYear", salesJSON);

        return "administrator/chartByYear";
    }
    @RequestMapping(value = { "admin/revenue-by-category" }, method = RequestMethod.GET)
    public String getRevenueByCategory(final Model model,
                                   final HttpServletRequest request,
                                   final HttpServletResponse response
    ) throws IOException {
        // tính toán doanh thu theo sản phẩm và lưu vào mảng data
        List<SalesDataByCategories> salesData = salesService.SalesByCategory();
        List<BigDecimal> dataSales = new ArrayList<>();
        List<String> category = new ArrayList<>();
        List<Categories> t1 = getAllCategories();
        for(Categories categories: t1){
            category.add(categories.getName());
        }
        boolean check = false;
        for(String i: category){
            check = false;
            for(SalesDataByCategories sales: salesData){
                if (i.equalsIgnoreCase(sales.getNameCategory())){
                    dataSales.add(sales.getSales());
                    check = true;
                    break;
                }
            }
            if(check == false){
                dataSales.add(BigDecimal.valueOf(0));
            }
        }
//        for(SalesDataByCategories sales: salesData){
//            System.out.println(sales.getNameCategory()+" "+sales.getSales());
//        }
//        for(BigDecimal data: dataSales){
//            System.out.print(data+" ");
//        }
        ObjectMapper mapper = new ObjectMapper();
        String salesJSON = mapper.writeValueAsString(dataSales);
        String cateJSON = mapper.writeValueAsString(category);
        // truyền dữ liệu vào model attribute "data"
        model.addAttribute("cate", cateJSON);
        model.addAttribute("dataSalesByCategory", salesJSON);

        return "administrator/chartByCategory";
    }

    @RequestMapping(value = { "admin/revenue-by-month/export/{year}" }, method = RequestMethod.GET)
    public void exportRevenueByMonthExcel(
            @PathVariable("year") String year,
            HttpServletResponse response) throws InvalidFormatException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=revenue_by_month_in_" + year + "_" + System.currentTimeMillis() + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<SalesData> salesData = salesService.Sales(year);
        String[] HEADERs = { "Tháng", "Doanh thu" };

        try {
            // Tạo workbook và sheet
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Revenue");

            // CSS header
            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            // Tạo header
            Row headerRow = sheet.createRow(0);
            for (int col = 0; col < HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERs[col]);
                cell.setCellStyle(headerStyle);
            }

            // Input data
            int rowIdx = 1;
            for (int i = 1; i <= 12; i++) {
                for (SalesData s : salesData) {
                    Row row = sheet.createRow(rowIdx++);
                    if (s.getMonth() == i) {
                        row.createCell(0).setCellValue(s.getMonth());
                        row.createCell(1).setCellValue(s.getSales().toString());
                        break;
                    }
                    row.createCell(0).setCellValue(i);
                    row.createCell(1).setCellValue(0);
                }
            }

            ServletOutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            workbook.close();

            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException("Fail to export excel file: " + e.getMessage());
        }
    }

    @RequestMapping(value = { "admin/revenue-by-year/export" }, method = RequestMethod.GET)
    public void exportRevenueByYearExcel(HttpServletResponse response) throws InvalidFormatException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=revenue_by_year_" + System.currentTimeMillis() + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<SalesData> salesData = salesService.SalesByYear();
        String[] HEADERs = { "Năm", "Doanh thu" };

        try {
            // Tạo workbook và sheet
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Revenue");

            // CSS header
            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            // Tạo header
            Row headerRow = sheet.createRow(1);
            for (int col = 0; col < HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERs[col]);
                cell.setCellStyle(headerStyle);
            }

            // Input data
            int rowIdx = 2;
            for (SalesData s : salesData) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(s.getMonth());
                row.createCell(1).setCellValue(s.getSales().toString());
            }

            ServletOutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            workbook.close();

            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException("Fail to export excel file: " + e.getMessage());
        }
    }

    @RequestMapping(value = { "admin/revenue-by-category/export" }, method = RequestMethod.GET)
    public void exportRevenueByCategoryExcel(HttpServletResponse response) throws InvalidFormatException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=revenue-by-category_" + System.currentTimeMillis() + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<SalesDataByCategories> salesData = salesService.SalesByCategory();
        String[] HEADERs = { "Loại sản phẩm", "Doanh thu" };

        try {
            // Tạo workbook và sheet
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Revenue");

            // CSS header
            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            // Tạo header
            Row headerRow = sheet.createRow(0);
            for (int col = 0; col < HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERs[col]);
                cell.setCellStyle(headerStyle);
            }

            // Input data
            List<Categories> categories = this.getAllCategories();
            int rowIdx = 1;
            boolean check;
            for (Categories c: categories) {
                Row row = sheet.createRow(rowIdx++);
                check = false;
                for (SalesDataByCategories s : salesData) {
                    if (s.getNameCategory().equalsIgnoreCase(c.getName())) {
                        row.createCell(0).setCellValue(s.getNameCategory());
                        row.createCell(1).setCellValue(s.getSales().toString());
                        check = true;
                        break;
                    }
                    if (check == false) {
                        row.createCell(0).setCellValue(c.getName());
                        row.createCell(1).setCellValue(0);
                    }
                }
            }

            ServletOutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            workbook.close();

            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException("Fail to export excel file: " + e.getMessage());
        }
    }
}
