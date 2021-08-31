package cn.com.tdj.spark.plugin;

import org.apache.spark.SparkContext;
import org.apache.spark.api.plugin.DriverPlugin;
import org.apache.spark.api.plugin.PluginContext;

import java.io.Serializable;
import java.util.Map;

/**
 * @author tangdijia
 * @date 2021/8/26
 */
public class UdDriverPlugin implements DriverPlugin, Serializable {

    @Override
    public Map<String, String> init(SparkContext sc, PluginContext pluginContext) {
        System.out.println("UdDriverPlugin -> init");
        return DriverPlugin.super.init(sc, pluginContext);
    }

    @Override
    public void registerMetrics(String appId, PluginContext pluginContext) {
        System.out.println("UdDriverPlugin -> registerMetrics");
        DriverPlugin.super.registerMetrics(appId, pluginContext);
    }

    @Override
    public Object receive(Object message) throws Exception {
        System.out.println("UdDriverPlugin -> receive" + message);
        return DriverPlugin.super.receive(message);
    }

    @Override
    public void shutdown() {
        System.out.println("UdDriverPlugin -> shutdown");
        DriverPlugin.super.shutdown();
    }
}
