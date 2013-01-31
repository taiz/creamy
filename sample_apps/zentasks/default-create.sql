create table project (
  id                        integer not null,
  name                      varchar(255),
  folder                    varchar(255),
  constraint pk_project primary key (id))
;

create table task (
  id                        integer not null,
  title                     varchar(255),
  done                      boolean,
  due_date                  timestamp,
  assigned_to_email         varchar(255),
  folder                    varchar(255),
  project_id                integer,
  constraint pk_task primary key (id))
;

create table account (
  email                     varchar(255) not null,
  name                      varchar(255),
  password                  varchar(255),
  constraint pk_account primary key (email))
;


create table project_account (
  project_id                     integer not null,
  account_email                  varchar(255) not null,
  constraint pk_project_account primary key (project_id, account_email))
;
create sequence project_seq;

create sequence task_seq;

create sequence account_seq;

alter table task add constraint fk_task_assignedTo_1 foreign key (assigned_to_email) references account (email);
create index ix_task_assignedTo_1 on task (assigned_to_email);
alter table task add constraint fk_task_project_2 foreign key (project_id) references project (id);
create index ix_task_project_2 on task (project_id);



alter table project_account add constraint fk_project_account_project_01 foreign key (project_id) references project (id);

alter table project_account add constraint fk_project_account_account_02 foreign key (account_email) references account (email);
