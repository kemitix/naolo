create sequence hibernate_sequence start with 1 increment by 1
create table veterinarians (id bigint not null, name varchar(255), primary key (id))
create table veterinarian_specialisations (VeterinarianJPA_id bigint not null, specialisations varchar(255))
alter table veterinarian_specialisations add constraint FKnleyt87nkftdjccsa8fyv10fp foreign key (VeterinarianJPA_id) references veterinarians
