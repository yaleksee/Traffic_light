package module;

import view.LightWindow;

import java.io.IOException;

import static controller.Write.writeMinute;

public class ProcessTraffic {
    public static void go() throws IOException, InterruptedException {
        writeMinute(); // запуск контроллера, ввод информации с клавиатуры
        if (writeMinute())
            new LightWindow().init(); // еслди ввод отработал коректно запуск режима окна и прорисовки для пользователя
    }
}
