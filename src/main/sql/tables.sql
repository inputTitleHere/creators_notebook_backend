create table MEMBER (
	id varchar(30), -- 30글자 한정 아아디.
	name text not null,
	password text not null,
	joined_at date default now(),
	email text not null,
	tier char(1),
	constraint pk_member_id primary key (id),
	constraint ck_member_tier check (tier in ('U','P','A'))
)

--drop table member;
create table PROJECT(
	proj_id uuid,
	proj_name varchar(100) not null,
	proj_owner varchar(30) not null,
	proj_data jsonb,
	proj_share char(1) default 'N',
	CONSTRAINT pk_proj_id primary key(proj_id),
	CONSTRAINT fk_proj_owner FOREIGN KEY (proj_owner) REFERENCES Member(id) on delete cascade,
	CONSTRAINT ck_proj_share check (proj_share in ('N','L','A')) -- N : No, L : Link, A : All
)
alter table project drop column uuid;
-- drop table Project;

create table PROJECT_IMAGE(
	proj_img_id varchar(40),
	proj_id uuid,
	CONSTRAINT pk_proj_img_id primary key(proj_img_id),
	CONSTRAINT fk_proj_id FOREIGN KEY (proj_id) REFERENCES PROJECT(proj_id) on delete cascade
)

drop table project;
drop table project_dto;
drop table project_image_dto;

