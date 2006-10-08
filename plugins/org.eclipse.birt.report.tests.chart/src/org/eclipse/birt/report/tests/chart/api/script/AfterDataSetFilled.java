/***********************************************************************
 * Copyright (c) 2004 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Actuate Corporation - initial API and implementation
 ***********************************************************************/

package org.eclipse.birt.report.tests.chart.api.script;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import org.eclipse.birt.chart.device.IDeviceRenderer;
import org.eclipse.birt.chart.exception.ChartException;
import org.eclipse.birt.chart.factory.GeneratedChartState;
import org.eclipse.birt.chart.factory.Generator;
import org.eclipse.birt.chart.factory.IDataRowExpressionEvaluator;
import org.eclipse.birt.chart.factory.RunTimeContext;
import org.eclipse.birt.chart.model.Chart;
import org.eclipse.birt.chart.model.ChartWithAxes;
import org.eclipse.birt.chart.model.attribute.AxisType;
import org.eclipse.birt.chart.model.attribute.Bounds;
import org.eclipse.birt.chart.model.attribute.DataType;
import org.eclipse.birt.chart.model.attribute.SortOption;
import org.eclipse.birt.chart.model.attribute.impl.BoundsImpl;
import org.eclipse.birt.chart.model.component.Axis;
import org.eclipse.birt.chart.model.component.Series;
import org.eclipse.birt.chart.model.component.impl.SeriesImpl;
import org.eclipse.birt.chart.model.data.NumberDataSet;
import org.eclipse.birt.chart.model.data.Query;
import org.eclipse.birt.chart.model.data.TextDataSet;
import org.eclipse.birt.chart.model.data.SeriesDefinition;
import org.eclipse.birt.chart.model.data.impl.NumberDataSetImpl;
import org.eclipse.birt.chart.model.data.impl.QueryImpl;
import org.eclipse.birt.chart.model.data.impl.TextDataSetImpl;
import org.eclipse.birt.chart.model.data.impl.SeriesDefinitionImpl;
import org.eclipse.birt.chart.model.impl.ChartWithAxesImpl;
import org.eclipse.birt.chart.model.type.LineSeries;
import org.eclipse.birt.chart.model.type.impl.LineSeriesImpl;
import org.eclipse.birt.chart.util.PluginSettings;
import org.eclipse.birt.report.tests.chart.ChartTestCase;

/**
 * Test decription:
 * </p>
 * Chart script: afterDataSetFilled()
 * </p>
 */

public class AfterDataSetFilled extends ChartTestCase{

//    private static String GOLDEN = "AfterDataSetFilled.jpg"; //$NON-NLS-1$
//    private static String OUTPUT = "AfterDataSetFilled.jpg"; //$NON-NLS-1$	
//	
//	/**
//	 * Comment for <code>serialVersionUID</code>
//	 */
//	private static final long serialVersionUID = 1L;

	/**
	 * A chart model instance
	 */
	private Chart cm = null;

	/**
	 * The swing rendering device
	 */
//	private IDeviceRenderer dRenderer = null;
//
//	private GeneratedChartState gcs = null;

	/**
	 * execute application
	 * 
	 * @param args
	 */
	public static void main( String[] args )
	{
		AfterDataSetFilled st = new AfterDataSetFilled( );
	}

	/**
	 * Constructor
	 */
	public AfterDataSetFilled()
	{
//		final PluginSettings ps = PluginSettings.instance( );
//		try
//		{
//			dRenderer = ps.getDevice( "dv.JPG" );//$NON-NLS-1$
//
//		}
//		catch ( ChartException ex )
//		{
//			ex.printStackTrace( );
//		}
		cm = createChart( );
		bindGroupingData( cm );
//
//		BufferedImage img = new BufferedImage( 600, 600,
//				BufferedImage.TYPE_INT_ARGB );
//		Graphics g = img.getGraphics( );
//
//		Graphics2D g2d = (Graphics2D) g;
//		dRenderer.setProperty( IDeviceRenderer.GRAPHICS_CONTEXT, g2d );
////		dRenderer.setProperty(IDeviceRenderer.FILE_IDENTIFIER, this
////				.getClassFolder( )
////				+ OUTPUT_FOLDER + OUTPUT); //$NON-NLS-1$
//		dRenderer.setProperty(IDeviceRenderer.FILE_IDENTIFIER, "C:/Documents and Settings/fxu/workspace/org.eclipse.birt.report.tests.chart/src/org/eclipse/birt/report/tests/chart/api/script/output"
//				+ OUTPUT); //$NON-NLS-1$
////		System.out.println(this.getClassFolder( ));
//		Bounds bo = BoundsImpl.create( 0, 0, 600, 600 );
//		bo.scale( 72d / dRenderer.getDisplayServer( ).getDpiResolution( ) );
//
//		Generator gr = Generator.instance( );
//
//		try
//		{
//			gcs = gr.build( dRenderer.getDisplayServer( ), cm, null, bo, null );
//			gr.render( dRenderer, gcs );
//		}
//		catch ( ChartException e )
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace( );
//		}
	}

	
	 private void bindGroupingData( Chart chart )

     {

            // Data Set

            TextDataSet categoryValues = TextDataSetImpl.create( new String[]{

                          "Item 1", "Item 2", "Item 3", "Item 4", "Item 5"} ); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$

            NumberDataSet orthoValues = NumberDataSetImpl.create( new double[]{

                          25, 35, 15, 2, 9

            } );



            final Object[][] data = new Object[][]{

                          {

                                        "x1", new Integer( 1 ), "g1"

                          }, {

                                        "x2", new Integer( 2 ), "g2"

                          }, {

                                        "x3", new Integer( 3 ), "g1"

                          }, {

                                        "x4", new Integer( 4 ), "g3"

                          }, {

                                        "x5", new Integer( 5 ), "g2"

                          }, {

                                        "x6", new Integer( 6 ), "g1"

                          }, {

                                        "x7", new Integer( 7 ), "g3"

                          }, {

                                        "x8", new Integer( 8 ), "g2"

                          }, {

                                        "x9", new Integer( 9 ), "g2"

                          }, {

                                        "x0", new Integer( 0 ), "g2"

                          },

            };



            try

            {

                   Generator gr = Generator.instance( );

                   gr.bindData( new IDataRowExpressionEvaluator( ) {



                          int idx = 0;



                          public void close( )

                          {

                          }



                          public Object evaluate( String expression )

                          {

                                 if ( "X".equals( expression ) )

                                 {

                                        return data[idx][0];

                                 }

                                 else if ( "Y".equals( expression ) )

                                 {

                                        return data[idx][1];

                                 }

                                 else if ( "G".equals( expression ) )

                                 {

                                        return data[idx][2];

                                 }

                                 return null;

                          }



                          public Object evaluateGlobal( String expression )

                          {

                                 return evaluate( expression );

                          }



                          public boolean first( )

                          {

                                 idx = 0;

                                 return true;

                          }



                          public boolean next( )

                          {

                                 idx++;

                                 return ( idx < 9 );

                          }

                   }, chart, new RunTimeContext( ) );

            }

            catch ( ChartException e )

            {

                   e.printStackTrace( );

            }

     }



     private Chart createChart( )

     {

            ChartWithAxes cwaBar = ChartWithAxesImpl.create( );
            
            cwaBar
    		.setScript( "function afterDataSetFilled( series, idsp,Iicsc )" //$NON-NLS-1$
     				+ "{importPackage(Packages.java.lang); " //$NON-NLS-1$
    				+ "if (series.getLabel().isVisible() == true)" //$NON-NLS-1$
    				+ "{System.out.println(\"ok\");} " //$NON-NLS-1$
    				+ "else" //$NON-NLS-1$
    				+ "{System.out.println(\"false\");}} " //$NON-NLS-1$
            );

            // X-Axis

            Axis xAxisPrimary = cwaBar.getPrimaryBaseAxes( )[0];

            xAxisPrimary.setType( AxisType.TEXT_LITERAL );



            // Y-Axis

            Axis yAxisPrimary = cwaBar.getPrimaryOrthogonalAxis( xAxisPrimary );

            yAxisPrimary.setType( AxisType.LINEAR_LITERAL );



            // X-Series

            Series seCategory = SeriesImpl.create( );

            Query xQ = QueryImpl.create( "G" );

            seCategory.getDataDefinition( ).add( xQ );



            SeriesDefinition sdX = SeriesDefinitionImpl.create( );

            xAxisPrimary.getSeriesDefinitions( ).add( sdX );

            sdX.getSeries( ).add( seCategory );



            // -------------------------------------------------------------

            sdX.setSorting( SortOption.ASCENDING_LITERAL );

            sdX.getGrouping( ).setEnabled( true );

            sdX.getGrouping( ).setGroupType( DataType.TEXT_LITERAL );

            sdX.getGrouping( ).setAggregateExpression( "Sum" );

            sdX.getGrouping( ).setGroupingInterval( 0 );

            // -------------------------------------------------------------



            // Y-Series

            LineSeries bs = (LineSeries) LineSeriesImpl.create( );

            bs.getLabel( ).setVisible( true );
           

            Query yQ = QueryImpl.create( "Y" );

            bs.getDataDefinition( ).add( yQ );



            SeriesDefinition sdY = SeriesDefinitionImpl.create( );

            yAxisPrimary.getSeriesDefinitions( ).add( sdY );

            sdY.getSeriesPalette( ).update( 0 );

            sdY.getSeries( ).add( bs );



            return cwaBar;

     }



	
}