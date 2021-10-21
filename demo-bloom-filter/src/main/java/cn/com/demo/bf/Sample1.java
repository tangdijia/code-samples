package cn.com.demo.bf;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.nio.charset.Charset;

/**
 *
 */
public class Sample1 {

    public static void main(String[] args) {

        // 创建布隆过滤器，设置存储的数据类型，预期数据量，误判率 (必须大于0，小于1)
        int insertions = 10000000;
        double fpp = 0.00001;

        BloomFilter<String> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charset.defaultCharset()), insertions, fpp);

        for (int i = 0; i < 100; i++) {
            bloomFilter.put(i + "");
        }
        boolean mightContain = bloomFilter.mightContain("2");

        System.out.println(mightContain);
    }
}
