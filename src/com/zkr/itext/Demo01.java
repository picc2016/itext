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
			//Step 1��Create a Document.  
			Document document = new Document(PageSize.A4); 
			//Step 2��Get a PdfWriter instance.  
			PdfWriter pdfWrite = PdfWriter.getInstance(document, new FileOutputStream("createSamplePDF01.pdf"));
			//Step 3��Open the Document.  
			document.open(); 
			BaseFont baseFont = null;
			Font fontChinese = null;
			BaseFont bfComic1 = null;
			Font fontChinese1 = null;
			Font fontChinese2 = null;
			Font fontChinese3 = null;//����
			BaseFont bfComic5 = null;//  ����
			Font bfComic5Chinese = null;
			Font bfComic5Chinese4 = null;

			try {
			     //   ������
				bfComic1 = BaseFont.createFont("c:\\windows\\fonts\\SIMFANG.TTF",BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
				  //����
				  baseFont = BaseFont.createFont("C:/Windows/Fonts/SIMKAI.ttf",BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			      //  ����
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
			//������
			Barcode128 code128 = new Barcode128();
			code128.setCode("* 000000000000000 *");
			code128.setCodeType(Barcode128.CODE128);
			Image code128Image = code128.createImageWithBarcode(canvas, null, null);
			code128Image.setAbsolutePosition(360, 790);
			code128Image.scalePercent(130);
			document.add(code128Image);
			
			
			
			Phrase phrase1 = new Phrase("PICC", FontFactory.getFont(FontFactory.HELVETICA, 20, new BaseColor(255, 0, 0))); 
			Phrase phrase2 = new Phrase("�й�������", bfComic5Chinese); 
			Phrase phrase3 = new Phrase("�й��������ٱ��չɷ����޹�˾���屣�ձ��� (A��)",fontChinese);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase1, 25, 795, 0);  
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase2, 75, 795, 0);  
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase3, 25, 775, 0);
			
			document.add(new Paragraph(" "));
			document.add(new Paragraph(" "));

			
			Phrase phrase4 = new Phrase(" Ͷ	  ��   ��   ֪", bfComic5Chinese4); 
			Phrase phrase5 = new Phrase("1����Ͷ�����Ƕ������պ�ͬ����Ҫ��ɲ��֣�����дͶ����֮ǰ�����������Ķ���Ͷ�����ֵı�������ڳ���������ı������Ρ������������ͬ��������ݺ�������Ͷ��������                                                                     "+
"2��Ͷ������Ͷ�����ڱ��չ�˾������Ա��ָ�����ú�ɫ������ɫīˮ����д������Ҫ��ʵ��׼ȷ���ּ�Ҫ����������������Ϳ�ģ�����Ͷ����/Ͷ����λ������/������������ǩ�������ױ�ǩ����                                                                                                    "+
"3�������ҹ������շ����涨�����չ�˾��Ͷ���˼��������˵��й������Ȩ����ѯ�ʣ�����ʵ��֪�����������ش��ʧδ��ʵ��֪���ҹ�˾��Ȩ������պ�ͬ�������ڱ��պ�ͬ���ǰ�����ı����¹ʲ��е��������Ρ����и�֪�����������Ϊ׼����ͷ��֪��Ч��                                                                           "+
"4�����պ�ͬ�������ҹ�˾��ȡ���ڱ��շѲ�ǩ�����յ�Ϊ��ͬ����Ч���������պ�ͬ��Ч�����Ա��յ���������Ϊ׼��", fontChinese1); 
			//����һ����2�еı��
			//PdfPTable iTable = new PdfPTable(2);
			float[] widths = {0.04f,0.96f};
			PdfPTable table = new PdfPTable(widths);
			//�����ұ߾���ϱ�
//			Rectangle r = new Rectangle(100, 200);
//			table.setWidthPercentage(widths, r);
			table.setTotalWidth(540);
			table.setLockedWidth(true);
			table.writeSelectedRows(0, -1, 10, 10, canvas);///////----------

			table.addCell(phrase4);
			table.addCell(phrase5);			
			document.add(table);
			//document.add(new Paragraph("һ��Ͷ��������",fontChinese));
			Phrase phrase6 = new Phrase("һ��Ͷ��������",fontChinese2);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase6, 30, 663, 0);  
			document.add(new Paragraph(" "));
			
			float[] widths2 = {0.18f,0.20f,0.10f,0.20f,0.15f,0.17f};
			PdfPTable table2 = new PdfPTable(widths2);
			table2.setTotalWidth(540);
			table2.setLockedWidth(true);
//		      // ����1Ϊ���ж��룬2Ϊ�Ҷ��룬3Ϊ�����
//	        table2.setHorizontalAlignment(2);
			table2.getDefaultCell().setMinimumHeight(16);
			PdfPCell cell;
			PdfPCell cell2;
			cell = new PdfPCell(new Phrase(" "));
			cell.setColspan(3);
			table2.addCell(new Phrase("Ͷ����",fontChinese1));
			table2.addCell(cell);
			table2.addCell(new Phrase("Ͷ��������",fontChinese1));
			table2.addCell(" ");
			
			table2.addCell(new Phrase("��ϵ��ַ",fontChinese1));
			table2.addCell(cell);
			table2.addCell(new Phrase("��������",fontChinese1));
			table2.addCell(" ");
			
			table2.addCell(new Phrase("֤������",fontChinese1));
			table2.addCell(" ");
			table2.addCell(new Phrase("֤������",fontChinese1));
			table2.addCell(cell);
			
			table2.addCell(new Phrase("����������/������",fontChinese1));
			table2.addCell(" ");
			table2.addCell(new Phrase("������ҵ",fontChinese1));
			table2.addCell(" ");
			table2.addCell(new Phrase("Ա������",fontChinese1));
			table2.addCell(" ");

			table2.addCell(new Phrase("��ϵ������",fontChinese1));
			table2.addCell(" ");
			table2.addCell(new Phrase("���ڲ���",fontChinese1));
			table2.addCell(cell);
	        
			table2.addCell(new Phrase("��ϵ�˵绰",fontChinese1));
			table2.addCell(" ");
			table2.addCell(new Phrase("��������",fontChinese1));
			table2.addCell(cell);
			 // ���ô�ֱ����
	        cell.setVerticalAlignment(1);
	        // ����ˮƽ����
	        cell.setHorizontalAlignment(1);
			document.add(table2);
			document.add(new Paragraph(" "));
			Phrase phrase7 = new Phrase("����������������",fontChinese2);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase7, 30, 550, 0);
			
			float[] widths3 = {0.18f,0.82f};
			PdfPTable table3 = new PdfPTable(widths3);
			table3.setTotalWidth(540);
			table3.setLockedWidth(true);
			table3.getDefaultCell().setMinimumHeight(16);
//			table3.addCell(table3.getDefaultCell());//��ӿո�
			table3.addCell(new Phrase("������������",fontChinese1));
			table3.addCell(new Phrase("                   �� (��������Ͷ��������_�����ļ�   _�����ļ�  _����________)",fontChinese1));
			document.add(table3);
			document.add(new Paragraph(" "));
			Phrase phrase8 = new Phrase("����Ͷ������",fontChinese2);
			Phrase phrase9 = new Phrase("(��ֵ��λ: �����Ԫ)",fontChinese1);
			Phrase phrase10 = new Phrase("���������Ƿ���: _��   _��",fontChinese1);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase8, 30, 515, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase9, 95, 515, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase10, 350, 515, 0);

			float[] widths4 = {0.38f,0.10f,0.15f,0.20f,0.17f};
			PdfPTable table4 = new PdfPTable(widths4);
			table4.setTotalWidth(540);
			table4.setLockedWidth(true);
			table4.getDefaultCell().setMinimumHeight(16);
			table4.addCell(new Phrase("����/������������",fontChinese2));
			table4.addCell(new Phrase("�����ڼ�",fontChinese2));
			table4.addCell(new Phrase("������������",fontChinese2));
			table4.addCell(new Phrase("(����)���ս���ܶ�",fontChinese2));
			table4.addCell(new Phrase("���շ�",fontChinese2));
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
			cell = new PdfPCell(new Phrase("���շѺϼ�",fontChinese2));
			cell.setRowspan(2);
			table5.addCell(cell);
			table5.addCell(new Phrase("(��д)	      ��      Ǫ      ��      ʰ     ��     Ǫ     ��      ʰ     Ԫ     ��    ��",fontChinese2));
			table5.addCell(new Phrase("��:",fontChinese2));
			document.add(table5);
			
			float[] widths6 = {0.13f,0.35f,0.15f,0.37f};
			PdfPTable table6 = new PdfPTable(widths6);
			table6.setTotalWidth(540);
			table6.setLockedWidth(true);
			table6.getDefaultCell().setMinimumHeight(16);
			table6.addCell(new Phrase("���ѷ�ʽ",fontChinese2));
			table6.addCell(new Phrase("_һ�ν���     _�꽻   _����_____",fontChinese1));
			table6.addCell(new Phrase("�����ڼ�",fontChinese1));
			table6.addCell(new Phrase(" ",fontChinese1));
			document.add(table6);

			float[] widths7 = {1.0f};
			PdfPTable table7 = new PdfPTable(widths7);
			table7.setTotalWidth(540);
			table7.setLockedWidth(true);
			table7.getDefaultCell().setMinimumHeight(32);
			//cell = new PdfPCell(new Phrase("����/����֧����ʽ:  _����ת��    _���д���    _�ֽ�    _֧Ʊ    _POS��    _����    _����____________",fontChinese1));
			//cell2 = new PdfPCell(new Phrase("���ڱ���֧����ʽ:  _����ת��   _����   ������ѡ������ת�ˣ�������д����������˾����������Ȩ�˺Ż�ת���շ�",fontChinese1));						
			//table7.getDefaultCell().setBorder(0);
			table7.addCell(new Phrase("����/����֧����ʽ:  _����ת��    _���д���    _�ֽ�    _֧Ʊ    _POS��    _����    _����____________   ���ڱ���֧����ʽ:  _����ת��   _����   ������ѡ������ת�ˣ�������д����������˾����������Ȩ�˺Ż�ת���շ�",fontChinese1));
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
			table8.addCell(new Phrase("Ͷ���˻���:         ��Ȩ����:                ��Ȩ�˺�:",fontChinese1));
			table8.addCell(new Phrase(" ",fontChinese1));
			document.add(table8);
			
			document.add(new Paragraph(" "));
			Phrase phrase11 = new Phrase("�ġ��ر�Լ�� (�����ر�Լ��������������ע����������ע�����ޡ�)",fontChinese2);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase11, 30, 275, 0);
			float[] widths9 = {1.0f};
			PdfPTable table9 = new PdfPTable(widths9);
			table9.setTotalWidth(540);
			table9.setLockedWidth(true);
			table9.getDefaultCell().setMinimumHeight(96);
			table9.addCell(new Paragraph(" ",fontChinese1));
			document.add(table9);

			document.add(new Paragraph(" "));
			Phrase phrase12 = new Phrase("�塢������֪",fontChinese2);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase12, 30, 160, 0);
			float[] widths10 = {0.15f,0.15f,0.15f,0.2f,0.2f,0.15f};
			PdfPTable table10 = new PdfPTable(widths10);
			table10.setTotalWidth(540);
			table10.setLockedWidth(true);
			table10.getDefaultCell().setMinimumHeight(16);
			
			table10.addCell(new Paragraph("ƽ������:   ",fontChinese1));
			table10.addCell(new Paragraph("�������:   ",fontChinese1));
			table10.addCell(new Paragraph("�������:   ",fontChinese1));
			table10.addCell(new Paragraph("��������ռ��:   ",fontChinese1));
			table10.addCell(new Paragraph("����������������:   ",fontChinese1));
			table10.addCell(new Paragraph("�⼮��Ա����:   ",fontChinese1));

			cell = new PdfPCell(new Phrase("����Ͷ���ı��ս����շѷ�������: _ƽ��   _������   _ְλ   _ְҵ���   _�ۺ϶������� (��˵��)                           ",fontChinese1));
			cell.setColspan(6);
			cell.setRowspan(2);
			cell.setMinimumHeight(32);
			table10.addCell(cell);
			
			cell = new PdfPCell(new Phrase("�������� (����������������) һ�����Ƿ���Ҫǰ���й���½����ĵ�������ң�                       _��   _��               ��������г���Ҫǰ���ĵ��������:       ",fontChinese1));
			cell.setColspan(6);
			cell.setRowspan(2);
			cell.setMinimumHeight(32);
			table10.addCell(cell);
			
			cell = new PdfPCell(new Phrase("��ȥ�����Ƿ���Ͷ����ҵ���� (��������д�Ͷ����˾�����֡����Ѽ��⸶���) :                     _��   _��",fontChinese1));
			cell.setColspan(6);
			cell.setRowspan(2);
			cell.setMinimumHeight(32);
			table10.addCell(cell);
			
			Phrase phrase13 = new Phrase("�� 1 ҳ   ��2ҳ",fontChinese2);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase13, 250, 25, 0);			
			document.add(table10);
			
			document.newPage();
			
			Phrase phrase18 = new Phrase("����������֪", fontChinese2); 
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase18, 30, 815, 0);
			
			//document.add(new Phrase("����������֪",fontChinese2));
			float[] widths11 = {0.5f,0.4f,0.1f};
			PdfPTable table11 = new PdfPTable(widths11);
			table11.setTotalWidth(540);
			table11.setLockedWidth(true);
			table11.getDefaultCell().setMinimumHeight(16);
			
			cell = new PdfPCell(new Phrase(" (һ) ���֪�������˼����������������¸���:",fontChinese2));
			cell.setColspan(3);	
			cell.setMinimumHeight(16);
			table11.addCell(cell);
			
			cell = new PdfPCell(new Phrase("1.���֪�Ƿ�ӵ�й���ҽ�ơ����ҽ�Ʊ��պ�����ҽ�Ʒ��ò����ͱ��գ�",fontChinese1));
			cell.setColspan(2);	
			cell.setMinimumHeight(16);
			table11.addCell(cell);
			table11.addCell(new Phrase("_��  _��",fontChinese1));
			
			cell = new PdfPCell(new Phrase("2.�����Ƿ��л���סԺ�ߣ�",fontChinese1));
			cell.setColspan(2);
			cell.setMinimumHeight(16);
			table11.addCell(cell);
			table11.addCell(new Phrase("_��  _��",fontChinese1));

			cell = new PdfPCell(new Phrase("3.���ڻ��ȥ�Ƿ����������ؼ�����: ���ಡ����Ѫѹ�����з硢���������� (����Ѫ��)�����Ը��ס���Ӳ������֢�����򲡡����񼲲���������ֲ���м���ֲ���˵�",fontChinese1));
			cell.setColspan(2);	
			cell.setRowspan(2);
			cell.setMinimumHeight(32);
			table11.addCell(cell);
			cell = new PdfPCell(new Phrase("_��  _��",fontChinese1));
			cell.setColspan(2);	
			cell.setRowspan(2);
			table11.addCell(cell);
				
			cell = new PdfPCell(new Phrase("4.��һ�����Ƿ����򲡲��ڹ�����λ�ۼƳ���10���ߣ�",fontChinese1));
			cell.setColspan(2);	
			cell.setMinimumHeight(16);
			table11.addCell(cell);
			cell = new PdfPCell(new Phrase("_��  _��",fontChinese1));
			table11.addCell(cell);

			cell = new PdfPCell(new Phrase("5.�Ƿ��вм���Ա�μӱ���Ͷ����",fontChinese1));
			cell.setColspan(2);	
			cell.setMinimumHeight(16);
			table11.addCell(cell);
			cell = new PdfPCell(new Phrase("_��  _��",fontChinese1));
			table11.addCell(cell);
			
			cell = new PdfPCell(new Phrase(" (��) Ͷ�������գ������ϸ�֪�⣬���֪�������˼��������������˹�ȥ5�����Ƿ������¼���:",fontChinese2));
			cell.setColspan(3);	
			cell.setMinimumHeight(16);
			table11.addCell(cell);
			
			cell = new PdfPCell(new Phrase("6.�Թ������Գ�Ѫ�����������֢��������ϵͳ�����;��񼲲�                                  _��  _��",fontChinese1));
			cell.setRowspan(2);
			cell.setMinimumHeight(25);
			table11.addCell(cell);
			cell = new PdfPCell(new Phrase("7.��Ѫѹ�����Ĳ�������ʧ�����ļ�����������˥�߻�����ѭ��ϵͳ����                                _��  _��",fontChinese1));
			cell.setColspan(2);	
			cell.setRowspan(2);	
			table11.addCell(cell);
			
			cell = new PdfPCell(new Phrase("8.����֧�����ס������ס�����˥�߻���������ϵͳ����                                                                    _��  _��",fontChinese1));
			cell.setRowspan(2);
			cell.setMinimumHeight(25);
			table11.addCell(cell);
			cell = new PdfPCell(new Phrase("9.���ס���Ӳ������ʯ֢�������ס��������������������ϵͳ����                               _��  _��",fontChinese1));
			cell.setColspan(2);	
			cell.setRowspan(2);	
			cell.setMinimumHeight(25);
			table11.addCell(cell);
			
			cell = new PdfPCell(new Phrase("10.���ס���֢���������༲������·��ʯ������������ֳϵͳ����                            _��  _��",fontChinese1));
			cell.setRowspan(2);
			cell.setMinimumHeight(25);
			table11.addCell(cell);
			cell = new PdfPCell(new Phrase("11.���򲡡���״�ټ��ܿ�������»������ڷ���ϵͳ����                                                _��  _��",fontChinese1));
			cell.setColspan(2);	
			cell.setRowspan(2);	
			cell.setMinimumHeight(25);
			table11.addCell(cell);
			
			cell = new PdfPCell(new Phrase("12.��Ѫ���������ϰ���ƶѪ���ܰ���������ѪҺϵͳ����                                                   _��  _��",fontChinese1));
			cell.setRowspan(2);
			cell.setMinimumHeight(25);
			table11.addCell(cell);
			cell = new PdfPCell(new Phrase("13.ϵͳ�Ժ���Ǵ������ʪ�Թؽ��ס�ǿֱ�Լ����׻�������ʪ�Լ���                           _��  _��",fontChinese1));
			cell.setColspan(2);	
			cell.setRowspan(2);	
			cell.setMinimumHeight(25);
			table11.addCell(cell);
			
			cell = new PdfPCell(new Phrase("14.׵����ͻ��֢���ǹؽڲ��������׻���������ϵͳ����                                                   _��  _��",fontChinese1));
			cell.setRowspan(2);
			cell.setMinimumHeight(25);
			table11.addCell(cell);
			cell = new PdfPCell(new Phrase("15.�����ϡ�����ۡ�����Ĥ�����񾭲���ȼ���                                                                    _��  _��",fontChinese1));
			cell.setColspan(2);	
			cell.setRowspan(2);	
			cell.setMinimumHeight(25);
			table11.addCell(cell);
			
			cell = new PdfPCell(new Phrase("16.��֢������ (���ԡ����Ի���δ֤ʵΪ���Ի����) ��Ϣ�� �����׵ȼ���                 _��  _��",fontChinese1));
			cell.setRowspan(2);
			cell.setMinimumHeight(25);
			table11.addCell(cell);
			cell = new PdfPCell(new Phrase("17.�����Լ�����ְҵ�����ƾ���ҩ�����ó��                                                                    _��  _��",fontChinese1));
			cell.setColspan(2);	
			cell.setRowspan(2);	
			cell.setMinimumHeight(25);
			table11.addCell(cell);
			
			cell = new PdfPCell(new Phrase("18.���ټ������ӹ��������ѳ����׻��������Ƽ���                 _��  _��",fontChinese1));
			cell.setMinimumHeight(25);
			table11.addCell(cell);
			cell = new PdfPCell(new Phrase("19.����δ�ἰ�ļ���                                                                    _��  _��",fontChinese1));
			cell.setMinimumHeight(25);
			cell.setColspan(2);	
			table11.addCell(cell);
		
			cell = new PdfPCell(new Phrase("������2-19���֪Ϊ���ǡ����뱻�����˻򸽴��������˱���(δ����������໤��)��д����������֪ͨ�� (����)����",fontChinese2));
			cell.setMinimumHeight(16);
			cell.setColspan(3);
			table11.addCell(cell);
						
			document.add(table11);
			document.add(new Paragraph(" "));
			Phrase phrase19 = new Phrase("�ߡ���ע��", fontChinese2); 
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase19, 30, 475, 0);
			
			//document.add(new Phrase("�ߡ���ע��",fontChinese2));
			float[] widths12 = {1.0f};
			PdfPTable table12 = new PdfPTable(widths12);
			table12.setTotalWidth(540);
			table12.setLockedWidth(true);
			table12.getDefaultCell().setMinimumHeight(48);
			table12.addCell(new Paragraph(" ",fontChinese1));
			document.add(table12);
			
			document.add(new Paragraph(" "));
			Phrase phrase20 = new Phrase("�ˡ�Ͷ������������Ȩ", fontChinese2); 
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase20, 30, 410, 0);
			
			//document.add(new Phrase("�ˡ�Ͷ������������Ȩ",fontChinese2));
			Phrase phrase14 = new Phrase("1. �������յ��й��������ٱ��չɷ����޹�˾�����¼�ơ��˱����ա����ṩ�ı������������Ա������˵�����պ�ͬ���ݣ����ͱ������Ρ�����������������������������������ʡ��ȴ��ڡ������⸶���������ֹ��ͬ�Ȳ��ֻ�ȫ����������Ʊ��������ε������Ͷ����ʾ���ر�Լ���������������ݽ����˵���˵���������������Ķ�����������Ѿ�֪�������е���˵�����ֵĸ�����ݼ��䷨�ɺ���������Ѿ�֪�������κ��˶Ա������Ͷ������������������֮��Υ���������Ŀ�ͷ˵�������������Ϊ��Ч��һ�о��Ա��պ�ͬ����Ϊ׼��                                                                             "+
"2.  ����Ͷ�����ݾ��Ѹ�֪�����������˻���໤��ͬ�⣬�������˾��ɱ�������ָ������Ͷ������д�ĸ���������ʵ������������ʵ��֪���˱����տ�����������պ�ͬ�����Ա��պ�ͬ���ǰ�����ı����¹ʲ��е��������Ρ�                                                                          "+
"3.  �������˽����ͬ������˱�����ǩ���ı��յ�������ǩ����κ��ļ�����Ϊ���Ͽ��˱����ն�Ͷ�����ĸ��Ӻ͸��ġ�                                                                                                                                 "+
"4.  ����ͬ���˱��������κλ�������֯�͸��˾ͱ����κ��йر������ˡ��������˽���״�������������ȡ����ѯ�й����Ϻ�֤��������Ȩ�˱����ջ�    �κ����˱�����ҵ���йصĻ�����������ڣ�1���������Ͷ�����������������ˣ�2���ṩ��ñ����йصķ���3���뱾�����硣����Ȩ���Ӱӡ��Ҳͬ����Ч��                                                                          "+
"5.  �������˽��˱����ն������ڹ�˾�ڱ���ȫ�������������������Ͻ��е��������񣬳���������4������������ⲻ�����á�                                                                                                            "+
"6.  �籾��Ͷ���ƻ����������ղ�Ʒ�������������Ķ�����������彡������Ͷ����ʾ��                                                                                                       "+
"7.  ����֪����ʹ������Ԥ�ɱ��շѣ������պ�ͬ��δ��Ч�������պ�ͬ����Ч�Ա��պ�ͬ���������˽ɸ����շ����˱�����ǩ�����յ�Ϊ�����������պ�ͬ����Ч�����Ա��յ�������������Ϊ׼��", fontChinese1); 
								
			
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
			Phrase phrase15 = new Phrase("��Ͷ����ȷ�ϱ�Ͷ����������������ʵ��׼ȷ�����������ѳ���˽���������Ȩ��ǩ������", fontChinese2); 
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase15, 30, 218, 0);
			
			Phrase phrase16 = new Phrase("Ͷ����/Ͷ����λ������/����������ǩ��:", fontChinese2); 
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase16, 30, 205, 0);
			
			Phrase phrase17 = new Phrase("Ͷ������:             ��          ��        ��", fontChinese2); 
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase17, 30, 190, 0);
			
			Phrase phrase21 = new Phrase("�š����������б��չ�˾��д", fontChinese2); 
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase21, 30, 175, 0);
			
			float[] widths14 = {0.15f,0.25f,0.6f};
			PdfPTable table14 = new PdfPTable(widths14);
			table14.setTotalWidth(540);
			table14.setLockedWidth(true);
			table14.getDefaultCell().setMinimumHeight(16);
			
			cell = new PdfPCell(new Phrase("���չ�˾",fontChinese1));
			cell.setRowspan(2);
			cell.setMinimumHeight(32);
			table14.addCell(cell);
			
			cell = new PdfPCell(new Phrase("��������:          Ӫҵ��λ:                 ����:        ",fontChinese1));
			cell.setColspan(2);
			cell.setMinimumHeight(16);
			table14.addCell(cell);
			
			cell = new PdfPCell(new Phrase("�˱�����������Ա:     ����:      ����������Ա:    ����:        ",fontChinese1));
			cell.setColspan(2);
			cell.setMinimumHeight(16);
			table14.addCell(cell);

			cell = new PdfPCell(new Phrase("�н����",fontChinese1));
			cell.setMinimumHeight(16);
			table14.addCell(cell);
			
			cell = new PdfPCell(new Phrase("��������:     ����:      ������:    ����:        ",fontChinese1));
			cell.setColspan(2);
			cell.setMinimumHeight(16);
			table14.addCell(cell);
			
			cell = new PdfPCell(new Phrase("ͳ��ҵ��",fontChinese1));
			cell.setMinimumHeight(16);
			table14.addCell(cell);
			
			cell = new PdfPCell(new Phrase("_��  _��",fontChinese1));
			cell.setMinimumHeight(16);
			table14.addCell(cell);
			
			cell = new PdfPCell(new Phrase("ͳ��ҵ����Ŀ���:",fontChinese1));
			cell.setMinimumHeight(16);
			table14.addCell(cell);
			
			cell = new PdfPCell(new Phrase("���ѷָ�",fontChinese1));
			cell.setMinimumHeight(16);
			table14.addCell(cell);
			
			cell = new PdfPCell(new Phrase("_��  _��           ��ѡ��Ϊ ���ǡ�ʱ�����ڡ�������Ϣ��������ҵ��ָ���𼰱�����",fontChinese1));
			cell.setColspan(2);
			table14.addCell(cell);
			
			cell = new PdfPCell(new Phrase("_��  _��           ��ѡ��Ϊ ���ǡ�ʱ�����ڡ�������Ϣ��������ҵ��ָ���𼰱�����",fontChinese1));
			cell.setColspan(3);
			cell.setRowspan(2);
			cell.setMinimumHeight(25);
			table14.addCell(cell);
			document.add(table14);	
		
			Phrase phrase22 = new Phrase("�ͻ�����绰:400-8895518   ��˾��ַ:WWW.picclife.com   ��˾����������ַ:WWW.e-picclife.com", fontChinese2); 
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase22, 30, 30, 0);
			
			Phrase phrase23 = new Phrase("�� 2 ҳ   ��2ҳ", fontChinese2); 
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase23, 250, 15, 0);
						
			//Step 5��Close the Document.  
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
