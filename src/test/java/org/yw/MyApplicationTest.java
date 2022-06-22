package org.yw;

import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.apache.camel.test.spring.MockEndpoints;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;


@RunWith(CamelSpringBootRunner.class)
@SpringBootTest
@MockEndpoints("file:sapoutput")
public class MyApplicationTest {

	@Autowired
	private ProducerTemplate template;

	@EndpointInject("mock:file:sapoutput")
	MockEndpoint mock_file;

	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Test
	public void testReceive() throws Exception {
		String message_body="ABAP RFC TEST";
		
		mock_file.expectedBodiesReceived(message_body);
		template.sendBody("direct:saprfc", message_body);
		mock_file.assertIsSatisfied();
		

	}

}
