create table CONSULTA(
    id bigint not null auto_increment,
    id_paciente varchar(10) not null unique,
    id_medico varchar(10) not null unique,
    dataDaConsulta varchar(10),
    horaDaConsulta varchar(10),
    primary key(id)
    );
    