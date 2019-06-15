package com.donald.sqldao;

import java.util.List;

import com.donald.users.Customer;
import com.donald.users.Payment;

public interface PaymentSQLDAO {
	
	public void insertPayment(Payment payment);

	public void updatePayment(Payment payment, Customer customer);

	public List<Payment> getAllPayments();

	public List<Payment> getPaymentsByCustomerId(int id);

}
