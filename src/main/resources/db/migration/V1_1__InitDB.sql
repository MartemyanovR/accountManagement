
create table employees (
    id bigint not null auto_increment,
    fio varchar(255),
    post varchar(255),
    role varchar(255),
    status_empl varchar(255),
    primary key (id)
);

create table credentials (
    id bigint not null,
    passwrd varchar(255),
    user_name varchar(255),
    primary key (id)
);

alter table credentials
    add constraint employees_fk
    foreign key (id) references employees (id);