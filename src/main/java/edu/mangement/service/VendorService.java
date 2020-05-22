package edu.mangement.service;

import edu.mangement.model.CategoryDTO;
import edu.mangement.model.VendorDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/21/2020
 * TIME : 5:11 PM
 */
@Service
public interface VendorService {
    VendorDTO saveVendor(VendorDTO vendorDTO) throws Exception;
    List<VendorDTO> findAllVendor(Pageable pageable);
    VendorDTO findVendorById(Long id);
    void deleteVendor(VendorDTO vendorDTO) throws Exception;
}
