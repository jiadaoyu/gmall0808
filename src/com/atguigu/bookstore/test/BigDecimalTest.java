package com.atguigu.bookstore.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class BigDecimalTest {

	@Test
	public void test() {
		double d1 = 0.1;
		double d2 = 0.2;
		//将计算的小数转为BigDecimal对象   必须调用String类型的构造器
		BigDecimal bd1 = new BigDecimal(d1+"");
		BigDecimal bd2 = new BigDecimal(d2+"");
		//再调用对象的方法进行运算
		BigDecimal add = bd1.add(bd2);
		//将运算后的对象转为double类型的数字
		double d = add.doubleValue();
		
		System.out.println(d);
		//System.out.println(d1+d2);
	}

}
