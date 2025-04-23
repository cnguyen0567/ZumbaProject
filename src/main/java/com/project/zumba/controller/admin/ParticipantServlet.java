package com.project.zumba.controller.admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.project.zumba.model.Participant;
import com.project.zumba.service.IParticipantService;
import com.project.zumba.service.ParticipantServiceImpl;

/**
 * Servlet implementation class ParticipantServlet
 */
@WebServlet({ "/ParticipantServlet", "/Participant" })
public class ParticipantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	private IParticipantService participantService;

	public ParticipantServlet() {
		super();
		participantService = new ParticipantServiceImpl();
	}
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type = request.getParameter("type");
		System.out.println("Inside ParticipantServlet.doGet()" + type + " type");

		if ("getParticipant".equals(type)) {
			System.out.println("Start ParticipantServlet - Get");
			Participant retrievedParticipant = null; // declare a variable to hold the info
			try {
				retrievedParticipant = participantService.getParticipant(Integer.parseInt(request.getParameter("id")));
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if ("getAllParticipant".equals(type)) {
			System.out.println("Start ParticipantServlet - GetAll");

		} else {
			System.out.println("Invalid ParticipantServlet Call");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Start ParticipantServlet.doPost() with " + request.getParameter("type") + " type from " + request.getParameter("sourcePage"));
		String type = request.getParameter("type");
		String sourcePage = request.getParameter("sourcePage");
		

		if ("createParticipant".equals(type) || "registerParticipant".equals(type)) {
			System.out.println("Start ParticipantServlet - Create");
			Participant participantObj = loadObject(request);
			
			// send to DB & output success/fail results
			try {
				participantService.saveParticipant(participantObj);

				request.setAttribute("successCreateParticipantMessage", "Participant created successfully! Thank You!");
				request.setAttribute("participant", participantObj);

				// Forward the request to the JSP for rendering the view
				RequestDispatcher dispatcher = request.getRequestDispatcher("/" + sourcePage);
				dispatcher.forward(request, response);
			} catch (Exception e) {
				System.out.println("Create failure!: ");
				e.printStackTrace();
			}

		} else if ("updateParticipant".equals(type)) {
			System.out.println("Start ParticipantServlet - Update");
			// update an existing category in db
			Participant participantUpdateObj = loadObject(request);
			try {
				participantService.updateParticipant(participantUpdateObj);
				System.out.println(participantUpdateObj);

				// setting an additional attribute for the end user
				request.setAttribute("successUpdateParticipantMessage", "Participant obj updated sucessfully!");
				// Forward the request to the JSP for rendering the view
				RequestDispatcher dispatcher = request.getRequestDispatcher("/"+sourcePage);
				dispatcher.forward(request, response);
			} catch (Exception e) {
				System.out.println("Update failure!: ");
				e.printStackTrace();
			}
		} else if ("deleteParticipant".equals(type)) {
			System.out.println("Start ParticipantServlet - Delete");
			try {
				System.out.println(request.getParameter("participantId"));
				participantService.deleteParticipant(Integer.parseInt(request.getParameter("participantId")));

				// setting an additional attribute for the end user
				request.setAttribute("successDeleteParticipantMessage", "Participant obj + associate enrollments deleted sucessfully!");
				// Forward the request to the JSP for rendering the view
				RequestDispatcher dispatcher = request.getRequestDispatcher("/"+sourcePage);
				dispatcher.forward(request, response);
			} catch (Exception e) {
				System.out.println("Delete failure!: ");
				e.printStackTrace();
			}
		}else if ("signIn".equals(type)) {
			System.out.println("Start ParticipantServlet - SignIn");
			Participant retrievedParticipant = null; // declare a variable to hold the info
			
			try {
				String signInEmail = request.getParameter("participantEmail");
				System.out.println("Signin email = " + signInEmail);
				String signInPW = request.getParameter("participantPassword");
				System.out.println("Signin pass = " + signInPW);
				retrievedParticipant = participantService.getParticipantBySignIn(signInEmail, signInPW);
				
				
				if (retrievedParticipant.getParticipantId() != null) {
					request.setAttribute("loginValidation", retrievedParticipant.getParticipantId());
					if(retrievedParticipant.getIsAdmin() == true) {
						response.sendRedirect("dashboard-admin.jsp");
						RequestDispatcher dispatcher = request.getRequestDispatcher("/dashboard-admin.jsp");
						dispatcher.forward(request, response);
					} else {
						response.sendRedirect("dashboard-user.jsp");
						RequestDispatcher dispatcher = request.getRequestDispatcher("/dashboard-user.jsp");
						dispatcher.forward(request, response);
					}
					
				} else {
					// setting an additional attribute for the end user
					request.setAttribute("invalidCredentialMessage", "Invalid credential. Please try again or register an account!");
					// Forward the request to the JSP for rendering the view
					RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
					dispatcher.forward(request, response);
				}

			} catch (Exception e) {
				System.out.println("Retrieve failure!: ");
				e.printStackTrace();
			}
	}
	}

	private Participant loadObject(HttpServletRequest request) {
		// Fields/Variables:
		System.out.println("load obj: " + request.getMethod());
		Participant participant = new Participant();

		// update the participant object with appropriate field values
		// drawn from the HttpServletRequest
		// Call the setters methods:
		if (request.getParameter("participantId") != null) {
			String participantId = request.getParameter("participantId"); // getParameter returns a string, need to convert
			participant.setParticipantId(Integer.parseInt(participantId));
		}
		System.out.println("Name = " + request.getParameter("participantName"));
		if (request.getParameter("participantName") != null) {
			participant.setParticipantName(request.getParameter("participantName"));
		}
		System.out.println("Email = " + request.getParameter("participantEmail"));
		if (request.getParameter("participantEmail") != null) {
			participant.setParticipantEmail(request.getParameter("participantEmail"));
		}
		System.out.println("Password = " + request.getParameter("participantPassword"));
		if (request.getParameter("participantPassword") != null) {
			participant.setParticipantPassword(request.getParameter("participantPassword"));
		}
		if (request.getParameter("isAdmin") != null) {
			String isAdmin = (request.getParameter("isAdmin"));
			participant.setIsAdmin(Boolean.parseBoolean(isAdmin));
		}
		return participant;
	}

}

