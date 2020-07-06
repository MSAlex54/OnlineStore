create table categories
(
    id                 bigint not null auto_increment,
    title              varchar(255),
    parent_category_id bigint,
    primary key (id)
) engine = InnoDB;

GO

create table products
(
    id          bigint not null auto_increment,
    description varchar(255),
    path_to_img varchar(255),
    price       decimal(19, 2),
    title       varchar(255),
    category_id bigint,
    primary key (id)
) engine = InnoDB;

GO

create table roles
(
    id   bigint       not null auto_increment,
    name varchar(255) not null,
    primary key (id)
) engine = InnoDB;

GO

create table users
(
    id         bigint       not null auto_increment,
    email      varchar(255),
    first_name varchar(255),
    last_name  varchar(255),
    user_name       varchar(32)  not null,
    password   varchar(128) not null,
    primary key (id)
) engine = InnoDB;

GO

create table users_roles
(
    user_id bigint not null,
    role_id bigint not null,
    primary key (user_id, role_id)
) engine = InnoDB;

GO

alter table roles
    add constraint UK_ofx66keruapi6vyqpv6f2or37 unique (name);

GO

alter table categories
    add constraint FK9il7y6fehxwunjeepq0n7g5rd
        foreign key (parent_category_id)
            references categories (id);

GO

alter table products
    add constraint FKog2rp4qthbtt2lfyhfo32lsw9
        foreign key (category_id)
            references categories (id);

GO

alter table users_roles
    add constraint FKj6m8fwv7oqv74fcehir1a9ffy
        foreign key (role_id)
            references roles (id);

GO

alter table users_roles
    add constraint FK2o0jvgh89lemvvo17cbqvdxaa
        foreign key (user_id)
            references users (id);

GO