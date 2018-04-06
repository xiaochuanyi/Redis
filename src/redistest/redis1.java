package redistest;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class redis1 {
	@Test
	//获得单一的jedis对象操作数据库
	public void test(){
		//获得jedis连接对象
		Jedis js = new Jedis("192.168.137.128",6379);
		//获得数据
		String username = js.get("username");
		System.out.println(username);
		//储存数据
		js.set("xcy", "lqy");
		System.out.println(js.get("xcy"));	
		js.close();
	}
	//同伙jedis的pool获得jedis连接对象
	@Test
	public void test2(){
		//创建池子配置对象
		JedisPoolConfig poolconfig = new JedisPoolConfig();
		poolconfig.setMaxIdle(30);//最大闲置数
		poolconfig.setMinIdle(10);//最小闲置数
		poolconfig.setMaxTotal(50);//最大连接数
		//创建连接池
		JedisPool jsp = new JedisPool(poolconfig,"192.168.137.128",6379);
		//从连接池中获取连接对象
		Jedis jdis = jsp.getResource();
		jdis.set("hello", "lj");
		//关闭资源
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
