package com.uusoft.atp.service.impl;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.dreamhead.moco.HttpServer;
import com.github.dreamhead.moco.Runner;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import static com.github.dreamhead.moco.Moco.httpServer;
import static com.github.dreamhead.moco.Runner.runner;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-application.xml")
public class testmoco {
	private Runner runner;
	
	@Before
	public void setup(){
		HttpServer server = httpServer(12306);
		server.response("foo");
		runner = runner(server);
		runner.start();		
	}
	
	@After
	public void tearDown(){
		runner.stop();
	}
	
	@Test
	public void should_response_as_expected() throws IOException{
		Content content = Request.Get("http://192.168.3.153:12306").execute().returnContent();
        assertThat(content.asString(), is("foo"));	
	}

}
