package edu.mangement.service.Impl;

import edu.mangement.entity.Category;
import edu.mangement.mapper.CategoryMapper;
import edu.mangement.model.CategoryDTO;
import edu.mangement.repository.CategoryRepository;
import edu.mangement.service.CategoryService;
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
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public CategoryDTO saveCategory(CategoryDTO categoryDTO) throws Exception {
        return Optional.ofNullable(categoryDTO)
                .map(CategoryMapper::toEntity)
                .map(category -> categoryRepository.save(category))
                .map(CategoryMapper::toDTO)
                .orElseThrow(null);
    }

    @Override
    public List<CategoryDTO> findAllCategory(Pageable pageable) {
        return categoryRepository.findAllByActiveFlag(1,pageable)
                                .stream()
                                    .map(CategoryMapper::toDTO)
                                    .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO findCategoryById(Long id) {
        if(id!=null){
            var category = categoryRepository.findCategoryByIdAndActiveFlag(id, 1);
            if(category!=null){
                return CategoryMapper.toDTO(category);
            }
        }
        return null;
    }

    @Override
    public void deleteCategory(CategoryDTO categoryDTO) throws Exception {
            categoryDTO.setActiveFlag(0);
            categoryRepository.save(CategoryMapper.toEntity(categoryDTO));
    }
}
