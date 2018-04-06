package redistest;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class redis1 {
	@Test
	//��õ�һ��jedis����������ݿ�
	public void test(){
		//���jedis���Ӷ���
		Jedis js = new Jedis("192.168.137.128",6379);
		//�������
		String username = js.get("username");
		System.out.println(username);
		//��������
		js.set("xcy", "lqy");
		System.out.println(js.get("xcy"));	
		js.close();
	}
	//ͬ��jedis��pool���jedis���Ӷ���
	@Test
	public void test2(){
		//�����������ö���
		JedisPoolConfig poolconfig = new JedisPoolConfig();
		poolconfig.setMaxIdle(30);//���������
		poolconfig.setMinIdle(10);//��С������
		poolconfig.setMaxTotal(50);//���������
		//�������ӳ�
		JedisPool jsp = new JedisPool(poolconfig,"192.168.137.128",6379);
		//�����ӳ��л�ȡ���Ӷ���
		Jedis jdis = jsp.getResource();
		jdis.set("hello", "lj");
		//�ر���Դ
		jdis.close();
		jsp.close();
	}
	@Test
	public void test3(){
		Jedis js = JedisTool.getjedis();
		System.out.println(js.get("xcy"));
		js.close();
		JedisTool.close();
	}
}
