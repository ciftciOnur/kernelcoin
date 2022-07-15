package tr.edu.yeditepe.kernelcoin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.ZoneId;
import java.util.TimeZone;

@SpringBootApplication

public class KernelCoinApplication {
    public static void main(String[] args) {
        SpringApplication.run(KernelCoinApplication.class, args);
    }

    @Bean
    public void setTimeZone() {
        TimeZone.setDefault(TimeZone.getTimeZone(ZoneId.of("Europe/Istanbul")));
    }

}