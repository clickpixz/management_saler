package edu.mangement.service.Impl;

import edu.mangement.mapper.VendorMapper;
import edu.mangement.model.VendorDTO;
import edu.mangement.repository.VendorRepository;
import edu.mangement.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/21/2020
 * TIME : 5:50 PM
 */
@Component
@Transactional(rollbackOn = Exception.class)
public class VendorSerivceImpl implements VendorService {
    @Autowired
    private VendorRepository vendorRepository;
    @Override
    public VendorDTO saveVendor(VendorDTO vendorDTO) throws Exception {
        return Optional.ofNullable(vendorDTO)
                .map(VendorMapper::toEntity)
                .map(vendor -> vendorRepository.save(vendor))
                .map(VendorMapper::toDTO)
                .orElseThrow(null);
    }

    @Override
    public List<VendorDTO> findAllVendor(Pageable pageable) {
        return vendorRepository.findAllByActiveFlag(1,pageable)
                                .stream()
                                    .map(VendorMapper::toDTO)
                                    .collect(Collectors.toList());
    }

    @Override
    public VendorDTO findVendorById(Long id) {
        return Optional.ofNullable(id)
                .map(vendorId -> vendorRepository.findCategoryByIdAndActiveFlag(vendorId,1))
                .map(VendorMapper::toDTO)
                .orElseGet(null);
    }

    @Override
    public void deleteVendor(VendorDTO vendorDTO) throws Exception {
            vendorDTO.setActiveFlag(0);
            vendorRepository.save(VendorMapper.toEntity(vendorDTO));
    }
}
