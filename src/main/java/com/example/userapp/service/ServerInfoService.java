package com.example.userapp.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Service
public class ServerInfoService {

    public Map<String, String> getServerInfo() {
        Map<String, String> info = new HashMap<>();

        info.put("Операционная система", executeCommand("uname -a"));
        info.put("Текущий пользователь", executeCommand("whoami"));
        info.put("Информация о пользователе", executeCommand("id"));
        info.put("Дата и время", executeCommand("date"));
        info.put("Список процессов", executeCommand("ps aux | head -20"));
        info.put("Использование диска", executeCommand("df -h"));
        info.put("Использование памяти", executeCommand("free -h"));
        info.put("Сетевые интерфейсы", executeCommand("ifconfig 2>/dev/null || ip addr"));

        return info;
    }

    private String executeCommand(String command) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("/bin/sh", "-c", command);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                return "Команда завершилась с кодом: " + exitCode;
            }

            return output.toString().trim();
        } catch (Exception e) {
            return "Ошибка выполнения команды: " + e.getMessage();
        }
    }
}