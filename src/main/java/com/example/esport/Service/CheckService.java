package com.example.esport.Service;
<<<<<<< HEAD

import com.example.esport.model.Checkin;
import com.example.esport.model.Customer;
import com.example.esport.model.Product;
import com.example.esport.model.Ticker;

import com.example.esport.repository.CustomerRepository;
import com.example.esport.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class CheckService {
    @Autowired
    private ProductRepository productRepository;
    private final List<Integer> availableSeats = new ArrayList<>();
    @Autowired
    private CustomerRepository customerRepository;

    public boolean isDuplicate(Checkin checkin) {
        // Kiểm tra sản phẩm có tồn tại không
        boolean productExists = productRepository.existsByIdAndNameAndBeginAt(
                checkin.getProductId(),
                checkin.getProductName(),
                String.valueOf(checkin.getBeginAt())
        );

        // Kiểm tra khách hàng có tồn tại không
        boolean customerExists = customerRepository.existsByFullNameAndEmail(
                checkin.getFullName(),
                checkin.getEmail()
        );

        // Trả về true nếu dữ liệu trùng lặp
        return productExists && customerExists;
    }
    public Ticker getTickerData(Checkin checkin) {
        Product product = productRepository.findById(checkin.getProductId()).orElse(null);
        Customer customer = customerRepository.findByFullNameAndEmail(
                checkin.getFullName(),
                checkin.getEmail()
        );

        // Tạo đối tượng Ticker chứa thông tin từ Product và Customer
        if (product != null && customer != null) {
            // Lấy ghế ngẫu nhiên
            Integer seat = assignRandomSeat();
            return new Ticker(product.getId(), product.getName(), product.getBeginAt(), customer.getFullName(), customer.getEmail(), seat);
        }
        return null;
    }

    public CheckService() {
        for (int i = 1; i <= 250; i++) {
            availableSeats.add(i);
        }
        Collections.shuffle(availableSeats); // Xáo trộn danh sách ghế
    }
    private static Set<Integer> usedSeats = new HashSet<>(); // Giả sử bạn lưu ghế đã sử dụng vào bộ nhớ tạm

    public Integer assignRandomSeat() {
        Random random = new Random();
        Integer seat;

        // Lặp cho đến khi tìm được ghế chưa được sử dụng
        do {
            seat = random.nextInt(250) + 1;  // Sinh ngẫu nhiên ghế từ 1 đến 50
        } while (usedSeats.contains(seat));  // Kiểm tra nếu ghế đã được chọn rồi

        // Lưu ghế vào bộ nhớ tạm để không bị trùng
        usedSeats.add(seat);
        return seat;
    }
}
=======
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

>>>>>>> b2be986 (jonggun)
