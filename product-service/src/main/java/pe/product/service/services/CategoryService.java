package pe.product.service.services;

import java.util.List;

import org.springframework.stereotype.Service;

import pe.product.service.entity.Category;
import pe.product.service.repository.CategoryRepository;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<Category> getAll() {
        return repository.findAll();
    }

    public Category getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Category save(Category category) {
        return repository.save(category);
    }

    public Category update(Long id, Category category) {
        return repository.findById(id).map(existing -> {
            existing.setName(category.getName());
            existing.setDescription(category.getDescription());
            return repository.save(existing);
        }).orElse(null);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}