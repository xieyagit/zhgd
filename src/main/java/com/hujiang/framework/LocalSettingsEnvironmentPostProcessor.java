package com.hujiang.framework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

@Resource
public class LocalSettingsEnvironmentPostProcessor implements EnvironmentPostProcessor {
    private static final String LOCATION = "E:\\application.properties";
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment configurableEnvironment, SpringApplication springApplication) {
        File file = new File(LOCATION);
        if (file.exists()) {
            MutablePropertySources propertySources = configurableEnvironment.getPropertySources();
            Properties properties = loadProperties(file);
            //两个配置文件加入程序环境的先后顺序，后面的会覆盖前面的
            //外部的文件最先导入
            propertySources.addFirst(new PropertiesPropertySource("Config", properties));
            //最后导入外部配置文件
//            propertySources.addLast(new PropertiesPropertySource("Config", properties));
        }
    }

    private Properties loadProperties(File f) {
        FileSystemResource resource = new FileSystemResource(f);
        try {
            return PropertiesLoaderUtils.loadProperties(resource);
        }
        catch (IOException ex) {
            throw new IllegalStateException("Failed to load local settings from " + f.getAbsolutePath(), ex);
        }
    }
}
