package org.happinesstree.oauth2.dao.dynamic.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源切换AOP类
 * 
 * @author shuhuan@qiyi.com
 * @date   2012-09-28
 * 
 * <!--简单的一个master和一个slaver 读写分离的数据源 -->
 * <bean id="dataSource" class="com.qisheng.util.datasource.DynamicDataSource">
 * 	<property name="targetDataSources">
 * 		<map key-type="java.lang.String">
 * 			<entry key="master" value-ref="masterDataSource" />
 * 			<entry key="slave" value-ref="slaveDataSource" />
 * 		</map>
 * 	</property>
 * 	<property name="defaultTargetDataSource" ref="masterDataSource"></property>
 * </bean>
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
	
	@Override  
    protected Object determineCurrentLookupKey() {   
        return DataSourceSwitch.getDataSouce();   
    } 
	
}
