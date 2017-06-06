package cn.mydoll.ssh.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

import cn.mydoll.ssh.constant.Const;
import cn.mydoll.ssh.entity.Standard;
import cn.mydoll.ssh.service.IStandardService;
import cn.mydoll.ssh.util.PageSupport;
import cn.mydoll.ssh.util.QueryResult;

public class StandardAction extends ActionSupport implements ServletContextAware {
	private static final long serialVersionUID = -5868440220984312963L;
	//标准列表
	private List<Standard> standardList=new ArrayList<Standard>();
	private String resultData;
	//列表封装到PageSupport类
	private QueryResult<Standard> queryResult=new QueryResult<Standard>();
	private PageSupport pageSupport;//=new PageSupport();
	private IStandardService standardService;
	private String condition;
	private Standard standard;
	//文件上传所需的属性
	private File pic;
	private String picContentType;
	private String picFileName;
	//文件下载所需的属性
	private String filename;
	private String mimeType;
	private ServletContext context;
	private InputStream inputStream;
	
	public List<Standard> getStandardList() {
		return standardList;
	}
	public void setStandardList(List<Standard> standardList) {
		this.standardList = standardList;
	}
	public String getResultData() {
		return resultData;
	}
	public void setResultData(String resultData) {
		this.resultData = resultData;
	}
	public QueryResult<Standard> getQueryResult() {
		return queryResult;
	}
	public void setQueryResult(QueryResult<Standard> queryResult) {
		this.queryResult = queryResult;
	}
	public PageSupport getPageSupport() {
		return pageSupport;
	}
	public void setPageSupport(PageSupport pageSupport) {
		this.pageSupport = pageSupport;
	}
	public void setStandardService(IStandardService standardService) {
		this.standardService = standardService;
	}
	public Standard getStandard() {
		return standard;
	}
	public void setStandard(Standard standard) {
		this.standard = standard;
	}	
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public File getPic() {
		return pic;
	}
	public void setPic(File pic) {
		this.pic = pic;
	}
	public String getPicContentType() {
		return picContentType;
	}
	public void setPicContentType(String picContentType) {
		this.picContentType = picContentType;
	}
	public String getPicFileName() {
		return picFileName;
	}
	public void setPicFileName(String picFileName) {
		this.picFileName = picFileName;
	}	
	//处理文件下载
	public String getFilename() {
		try {  
            return new String(filename.getBytes(),"ISO8859-1");  
        } catch (UnsupportedEncodingException e) {  
            return this.filename;  
        }  
	}
	
	public void setFilename(String filename) {
		 try {  
			 this.filename = new String(filename.getBytes("ISO8859-1"),"utf-8");  
	     } catch (UnsupportedEncodingException e) {  }  
	}
	
	public InputStream getInputStream() {
		inputStream=context.getResourceAsStream(File.separatorChar+"upload"+File.separatorChar+filename);
		if(null==inputStream){
			inputStream=new ByteArrayInputStream("文件不存在".getBytes());
		}
		return inputStream;
	}	
	
	public String getMimeType() {
		return mimeType;
	}

	public String downloadFile(){
		mimeType=context.getMimeType(filename);
		return SUCCESS;
	}	
	@Override
	public void setServletContext(ServletContext context) {
		this.context=context;
	}

	public String saveStandard(){
		String rootpath=ServletActionContext.getServletContext().getRealPath(File.separator);
		String fileEx=picFileName.substring(picFileName.indexOf("."),picFileName.length());
		String filepath=System.currentTimeMillis()+fileEx;
		String pathname=rootpath+"upload"+File.separator+filepath;
		File destFile=new File(pathname);
		try {
			FileUtils.copyFile(pic, destFile);
			standard.setPackagepath(filepath);
			boolean result=standardService.saveStandard(standard);
			resultData = "{\"result\":\"" + result + "\"}";
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String deleteStandard(){
		boolean result = standardService.deleteStandard(standard);
		resultData = "{\"result\":\"" + result + "\"}";
		return SUCCESS;			
	}	
	
	public String updateStandard(){
		try {
			if(pic!=null){
				String rootpath=ServletActionContext.getServletContext().getRealPath(File.separator);
				String fileEx=picFileName.substring(picFileName.indexOf("."),picFileName.length());
				String filepath=System.currentTimeMillis()+fileEx;
				String pathname=rootpath+"upload"+File.separator+filepath;
				File destFile=new File(pathname);
				FileUtils.copyFile(pic, destFile);
				standard.setPackagepath(filepath);
			}	
			boolean result=standardService.updateStandard(standard);
			resultData = "{\"result\":\"" + result + "\"}";			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return SUCCESS;	
	}
	
	public String searchStandardList(){
		try{
			int totalCount=standardService.getStandardListCount(condition);
			if(null==pageSupport){
				pageSupport=new PageSupport();
			}
			standardList=standardService.getStandardList(condition,pageSupport.getPageIndex(),Const.pageSize);
			pageSupport.setTotalCount(totalCount);
			pageSupport.init();
			
			queryResult.setList(standardList);
			queryResult.setTotalCount(totalCount);
			queryResult.setPageSupport(pageSupport);
			
			resultData=JSONUtil.serialize(queryResult);			
		}
		catch (JSONException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String judgeStandard(){
		boolean result=false;
		try {
			result = standardService.haveStandard(standard);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resultData = "{\"result\":\"" + result + "\"}";
		return SUCCESS;		
	}	
		
	public String getStandardById(){
		standard=standardService.getStandardById(standard.getId());
		try {
			resultData=JSONUtil.serialize(standard);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return SUCCESS;		
	}
}