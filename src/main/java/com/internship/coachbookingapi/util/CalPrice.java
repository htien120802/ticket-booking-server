package com.internship.coachbookingapi.util;

import java.util.Arrays;
import java.util.UUID;

public class CalPrice {
    public double getPrice(String departureSlug, String destinationSlug, UUID coachTypeId) {
        // Tính giá theo điểm đi và điểm đến
        if (coachTypeId.equals("c0a80203-8b83-1195-818b-83b7df7d0000")) { //Giường nằm
            switch (checkLine(departureSlug, destinationSlug)) {
                case 1:
                    return 290000;
                case 2, 3:
                    return 270000;
                default:
                    return 0;
            }
        } else if (coachTypeId.equals("c0a80203-8b83-1195-818b-83b7e0030001")) { // Giường nằm 34
            switch (checkLine(departureSlug, destinationSlug)) {
                case 1:
                    return 330000;
                case 2, 3:
                    return 310000;
                default:
                    return 0;
            }

        } else if (coachTypeId.equals("c0a80203-8b83-1195-818b-83b7e00d0002")) { // Phòng nằm
            switch (checkLine(departureSlug, destinationSlug)) {
                case 1, 2, 3:
                    return 440000;
                default:
                    return 0;
            }

        } else if (coachTypeId.equals("c0a80203-8b83-1195-818b-83b7e0170003")) { // Ghế nằm
            switch (checkLine(departureSlug, destinationSlug)) {
                case 1:
                    return 330000;
                case 2, 3:
                    return 310000;
                default:
                    return 0;
            }
        } else {
            return 0;
        }
    }

    public int checkLine(String departureSlug, String destinationSlug) {
        // Kiểm tra chuyến đi theo nơi đi và đến
        String[] list1a = {"tp-ho-chi-minh"};
        String[] list1b = {"duc-trong", "da-lat"};

        String[] list2a = {"tp-ho-chi-minh"};
        String[] list2b = {"phuong-lam-dinh-quan", "tan-phu", "madagui", "bao-loc", "di-linh"};

        String[] list3a = {"duc-trong", "da-lat"};
        String[] list3b = {"madagui", "tan-phu", "phuong-lam-dinh-quan"};

        if (Arrays.asList(list1a).contains(departureSlug) && Arrays.asList(list1b).contains(destinationSlug) ||
                Arrays.asList(list1b).contains(departureSlug) && Arrays.asList(list1a).contains(destinationSlug)) {
            return 1; // Tp Hồ Chí Minh <=> Đức Trọng, Đà Lạt
        } else if (Arrays.asList(list2a).contains(departureSlug) && Arrays.asList(list2b).contains(destinationSlug) ||
                Arrays.asList(list2b).contains(departureSlug) && Arrays.asList(list2a).contains(destinationSlug)) {
            return 2; // Tp Hồ Chí Minh <=> Định Quán, Tân phú,Phương Lâm, Mađagui, Bảo Lộc, Di Linh
        } else if (Arrays.asList(list3a).contains(departureSlug) && Arrays.asList(list3b).contains(destinationSlug) ||
                Arrays.asList(list3b).contains(departureSlug) && Arrays.asList(list3a).contains(destinationSlug)) {
            return 3; // Đà Lạt, Đức Trọng <=> Mađagui, Phương Lâm, Tân Phú, Định Quán
        } else {
            return 0;
        }
    }
}
