create or replace TYPE OBJ_person FORCE AS OBJECT (

  id integer,
  age integer,
  name varchar2(32),
  
  CONSTRUCTOR FUNCTION OBJ_person RETURN SELF AS RESULT
);
/

create or replace TYPE BODY OBJ_person IS
  CONSTRUCTOR FUNCTION OBJ_person RETURN SELF AS RESULT IS
  BEGIN
    return;
  END;

END;
/