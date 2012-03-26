package com.sap.hadoop.windowing.functions

import com.sap.hadoop.windowing.WindowingException;
import com.sap.hadoop.windowing.functions.annotations.FunctionDef;
import com.sap.hadoop.windowing.query.FuncSpec;

class FunctionRegistry
{
	static FunctionTranslator fT = new FunctionTranslator();
	static FunctionTranslator getTranslator(FuncSpec funcSpec)
	{
		return fT;
	}
	
	static HashMap<String, Class<? extends IWindowFunction>> FUNCTION_MAP = [:]
	
	public static void registerClass(Class<? extends IWindowFunction> cls)
	{
		FunctionDef fDef = cls.getAnnotation(FunctionDef.class);
		FUNCTION_MAP[fDef.name().toLowerCase()] = cls;
	}
	
	static
	{
		registerClass(RowNumber.class);
		registerClass(Rank.class);
		registerClass(DenseRank.class);
		registerClass(CumeDist.class);
		registerClass(PercentRank.class);
		registerClass(NTile.class);
		
		registerClass(Sum.class);
		registerClass(Min.class);
		registerClass(Max.class);
		registerClass(Avg.class);
		registerClass(StdDev.class);
		registerClass(Variance.class);
		registerClass(Count.class);
		
		registerClass(FirstValue.class);
		registerClass(LastValue.class);
		
		registerClass(CoVariance.class);
		registerClass(LinearRegSlope.class);
		registerClass(LinearRegIntercept.class);
		registerClass(RegCount.class);
	}
	
	static HashMap<String, Class<? extends AbstractTableFunction>> TABLEFUNCTION_MAP = [:]
	
	public static void registerTableClass(Class<? extends AbstractTableFunction> cls)
	{
		FunctionDef fDef = cls.getAnnotation(FunctionDef.class);
		TABLEFUNCTION_MAP[fDef.name().toLowerCase()] = cls;
	}
	
	static
	{
		registerTableClass(Noop.class)
		registerTableClass(NPath.class)
	}
}	
