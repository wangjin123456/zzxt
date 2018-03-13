package com.zzxt.entity;

import java.io.Serializable;

/**
 * 
 * @ClassName: TypeEntity
 * @Description: TODO 证件类型
 * @author wangdekun
 * @date 2017年9月3日 下午7:51:23
 *
 */

public class TypeEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2949422961538363383L;
	private int id;
	private String typeName;
	

	public TypeEntity() {
		super();
	}

	public TypeEntity(int id, String typeName) {
		super();
		this.id = id;
		this.typeName = typeName;
	}

	@Override
	public String toString() {
		return "TypeEntity [id=" + id + ", typeName=" + typeName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((typeName == null) ? 0 : typeName.hashCode());
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
		TypeEntity other = (TypeEntity) obj;
		if (id != other.id)
			return false;
		if (typeName == null) {
			if (other.typeName != null)
				return false;
		} else if (!typeName.equals(other.typeName))
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}
