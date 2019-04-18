package com.agent.client;

import java.util.List;
import java.util.Properties;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.util.PropertyLoader;

public class AgentClient {

	private Client client;
	private PropertyLoader propLoader = PropertyLoader.getInstance();
	private Properties props = propLoader.getProperties();
	static Logger log = Logger.getLogger(AgentClient.class.getName());

	public void init() {
		if (client == null) {
			this.client = ClientBuilder.newClient();
		}
	}

	public List<String> getLogList(String urlPrefix, String dir) {
		GenericType<List<String>> list = new GenericType<List<String>>() {
		};
		String url = urlPrefix + props.getProperty("log.list.url");
		List<String> logList = client.target(url).path("/{dir}").resolveTemplate("dir", dir).request(MediaType.APPLICATION_JSON).get(list);

		return logList;

	}

	public String getLog(String urlPrefix, String logName, String dir) {

		String url = urlPrefix + props.getProperty("log.retrieval.url");

		GenericType<String> log = new GenericType<String>() {
		};
		String logFile;

		logFile = client.target(url).path("/{name}/{dir}").resolveTemplate("name", logName).resolveTemplate("dir", dir)
				.request(MediaType.APPLICATION_JSON).get(log);
		
		return logFile;

	}

}