package com.rk.az.service.impl;

import com.rk.az.config.JwtProvider;
import com.rk.az.domain.AccountStatus;
import com.rk.az.domain.USER_ROLE;
import com.rk.az.exceptions.SellerException;
import com.rk.az.model.Address;
import com.rk.az.model.Seller;
import com.rk.az.repository.AddressRepository;
import com.rk.az.repository.SellerRepository;
import com.rk.az.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl  implements SellerService {
    private final SellerRepository sellerRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final AddressRepository addressRepository;


    @Override
    public Seller getSellerProfile(String jwt) throws SellerException {
        String email = jwtProvider.getEmailFromJwtToken(jwt);

        return this.getSellerByEmail(email);
    }

    @Override
    public Seller createSeller(Seller seller) throws SellerException {

        Seller sellerExist = sellerRepository.findByEmail(seller.getEmail());

        if (sellerExist!=null){
            throw new SellerException("Seller already exist, used different email");
        }

        Address saveAddress = addressRepository.save(seller.getPickupAddress());

        Seller newSeller =new Seller();

        newSeller.setEmail(seller.getEmail());
        newSeller.setPassword(passwordEncoder.encode(seller.getPassword()));
        newSeller.setSellerName(seller.getSellerName());
        newSeller.setPickupAddress(saveAddress);
        newSeller.setGSTIN(seller.getGSTIN());
        newSeller.setRole(USER_ROLE.ROLE_SELLER);
        newSeller.setMobile(seller.getMobile());
        newSeller.setBankDetails(seller.getBankDetails());
        newSeller.setBusinessDetails(seller.getBusinessDetails());

        return sellerRepository.save(newSeller);
    }

    @Override
    public Seller getSellerById(Long id) throws SellerException {
        return sellerRepository.findById(id)
                .orElseThrow(()-> new SellerException("seller not found with id "+id));
    }

    @Override
    public Seller getSellerByEmail(String email) throws SellerException {
        Seller seller=sellerRepository.findByEmail(email);
        if (seller==null){
            throw new SellerException("Seller not found...");
        }
        return seller;
    }

    @Override
    public List<Seller> getAllSeller(AccountStatus status) {

        return sellerRepository.findByAccountStatus(status);
    }

    @Override
    public Seller updateSeller(Long id, Seller seller) throws SellerException {

        Seller existingSeller = this.getSellerById(id);

        if (seller.getSellerName() != null){
            existingSeller.setSellerName(seller.getSellerName());
        }
        if (seller.getMobile() != null){
            existingSeller.setMobile(seller.getMobile());
        }
        if (seller.getEmail() != null){
            existingSeller.setEmail(seller.getEmail());
        }
        if (seller.getBusinessDetails() != null &&
                seller.getBusinessDetails().getBusinessName() != null){
            existingSeller.getBusinessDetails()
                    .setBusinessName(seller.getBusinessDetails().getBusinessName());
        }
        if (seller.getBankDetails() != null
                && seller.getBankDetails().getAccountHolderName() !=null
                && seller.getBankDetails().getIfscCode() != null
                && seller.getBankDetails().getAccountNumber() != null)
        {

            existingSeller.getBankDetails().setAccountHolderName(
                    seller.getBankDetails().getAccountHolderName());

            existingSeller.getBankDetails().setAccountNumber(
                    seller.getBankDetails().getAccountNumber());

            existingSeller.getBankDetails().setIfscCode(
                    seller.getBankDetails().getIfscCode());

        }
        if (seller.getPickupAddress() != null
                && seller.getPickupAddress().getAddress() != null
                && seller.getPickupAddress().getMobile() != null
                && seller.getPickupAddress().getCity() != null
                && seller.getPickupAddress().getState() != null)
        {
            existingSeller.getPickupAddress()
                    .setAddress(seller.getPickupAddress().getAddress());
            existingSeller.getPickupAddress().setCity(seller.getPickupAddress().getCity());
            existingSeller.getPickupAddress().setState(seller.getPickupAddress().getState());
            existingSeller.getPickupAddress().setMobile(seller.getPickupAddress().getMobile());
            existingSeller.getPickupAddress().setPinCode(seller.getPickupAddress().getPinCode());
        }
        if (seller.getGSTIN() != null){
            existingSeller.setGSTIN(seller.getGSTIN());
        }
        return sellerRepository.save(existingSeller);
    }

    @Override
    public void deleteSeller(Long id) throws SellerException {
        Seller seller= getSellerById(id);
        sellerRepository.delete(seller);

    }

    @Override
    public Seller verifyEmail(String email, String otp) throws SellerException {
        Seller seller = getSellerByEmail(email);
        seller.setEmailVerified(true);
        return sellerRepository.save(seller);
    }

    @Override
    public Seller updateSellerAccountStatus(Long sellerId, AccountStatus status) throws SellerException {
        Seller seller = getSellerById(sellerId);
        seller.setAccountStatus(status);
        return sellerRepository.save(seller);
    }
}
