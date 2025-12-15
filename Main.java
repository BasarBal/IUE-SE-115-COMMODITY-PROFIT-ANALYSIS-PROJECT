// Main.java — Students version
import java.io.*;
import java.util.*;

public class Main {
    static final int MONTHS = 12;
    static final int DAYS = 28;
    static final int COMMS = 5;
    static String[] commodities = {"Gold", "Oil", "Silver", "Wheat", "Copper"};
    static String[] months = {"January","February","March","April","May","June","July","August","September","October","November","December"}; 

    static int[][][] infoData = new int[MONTHS][DAYS + 1][COMMS];
    // ======= CONVERTING COMMODITIES INTO NUMBERS =======
    public static int commodityChange(String name){
        for (int i = 0; i < COMMS; i++) {
            if (commodities[i].equals(name)) {
                return i;
            }
        }
        return -1;
    }

    // ======== REQUIRED METHOD LOAD DATA (Students fill this) ========
    public static void loadData() {
    }

    // ======== 10 REQUIRED METHODS (Students fill these) ========
// 1
    public static String mostProfitableCommodityInMonth(int month) {
        if (month < 0 || month >= MONTHS) {
            return "INVALID_MONTH";
        }

        int maxProfit = -2000000000;
        int bestCommIndex = -1;

        for (int i = 0; i < COMMS; i++) {
            int totalComm = 0;

            for (int d = 1; d <= DAYS; d++) {
                totalComm += infoData[month][d][i];
            }

            if (totalComm > maxProfit) {
                maxProfit = totalComm;
                bestCommIndex = i;
            }
        }
        return commodities[bestCommIndex] + " " + maxProfit;
    }
// 2
    public static int totalProfitOnDay(int month, int day) {
        
        if (month < 0 || month >= MONTHS || day < 1 || day > DAYS) return -99999;

        int total = 0;
        
        for (int i = 0; i < COMMS; i++) {
            total += infoData[month][day][i];
        }
        return total;
    }
// 3
    public static int commodityProfitInRange(String commodity, int fromDay, int toDay) { 
        int commIndex = commodityChange(commodity);
        if (commIndex == -1 || fromDay > toDay || fromDay < 1 || toDay > DAYS) {
            return -99999;
        }

        int total = 0;

        for (int m = 0; m < MONTHS; m++) {

            for (int d = fromDay; d <= toDay; d++) {
                total += infoData[m][d][commIndex];
            }
        }
        return total;
    }
// 4
    public static int bestDayOfMonth(int month) { 
        if (month < 0 || month >= MONTHS) {
            return -1;
        }

        int maxProfit = 0;
        int bestDay = -1;

        for (int d = 1; d <= DAYS; d++) {
            int dailyTotal = 0;
            for (int i = 0; i < COMMS; i++) {
                dailyTotal += infoData[month][d][i];
            }

            if (dailyTotal > maxProfit) {
                maxProfit = dailyTotal;
                bestDay = d; 
            }
        }
        return bestDay;
        
    }
// 5
    public static String bestMonthForCommodity(String commodity) {
        return "DUMMY";
    }
// 6
    public static int consecutiveLossDays(String commodity) {
        return 1234;
    }
// 7
    public static int daysAboveThreshold(String commodity, int threshold) { 
        int commIndex = commodityChange(commodity);
        if (commIndex == -1) {
            return -1;
        }

        int count = 0;
        for (int m = 0; m < MONTHS; m++) {
            for (int d = 1; d <= DAYS; d++) {
                if (infoData[m][d][commIndex] > threshold) {
                    count++;
                }
            }
        }
        return count;
    }
// 8
    public static int biggestDailySwing(int month) {
        return 1234;
    }
// 9
    public static String compareTwoCommodities(String c1, String c2) {
        return "DUMMY is better by 1234";
    }
// 10
    public static String bestWeekOfMonth(int month) {
        return "DUMMY";
    }

    public static void main(String[] args) {
        loadData();
        System.out.println("Data loaded – ready for queries");
    }
}
