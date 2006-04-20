package org.eclipse.birt.tests.data.engine.api;

import org.eclipse.birt.data.engine.api.IBaseExpression;
import org.eclipse.birt.data.engine.api.IPreparedQuery;
import org.eclipse.birt.data.engine.api.IQueryResults;
import org.eclipse.birt.data.engine.api.IResultIterator;
import org.eclipse.birt.data.engine.api.querydefn.BaseTransform;
import org.eclipse.birt.data.engine.api.querydefn.GroupDefinition;
import org.eclipse.birt.data.engine.api.querydefn.OdaDataSetDesign;
import org.eclipse.birt.data.engine.api.querydefn.QueryDefinition;
import org.eclipse.birt.data.engine.api.querydefn.ScriptExpression;
import org.eclipse.birt.tests.data.DataTestCase;

import testutil.ConfigText;

public class MultiPassTest extends DataTestCase {

	protected DataSourceInfo getDataSourceInfo() {
		return new DataSourceInfo( ConfigText.getString( "Api.TestData.TableName" ),
				ConfigText.getString( "Api.TestData.TableSQL" ),
				ConfigText.getString( "Api.TestData.TestDataFileName" ) );
	}
	/**
	 * Test feature of
	 * 		aggregation expression 
	 */
	public void test_RunningAggregationExpression() throws Exception
	{
		// Test a SQL with duplicate column name (quite common with join data sets)
		String testSQL =  "select COUNTRY, AMOUNT from " + getTestTableName( );
		((OdaDataSetDesign)this.dataSet).setQueryText( testSQL );
		
		IBaseExpression[] expressions = new IBaseExpression[]{
				new ScriptExpression( "row.COUNTRY", 0 ),
				new ScriptExpression( "row.AMOUNT", 2 ),
				new ScriptExpression( "Total.Sum( row.AMOUNT )", 2 ) };

		GroupDefinition[] groupDef = new GroupDefinition[]{ new GroupDefinition( "G1" ), new GroupDefinition( "G2" ) };
		groupDef[0].setKeyExpression(
				"row.COUNTRY" );
		
		groupDef[1].setKeyExpression("Total.Sum( row.AMOUNT,null,1 )");		
		
		// define a query design				
		QueryDefinition queryDefn = newReportQuery( );
		queryDefn.addGroup( groupDef[0] );
		queryDefn.addGroup( groupDef[1] );
		
		
		queryDefn.addExpression(expressions[0], BaseTransform.ON_EACH_ROW);
		queryDefn.addExpression(expressions[1], BaseTransform.AFTER_LAST_ROW);	
		
		IPreparedQuery preparedQuery = dataEngine.prepare( queryDefn );
		IQueryResults queryResults = preparedQuery.execute( null );
		IResultIterator resultIt = queryResults.getResultIterator( );
		assertTrue( resultIt.next() );

		resultIt.getValue( expressions[0] );
		resultIt.getValue( expressions[1] );		
	}
	
}