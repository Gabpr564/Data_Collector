package datacollector.datacollector;

import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;

import java.util.Timer;
import java.util.TimerTask;


public class Main{
    public static void main(String[] args) {
        Timer timer = new Timer();
        OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                double cpuLoad = osBean.getSystemCpuLoad() * 100;
                long totalMemory = osBean.getTotalPhysicalMemorySize();
                long freeMemory = osBean.getFreePhysicalMemorySize();
                int cpupercent = (int)Math.round(cpuLoad);
                long memoryUsed = 0;
                memoryUsed =(totalMemory / (1024 * 1024)) - (freeMemory / (1024 * 1024));
                System.out.println("CPU Load: " + cpupercent + "%");
                System.out.println("Total Memory: " + totalMemory / (1024 * 1024) + " MB");
                System.out.println("Free Memory: " + freeMemory / (1024 * 1024) + " MB");
                SQLconnector.updateDatalist(cpupercent,Math.toIntExact(memoryUsed));
            }
        };

        timer.schedule(task, 0, 5000);
    }
}

