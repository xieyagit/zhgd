package com.hujiang;



import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 启动程序
 * 
 * @author ruoyi
 */

@EnableJms //启动消息队列
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@EnableFeignClients(basePackages = {"com.hujiang"})
@EnableTransactionManagement
@EnableScheduling
@EnableEurekaClient
@MapperScan({"com.hujiang.project.*.*.mapper","com.hujiang.project.*.*.*.mapper"})
public class ProviderApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(ProviderApplication.class, args);
        System.out.println(
                "  .--,       .--,\n" +
                "  ( (  \\.---./  ) )\n" +
                "   '.__/o   o\\__.'\n" +
                "      {=  ^  =}\n" +
                "       >  -  <\n" +
                "      /       \\\n" +
                "     //       \\\\\n" +
                "    //|   .   |\\\\\n" +
                "    \"'\\       /'\"_.-~^`'-.\n" +
                "       \\  _  /--'         `启动成功\n" +
                "     ___)( )(___\n" +
                "    (((__) (__)))");

    }
}