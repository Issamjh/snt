package snt.repositories;


import snt.entities.FilmByRentingTotal;

public interface FilmByRentingTotalRepository extends ReadOnlyRepository<FilmByRentingTotal,Short>{
    FilmByRentingTotal findByFilmId(Short filmId);
}