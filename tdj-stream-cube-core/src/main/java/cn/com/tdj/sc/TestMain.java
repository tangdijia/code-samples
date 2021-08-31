package cn.com.tdj.sc;

import cn.com.bsfit.sc.build.AbstractVarTask;
import cn.com.bsfit.sc.build.VarBuilder;
import cn.com.bsfit.sc.oper.AvgNumber;
import cn.com.bsfit.sc.oper.CubeRecord;
import cn.com.bsfit.sc.oper.MaxNumber;
import cn.com.bsfit.sc.oper.SumNumber;
import cn.com.tdj.sc.obj.PayOrder;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 */
public class TestMain {

    public static void main(String[] args) {

        List<AbstractVarTask> tasks = new ArrayList<>();

        tasks.add(VarBuilder.feature("同一卡最近一段时间的累计交易额", PayOrder.class)
                .prefix("BANK")
                .key(PayOrder::getCardNo)
                .ts("5h") //合并
                .tw("5m") //不合并
                .time(PayOrder::getTime)
                .apply((order) -> new SumNumber(order.getAmount()))
                .build());

        tasks.add(VarBuilder.feature("同一卡最近一段时间的最大交易额", PayOrder.class)
                .prefix("BANK")
                .key(PayOrder::getCardNo)
                .ts("5h") //合并
                .tw("5m") //不合并
                .time(PayOrder::getTime)
                .apply((order) -> new MaxNumber(order.getAmount()))
                .build());

        Map<Class<?>, List<Object>> map = new HashMap<>();
        map.put(PayOrder.class, getTestObject());

        for (AbstractVarTask task : tasks) {
            task.setObjects(map);
        }

        Map<String, List<CubeRecord>> cube = new HashMap<>();

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        try {
            List<Future<Set<CubeRecord>>> futures = executorService.invokeAll(tasks);
            for (Future<Set<CubeRecord>> future : futures) {
                Set<CubeRecord> cubeRecordSet = future.get();
                for (CubeRecord cubeRecord : cubeRecordSet) {
                    String key = cubeRecord.getPrefix() + "_" + cubeRecord.getPrimaryKey();
                    List<CubeRecord> list = cube.get(key) == null ? new ArrayList<>() : cube.get(key);
                    list.add(cubeRecord);
                    cube.put(key, list);
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("over");
    }

    private static List<Object> getTestObject() {
        List<Object> events = new ArrayList<>();
        //yyyy-MM-dd HH:mm:ss
        events.add(new PayOrder(0, "card_001", 100L, "2021-05-02 00:10:11"));
        events.add(new PayOrder(1, "card_001", 100L, "2021-05-02 01:12:12"));
        events.add(new PayOrder(2, "card_001", 200L, "2021-05-02 02:12:12"));
        events.add(new PayOrder(3, "card_001", 300L, "2021-05-02 03:12:12"));
        events.add(new PayOrder(4, "card_001", 400L, "2021-05-02 04:12:12"));
        events.add(new PayOrder(5, "card_001", 500L, "2021-05-02 06:01:12"));
        events.add(new PayOrder(6, "card_001", 600L, "2021-05-02 06:12:12"));
        events.add(new PayOrder(7, "card_001", 700L, "2021-05-02 06:12:15"));
        events.add(new PayOrder(8, "card_002", 650L, "2021-05-02 06:12:16"));
        return events;
    }

}
