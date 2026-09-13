package com.example.userapp.controller;

import com.example.userapp.service.DrawerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DrawerController {

    @Autowired
    private DrawerService drawerService;

    @GetMapping("/drawer")
    public String drawer(
            @RequestParam(value = "num", required = false) Integer num,
            Model model) {

        if (num == null) {
            model.addAttribute("error", "Параметр num не указан");
            return "drawer";
        }

        if (num < 0 || num > 255) {
            model.addAttribute("error", "Параметр num должен быть от 0 до 255");
            return "drawer";
        }

        int shape = num & 0b11;
        int color = (num >> 2) & 0b111;
        int size = (num >> 5) & 0b111;

        String svg = drawerService.generateSVG(num);

        model.addAttribute("num", num);
        model.addAttribute("svg", svg);
        model.addAttribute("shape", drawerService.getShapeName(shape));
        model.addAttribute("color", drawerService.getColorName(color));
        model.addAttribute("size", size);

        return "drawer";
    }
}