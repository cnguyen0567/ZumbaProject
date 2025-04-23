package com.project.zumba.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class FrontControllerServlet
 */
@WebServlet({ "/FrontControllerServlet", "/FrontController" })
public class FrontControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FrontControllerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Inside FrontControllerServlet.serive()");
		String requestType = request.getParameter("type");

		RequestDispatcher dispatcher = null;

		// requestType = value of name = "type" for each form
		if ("signIn".equals(requestType) || "registerParticipant".equals(requestType) || "updateParticiapnt".equals(requestType)) {
			dispatcher = request.getRequestDispatcher("Participant"); 
		} else if ("createBatch".equals(requestType) || "updateBatch".equals(requestType) || "deleteBatch".equals(requestType)) {
			dispatcher = request.getRequestDispatcher("Batch");
		} else if ("createEnrollment".equals(requestType)) {
			dispatcher = request.getRequestDispatcher("Enrollment"); 
		} else if ("getAllBatches".equals(requestType)) {
			dispatcher = request.getRequestDispatcher("Batch"); 
		} else {
			dispatcher = request.getRequestDispatcher("error");
		}
		dispatcher.forward(request, response);
	}

}
