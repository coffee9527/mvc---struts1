package annotation.myannotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * action类的注解：通过注解将请求路径和action
 * @author coffee
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyAction {
	//类路径，解析时默认为类名称首字母小写
	String value() default "";
	//action对应的实体类的类对象
	Class bean() default Exception.class;
	//处理成功是跳转的页面路径
	String successUrl();
	//处理失败时跳转的页面路径
	String failureUrl() default "";
}
