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
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductCategoryServiceTest {
    @InjectMocks
    private ProductCategoryServiceImpl productCategoryService;
    @Mock
    private ProductCategoryRepository productCategoryRepository;
    private ProductCategory productCategory;

    @BeforeEach
    void setUp() {
        productCategory = new ProductCategory();
        productCategory.setProductCategoryId(1L);
        productCategory.setCategoryName("Electronics");
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
        when(productCategoryRepository.findById(1L)).thenReturn(Optional.of(productCategory));

        Optional<ProductCategory> result = productCategoryService.getProductCategory(1L);

        assertTrue(result.isPresent());
//        assertEquals("Electronics", result.get().getName());
        verify(productCategoryRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(productCategoryRepository);
    }
}