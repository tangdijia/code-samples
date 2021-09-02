package cn.com.tdj.bf;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.nio.charset.Charset;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * int insertions = 50000000;
 * double fpp = 0.00001;
 * <p>
 * step 1 cost: 112109
 * step 2 cost: 10702
 * 在 <已存在> 布隆过滤器中的5000000条数据中，布隆过滤器认为存在的数量为：5000000
 * step 3 cost: 9105
 * 在 <不存在> 布隆过滤器中的10000000条数据中，布隆过滤器认为存在的数量为：93
 * step 4 cost: 5370
 * 命中率为：100%，误判率为：0.00093%
 * all cost: 137439
 */
public class GuavaBloomFilterExample {

    public static void main(String[] args) {
        long start_ = System.currentTimeMillis();
        // 创建布隆过滤器，设置存储的数据类型，预期数据量，误判率 (必须大于0，小于1)
        int insertions = 10000000;
        double fpp = 0.00001;
        BloomFilter<String> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charset.defaultCharset()), insertions, fpp);

        long start = System.currentTimeMillis();
        // 随机生成数据，并添加到布隆过滤器中（将预期数据量全部塞满）
        // 同时也创建一个List集合，将布隆过滤器中预期数据的十分之一存储到该List中
        List<String> lists_1 = new ArrayList<String>();
        for (int i = 0; i < insertions; i++) {
            String uid = UUID.randomUUID().toString();
            bloomFilter.put(uid);
            if (i < insertions / 10) {
                lists_1.add(uid);
            }
        }
        System.out.println("step 1 cost: " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        // 再创建一个List集合，用来存储另外 五分之一 不存在布隆过滤器中的数据
        List<String> lists_2 = new ArrayList<String>();
        for (int i = 0; i < insertions / 5; i++) {
            String uid = UUID.randomUUID().toString();
            lists_2.add(uid);
        }
        System.out.println("step 2 cost: " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        // 对已存在布隆过滤器中的lists_1中的数据进行判断，看是否在布隆过滤器中
        int result_1 = 0;
        for (String s : lists_1) {
            if (bloomFilter.mightContain(s)) result_1++;
        }
        System.out.println("在 <已存在> 布隆过滤器中的" + lists_1.size() + "条数据中，布隆过滤器认为存在的数量为：" + result_1);
        System.out.println("step 3 cost: " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        // 对不存在布隆过滤器中的lists_2中的数据进行判断，看是否在布隆过滤器中
        int result_2 = 0;
        for (String s : lists_2) {
            if (bloomFilter.mightContain(s)) {
                if (bloomFilter.mightContain(s)) {
                    System.err.println("a->" + s);
                    result_2++;
                } else {
                    System.err.println("b->" + s);
                }
            }
        }
        System.out.println("在 <不存在> 布隆过滤器中的" + lists_2.size() + "条数据中，布隆过滤器认为存在的数量为：" + result_2);
        System.out.println("step 4 cost: " + (System.currentTimeMillis() - start));

        // 对数据进行整除，求出百分率
        NumberFormat percentFormat = NumberFormat.getPercentInstance();
        percentFormat.setMaximumFractionDigits(10);
        float percent = (float) result_1 / lists_1.size();
        float bingo = (float) result_2 / lists_2.size();
        System.out.println("命中率为：" + percentFormat.format(percent) + "，误判率为：" + percentFormat.format(bingo));
        System.out.println("all cost: " + (System.currentTimeMillis() - start_));
    }
}
