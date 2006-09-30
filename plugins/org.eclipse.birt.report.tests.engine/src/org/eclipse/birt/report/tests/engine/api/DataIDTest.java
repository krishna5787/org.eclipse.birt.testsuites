
package org.eclipse.birt.report.tests.engine.api;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.birt.report.engine.api.DataID;
import org.eclipse.birt.report.engine.api.DataSetID;
import org.eclipse.birt.report.engine.api.EngineException;
import org.eclipse.birt.report.engine.api.HTMLRenderOption;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IRunAndRenderTask;
import org.eclipse.birt.report.engine.api.InstanceID;
import org.eclipse.birt.report.tests.engine.EngineCase;

/**
 * <b>Test DataID API</b>
 */
public class DataIDTest extends EngineCase
{

	private String inPath = getClassFolder( ) + "/" + INPUT_FOLDER + "/";

	/**
	 * Test DataID methods with input report design
	 * @throws EngineException
	 * @throws IOException 
	 */
	public void test_DataIDFromReport( ) throws EngineException,
			IOException
	{
		String reportName = "dataID.rptdesign";
		IReportRunnable reportRunnable = engine.openReportDesign( inPath
				+ reportName );
		HTMLRenderOption options = new HTMLRenderOption( );
		options.setOutputFormat( "html" );
		ByteArrayOutputStream ostream = new ByteArrayOutputStream( );
		options.setOutputStream( ostream );
		IRunAndRenderTask task = engine.createRunAndRenderTask( reportRunnable );
		task.setRenderOption( options );
		task.run( );
		assertTrue( task.getErrors( ).size( ) <= 0 );
		task.close( );

		// get instance id of two tables and one list in report
		ArrayList iids = new ArrayList( );
		String content = ostream.toString( "utf-8" );
		ostream.close( );
		Pattern typePattern = Pattern.compile( "(element_type=\"TABLE"
				+ "\".*iid=\".*\")" );
		Matcher matcher = typePattern.matcher( content );
		String strIid = null;
		while ( matcher.find( ) )
		{
			String tmp_type = null;
			tmp_type = matcher.group( 0 );
			strIid = tmp_type.substring( tmp_type.indexOf( "iid" ) );
			strIid = strIid.substring( 5, strIid.indexOf( "\"", 6 ) );
			iids.add( strIid );
		}
		typePattern = Pattern.compile( "(element_type=\"LIST"
				+ "\".*iid=\".*\")" );
		matcher = typePattern.matcher( content );
		while ( matcher.find( ) )
		{
			String tmp_type = null;
			tmp_type = matcher.group( 0 );
			strIid = tmp_type.substring( tmp_type.indexOf( "iid" ) );
			strIid = strIid.substring( 5, strIid.indexOf( "\"", 6 ) );
			iids.add( strIid );
		}

		// DataID: dataSet:0
		InstanceID iid = InstanceID.parse( iids.get( 0 ).toString( ) );
		DataID dataID = iid.getDataID( );
		assertEquals( iid.toString( ), "/6(" + dataID.getDataSetID( ) + ":"
				+ dataID.getRowID( ) + ")" );
		// DataID: {dataSet}.0.group:0
		iid = InstanceID.parse( iids.get( 1 ).toString( ) );
		dataID = iid.getDataID( );
		assertEquals( iid.toString( ), "/52(" + dataID.getDataSetID( ) + ":"
				+ dataID.getRowID( ) + ")" );
		// DataID:{{dataSet}.0.group}.0.group1:0
		iid = InstanceID.parse( iids.get( 2 ).toString( ) );
		dataID = iid.getDataID( );
		assertEquals( iid.toString( ), "/61(" + dataID.getDataSetID( ) + ":"
				+ dataID.getRowID( ) + ")" );
	}

	/**
	 * Test getDataSetID() method
	 */
	public void test_getDataSetID( )
	{
		DataSetID dsID = new DataSetID( null, 0, "query0" );
		DataID dataID = new DataID( dsID, 1 );
		assertEquals( dsID, dataID.getDataSetID( ) );

		dataID = new DataID( null, 0 );
		assertNull( dataID.getDataSetID( ) );
	}

	/**
	 * Test getDataSetID() method
	 */
	public void test_getRowID( )
	{
		DataID dataID = new DataID( null, 0 );
		assertEquals( 0, dataID.getRowID( ) );

		dataID = new DataID( null, 1 );
		assertEquals( 1, dataID.getRowID( ) );

		dataID = new DataID( null, -1 );
		assertEquals( -1, dataID.getRowID( ) );

		dataID = new DataID( null, Long.MAX_VALUE );
		assertEquals( Long.MAX_VALUE, dataID.getRowID( ) );

		dataID = new DataID( null, Long.MIN_VALUE );
		assertEquals( Long.MIN_VALUE, dataID.getRowID( ) );
	}

	/**
	 * Test append() method
	 */
	public void test_append( )
	{
		DataSetID dsID = new DataSetID( "ds1" );
		DataID dataID = new DataID( dsID, 0 );
		dataID.append( new StringBuffer( "buffer" ) );
		assertEquals( dsID, dataID.getDataSetID( ) );
		// TODO: no enough javadoc
		 dataID=new DataID(null,0);
		 dataID.append( new StringBuffer("buffer") );
		 assertNull(dataID.getDataSetID( ));
	}

	/**
	 * Test toString() method
	 */
	public void test_toString( )
	{
		DataSetID dsID = new DataSetID( "ds1" );
		DataID dataID = new DataID( dsID, 0 );
		assertEquals( "ds1:0", dataID.toString( ) );

		dsID = new DataSetID( "���ݼ�" );
		dataID = new DataID( dsID, 0 );
		assertEquals( "���ݼ�:0", dataID.toString( ) );

		dsID = new DataSetID( "�ˤۤ����" );
		dataID = new DataID( dsID, 0 );
		assertEquals( "�ˤۤ����:0", dataID.toString( ) );

		dsID = new DataSetID( "~!@#$%^&*()_+?>:" );
		dataID = new DataID( dsID, 0 );
		assertEquals( "~!@#$%^&*()_+?>::0", dataID.toString( ) );

		dsID = new DataSetID( null );
		dataID = new DataID( dsID, 0 );
		assertEquals( "null:0", dataID.toString( ) );
		
		dataID=new DataID(dsID,Long.MAX_VALUE);
		assertEquals("null:"+Long.MAX_VALUE,dataID.toString( ));
		// TODO: no enough javadoc
	}

}