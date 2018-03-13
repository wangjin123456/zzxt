package com.zzxt.dao;

import java.util.List;

import com.zzxt.entity.TypeEntity;

/**
 * 
 * @ClassName: TypeDao
 * @Description: 文件类型表（tb_filetype） CURD
 * 
 * @author wangdekun
 * @date 2017年9月3日 下午8:09:48
 *
 */
public interface TypeDao {
	/**
	 * 
	 * @Description
	 *
	 * @param typeName
	 * @return 返回1 表示成功
	 */

	Integer insertType(String typeName);

	/**
	 * 
	 * @Description
	 *
	 * @return
	 */
	List<TypeEntity> selectType();

	/**
	 * 
	 * @Description
	 *
	 * @param typeEntity
	 * @return
	 */
	Integer updateType(TypeEntity typeEntity);

	/**
	 * 
	 * @Description
	 *
	 * @param id
	 * @return
	 */
	Integer deleteType(Integer id);
}
