
create table IF NOT EXISTS users(
                      username varchar(50) not null primary key,
                      password varchar(500) not null,
                      email varchar(500) ,
                      create_time timestamp not null default current_timestamp,
                      enabled boolean not null default true,
                      enable_time timestamp not null default current_timestamp

);

create table IF NOT EXISTS authorities (
                             username varchar(50) not null,
                             authority varchar(50) not null,
                             primary key pk_auth_username(username,authority),
                             foreign key fk_authorities_users(username) references users(username)

);
create or replace view sales_by_film_category as
select `c`.`name` AS `category`, sum(`p`.`amount`) AS `total_sales`
from (((((`payment` `p` join `rental` `r` on ((`p`.`rental_id` = `r`.`rental_id`))) join `inventory` `i`
         on ((`r`.`inventory_id` = `i`.`inventory_id`))) join `film` `f`
        on ((`i`.`film_id` = `f`.`film_id`))) join `film_category` `fc`
       on ((`f`.`film_id` = `fc`.`film_id`))) join `category` `c` on ((`fc`.`category_id` = `c`.`category_id`)))
group by `c`.`name`
order by `total_sales` desc;

create or replace  view film_by_language as
select `sakila`.`language`.`name` AS `language_name`, count(`sakila`.`film`.`film_id`) AS `count_film`
from (`sakila`.`language` left join `sakila`.`film`
    on ((`sakila`.`film`.`language_id` = `sakila`.`language`.`language_id`)))
group by `language_name` ;

create or replace view film_by_renting_total as
with `count_rental` as (select `sakila`.`film`.`film_id` AS `film_id`, count(0) AS `count_rental`
                        from ((`sakila`.`film` left join `sakila`.`inventory`
                               on ((`sakila`.`film`.`film_id` = `sakila`.`inventory`.`film_id`))) join `sakila`.`rental`
                              on ((`sakila`.`inventory`.`inventory_id` = `sakila`.`rental`.`inventory_id`)))
                        group by `sakila`.`film`.`film_id`)
select `count_rental`.`film_id`      AS `film_id`,
       `count_rental`.`count_rental` AS `count_rental`,
       `sakila`.`film`.`title`       AS `title`,
       `sakila`.`film`.`rental_rate` AS `rental_rate`
from (`count_rental` join `sakila`.`film` on ((`count_rental`.`film_id` = `sakila`.`film`.`film_id`))) ;

