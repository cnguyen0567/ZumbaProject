package com.project.zumba.controller.admin;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.project.zumba.model.Batch;
import com.project.zumba.service.BatchServiceImpl;
import com.project.zumba.service.IBatchService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BatchServlet
 */
@WebServlet({ "/BatchServlet", "/Batch" })
public class BatchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	private IBatchService batchService;

	public BatchServlet() {
		super();
		batchService = new BatchServiceImpl();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type = request.getParameter("type");
		System.out.println("Inside BatchServlet.doGet()" + type + " type");

		if ("getBatch".equals(type)) {
			System.out.println("Start BatchServlet - Get");
			Batch retrievedBatch = null; // declare a variable to hold the info
			try {
				retrievedBatch = batchService.getBatch(Integer.parseInt(request.getParameter("id")));
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if ("getAllBatches".equals(type)) {
			System.out.println("Start BatchServlet - GetAll");
			List<Batch> batchList = batchService.getAllBatches();
			request.setAttribute("batchList", batchList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/dashboard-admin.jsp");
			dispatcher.forward(request, response);

		} else {
			System.out.println("Invalid BatchServlet Call");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Start BatchServlet.doPost() with " + request.getParameter("type") + " type");
		String type = request.getParameter("type");

		if ("createBatch".equals(type)) {
			System.out.println("Start BatchServlet - Create");

			Batch batchObj = loadObject(request);

			// send to DB & output success/fail results
			try {
				batchService.saveBatch(batchObj);

				request.setAttribute("successCreateBatchMessage", "Batch added successfully!");
				request.setAttribute("batch", batchObj);

				// Forward the request to the JSP for rendering the view
				RequestDispatcher dispatcher = request.getRequestDispatcher("/dashboard-admin.jsp");
				dispatcher.forward(request, response);
			} catch (Exception e) {
				System.out.println("Create failure!: ");
				e.printStackTrace();
			}

		} else if ("updateBatch".equals(type)) {
			System.out.println("Start BatchServlet - Update");
			// update an existing category in db
			Batch batchUpdateObj = loadObject(request);
			try {
				// the appropriate to UPDATE the category obj created above
				batchService.updateBatch(batchUpdateObj);
				System.out.println(batchUpdateObj);

				// setting an additional attribute for the end user
				request.setAttribute("successUpdateBatchMessage", "Batch obj updated sucessfully!");
				// Forward the request to the JSP for rendering the view
				RequestDispatcher dispatcher = request.getRequestDispatcher("/dashboard-admin.jsp");
				dispatcher.forward(request, response);
			} catch (Exception e) {
				System.out.println("Update failure!: ");
				e.printStackTrace();
			}
		} else if ("deleteBatch".equals(type)) {
			System.out.println("Start BatchServlet - Delete");
			try {
				System.out.println(request.getParameter("batchId"));
				batchService.deleteBatch(Integer.parseInt(request.getParameter("batchId")));

				// setting an additional attribute for the end user
				request.setAttribute("successDeleteBatchMessage", "Batch obj + associate enrollments deleted sucessfully!");
				// Forward the request to the JSP for rendering the view
				RequestDispatcher dispatcher = request.getRequestDispatcher("/others.jsp");
				dispatcher.forward(request, response);
			} catch (Exception e) {
				System.out.println("Delete failure!: ");
				e.printStackTrace();
			}
		}
	}

	private Batch loadObject(HttpServletRequest request) {
		// Fields/Variables:
		System.out.println("load obj: " + request.getMethod());
		Batch batch = new Batch();
		DateTimeFormatter formatterDate = DateTimeFormatter.ISO_LOCAL_DATE;
		DateTimeFormatter formatterTime = DateTimeFormatter.ISO_LOCAL_TIME;

		// update the batch object with appropriate field values
		// drawn from the HttpServletRequest
		// Call the setters methods:
		if (request.getParameter("batchId") != null) {
			String batchId = request.getParameter("batchId"); // getParameter returns a string, need to convert
			batch.setBatchId(Integer.parseInt(batchId));
		}
		if (request.getParameter("batchName") != null) {
			batch.setBatchName(request.getParameter("batchName"));
		}
		if (request.getParameter("batchStartDate") != null) {
			String startDateString = request.getParameter("batchStartDate");
			LocalDate batchStartDate = LocalDate.parse(startDateString, formatterDate);
			batch.setBatchStartDate(batchStartDate);
		}
		if (request.getParameter("batchEndDate") != null) {
			String endDateString = request.getParameter("batchEndDate");
			LocalDate batchEndDate = LocalDate.parse(endDateString, formatterDate);
			batch.setBatchEndDate(batchEndDate);
		}
		if (request.getParameter("batchStartTime") != null) {
			String startTimeString = request.getParameter("batchStartTime");
			LocalTime batchStartTime = LocalTime.parse(startTimeString, formatterTime);
			batch.setBatchStartTime(batchStartTime);
		}
		if (request.getParameter("batchDuration") != null) {
			String batchDuration = request.getParameter("batchDuration");
			batch.setBatchDuration(Float.parseFloat(batchDuration));
		}
		if (request.getParameter("batchSizeLimit") != null) {
			String batchSizeLimit = request.getParameter("batchSizeLimit"); // getParameter returns a string, need to
																			// convert
			batch.setBatchSizeLimit(Integer.parseInt(batchSizeLimit));
		}
		return batch;
	}

}
