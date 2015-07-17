package com.sina.util.dnscache.score;

import java.util.ArrayList;

import com.sina.util.dnscache.model.DomainModel;
import com.sina.util.dnscache.model.IpModel;

/**
 *
 * 对 ip 进行排序
 *
 * Created by fenglei on 15/4/21.
 */
public class ScoreManager implements IScore {
	
	/**
	 * 是否开启排序开关
	 */
	public static boolean IS_SORT = true ; 
	
	private PlugInManager plugInManager = new PlugInManager() ;
	
	@Override
	public String[] serverIpScore(DomainModel domainModel) {

		String[] IpArr = null ;

		if( IS_SORT ){
			// 数据库中得数据，进行排序 , 当ipmodelSize 大于1个的时候在参与排序
			if( domainModel.id != -1 && domainModel.ipModelArr.size() > 1){
				plugInManager.run(domainModel.ipModelArr);
			}
		}
		
		// 转换数据格式
		IpArr = ListToArr(domainModel.ipModelArr) ; 

		return IpArr;
	}
	
	public String[] ListToArr( ArrayList<IpModel> list){
		if(list == null || list.size() == 0  ) return null ; 
		String[] IpArr = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			if( list.get(i) == null ) continue ; 
			IpArr[i] = list.get(i).ip;
		}
		return IpArr ;
	}
	

}
