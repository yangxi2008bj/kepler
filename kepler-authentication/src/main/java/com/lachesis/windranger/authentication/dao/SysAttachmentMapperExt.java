package com.lachesis.windranger.authentication.dao;

import java.util.List;
import com.lachesis.windranger.authentication.dbmodel.SysAttachment;
import com.lachesis.windranger.common.model.ISearchableDAO;

public interface SysAttachmentMapperExt extends ISearchableDAO {
	
	public List<SysAttachment> getSysAttachmentInfo(SysAttachment sysAttachment);
	
	public List<String> getCompanyName();
	
	public List<String> getSourceType();
	
}