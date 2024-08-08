package back.decorwell.Service;

import back.decorwell.Entity.Category;
import back.decorwell.Enum.State;
import back.decorwell.Repository.CategoryRepository;
import back.decorwell.Request.CategoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Map<String, Integer> getCategoryCount() {
        List<Category> allCategories = categoryRepository.findAll();
        Map<String, Integer> categoryCountMap = new HashMap<>();

        int totalCategories = allCategories.size();
        categoryCountMap.put("CATEGORY", totalCategories);

        return categoryCountMap;
    }
    public void updateCategoryState(Long categoryId, State newState) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            category.setState(newState);
            category.setUpdatedAt(LocalDateTime.now());
            categoryRepository.save(category);
        } else {
            throw new RuntimeException("Category with ID " + categoryId + " not found");
        }
    }
    public Category register(CategoryRequest request) {
        LocalDateTime createdAt = request.getCreatedAt() != null ? request.getCreatedAt() : LocalDateTime.now();
        Category category = Category.builder()
                .name(request.getName())
                .state(State.ACTIVE)
                .createdAt(createdAt)
                .build();

        categoryRepository.save(category);
        return category;
    }
    public void updateCategory(Long categoryId, Category updatedCategory) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            if (updatedCategory.getName() != null) {
                category.setName(updatedCategory.getName());
            }
            category.setUpdatedAt(LocalDateTime.now());
            categoryRepository.save(category);
        } else {
            throw new RuntimeException("Category with ID " + categoryId + " not found");
        }
    }
}
