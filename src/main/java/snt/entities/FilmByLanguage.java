/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snt.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;
/**
 *
 * @author Issam
 */
@Entity
@Table(name = "film_by_language")
@Immutable
@NamedQueries({
        @NamedQuery(name = "FilmByLanguage.findAll", query = "SELECT f FROM FilmByLanguage f")
        , @NamedQuery(name = "FilmByLanguage.findByLanguageName", query = "SELECT f FROM FilmByLanguage f WHERE f.languageName = :languageName")
        , @NamedQuery(name = "FilmByLanguage.findByCountFilm", query = "SELECT f FROM FilmByLanguage f WHERE f.countFilm = :countFilm")})
public class FilmByLanguage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Id
    @Column(name = "language_name")
    private String languageName;
    @Basic(optional = false)

    @Column(name = "count_film")
    private long countFilm;

    public FilmByLanguage() {
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageId) {
        this.languageName = languageId;
    }

    public long getCountFilm() {
        return countFilm;
    }

    public void setCountFilm(long countFilm) {
        this.countFilm = countFilm;
    }

}
