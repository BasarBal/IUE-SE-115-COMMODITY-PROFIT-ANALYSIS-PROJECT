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

    public static int totalProfitOnDay(int month, int day) {
        
        if (month < 0 || month >= MONTHS || day < 1 || day > DAYS) return -99999;

        int total = 0;
        
        for (int i = 0; i < COMMS; i++) {
            total += infoData[month][day][i];
        }
        return total;
    }

    public static int commodityProfitInRange(String commodity, int from, int to) { 
        int commIndex = commodityChange(commodity);
        if (commIndex == -1 || from > to || from < 1 || to > DAYS) {
            return -99999;
        }

        int total = 0;

        for (int m = 0; m < MONTHS; m++) {

            for (int d = from; d <= to; d++) {
                total += infoData[m][d][commIndex];
            }
        }
        return total;
    }

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

    public static String bestMonthForCommodity(String comm) {
        return "DUMMY";
    }

    public static int consecutiveLossDays(String comm) {
        return 1234;
    }

    public static int daysAboveThreshold(String comm, int threshold) {
        return 1234;
    }

    public static int biggestDailySwing(int month) {
        return 1234;
    }

    public static String compareTwoCommodities(String c1, String c2) {
        return "DUMMY is better by 1234";
    }

    public static String bestWeekOfMonth(int month) {
        return "DUMMY";
    }

    public static void main(String[] args) {
        loadData();
        System.out.println("Data loaded – ready for queries");
    }
}
