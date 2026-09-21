package net.spfwork.forum.service.impl;

import net.spfwork.forum.dao.CategoryDao;
import net.spfwork.forum.domain.Category;
import net.spfwork.forum.service.CategoryService;

import java.util.List;

/**
 * 分类服务实现类
 * 实现了categoryService接口，提供分类相关的服务实现
 */
public class CategoryServiceImpl implements CategoryService {
    /**
     * 获取分类列表
     * @return 返回分类列表，当前实现返回null
     */
    CategoryDao dao=new CategoryDao();
    @Override
    public List<Category> list() {
        return dao.list();
    }

}
