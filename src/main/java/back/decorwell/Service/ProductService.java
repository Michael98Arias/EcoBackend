package back.decorwell.Service;

import back.decorwell.Entity.Category;
import back.decorwell.Entity.Product;
import back.decorwell.Entity.User;
import back.decorwell.Enum.State;
import back.decorwell.Repository.CategoryRepository;
import back.decorwell.Repository.ProductRepository;
import back.decorwell.Repository.UserRepository;
import back.decorwell.Request.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Map<String, Integer> getProductCount() {
        List<Product> allProducts = productRepository.findAll();
        Map<String, Integer> productCountMap = new HashMap<>();

        int totalProducts = allProducts.size();
        productCountMap.put("PRODUCT", totalProducts);

        return productCountMap;
    }

    public void updateProductState(Long productId, State newState) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            product.setState(newState);
            product.setUpdatedAt(LocalDateTime.now());
            productRepository.save(product);
        } else {
            throw new RuntimeException("Product with ID " + productId + " not found");
        }
    }

    public Product register(ProductRequest request) {
        LocalDateTime createdAt = request.getCreatedAt() != null ? request.getCreatedAt() : LocalDateTime.now();

        Optional<Category> categoryOptional = categoryRepository.findById(request.getCategoryId());
        if (!categoryOptional.isPresent()) {
            throw new RuntimeException("Category with ID " + request.getCategoryId() + " not found");
        }
        Category category = categoryOptional.get();

        Optional<User> userOptional = userRepository.findById(request.getSellerId());
        if (!userOptional.isPresent()) {
            throw new RuntimeException("User with ID " + request.getSellerId() + " not found");
        }
        User seller = userOptional.get();

        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .amount(request.getAmount())
                .price(request.getPrice())
                .state(State.ACTIVE)
                .createdAt(createdAt)
                .category(category)
                .seller(seller)
                .build();

        productRepository.save(product);
        return product;
    }

    public void updateProduct(Long productId, Product updatedProduct) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            if (updatedProduct.getName() != null) {
                product.setName(updatedProduct.getName());
            }
            if (updatedProduct.getDescription() != null) {
                product.setDescription(updatedProduct.getDescription());
            }
            if (updatedProduct.getAmount() != null) {
                product.setAmount(updatedProduct.getAmount());
            }
            if (updatedProduct.getPrice() != null) {
                product.setPrice(updatedProduct.getPrice());
            }
            product.setUpdatedAt(LocalDateTime.now());
            productRepository.save(product);
        } else {
            throw new RuntimeException("Product with ID " + productId + " not found");
        }
    }
}
