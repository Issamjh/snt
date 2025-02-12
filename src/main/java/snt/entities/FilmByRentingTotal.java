/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snt.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;

/**
 *
 * @author Issam
 */
@Entity
@Immutable
@Table(name = "film_by_renting_total")
@NamedQueries({
    @NamedQuery(name = "FilmByRentingTotal.findAll", query = "SELECT f FROM FilmByRentingTotal f")
    , @NamedQuery(name = "FilmByRentingTotal.findByFilmId", query = "SELECT f FROM FilmByRentingTotal f WHERE f.filmId = :filmId")
    , @NamedQuery(name = "FilmByRentingTotal.findByCountRental", query = "SELECT f FROM FilmByRentingTotal f WHERE f.countRental = :countRental")
    , @NamedQuery(name = "FilmByRentingTotal.findByRentalRate", query = "SELECT f FROM FilmByRentingTotal f WHERE f.rentalRate = :rentalRate")})
public class FilmByRentingTotal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "film_id")
    @Id
    private short filmId;
    @Basic(optional = false)

    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @Column(name = "count_rental")
    private long countRental;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "rental_rate")
    private BigDecimal rentalRate;

    public FilmByRentingTotal() {
    }

    public short getFilmId() {
        return filmId;
    }

    public void setFilmId(short filmId) {
        this.filmId = filmId;
    }

    public long getCountRental() {
        return countRental;
    }

    public void setCountRental(long countRental) {
        this.countRental = countRental;
    }

    public BigDecimal getRentalRate() {
        return rentalRate;
    }

    public void setRentalRate(BigDecimal rentalRate) {
        this.rentalRate = rentalRate;
    }
    
}
