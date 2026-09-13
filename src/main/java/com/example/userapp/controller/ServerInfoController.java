package com.example.userapp.controller;

import com.example.userapp.service.ServerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class ServerInfoController {

    @Autowired
    private ServerInfoService serverInfoService;

    @GetMapping("/server-info")
    public String serverInfo(Model model) {
        Map<String, String> info = serverInfoService.getServerInfo();
        model.addAttribute("serverInfo", info);
        return "server-info";
    }
}