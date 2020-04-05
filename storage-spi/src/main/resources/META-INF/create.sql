create sequence hibernate_sequence start with 1 increment by 1;
create table vet_specialisations (value varchar(255) not null, primary key (value));
create table veterinarians (id bigint not null, name varchar(255), primary key (id));
create table veterinarians_vet_specialisations (VeterinarianJPA_id bigint not null, specialisations_value varchar(255) not null, primary key (VeterinarianJPA_id, specialisations_value));
alter table veterinarians_vet_specialisations add constraint FK83t86irotgtj96kh1b7dw2vq0 foreign key (specialisations_value) references vet_specialisations;
alter table veterinarians_vet_specialisations add constraint FKo2a8xparv7x3llsgfnxx01kr5 foreign key (VeterinarianJPA_id) references veterinarians;
