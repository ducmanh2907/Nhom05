package com.example.esport.Controller;


import com.example.esport.Service.CartService;
import com.example.esport.Service.CheckService;
import com.example.esport.model.Checkin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller

public class CheckController {

    @Autowired
    private CheckService checkService;


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
}
