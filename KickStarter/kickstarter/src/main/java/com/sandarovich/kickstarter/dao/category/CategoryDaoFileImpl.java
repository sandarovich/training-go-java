package com.sandarovich.kickstarter.dao.category;

import com.sandarovich.kickstarter.domain.Category;
import com.sandarovich.kickstarter.domain.Project;

import java.util.List;

/**
 * Category Dao file mode
 */
public class CategoryDaoFileImpl implements CategoryDao {
    @Override
    public List<Category> getCategories() {
        return null;
    }

    @Override
    public Category findCategoryById(int id) {
        return null;
    }

    @Override
    public boolean isValidCategory(String category) {
        return false;
    }

    @Override
    public Project findProject(Category category, String inputValue) {
        return null;
    }
}
