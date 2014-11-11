--liquibase formatted sql

--changeset PADMA:42
UPDATE TITLE SET CATEGORY_ID = 7 WHERE ORIGINAL_TITLE = 'किछु दिन मोने मोने';
UPDATE TITLE SET CATEGORY_ID = 7 WHERE  ORIGINAL_TITLE = 'भजन रो गुड़क रहयो गाड़ो';
UPDATE TITLE SET CATEGORY_ID = 7 WHERE ORIGINAL_TITLE = 'हिए काया में';