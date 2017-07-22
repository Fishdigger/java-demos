<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Loan Payments</title>
<link rel="stylesheet" href="bootstrap.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<h2 class="text-center">Loan Details</h2>
	<div class="col-md-6 col-md-offset-3">
		<p><strong>Loan Amount: </strong>${ loan.loanAmount }</p>
		<p><strong>Interest Rate: </strong>${ loan.interestRate }</p>
		<p><strong>Number of Years: </strong>${ loan.numYears }</p>
		<p><strong><em>Monthly Payment: </em></strong>${ loan.monthlyPayment }</p>
		<p><strong><em>Total Payment: </em></strong>${ loan.totalPayment }</p>
	</div>
</body>
</html>