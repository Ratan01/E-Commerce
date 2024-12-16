package com.rk.az.service;

import com.rk.az.model.Seller;
import com.rk.az.model.SellerReport;

public interface SellerReportService {
    SellerReport getSellerReport(Seller seller);
    SellerReport updateSellerReport(SellerReport sellerReport);
}
