package com.rk.az.service;


import com.razorpay.PaymentLink;
import com.rk.az.model.Order;
import com.rk.az.model.PaymentOrder;
import com.rk.az.model.User;

import java.util.Set;

public interface PaymentService {
    PaymentOrder createOrder(User user, Set<Order> orders);
    PaymentOrder getPaymentOrderById(Long orderId) throws Exception;
    PaymentOrder getPaymentOrderByPaymentId(String orderId);
    Boolean proceedPaymentOrder(PaymentOrder paymentOrder, String paymentId, String paymentLinkId);
    PaymentLink createRazorpayPaymentLink(User user,  Long amount, Long orderId);
}
