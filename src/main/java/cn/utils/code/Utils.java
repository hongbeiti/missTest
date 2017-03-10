package cn.utils.code;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import javax.imageio.ImageIO;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

/** 工具类 */
public class Utils {

	/** 日期格式： yyyy-MM-dd HH:mm:ss */
	private static String DF_DATETIME = "yyyyMMdd";
	private static float alpha = 1f;

	/**
	 * @Description 把文字转化为二维码对象
	 * @param text
	 *            二维码内容
	 * @param width
	 *            二维码高度
	 * @param height
	 *            二维码宽度
	 * @return BufferImage 返回文件
	 * @throws Exception
	 */
	public static BufferedImage toBufferedImage(String text, int width, int height) throws Exception {
		int BLACK = 0xFF000000;
		int WHILE = 0xFFFFFFFF;
		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");// 设置所使用字符集编码
		hints.put(EncodeHintType.MARGIN, 1);
		BitMatrix matrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHILE);
			}
		}
		return image;
	}

	public static void markImageByCode(Image img, String srcImagPath, String targetPath, int positionWidth,
			int positionHeight) {
		OutputStream os = null;
		try {
			Image srcImg = ImageIO.read(new File(srcImagPath));

			BufferedImage buffImag = new BufferedImage(srcImg.getWidth(null), srcImg.getHeight(null),
					BufferedImage.TYPE_INT_RGB);

			// 1、得到画笔
			Graphics2D g = buffImag.createGraphics();

			// 2、设置对线段锯齿状边缘处理
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

			// 3、二维码位置
			g.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), srcImg.getHeight(null), Image.SCALE_SMOOTH), 0,
					0, null);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));

			g.drawImage(img, positionWidth, positionHeight, null);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
			// 4、释放资源
			g.dispose();

			// 5、生成图片(建议生成png的，jsp会失真)
			os = new FileOutputStream(targetPath);
			ImageIO.write(buffImag, "PNG", os);

			System.out.println("二维码图片生成成功");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != os)
					os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void pressText(String pressText, String srcImageFile, String destImageFie, int x, int y,
			float alpha) {
		try {
			File img = new File(srcImageFile);
			Image src = ImageIO.read(img);
			int width = src.getWidth(null);
			int height = src.getHeight(null);
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = image.createGraphics();
			// 开文字挖锯齿 去文字毛刺
			g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			g.drawImage(src, 0, 0, width, height, null);
			// 设置颜色
			g.setColor(new Color(89, 87, 87));
			// 设置Font
			g.setFont(new Font("方正兰亭中黑_GBK", Font.BOLD, 14));
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
			// 第一参数->设置的内容，后面两个参数->文字在图片上的坐标位置(x,y) .
			g.drawString(pressText, x, y);
			g.dispose();
			ImageIO.write((BufferedImage) image, "PNG", new File(destImageFie)); // 输出到文件流
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String toStr() {
		return toStr(DF_DATETIME);
	}

	public static String toStr(String format) {
		return toStr(format, new Date());
	}

	public static String toStr(Date date) {
		return toStr(DF_DATETIME, date);
	}

	public static String toStr(String format, Date date) {
		return new SimpleDateFormat(format).format(date);
	}

	public static String formateNumber(int num) {
		DecimalFormat df = new DecimalFormat("000000");
		String str2 = df.format(num);
		return str2;
	}

	public static boolean makeDirs(String filePath) {
		File folder = new File(filePath);
		return (folder.exists() && folder.isDirectory()) ? true : folder.mkdirs();
	}

}
