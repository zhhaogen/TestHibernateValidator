/**
 * 创建于2018-04-11 20:16:25
 * @author zhhaogen
 */
package cn.zhg.test.validator;

import java.util.Date;

/**
 * 测试模型,评论
 * @author zhhaogen
 *
 */
public class Comment
{ 
	public String author;
	@SafeText
	public String txt;
	public Date date;
}
