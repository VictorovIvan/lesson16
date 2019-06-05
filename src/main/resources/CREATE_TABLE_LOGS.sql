-- Table: public."LOGS"

-- DROP TABLE public."LOGS";

CREATE TABLE public."LOGS"
(
    "ID" text COLLATE pg_catalog."default",
    "DATE" text COLLATE pg_catalog."default",
    "LOG_LEVEL" text COLLATE pg_catalog."default",
    "MESSAGE" text COLLATE pg_catalog."default",
    "EXCEPTION" text COLLATE pg_catalog."default"
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."LOGS"
    OWNER to postgres;