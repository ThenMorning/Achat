1.mvc模式的简介
	m：model == 数据库 == mybatis mapper.xml的sql数据操作
	v：view  == 后端输出 == controller 返回的结果
	c：controller == 控制器 == controller.java entity.java service.java serviceImpl.java
	c与m的对接： dao  mapper.java

2.对比 非mvc模式与mvc模式
	非mvc模式之前为all in one 模式，个人理解为mv模式
	all in one ： 没有c，一个请求对应一个m，在m中处理业务逻辑
	mvc        ： mvc,一个请求对应一个c，多个c对应1个m
	
	e.g：用户2个请求（找出所有年龄小于5的学生,找出所有年龄小于6的学生）
		all in one:
					select * from student
							where age < 5
					select * from student
							where age < 6
		
		mvc       : 
					select * from student
							where age < param
		注:param 是用户传参过来。
		和all in one不同的是，mvc通过controller 动态生成sql，从而达到解耦。
					