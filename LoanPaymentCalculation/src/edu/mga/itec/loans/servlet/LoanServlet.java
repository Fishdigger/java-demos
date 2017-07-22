package edu.mga.itec.loans.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;


import edu.mga.itec.loans.LoanCalculator;
import edu.mga.itec.loans.viewmodels.LoanViewModel;

@WebServlet("/LoanServlet")
public class LoanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoanServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoanCalculator calc = new LoanCalculator();
		calc.setInterestRate(Double.parseDouble(request.getParameter("interest")));
		calc.setLoanAmount(Double.parseDouble(request.getParameter("loanAmount")));
		calc.setNumYears(Integer.parseInt(request.getParameter("numYears")));
		
		LoanViewModel vm = new LoanViewModel();
		vm.setInterestRate(calc.getInterestRate());
		vm.setLoanAmount(calc.getLoanAmount());
		vm.setNumYears(calc.getNumYears());
		vm.setMonthlyPayment(calc.getMonthlyPayment());
		vm.setTotalPayment(calc.getTotalPayment());

		request.setAttribute("rand", 3);
		
		request.setAttribute("loan", vm);
		RequestDispatcher dispatch = getServletContext().getRequestDispatcher("/loanPayments.jsp");
		dispatch.forward(request, response);
	}

}
