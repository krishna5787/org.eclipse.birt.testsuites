/*******************************************************************************
 * Copyright (c) 2004 Actuate Corporation. All rights reserved. This program and
 * the accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Actuate Corporation - initial API and implementation
 ******************************************************************************/

package org.eclipse.birt.report.tests.model.regression;

import java.io.IOException;

import org.eclipse.birt.report.model.api.DesignConfig;
import org.eclipse.birt.report.model.api.DesignEngine;
import org.eclipse.birt.report.model.api.DesignFileException;
import org.eclipse.birt.report.model.api.LibraryHandle;
import org.eclipse.birt.report.model.api.SessionHandle;
import org.eclipse.birt.report.model.api.activity.SemanticException;
import org.eclipse.birt.report.tests.model.BaseTestCase;

import com.ibm.icu.util.ULocale;

/**
 * Regression description:
 * </p>
 * Steps to reproduce:
 * <p>
 * <ol>
 * <li>create 2 libraries: LibA & LibB.
 * <li>LibA include LibB.
 * <li>LibB include LibA.
 * </ol>
 * 
 * Excepted result: When doing step3, model should check out it as a
 * recursive inclusion, and report an error to user.
 * </p>
 * Test description:
 * <p>
 * TODO:
 * </p>
 */
public class Regression_123377 extends BaseTestCase
{

	private final static String INPUT1 = "regression_123377_lib1.xml"; //$NON-NLS-1$
	private final static String INPUT2 = "regression_123377_lib2.xml"; //$NON-NLS-1$

	/**
	 * @throws DesignFileException
	 * @throws SemanticException
	 * @throws IOException
	 */
	public void test_123377( ) throws DesignFileException, SemanticException,
			IOException
	{
		String lib1Input = getClassFolder( ) + INPUT_FOLDER
				+ "regression_123377_lib1.xml"; //$NON-NLS-1$
		String lib2Input = getClassFolder( ) + INPUT_FOLDER
				+ "regression_123377_lib2.xml"; //$NON-NLS-1$

		String lib1Output = getClassFolder( ) + OUTPUT_FOLDER
				+ "regression_123377_lib1.xml";//$NON-NLS-1$
		String lib2Output = getClassFolder( ) + OUTPUT_FOLDER
				+ "regression_123377_lib2.xml";//$NON-NLS-1$

		// open and modify the library files under the output folder.

		copyFile( lib1Input, lib1Output );
		copyFile( lib2Input, lib2Output );

		SessionHandle sessionHandle = new DesignEngine( new DesignConfig( ) )
				.newSessionHandle( ULocale.ENGLISH );
		LibraryHandle lib1 = sessionHandle.openLibrary( lib1Output );
		LibraryHandle lib2 = sessionHandle.openLibrary( lib2Output );

		// lib1 include lib2

		lib1.includeLibrary( INPUT2, "regression_123377_lib2" ); //$NON-NLS-1$
		lib1.saveAs( lib1Output );

		// make sure that lib2 can not include lib1.

		try
		{
			lib2.includeLibrary( INPUT1, "regression_123377_lib1" ); //$NON-NLS-1$
			fail( );
		}
		catch ( Exception e )
		{
			// success
		}
	}
}
