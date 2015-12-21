package com.james.tvplay.interf;

import java.util.List;

import com.james.tvplay.bean.DataInfo;

/**
 * 获取javabean集合的回调接口
 * 
 * @author james
 *
 */
public interface OnGetData {

	public void getData(List<DataInfo> list);
}
