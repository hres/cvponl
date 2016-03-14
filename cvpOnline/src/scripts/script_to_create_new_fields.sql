SET ECHO ON
SPOOL script_to_create_new_fields.lst

--===========================================================================
-- Name        : script_to_create_new_fields.sql
-- Description : Modify CVP ONLINE tables
-- Note        :
-- Usage       : Run as CVPONLINE_OWNER
-- Created by  : Diane Beauvais
--============================================================================


-- 
-- add new fields to existing tables
-- 

ALTER TABLE REPORTS ADD (INDICATION_NAME_ENG VARCHAR2(4000 CHAR));
ALTER TABLE REPORTS ADD (INDICATION_NAME_FR VARCHAR2(4000 CHAR));

COMMIT;
SPOOL OFF