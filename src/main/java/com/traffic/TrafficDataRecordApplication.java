package com.traffic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@MapperScan("com.traffic.mapper")
@SpringBootApplication
public class TrafficDataRecordApplication {

    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext application = SpringApplication.run(TrafficDataRecordApplication.class, args);
        Environment env = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        String property = env.getProperty("server.servlet.context-path");
        String path = property == null ? "" :  property;
        System.out.println(
                "\n\t" +
                        "----------------------------------------------------------\n\t" +
                        "Application traffic-data-record is running! Access URLs:\n\t" +
                        "Local: \t\thttp://localhost:" + port + path + "/\n\t" +
                        "doc: \t\thttp://localhost:" + port + path + "/doc.html" + "\n\t" +
                        "External: \thttp://" + ip + ":" + port + path + "/\n\t" +
                        "index:    \thttp://" + ip + ":" + port + path + "/templates/index.html\n\t" +
                        "------------------------------------------------------------");

    }


}
