/*******************************************************************************
 * Copyright (c) 2004 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *******************************************************************************/

package org.eclipse.birt.report.tests.engine.api;

import java.util.Date;
import java.util.Locale;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.birt.report.engine.api.IScalarParameterDefn;
import org.eclipse.birt.report.engine.api.ReportParameterConverter;
import org.eclipse.birt.report.tests.engine.EngineCase;

public class ReportParameterConverterTest extends EngineCase {

	/**
	 * @param name
	 */
	public ReportParameterConverterTest(String name) {
		super(name);
	}
	
	/**
	 * Test suite
	 * @return
	 */
	public static Test suite(){
		return new TestSuite(ReportParameterConverterTest.class);
	}

	/**
	 * Test format(java.lang.Object reportParameterObj) method
	 *
	 */
	public void testFormat(){
		Object pStr=new String("p1Value");
		Object pDate=new Date("2005/05/06");
		Object pBool=new Boolean("false");
		Object pInt=new Integer(2);
		Object pFloat=new Float(0.25);
		//string parameter
		ReportParameterConverter converter=new ReportParameterConverter("(@@)",Locale.US);
		String pGet="";
		pGet=converter.format(pStr);
		assertEquals("format() fail","p1Val(ue)",pGet);
		//datetime parameter
		converter=new ReportParameterConverter("yyyy",Locale.US);
		pGet=converter.format(pDate);
		assertEquals("format() fail","2005",pGet);
		//boolean parameter
		converter=new ReportParameterConverter("",Locale.US);
		pGet=converter.format(pBool);
		assertEquals("format() fail","false",pGet);
		
		//float parameter
		/*
		converter=new ReportParameterConverter(null,Locale.US);
		pGet=converter.format(pFloat);
		assertEquals("format() fail","0.25",pGet);
		*/
		//integer parameter
		/*
		converter=new ReportParameterConverter("",Locale.US);
		pGet=converter.format(pInt);
		assertEquals("format() fail","2",pGet);
		*/
	}
	
	/**
	 * Test parse(java.lang.String reportParameterValue, int parameterValueType) method
	 *
	 */
	public void testParse(){
		String str1="str",date1="2005/05/06",bool1="true";
		String int1="8",float1="3.5",double1="1.352";
		ReportParameterConverter converter=new ReportParameterConverter("(@@)",Locale.US);
		assertTrue("parse() fail",converter.parse(str1,IScalarParameterDefn.TYPE_STRING) instanceof String);
		assertTrue("parse() fail",converter.parse(date1,IScalarParameterDefn.TYPE_DATE_TIME) instanceof Date);
		assertTrue("parse() fail",converter.parse(bool1,IScalarParameterDefn.TYPE_BOOLEAN) instanceof Boolean);
		//assertTrue("parse() fail",converter.parse(float1,IScalarParameterDefn.TYPE_FLOAT) instanceof Float);
	}
}
