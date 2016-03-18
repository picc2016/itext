package  com.zkr.itext;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.plaf.metal.MetalIconFactory.FolderIcon16;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfAnnotation;
import com.itextpdf.text.pdf.PdfAppearance;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfFormField;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;



public class Demo02 {	
	public static void main(String[] args){
		System.out.println("My First PdfTable");         
		try {
			//Step 1—Create a Document.  
			Document document = new Document(PageSize.A4); 
			//Step 2—Get a PdfWriter instance.  
			PdfWriter pdfWrite = PdfWriter.getInstance(document, new FileOutputStream("createSamplePDF02.pdf"));
			//Step 3—Open the Document.  
			document.open(); 
			BaseFont baseFont = null;
			Font fontChinese = null;
			BaseFont bfComic1 = null;
			Font fontChinese1 = null;
			Font fontChinese2 = null;
			Font fontChinese3 = null;//宋体
			BaseFont bfComic5 = null;//  黑体
			Font bfComic5Chinese = null;
			Font bfComic5Chinese4 = null;

			try {
			     //   仿宋体
				bfComic1 = BaseFont.createFont("c:\\windows\\fonts\\SIMFANG.TTF",BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
				  //楷体
				  baseFont = BaseFont.createFont("C:/Windows/Fonts/SIMKAI.ttf",BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			      //  黑体
			      bfComic5 = BaseFont.createFont("c:\\windows\\fonts\\SIMHEI.TTF",BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);			     
			      fontChinese = new Font(bfComic5, 14, Font.NORMAL);
			      fontChinese1 = new Font(bfComic1, 10, Font.NORMAL);
			      fontChinese2 = new Font(bfComic5, 10, Font.NORMAL);
			      fontChinese3 = new Font(baseFont, 10, Font.NORMAL);
			      bfComic5Chinese = new Font(bfComic5, 20, Font.NORMAL);
			      bfComic5Chinese4 = new Font(bfComic5, 14, Font.NORMAL);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
//			Chunk chunk1 = new Chunk("PICC", FontFactory.getFont(FontFactory.HELVETICA, 20, new BaseColor(255, 0, 0))); 
//			document.add(chunk1);
						
			PdfContentByte canvas = pdfWrite.getDirectContent();
			

		//	PdfContentByte canvas = pdfWrite.getDirectContent();
			//条形码
			Barcode128 code128 = new Barcode128();
			code128.setCode("* 000000000000000 *");
			code128.setCodeType(Barcode128.CODE128);
			Image code128Image = code128.createImageWithBarcode(canvas, null, null);
			code128Image.setAbsolutePosition(360, 790);
			code128Image.scalePercent(130);
			document.add(code128Image);
			
			
			
			Phrase phrase1 = new Phrase("PICC", FontFactory.getFont(FontFactory.HELVETICA, 20, new BaseColor(255, 0, 0))); 
			Phrase phrase2 = new Phrase("中国人民保险", bfComic5Chinese); 
			Phrase phrase3 = new Phrase("中国人民人寿保险股份有限公司团体保险保单 (B款)",fontChinese);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase1, 25, 795, 0);  
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase2, 75, 795, 0);  
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase3, 25, 775, 0);
			
			document.add(new Paragraph(" "));
			document.add(new Paragraph(" "));

			
			Phrase phrase4 = new Phrase(" 投	  保   须   知", bfComic5Chinese4); 
			Phrase phrase5 = new Phrase("1．本投保单是订立保险合同的重要组成部分，在填写投保单之前，请您认真阅读所投保险种的保险条款，在充分理解条款的保险责任、责任免除、合同解除等内容后，再做出投保决定。                                                                     "+
"2．投保单由投保人在保险公司销售人员的指导下用黑色或蓝黑色墨水笔填写，内容要真实、准确，字迹要工整、清晰，不得涂改，并请投保人/投保单位负责人/法定代表人在签名栏内亲笔签名。                                                                                                    "+
"3．根据我国《保险法》规定，保险公司对投保人及被保险人的有关情况有权进行询问，请如实告知；如故意或因重大过失未如实告知，我公司有权解除保险合同，并对于保险合同解除前发生的保险事故不承担保险责任。所有告知事项均以书面为准，口头告知无效。                                                                           "+
"4．保险合同成立、我公司收取首期保险费并签发保险单为合同的生效条件，保险合同生效日期以保险单载明日期为准。", fontChinese1); 
			//创建一个有2列的表格
			//PdfPTable iTable = new PdfPTable(2);
			float[] widths = {0.04f,0.96f};
			PdfPTable table = new PdfPTable(widths);
			//定义右边距和上边
//			Rectangle r = new Rectangle(100, 200);
//			table.setWidthPercentage(widths, r);
			table.setTotalWidth(540);
			table.setLockedWidth(true);
			table.writeSelectedRows(0, -1, 10, 10, canvas);///////----------

			table.addCell(phrase4);
			table.addCell(phrase5);			
			document.add(table);
			//document.add(new Paragraph("一、投保人资料",fontChinese));
			Phrase phrase6 = new Phrase("一、投保人资料",fontChinese2);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase6, 30, 663, 0);  
			document.add(new Paragraph(" "));
			
			float[] widths2 = {0.18f,0.20f,0.10f,0.20f,0.15f,0.17f};
			PdfPTable table2 = new PdfPTable(widths2);
			table2.setTotalWidth(540);
			table2.setLockedWidth(true);
//		      // 其中1为居中对齐，2为右对齐，3为左对齐
//	        table2.setHorizontalAlignment(2);
			table2.getDefaultCell().setMinimumHeight(16);
			PdfPCell cell;
			PdfPCell cell2;
			cell = new PdfPCell(new Phrase(" "));
			cell.setColspan(3);
			table2.addCell(new Phrase("投保人",fontChinese1));
			table2.addCell(cell);
			table2.addCell(new Phrase("投保人性质",fontChinese1));
			table2.addCell(" ");
			
			table2.addCell(new Phrase("联系地址",fontChinese1));
			table2.addCell(cell);
			table2.addCell(new Phrase("邮政编码",fontChinese1));
			table2.addCell(" ");
			
			table2.addCell(new Phrase("证件类型",fontChinese1));
			table2.addCell(" ");
			table2.addCell(new Phrase("证件号码",fontChinese1));
			table2.addCell(cell);
			
			table2.addCell(new Phrase("法定代表人/负责人",fontChinese1));
			table2.addCell(" ");
			table2.addCell(new Phrase("所属行业",fontChinese1));
			table2.addCell(" ");
			table2.addCell(new Phrase("员工总数",fontChinese1));
			table2.addCell(" ");

			table2.addCell(new Phrase("联系人姓名",fontChinese1));
			table2.addCell(" ");
			table2.addCell(new Phrase("所在部门",fontChinese1));
			table2.addCell(cell);
	        
			table2.addCell(new Phrase("联系人电话",fontChinese1));
			table2.addCell(" ");
			table2.addCell(new Phrase("电子信箱",fontChinese1));
			table2.addCell(cell);
			 // 设置垂直居中
	        cell.setVerticalAlignment(1);
	        // 设置水平居中
	        cell.setHorizontalAlignment(1);
			document.add(table2);
			document.add(new Paragraph(" "));
			Phrase phrase7 = new Phrase("二、被保险人资料",fontChinese2);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase7, 30, 550, 0);
			
			float[] widths3 = {0.18f,0.82f};
			PdfPTable table3 = new PdfPTable(widths3);
			table3.setTotalWidth(540);
			table3.setLockedWidth(true);
			table3.getDefaultCell().setMinimumHeight(16);
//			table3.addCell(table3.getDefaultCell());//添加空格
			table3.addCell(new Phrase("被保险人总数",fontChinese1));
			table3.addCell(new Phrase("                   人 (被保险人投保名单：_电子文件   _书面文件  _其它________)",fontChinese1));
			document.add(table3);
			document.add(new Paragraph(" "));
			Phrase phrase8 = new Phrase("三、投保内容",fontChinese2);
			Phrase phrase9 = new Phrase("(币值单位: 人民币元)",fontChinese1);
			Phrase phrase10 = new Phrase("保险条款是否变更: _是   _否",fontChinese1);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase8, 30, 515, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase9, 95, 515, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase10, 350, 515, 0);

			float[] widths4 = {0.18f,0.82f};
			PdfPTable table4 = new PdfPTable(widths4);
			table4.setTotalWidth(540);
			table4.setLockedWidth(true);
			table4.getDefaultCell().setMinimumHeight(16);
			cell = new PdfPCell(new Phrase("险种/保险责任名称",fontChinese2));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中 
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中

			cell.setRowspan(3);
			table4.addCell(cell);
			table4.addCell(new Phrase("",fontChinese2));
			table4.addCell(new Phrase("",fontChinese2));
			table4.addCell(new Phrase("",fontChinese2));
			document.add(table4);
			
			float[] widths5 = {0.18f,0.12f,0.16f,0.15f,0.39f};
			PdfPTable table5 = new PdfPTable(widths5);
			table5.setTotalWidth(540);
			table5.setLockedWidth(true);
			table5.getDefaultCell().setMinimumHeight(16);
			cell = new PdfPCell(new Phrase("首期保险费",fontChinese2));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中 
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
			cell.setRowspan(6);
			cell.setMinimumHeight(96);
			table5.addCell(cell);
			
			cell = new PdfPCell(new Phrase("保险费合计",fontChinese1));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中 
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
			cell.setRowspan(2);
			cell.setMinimumHeight(32);
			table5.addCell(cell);
			
			cell = new PdfPCell(new Phrase("(大写)	      亿      仟      佰      拾     万     仟     佰      拾     元     角    分",fontChinese1));
			cell.setColspan(3);
			cell.setMinimumHeight(16);
			table5.addCell(cell);
			
			cell = new PdfPCell(new Phrase("￥:",fontChinese1));
			cell.setColspan(3);
			cell.setMinimumHeight(16);
			table5.addCell(cell);
			
			cell = new PdfPCell(new Phrase("账户型险种填写",fontChinese1));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中 
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
			cell.setRowspan(4);
			cell.setMinimumHeight(64);
			table5.addCell(cell);
			
			cell = new PdfPCell(new Phrase("计入公共账户金额：￥:",fontChinese1));
			cell.setColspan(3);
			cell.setMinimumHeight(16);
			table5.addCell(cell);
			
			cell = new PdfPCell(new Phrase("计入个人账户金额：￥:",fontChinese1));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中 
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
			cell.setRowspan(3);
			cell.setMinimumHeight(48);
			table5.addCell(cell);
			
			cell = new PdfPCell(new Phrase("单位交费：￥:",fontChinese1));
			cell.setColspan(2);
			cell.setMinimumHeight(16);
			table5.addCell(cell);
			
			cell = new PdfPCell(new Phrase("个人交费：￥:",fontChinese1));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中 
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
			cell.setRowspan(2);
			cell.setMinimumHeight(32);
			table5.addCell(cell);
			
			cell = new PdfPCell(new Phrase("单位代扣代交：￥:",fontChinese1));
			cell.setMinimumHeight(16);
			table5.addCell(cell);
			
			cell = new PdfPCell(new Phrase("个人直接交费：￥:",fontChinese1));
			cell.setMinimumHeight(16);
			table5.addCell(cell);
			
			document.add(table5);
			
			float[] widths6 = {0.18f,0.28f,0.54f};
			PdfPTable table6 = new PdfPTable(widths6);
			table6.setTotalWidth(540);
			table6.setLockedWidth(true);
			table6.getDefaultCell().setMinimumHeight(16);
			cell = new PdfPCell(new Phrase("相关费用",fontChinese2));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中 
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
			cell.setRowspan(3);
			cell.setMinimumHeight(48);
			table6.addCell(cell);
			
			cell = new PdfPCell(new Phrase("管理费比例      __________% ",fontChinese1));
			cell.setMinimumHeight(16);
			table6.addCell(cell);
			
			cell = new PdfPCell(new Phrase("首期管理费金额：￥ ____________",fontChinese1));
			cell.setMinimumHeight(16);
			table6.addCell(cell);
			
			cell = new PdfPCell(new Phrase("初始费用比例      __________% ",fontChinese1));
			cell.setMinimumHeight(16);
			table6.addCell(cell);
			
			cell = new PdfPCell(new Phrase("首期初始费用金额：￥ ____________",fontChinese1));
			cell.setMinimumHeight(16);
			table6.addCell(cell);
			
			cell = new PdfPCell(new Phrase("其他费用约定",fontChinese1));
			cell.setColspan(2);
			cell.setMinimumHeight(16);
			table6.addCell(cell);
			
			document.add(table6);
			
			float[] widths7 = {0.18f,0.82f};
			PdfPTable table7 = new PdfPTable(widths7);
			table7.setTotalWidth(540);
			table7.setLockedWidth(true);
			table7.getDefaultCell().setMinimumHeight(16);
			table7.addCell(new Phrase("交费方式",fontChinese2));
			table7.addCell(new Phrase("_一次交清     _年交  _限期 ____年交          _不定期、不定额       _其它_____",fontChinese1));
			table7.addCell(new Phrase(" ",fontChinese1));
			document.add(table7);

			float[] widths8 = {1.0f};
			PdfPTable table8 = new PdfPTable(widths8);
			table8.setTotalWidth(540);
			table8.setLockedWidth(true);
			table8.getDefaultCell().setMinimumHeight(32);
			table8.addCell(new Phrase("首期/趸交支付方式:  _银行转账    _银行代收    _现金    _支票    _POS机    _网银    _其它____________   续期保费支付方式:  _银行转账   _其它   本栏如选择银行转账，请您填写下栏，本公司将从以下授权账号划转保险费",fontChinese1));
			document.add(table8);

			float[] widths9 = {0.52f,0.48f};
			PdfPTable table9 = new PdfPTable(widths9);
			table9.setTotalWidth(540);
			table9.setLockedWidth(true);
			table9.getDefaultCell().setMinimumHeight(16);
			table9.addCell(new Phrase("投保人户名:         授权银行:                授权账号:",fontChinese1));
			table9.addCell(new Phrase(" ",fontChinese1));
			document.add(table9);
			
			float[] widths10 = {0.18f,0.06f,0.12f,0.64f};
			PdfPTable table10 = new PdfPTable(widths10);
			table10.setTotalWidth(540);
			table10.setLockedWidth(true);
			table10.getDefaultCell().setMinimumHeight(16);
			cell = new PdfPCell(new Phrase("年金领取",fontChinese2));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中 
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
			cell.setRowspan(5);
			cell.setMinimumHeight(90);
			table10.addCell(cell);
			
			cell = new PdfPCell(new Phrase("年金领取年龄    _男___岁   女___岁       _在被保险人名单上注册",fontChinese1));
			cell.setColspan(3);
			cell.setMinimumHeight(16);
			table10.addCell(cell);
			
			cell = new PdfPCell(new Phrase("年金领取方式",fontChinese1));
			cell.setRowspan(4);
			cell.setMinimumHeight(64);
			table10.addCell(cell);
			
			cell = new PdfPCell(new Phrase("_即期     _延期                  _其它_______  _在被保险人名单上注明",fontChinese1));
			cell.setColspan(2);
			cell.setMinimumHeight(16);
			table10.addCell(cell);
			
			cell = new PdfPCell(new Phrase("_一次性领取     _年领    _月领                _其它_______  _在被保险人名单上注明",fontChinese1));
			cell.setColspan(2);
			cell.setMinimumHeight(16);
			table10.addCell(cell);
			
			cell = new PdfPCell(new Phrase("年领或月领选择",fontChinese1));
			cell.setRowspan(2);
			cell.setMinimumHeight(32);
			table10.addCell(cell);
			
			cell = new PdfPCell(new Phrase("_普通终身领取                    _保证十年终身领取                          _其它___________     _在被保险人名单上注明",fontChinese1));
			cell.setRowspan(2);
			cell.setMinimumHeight(32);
			table10.addCell(cell);
			
			document.add(table10);
			
			float[] widths11 = {0.18f,0.82f};
			PdfPTable table11 = new PdfPTable(widths11);
			table11.setTotalWidth(540);
			table11.setLockedWidth(true);
			table11.getDefaultCell().setMinimumHeight(16);
			cell = new PdfPCell(new Phrase("红利分配",fontChinese2));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中 
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
			cell.setRowspan(4);
			cell.setMinimumHeight(64);
			table11.addCell(cell);
			
			cell = new PdfPCell(new Phrase("分红型险种填写：                                                                                      公共账户和个人账户“单位交费部分”产生的红利，选择下列方式处理                                                                                _按照红利来源分别记入公共账户和个人账户中的单位交费部分                                                      _全部记入公共账户                                                                                        _以转账的方式支付给投保人",fontChinese1));
			cell.setRowspan(4);
			cell.setMinimumHeight(64);
			table11.addCell(cell);
			
			document.add(table11);
			
			Phrase phrase11 = new Phrase("第 1 页   共2页",fontChinese2);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase11, 250, 25, 0);			
			
			document.newPage();
			
//			document.add(new Paragraph(" "));
			
			Phrase phrase12 = new Phrase("四、保险责任 (医疗型险种填写)",fontChinese2);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase12, 30, 820, 0);
			float[] widths12 = {0.3f,0.35f,0.35f};
			PdfPTable table12 = new PdfPTable(widths12);
			table12.setTotalWidth(540);
			table12.setLockedWidth(true);
			table12.getDefaultCell().setMinimumHeight(10);
			
			cell = new PdfPCell(new Phrase("保险责任",fontChinese2));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中 
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
			cell.setMinimumHeight(16);
			table12.addCell(cell);
			
			cell = new PdfPCell(new Phrase("免赔额",fontChinese2));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中 
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
			cell.setMinimumHeight(16);
			table12.addCell(cell);
			cell = new PdfPCell(new Phrase("赔付比例",fontChinese2));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中 
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
			cell.setMinimumHeight(16);
			table12.addCell(cell);
			cell = new PdfPCell(new Phrase("门（急）诊医疗保障",fontChinese2));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中 
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
			cell.setMinimumHeight(16);
			table12.addCell(cell);
			cell = new PdfPCell(new Phrase("",fontChinese2));
			cell.setMinimumHeight(16);
			table12.addCell(cell);
			cell = new PdfPCell(new Phrase("",fontChinese2));
			cell.setMinimumHeight(16);
			table12.addCell(cell);
			cell = new PdfPCell(new Phrase("住院医疗保障",fontChinese2));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中 
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
			cell.setMinimumHeight(16);
			table12.addCell(cell);
			cell = new PdfPCell(new Phrase("",fontChinese2));
			cell.setMinimumHeight(16);
			table12.addCell(cell);
			cell = new PdfPCell(new Phrase("",fontChinese2));
			cell.setMinimumHeight(16);
			table12.addCell(cell);
			cell = new PdfPCell(new Phrase("体检保障",fontChinese2));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中 
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
			cell.setMinimumHeight(16);
			table12.addCell(cell);
			cell = new PdfPCell(new Phrase("",fontChinese2));
			cell.setMinimumHeight(16);
			table12.addCell(cell);
			cell = new PdfPCell(new Phrase("",fontChinese2));
			cell.setMinimumHeight(16);
			table12.addCell(cell);
			document.add(table12);
			
			document.add(new Paragraph(" "));
			
			Phrase phrase13 = new Phrase("五、特别约定 (若有特别约定，请在下栏内注明：否则，请注明“无”)",fontChinese2);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase13, 30, 732, 0);
			float[] widths13 = {1.0f};
			PdfPTable table13 = new PdfPTable(widths13);
			table13.setTotalWidth(540);
			table13.setLockedWidth(true);
			table13.getDefaultCell().setMinimumHeight(60);
			table13.addCell(new Paragraph(" ",fontChinese1));
			document.add(table13);

			document.add(new Paragraph(" "));
			
			Phrase phrase14 = new Phrase("六、健康告知", fontChinese2); 
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase14, 30, 650, 0);
			
			float[] widths14 = {0.5f,0.4f,0.1f};
			PdfPTable table14 = new PdfPTable(widths14);
			table14.setTotalWidth(540);
			table14.setLockedWidth(true);
			table14.getDefaultCell().setMinimumHeight(12);
			
			cell = new PdfPCell(new Phrase("请告知被保险人以下各项:",fontChinese2));
			cell.setColspan(3);	
			cell.setMinimumHeight(12);
			table14.addCell(cell);
			
			cell = new PdfPCell(new Phrase("1.请告知是否拥有公费医疗、社会医疗保险和其它医疗费用补偿型保险？",fontChinese1));
			cell.setColspan(2);	
			cell.setMinimumHeight(12);
			table14.addCell(cell);
			table14.addCell(new Phrase("_是  _否",fontChinese1));
			
			cell = new PdfPCell(new Phrase("2.现在是否有患病住院者？",fontChinese1));
			cell.setColspan(2);
			cell.setMinimumHeight(12);
			table14.addCell(cell);
			table14.addCell(new Phrase("_是  _否",fontChinese1));

			cell = new PdfPCell(new Phrase("3.现在或过去是否有下列严重疾病者: 心脏病、高血压、脑中风、癫痫、恶性肿瘤 (含白血病)、慢性肝炎、肝硬化、尿毒症、糖尿病、精神疾病、器官移植、残疾、植物人等",fontChinese1));
			cell.setColspan(2);	
			cell.setRowspan(2);
			cell.setMinimumHeight(24);
			table14.addCell(cell);
			cell = new PdfPCell(new Phrase("_是  _否",fontChinese1));
			cell.setColspan(2);	
			cell.setRowspan(2);
			table14.addCell(cell);
				
			cell = new PdfPCell(new Phrase("4.近一年内是否有因病不在工作岗位累计超过10天者？",fontChinese1));
			cell.setColspan(2);	
			cell.setMinimumHeight(12);
			table14.addCell(cell);
			cell = new PdfPCell(new Phrase("_是  _否",fontChinese1));
			table14.addCell(cell);

			cell = new PdfPCell(new Phrase("5.是否有残疾人员参加本次投保？",fontChinese1));
			cell.setColspan(2);	
			cell.setMinimumHeight(12);
			table14.addCell(cell);
			cell = new PdfPCell(new Phrase("_是  _否",fontChinese1));
			table14.addCell(cell);
			
			
		
			cell = new PdfPCell(new Phrase("若上述2-5项告知为“是”，请被保险人本人填写《被保险人告知书 (团险)》。",fontChinese2));
			cell.setMinimumHeight(12);
			cell.setColspan(3);
			table14.addCell(cell);
						
			document.add(table14);
			document.add(new Paragraph(" "));
			Phrase phrase15 = new Phrase("七、备注栏", fontChinese2); 
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase15, 30, 524, 0);
			
			float[] widths15 = {1.0f};
			PdfPTable table15 = new PdfPTable(widths15);
			table15.setTotalWidth(540);
			table15.setLockedWidth(true);
			table15.getDefaultCell().setMinimumHeight(48);
			table15.addCell(new Paragraph(" ",fontChinese1));
			document.add(table15);
			
			document.add(new Paragraph(" "));
			
			Phrase phrase16 = new Phrase("八、投保人声明和授权", fontChinese2); 
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase16, 30, 459, 0);
			
			Phrase phrase17 = new Phrase("1. 本人已收到中国人民人寿保险股份有限公司（以下简称“人保寿险”）提供的保险条款，销售人员已向本人说明保险合同内容，并就保险责任、责任免除条款（包括除外责任条款、免赔额、免赔率、等待期、比例赔付、解除或中止合同等部分或全部免除或限制保险人责任的条款）、投保提示、特别约定及相关释义等内容进行了单独说明。本人已认真阅读上述条款，并已经知晓了所有单独说明部分的概念、内容及其法律后果。本人已经知晓其他任何人对保险条款、投保单等内容做出的与之相违背或增减的口头说明及书面陈述均为无效，一切均以保险合同载明为准。                                                                             "+
"2.  本次投保内容均已告知并经被保险人或其监护人同意，其受益人均由被保险人指定，本投保单填写的各项内容真实，如有隐瞒或不实告知，人保寿险可依法解除保险合同，并对保险合同解除前发生的保险事故不承担保险责任。                                                                          "+
"3.  本人已了解如果同意接受人保寿险签发的保险单，则本人签署的任何文件均视为已认可人保寿险对投保单的附加和更改。                                                                                                                                 "+
"4.  本人同意人保寿险向任何机构、组织和个人就本人任何有关保险事宜、被保险人健康状况及其它情况索取、查询有关资料和证明，并授权人保寿险或    任何与人保寿险业务有关的机构或个人用于：1）处理及审核投保单及其它保险事宜；2）提供与该保险有关的服务；3）与本人联络。此授权书的影印本也同样有效。                                                                          "+
"5.  本人已了解人保寿险对留存于公司内本人全部健康、财务及其它资料将承担保密义务，除本声明第4条列明的情况外不作它用。                                                                                                            "+
"6.  对于所投保的分红保险，万能保险和投资连接保险等产品，本人已阅读保险条款和产品说明书，了解产品的特点和保单利益的不确定性。                                                                                                            "+
"7.  如本次投保计划含健康保险产品，本人已认真阅读并理解了团体健康保险投保提示。                                                                                                       "+
"8.  本人知晓即使本人已预缴保险费，本保险合同仍未生效，本保险合同的生效以保险合同成立、本人缴付保险费且人保寿险签发保险单为条件，本保险合同的生效日期以保险单上载明的日期为准。", fontChinese1); 
								
			
			float[] widths16 = {1.0f};
			PdfPTable table16 = new PdfPTable(widths16);
			table16.setTotalWidth(540);
			table16.setLockedWidth(true);
			table16.addCell(phrase17);
			document.add(table16);	
			document.add(new Paragraph(" "));
			
			Phrase phrase18 = new Phrase("若投保分红、万能和投资连结保险产品，请在下栏内亲笔注明《投保人声明和授权》中第6条的内容:", fontChinese2); 
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase18, 30,248, 0);
			
			Phrase phrase19 = new Phrase("投保人声明：对于所投保的分红保险、万能保险和投资连接保险产品，本人____________________                                                                             "+
					"_____________________________________________________________________________________", fontChinese1); 
													
								
			float[] widths17 = {1.0f};
			PdfPTable table17 = new PdfPTable(widths15);
			table17.setTotalWidth(540);
			table17.setLockedWidth(true);
			table17.addCell(phrase19);
			document.add(table17);	
			document.add(new Paragraph(" "));
			document.add(new Paragraph(" "));
			document.add(new Paragraph(" "));
			Phrase phrase20 = new Phrase("请投保人确认本投保单中所有内容真实、准确、完整，且已充分了解声明和授权后签名盖章", fontChinese2); 
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase20, 30, 204, 0);
			
			Phrase phrase21 = new Phrase("投保人/投保单位负责人/法定代表人签名:                     投保人盖章:", fontChinese2); 
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase21, 30, 193, 0);
			
			Phrase phrase22 = new Phrase("投保日期:             年          月        日", fontChinese2); 
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase22, 30, 182, 0);
			
			Phrase phrase23 = new Phrase("九、以下内容有保险公司填写", fontChinese2); 
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase23, 30, 167, 0);
			
			float[] widths18 = {0.15f,0.25f,0.6f};
			PdfPTable table18 = new PdfPTable(widths18);
			table18.setTotalWidth(540);
			table18.setLockedWidth(true);
			table18.getDefaultCell().setMinimumHeight(12);
			
			cell = new PdfPCell(new Phrase("保险公司",fontChinese1));
			cell.setRowspan(2);
			cell.setMinimumHeight(24);
			table18.addCell(cell);
			
			cell = new PdfPCell(new Phrase("销售渠道:          营业单位:                 代码:        ",fontChinese1));
			cell.setColspan(2);
			cell.setMinimumHeight(12);
			table18.addCell(cell);
			
			cell = new PdfPCell(new Phrase("人保寿险销售人员:     代码:      其它销售人员:    代码:        ",fontChinese1));
			cell.setColspan(2);
			cell.setMinimumHeight(12);
			table18.addCell(cell);

			cell = new PdfPCell(new Phrase("中介机构",fontChinese1));
			cell.setMinimumHeight(12);
			table18.addCell(cell);
			
			cell = new PdfPCell(new Phrase("机构名称:     代码:      经办人:    代码:        ",fontChinese1));
			cell.setColspan(2);
			cell.setMinimumHeight(12);
			table18.addCell(cell);
			
			cell = new PdfPCell(new Phrase("统括业务",fontChinese1));
			cell.setMinimumHeight(12);
			table18.addCell(cell);
			
			cell = new PdfPCell(new Phrase("_是  _否",fontChinese1));
			cell.setMinimumHeight(12);
			table18.addCell(cell);
			
			cell = new PdfPCell(new Phrase("统括业务项目编号:",fontChinese1));
			cell.setMinimumHeight(12);
			table18.addCell(cell);
			
			cell = new PdfPCell(new Phrase("保费分割",fontChinese1));
			cell.setMinimumHeight(12);
			table18.addCell(cell);
			
			cell = new PdfPCell(new Phrase("_是  _否           若选择为 “是”时，需在“其它信息”栏详述业务分割类别及比例。",fontChinese1));
			cell.setColspan(2);
			table18.addCell(cell);
			
			cell = new PdfPCell(new Phrase("其他信息:",fontChinese1));
			cell.setColspan(3);
			cell.setRowspan(2);
			cell.setMinimumHeight(25);
			table18.addCell(cell);
			document.add(table14);	
		
			Phrase phrase24 = new Phrase("客户服务电话:400-8895518   公司网址:WWW.picclife.com   公司电子商务网址:WWW.e-picclife.com", fontChinese2); 
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase24, 30, 30, 0);
			
			Phrase phrase25 = new Phrase("第 2 页   共2页", fontChinese2); 
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase25, 250, 15, 0);
						
			//Step 5—Close the Document.  
			document.close();  
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
