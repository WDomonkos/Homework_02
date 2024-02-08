package main.java;

public class Task3 {

    public static void main(String[] args) {
        HomeworkTask task3 = new HomeworkTask();
        task3.execute(session -> {
            // Selecting visits with extended stays (more than 7 days) and joining with weather data
            Dataset<Row> extendedStayVisits = session.sql(
                    "SELECT v.visit_id, v.hotel_id, v.visit_date, w.avg_tmpr_c\n" +
                            "FROM visits v\n" +
                            "JOIN weather w ON v.visit_date = w.date\n" +
                            "WHERE DATEDIFF(v.departure_date, v.visit_date) > 7"
            );

            // Calculate weather trend and average temperature during stay
            // Grouping by visit_id and hotel_id and aggregating relevant data
            Dataset<Row> weatherTrendAndAvgTemp = extendedStayVisits
                    .groupBy("visit_id", "hotel_id")
                    .agg(
                            functions.max("visit_date").alias("last_day"),
                            functions.min("visit_date").alias("first_day"),
                            functions.avg("avg_tmpr_c").alias("avg_temp")
                    )
                    // Calculating weather trend using window function
                    .withColumn("weather_trend", functions.abs(functions.col("avg_tmpr_c").minus(functions.lag("avg_tmpr_c", 1, 0).over(Window.partitionBy("hotel_id").orderBy("visit_date")))));

            log.info("Weather trend and average temperature during extended stays: ");
            weatherTrendAndAvgTemp.show(false);
        });
    }
}