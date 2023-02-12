package com.mywed.repository;

import com.mywed.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
//Đây là một mẫu của interface SearchRepository được mở rộng từ JpaRepository.
// Nó bao gồm các phương thức @Query để thực hiện các truy vấn tìm kiếm trên cơ sở dữ liệu.
public interface SearchRepository extends JpaRepository<Products, Integer> {
    // tìm kiếm bằng từ khoá ng dùng nhập vào
    @Query(value = "select * from db_motor.categories where match(name, Picture, category, specifications, motor, Information, description) against(?1)", nativeQuery = true)
    public List<Products>  search( String keywords);
    // tìm kiếm sp bằng 1 form checkbox tên hãng sp
    @Query(value = "SELECT * FROM db_motor.categories WHERE motor in ?1",nativeQuery = true)
    public List<Products>  findByMotor(@Param("productType") List<String> regions);
    // tìm kiến sp bằng 1 form checkbox về phân khúc
    @Query(value = "SELECT * FROM db_motor.categories WHERE cylinder in ?1",nativeQuery = true)
    public List<Products>  findByCylinder(@Param("cylinder") List<String> cylinder);
    // tìm kiếm sp trong khoảng giá nào đó
    @Query(value = "SELECT * FROM db_motor.categories WHERE price BETWEEN ?1 AND ?2",nativeQuery = true)
    public List<Products>  findByPrice(Integer min,@Param("searchprice") List<String> listPrice);
//    @Query(value = "SELECT * FROM db_motor.categories WHERE price BETWEEN :start AND :end",nativeQuery = true)
//    public List<Products> findByPrice(Integer start ,Integer end);
}
