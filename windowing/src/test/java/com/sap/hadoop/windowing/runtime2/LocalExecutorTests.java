package com.sap.hadoop.windowing.runtime2;

//import junit.framework.Assert;

import org.junit.Test;

import com.sap.hadoop.windowing.WindowingException;
import com.sap.hadoop.windowing.testutils.LocalExecutorTest;

public class LocalExecutorTests extends LocalExecutorTest
{
	@Test
	public void test1() throws WindowingException
	{
		wshell.execute("select  p_mfgr,p_name, p_size,\n" +
				"rank() as r,\n" +
				"denserank() as dr\n" +
				"from part_tiny\n" +
				"partition by p_mfgr\n" +
				"order by p_mfgr\n" +
				"window w1 as rows between 2 preceding and 2 following\n" +
				"into path='/tmp/wout2'\n" +
				"serde 'org.apache.hadoop.hive.serde2.lazy.LazySimpleSerDe'\n" +
				"with serdeproperties('field.delim'=',')\n" +
				"format 'org.apache.hadoop.mapred.TextOutputFormat'", outPrinter);
		
		String r = outStream.toString();
		r = r.replace("\r\n", "\n");
		System.out.println(r);
		//String e = WindowingTestsFactory.BASIC.getResult().replaceAll("\t", ",");
		//Assert.assertEquals(r, e);
	}
}
