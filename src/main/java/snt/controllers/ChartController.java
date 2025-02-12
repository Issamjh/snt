package snt.controllers;

import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import snt.entities.FilmByLanguage;
import snt.entities.FilmByRentingTotal;
import snt.entities.SalesByFilmCategory;
import snt.repositories.FilmByLanguageRepository;
import snt.repositories.FilmByRentingTotalRepository;
import snt.repositories.SalesByFilmCategoryRepository;

import java.util.List;


@RestController
@RequestMapping("/chart")
public class ChartController {

    private final SalesByFilmCategoryRepository salesByFilmCategoryRepository;
    private final FilmByLanguageRepository filmByLanguageRepository;
    private final FilmByRentingTotalRepository filmByRentingTotalRepository;

    Logger log = LoggerFactory.getLogger(UserController.class);


    @Autowired
    public ChartController(SalesByFilmCategoryRepository salesByFilmCategoryRepository,
                           FilmByLanguageRepository filmByLanguageRepository,
                           FilmByRentingTotalRepository filmByRentingTotalRepository) {
        this.salesByFilmCategoryRepository=salesByFilmCategoryRepository;
        this.filmByLanguageRepository=filmByLanguageRepository;
        this.filmByRentingTotalRepository =filmByRentingTotalRepository;

    }
    @GetMapping("/salesByCategory")
    private List<SalesByFilmCategory> getSalesByCategory()  {
       return salesByFilmCategoryRepository.findAll();

    }
    @GetMapping("/filmByLanguage")
    private List<FilmByLanguage> getFilmByLanguage()  {
        return filmByLanguageRepository.findAll();

    }
    @GetMapping("/filmByRentingTotal")
    private List<FilmByRentingTotal> getFilmByRentingTotal()  {
        return filmByRentingTotalRepository.findAll();

    }
}

