package com.ngx20080110.mains;

import com.ngx20080110.integer.test.IntegerTest;
import com.ngx20080110.tableindexbyvarchar2.TestTableIndexByVarchar2;

public class MasterMain {

	public static void main(String[] args) {
		IntegerTest it = new IntegerTest();
		it.test();
		
		TestTableIndexByVarchar2 ttibv = new TestTableIndexByVarchar2();
		ttibv.test();
	}

}
