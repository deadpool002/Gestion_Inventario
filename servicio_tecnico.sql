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
select * from usuario u inner join bitacora b on u.id=b.id where b.id=1;
Create Table cliente(
	id int auto_increment,
    carnetIdentidad int,
    nombre varchar(100),
    numeroTelefono varchar(12),
    fechaRegistro datetime,
    direccion varchar(100),
    primary key(id,carnetIdentidad)
);

insert into cliente(carnetIdentidad,nombre,numeroTelefono,fechaRegistro)values
(14176648,'Juan Pablo','78670128',now());



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
select * from cliente;
select * from numeroServicio;
Update numeroServicio set fecha_registro ='2019-06-05' where id = 4;
insert into numeroServicio(id_cliente,fecha_registro) values
(4,NOW());
select max(id) from numeroServicio ;

select date(S.fecha_registro),time(S.fecha_registro),C.carnetIdentidad,
C.nombre,S.id_cliente
from numeroServicio S inner join cliente C on S.id_cliente = C.id where S.id=11;

drop table soporte_impresora;
drop table soporte_portatil;
drop table soporte_pc;
drop table soporte_otros;
drop table numeroServicio;
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
INSERT INTO soporte_impresora
	(id_servicio,marca,modelo,numero_serie,cartuchos,color,negro,descripcion_trabajo)
VALUES(5,'CANON','asdas','356235685','NO','','','alkshdasmdkjashg');
select * from soporte_impresora;

Update soporte_impresora SET
	marca='asd',modelo='asd',numero_serie='asd',
    cartuchos='NO',color='254651',negro='656',descripcion_trabajo='54'
WHERE id_servicio = 28;

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
select date(S.fecha_registro),time(S.fecha_registro),date(S.fecha_entrega),time(S.fecha_entrega),
	S.costo_repuesto,S.costo_servicio,C.carnetIdentidad,C.nombre,C.numeroTelefono,C.direccion,
    I.marca,I.modelo,I.numero_serie,I.cartuchos,I.color,I.negro,I.descripcion_trabajo
from soporte_impresora I inner join numeroServicio S on I.id_servicio = S.id
						inner join cliente C on C.id = S.id_cliente where I.id_servicio=30;

UPDATE soporte_portatil SET marca='',modelo='',numero_serie='',cargador='',
numero_serie_cargador='',
descripcion_trabajo ='' where id_servicio=17;                        
                        
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

