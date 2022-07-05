package tw.com.ourProject.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Collection;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.itextpdf.io.font.FontEncoding;
import com.itextpdf.kernel.pdf.EncryptionConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.WriterProperties;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;

@Component
public class PdfUtil {
	private static final byte[] USERPASS = "user".getBytes();
	private static final byte[] OWNERPASS = "owner".getBytes();
	PdfWriter pdfWriter;
	String path = "src/main/resources/static/myPdf/test.pdf";

	public String converPdf(Set<String> col, Collection<Object> val) {
		try {

			pdfWriter = new PdfWriter(new FileOutputStream(path));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//    	產生密碼
			WriterProperties ps = new WriterProperties();
			ps.setStandardEncryption(USERPASS, OWNERPASS, EncryptionConstants.ALLOW_PRINTING,
					EncryptionConstants.ENCRYPTION_AES_128);
			PdfDocument pdf = new PdfDocument(pdfWriter);

			
//    	介面物件
			Document doc = new Document(pdf);
			doc.setMargins(80, 0, 60, 150);

//    	產生表格       
			float[] pointColumnWidths = { 80F, 200F };
			Table table = new Table(pointColumnWidths);
//	    表格資料
			for (int i = 0; i < col.size(); i++) {
				table.addCell(col.toArray()[i].toString());
				table.addCell(val.toArray()[i].toString());
			}


// 		加入表格
			doc.add(table);
			doc.close();
			pdf.close();
//		回傳檔案路徑
			return path;
	}

}
