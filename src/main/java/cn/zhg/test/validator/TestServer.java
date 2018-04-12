/**
 * 创建于2018-04-11 20:42:47
 * @author zhhaogen
 */
package cn.zhg.test.validator;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.*;

import xiaogen.util.Logger;

/**
 * 用户服务测试
 * 
 * @author zhhaogen
 *
 */ 
public class TestServer
{
	/**
	 * 添加用户
	 * 
	 * @param name
	 *            不能为空
	 * @param level
	 * @return
	 */
	public User addUser(@NotBlank @Size(min = 1, max = 20) String name, int level)
	{
		Logger.d("name=" + name + ",level=" + level);
		return null;
	}
	/***
	 * 自定义注解在方法中
	 * @param address
	 * @param tel
	 * @return
	 */
	public Order addOrder(String address, @PhoneNumber  String tel)
	{
		Logger.d(" address=" + address + ",tel=" + tel);
		return null; 
	}
	/***
	 * 自定义注解在方法中
	 * @param author
	 * @param text
	 * @return
	 */
	public Comment addComment(String author, @SafeText  String text)
	{
		Logger.d(" author=" + author + ",text=" + text);
		return null; 
	}
	/**
	 * 方法返回值校验
	 * @param author
	 * @param text
	 * @return
	 */
	public @Valid Comment saveComment(String author,    String text)
	{
		Logger.d(" author=" + author + ",text=" + text);
		Comment item=new Comment();
		item.author=author;
		item.txt=text;
		return item; 
	}
	/**
	 * 方法返回值校验 @NotNull
	 * @param author
	 * @param text
	 * @return
	 */
	public @NotNull  Comment saveCommentNull(String author,    String text)
	{
		Logger.d(" author=" + author + ",text=" + text);
		return null;
	} 
	/***
	 * 删除用户,测试方法
	 * <p>
	 * 只有root用户才能删除使用用户,其他只能删除自己
	 * @param adminName 管理员名称
	 * @param userName 用户名称
	 * @return
	 */
	@RoleControl("root")
	public User deleteUser(String adminName,String userName)
	{
		Logger.d("admin="+adminName+",uid="+userName);
		User item=new User();
		item.name=userName;
		return item; 
	}
	/**
	 * 
	 * @see #deleteUser(String, String)
	 * @param adminName 不能为空
	 * @param userName 不能为空
	 * @return
	 */
	@RoleControl("root")
	public User deleteNotEmptyUser(@NotBlank String adminName,@NotBlank String userName)
	{
		Logger.d("admin="+adminName+",uid="+userName);
		User item=new User();
		item.name=userName;
		return item; 
	}
}
