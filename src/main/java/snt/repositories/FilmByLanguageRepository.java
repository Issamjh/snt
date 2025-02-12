package snt.repositories;


import snt.entities.FilmByLanguage;
import snt.entities.SalesByFilmCategory;

public interface FilmByLanguageRepository extends ReadOnlyRepository<FilmByLanguage,String>{
    FilmByLanguage  findByLanguageName(String languageName);
}