package org.caringbridge.ui.audits.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.caringbridge.ui.audits.CbApplication;
import org.caringbridge.ui.audits.controllers.PingController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


public class PingControllerTest extends BaseControllerTest {

	private MockMvc mockMvc;
	
	@Autowired
	private PingController mc;

	private final String PROVIDER_ID = "49V843F";

	@Before
	public void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(mc).build();
	}

	@Test
	public void pingControllerShouldReturnRightData() throws Exception {

		RequestBuilder mockGet = MockMvcRequestBuilders.get("/ui/audits/ping");

		ResultActions response = mockMvc.perform(mockGet);
		response.andDo(MockMvcResultHandlers.print()).andExpect(status().is(200))
				.andExpect(content().contentType("application/json"));
	}

}
