--liquibase formatted sql

--changeset SSWAROOP:43
CREATE TABLE word
(

	id serial NOT NULL,
  
	word_original character varying(100) NOT NULL,
  
	word_translation character varying(100) NOT NULL,
  
	word_transliteration character varying(100) NOT NULL,
  
	show_on_landing_page boolean NOT NULL,
  
	intr_summary_original character varying(500),
  
	intr_summary_translation character varying(500),
  
	intr_summary_transliteration character varying(500),
  
	CONSTRAINT word_pkey PRIMARY KEY (id)
);
);
--rollback drop table WORD;