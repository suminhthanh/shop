package net.vnict.shop;

import net.vnict.shop.domain.entities.Category;
import net.vnict.shop.service.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryTest {

    @Autowired
    CategoryService categoryService;

    @Test
    public void testCategory_getCategoryOK() {
        Category category = categoryService.findOne(1);
        assertThat("Pizza").isEqualTo(category.getName());
    }

    @Test
    public void testCategory_getCategoryNotFound() {
        Category category = categoryService.findOne(22222);
        assertThat(category).isEqualTo(null);
    }

    @Test
    public void testCategory_getCategoryAll(){
        List<Category> categories = (List<Category>) categoryService.findAll();
        assertThat(categories.size()).isGreaterThan(0);
    }

    @Test
    public void testCategory_saveCategoryOK() {
        Category category = new Category();
        category.setName("Drink");
        categoryService.save(category);
        List<Category> categories = (List<Category>) categoryService.findAll();
       Category category1 = categoryService.findOne(categories.size()-1);
       assertThat(category.getName()).isEqualTo(category1.getName());
    }

    @Test
    public void testCategory_saveCategoryFail() {
        Category category = null;
        assertThrows(InvalidDataAccessApiUsageException.class, () -> {
            categoryService.save(category);
        });
    }

    @Test
    public void testCategory_deleteOK() {
        List<Category> categories = (List<Category>) categoryService.findAll();
        Category category = categories.get(categories.size()-1);
        assertThat(categoryService.delete(category.getId())).isEqualTo(true);
    }

    @Test
    public void testCategory_deleteFail() {
        assertThrows(NoSuchElementException.class, () -> {
            categoryService.delete(222);
        });
    }
}
