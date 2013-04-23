package com.happinesstree.oauth2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happinesstree.oauth2.dao.domain.AppInfo;
import com.happinesstree.oauth2.dao.mapper.AppInfoMapper;
import com.happinesstree.oauth2.service.AppInfoService;

@Service("appInfoService")
public class AppInfoServiceImpl implements AppInfoService {
	
	@Autowired
	private AppInfoMapper appInfoMapper;

	@Override
	public boolean saveAppInfo(AppInfo appInfo) {
		int result = appInfoMapper.insert(appInfo);
		if( result > 0 ) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public AppInfo findAppInfoByAppKey(String appKey) {
		return appInfoMapper.selectByAppKey(appKey);
	}
	

}
