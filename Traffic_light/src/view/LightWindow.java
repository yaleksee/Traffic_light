package view;

import javax.swing.*;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;
import java.awt.*;
import java.util.ArrayList;

import static controller.Write.*;

public class LightWindow extends JFrame {

    private int tominute = 1000; // увеличение введенного значения
    private final static int SIZE = 150; // фиксированный размер для кружочков

    private JLabel one, two, three; // элементы окна

    private ArrayList<JLabel> list = new ArrayList<>(); // массив для элементов окна и геттер для него

    private ArrayList getList() {
        return list;
    }

    private static Icon[] icons = new Icon[4]; // массив для хранения цветов и размеров, через иконки

    static { // обращение к конструктору
        icons[0] = new curveIcon(Color.RED, SIZE);
        icons[1] = new curveIcon(Color.YELLOW, SIZE);
        icons[2] = new curveIcon(Color.GREEN, SIZE);
        icons[3] = new curveIcon(Color.BLACK, SIZE); // выключенная ячейка светофора
    }


    public void init() throws InterruptedException {
        setTitle("Traffic_light");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS)); // выравнивание по оси y

        //назначаем переменные и заполняем массив, чтобы не было исключения пустой массив при запуске потоков
        one = new JLabel(icons[0]);
        list.add(one);
        two = new JLabel(icons[1]);
        list.add(two);
        three = new JLabel(icons[2]);
        list.add(three);

        jPanel.add(one);
        jPanel.add(two);
        jPanel.add(three);


        getContentPane().add(jPanel);
        pack(); // стягивание рамок экрана до размеров самого крупного элемента
        initMetalLookAndFeel(); // метод установки дизайна экрана
        setVisible(true);
        for (int i = 10; i >= 0 ; i-- ) {
            runLight(); // запуск переключения цветов
        }
    }

    private void runLight() throws InterruptedException {
        ArrayList lst = getList();
        Thread red = new Thread(() -> {
            try {
                ((JLabel) lst.get(0)).setIcon(icons[0]);
                ((JLabel) lst.get(2)).setIcon(icons[3]);
                Thread.sleep(getMinuteRed() * tominute);
            } catch (Exception ex) {
                System.err.println("Warning!");
            }
        });
        Thread yellow = new Thread(() -> {
            try {
                ((JLabel) lst.get(1)).setIcon(icons[1]);
                ((JLabel) lst.get(0)).setIcon(icons[3]);
                Thread.sleep(getMinuteYellow() * tominute);
            } catch (Exception ex) {
                System.err.println("Warning!");
            }
        });
        Thread green = new Thread(() -> {
            try {
                ((JLabel) lst.get(2)).setIcon(icons[2]);
                ((JLabel) lst.get(1)).setIcon(icons[3]);
                Thread.sleep(getMinuteGreen() * tominute);
            } catch (Exception ex) {
                System.err.println("Warning!");
            }
        });
        red.start();
        red.join();
        yellow.start();
        yellow.join();
        green.start();
        green.join();

    }

    static class curveIcon implements Icon {
        private int width;
        private int height;
        private Color useColor;

        curveIcon(Color c, int s) {
            this.useColor = c;
            this.width = s;
            this.height = s;
        }

        public void paintIcon(Component c, Graphics g, int x, int y) {
            g.setColor(useColor);
            g.drawOval(x, y, width, height);
            g.setColor(useColor);
            g.fillOval(x, y, width, height);
        }

        public int getIconWidth() {
            return width;
        }

        public int getIconHeight() {
            return height;
        }

        public Color getColor() {
            return useColor;
        }
    }

    private static void initMetalLookAndFeel() {
        try {
            // устанавливаем используемую тему оформления.
            MetalLookAndFeel.setCurrentTheme(new DefaultMetalTheme());
            // устанавливаем Look And Feel
            UIManager.setLookAndFeel(new MetalLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            System.err.println("Can't use the specified look and feel on this platform.");
        } catch (Exception e) {
            System.err.println("Couldn't get specified look and feel, for some reason.");
        }
    }
}
