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
    // ======== REQUIRED METHOD LOAD DATA (Students fill this) ========
    public static void loadData() {
        for (int monthIndex = 0; monthIndex < MONTHS; monthIndex++) {
            String fileName = "Data_Files/" + months[monthIndex] + ".txt";
            File dataFile = new File(fileName);

            try (Scanner fileScanner = new Scanner(dataFile)) {
                if (fileScanner.hasNextLine()) {
                    fileScanner.nextLine();
                }

                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();
                    if (line.trim().isEmpty()) continue;
                    String[] lineParts = line.split(",");

                    if (lineParts.length == 3) {
                        try {
                            int dayNumber = Integer.parseInt(lineParts[0].trim());
                            String commodityName = lineParts[1].trim();
                            int profitValue = Integer.parseInt(lineParts[2].trim());

                            int commodityIndex = -1;
                            for (int i = 0; i < COMMS; i++) {
                                if (commodities[i].equalsIgnoreCase(commodityName)) {
                                    commodityIndex = i;
                                    break;
                                }
                            }

                            if (commodityIndex != -1 && dayNumber >= 1 && dayNumber <= DAYS) {
                                profitMatrix[monthIndex][dayNumber][commodityIndex] = profitValue;
                            }
                        } catch (NumberFormatException e) {
                            System.err.println("Hatalı veri formatı atlandı: " + line);
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println(months[monthIndex] + ".txt dosyası bulunamadı.");
            }
        }
    }

    // ======== 10 REQUIRED METHODS (Students fill these) ========
    public static String mostProfitableCommodityInMonth(int month){
        if (month < 0 || month >= MONTHS) {
            return "INVALID_MONTH";
        }

        int maxProfit = -99999999;
        int bestComm = 0;

        for (int i = 0; i < COMMS; i++) {
            int total = 0;
            for (int day = 1; day <= DAYS; day++) {
                total += profitMatrix[month][day][i];
            }
            if (total > maxProfit) {
                maxProfit = total;
                bestComm = i;
            }
        }
        return commodities[bestComm] + " " + maxProfit;
    }

    public static int totalProfitOnDay(int month, int day) {
        if (month < 0 || month >= MONTHS || day < 1 || day > DAYS) {
            return -99999;
        }

        int sum = 0;
        for (int i = 0; i < COMMS; i++) {
            sum += profitMatrix[month][day][i];
        }
        return sum;
    }

    public static int commodityProfitInRange(String commodity, int from, int to) {
        int connIndex = getCommodityOfIndex(commodity);
        if (connIndex == -1 || from < 1 || to > DAYS || from > to) {
            return -99999;
        }

        int total = 0;
        for (int m = 0; m < MONTHS; m++) {
            for (int d = from; d <= to; d++) {
                total += profitMatrix[m][d][connIndex];
            }
        }
        return total;
    }

    public static int bestDayOfMonth(int month) {
        if (month < 0 || month >= MONTHS) {
            return -1;
        }

        int highest = -99999999;
        int bestDay = 1;

        for (int d = 1; d <= DAYS; d++) {
            int dailySum = 0;
            for (int i = 0; i < COMMS; i++) {
                dailySum += profitMatrix[month][d][i];
            }
            if (dailySum > highest) {
                highest = dailySum;
                bestDay = d;
            }
        }
        return bestDay;
    }

    public static String bestMonthForCommodity(String comm) {
        int commIndex = getCommodityOfIndex(comm);
        if (commIndex == -1) {
            return "INVALID_COMMODITY";
        }

        int maxMonthProfit = -99999999;
        int bestMonth = 0;

        for (int m = 0; m < MONTHS; m++) {
            int monthTotal = 0;
            for (int d = 1; d <= DAYS; d++) {
                monthTotal += profitMatrix[m][d][commIndex];
            }
            if (monthTotal > maxMonthProfit) {
                maxMonthProfit = monthTotal;
                bestMonth = m;
            }
        }
        return months[bestMonth];
    }

    public static int consecutiveLossDays(String comm) {
        int commIndex = getCommodityOfIndex(comm);
        if (commIndex == -1) {
            return -1;
        }

        int maxStreak = 0;
        int currentStreak = 0;

        for (int m = 0; m < MONTHS; m++) {
            for (int d = 1; d <= DAYS; d++) {
                if (profitMatrix[m][d][commIndex] < 0) {
                    currentStreak++;
                } else {
                    if (currentStreak > maxStreak) {
                        maxStreak = currentStreak;
                    }
                    currentStreak = 0;
                }
            }
        }
        if (currentStreak > maxStreak){
            maxStreak = currentStreak;
        }
        return maxStreak;
    }

    public static int daysAboveThreshold(String comm, int threshold) {
        int commIndex = getCommodityOfIndex(comm);
        if (commIndex == -1) {
            return -1;
        }

        int matches = 0;
        for (int m = 0; m < MONTHS; m++) {
            for (int d = 1; d <= DAYS; d++) {
                if (profitMatrix[m][d][commIndex] > threshold) {
                    matches++;
                }
            }
        }
        return matches;
    }

    public static int biggestDailySwing(int month) {
        if (month < 0 || month >= MONTHS) {
            return -99999;
        }

        int maxDifference = 0;
        for (int d = 1; d < DAYS; d++) {
            int difference = totalProfitOnDay(month, d) - totalProfitOnDay(month, d + 1);
            if (difference < 0) {
                difference = -difference;
            }

            if (difference > maxDifference) {
                maxDifference = difference;
            }
        }
        return maxDifference;
    }

    public static String compareTwoCommodities(String c1, String c2) {
        int index1 = getCommodityOfIndex(c1);
        int index2 = getCommodityOfIndex(c2);
        if (index1 == -1 || index2 == -1) {
            return "INVALID_COMMODITY";
        }

        long total1 = 0;
        long total2 = 0;

        for (int m = 0; m < MONTHS; m++) {
            for (int d = 1; d <= DAYS; d++) {
                total1 += profitMatrix[m][d][index1];
                total2 += profitMatrix[m][d][index2];
            }
        }

        if (total1 > total2) {
            return c1 + " is better by " + (total1 - total2);
        }
        else if (total2 > total1) {
            return c2 + " is better by " + (total2 - total1);
        }
        else {
            return "Equal";
        }
    }

    public static String bestWeekOfMonth(int month) {
        if (month < 0 || month >= MONTHS) {
            return "INVALID_MONTH";
        }

        int maxWeekProfit = -99999999;
        int bestWeek = 1;

        for (int w = 1; w <= 4; w++) {
            int weekTotal = 0;
            int start = (w - 1) * 7 + 1;
            int end = w * 7;

            for (int d = start; d <= end; d++) {
                weekTotal += totalProfitOnDay(month, d);
            }

            if (weekTotal > maxWeekProfit) {
                maxWeekProfit = weekTotal;
                bestWeek = w;
            }
        }
        return "Week " + bestWeek;
    }

    public static void main(String[] args) {
        loadData();
        System.out.println("Data loaded – ready for queries");
    }   
}
