package back.decorwell.Controller;

import back.decorwell.Entity.Product;
import back.decorwell.Enum.State;
import back.decorwell.Request.ProductRequest;
import back.decorwell.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ProductController extends BaseController {
    @Autowired
    private ProductService productService;

    @GetMapping("product")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("product/count")
    public Map<String, Integer> getProductCount() {
        return productService.getProductCount();
    }

    @PostMapping("createProduct")
    public Product register(@RequestBody ProductRequest request) {
        return productService.register(request);
    }

    @PatchMapping("productState/{productId}/{newState}")
    public void updateProductState(@PathVariable Long productId, @PathVariable State newState) {
        productService.updateProductState(productId, newState);
    }

    @PutMapping("editProduct/{productId}")
    public void updateProduct(@PathVariable Long productId, @RequestBody Product product) {
        productService.updateProduct(productId, product);
    }
}
