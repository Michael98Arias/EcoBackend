package back.decorwell.Controller;

import back.decorwell.Entity.Category;
import back.decorwell.Enum.State;
import back.decorwell.Request.CategoryRequest;
import back.decorwell.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class CategoryController extends BaseController {
    @Autowired
    private CategoryService categoryService;


    @GetMapping("category/count")
    public Map<String, Integer> getCategoryCount() {
        return categoryService.getCategoryCount();
    }

    @GetMapping("category")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }
    @PatchMapping("categoryState/{categoryId}/{newState}")
    public void updateCategoryState(@PathVariable Long categoryId, @PathVariable State newState) {
        categoryService.updateCategoryState(categoryId, newState);
    }
    @PostMapping("createCategory")
    public Category register(@RequestBody CategoryRequest request) {
        return categoryService.register(request);
    }

    @PutMapping("editCategory/{categoryId}")
    public void updateCategory(@PathVariable Long categoryId, @RequestBody Category category) {
        categoryService.updateCategory(categoryId, category);
    }

}
