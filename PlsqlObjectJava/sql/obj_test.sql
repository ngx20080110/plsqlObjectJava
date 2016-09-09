create or replace TYPE OBJ_test FORCE AS OBJECT (

  l_a_number number,
  l_a_char   char(2),
  l_a_varchar2 varchar2(20),
  l_a_nvarchar2 nvarchar2(20),
  l_a_date   date,
  l_a_clob clob,
  l_a_blob blob,
  
  CONSTRUCTOR FUNCTION OBJ_test RETURN SELF AS RESULT,

  MEMBER PROCEDURE ins

);
/

create or replace TYPE BODY OBJ_test IS
  CONSTRUCTOR FUNCTION OBJ_test RETURN SELF AS RESULT IS
  BEGIN
    return;
  END;

  MEMBER PROCEDURE ins IS
  BEGIN
    INSERT INTO test_table
      (a_number, a_char, a_varchar2, a_nvarchar2, a_date, a_clob, a_blob)
      VALUES
      (l_a_number, l_a_char, l_a_varchar2, l_a_nvarchar2, l_a_date, l_a_clob, l_a_blob);
  END ins;

END;
/