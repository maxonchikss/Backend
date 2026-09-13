package com.example.userapp.service;

import org.springframework.stereotype.Service;

@Service
public class DrawerService {

    public String generateSVG(int num) {
        int shape = num & 0b11;
        int color = (num >> 2) & 0b111;
        int size = (num >> 5) & 0b111;

        String[] shapes = {"circle", "rect", "ellipse", "polygon"};
        String[] colors = {"red", "blue", "green", "yellow", "purple", "orange", "pink", "brown"};

        String shapeType = shapes[shape % shapes.length];
        String colorName = colors[color % colors.length];
        int dimension = 50 + size * 10;

        StringBuilder svg = new StringBuilder();
        svg.append("<svg width=\"").append(dimension * 2).append("\" height=\"").append(dimension * 2).append("\" xmlns=\"http://www.w3.org/2000/svg\">");

        switch (shapeType) {
            case "circle":
                svg.append("<circle cx=\"").append(dimension)
                        .append("\" cy=\"").append(dimension)
                        .append("\" r=\"").append(dimension / 2)
                        .append("\" fill=\"").append(colorName).append("\"/>");
                break;
            case "rect":
                svg.append("<rect x=\"").append(dimension / 2)
                        .append("\" y=\"").append(dimension / 2)
                        .append("\" width=\"").append(dimension)
                        .append("\" height=\"").append(dimension)
                        .append("\" fill=\"").append(colorName).append("\"/>");
                break;
            case "ellipse":
                svg.append("<ellipse cx=\"").append(dimension)
                        .append("\" cy=\"").append(dimension)
                        .append("\" rx=\"").append(dimension / 2)
                        .append("\" ry=\"").append(dimension / 3)
                        .append("\" fill=\"").append(colorName).append("\"/>");
                break;
            case "polygon":
                svg.append("<polygon points=\"")
                        .append(dimension).append(",").append(dimension / 2).append(" ")
                        .append(dimension * 3 / 2).append(",").append(dimension * 3 / 2).append(" ")
                        .append(dimension / 2).append(",").append(dimension * 3 / 2)
                        .append("\" fill=\"").append(colorName).append("\"/>");
                break;
        }

        svg.append("</svg>");
        return svg.toString();
    }

    public String getShapeName(int shape) {
        String[] shapes = {"Круг", "Прямоугольник", "Эллипс", "Многоугольник"};
        return shapes[shape % shapes.length];
    }

    public String getColorName(int color) {
        String[] colors = {"Красный", "Синий", "Зеленый", "Желтый", "Фиолетовый", "Оранжевый", "Розовый", "Коричневый"};
        return colors[color % colors.length];
    }
}