package com.lachesis.windranger.common.util;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.icepdf.core.pobjects.Document;
import org.icepdf.core.pobjects.Page;
import org.icepdf.core.util.GraphicsRenderingHints;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.lachesis.windranger.api.restful.exception.RfForbiddenException;
import com.lachesis.windranger.common.exception.RfNotFoundException;

import sun.misc.BASE64Decoder;

/**
 * 
 * @ClassName: UploadFileUtils
 * @Description: 上传文件工具类
 * @author A18ccms a18ccms_gmail_com
 * @date 2017年9月4日 下午7:20:05
 *
 */
public class UploadFileUtils {
	private static final Logger LOG = LoggerFactory.getLogger(UploadFileUtils.class);

	public static void uploadStringFile(String fileString, String filePath, final String name) {
		FileOutputStream outputStream = null;
		try {
			File dirfile = new File(filePath, name);
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] fileByte = decoder.decodeBuffer(fileString);

			if (fileByte.length == 0) {
				throw new RfForbiddenException("文件内容为空");
			}
			dirfile.createNewFile();
			outputStream = new FileOutputStream(dirfile);
			outputStream.write(fileByte);
			outputStream.flush();
			outputStream.close();
			LOG.info("文件上传完成.");

		} catch (IOException e) {
			LOG.warn("文件上传失败.", e);
		} finally {
			try {
				if (outputStream != null) {
					outputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void uploadFile(final MultipartFile uploadFile, String filePath, final String name) {
		FileOutputStream outputStream = null;
		InputStream inputStream = null;
		try {
			inputStream = uploadFile.getInputStream();
			File file = new File(filePath, name);
			file.createNewFile();
			outputStream = new FileOutputStream(file);
			byte temp[] = new byte[1024];
			int size = -1;
			while ((size = inputStream.read(temp)) != -1) { // 每次读取1KB，直至读完
				outputStream.write(temp, 0, size);
			}
			outputStream.flush();
			outputStream.close();
			LOG.info("文件上传完成.");

		} catch (IOException e) {
			LOG.warn("文件上传失败.", e);
		} finally {
			try {
				if (outputStream != null) {
					outputStream.close();
				}
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void readFile(HttpServletResponse response, String fileBasePath, String path) throws IOException {
		OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
		try {
			File f = new File(fileBasePath, path);
			if (f.exists()) {
				response.setContentType("multipart/form-data");
				BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(f));
				byte[] temp = new byte[1024 * 1024 * 4];
				int size = -1;
				while ((size = inputStream.read(temp)) != -1) { // 每次读取1KB，直至读完
					outputStream.write(temp, 0, size);
				}
				inputStream.close();

			} else {
				outputStream.write("文件不存在".getBytes("UTF-8"));
			}
		} catch (Exception e) {
			outputStream.flush();
			outputStream.close();
			LOG.error(e.getMessage());
			throw new RfNotFoundException(e.getMessage());
		}
	}

	public static Map<String, String> uploadFile(final MultipartFile uploadFile, String moduleName, String type,
			String fileBasePath, String resourcePath) {
		String filePath = fileBasePath + "/" + moduleName + "/" + type;
		File dir = new File(filePath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		// 给新文件拼上时间毫秒，防止重名
		final long now = System.currentTimeMillis();
		Map<String, String> result = new HashMap<>();
		final String url = "/windranger/sys/files/" + moduleName + "/" + type + "/";
		String originalName = uploadFile.getOriginalFilename();
		final String fileType = originalName.substring(originalName.lastIndexOf(".") + 1, originalName.length());
		// 图片文件去掉.后缀
		String smallImageName = null;
		if ("jpgpng".indexOf(fileType) != -1) {
			originalName = originalName.replace("." + fileType, "");
		} else {
			smallImageName = originalName.substring(0, originalName.lastIndexOf("."));
		}
		final String mp4Name = "mp4-" + now + "-" + smallImageName;
		final String pdfName = "pdf-" + now + "-" + smallImageName;
		// MP4生成缩略图
		if (("mp4".contains(fileType)) || ("MP4".contains(fileType))) {
			result.put("image_mp4_URL", url + mp4Name);
		}
		// PDF生成缩略图
		if (("pdf".contains(fileType)) || ("PDF".contains(fileType))) {
			result.put("image_pdf_URL", url + pdfName);
		}

		// 给新文件拼上时间毫秒，防止重名
		final String name = now + "-" + originalName;

		result.put("file_url", url + name);
		UploadFileUtils.uploadFile(uploadFile, filePath, name);

		// MP4 生成缩略图
		if (("mp4".contains(fileType)) || ("MP4".contains(fileType))) {
			videoToPicture(filePath + "/" + name, resourcePath.substring(1), filePath + "/" + mp4Name);
		}
		// PDF 生成缩略图
		if (("pdf".contains(fileType)) || ("PDF".contains(fileType))) {
			pdfToImage(filePath + "/" + name, filePath + "/" + pdfName);
		}
		return result;
	}

	/**
	 * 参数 veido_path : 视频位置 ffmpeg_path : 转换程序 picPath : 图片位置
	 */
	public static boolean videoToPicture(String veidoPath, String ffmpegPath, String picPath) {
		File file = new File(veidoPath);
		if (!file.exists()) {
			return false;
		}
		List<String> commands = new ArrayList<>();
		commands.add(ffmpegPath);
		commands.add("-i");
		commands.add(veidoPath);
		commands.add("-y");
		commands.add("-f");
		commands.add("image2");
		commands.add("-ss");
		commands.add("2");// 这个参数是设置截取视频多少秒时的画面
		commands.add("-s");
		commands.add("700x525");
		commands.add(picPath);
		try {
			ProcessBuilder builder = new ProcessBuilder();
			builder.command(commands);
			builder.start();
			System.out.println("截取成功");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 将视频转为flv视频
	 */
	public static boolean videoToVideo(String veidopath, String ffmpegpath, String codcFilePath) {

		File file = new File(veidopath);
		if (!file.exists()) {
			return false;
		}
		// 创建一个List集合来保存转换视频文件为flv格式的命令
		List<String> commands = new java.util.ArrayList<String>();
		commands.add(ffmpegpath); // 添加转换工具路径
		commands.add("-i"); // 添加参数＂-i＂，该参数指定要转换的文件
		commands.add(veidopath); // 添加要转换格式的视频文件的路径
		commands.add("-qscale"); // 指定转换的质量
		commands.add("6");
		commands.add("-ab"); // 设置音频码率
		commands.add("64");
		commands.add("-ac"); // 设置声道数
		commands.add("2");
		commands.add("-ar"); // 设置声音的采样频率
		commands.add("22050");
		commands.add("-r"); // 设置帧频
		commands.add("24");
		commands.add("-y"); // 添加参数＂-y＂，该参数指定将覆盖已存在的文件
		commands.add(codcFilePath);
		try {
			ProcessBuilder builder = new ProcessBuilder();
			builder.command(commands);
			builder.redirectErrorStream(true);
			builder.start();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 生成一本书的缩略图
	 * 
	 * @param inputFile
	 *            需要生成缩略图的书籍的完整路径
	 * @param outputFile
	 *            生成缩略图的放置路径
	 */
	public static void pdfToImage(String inputFile, String outputFile) {
		Document document = null;

		try {
			float rotation = 0f;
			// 缩略图显示倍数，1表示不缩放，0.5表示缩小到50%
			float zoom = 0.8f;

			document = new Document();
			document.setFile(inputFile);
			BufferedImage image = (BufferedImage) document.getPageImage(0, GraphicsRenderingHints.SCREEN,
					Page.BOUNDARY_CROPBOX, rotation, zoom);

			Iterator<ImageWriter> iter = ImageIO.getImageWritersBySuffix("jpg");
			ImageWriter writer = iter.next();

			FileOutputStream out = new FileOutputStream(new File(outputFile));
			ImageOutputStream outImage = ImageIO.createImageOutputStream(out);

			writer.setOutput(outImage);
			writer.write(new IIOImage(image, null, null));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
