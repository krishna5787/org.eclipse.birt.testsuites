/*******************************************************************************
 * Copyright (c) 2004 Actuate Corporation. All rights reserved. This program and
 * the accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html Contributors: Actuate Corporation -
 * initial API and implementation
 ******************************************************************************/

package org.eclipse.birt.report.tests.model.regression;

import org.eclipse.birt.report.model.api.FilterConditionHandle;
import org.eclipse.birt.report.model.api.TableHandle;
import org.eclipse.birt.report.tests.model.BaseTestCase;

/**
 * <b>Bug Description:</b>
 * <p>
 * Exception is thrown out while running the attached report
 * <p>
 * <b>Test Description:</b>
 * <p>
 * The reason for the bug is not create column binding on the outer table.
 * <p>
 * Open the old report, check the column binding is created for the outer table
 * and row[0] is changed to row._out
 */
public class Regression_149621 extends BaseTestCase
{

	private String filename = "Regression_149621.xml"; //$NON-NLS-1$
	private String outfile = "Regression_149621_out.xml"; //$NON-NLS-1$

	/**
	 * @throws Exception
	 */
	public void test_regression_149621( ) throws Exception
	{
		openDesign( filename );
		saveAs( outfile );

		String input = getClassFolder( ) + OUTPUT_FOLDER + outfile;
		String output = getClassFolder( ) + INPUT_FOLDER
				+ "Regression_149621_1.xml"; //$NON-NLS-1$
		copyFile( input, output );

		openDesign( "Regression_149621_1.xml" ); //$NON-NLS-1$
		TableHandle outtable = (TableHandle) designHandle.findElement( "outer" ); //$NON-NLS-1$

		// column binding is created automatically
		assertNotNull( outtable.getColumnBindings( ) );

		TableHandle innertable = (TableHandle) designHandle
				.findElement( "inner" ); //$NON-NLS-1$
		FilterConditionHandle filter = (FilterConditionHandle) innertable
				.filtersIterator( )
				.next( );
		assertEquals( "row._outer[\"EMPLOYEENUMBER\"]", filter.getValue1( ) ); //$NON-NLS-1$

	}
}