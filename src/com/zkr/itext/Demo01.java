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



public class Demo01 {	
	public static void main(String[] args){
		System.out.println("My First PdfTable");         
		try {
			//Step 1—Create a Document.  
			Document document = new Document(PageSize.A4); 
			//Step 2—Get a PdfWriter instance.  
			PdfWriter pdfWrite = PdfWriter.getInstance(document, new FileOutputStream("createSamplePDF01.pdf"));
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
			Phrase phrase3 = new Phrase("中国人民人寿保险股份有限公司团体保险保单 (A款)",fontChinese);
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

			float[] widths4 = {0.38f,0.10f,0.15f,0.20f,0.17f};
			PdfPTable table4 = new PdfPTable(widths4);
			table4.setTotalWidth(540);
			table4.setLockedWidth(true);
			table4.getDefaultCell().setMinimumHeight(16);
			table4.addCell(new Phrase("险种/保险责任名称",fontChinese2));
			table4.addCell(new Phrase("保险期间",fontChinese2));
			table4.addCell(new Phrase("被保险人人数",fontChinese2));
			table4.addCell(new Phrase("(基本)保险金额总额",fontChinese2));
			table4.addCell(new Phrase("保险费",fontChinese2));
			for (int i = 0; i < 7; i++) {
				table4.addCell(new Phrase(" ",fontChinese1));
				table4.addCell(new Phrase(" ",fontChinese1));
				table4.addCell(new Phrase(" ",fontChinese1));
				table4.addCell(new Phrase(" ",fontChinese1));
				table4.addCell(new Phrase(" ",fontChinese1));
			}			
			document.add(table4);
			
			float[] widths5 = {0.13f,0.87f};
			PdfPTable table5 = new PdfPTable(widths5);
			table5.setTotalWidth(540);
			table5.setLockedWidth(true);
			table5.getDefaultCell().setMinimumHeight(16);
			cell = new PdfPCell(new Phrase("保险费合计",fontChinese2));
			cell.setRowspan(2);
			table5.addCell(cell);
			table5.addCell(new Phrase("(大写)	      亿      仟      佰      拾     万     仟     佰      拾     元     角    分",fontChinese2));
			table5.addCell(new Phrase("￥:",fontChinese2));
			document.add(table5);
			
			float[] widths6 = {0.13f,0.35f,0.15f,0.37f};
			PdfPTable table6 = new PdfPTable(widths6);
			table6.setTotalWidth(540);
			table6.setLockedWidth(true);
			table6.getDefaultCell().setMinimumHeight(16);
			table6.addCell(new Phrase("交费方式",fontChinese2));
			table6.addCell(new Phrase("_一次交清     _年交   _其它_____",fontChinese1));
			table6.addCell(new Phrase("交费期间",fontChinese1));
			table6.addCell(new Phrase(" ",fontChinese1));
			document.add(table6);

			float[] widths7 = {1.0f};
			PdfPTable table7 = new PdfPTable(widths7);
			table7.setTotalWidth(540);
			table7.setLockedWidth(true);
			table7.getDefaultCell().setMinimumHeight(32);
			//cell = new PdfPCell(new Phrase("首期/趸交支付方式:  _银行转账    _银行代收    _现金    _支票    _POS机    _网银    _其它____________",fontChinese1));
			//cell2 = new PdfPCell(new Phrase("续期保费支付方式:  _银行转账   _其它   本栏如选择银行转账，请您填写下栏，本公司将从以下授权账号划转保险费",fontChinese1));						
			//table7.getDefaultCell().setBorder(0);
			table7.addCell(new Phrase("首期/趸交支付方式:  _银行转账    _银行代收    _现金    _支票    _POS机    _网银    _其它____________   续期保费支付方式:  _银行转账   _其它   本栏如选择银行转账，请您填写下栏，本公司将从以下授权账号划转保险费",fontChinese1));
//			table7.addCell(cell);
//			table7.addCell(cell2);
			document.add(table7);

//			float[] widths8 = {0.516f,0.022f,0.022f,0.022f,0.022f,0.022f,0.022f,0.022f,0.022f,0.022f,0.022f,0.022f,0.022f,0.022f,0.022f,0.022f,0.022f,0.022f,0.022f,0.022f,0.022f,0.022f,0.022f};
//			float[] widths8 = {0.52f,0.48f};
			float[] widths8 = {0.52f,0.48f};
			PdfPTable table8 = new PdfPTable(widths8);
			table8.setTotalWidth(540);
			table8.setLockedWidth(true);
			table8.getDefaultCell().setMinimumHeight(16);
			table8.addCell(new Phrase("投保人户名:         授权银行:                授权账号:",fontChinese1));
			table8.addCell(new Phrase(" ",fontChinese1));
			document.add(table8);
			
			document.add(new Paragraph(" "));
			Phrase phrase11 = new Phrase("四、特别约定 (若有特别约定，请在下栏内注明：否则，请注明“无”)",fontChinese2);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase11, 30, 275, 0);
			float[] widths9 = {1.0f};
			PdfPTable table9 = new PdfPTable(widths9);
			table9.setTotalWidth(540);
			table9.setLockedWidth(true);
			table9.getDefaultCell().setMinimumHeight(96);
			table9.addCell(new Paragraph(" ",fontChinese1));
			document.add(table9);

			document.add(new Paragraph(" "));
			Phrase phrase12 = new Phrase("五、基本告知",fontChinese2);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase12, 30, 160, 0);
			float[] widths10 = {0.15f,0.15f,0.15f,0.2f,0.2f,0.15f};
			PdfPTable table10 = new PdfPTable(widths10);
			table10.setTotalWidth(540);
			table10.setLockedWidth(true);
			table10.getDefaultCell().setMinimumHeight(16);
			
			table10.addCell(new Paragraph("平均年龄:   ",fontChinese1));
			table10.addCell(new Paragraph("最高年龄:   ",fontChinese1));
			table10.addCell(new Paragraph("最低年龄:   ",fontChinese1));
			table10.addCell(new Paragraph("退休人数占比:   ",fontChinese1));
			table10.addCell(new Paragraph("附带被保险人人数:   ",fontChinese1));
			table10.addCell(new Paragraph("外籍人员人数:   ",fontChinese1));

			cell = new PdfPCell(new Phrase("本次投保的保险金额或保险费分配依据: _平均   _年收入   _职位   _职业类别   _综合多种因素 (请说明)                           ",fontChinese1));
			cell.setColspan(6);
			cell.setRowspan(2);
			cell.setMinimumHeight(32);
			table10.addCell(cell);
			
			cell = new PdfPCell(new Phrase("被保险人 (包括附带被保险人) 一年内是否需要前往中国大陆以外的地区或国家？                       _是   _否               如果是请列出需要前往的地区或国家:       ",fontChinese1));
			cell.setColspan(6);
			cell.setRowspan(2);
			cell.setMinimumHeight(32);
			table10.addCell(cell);
			
			cell = new PdfPCell(new Phrase("过去三年是否曾投保商业保险 (如果是请列储投保公司、险种、保费及赔付情况) :                     _是   _否",fontChinese1));
			cell.setColspan(6);
			cell.setRowspan(2);
			cell.setMinimumHeight(32);
			table10.addCell(cell);
			
			Phrase phrase13 = new Phrase("第 1 页   共2页",fontChinese2);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase13, 250, 25, 0);			
			document.add(table10);
			
			document.newPage();
			
			Phrase phrase18 = new Phrase("六、健康告知", fontChinese2); 
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase18, 30, 815, 0);
			
			//document.add(new Phrase("六、健康告知",fontChinese2));
			float[] widths11 = {0.5f,0.4f,0.1f};
			PdfPTable table11 = new PdfPTable(widths11);
			table11.setTotalWidth(540);
			table11.setLockedWidth(true);
			table11.getDefaultCell().setMinimumHeight(16);
			
			cell = new PdfPCell(new Phrase(" (一) 请告知被保险人及附带被保险人以下各项:",fontChinese2));
			cell.setColspan(3);	
			cell.setMinimumHeight(16);
			table11.addCell(cell);
			
			cell = new PdfPCell(new Phrase("1.请告知是否拥有公费医疗、社会医疗保险和其它医疗费用补偿型保险？",fontChinese1));
			cell.setColspan(2);	
			cell.setMinimumHeight(16);
			table11.addCell(cell);
			table11.addCell(new Phrase("_是  _否",fontChinese1));
			
			cell = new PdfPCell(new Phrase("2.现在是否有患病住院者？",fontChinese1));
			cell.setColspan(2);
			cell.setMinimumHeight(16);
			table11.addCell(cell);
			table11.addCell(new Phrase("_是  _否",fontChinese1));

			cell = new PdfPCell(new Phrase("3.现在或过去是否有下列严重疾病者: 心脏病、高血压、脑中风、癫痫、恶性肿瘤 (含白血病)、慢性肝炎、肝硬化、尿毒症、糖尿病、精神疾病、器官移植、残疾、植物人等",fontChinese1));
			cell.setColspan(2);	
			cell.setRowspan(2);
			cell.setMinimumHeight(32);
			table11.addCell(cell);
			cell = new PdfPCell(new Phrase("_是  _否",fontChinese1));
			cell.setColspan(2);	
			cell.setRowspan(2);
			table11.addCell(cell);
				
			cell = new PdfPCell(new Phrase("4.近一年内是否有因病不在工作岗位累计超过10天者？",fontChinese1));
			cell.setColspan(2);	
			cell.setMinimumHeight(16);
			table11.addCell(cell);
			cell = new PdfPCell(new Phrase("_是  _否",fontChinese1));
			table11.addCell(cell);

			cell = new PdfPCell(new Phrase("5.是否有残疾人员参加本次投保？",fontChinese1));
			cell.setColspan(2);	
			cell.setMinimumHeight(16);
			table11.addCell(cell);
			cell = new PdfPCell(new Phrase("_是  _否",fontChinese1));
			table11.addCell(cell);
			
			cell = new PdfPCell(new Phrase(" (二) 投保健康险，除以上告知外，请告知被保险人及附件带被保险人过去5年内是否有以下疾病:",fontChinese2));
			cell.setColspan(3);	
			cell.setMinimumHeight(16);
			table11.addCell(cell);
			
			cell = new PdfPCell(new Phrase("6.脑梗塞、脑出血、癫痫、精神分裂症或其它神经系统疾病和精神疾病                                  _是  _否",fontChinese1));
			cell.setRowspan(2);
			cell.setMinimumHeight(25);
			table11.addCell(cell);
			cell = new PdfPCell(new Phrase("7.高血压、冠心病、心律失常、心肌梗塞、心力衰竭或其它循环系统疾病                                _是  _否",fontChinese1));
			cell.setColspan(2);	
			cell.setRowspan(2);	
			table11.addCell(cell);
			
			cell = new PdfPCell(new Phrase("8.慢性支气管炎、肺气肿、呼吸衰竭或其它呼吸系统疾病                                                                    _是  _否",fontChinese1));
			cell.setRowspan(2);
			cell.setMinimumHeight(25);
			table11.addCell(cell);
			cell = new PdfPCell(new Phrase("9.肝炎、肝硬化、胆石症、胰腺炎、消化性溃疡或其它消化系统疾病                               _是  _否",fontChinese1));
			cell.setColspan(2);	
			cell.setRowspan(2);	
			cell.setMinimumHeight(25);
			table11.addCell(cell);
			
			cell = new PdfPCell(new Phrase("10.肾炎、尿毒症等慢性肾脏疾病、尿路结石或其它泌尿生殖系统疾病                            _是  _否",fontChinese1));
			cell.setRowspan(2);
			cell.setMinimumHeight(25);
			table11.addCell(cell);
			cell = new PdfPCell(new Phrase("11.糖尿病、甲状腺技能亢进或低下或其它内分泌系统疾病                                                _是  _否",fontChinese1));
			cell.setColspan(2);	
			cell.setRowspan(2);	
			cell.setMinimumHeight(25);
			table11.addCell(cell);
			
			cell = new PdfPCell(new Phrase("12.白血病、再生障碍性贫血、淋巴瘤或其它血液系统疾病                                                   _是  _否",fontChinese1));
			cell.setRowspan(2);
			cell.setMinimumHeight(25);
			table11.addCell(cell);
			cell = new PdfPCell(new Phrase("13.系统性红斑狼疮、类风湿性关节炎、强直性脊柱炎或其它风湿性疾病                           _是  _否",fontChinese1));
			cell.setColspan(2);	
			cell.setRowspan(2);	
			cell.setMinimumHeight(25);
			table11.addCell(cell);
			
			cell = new PdfPCell(new Phrase("14.椎间盘突出症、骨关节病、骨髓炎或其它骨髓系统疾病                                                   _是  _否",fontChinese1));
			cell.setRowspan(2);
			cell.setMinimumHeight(25);
			table11.addCell(cell);
			cell = new PdfPCell(new Phrase("15.白内障、青光眼、视网膜或视神经病变等疾病                                                                    _是  _否",fontChinese1));
			cell.setColspan(2);	
			cell.setRowspan(2);	
			cell.setMinimumHeight(25);
			table11.addCell(cell);
			
			cell = new PdfPCell(new Phrase("16.癌症、肿瘤 (恶性、良性或尚未证实为良性或恶性) 、息肉 、囊肿等疾病                 _是  _否",fontChinese1));
			cell.setRowspan(2);
			cell.setMinimumHeight(25);
			table11.addCell(cell);
			cell = new PdfPCell(new Phrase("17.先天性疾病、职业病、酒精或药物滥用成瘾                                                                    _是  _否",fontChinese1));
			cell.setColspan(2);	
			cell.setRowspan(2);	
			cell.setMinimumHeight(25);
			table11.addCell(cell);
			
			cell = new PdfPCell(new Phrase("18.乳腺疾病、子宫肌瘤、卵巢囊肿或其它妇科疾病                 _是  _否",fontChinese1));
			cell.setMinimumHeight(25);
			table11.addCell(cell);
			cell = new PdfPCell(new Phrase("19.上述未提及的疾病                                                                    _是  _否",fontChinese1));
			cell.setMinimumHeight(25);
			cell.setColspan(2);	
			table11.addCell(cell);
		
			cell = new PdfPCell(new Phrase("若上述2-19项告知为“是”，请被保险人或附带被保险人本人(未成年人由其监护人)填写《被保险人通知书 (团险)》。",fontChinese2));
			cell.setMinimumHeight(16);
			cell.setColspan(3);
			table11.addCell(cell);
						
			document.add(table11);
			document.add(new Paragraph(" "));
			Phrase phrase19 = new Phrase("七、备注栏", fontChinese2); 
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase19, 30, 475, 0);
			
			//document.add(new Phrase("七、备注栏",fontChinese2));
			float[] widths12 = {1.0f};
			PdfPTable table12 = new PdfPTable(widths12);
			table12.setTotalWidth(540);
			table12.setLockedWidth(true);
			table12.getDefaultCell().setMinimumHeight(48);
			table12.addCell(new Paragraph(" ",fontChinese1));
			document.add(table12);
			
			document.add(new Paragraph(" "));
			Phrase phrase20 = new Phrase("八、投保人声明和授权", fontChinese2); 
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase20, 30, 410, 0);
			
			//document.add(new Phrase("八、投保人声明和授权",fontChinese2));
			Phrase phrase14 = new Phrase("1. 本人已收到中国人民人寿保险股份有限公司（以下简称“人保寿险”）提供的保险条款，销售人员已向本人说明保险合同内容，并就保险责任、责任免除条款（包括除外责任条款、免赔额、免赔率、等待期、比例赔付、解除或中止合同等部分或全部免除或限制保险人责任的条款）、投保提示、特别约定及相关释义等内容进行了单独说明。本人已认真阅读上述条款，并已经知晓了所有单独说明部分的概念、内容及其法律后果。本人已经知晓其他任何人对保险条款、投保单等内容做出的与之相违背或增减的口头说明及书面陈述均为无效，一切均以保险合同载明为准。                                                                             "+
"2.  本次投保内容均已告知并经被保险人或其监护人同意，其受益人均由被保险人指定，本投保单填写的各项内容真实，如有隐瞒或不实告知，人保寿险可依法解除保险合同，并对保险合同解除前发生的保险事故不承担保险责任。                                                                          "+
"3.  本人已了解如果同意接受人保寿险签发的保险单，则本人签署的任何文件均视为已认可人保寿险对投保单的附加和更改。                                                                                                                                 "+
"4.  本人同意人保寿险向任何机构、组织和个人就本人任何有关保险事宜、被保险人健康状况及其它情况索取、查询有关资料和证明，并授权人保寿险或    任何与人保寿险业务有关的机构或个人用于：1）处理及审核投保单及其它保险事宜；2）提供与该保险有关的服务；3）与本人联络。此授权书的影印本也同样有效。                                                                          "+
"5.  本人已了解人保寿险对留存于公司内本人全部健康、财务及其它资料将承担保密义务，除本声明第4条列明的情况外不作它用。                                                                                                            "+
"6.  如本次投保计划含健康保险产品，本人已认真阅读并理解了团体健康保险投保提示。                                                                                                       "+
"7.  本人知晓即使本人已预缴保险费，本保险合同仍未生效，本保险合同的生效以保险合同成立、本人缴付保险费且人保寿险签发保险单为条件，本保险合同的生效日期以保险单上载明的日期为准。", fontChinese1); 
								
			
			float[] widths13 = {1.0f};
			PdfPTable table13 = new PdfPTable(widths13);
			table13.setTotalWidth(540);
			table13.setLockedWidth(true);
			//table13.addCell(new Paragraph(" ",fontChinese1));
			table13.addCell(phrase14);
			document.add(table13);	
			document.add(new Paragraph(" "));
			document.add(new Paragraph(" "));
			document.add(new Paragraph(" "));
			document.add(new Paragraph(" "));
			Phrase phrase15 = new Phrase("请投保人确认本投保单中所有内容真实、准确、完整，且已充分了解声明和授权后签名盖章", fontChinese2); 
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase15, 30, 218, 0);
			
			Phrase phrase16 = new Phrase("投保人/投保单位负责人/法定代表人签名:", fontChinese2); 
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase16, 30, 205, 0);
			
			Phrase phrase17 = new Phrase("投保日期:             年          月        日", fontChinese2); 
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase17, 30, 190, 0);
			
			Phrase phrase21 = new Phrase("九、以下内容有保险公司填写", fontChinese2); 
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase21, 30, 175, 0);
			
			float[] widths14 = {0.15f,0.25f,0.6f};
			PdfPTable table14 = new PdfPTable(widths14);
			table14.setTotalWidth(540);
			table14.setLockedWidth(true);
			table14.getDefaultCell().setMinimumHeight(16);
			
			cell = new PdfPCell(new Phrase("保险公司",fontChinese1));
			cell.setRowspan(2);
			cell.setMinimumHeight(32);
			table14.addCell(cell);
			
			cell = new PdfPCell(new Phrase("销售渠道:          营业单位:                 代码:        ",fontChinese1));
			cell.setColspan(2);
			cell.setMinimumHeight(16);
			table14.addCell(cell);
			
			cell = new PdfPCell(new Phrase("人保寿险销售人员:     代码:      其它销售人员:    代码:        ",fontChinese1));
			cell.setColspan(2);
			cell.setMinimumHeight(16);
			table14.addCell(cell);

			cell = new PdfPCell(new Phrase("中介机构",fontChinese1));
			cell.setMinimumHeight(16);
			table14.addCell(cell);
			
			cell = new PdfPCell(new Phrase("机构名称:     代码:      经办人:    代码:        ",fontChinese1));
			cell.setColspan(2);
			cell.setMinimumHeight(16);
			table14.addCell(cell);
			
			cell = new PdfPCell(new Phrase("统括业务",fontChinese1));
			cell.setMinimumHeight(16);
			table14.addCell(cell);
			
			cell = new PdfPCell(new Phrase("_是  _否",fontChinese1));
			cell.setMinimumHeight(16);
			table14.addCell(cell);
			
			cell = new PdfPCell(new Phrase("统括业务项目编号:",fontChinese1));
			cell.setMinimumHeight(16);
			table14.addCell(cell);
			
			cell = new PdfPCell(new Phrase("保费分割",fontChinese1));
			cell.setMinimumHeight(16);
			table14.addCell(cell);
			
			cell = new PdfPCell(new Phrase("_是  _否           若选择为 “是”时，需在“其它信息”栏详述业务分割类别及比例。",fontChinese1));
			cell.setColspan(2);
			table14.addCell(cell);
			
			cell = new PdfPCell(new Phrase("_是  _否           若选择为 “是”时，需在“其它信息”栏详述业务分割类别及比例。",fontChinese1));
			cell.setColspan(3);
			cell.setRowspan(2);
			cell.setMinimumHeight(25);
			table14.addCell(cell);
			document.add(table14);	
		
			Phrase phrase22 = new Phrase("客户服务电话:400-8895518   公司网址:WWW.picclife.com   公司电子商务网址:WWW.e-picclife.com", fontChinese2); 
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase22, 30, 30, 0);
			
			Phrase phrase23 = new Phrase("第 2 页   共2页", fontChinese2); 
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase23, 250, 15, 0);
						
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
