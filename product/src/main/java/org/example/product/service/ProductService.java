package org.example.product.service;

import org.example.product.dto.ProductDTO;
import org.example.product.model.Product;
import org.example.product.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return modelMapper.map(products,new TypeToken<List<ProductDTO>>(){}.getType());
    }
    public ProductDTO getProduct(int productId) {
        Product product = productRepository.getProductById(productId);
        return modelMapper.map(product,ProductDTO.class);
    }

    public ProductDTO addProduct(ProductDTO productDTO) {
        productRepository.save(modelMapper.map(productDTO,Product.class));
        return productDTO;
    }
    public ProductDTO updateProduct(ProductDTO productDTO) {
        productRepository.save(modelMapper.map(productDTO,Product.class));
        return productDTO;
    }
    public  String deleteProduct(int productId) {
        productRepository.deleteById(productId);
        return "Product "+ productId+" is deleted";
    }
}
