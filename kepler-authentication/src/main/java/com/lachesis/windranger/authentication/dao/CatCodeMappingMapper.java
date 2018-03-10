/*
 * @ClassName CatCodeMappingMapper
 * @Description 
 * @version 1.0
 * @Date 2017-12-30 22:25:25
 */
package com.lachesis.windranger.authentication.dao;

import com.lachesis.windranger.authentication.dbmodel.CatCodeMapping;
import com.lachesis.windranger.common.model.ICrudGenericDAO;
import java.util.List;

public interface CatCodeMappingMapper extends ICrudGenericDAO {
    int insert(CatCodeMapping record);

    List<CatCodeMapping> selectAll();
}