alter table roles
    drop index UK_ofx66keruapi6vyqpv6f2or37;

alter table roles
    drop constraint UK_ofx66keruapi6vyqpv6f2or37;

alter table user_roles
    drop constraint FKh8ciramu9cc9q3qcqiv4ue8a6;

alter table user_roles
    drop constraint FKhfh9dx7w3ubf1co1vdev94g3f;

drop table roles;
drop table user_roles;
drop table users;