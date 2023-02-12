package com.mywed;

import com.mywed.model.Account;
import com.mywed.model.ProductsNotFoundException;
import com.mywed.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//    Đây là một lớp dịch vụ tài khoản, nó cung cấp các phương thức
//            để lưu, xóa, lấy và liệt kê tất cả các tài khoản.
//            Phương thức listAll() sẽ trả về danh sách tất cả các tài khoản được lấy từ AccountRepository.
//            Phương thức save() sẽ lưu một tài khoản mới trong cơ sở dữ liệu.
//            Phương thức get() sẽ trả về một tài khoản có id được chỉ định, nếu không tìm thấy tài khoản nào sẽ báo lỗi.
//            Phương thức delete() sẽ xóa một tài khoản có id được chỉ định, nếu không tìm thấy tài khoản nào sẽ báo lỗi.
// phần này để làm cho admin để quản lý tài khoản đã tạo
@Service
public class AccountService {
    @Autowired
    private AccountRepository repo;

    public List<Account> listAll(){
        return (List<Account>) repo.findAll();
    }

    public void save(Account account){
        repo.save(account);
    }

    public Account get(Integer id) throws ProductsNotFoundException {
        Optional<Account> result = repo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new ProductsNotFoundException("Could not find any product with ID" + id);
    }
    public void delete(Integer id) throws ProductsNotFoundException {
        Long count = repo.countById(id);
        if(count == null || count == 0){
            throw new ProductsNotFoundException("Could not find any product with ID" + id);
        }
        repo.deleteById(id);
    }
}
