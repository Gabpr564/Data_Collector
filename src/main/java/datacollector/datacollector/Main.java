package datacollector.datacollector;

import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;


public class Main{
    private static String testName;
    public static void main(String[] args) {
        System.out.println("Enter test Name: ");
        Scanner scanner = new Scanner(System.in);
        testName = scanner.nextLine();
        Timer timer = new Timer();
        DataExtractor datacollector = new DataExtractor(testName);
        timer.schedule(datacollector, 0, 1000);
        new Thread(() -> {
            System.out.println("Press Enter to stop Test...");
            Scanner enter = new Scanner(System.in);
            enter.nextLine();
            timer.cancel();
            System.out.println("Test Stopped");
            System.out.println("Test Ran for:" + datacollector.getCount());
            SQLconnector.updateDatalist(testName,datacollector.getAverageCpuLoad(), datacollector.getAverageMemoryUsed(), datacollector.getCount());
        }).start();
    }
}

