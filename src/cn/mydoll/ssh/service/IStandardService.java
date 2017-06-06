package cn.mydoll.ssh.service;

import java.io.Serializable;
import java.util.List;

import cn.mydoll.ssh.entity.Standard;

public interface IStandardService {
	public boolean saveStandard(Standard standard);
	public boolean updateStandard(Standard standard);
	public boolean deleteStandard(Standard standard);
	public Standard getStandardById(Serializable id);
	public List<Standard> getStandardList(String condition,int pageIndex,int pageSize);
	public int getStandardListCount(String condition);
	public boolean haveStandard(Standard standard) throws Exception;
}