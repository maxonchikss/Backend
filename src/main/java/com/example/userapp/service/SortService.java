package com.example.userapp.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SortService {

    public List<Integer> mergeSort(List<Integer> array) {
        if (array.size() <= 1) {
            return array;
        }

        int mid = array.size() / 2;
        List<Integer> left = mergeSort(array.subList(0, mid));
        List<Integer> right = mergeSort(array.subList(mid, array.size()));

        return merge(left, right);
    }

    private List<Integer> merge(List<Integer> left, List<Integer> right) {
        List<Integer> result = new java.util.ArrayList<>();
        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {
            if (left.get(i) <= right.get(j)) {
                result.add(left.get(i++));
            } else {
                result.add(right.get(j++));
            }
        }

        while (i < left.size()) {
            result.add(left.get(i++));
        }

        while (j < right.size()) {
            result.add(right.get(j++));
        }

        return result;
    }

    public List<Integer> parseArray(String arrayString) {
        return Arrays.stream(arrayString.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}