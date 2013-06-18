package battleship.logic;

import java.awt.Font;
import java.awt.Image;
import java.net.URL;
import java.util.List;

import javax.swing.text.Document;

import battleship.objects.User;

public class PdfGenerator extends PdfPageEventHelper  {
	
	private Font def = createFont(FontFactory.defaultEncoding, 10, Font.NORMAL);
	private Font subdef = createFont(FontFactory.HELVETICA_BOLD, 10, Font.NORMAL);
    private Font subheadline = createFont(FontFactory.HELVETICA_BOLD, 12,Font.NORMAL);

    public Document createPDFDocument(List<User> userList, Document document) throws DocumentException {
		Font def = createFont(FontFactory.defaultEncoding, 10, Font.NORMAL);
		Font subdef = createFont(FontFactory.HELVETICA_BOLD, 10, Font.NORMAL);
	    Font subheadline = createFont(FontFactory.HELVETICA_BOLD, 12,Font.NORMAL);
	    for (User user : userList) {
	    	//Battleship Logo
	    	getLogo(document);
			document.add(new Paragraph("\n\n\n\n"));
			
			//

			
			
	    	
		}
		return document;
    }
    
    
    private void getLogo(Document document) {
    	URL configURL = getClass().getResource("graphics/BattleshipLogo.png");
    	Image png;
		png = Image.getInstance(configURL);
		png.setAbsolutePosition(468, 778);
		png.scaleAbsolute(100, 28);
		png.setAlignment(Element.ALIGN_RIGHT);
    }
    
}