drop database if exists tienda_db_in5bm;

create database tienda_db_in5bm;

use tienda_db_in5bm;
 
create table usuario(
	id_usuario int auto_increment not null primary key,
    nombre_usuario varchar(60) not null,
    apellido_usuario varchar(60) not null,
    edad_usuario int not null
);
 
create table categoria(
	id_categoria int auto_increment not null primary key,
    nombre_categoria varchar(60) not null,
    descripcion_categoria varchar(150)
);
 
create table producto(
	id_producto int auto_increment not null primary key,
    nombr_producto varchar(80) not null,
    precio_producto decimal(10,2) not null,
    stock int not null,
    id_categoria int not null,
    constraint fk_producto_categoria
        foreign key (id_categoria)
        references categoria(id_categoria)
        ON DELETE CASCADE
);
 
create table pedido(
	id_pedido int auto_increment not null primary key,
    fecha_pedido varchar(60) not null,
    total_pedido decimal(10,2) not null,
    id_usuario int not null,
    constraint fk_pedido_usuario
        foreign key (id_usuario)
        references usuario(id_usuario)
	    ON DELETE CASCADE
);
 
create table detalle_pedido(
	id_detalle int auto_increment not null primary key,
    cantidad int not null,
    precio_unitario decimal(10,2) not null,
    id_pedido int not null,
    id_producto int not null,
    constraint fk_detalle_pedido
        foreign key (id_pedido)
        references pedido(id_pedido)
		ON DELETE CASCADE,
        constraint fk_detalle_producto
        foreign key (id_producto)
        references producto(id_producto)
		ON DELETE CASCADE
);
 
 insert into Usuario(nombre_usuario, apellido_usuario, edad_usuario) values("eros", "duarte", 16);
  insert into Usuario(nombre_usuario, apellido_usuario, edad_usuario) values("mario", "hernandez", 16);

 insert into Categoria(nombre_categoria, descripcion_categoria) values("comida", "categoria de comida");
 insert into Producto(nombr_producto, precio_producto, stock, id_categoria) values("Arroz", 5.50, 200, 1);
 insert into Pedido(fecha_pedido, total_pedido, id_usuario) values("2026-01-01", 190.20, 1);
 insert into detalle_pedido(cantidad, precio_unitario, id_pedido, id_producto) values(10, 29.00, 1, 1);
select * from usuario;
 

