1.mvcģʽ�ļ��
	m��model == ���ݿ� == mybatis mapper.xml��sql���ݲ���
	v��view  == ������ == controller ���صĽ��
	c��controller == ������ == controller.java entity.java service.java serviceImpl.java
	c��m�ĶԽӣ� dao  mapper.java

2.�Ա� ��mvcģʽ��mvcģʽ
	��mvcģʽ֮ǰΪall in one ģʽ���������Ϊmvģʽ
	all in one �� û��c��һ�������Ӧһ��m����m�д���ҵ���߼�
	mvc        �� mvc,һ�������Ӧһ��c�����c��Ӧ1��m
	
	e.g���û�2�������ҳ���������С��5��ѧ��,�ҳ���������С��6��ѧ����
		all in one:
					select * from student
							where age < 5
					select * from student
							where age < 6
		
		mvc       : 
					select * from student
							where age < param
		ע:param ���û����ι�����
		��all in one��ͬ���ǣ�mvcͨ��controller ��̬����sql���Ӷ��ﵽ���
					