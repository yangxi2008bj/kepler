package com.lachesis.windranger.authentication.service;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;
import com.lachesis.windranger.authentication.dbmodel.SysAttachment;
import com.lachesis.windranger.authentication.model.vo.MailDTO;

public interface IFileManager {
	Map<String, Object> uploadFile(MultipartFile file, SysAttachment sysAttachment);

	void deleteAttachment(long seqId);

	void downloadFile(Long seqId, HttpServletResponse response);

	List<SysAttachment> searchByCondition(SysAttachment sysAttachment);
	
	void sendMail(MailDTO mailDTO);
	
}
