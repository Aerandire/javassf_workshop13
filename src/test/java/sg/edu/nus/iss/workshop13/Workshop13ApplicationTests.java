package sg.edu.nus.iss.workshop13;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import sg.edu.nus.iss.workshop13.controller.AddressBookController;

@SpringBootTest
class Workshop13ApplicationTests {

	@Autowired
	private AddressBookController controller;

	@Test
	void contextLoads() {
			assertThat(controller).isNotNull();
	}

}
