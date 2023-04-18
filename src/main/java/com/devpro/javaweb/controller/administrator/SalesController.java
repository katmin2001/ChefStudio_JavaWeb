package com.devpro.javaweb.controller.administrator;

import com.devpro.javaweb.controller.BaseController;
import com.devpro.javaweb.dto.SalesData;
import com.devpro.javaweb.services.SalesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = { "admin/revenue-by-month/{year}" }, method = RequestMethod.GET)
    public String getRevenue(final Model model,
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
}
