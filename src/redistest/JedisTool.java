package redistest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/*
 * 获得jedis资源
 */
public class JedisTool {
	private static JedisPool pool = null;
	static{
		//加载配置文件
		InputStream in =JedisTool.class.getClassLoader().getResourceAsStream("redis.properties");
		JedisPoolConfig poolconfig = new JedisPoolConfig();
		Properties pr = new Properties();
		try {
			pr.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		poolconfig.setMaxIdle(Integer.parseInt(pr.get("redis.maxIdle").toString()));//最大闲置数
		poolconfig.setMinIdle(Integer.parseInt(pr.get("redis.minIdle").toString()));//最小闲置数
		poolconfig.setMaxTotal(Integer.parseInt(pr.get("redis.maxTotal").toString()));//最大连接数
		pool = new JedisPool(poolconfig,pr.getProperty("redis.url"),Integer.parseInt(pr.get("port").toString()));
	}
	public static Jedis getjedis(){
		return pool.getResource();
	}
	public static void close(){
		pool.close();
	}
}
