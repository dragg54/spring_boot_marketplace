package ecommerce.services;

import ecommerce.repositories.ProductCategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import ecommerce.entities.ProductCategory;
import ecommerce.exceptions.NotFoundException;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductCategoryServiceTest {
    @InjectMocks
    private ProductCategoryServiceImpl productCategoryService;
    @Mock
    private ProductCategoryRepository productCategoryRepository;
    private ProductCategory productCategory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("When get product category is invoked with a given id " +
            "then should throw not found exception if it returns null")
    public void getNonExistingProductCategory() throws Exception {
        // Arrange
        when(productCategoryRepository.findById(1L)).thenReturn(Optional.empty());
        // Act & Assert
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> {
            productCategoryService.getProductCategory(1l);
        });
    }

    @Test
    @DisplayName("When get product category is invoked with a valid id " +
            "then should return a product category")
    public void getExistingProductCategory() throws NotFoundException {
        // Arrange
        productCategory = ProductCategory.builder()
                                         .productCategoryId(1L)
                                         .categoryName("Electronics")
                                         .createdAt(LocalDateTime.now())
                                         .updatedAt(null)
                                         .build();

        when(productCategoryRepository.findById(1L)).thenReturn(Optional.ofNullable(productCategory));
        // Act
        Optional<ProductCategory> result = productCategoryService.getProductCategory(1L);
        assertNotNull(result);
        assertEquals(productCategory, result);
    }
}