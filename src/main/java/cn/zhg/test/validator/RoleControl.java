/**
 * 创建于2018-04-11 21:49:37
 * @author zhhaogen
 */
package cn.zhg.test.validator;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.*;

import javax.validation.Constraint;
import javax.validation.Payload; 

@Retention(RUNTIME)
@Target(METHOD)
@Constraint(validatedBy = {RoleControlValidator.class })
/**
 * 权限管理
 * <p>
 * 多个参数控制
 * @author zhhaogen
 *
 */
public @interface RoleControl
{

	String  value();
	Class<?>[] groups() default { };
	Class<? extends Payload>[] payload() default { };
	String message() default "没有权限";
}
