package struts1.test;

import org.junit.Test;

import struts1.common.action.Action;
import struts1.register.ToRegisterAction;

public class TestToRegisterAction {
	/**
	 * 测试接口中定义的变量是否可修改，默认是被什么修饰符修饰的
	 */
	@Test
	public void toRegisterActionTest() {
		
		//Action.success = "abcd";
		//System.out.println(Action.success);
		
		/**
		 * 测试发现接口中的变量默认被public static final修饰
		 */
	}
}
