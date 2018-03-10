package com.lachesis.windranger.authentication.service.impl;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.lachesis.windranger.authentication.dao.SysAttachmentMapper;
import com.lachesis.windranger.authentication.dao.SysAttachmentMapperExt;
import com.lachesis.windranger.authentication.dbmodel.SysAttachment;
import com.lachesis.windranger.authentication.model.vo.MailDTO;
import com.lachesis.windranger.authentication.service.IFileManager;
import com.lachesis.windranger.common.constants.CommonConstants;
import javax.mail.*;
import javax.mail.internet.*;

@Service
public class FileManager implements IFileManager {

	private static Logger logger = Logger.getLogger(FileManager.class);

	@Autowired
	SysAttachmentMapper sysAttachmsentMapper;
	@Autowired
	SysAttachmentMapperExt sysAttachmentMapperExt;

	@Override
	public Map<String, Object> uploadFile(MultipartFile file, SysAttachment sysAttachment) {
		Map<String, Object> map = new HashMap<>();
		if (file.getSize() > 20971520) {
			map.put(CommonConstants.CONTROLLER_ERROR, true);
			map.put(CommonConstants.MESSAGE_NAME, "文件大于20M，请重新上传");
			return map;
		}

		if (!validateExtension(file.getOriginalFilename())) {
			map.put(CommonConstants.CONTROLLER_ERROR, true);
			map.put(CommonConstants.MESSAGE_NAME, "请上传正确格式的文件");
			return map;
		}
		// 设置附件信息
		sysAttachment.setCreatePerson("admin");
		sysAttachment.setCreateTime(new Date());
		sysAttachment.setUpdatePerson("admin");
		sysAttachment.setUpdateTime(new Date());
		sysAttachment.setLength(file.getSize());
		sysAttachment.setFileName(file.getOriginalFilename());
		sysAttachment.setFileType(file.getContentType());

		try {
			byte[] strout = file.getBytes();
			byte[] encoded = Base64.getEncoder().encode(strout);
			sysAttachment.setUrl(encoded);
		} catch (IOException e) {
			map.put(CommonConstants.CONTROLLER_ERROR, true);
			map.put(CommonConstants.MESSAGE_NAME, "文件解析出错！");
			return map;
		}

		sysAttachmsentMapper.insert(sysAttachment);
		map.put("success", true);
		return map;
	}

	@Override
	public void deleteAttachment(long seqId) {
		sysAttachmsentMapper.deleteByPrimaryKey(seqId);
	}

	@Override
	public void downloadFile(Long seqId, HttpServletResponse response) {
		SysAttachment sysAttachment = sysAttachmsentMapper.selectByPrimaryKey(seqId);
		byte[] contents = Base64.getDecoder().decode(sysAttachment.getUrl());

		try {
			OutputStream out = new BufferedOutputStream(response.getOutputStream());
			String fileName = URLEncoder.encode(sysAttachment.getFileName(), "UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			response.addHeader("Content-Length", "" + sysAttachment.getLength());
			response.setContentType(sysAttachment.getFileType() + ";charset=UTF-8");
			out.write(contents);
			out.flush();
			out.close();
		} catch (IOException e) {
			logger.error("download failed!");
		}
	}

	@Override
	public List<SysAttachment> searchByCondition(SysAttachment sysAttachment) {
		return sysAttachmentMapperExt.getSysAttachmentInfo(sysAttachment);
	}

	private boolean validateExtension(String contentType) {
		String[] strings = contentType.split("\\.");
		if (strings.length >= 2) {
			String str = strings[strings.length - 1];
			if ("doc".equals(str) || "docx".equals(str) || "pdf".equals(str) || "txt".equals(str)
							|| "xls".equals(str) || "xlsx".equals(str)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void sendMail(MailDTO mailDTO) {
		String from = "xi.yang@lachesis-mh.com";
		mailDTO.setFrom(from);
		Properties props = new Properties();
		props.put("mail.host", "183.62.162.28");
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");

		Session session = Session.getInstance(props);
		session.setDebug(true);
		Transport ts;
		try {
			ts = session.getTransport();
			ts.connect(from, "19900613Jll!");
			Message msg=createMail(session, mailDTO);
			ts.sendMessage(msg, msg.getAllRecipients());
		} catch (Exception e) {
			logger.error("Email 发送异常");
		}

	}

	private MimeMessage createMail(Session session, MailDTO mailDTO)
					throws AddressException, MessagingException {
		MimeMessage mm = new MimeMessage(session);
		mm.setFrom(new InternetAddress(mailDTO.getFrom()));
		mm.setRecipient(Message.RecipientType.TO, new InternetAddress(mailDTO.getTo()));
		mm.setSubject(mailDTO.getSubject());
		mm.setContent(mailDTO.getMessage(),"text/html;charset=gbk");
		
		return mm;
	}
	
}
