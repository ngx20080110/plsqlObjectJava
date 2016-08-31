create or replace PACKAGE pkg_test IS 
 PROCEDURE add (
    oo_test  IN OUT OBJ_test,
    o_result       OUT VARCHAR2
  ) ;
  procedure test_array_object(
    o_arr_obj_test out arr_obj_test,
    o_result       OUT VARCHAR2
  );
  procedure test_inout_array_object(
    o_arr_obj_test in out arr_obj_test,
    o_result       OUT VARCHAR2
  );
  procedure test_integer_object(
    p_obj_person in out obj_person,
    p_result out varchar2
  );
  
END;
/

create or replace PACKAGE BODY pkg_test IS
  PROCEDURE add (
    oo_test  IN OUT OBJ_test,
    o_result       OUT VARCHAR2
  ) IS
  BEGIN
    o_result := 'OK';

    -- Insert record into MMBSAPP
    oo_test.ins();
    
    oo_test.l_a_varchar2 := 'wugy';

  EXCEPTION WHEN OTHERS THEN
    ROLLBACK;
    o_result := sqlerrm || chr(10) || DBMS_UTILITY.FORMAT_ERROR_BACKTRACE;
  END add;
  procedure test_array_object(
    o_arr_obj_test out arr_obj_test,
    o_result       OUT VARCHAR2
  )is
    l_obj_test obj_test;
  begin
    o_result := 'OK';
    o_arr_obj_test := arr_obj_test();
    o_arr_obj_test.extend;
    l_obj_test := obj_test();
    l_obj_test.l_a_varchar2 := 'test1';
    o_arr_obj_test(1) := l_obj_test;
    o_arr_obj_test.extend;
    l_obj_test := obj_test();
    l_obj_test.l_a_varchar2 := 'test2';
    o_arr_obj_test(2):= l_obj_test;
  EXCEPTION WHEN OTHERS THEN
    ROLLBACK;
    o_result := sqlerrm || chr(10) || DBMS_UTILITY.FORMAT_ERROR_BACKTRACE;
  end test_array_object;
  procedure test_inout_array_object(
    o_arr_obj_test in out arr_obj_test,
    o_result       OUT VARCHAR2
  )is
  begin
    o_result := 'OK';
    for i in o_arr_obj_test.first .. o_arr_obj_test.last
    loop
      o_arr_obj_test(i).l_a_varchar2 := 'pkg modify ' || i;
    end loop;
  EXCEPTION WHEN OTHERS THEN
    ROLLBACK;
    o_result := sqlerrm || chr(10) || DBMS_UTILITY.FORMAT_ERROR_BACKTRACE;
  end test_inout_array_object;
  
  
  procedure test_integer_object(
    p_obj_person in out obj_person,
    p_result out varchar2
  ) is
  begin
	p_obj_person.name := 'Name ' || p_obj_person.id;
	if (p_obj_person.age is not null) then
	  p_obj_person.age := p_obj_person.age + 1;
	else
	  p_obj_person.age := 19;
	end if;
	p_result := 'OK';
  EXCEPTION WHEN OTHERS THEN
    ROLLBACK;
    p_result := sqlerrm || chr(10) || DBMS_UTILITY.FORMAT_ERROR_BACKTRACE;
  end test_integer_object;
END;
/


  CREATE type arr_obj_test is table of obj_test;