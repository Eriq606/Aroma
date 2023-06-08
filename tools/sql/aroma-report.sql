create table report(
    id_report serial primary key, 
    valeur numeric, 
    date_report timestamp default now()
);

create table transaction(
    numero_transaction serial primary key, 
    description varchar(50),
    date_transaction timestamp default now(),
    entree numeric, 
    sortie numeric
);

-- Mamafa
drop view v_caisse_now;
drop view v_dernier_report;
drop table report;
drop table transaction;