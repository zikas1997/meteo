create table public.atmosphere(
 id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
 date_time timestamp without time zone,
 height numeric,
 temperature numeric,
 primary key (id)
);

create table public.radiation(
 id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
 complex integer,
 device character varying(1000),
 parameter integer,
 middleDate timestamp without time zone,
 middleValue numeric,
 minDate timestamp without time zone,
 minValue numeric,
 maxDate timestamp without time zone,
 maxValue numeric,
 primary key (id)
);

create table public.parameter(
 id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
 title character varying(100),
 number integer,
 act_number integer,
 full_name character varying(1000),
 unit_id integer,
 accuracy numeric,
 type character varying(100),
 primary key (id)
);

INSERT INTO public.parameter(title,full_name) VALUES ('F1', 'относительная влажность');
INSERT INTO public.parameter(title,full_name) VALUES ('J1', 'явления погоды');
INSERT INTO public.parameter(title,full_name) VALUES ('M1', 'видимость');
INSERT INTO public.parameter(title,full_name) VALUES ('O1', 'кол-во осадков');
INSERT INTO public.parameter(title,full_name) VALUES ('P1', 'давление');
INSERT INTO public.parameter(title,full_name) VALUES ('T1', 'температура воздуха');
INSERT INTO public.parameter(title,full_name) VALUES ('W1', 'данные ветра');
INSERT INTO public.parameter(title,full_name) VALUES ('W2', 'данные ветра');
INSERT INTO public.parameter(title,full_name) VALUES ('W3', 'данные ветра');

create table public.station(
 id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
 title character varying(100),
 number integer,
 type integer,
 longitude numeric,
 latitude numeric,
 distircit_id integer,
 primary key (id)
);

INSERT INTO public.station(title, type) VALUES ('Станция температурно-ветровой стратификации Останкинской телебашни', 3);

create table public.station_parameter(
	id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
 	station_id bigint  references public.station(id),
	parameter_id bigint  references public.parameter(id),
	primary key (id)
);

INSERT INTO public.station_parameter(station_id, parameter_id) VALUES (1, 1);
INSERT INTO public.station_parameter(station_id, parameter_id) VALUES (1, 2);
INSERT INTO public.station_parameter(station_id, parameter_id) VALUES (1, 3);
INSERT INTO public.station_parameter(station_id, parameter_id) VALUES (1, 4);
INSERT INTO public.station_parameter(station_id, parameter_id) VALUES (1, 5);
INSERT INTO public.station_parameter(station_id, parameter_id) VALUES (1, 6);
INSERT INTO public.station_parameter(station_id, parameter_id) VALUES (1, 7);
INSERT INTO public.station_parameter(station_id, parameter_id) VALUES (1, 8);
INSERT INTO public.station_parameter(station_id, parameter_id) VALUES (1, 9);

create table public.research(
 id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
 date timestamp without time zone,
 station_id bigint references public.station(id),
 parameter_id bigint references public.parameter(id),
 height numeric,
 research_value numeric,
 modify_value numeric,
 primary key (id)
);