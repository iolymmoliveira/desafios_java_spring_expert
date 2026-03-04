package com.iolyoliveira.DSCatalog.services;

import com.iolyoliveira.DSCatalog.dto.CategoryDTO;
import com.iolyoliveira.DSCatalog.entities.Category;
import com.iolyoliveira.DSCatalog.repositories.CategoryRepository;
import com.iolyoliveira.DSCatalog.services.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll() {
        List<Category> list = repository.findAll();
        return list.stream().map(CategoryDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id) {
        Optional<Category> obj = repository.findById(id);
        return new CategoryDTO(obj.orElseThrow(() -> new EntityNotFoundException("Entity not found")));
    }
}
