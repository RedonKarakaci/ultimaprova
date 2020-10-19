package SpaceClash.utils;

import SpaceClash.logic.Logic;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class WriteXml {

    //---------------------------------------------------------------
    // INSTANCE ATTRIBUTES
    //---------------------------------------------------------------
    private Player p;

    public WriteXml(){

    }

    public void writeTheFirstTime(){
        try {
            PlayersData.getInstance().add(p);
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Element configGame = document.createElement("configuration-game");
            document.appendChild(configGame);
            Element tagplayer = document.createElement("player");
            configGame.appendChild(tagplayer);
            Element playername = document.createElement("player-name");
            tagplayer.appendChild(playername);
            tagplayer.setAttribute("id",String.valueOf(0));
            playername.appendChild(document.createTextNode(p.getPlayerName()));
            Element score = document.createElement("score");
            tagplayer.appendChild(score);
            score.appendChild(document.createTextNode(String.valueOf(p.getScore())));

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File("DataPlayers.xml"));
            transformer.transform(source, streamResult);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void writeTheOtherTimes() {
        File xmlFile = new File("DataPlayers.xml");
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);
            Node configurationGame = document.getDocumentElement();
            Element tagplayer = document.createElement("player");
            configurationGame.appendChild(tagplayer);
            Element playername = document.createElement("player-name");
            tagplayer.appendChild(playername);
            tagplayer.setAttribute("id",String.valueOf(PlayersData.getInstance().getNumberOfPlayers()));
            playername.appendChild(document.createTextNode(p.getPlayerName()));
            Element score = document.createElement("score");
            tagplayer.appendChild(score);
            score.appendChild(document.createTextNode(String.valueOf(p.getScore())));

            DOMSource source = new DOMSource(document);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            StreamResult result = new StreamResult(xmlFile);
            transformer.transform(source, result);
            PlayersData.getInstance().add(p);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateScoreInTheXmlFile(){
        File xmlFile = new File("DataPlayers.xml");
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document doc = documentBuilder.parse(xmlFile);
            NodeList playerdata = doc.getElementsByTagName("player");
            Element player = null;
            // loop for each user
            for (int i = 0; i < playerdata.getLength(); i++) {
                player = (Element) playerdata.item(i);
                String v=player.getAttribute("id");
                if (v.equals(String.valueOf((Config.getInstance().getCounterPlayer())))){
                    Node score = player.getElementsByTagName("score").item(0).getFirstChild();
                    score.setTextContent(String.valueOf(Logic.getInstance().getScore()));
                }
            }
            DOMSource source = new DOMSource(doc);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            StreamResult result = new StreamResult(xmlFile);
            transformer.transform(source, result);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setPlayer(String name, int score){
        p=new Player(name, score);
    }

}
