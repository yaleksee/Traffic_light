package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Write {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static int getMinuteRed() {
        return minuteRed;
    }

    public static int getMinuteGreen() {
        return minuteGreen;
    }

    public static int getMinuteYellow() {
        return minuteYellow;
    }

    private static int minuteRed = 0;
    private static int minuteGreen = 0;
    private static int minuteYellow = 0;

    //три вспомогательные переменные для фиксации того, что ввод был осуществлен верно
    private static int i = 0;
    private static int j = 0;
    private static int z = 0;

    //регулярное выражение
    private static String pattern1 = "[1-9]";
    private static Pattern p1 = Pattern.compile(pattern1);

    private static int entryIsChecked(int minute, String name) throws IOException {
        System.out.println("please write minute for color " + name + " only numeric");
        String s = bufferedReader.readLine();
        Matcher m = p1.matcher(s);
        if (m.find()) {
            System.out.println("ok");
            minute = Integer.valueOf(s);
        } else System.out.println("no!");
        return minute;
    }

    //метод записи
    public static boolean writeMinute() throws IOException {
        for (; ; ) {
            if (i == 0) {
                entryIsChecked(minuteRed, "RED");
                i = +1;
            }

            if (j == 0) {
                entryIsChecked(minuteYellow, "YELLOW");
                j = +1;
            }

            if (z == 0) {
                entryIsChecked(minuteGreen, "GREEN");
                z = +1;
            }

            if (i != 0 && j != 0 && z != 0) {
                bufferedReader.close();
                System.out.println("all right");
                return true;
            }
        }
    }
}
