package cn.com.demo.parquet.controller;

import cn.com.demo.parquet.config.HadoopConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.apache.parquet.example.data.Group;
import org.apache.parquet.hadoop.ParquetReader;
import org.apache.parquet.hadoop.example.GroupReadSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 */
@RestController
@Slf4j
public class ParquetController {

    @Autowired
    private HadoopConfig hadoopConfig;

    @RequestMapping("/parquet/view")
    public String parquetView(String parquetFilePath) {
        log.info("parquetView start");
        Configuration configuration = hadoopConfig.getConfiguration();
        FileSystem fileSystem = null;
        try {
            fileSystem = FileSystem.get(configuration);

            boolean exists = fileSystem.exists(new Path(parquetFilePath));
            if (!exists) {
                //TODO
            }
            RemoteIterator<LocatedFileStatus> listFiles = fileSystem.listFiles(new Path(parquetFilePath), true);
            List<HashMap<String, String>> sampleData = new ArrayList<>();
            while (listFiles.hasNext()) {
                LocatedFileStatus fileStatus = listFiles.next();
                Path filePath = fileStatus.getPath();
                ParquetReader<Group> reader = null;
                try {
                    reader = ParquetReader.builder(new GroupReadSupport(), filePath).withConf(configuration).build();
                    for (Group group = reader.read(); group != null; group = reader.read()) {
                        HashMap<String, String> record = new HashMap<>();
                        for (int i = 0; i < group.getType().getFields().size(); i++) {
                            String name = group.getType().getFields().get(i).getName();
                            String value = group.getValueToString(i, 0);
                            record.put(name, value);
                        }
                        sampleData.add(record);
                    }
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                } finally {
                    reader.close();
                }
            }
        } catch (IOException e1) {
            log.error(e1.getMessage(), e1);
            if (fileSystem != null) {
                try {
                    fileSystem.close();
                } catch (IOException e2) {
                    log.error(e2.getMessage(), e2);
                }
            }
        }
        return null;
    }
}
