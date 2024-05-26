package ecommerce.services;

import ecommerce.dtos.mappers.ProductMapper;
import ecommerce.dtos.responses.ProductResponse;
import ecommerce.entities.Product;
import ecommerce.exceptions.NotFoundException;
import ecommerce.repositories.ProductCategoryRepository;
import ecommerce.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class ProductServiceTest {
    @InjectMocks
    private ProductServiceImpl productService;
    @Mock
    private ProductCategoryRepository productCategoryRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductMapper productMapper;
    private Product product;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Given a valid id of an existing product" +
            " when findProductById is invoked then should return the product")
    public void getProductById() throws NotFoundException {
        //Arrange
        product = new Product();
        ProductResponse productResponse = new ProductResponse();
        when(productRepository.findById(1L)).thenReturn(Optional.ofNullable(product));
        when(productMapper.productToProductResponse(product)).thenReturn(productResponse);

        //Act
        ProductResponse result = productService.getProduct(1L);

        //Assert
        assertNotNull(result);
    }

    @Test
    void testGetProduct_NotFound() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            productService.getProduct(1L);
        });

        assertEquals("Product with id 1 not found", exception.getMessage());
        verify(productRepository, times(1)).findById(1L);
        verify(productMapper, times(0)).productToProductResponse(any(Product.class));
    }

}
