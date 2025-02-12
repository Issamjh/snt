package snt.repositories;


import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;
import snt.entities.SalesByFilmCategory;

public interface SalesByFilmCategoryRepository extends ReadOnlyRepository<SalesByFilmCategory,String>{

    SalesByFilmCategory  findByCategory(String category);
}