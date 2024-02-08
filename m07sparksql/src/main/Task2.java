package main;

import main.HomeworkTask;

public class Task2 {

    public static void main(String[] args) {
        HomeworkTask task2 = new HomeworkTask();
        task2.execute(session -> {
            Dataset<Row> topBusyHotelsByMonth = session.sql(
                    "SELECT id, name, month, year, COUNT(DISTINCT visit_date) AS visit_count\n" +
                            "FROM (\n" +
                            "   SELECT h.id, h.address AS name, MONTH(v.visit_date) AS month, YEAR(v.visit_date) AS year, v.visit_date\n" +
                            "   FROM hotels h\n" +
                            "   JOIN visits v ON h.id = v.hotel_id\n" +
                            ")\n" +
                            "GROUP BY id, name, month, year\n" +
                            "ORDER BY year, month, visit_count DESC"
            );

            log.info("Execution plan: ");
            topBusyHotelsByMonth.explain("formatted");

            log.info("Top 10 busy hotels for each month are: ");
            topBusyHotelsByMonth.show(false);
        });
    }
}