package com.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.LogFile;
import com.util.ProcessingUtil;


/**
 * Servlet implementation class Log Viewer Console
 */
@WebServlet("/LogViewerConsole")
public class LogViewerController
    extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogViewerController() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	ProcessingUtil util = ProcessingUtil.getInstance();
    	List<LogFile> logList = null;
    	String logContent = null;
    	String encodedDir = null;
    	String url = request.getParameter("url"); 
    	String dir = request.getParameter("dir"); 
    	String logFile = request.getParameter("logfilelist");
    	
    	//retrieve loglist if url is passed.
    	if ((url != null  && !url.isEmpty()) && (dir != null  && !dir.isEmpty()))  {
    		encodedDir = util.encodeDir(dir);
    		logList = util.retrieveLogList(url, encodedDir);
    	}
    	//retrieve log content if logfile name is passed.
    	if ((logFile != null  && !logFile.isEmpty()) && !logFile.equals("Select Log"))  {
    		logContent = util.retrieveLog(logFile, url, encodedDir);
    		logContent = util.changeNL2Break(logContent);
    		logList = util.setSelected(logFile, logList);
    	}
    	
    	request.setAttribute("logList", logList);
		request.setAttribute("url", url);
		request.setAttribute("dir", dir);
		request.setAttribute("logContent", logContent);
		request.getSession().setAttribute("sessionLogFile", logFile);
		request.getSession().setAttribute("sessionUrl", url);
		request.getSession().setAttribute("sessionDir", dir);
        RequestDispatcher view = request.getRequestDispatcher("LogViewerMain.jsp");
        view.forward(request, response);

    }

}
