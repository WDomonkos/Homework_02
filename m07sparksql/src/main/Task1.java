package main;

import main.HomeworkTask;

public class Task1 {

    public static void main(String[] args) {
        HomeworkTask task1 = new HomeworkTask();
        task1.execute(session -> {
            Dataset<Row> topHotelWithMaxTempDiff = session.sql(
                    "SELECT id, name, ROUND(MAX(ABS(max - min)), 2) AS max_diff\n" +
                    "FROM (\n" +
                    "   SELECT id, address AS name, MIN(avg_tmpr_c) AS min, MAX(avg_tmpr_c) AS max\n" +
                    "   FROM hotels:weather\n" +
                    "   GROUP BY id, address, year, month\n" +
                    ")\n" +
                    "GROUP BY id, name\n" +
                    "ORDER BY max_diff DESC\n" +
                    "LIMIT 10"
            );

            Process log;
            log.info("Execution plan: ");
            topHotelWithMaxTempDiff.explain("formatted");

            log.info("Top 10 hotels with maximum abosulte temperature difference by month are: ");
            topHotelWithMaxTempDiff.show(false);
        });
    }
}