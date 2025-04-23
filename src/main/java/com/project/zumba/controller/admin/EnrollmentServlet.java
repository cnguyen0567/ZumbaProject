package com.project.zumba.controller.admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.project.zumba.model.Enrollment;
import com.project.zumba.service.IEnrollmentService;
import com.project.zumba.service.EnrollmentServiceImpl;

/**
 * Servlet implementation class EnrollmentServlet
 */
@WebServlet({ "/EnrollmentServlet", "/Enrollment" })
public class EnrollmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	private IEnrollmentService enrollmentService;

	public EnrollmentServlet() {
		super();
		enrollmentService = new EnrollmentServiceImpl();
	}
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type = request.getParameter("type");
		System.out.println("Inside EnrollmentServlet.doGet()" + type + " type");

		if ("getEnrollment".equals(type)) {
			System.out.println("Start EnrollmentServlet - Get");
			Enrollment retrievedEnrollment = null; // declare a variable to hold the info
			try {
				retrievedEnrollment = enrollmentService.getEnrollment(Integer.parseInt(request.getParameter("id")));
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if ("getAllEnrollment".equals(type)) {
			System.out.println("Start EnrollmentServlet - GetAll");

		} else {
			System.out.println("Invalid EnrollmentServlet Call");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Start EnrollmentServlet.doPost() with " + request.getParameter("type") + " type");
		String type = request.getParameter("type");

		if ("createEnrollment".equals(type) || "registerEnrollment".equals(type)) {
			System.out.println("Start EnrollmentServlet - Create");

			Enrollment enrollmentObj = loadObject(request);

			// send to DB & output success/fail results
			try {
				enrollmentService.saveEnrollment(enrollmentObj);

				request.setAttribute("successCreateEnrollmentMessage", "Enrollment registered successfully! Thank You!");
				request.setAttribute("enrollment", enrollmentObj);

				// Forward the request to the JSP for rendering the view
				RequestDispatcher dispatcher = request.getRequestDispatcher("/dashboard-admin.jsp");
				dispatcher.forward(request, response);
			} catch (Exception e) {
				System.out.println("Create failure!: ");
				e.printStackTrace();
			}

		} else if ("updateEnrollment".equals(type)) {
			System.out.println("Start EnrollmentServlet - Update");
			// update an existing category in db
			Enrollment enrollmentUpdateObj = loadObject(request);
			try {
				// the appropriate to UPDATE the category obj created above
				enrollmentService.updateEnrollment(enrollmentUpdateObj);
				System.out.println(enrollmentUpdateObj);

				// setting an additional attribute for the end user
				request.setAttribute("successUpdateEnrollmentMessage", "Enrollment obj updated sucessfully!");
				// Forward the request to the JSP for rendering the view
				RequestDispatcher dispatcher = request.getRequestDispatcher("/dashboard-admin.jsp");
				dispatcher.forward(request, response);
			} catch (Exception e) {
				System.out.println("Update failure!: ");
				e.printStackTrace();
			}
		} else if ("deleteEnrollment".equals(type)) {
			System.out.println("Start EnrollmentServlet - Delete");
			try {
				System.out.println(request.getParameter("enrollmentId"));
				enrollmentService.deleteEnrollment(Integer.parseInt(request.getParameter("enrollmentId")));

				// setting an additional attribute for the end user
				request.setAttribute("successDeleteEnrollmentMessage", "Enrollment obj + associate enrollments deleted sucessfully!");
				// Forward the request to the JSP for rendering the view
				RequestDispatcher dispatcher = request.getRequestDispatcher("/others.jsp");
				dispatcher.forward(request, response);
			} catch (Exception e) {
				System.out.println("Delete failure!: ");
				e.printStackTrace();
			}
		}
	}

	private Enrollment loadObject(HttpServletRequest request) {
		// Fields/Variables:
		System.out.println("load obj: " + request.getMethod());
		Enrollment enrollment = new Enrollment();

		// update the enrollment object with appropriate field values
		// drawn from the HttpServletRequest
		// Call the setters methods:
		if (request.getParameter("enrollmentId") != null) {
			String enrollmentId = request.getParameter("enrollmentId"); // getParameter returns a string, need to convert
			enrollment.setEnrollmentId(Integer.parseInt(enrollmentId));
		}
		if (request.getParameter("enrollmentBatchId") != null) {
			String batchId = request.getParameter("enrollmentBatchId"); // getParameter returns a string, need to convert
			enrollment.setBatchId(Integer.parseInt(batchId));
		}
		if (request.getParameter("enrollmentParticipantId") != null) {
			String participantId = request.getParameter("enrollmentParticipantId"); // getParameter returns a string, need to convert
			enrollment.setParticipantId(Integer.parseInt(participantId));
		}
		return enrollment;
	}

}


