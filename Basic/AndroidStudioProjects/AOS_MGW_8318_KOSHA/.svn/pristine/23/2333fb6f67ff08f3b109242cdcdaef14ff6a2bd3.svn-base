package com.hs.mobile.gw.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Pattern;

import org.apache.http.util.ByteArrayBuffer;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import androidx.core.content.FileProvider;
import android.text.TextUtils;

public class DownloadUtils {
	public static final String DEFAULT_ATTACHMENT_MIME_TYPE = "application/octet-stream";

	/*
	 * http://www.w3schools.com/media/media_mimeref.asp
	 */
	public static final String[][] MIME_TYPE_BY_EXTENSION_MAP = new String[][] { { "", "application/octet-stream" },
			{ "323", "text/h323" }, { "acx", "application/internet-property-stream" }, { "ai", "application/postscript" },
			{ "aif", "audio/x-aiff" }, { "aifc", "audio/x-aiff" }, { "aiff", "audio/x-aiff" },
			{ "apk", "application/vnd.android.package-archive" }, { "asf", "video/x-ms-asf" }, { "asr", "video/x-ms-asf" },
			{ "asx", "video/x-ms-asf" }, { "au", "audio/basic" }, { "avi", "video/x-msvideo" }, { "axs", "application/olescript" },
			{ "bas", "text/plain" }, { "bcpio", "application/x-bcpio" }, { "bin", "application/octet-stream" }, { "bmp", "image/bmp" },
			{ "c", "text/plain" }, { "cat", "application/vnd.ms-pkiseccat" }, { "cdf", "application/x-cdf" },
			{ "cer", "application/x-x509-ca-cert" }, { "class", "application/octet-stream" }, { "clp", "application/x-msclip" },
			{ "cmx", "image/x-cmx" }, { "cod", "image/cis-cod" }, { "cpio", "application/x-cpio" }, { "crd", "application/x-mscardfile" },
			{ "crl", "application/pkix-crl" }, { "crt", "application/x-x509-ca-cert" }, { "csh", "application/x-csh" },
			{ "css", "text/css" }, { "dcr", "application/x-director" }, { "der", "application/x-x509-ca-cert" },
			{ "dir", "application/x-director" }, { "dll", "application/x-msdownload" }, { "dms", "application/octet-stream" },
			{ "doc", "application/msword" }, { "dot", "application/msword" }, { "dvi", "application/x-dvi" },
			{ "dxr", "application/x-director" }, { "eps", "application/postscript" }, { "etx", "text/x-setext" },
			{ "evy", "application/envoy" }, { "exe", "application/octet-stream" }, { "fif", "application/fractals" },
			{ "flr", "x-world/x-vrml" }, { "gif", "image/gif" }, { "gtar", "application/x-gtar" }, { "gz", "application/x-gzip" },
			{ "h", "text/plain" }, { "hdf", "application/x-hdf" }, { "hlp", "application/winhlp" }, { "hqx", "application/mac-binhex40" },
			{ "hta", "application/hta" }, { "htc", "text/x-component" }, { "htm", "text/html" }, { "html", "text/html" },
			{ "htt", "text/webviewhtml" }, { "hwp", "application/hwp" }, { "ico", "image/x-icon" }, { "ief", "image/ief" },
			{ "iii", "application/x-iphone" }, { "ins", "application/x-internet-signup" }, { "isp", "application/x-internet-signup" },
			{ "jfif", "image/pipeg" }, { "jpe", "image/jpeg" }, { "jpeg", "image/jpeg" }, { "jpg", "image/jpeg" },
			{ "js", "application/x-javascript" }, { "latex", "application/x-latex" }, { "lha", "application/octet-stream" },
			{ "lsf", "video/x-la-asf" }, { "lsx", "video/x-la-asf" }, { "lzh", "application/octet-stream" },
			{ "m13", "application/x-msmediaview" }, { "m14", "application/x-msmediaview" }, { "m3u", "audio/x-mpegurl" },
			{ "man", "application/x-troff-man" }, { "mdb", "application/x-msaccess" }, { "me", "application/x-troff-me" },
			{ "mht", "message/rfc822" }, { "mhtml", "message/rfc822" }, { "mid", "audio/mid" }, { "mny", "application/x-msmoney" },
			{ "mov", "video/quicktime" }, { "movie", "video/x-sgi-movie" }, { "mp2", "video/mpeg" }, { "mp3", "audio/mpeg" },
			{ "mpa", "video/mpeg" }, { "mpe", "video/mpeg" }, { "mpeg", "video/mpeg" }, { "mpg", "video/mpeg" },
			{ "mpp", "application/vnd.ms-project" }, { "mpv2", "video/mpeg" }, { "ms", "application/x-troff-ms" },
			{ "mvb", "application/x-msmediaview" }, { "nws", "message/rfc822" }, { "oda", "application/oda" },
			{ "p10", "application/pkcs10" }, { "p12", "application/x-pkcs12" }, { "p7b", "application/x-pkcs7-certificates" },
			{ "p7c", "application/x-pkcs7-mime" }, { "p7m", "application/x-pkcs7-mime" }, { "p7r", "application/x-pkcs7-certreqresp" },
			{ "p7s", "application/x-pkcs7-signature" }, { "pbm", "image/x-portable-bitmap" }, { "pdf", "application/pdf" },
			{ "pfx", "application/x-pkcs12" }, { "pgm", "image/x-portable-graymap" }, { "pko", "application/ynd.ms-pkipko" },
			{ "pma", "application/x-perfmon" }, { "pmc", "application/x-perfmon" }, { "pml", "application/x-perfmon" },
			{ "pmr", "application/x-perfmon" }, { "pmw", "application/x-perfmon" }, { "png", "image/png" },
			{ "pnm", "image/x-portable-anymap" }, { "pot,", "application/vnd.ms-powerpoint" }, { "ppm", "image/x-portable-pixmap" },
			{ "pps", "application/vnd.ms-powerpoint" }, { "ppt", "application/vnd.ms-powerpoint" }, { "prf", "application/pics-rules" },
			{ "ps", "application/postscript" }, { "pub", "application/x-mspublisher" }, { "qt", "video/quicktime" },
			{ "ra", "audio/x-pn-realaudio" }, { "ram", "audio/x-pn-realaudio" }, { "ras", "image/x-cmu-raster" }, { "rgb", "image/x-rgb" },
			{ "rmi", "audio/mid" }, { "roff", "application/x-troff" }, { "rtf", "application/rtf" }, { "rtx", "text/richtext" },
			{ "scd", "application/x-msschedule" }, { "sct", "text/scriptlet" }, { "setpay", "application/set-payment-initiation" },
			{ "setreg", "application/set-registration-initiation" }, { "sh", "application/x-sh" }, { "shar", "application/x-shar" },
			{ "sit", "application/x-stuffit" }, { "snd", "audio/basic" }, { "spc", "application/x-pkcs7-certificates" },
			{ "spl", "application/futuresplash" }, { "src", "application/x-wais-source" }, { "sst", "application/vnd.ms-pkicertstore" },
			{ "stl", "application/vnd.ms-pkistl" }, { "stm", "text/html" }, { "svg", "image/svg+xml" },
			{ "sv4cpio", "application/x-sv4cpio" }, { "sv4crc", "application/x-sv4crc" }, { "swf", "application/x-shockwave-flash" },
			{ "t", "application/x-troff" }, { "tar", "application/x-tar" }, { "tcl", "application/x-tcl" }, { "tex", "application/x-tex" },
			{ "texi", "application/x-texinfo" }, { "texinfo", "application/x-texinfo" }, { "tgz", "application/x-compressed" },
			{ "tif", "image/tiff" }, { "tiff", "image/tiff" }, { "tr", "application/x-troff" }, { "trm", "application/x-msterminal" },
			{ "tsv", "text/tab-separated-values" }, { "txt", "text/plain" }, { "uls", "text/iuls" }, { "ustar", "application/x-ustar" },
			{ "vcf", "text/x-vcard" }, { "vrml", "x-world/x-vrml" }, { "wav", "audio/x-wav" }, { "wcm", "application/vnd.ms-works" },
			{ "wdb", "application/vnd.ms-works" }, { "wks", "application/vnd.ms-works" }, { "wmf", "application/x-msmetafile" },
			{ "wps", "application/vnd.ms-works" }, { "wri", "application/x-mswrite" }, { "wrl", "x-world/x-vrml" },
			{ "wrz", "x-world/x-vrml" }, { "xaf", "x-world/x-vrml" }, { "xbm", "image/x-xbitmap" }, { "xla", "application/vnd.ms-excel" },
			{ "xlc", "application/vnd.ms-excel" }, { "xlm", "application/vnd.ms-excel" }, { "xls", "application/vnd.ms-excel" },
			{ "xlt", "application/vnd.ms-excel" }, { "xlw", "application/vnd.ms-excel" },
			{ "docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document" },
			{ "docm", "application/vnd.openxmlformats-officedocument.wordprocessingml.document" },
			{ "pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation" },
			{ "pptm", "application/vnd.openxmlformats-officedocument.presentationml.presentation" },
			{ "xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" },
			{ "xlsm", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" },
			{ "cab", "application/vnd.ms-cab-compressed" }, { "xof", "x-world/x-vrml" }, { "xpm", "image/x-xpixmap" },
			{ "xwd", "image/x-xwindowdump" }, { "z", "application/x-compress" }, { "zip", "application/zip" } };

	public static String unfold(String s) {
		if (s == null) {
			return null;
		}
		return s.replaceAll("\r|\n", "");
	}

	public static String foldAndEncode(String s) {
		return s;
	}

	/**
	 * Returns the named parameter of a header field. If name is null the first
	 * parameter is returned, or if there are no additional parameters in the
	 * field the entire field is returned. Otherwise the named parameter is
	 * searched for in a case insensitive fashion and returned. If the parameter
	 * cannot be found the method returns null.
	 * 
	 * @param header
	 * @param name
	 * @return
	 */
	public static String getHeaderParameter(String header, String name) {
		if (header == null) {
			return null;
		}
		header = header.replaceAll("\r|\n", "");
		String[] parts = header.split(";");
		if (name == null) {
			return parts[0];
		}
		for (String part : parts) {
			if (part.trim().toLowerCase().startsWith(name.toLowerCase())) {
				String parameter = part.split("=", 2)[1].trim();
				if (parameter.charAt(0) == '\"' && parameter.endsWith("\"")) {
					return parameter.substring(1, parameter.length() - 1);
				} else {
					return parameter;
				}
			}
		}
		return null;
	}

	/**
	 * Returns true if the given mimeType matches the matchAgainst
	 * specification.
	 * 
	 * @param mimeType
	 *            A MIME type to check.
	 * @param matchAgainst
	 *            A MIME type to check against. May include wildcards such as
	 *            image/* or * /*.
	 * @return
	 */
	public static boolean mimeTypeMatches(String mimeType, String matchAgainst) {
		Pattern p = Pattern.compile(matchAgainst.replaceAll("\\*", "\\.\\*"), Pattern.CASE_INSENSITIVE);
		return p.matcher(mimeType).matches();
	}

	/**
	 * Returns true if the given mimeType matches any of the matchAgainst
	 * specifications.
	 * 
	 * @param mimeType
	 *            A MIME type to check.
	 * @param matchAgainst
	 *            An array of MIME types to check against. May include wildcards
	 *            such as image/* or * /*.
	 * @return
	 */
	public static boolean mimeTypeMatches(String mimeType, String[] matchAgainst) {
		for (String matchType : matchAgainst) {
			if (mimeTypeMatches(mimeType, matchType)) {
				return true;
			}
		}
		return false;
	}

	public static String getMimeTypeByExtension(String filename) {
		if (filename != null && filename.lastIndexOf('.') != -1) {
			String extension = filename.substring(filename.lastIndexOf('.') + 1).toLowerCase();
			for (String[] contentTypeMapEntry : MIME_TYPE_BY_EXTENSION_MAP) {
				if (TextUtils.equals(contentTypeMapEntry[0], extension)) {
					return contentTypeMapEntry[1];
				}
			}
		}
		return DEFAULT_ATTACHMENT_MIME_TYPE;
	}

	public static String DownloadFromUrl(URL url, String fileName, File defaultDownloadDir) {
		File tmpFile = new File(defaultDownloadDir.getAbsolutePath() + File.separator + fileName);
		InputStream is = null;
		BufferedInputStream bis = null;
		FileOutputStream fos = null;
		try {
			long startTime = System.currentTimeMillis();
			Debug.trace("DOWNLOAD", "download begining");
			Debug.trace("DOWNLOAD", "download url:" + url);
			Debug.trace("DOWNLOAD", "downloaded file name:" + tmpFile.getName());
			/* Open a connection to that URL. */
			URLConnection ucon = url.openConnection();

			/*
			 * Define InputStreams to read from the URLConnection.
			 */

			is = ucon.getInputStream();
			bis = new BufferedInputStream(is);
			

			/*
			 * Read bytes to the Buffer until there is nothing more to read(-1).
			 */
			ByteArrayBuffer baf = new ByteArrayBuffer(50);
			int current = 0;
			while ((current = bis.read()) != -1) {
				baf.append((byte) current);
			}
			/* Convert the Bytes read to a String. */
			Debug.trace("DOWNLAOD", "tmpFile.exists?? " + tmpFile.exists());
			fos = new FileOutputStream(tmpFile);
			fos.write(baf.toByteArray());
			Debug.trace("DOWNLOAD", "download ready in" + ((System.currentTimeMillis() - startTime) / 1000) + " sec");

		} catch (IOException e) {
			Debug.trace("DOWNLOAD", "Error: " + e);
		} catch (Exception e){
			Debug.trace("DOWNLOAD", "Error: " + e);
		} finally {
			try {
				if (is != null) is.close();
				if (bis !=null) bis.close();
				if (fos !=null) fos.close();
			} catch (IOException e) {
				Debug.trace("DOWNLOAD", "Error: " + e);
			}
		}
		
		return tmpFile.getAbsolutePath();
	}

	public static Intent createLaunchFileIntent(Activity activity, String filename) {
		// SEOJAEHWA : N 이상의 경우 FileProvider 를 사용해야 함
		Intent newIntent = new Intent(Intent.ACTION_VIEW);
		File file = new File(filename);
		String mimeType;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file.getAbsolutePath());
			mimeType = URLConnection.guessContentTypeFromStream(fis);
			if (mimeType == null) mimeType = URLConnection.guessContentTypeFromName(file.getAbsolutePath());
			Debug.trace("DOWNLOAD", filename);
			Debug.trace("DOWNLOAD", "mimeType->" + mimeType);

			// SEOJAEHWA : 기존 코드는 보호하고 'N' 이상 버전에서는 file path 로 세팅
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
				final String authority = activity.getPackageName() + ".fileprovider";
				final Uri uri = FileProvider.getUriForFile(activity, authority, file);
				newIntent.setDataAndType(uri, mimeType);
				newIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
			} else {
				newIntent.setDataAndType(Uri.fromFile(file), mimeType);
			}
//			activity.startActivity(newIntent);

		} catch (FileNotFoundException e) {
			Debug.trace(e.getMessage());
			PopupUtil.showToastMessage(activity, "No handler for this type of file.");
		} catch (IOException e) {
			Debug.trace(e.getMessage());
			PopupUtil.showToastMessage(activity, "No handler for this type of file.");
		} catch (ActivityNotFoundException e) {
			Debug.trace(e.getMessage());
			PopupUtil.showToastMessage(activity, "No handler for this type of file.");
		}  catch (Exception e){
			Debug.trace(e.getMessage());
			PopupUtil.showToastMessage(activity, "No handler for this type of file.");
		} finally{
			try {
				if(fis !=null) fis.close();
			} catch (IOException e) {
				Debug.trace(e.getMessage());
			}
		}
		return newIntent;
	}
}
