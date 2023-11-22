package com.example.demo.repository;

import com.example.demo.dto.CategoryDto;
import com.example.demo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query("select new com.example.demo.dto.CategoryDto(ctg.id,ctg.name)from Category ctg")
    List<CategoryDto> fetchAllCategory();
}
