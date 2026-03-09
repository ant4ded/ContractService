package my.idp.spring.contract.service;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class HelloComponent {

	private final String helloString;

	public HelloComponent(String helloString) {
		this.helloString = helloString;
		System.out.println(helloString);
	}

	@PostConstruct
	private void init(){
		System.out.println(helloString);
	}
}
