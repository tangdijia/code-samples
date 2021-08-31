package cn.com.tdj.spark;

import cn.com.tdj.spark.plugin.UdSparkPlugin;
import org.apache.spark.SparkConf;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;

/**
 * @author tangdijia
 * @date 2021/8/26
 */
public class Test {

    public static void main(String[] args) {
        SparkConf sparkConf = new SparkConf();
        sparkConf.set("spark.plugins", UdSparkPlugin.class.getName());

        SparkSession spark = SparkSession.builder()
                .master("local[*]")
                .config(sparkConf)
                .getOrCreate();
        Dataset<String> dataset = spark.read().textFile("src/main/resources/README.md");
        dataset.show();
    }
}
