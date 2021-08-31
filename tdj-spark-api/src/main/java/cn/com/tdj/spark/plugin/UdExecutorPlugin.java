package cn.com.tdj.spark.plugin;

import org.apache.spark.TaskFailedReason;
import org.apache.spark.api.plugin.ExecutorPlugin;
import org.apache.spark.api.plugin.PluginContext;

import java.util.Map;

/**
 * @author tangdijia
 * @date 2021/8/26
 */
public class UdExecutorPlugin implements ExecutorPlugin {
    @Override
    public void init(PluginContext ctx, Map<String, String> extraConf) {
        System.out.println("UdExecutorPlugin -> init");
        ExecutorPlugin.super.init(ctx, extraConf);
    }

    @Override
    public void shutdown() {
        System.out.println("UdExecutorPlugin -> shutdown");
        ExecutorPlugin.super.shutdown();
    }

    @Override
    public void onTaskStart() {
        System.out.println("UdExecutorPlugin -> onTaskStart");
        ExecutorPlugin.super.onTaskStart();
    }

    @Override
    public void onTaskSucceeded() {
        System.out.println("UdExecutorPlugin -> onTaskSucceeded");
        ExecutorPlugin.super.onTaskSucceeded();
    }

    @Override
    public void onTaskFailed(TaskFailedReason failureReason) {
        System.out.println("UdExecutorPlugin -> onTaskFailed");
        ExecutorPlugin.super.onTaskFailed(failureReason);
    }
}
