package com.example.userapp.controller;

import com.example.userapp.service.SortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SortController {

    @Autowired
    private SortService sortService;

    @GetMapping("/sort")
    public String sort(
            @RequestParam(value = "array", required = false) String arrayString,
            Model model) {

        if (arrayString == null || arrayString.trim().isEmpty()) {
            model.addAttribute("error", "Параметр array не указан");
            return "sort";
        }

        try {
            List<Integer> originalArray = sortService.parseArray(arrayString);
            List<Integer> sortedArray = sortService.mergeSort(originalArray);

            model.addAttribute("originalArray", originalArray);
            model.addAttribute("sortedArray", sortedArray);
        } catch (NumberFormatException e) {
            model.addAttribute("error", "Неверный формат массива. Используйте числа, разделенные запятыми");
        }

        return "sort";
    }
}