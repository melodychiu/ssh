package cn.mydoll.ssh.service.impl;

import java.io.Serializable;
import java.util.List;

import cn.mydoll.ssh.dao.IStandardDao;
import cn.mydoll.ssh.entity.Standard;
import cn.mydoll.ssh.service.IStandardService;

public class StandardServiceImpl implements IStandardService {
	private IStandardDao standardDao=null;
	
	public void setStandardDao(IStandardDao standardDao) {
		this.standardDao = standardDao;
	}
	@Override
	public boolean saveStandard(Standard standard){
		try {
			standardDao.save(standard);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public boolean updateStandard(Standard standard){
		try {
			standardDao.update(standard);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}	
	}
	@Override
	public boolean deleteStandard(Standard standard){
		try {
			standardDao.delete(standard);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public Standard getStandardById(Serializable id){
		Standard _std=null;
		try {
			_std=standardDao.getById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return _std;
	}
	@Override
	public List<Standard> getStandardList(String condition, int pageIndex, int pageSize){
		List<Standard> _lst=null;
		try {
			_lst=standardDao.getStandardList(condition, pageIndex, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return _lst;
	}
	@Override
	public int getStandardListCount(String condition) {
		return standardDao.getStandardListCount(condition);
	}
	@Override
	public boolean haveStandard(Standard standard) throws Exception {
		Standard _std= standardDao.haveStandard(standard);
		if(_std==null)
			return false;
		else
			return true;
	}
}