package datacollector.datacollector;

import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.TimerTask;

public class DataExtractor extends TimerTask {
    private double averageCpuLoad = 0;
    private double averageMemoryUsed = 0;
    private int count = 0;
    private static String testName;
    public DataExtractor(String testName){
        this.testName = testName;
    }
    private ArrayList<Double> averageCpuLoadList = new ArrayList<>();
    private ArrayList<Double> averageMemoryList = new ArrayList<>();
    OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
    @Override
    public void run() {
        count++;
        double cpuLoad = osBean.getSystemCpuLoad() * 100;
        long totalMemory = osBean.getTotalPhysicalMemorySize();
        long freeMemory = osBean.getFreePhysicalMemorySize();
        long memoryUsed = (totalMemory / (1024 * 1024)) - (freeMemory / (1024 * 1024));
        double memoryUsedtoDouble = (double)memoryUsed;
        averageCpuLoadList.add(cpuLoad);
        averageMemoryList.add(memoryUsedtoDouble);

    }
    public double getAverageCpuLoad(){
        double sum = 0;
        for(double i : averageCpuLoadList){
            sum += i;
        }
        return sum / averageCpuLoadList.size();
    }
    public double getAverageMemoryUsed(){
        double sum = 0;
        for(double i : averageMemoryList){
            sum += i;
        }
        return sum / averageMemoryList.size();
    }
    public int getCount(){
        return count;
    }

}
