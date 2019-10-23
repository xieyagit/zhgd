package com.hujiang.framework;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

@Resource
@Component
public class LocalSettingsEnvironmentPostProcessor implements EnvironmentPostProcessor {
    //private static final String LOCATION = "E:\\apache-tomcat-8.5.46-windows-x64\\apache-tomcat-8.5.46\\conf\\application.properties";


    @Override
    public void postProcessEnvironment(ConfigurableEnvironment configurableEnvironment, SpringApplication springApplication) {
        //tomcat路径
        String property = System.getProperty("catalina.home");
        System.out.println("catalinahome:"+property);
        String path =property+File.separator+"conf"+File.separator+"application.properties";
        File file = new File(path);
        System.out.println("Loading local settings from : 》》》》》》》》》》》》》》》》》》》》》》》"+path);
        if (file.exists()) {
            MutablePropertySources propertySources = configurableEnvironment.getPropertySources();
            Properties properties = loadProperties(file);

            //以外部配置文件为准
            propertySources.addFirst(new PropertiesPropertySource("Config", properties));
            //以application.yml文件为准
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
