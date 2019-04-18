package com.util;

import java.util.ArrayList;
import java.util.List;

import com.agent.client.AgentClient;
import com.model.LogFile;

public class ProcessingUtil {
	private static ProcessingUtil plObj;
	AgentClient agentClient = null;

	private ProcessingUtil() {
		agentClient = new AgentClient();
		agentClient.init();

	}

	public static ProcessingUtil getInstance() {
		if (plObj == null)
			plObj = new ProcessingUtil();
		return plObj;
	}

	public List<LogFile> retrieveLogList(String url, String dir) {

		ArrayList<String> logList = (ArrayList<String>) agentClient.getLogList(url, dir);
		List<LogFile> logfiles = new ArrayList<LogFile>();
		LogFile logfile = null;
		for (String log : logList) {
			logfile = new LogFile();
			logfile.setName(log);
			logfiles.add(logfile);

		}
		logfile = new LogFile();
		logfile.setName("Select Log");
		logfiles.add(0, logfile);

		return logfiles;

	}

	public String retrieveLog(String logFile, String url, String dir) {

		String logContent = agentClient.getLog(url, logFile, dir);

		return logContent;

	}

	public List<LogFile> setSelected(String logFile, List<LogFile> logFiles) {

		for (int i = 0; i < logFiles.size(); i++) {
			if (logFiles.get(i).getName().equals(logFile)) {
				logFiles.get(i).setSelected("selected");
			}

		}

		return logFiles;

	}

	public String encodeDir(String dir) {

		dir = dir.replace("/", "%2F");
		dir = dir.replace("//", "%2F");
		dir = dir.replace("\"", "%2F");
		dir = dir.replace("\\", "%2F");

		return dir;

	}

	public String changeNL2Break(String logContent) {

		logContent = logContent.replaceAll("(\r\n|\n)", "<br />");

		return logContent;

	}

}
