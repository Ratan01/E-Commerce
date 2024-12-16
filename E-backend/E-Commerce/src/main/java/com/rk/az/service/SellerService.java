package com.rk.az.service;

import com.rk.az.domain.AccountStatus;
import com.rk.az.exceptions.SellerException;
import com.rk.az.model.Seller;

import java.util.List;

public interface SellerService {
    Seller getSellerProfile(String jwt) throws SellerException;

    Seller createSeller(Seller seller) throws SellerException;

    Seller getSellerById(Long id) throws SellerException;

    Seller getSellerByEmail(String email) throws SellerException;

    List<Seller> getAllSeller(AccountStatus status);

    Seller updateSeller(Long id, Seller seller) throws SellerException;

    void deleteSeller(Long id) throws SellerException;

    Seller verifyEmail(String email, String otp) throws SellerException;

    Seller updateSellerAccountStatus(Long sellerId, AccountStatus status) throws SellerException;


}
