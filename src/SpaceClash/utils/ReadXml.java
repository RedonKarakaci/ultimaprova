package SpaceClash.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class ReadXml {

    //---------------------------------------------------------------
    // STATIC FIELDS
    //---------------------------------------------------------------
    private int playerCounter=0;

    //---------------------------------------------------------------
    // INSTANCE ATTRIBUTES
    //---------------------------------------------------------------

    public ReadXml(){
        File f = new File("DataPlayers.xml");
        if(f.length()!=0) {
            DocumentBuilderFactory factory;
            DocumentBuilder parser;
            Document document;
            try {
                factory = DocumentBuilderFactory.newInstance();
                parser = factory.newDocumentBuilder();
                document = parser.parse(f);
                handleDocument(document);
            } catch (Exception ex) {
                System.out.println("Errore.");
                ex.printStackTrace();
            }
        }
    }

    //---------------------------------------------------------------
    // PRIVATE INSTANCE METHOD
    //---------------------------------------------------------------
    private void handleDocument(Document document) {
        NodeList player = document.getElementsByTagName("player");
        for(int i = 0; i < player.getLength(); i++) {
            Element conf = (Element)player.item(i);
            String playername = conf.getElementsByTagName("player-name").item(0).getTextContent();
            String score=conf.getElementsByTagName("score").item(0).getTextContent();
            Player p= new Player(playername, Integer.parseInt(score));
            PlayersData.getInstance().add(p);
            playerCounter++;
        }
    }

    public int getPlayerCounter(){
        return playerCounter;
    }


}
