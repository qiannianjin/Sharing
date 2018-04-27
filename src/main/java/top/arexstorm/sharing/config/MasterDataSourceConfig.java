package top.arexstorm.sharing.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "master.datasource")
public class MasterDataSourceConfig extends  BaseDataSourceConfig{

}
