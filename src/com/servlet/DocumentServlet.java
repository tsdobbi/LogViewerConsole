package com.servlet;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.util.ProcessingUtil;

/**
 * Servlet implementation class Log Viewer Console
 */
@WebServlet("/DocumentServletController")
public class DocumentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DocumentServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String logContent = null;
		String logFile = (String) request.getSession().getAttribute("sessionLogFile");
		String url = (String) request.getSession().getAttribute("sessionUrl");
		String dir = (String) request.getSession().getAttribute("sessionDir");
		ProcessingUtil util = ProcessingUtil.getInstance();
		if ((logFile != null && !logFile.isEmpty()) && !logFile.equals("Select Log")) {
			dir = util.encodeDir(dir);
			logContent = util.retrieveLog(logFile, url, dir);
		}

		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + logFile + "\"");

		PrintWriter out = response.getWriter();
		InputStream fileInputStream = new ByteArrayInputStream(logContent.getBytes(StandardCharsets.UTF_8));

		int i;
		while ((i = fileInputStream.read()) != -1) {
			out.write(i);
		}
		fileInputStream.close();
		out.close();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
