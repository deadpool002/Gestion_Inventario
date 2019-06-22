create database ServicioTecnico;
use ServicioTecnico;


Create Table usuario(
	id int auto_increment,
	user varchar(25),
    password varchar(60),
    nombreUsuario varchar(50),
    cargo varchar(25),
	fechaIngreso datetime,
    primary key(id,user)
);

insert into usuario(user,password,nombreUsuario,cargo,fechaIngreso) values('Ruben',SHA1('password'),'Ruben','Administrador',NOW());
insert into usuario(user,password,nombreUsuario,cargo,fechaIngreso) values('Ruben2',SHA1('password'),'Ruben','Usuario',NOW());

Create Table cliente(
	id int auto_increment,
    carnetIdentidad int,
    nombre varchar(100),
    numeroTelefono varchar(12),
    fechaRegistro datetime,
    direccion varchar(100),
    primary key(id,carnetIdentidad)
);


create table numeroServicio(
	id int auto_increment primary key,
    id_cliente int,
    fecha_registro datetime,
    estado varchar(30),
    fecha_entrega datetime,
    fecha_recogida datetime,
    costo_repuesto int,
    costo_servicio int, 
    tipo_registro varchar(20),
    foreign key (id_cliente) references cliente(id)
);


create table soporte_impresora(
	id_servicio int,
    marca varchar(50),
    modelo varchar(50),
    numero_serie  varchar(100),
    cartuchos char(2),
    color varchar(20),
    negro varchar(20),
	descripcion_trabajo text,
    foreign key (id_servicio) references numeroServicio(id)
);
create table soporte_otros(
	id_servicio int,
    descripcion_trabajo text,
	foreign key (id_servicio) references numeroServicio(id)
);

create table soporte_portatil(
	id_servicio int,
    marca varchar(100),
    modelo varchar(100),
    numero_serie varchar(100),
    cargador char(2),
    numero_serie_cargador varchar(100),
    descripcion_trabajo text,
    foreign key (id_servicio) references numeroServicio(id)
);

                  
                        
create table soporte_pc(
	id_servicio int,
    tarjeta_madre varchar(150),
    procesador varchar(100),
    ram varchar(50),
    disco_duro varchar(50),
    tarjeta_video varchar(100),
    tarjeta_sonido varchar(100),
    tarjeta_wifi varchar(100),
    tarjeta_red varchar(100),
    quemador varchar(100),
    monitor char(2),
    numero_serie_monitor varchar(100),
    cable varchar(10),
    numero_serie_cable varchar(100),
    trabajo_realizar text,
    foreign key (id_servicio) references numeroServicio(id)
);

