package com.example.data_masking_project.sercurity;


import java.util.Random;

public class FakeData {
    private static final int PHONE_NUMBER_LENGTH = 10;
    private static final int BANK_NUM_LENGTH = 19;
    private static final int CARD_ID_LENGTH = 16;

    public static String generateFakeBankNum(String bankNum) {
        StringBuilder sb = new StringBuilder();
        sb.append(bankNum.substring(0,4));
        sb.append("-");
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            sb.append(random.nextInt(10)); // thêm số ngẫu nhiên từ 0 đến 9 vào chuỗi
        }
        sb.append("-");
        for (int i = 0; i < 4; i++) {
            sb.append(random.nextInt(10)); // thêm số ngẫu nhiên từ 0 đến 9 vào chuỗi
        }
        sb.append("-");
        sb.append(bankNum.substring(bankNum.length() - 4));
        return sb.toString();
    }



    public static String generateFakePhone(String phone) {
        String suffix = phone.substring(phone.length() - 4);
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        sb.append("0"); // bắt đầu với số 0
        // tạo 5 số ngẫu nhiên đầu tiên
        for (int i = 0; i < PHONE_NUMBER_LENGTH - 5; i++) {
            sb.append(random.nextInt(10)); // thêm số ngẫu nhiên từ 0 đến 9 vào chuỗi
        }

        // thêm 4 số cuối của chuỗi tham số vào chuỗi kết quả
        sb.append(suffix);

        return sb.toString();
    }
        public static String generateFakeIdCard(String idCard) {
            StringBuilder sb = new StringBuilder();
            String twoChartFirst = idCard.substring(0,2);
            String twoChartLast = idCard.substring(idCard.length() - 2);
            sb.append(twoChartFirst);
            Random random = new Random();
            for (int i = 0; i < CARD_ID_LENGTH - 4; i++) {
                sb.append(random.nextInt(10)); // thêm số ngẫu nhiên từ 0 đến 9 vào chuỗi
            }
            sb.append(twoChartLast);
            return sb.toString();
        }
}
