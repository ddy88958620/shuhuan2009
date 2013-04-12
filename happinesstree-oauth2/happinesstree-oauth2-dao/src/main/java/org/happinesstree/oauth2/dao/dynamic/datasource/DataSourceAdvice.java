package org.happinesstree.oauth2.dao.dynamic.datasource;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;

/**
 * 数据源AOP切面类
 * 
 * @author shuhuan@qiyi.com
 * @date 2012-09-28
 */
public class DataSourceAdvice implements MethodBeforeAdvice,
		AfterReturningAdvice, ThrowsAdvice {
	private static Logger log = Logger.getLogger(DataSourceAdvice.class);   
    private long beginTime=0;
    
	/**  
     * SERVICE方法调用开始时调用  
     */ 
	public void before(Method method, Object[] arg, Object target)
			throws Throwable {
		
		log.info("开始业务处理[" + method.getName() + "];全路径["
				+ target.getClass().getName() + "]");
		beginTime = System.currentTimeMillis();
		log.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		
		String db = DataSourceSwitch.checkDataSouce();
		if( null == db ) {
			if (method.getName().startsWith("add")
					|| method.getName().startsWith("create")
					|| method.getName().startsWith("del")
					|| method.getName().startsWith("edit")
					|| method.getName().startsWith("insert")
					|| method.getName().startsWith("save")
					|| method.getName().startsWith("update")
					|| method.getName().startsWith("modeify")) {
				log.info("数据源切换到:master");
				DataSourceSwitch.setMaster();
				log.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			} else {
				log.info("数据源切换到:slave");
				DataSourceSwitch.setSlave();
				log.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			}
		} else {
			log.info("数据源切换到:" + db + " by shuhuan^_^");
			log.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		}
		
	}
	
	/**  
     * SERVICE方法调用结束后调用  
     */
	public void afterReturning(Object arg, Method method, Object[] args,
			Object target) throws Throwable {

		log.info("结束业务处理[" + method.getName() + "];耗时:"
				+ (System.currentTimeMillis() - beginTime) + "毫秒;全路径["
				+ target.getClass().getName() + "]");
		log.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
	}
	
	/**  
     * 抛出Exception异常调用  
     */  
    public void afterThrowing(Method method, Object[] args, Object target, Exception ex) throws Throwable {   
		 log.info("数据源调用发生异常,数据源切换到:slave");   
		 DataSourceSwitch.setSlave();   
		 log.error("数据源调用异常信息:"+ex.getMessage());   
		 log.info("数据源调用异常信息:"+ex.getMessage());   
		 log.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");   
    }    

}
