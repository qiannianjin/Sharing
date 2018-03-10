package top.arexstorm.sharing;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages= {"top.arexstorm.sharing"})
@MapperScan(value="top.arexstorm.sharing.mapper")
public class SharingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SharingApplication.class, args);
	}
}
