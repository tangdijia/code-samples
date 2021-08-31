package cn.com.tdj.spark.plugin;

import org.apache.spark.api.plugin.DriverPlugin;
import org.apache.spark.api.plugin.ExecutorPlugin;
import org.apache.spark.api.plugin.SparkPlugin;

import java.io.Serializable;

/**
 * @author tangdijia
 * @date 2021/8/26
 */
public class UdSparkPlugin implements SparkPlugin, Serializable {

    @Override
    public DriverPlugin driverPlugin() {
        return new UdDriverPlugin();
    }

    @Override
    public ExecutorPlugin executorPlugin() {
        return new UdExecutorPlugin();
    }
}
