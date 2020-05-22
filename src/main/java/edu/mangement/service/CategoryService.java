package edu.mangement.service;

import edu.mangement.model.CategoryDTO;
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
public interface CategoryService {
    CategoryDTO saveCategory(CategoryDTO categoryDTO) throws Exception;
    List<CategoryDTO> findAllCategory(Pageable pageable);
    CategoryDTO findCategoryById(Long id);
    void deleteCategory(CategoryDTO categoryDTO) throws Exception;
}
