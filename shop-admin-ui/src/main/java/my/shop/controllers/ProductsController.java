package my.shop.controllers;

import my.shop.entity.Product;
import my.shop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RequestMapping("/product")
@Controller
public class ProductsController {

    private ProductService productService;

    @Autowired
    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

//    @GetMapping("/list")
//    public String productList(Model model,
//                              @RequestParam (name = "page", defaultValue = "1", required = false) Integer page,
//                              @RequestParam (name = "size", defaultValue = "5", required = false) Integer size
//    ) {
//        Page<Product> productsPage = productService.filterAll(
//                PageRequest.of(page- 1, size)
//        );
//        model.addAttribute("productsPage", productsPage);
//        model.addAttribute("prevPageNumber", productsPage.hasPrevious() ? productsPage.previousPageable().getPageNumber() + 1 : -1);
//        model.addAttribute("nextPageNumber", productsPage.hasNext() ? productsPage.nextPageable().getPageNumber() + 1 : -1);
//        return "products";
//    }
    @GetMapping("/list")
    public String productList(){
        return "products";
    }



    @GetMapping("new")
    public String createProduct(Model model) {
        model.addAttribute("product", new Product());
        return "products";
    }

    @GetMapping(path="/update/{id}")
    public String updateById(Model model, @PathVariable(value = "id") Long productId) {
        Optional<Product> product = productService.findById(productId);
        model.addAttribute("product", product);
        return "products";
    }

    @GetMapping(path="/remove/{id}")
    public String removeById(@PathVariable(value = "id") Long productId) {
        productService.delete(productId);
        return "redirect:/product/list";
    }



    @PostMapping
    public String saveProduct(@Valid Product product, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "products";
        }
        if(product.getPrice().doubleValue()<0){
            bindingResult.rejectValue("price", "", "Цена не может быть отрицательной");
            return "products";
        }

        productService.save(product);
        return "redirect:/product/list";
    }
}
