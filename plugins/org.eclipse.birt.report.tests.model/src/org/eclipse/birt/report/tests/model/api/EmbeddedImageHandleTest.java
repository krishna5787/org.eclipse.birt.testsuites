package org.eclipse.birt.report.tests.model.api;

import java.util.List;
import java.util.Locale;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.birt.report.model.api.DesignEngine;
import org.eclipse.birt.report.model.api.EmbeddedImageHandle;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.SessionHandle;
import org.eclipse.birt.report.model.api.SimpleValueHandle;
import org.eclipse.birt.report.model.elements.ReportDesign;
import org.eclipse.birt.report.tests.model.BaseTestCase;

public class EmbeddedImageHandleTest extends BaseTestCase
{
    String filename = "Improved_test6.xml";
    protected static final String pluginpath = System.getProperty("eclipse.home")+"/plugins/"+ PLUGIN_NAME +"/bin/";
	public EmbeddedImageHandleTest(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	 public static Test suite(){
			
			return new TestSuite(EmbeddedImageHandleTest.class);
		}
		protected void setUp( ) throws Exception
		{
			super.setUp( );
		}
	
		public void testDrop( ) throws Exception
		{
	
			SessionHandle sessionHandle = DesignEngine.newSession( Locale.ENGLISH );
			ReportDesignHandle designHandle = sessionHandle.openDesign(pluginpath+getFullQualifiedClassName( ) + INPUT_FOLDER+filename);
			
			SimpleValueHandle propHandle = (SimpleValueHandle)designHandle.getPropertyHandle( ReportDesign.IMAGES_PROP );
			
	
			EmbeddedImageHandle image1handle = (EmbeddedImageHandle) designHandle
			                                   .findImage("group confirmation logo.jpg").getHandle(propHandle);
			EmbeddedImageHandle image2handle = (EmbeddedImageHandle) designHandle
			                                   .findImage("circles.png").getHandle(propHandle);
		
			image1handle.drop();
			
			List value = propHandle.getListValue( );
			assertEquals( 1, value.size( ) );
			assertEquals( image2handle.getStructure( ), value.get( 0 ) );
			assertNull( image1handle.getStructure( ) );

			image2handle.drop();
			assertEquals( 0, value.size( ) );
			assertNull( image2handle.getStructure( ) );
		}

}