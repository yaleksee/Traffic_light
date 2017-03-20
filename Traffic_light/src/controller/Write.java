package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Write {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static int getMinuteRed() {
        return minuteRed;
    }

    public static int getMinuteGreen() {
        return minuteGreen;
    }

    public static int getMinuteYelow() {
        return minuteYelow;
    }

    static int minuteRed;
    static int minuteGreen;
    static int minuteYelow;

    //три вспомогательные переменные для фиксации того, что ввод был осуществлен верно
    static int i = 0;
    static int j = 0;
    static int z = 0;

    //регулярное выражение
    static String pattern1 = "[0-9]";
    static Pattern p1 = Pattern.compile(pattern1);

    //метод записи
    public static boolean writeMinute() throws IOException {
        for (; ; ) {
            if (i == 0) {
                System.out.println("please write minute for red, only numeric");
                String s1 = bufferedReader.readLine();
                Matcher m1 = p1.matcher(s1);
                if (m1.find()) {
                    System.out.println("ok");
                    i = +1;
                    minuteRed = Integer.valueOf(s1);
                } else System.out.println("no!");
            }

            if (j == 0) {
                System.out.println("please write minute for Green, only numeric");
                String s2 = bufferedReader.readLine();
                Matcher m2 = p1.matcher(s2);
                if (m2.find()) {
                    System.out.println("ok");
                    j = +1;
                    minuteGreen = Integer.valueOf(s2);
                } else System.out.println("no!");
            }

            if (z == 0) {
                System.out.println("please write minute for Yellow, only numeric");
                String s3 = bufferedReader.readLine();
                Matcher m3 = p1.matcher(s3);
                if (m3.find()) {
                    System.out.println("ok");
                    z = +1;
                    minuteYelow = Integer.valueOf(s3);
                } else System.out.println("no!");
            }
            if (i != 0 && j != 0 && z != 0) {
                bufferedReader.close();
                return true;
            }
        }
    }

}
