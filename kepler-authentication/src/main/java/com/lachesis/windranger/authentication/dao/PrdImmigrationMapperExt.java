package com.lachesis.windranger.authentication.dao;

import java.util.List;
import com.lachesis.windranger.common.model.ISearchableDAO;

public interface PrdImmigrationMapperExt extends ISearchableDAO {

	List<String> getImmigrationCountry();
}