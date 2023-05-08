package com.example.restservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.beans.factory.annotation.Value;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class RestServiceApplicationTests {
	
	@Test
	public void contextLoads() {
		String template = "Hello, %s!";
		AtomicLong counter = new AtomicLong();
		List<Greeting> greetingsList = new List<>();

		GreetingController controller = new GreetingController();
		List<Greeting> greetings = new List<>();
		List<Greeting> groundTruth = new List<>();

		Greeting test1 = controller.postGreeting("test 1");
		groundTruth.add(new Greeting(counter.incrementAndGet(), String.format(template, "test 1")));
		greetings = controller.getAllGreetings();
		assertEquals(greetings, groundTruth);

		Greeting test2 = controller.postGreeting("test 2");
		groundTruth.add(new Greeting(counter.incrementAndGet(), String.format(template, "test 2")));
		greetings = controller.getAllGreetings();
		assertEquals(greetings, groundTruth);

		Greeting test3 = controller.postGreeting("test 3");
		groundTruth.add(new Greeting(counter.incrementAndGet(), String.format(template, "test 3")));
		greetings = controller.getAllGreetings();
		assertEquals(greetings, groundTruth);

		Greeting t4 = new Greeting();
		long idIn = counter.incrementAndGet();
		String contentIn = String.format(template, "test 3");
		t4.setId(idIn);
		t4.setContent(contentIn);
		long idOut = t4.getId();
		String contentOut = t4.getContent();
		assertEquals(idIn, idOut);
		assertEquals(contentIn, contentOut);
	}

}
