/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package com.example.demo.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author linhlh2
 */
@Slf4j
public class FileUtil {

	public final static Map<String, String> FILE_TYPE_MAP = new HashMap<String, String>() {
		private static final long serialVersionUID = 6144887420160967657L;

		{
			put(PNG, "image/png");
			put(DOC, "application/msword");
			put(DOCX, "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
			put(PDF, "application/pdf");
			put(JPG, "image/jpeg");
			put(JPEG, "image/jpeg");
			put(GIF, "image/gif");
			put(XLS, "application/vnd.ms-excel");
			put(XLSX, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			put(PPT, "application/vnd.ms-powerpoint");
			put(PPTX, "application/vnd.openxmlformats-officedocument.presentationml.presentation");
			put(HEIC, "image/heic");
		}
	};

	public static final String DOC = "doc";

	public static final String DOCX = "docx";

	public static final String GIF = "gif";

	public static final String JPEG = "jpeg";

	public static final String JPG = "jpg";

	public static final String PDF = "pdf";

	public static final String PNG = "png";

	public static final String PPT = "ppt";

	public static final String PPTX = "pptx";

	public static final String XLS = "xls";

	public static final String XLSX = "xlsx";
	
	public static final String HEIC = "heic";
	
	public static final String ATTACHMENT = "attachment;filename=";
	
	public static final String INLINE = "inline;filename=";
	
	public static final String FILE_SPLIT_PARTERN = "\\.(?=[^\\.]+$)";

	public static BufferedImage decodeToImage(String imageString) {
		BufferedImage image = null;

		byte[] imageByte;

		try {
			imageByte = Base64.decodeBase64(imageString);

			ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);

			image = ImageIO.read(bis);

			bis.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		return image;
	}

	/**
	 * Encode image to string
	 *
	 * @param image The image to encode
	 * @param type jpeg, bmp, ...
	 * @return encoded string
	 */
	public static String encodeToString(BufferedImage image, String type) {
		String imgStr = StringPool.BLANK;

		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		try {
			ImageIO.write(image, type, bos);

			byte[] imageBytes = bos.toByteArray();

			imgStr = Base64.encodeBase64String(imageBytes);

			bos.close();
		} catch (IOException ioe) {
			log.error(ioe.getMessage(), ioe);
		}

		return imgStr;
	}

	public static String getAutoIncrementName(String folder, String fileName) {
		Pattern p = Pattern.compile("(.*?)_(\\d+)?(\\..*)?");

		while (new File(folder + StringPool.SLASH + fileName).exists()) {
			Matcher m = p.matcher(fileName);

			if (!m.matches()) {
				break;
			}

			StringBuilder sb = new StringBuilder();

			// group 1 is the prefix, group 2 is the number, group 3 is the suffix
			sb.append(m.group(1));
			sb.append(StringPool.UNDERLINE);

			if (Validator.isNull(m.group(2))) {
				sb.append(1);
			} else {
				sb.append(Integer.parseInt(m.group(2)) + 1);
			}

			if (Validator.isNull(m.group(3))) {
				sb.append(StringPool.BLANK);
			} else {
				sb.append(m.group(3));
			}

			fileName = sb.toString();
		}

		return fileName;
	}

	public static BufferedImage getBufferedImage(Blob blob) {
		if (blob == null) {
			return null;
		}

		BufferedImage buffered = null;

		try {
			InputStream inStream = blob.getBinaryStream();

			buffered = ImageIO.read(inStream);
		} catch (SQLException sqlex) {
			log.error(sqlex.getMessage(), sqlex);
		} catch (IOException ioe) {
			log.error(ioe.getMessage(), ioe);
		}

		return buffered;
	}

	public static String getContentType(String extention) {
		return FILE_TYPE_MAP.get(extention);
	}

	public static String getFileChecksum(MessageDigest digest, byte[] bytes) {
		StringBuilder sb = new StringBuilder();

		byte[] mdbytes = digest.digest(bytes);

		for (int i = 0; i < mdbytes.length; i++) {
			sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
		}

		return sb.toString();
	}

	public static String getFileExtension(String fileName) {
		if (Validator.isNull(fileName)) {
			return null;
		}

		String[] splits = fileName.split(FILE_SPLIT_PARTERN);

		if (splits.length < 2) {
			return null;
		}

		return splits[1].toLowerCase();
	}

	public static String getFileName(String fileName) {
        if (Validator.isNull(fileName)) {
            return null;
        }

        String[] splits = fileName.split(FILE_SPLIT_PARTERN);

        if (splits.length < 2) {
            return null;
        }

        return splits[0].toLowerCase();
    }
	
	public static String getFileNameTimestamp(String fileName) {
		if (Validator.isNull(fileName)) {
			return null;
		}

		String[] splits = fileName.split(FILE_SPLIT_PARTERN);

		if (splits.length < 2) {
			return fileName;
		}

		StringBuilder sb = new StringBuilder(5);

		sb.append(splits[0]);
		sb.append(StringPool.UNDERLINE);
		sb.append(DateUtil.formatStringLongTimestamp(new Date()));
		sb.append(StringPool.PERIOD);
		sb.append(splits[1]);

		return sb.toString();
	}

	public static String getNormalizeName(String fileName) {
		if (Validator.isNull(fileName)) {
			return null;
		}

		fileName = AccentRemoverUtil.removeAccent(fileName).toLowerCase();

		String[] splits = fileName.split(FILE_SPLIT_PARTERN);

		if (splits.length < 2) {
			return fileName;
		}

		StringBuilder sb = new StringBuilder(5);

		sb.append(splits[0]);
		sb.append(StringPool.DASH);
		sb.append(DateUtil.formatStringShortTimestamp(new Date()));
		sb.append(StringPool.PERIOD);
		sb.append(splits[1]);

		return StringUtil.replace(StringUtil.trimAll(sb.toString()), StringPool.SPACE, StringPool.DASH);
	}

	public static String getFileSHA256Checksum(byte[] bytes) {
		String checkSum = StringPool.BLANK;

		try {
			MessageDigest sha256Digest = MessageDigest.getInstance("SHA-256");

			checkSum = getFileChecksum(sha256Digest, bytes);
		} catch (NoSuchAlgorithmException nsae) {
			log.error(nsae.getMessage(), nsae);
		}

		return checkSum;
	}

	public static String getImageBase64String(Blob blob, String type) {
		String imgStr = StringPool.BLANK;

		try {
			InputStream inStream = blob.getBinaryStream();

			imgStr = getImageBase64String(inStream, type);
		} catch (SQLException sqlex) {
			log.error(sqlex.getMessage(), sqlex);
		}

		return imgStr;
	}

	public static String getImageBase64String(InputStream inStream, String type) {
		String imgStr = StringPool.BLANK;

		try {
			BufferedImage img = ImageIO.read(inStream);

			imgStr = encodeToString(img, type);
		} catch (IOException ioe) {
			log.error(ioe.getMessage(), ioe);
		}

		return imgStr;
	}

	public static String getImageBase64String(String url, String type) {
		String imgStr = StringPool.BLANK;

		try {
			BufferedImage img = ImageIO.read(new File(url));

			imgStr = encodeToString(img, type);
		} catch (IOException ioe) {
			log.error(ioe.getMessage(), ioe);
		}

		return imgStr;
	}

	public static String getImageSrcBase64String(Blob blob, String type) {
		StringBuilder sb = new StringBuilder();

		sb.append("data:image");
		sb.append(StringPool.SLASH);
		sb.append(type);
		sb.append(";base64,");
		sb.append(getImageBase64String(blob, type));

		return sb.toString();
	}

	public static String getImageSrcBase64String(BufferedImage image, String type) {
		StringBuilder sb = new StringBuilder();

		sb.append("data:image");
		sb.append(StringPool.SLASH);
		sb.append(type);
		sb.append(";base64,");
		sb.append(encodeToString(image, type));

		return sb.toString();
	}

	public static String getImageSrcBase64String(InputStream inStream, String type) {
		StringBuilder sb = new StringBuilder();

		sb.append("data:image");
		sb.append(StringPool.SLASH);
		sb.append(type);
		sb.append(";base64,");
		sb.append(getImageBase64String(inStream, type));

		return sb.toString();
	}

	public static String getImageSrcBase64String(String url, String type) {
		StringBuilder sb = new StringBuilder();

		sb.append("data:image");
		sb.append(StringPool.SLASH);
		sb.append(type);
		sb.append(";base64,");
		sb.append(getImageBase64String(url, type));

		return sb.toString();
	}

	public static Long getKilobyte(Long sizeB) {
		return sizeB / (1024);
	}

	public static Long getMegabyte(Long sizeB) {
		return getKilobyte(sizeB) / 1024;
	}

	public static String getOrCreateFolder(String rootDirName, String newDirName) {
		StringBuilder sb = new StringBuilder();

		sb.append(rootDirName);

		if (newDirName != null) {
			sb.append(StringPool.SLASH);
			sb.append(newDirName);
		}

		File newDir = new File(sb.toString());

		if (!newDir.exists() && !newDir.mkdirs()) {
			log.warn("Cannot create new folder: {}", newDirName);

			return null;
		}

		if (!newDir.isDirectory()) {
			if (!newDir.delete() || !newDir.mkdir()) {
				log.warn("Cannot create new folder, the target is not a folder: {}", newDirName);

				return null;
			}
		}

		return sb.toString();
	}

	public static String getSafeFileName(String input) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);

			if (c != '/' && c != '\\' && c != 0) {
				sb.append(c);
			}
		}

		return sb.toString();
	}

	public static String sanitizeFilename(String input) {
		String[] invalidSymbols = {"\\", "/", ":", "*", "?", "\"", "<", ">", "|", "(", ")", "&"};

		for (String currentSymbol : invalidSymbols) {
			input = input.replaceAll("[\\" + currentSymbol + "]", "_");
		}

		return input;
	}


	public static boolean isValidFileExtension(String extension, List<String> allowExts) {

		return allowExts.contains(extension.toLowerCase());
	}

	public static boolean isValidFileExtension(String extension, String[] allowExts) {
		List<String> exts = Arrays.asList(allowExts);

		return isValidFileExtension(extension, exts);
	}

	public static boolean isValidMaxSize(int size, Long maxSize) {
		if (Validator.isNull(maxSize)) {
			return true;
		}

		return size <= maxSize.intValue();
	}

	public static boolean write(byte[] data, String dirPath, String fileName) {
		boolean success = false;

		FileOutputStream out = null;

		try {
			out = new FileOutputStream(dirPath + StringPool.SLASH + fileName);

			out.write(data);

			success = true;
		} catch (Exception ex) {
			log.error("Cannot create file: " + fileName);
		} finally {
			try {
				out.close();
			} catch (IOException ioe) {
				log.error(ioe.getMessage(), ioe);
			}
		}

		return success;
	}

	public static boolean write(MultipartFile file, String dirPath, String fileName) {
		boolean success = false;

		FileOutputStream out = null;

		try {
			out = new FileOutputStream(dirPath + StringPool.SLASH + fileName);

			out.write(file.getBytes());

			success = true;
		} catch (Exception ex) {
			log.error("Cannot create file: " + fileName);
		} finally {
			try {
				out.close();
			} catch (IOException ioe) {
				log.error(ioe.getMessage(), ioe);
			}
		}

		return success;
	}

	public static void write(InputStream in, File file) {

		try {
			OutputStream out = new FileOutputStream(file);

			int read = 0;

			byte[] bytes = new byte[5120];

			while ((read = in.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}

			out.close();

			in.close();
		} catch (FileNotFoundException fnfe) {
			log.error(fnfe.getMessage(), fnfe);
		} catch (IOException ioe) {
			log.error(ioe.getMessage(), ioe);
		}
	}
}
