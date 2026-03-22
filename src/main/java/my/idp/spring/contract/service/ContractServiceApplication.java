package my.idp.spring.contract.service;

import my.idp.spring.currency.rate.starter.dto.CurrencyRateDto;
import my.idp.spring.currency.rate.starter.service.CurrencyRateService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class ContractServiceApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(ContractServiceApplication.class, args);
		CurrencyRateService bean = context.getBean(CurrencyRateService.class);
		CurrencyRateDto rate = bean.getRate("EUR", "USD", LocalDate.now());
		System.out.println(rate);
	}

}
