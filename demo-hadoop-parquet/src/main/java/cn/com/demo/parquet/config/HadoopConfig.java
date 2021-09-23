package cn.com.demo.parquet.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;

/**
 *
 */
@Slf4j
@Component
public class HadoopConfig {

    @Value("${hadoop.config.path: conf/}")
    private String hadoopConfigPath;

    private Configuration configuration;

    @PostConstruct
    public void init() {
        log.info("初始化 hadoop config 开始......");
        log.info("hadoop.config.path: {}", hadoopConfigPath);
        configuration = new Configuration();

        File confDir = new File(hadoopConfigPath);
        if (!confDir.exists()) {
            log.error("{} is not exist.", hadoopConfigPath);
        }

        File[] confFiles = confDir.listFiles();

        for (File confFile : confFiles) {
            String confFilePath = confFile.getAbsolutePath();
            log.info("{}", confFilePath);
            configuration.addResource(new Path(confFilePath));
            log.info("load [{}] to hadoop configuration", confFilePath);
        }
        log.info("初始化 hadoop config 完成......");
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
