package org.zwh.service;

import java.util.List;
import org.zwh.entity.User;
//�ӿڴ��ڵ�����:
//1.��� ʵ���� ӵ��ͬ���ķ�����ʱ��  �ýӿڻ���ٴ�����
//2.�Ժ���Ҫ����һ������ ��ʱ��,ֱ���ڽӿ�����д,�̳иýӿڵ�ʵ����������Ҫ��������.
//3.�ӿ��൱��һ���淶,
//(a)���˿���ʱ,���Լ�д�ӿڣ��Լ�дʵʵ���࣬��ô��û��Ҫд�ӿ���.
//(b)�Ŷӿ�������д�˽ӿ�,��ͻȻ�����뿪������дʵ���࣬�㿴�˽ӿھ�֪��Ҫд��Щ�ˡ����ʱ��ͺ��б�Ҫ�ˡ�

public interface UserService {
	public User SelectUserByPrimaryKey(String username);
	public List<User> SelectAllUser();
	public void InsertUser(User user);
}
