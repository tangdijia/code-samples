package cn.com.demo.parquet.controller;

import cn.com.demo.parquet.Application;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Slf4j
public class ParquetControllerTest {

    @Autowired
    private ParquetController parquetController;

    @Test
    public void parquetView() {
        parquetController.parquetView("hdfs://nameservice1/user/hive/warehouse/tdj.db/cc_test_data_t1_parquet");
    }
}