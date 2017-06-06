package cn.mydoll.ssh.entity;

import java.util.Date;

public class Standard implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String stdnum;
	private String zhname;
	private String version;
	private String keys;
	private Date releasedate;
	private Date impldate;
	private String packagepath;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStdnum() {
		return stdnum;
	}
	public void setStdnum(String stdnum) {
		this.stdnum = stdnum;
	}
	public String getZhname() {
		return zhname;
	}
	public void setZhname(String zhname) {
		this.zhname = zhname;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getKeys() {
		return keys;
	}
	public void setKeys(String keys) {
		this.keys = keys;
	}
	public Date getReleasedate() {
		return releasedate;
	}
	public void setReleasedate(Date releasedate) {
		this.releasedate = releasedate;
	}
	public Date getImpldate() {
		return impldate;
	}
	public void setImpldate(Date impldate) {
		this.impldate = impldate;
	}
	public String getPackagepath() {
		return packagepath;
	}
	public void setPackagepath(String packagepath) {
		this.packagepath = packagepath;
	}
}