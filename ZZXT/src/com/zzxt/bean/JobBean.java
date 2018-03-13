package com.zzxt.bean;

/**
 * 
 * @ClassName: JobBean
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author wangdekun
 * @date 2017年9月11日 下午3:27:22
 *
 */
public class JobBean {
	// {
	// "jobName": "job_type",
	// "jobUuid": "41374acc-1978-40bc-8fae-2d14d796eccf",
	// "orgName": "彩生活服务集团",
	// "orgUuid": "9959f117-df60-4d1b-a354-776c20ffb8c7"
	// },

	private String jobName;
	private String jobUuid;
	private String orgName;
	private String orgUuid;

	public JobBean() {
		super();
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobUuid() {
		return jobUuid;
	}

	public void setJobUuid(String jobUuid) {
		this.jobUuid = jobUuid;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgUuid() {
		return orgUuid;
	}

	public void setOrgUuid(String orgUuid) {
		this.orgUuid = orgUuid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((jobName == null) ? 0 : jobName.hashCode());
		result = prime * result + ((jobUuid == null) ? 0 : jobUuid.hashCode());
		result = prime * result + ((orgName == null) ? 0 : orgName.hashCode());
		result = prime * result + ((orgUuid == null) ? 0 : orgUuid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JobBean other = (JobBean) obj;
		if (jobName == null) {
			if (other.jobName != null)
				return false;
		} else if (!jobName.equals(other.jobName))
			return false;
		if (jobUuid == null) {
			if (other.jobUuid != null)
				return false;
		} else if (!jobUuid.equals(other.jobUuid))
			return false;
		if (orgName == null) {
			if (other.orgName != null)
				return false;
		} else if (!orgName.equals(other.orgName))
			return false;
		if (orgUuid == null) {
			if (other.orgUuid != null)
				return false;
		} else if (!orgUuid.equals(other.orgUuid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "JobBean [jobName=" + jobName + ", jobUuid=" + jobUuid + ", orgName=" + orgName
				+ ", orgUuid=" + orgUuid + "]";
	}

}
