import cn.Application;
import cn.enums.SkillType;
import cn.mapper.GameSkillsMapper;
import cn.model.GameSkills;
import cn.service.IGameSkillsService;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.property.VerticalAlignment;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

import static com.itextpdf.kernel.PdfException.PdfEncodings;
import static com.itextpdf.kernel.pdf.PdfName.BaseFont;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ImportDataTest {

    @Autowired
    private GameSkillsMapper gameSkillsMapper;

    @Autowired
    private IGameSkillsService iGameSkillsService;

    public static List<String> splitString(String str, int length) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < str.length(); i += length) {
            result.add(str.substring(i, Math.min(i + length, str.length())));
        }
        return result;
    }

    @Test
    public void ImportData2() throws Exception{
        System.out.println(splitString("张三是个大笨蛋", 3));
    }

    @Test
    public void ImportData4() throws Exception{

        try (PdfWriter writer = new PdfWriter("D:\\game_skills.pdf");
             PdfDocument pdf = new PdfDocument(writer);
             com.itextpdf.layout.Document document = new com.itextpdf.layout.Document(pdf)) {
             //document.setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA));
            Table table = new Table(UnitValue.createPercentArray(new float[]{50, 50}));
            table.setWidth(UnitValue.createPercentValue(40)); // 设置表格宽度为100%

            //PdfFont font = PdfFontFactory.createFont("STSongStd-Light", "UniGB-UCS2-H", false);
            //PdfFont font = PdfFontFactory.createFont("arial.ttf", "GBK", false);
            PdfFont font = PdfFontFactory.createFont
                    ("C:/Windows/Fonts/simsun.ttc,0", com.itextpdf.io.font.PdfEncodings.IDENTITY_H,false);


            // 添加表头
            table.addHeaderCell(new Cell().add(new Paragraph("游戏技能名称").setTextAlignment(TextAlignment.CENTER)
                    .setFont(font)
                    .setFontSize(12)// 设置字体大小为12
                    .setBorder(Border.NO_BORDER) // 设置边框
                    .setPadding(5) // 增加内边距
                    .setBackgroundColor(new DeviceRgb(255, 255, 255)) // 设置背景颜色
                    .setWidth(UnitValue.createPercentValue(50)) // 设置单元格宽度为50%
                    .setHorizontalAlignment(HorizontalAlignment.CENTER) // 设置水平对齐方式
                    .setVerticalAlignment(VerticalAlignment.MIDDLE) // 设置垂直对齐方式
            ));

            table.addHeaderCell(new Cell().add(new Paragraph("游戏技能图片").setTextAlignment(TextAlignment.CENTER)
                    .setFont(font)
                    .setFontSize(12)// 设置字体大小为12
                    .setBorder(Border.NO_BORDER) // 设置边框
                    .setPadding(5) // 增加内边距
                    .setBackgroundColor(new DeviceRgb(255, 255, 255)) //
                    .setHorizontalAlignment(HorizontalAlignment.CENTER) // 设置水平对齐方式
                    .setVerticalAlignment(VerticalAlignment.MIDDLE) // 设置垂直对齐方式// 设置背景颜色
            ));

            List<GameSkills> list = iGameSkillsService.getAllData();

            for (int i = 0; i < list.size(); i++) {
                GameSkills gameSkill = list.get(i);


                // 获取图片的Base64编码字符串
                // 将Base64字符串解码为字节数据
                byte[] imageBytes = java.util.Base64.getDecoder().decode(gameSkill.getSkillImage());

                // 使用字节数据创建ImageData对象
                ImageData itemData = ImageDataFactory.create(imageBytes);

                // 创建Image对象并添加到文档中
                Image image = new Image(itemData).setAutoScale(false);

                Paragraph paragraph = new Paragraph(gameSkill.getSkillName()).setTextAlignment(TextAlignment.CENTER)
                        .setFont(font)
                        .setFontSize(12); // 设置字体大小为12

                // 创建单元格并添加内容
                Cell nameCell = new Cell().add(paragraph).
                        setTextAlignment(TextAlignment.CENTER)
                        .setPadding(5) // 增加内边距
//                        .setFont(font)
//                        .setFontSize(12)
                        .setBackgroundColor(new DeviceRgb(255, 255, 255)) //
                        .setHorizontalAlignment(HorizontalAlignment.CENTER) // 设置水平对齐方式
                        .setVerticalAlignment(VerticalAlignment.MIDDLE); // 设置垂直对齐方式// 设置背景颜色
                //.setWidth(UnitValue.createPercentValue(70)); // 设置单元格宽度为50%


                Cell imageCell = new Cell().add(image).setBorder(Border.NO_BORDER);

                // 将单元格添加到表格的数据行中
                table.addCell(nameCell);
                table.addCell(imageCell);
            }

            document.add(table);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void ImportData3() throws Exception{
        System.out.println(iGameSkillsService.search("强身中医",2));
    }

    @Test
    public void ImportData() throws Exception{


    Document document = Jsoup.parse(new URL("https://xyq.netease.com/thread-7673630-1-1.html?refer_site=chatbot"),
            10000);
    Elements element = document.getElementsByClass("t_table");

    Map<String, LinkedHashMap<String, String>> hashMap = new LinkedHashMap<String, LinkedHashMap<String, String>>();

        for (int m = 1; m < element.size(); m++) {
        Element item = element.get(m);
        Elements tr = item.select("tbody tr");

        LinkedHashMap<String, String> linkedHashMap = null;

        String tag = null;
        for (int i = 0; i < tr.size(); i++) {
            if((i-1)%2 == 0){
                continue;
            }
            Elements td = tr.get(i).select("td");

            boolean flag = false;
            Elements gd = tr.get(i+1).select("td");
            String html = getHtml(td);

            if(html.length() > 0) {
                linkedHashMap =  new LinkedHashMap();
                hashMap.put(html, linkedHashMap);
                tag = html;
                flag = true;
            }

            int index = 0;

            LinkedHashMap<String, String> map = hashMap.get(tag);

            if(flag){
                index = 1;

                for (int k = index; k < td.size(); k++) {
                    extracted2(td, gd, map, k, "font img", tag);
                    extracted2(td, gd, map, k, "img", tag);
                }
            }else{
                for (int k = 0; k < td.size(); k++) {
                    extracted(td, gd, map, k, "font img", tag);
                    extracted(td, gd, map, k, "img", tag);
                }


            }
        }
    }
        for(Map.Entry entry: hashMap.entrySet()) {
        String entryKey = (String) entry.getKey();
        System.out.println(entryKey);
        LinkedHashMap<String, String> item = (LinkedHashMap<String, String>) entry.getValue();

        for (Map.Entry fv : item.entrySet()) {
            System.out.println(fv.getKey() + ":" + fv.getValue());
            GameSkills gameSkills = new GameSkills();
            SkillType skillType = SkillType.getSkillTypeByName(entryKey);
            gameSkills.setSkillType(skillType.getCode());
            gameSkills.setSkillTypeName(skillType.getName());
            gameSkills.setSkillName(fv.getValue().toString());
            // 引用形式的描述信息
            String url = fv.getKey().toString();
            gameSkills.setSkillImage(extracted(url));
            gameSkills.setSkillUrl(url);
            gameSkillsMapper.insert(gameSkills);
        }
    }
}

    private String extracted(String url) throws IOException {
        URL urlObject = new URL(url);
        URLConnection connection = urlObject.openConnection();
        InputStream inputStream = connection.getInputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int len;
        byte[] buffer = new byte[4096];
        while ((len = inputStream.read(buffer)) != -1) {
            baos.write(buffer, 0, len);
        }
        byte[] data = baos.toByteArray();
        String base64Encoded = Base64.getEncoder().encodeToString(data);
        return base64Encoded;
    }

    private static String getHtml(Elements td) {
        String html = td.get(0).select("font b").html();
        if(html!=null && !html.equals("")){
            return html;
        }else{
            return td.get(0).select("b").html();
        }
    }

    private static void extracted(Elements td, Elements gd, LinkedHashMap<String, String> map, int k, String key, String tag) {
        Elements js = td.get(k).select(key);

        if(tag == null){
            return;
        }

        if(td.html().contains("https://ok.166.net/forum/xyq/forum/202309/03/64f35d5e77c8d.png")){
            System.out.println("211");
        }
        for (Element gs :
                js) {
            String attr = gs.attr("src");
            if (attr.length() > 0) {
                Elements wz = gd.get(k).select("font");
                if(!"".equals(wz.html())) {
                    map.put(attr, wz.html());
                }else if(gd.get(k).select("div").size()!=0){
                    Elements qs = gd.get(k).select("div");
                    map.put(attr, qs.html());
                }else if(gd.get(k).select("td").size()!=0){
                    Elements qs = gd.get(k).select("td");
                    map.put(attr, qs.html());
                }
            }
        }
    }

    private static void extracted2(Elements td, Elements gd, LinkedHashMap<String, String> map, int k, String key, String tag) {
        Elements js = td.get(k).select(key);

        if(tag == null){
            return;
        }

        if(td.html().contains("https://ok.166.net/forum/xyq/forum/202309/03/64f35d5e77c8d.png")){
            System.out.println("211");
        }

        for (Element gs :
                js) {
            String attr = gs.attr("src");
            if (attr.length() > 0) {
                Elements wz = gd.get(k-1).select("font");
                if(!"".equals(wz.html())) {
                    //sstem.out.println("td = " + td + ", gd = " + gd + ", map = " + map + ", k = " + k + ", tag = " + tag);
                    map.put(attr, wz.html());
                }else if(gd.get(k-1).select("div").size()!=0){
                    Elements qs = gd.get(k-1).select("div");
                    map.put(attr, qs.html());
                }else if(gd.get(k-1).select("td").size()!=0){
                    Elements qs = gd.get(k-1).select("td");
                    map.put(attr, qs.html());
                }
            }
        }
    }


}
