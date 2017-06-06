package cn.mydoll.ssh.dao;

import java.io.Serializable;
import java.util.List;

import cn.mydoll.ssh.entity.Standard;

public interface IStandardDao{
	public void save(Standard standard) throws Exception;
	public void update(Standard standard) throws Exception;
	public void delete(Standard standard) throws Exception;
	public Standard getById(Serializable id) throws Exception;
	public List<Standard> getStandardList(String condition,int pageIndex,int pageSize) throws Exception;
	public int getStandardListCount(String condition);
	public Standard haveStandard(Standard standard) throws Exception;
}