package shop.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.dto.category.CategoryItemDTO;
import shop.dto.product.ProductCreateDTO;
import shop.dto.product.ProductEditDTO;
import shop.dto.product.ProductItemDTO;
import shop.interfaces.ProductService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductItemDTO>> index() throws InterruptedException {
        var result = productService.get();
        Thread.sleep(2000);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductItemDTO> create(@Valid @ModelAttribute ProductCreateDTO model) throws InterruptedException {
        var result = productService.create(model);
        Thread.sleep(2000);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping(value = "{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductItemDTO> edit(@PathVariable("id") int id,
                                               @Valid @ModelAttribute ProductEditDTO model) throws InterruptedException {
        var result = productService.edit(id, model);
        Thread.sleep(2000);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductItemDTO> getProductById(@PathVariable("id") int id) throws InterruptedException {
        var product = productService.getById(id);
        Thread.sleep(2000);
        if(product!=null)
        {
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int productId) throws InterruptedException {
        productService.delete(productId);
        Thread.sleep(2000);
        return new ResponseEntity<>("Продукт знищено.", HttpStatus.OK);
    }
}
