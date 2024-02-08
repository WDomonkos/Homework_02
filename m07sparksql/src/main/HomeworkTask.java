package main;
public class HomeworkTask {
    public void execute(Consumer<SparkSession> logic) {
        SparkSession session = SparkSession .builder()
                .appName("Spark SQL homework")
                .getOrCreate();
        //loading tables
        Dataset<Row> hotelsWeatherDS = session.read()
                .parquet(System.getenv(INPUT_HOTELS_WEATHER_PATH));
        hotelsWeatherDS.createOrReplaceTempView("hotels_weather");
        Dataset<Row> expediaDS = session.read()
                .format("avro")
                .load(System.getenv(INPUT_EXPEDIA_PATH));
        expediaDS.createOrReplaceTempView("expedia");

        //custom logic
        logic.accept(session);
        session.stop();
    }
}


