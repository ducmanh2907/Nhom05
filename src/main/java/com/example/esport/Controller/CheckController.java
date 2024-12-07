package com.example.esport.Controller;

import com.example.esport.model.Checkin;
import com.example.esport.Service.CheckService;
import com.example.esport.model.Ticker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/checkins")
public class CheckController {
    @Autowired
    private CheckService checkService;


    @GetMapping
    public String showForm(Model model) {
        model.addAttribute("checkin", new Checkin());
        return "checkins/checkin"; // Tên file HTML
    }

    // Xử lý form
    @PostMapping
    public String submitForm(@ModelAttribute("checkin") Checkin checkin, Model model) {

        if (checkin.getProductId() == null || checkin.getProductName() == null || checkin.getBeginAt() == null
                || checkin.getFullName() == null || checkin.getEmail() == null) {
            model.addAttribute("message", "Dữ liệu không đúng");
            return "checkins/checkin";
        }


        if (checkService.isDuplicate(checkin)) {
            // Lấy dữ liệu Ticker từ Service
            Ticker ticker = checkService.getTickerData(checkin);

            // Đưa dữ liệu vào model
            model.addAttribute("ticker", ticker);

            return "checkins/ticker"; // Chuyển đến form Ticker
        }

        model.addAttribute("message", "Dữ liệu hợp lệ. Bạn có thể tiếp tục.");
        return "checkins/checkin";



    }




}
