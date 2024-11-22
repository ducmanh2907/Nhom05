package com.example.esport.Service;
import com.example.esport.model.Checkin;
import com.example.esport.model.Customer;
import com.example.esport.model.Product;
import com.example.esport.repository.CheckinRepository;
import com.example.esport.repository.CustomerRepository;
import com.example.esport.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.constraints.NotNull;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
@Service
public class CheckService {

    @Autowired
    private CheckinRepository checkinRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;



    public boolean checkIfDataExists(Long id, String name, String beginAt, String fullname, String email) {
        Checkin existingCheckin = checkinRepository.findByIdAndNameAndBeginAtAndFullnameAndEmail(id, name, beginAt, fullname, email);
        return existingCheckin != null;
    }
    }

