-- INSERTS CONCESIONARIO
INSERT INTO bdconcesionario.concesionario VALUES(1,"ESPAÑA","C/ Engaño, Nº33");
INSERT INTO bdconcesionario.concesionario VALUES(2,"ESPAÑA","Avda. de la electricidad");
INSERT INTO bdconcesionario.concesionario VALUES(3,"FRANCIA","Avenue des chevaux, non 23");

-- INSERTS CLIENTES
INSERT INTO bdconcesionario.cliente VALUES(1,"29651487G","Pedro","Cuevas Picapiedra",698521456,2);
INSERT INTO bdconcesionario.cliente VALUES(2,"79265879Ñ","Arnold","Moreau Simon",635129874,3);
INSERT INTO bdconcesionario.cliente VALUES(3,"45962258O","Juan","Moreno Mena",698154736,1);

-- INSERTS COCHE
INSERT INTO bdconcesionario.coche VALUES("1259DBH","Peugeot","2005-02-10","España","29000",1);
INSERT INTO bdconcesionario.coche VALUES("2694LJC","Ford","2022-01-25","España","69000",2);
INSERT INTO bdconcesionario.coche VALUES("5694HJL","Renault","2020-08-19","Francia","24000",3);

-- INSERTS DISTRIBUIDOR
INSERT INTO bdconcesionario.distribuidor VALUES(1,"TodoCoches","España");
INSERT INTO bdconcesionario.distribuidor VALUES(2,"ToutesVoitures","Francia");
INSERT INTO bdconcesionario.distribuidor VALUES(3,"JamonesEnrique","España");

-- INSERTS DISTRIBUIDOR_HAS_CONCESIONARIO
INSERT INTO bdconcesionario.distribuidor_has_concesionario VALUES(1,1,25000,"2021-03-24");
INSERT INTO bdconcesionario.distribuidor_has_concesionario VALUES(2,3,12000,"2022-04-16");
INSERT INTO bdconcesionario.distribuidor_has_concesionario VALUES(3,2,46000,"2022-06-09");