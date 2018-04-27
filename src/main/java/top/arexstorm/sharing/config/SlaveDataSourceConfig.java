package top.arexstorm.sharing.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(value = "slave.datasource")
public class SlaveDataSourceConfig extends BaseDataSourceConfig {
}
