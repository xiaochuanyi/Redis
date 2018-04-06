package redistest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/*
 * ���jedis��Դ
 */
public class JedisTool {
	private static JedisPool pool = null;
	static{
		//���������ļ�
		InputStream in =JedisTool.class.getClassLoader().getResourceAsStream("redis.properties");
		JedisPoolConfig poolconfig = new JedisPoolConfig();
		Properties pr = new Properties();
		try {
			pr.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		poolconfig.setMaxIdle(Integer.parseInt(pr.get("redis.maxIdle").toString()));//���������
		poolconfig.setMinIdle(Integer.parseInt(pr.get("redis.minIdle").toString()));//��С������
		poolconfig.setMaxTotal(Integer.parseInt(pr.get("redis.maxTotal").toString()));//���������
		pool = new JedisPool(poolconfig,pr.getProperty("redis.url"),Integer.parseInt(pr.get("port").toString()));
	}
	public static Jedis getjedis(){
		return pool.getResource();
	}
	public static void close(){
		pool.close();
	}
}
