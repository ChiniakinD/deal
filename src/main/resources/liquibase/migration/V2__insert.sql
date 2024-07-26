--liquibase formatted sql

--changeset ChiniakinD:7
--comment: create table deal_type
insert into deal_type (id, name)
values ('CREDIT', 'Кредитная сделка'),
       ('OVERDRAFT', 'Овердрафт'),
       ('OTHER', 'Иное');

--changeset ChiniakinD:8
--comment: create table deal_status
insert into deal_status (id, name)
values ('DRAFT', 'Черновик'),
       ('ACTIVE', 'Утвержденная'),
       ('CLOSED', 'Закрытая');

--changeset ChiniakinD:9
--comment: create table contractor_role
insert into contractor_role (id, name, category)
values ('BORROWER', 'Заемщик', 'BORROWER'),
       ('DRAWER', 'Векселедатель', 'BORROWER'),
       ('ISSUER', 'Эмитент', 'BORROWER'),
       ('WARRANTY', 'Поручитель', 'WARRANTY'),
       ('GARANT', 'Гарант', 'WARRANTY'),
       ('PLEDGER', 'Залогодатель', 'WARRANTY');
