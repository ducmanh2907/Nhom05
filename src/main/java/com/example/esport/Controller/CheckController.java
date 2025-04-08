package com.example.esport.Controller;

<<<<<<< HEAD
import com.example.esport.model.Checkin;
import com.example.esport.Service.CheckService;
import com.example.esport.model.Ticker;
=======

import com.example.esport.Service.CartService;
import com.example.esport.Service.CheckService;
import com.example.esport.model.Checkin;
>>>>>>> b2be986 (jonggun)
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

<<<<<<< HEAD
@Controller
@RequestMapping("/checkins")
public class CheckController {
=======


@Controller

public class CheckController {

>>>>>>> b2be986 (jonggun)
    @Autowired
    private CheckService checkService;


<<<<<<< HEAD
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




=======
    @GetMapping("/checkins")
    public String showCheckinForm(Model model) {
        model.addAttribute("checkin", new Checkin());
        return "checkins/checkin";
    }
    @PostMapping(value = "/checkins", consumes = "application/x-www-form-urlencoded")
    public String checkinData(@ModelAttribute Checkin checkin, Model model) {
        boolean dataExists = checkService.checkIfDataExists(checkin.getId(), checkin.getName(), checkin.getBeginAt(), checkin.getFullname(), checkin.getEmail());

        if (dataExists) {
            return "checkins/haha"; // Trả về template haha.html nếu dữ liệu tồn tại
        } else {
            model.addAttribute("message", "Data does not exist in the database.");
            return "error"; // Trả về template error.html với thông báo lỗi
        }
    }
>>>>>>> b2be986 (jonggun)
}
