package SpaceClash.utils;

import javax.swing.*;
import java.io.*;
import java.util.Properties;

public class Config {

    //---------------------------------------------------------------
    // STATIC ATTRIBUTE
    //---------------------------------------------------------------
    private static Config instance = null;

    //---------------------------------------------------------------
    // INSTANCE ATTRIBUTE
    //---------------------------------------------------------------
    private File dataPlayerFile =new File("DataPlayers.xml");
    private WriteXml writeXml=new WriteXml();
    private ReadXml readXml;
    private Properties properties;

    private Config(){
        BufferedReader buffRead = null;
        try {
            File configfile = new File("config.txt");
            buffRead = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(configfile),"ISO-8859-1"));
            this.properties = new Properties();
            this.properties.load(buffRead);
        }
        catch(FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(null,
                    "Configuration file not found, the program will be closed.",
                    "Serious ERROR",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
        catch(IOException ioe) {
            JOptionPane.showMessageDialog(null,
                    "Unable to read the configuration file, the program will be closed.",
                    "Serious ERROR",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        } finally {
            try {
                if (buffRead != null)
                    buffRead.close();
            }
            catch(IOException ioe) {
                ioe.printStackTrace();
            }
        } // end finally
    }

    //---------------------------------------------------------------
    // PUBLIC INSTANCE METHODS
    //---------------------------------------------------------------
    public void WriteXml(){
        if(dataPlayerFile.length()!=0) {
            writeXml.writeTheOtherTimes();
        }
        else {
            writeXml.writeTheFirstTime();
        }
    }

    public void setPlayer(String name, int score){
        writeXml.setPlayer(name, score);
    }

    public void ReadXml(){
        readXml=new ReadXml();
    }

    public void updateScoreInTheXmlFile(){
        writeXml.updateScoreInTheXmlFile();
    }

    public int getCounterPlayer(){
        return readXml.getPlayerCounter();
    }

    public int getHisghscore(){
        return PlayersData.getInstance().highscore();
    }

    public int getDelayTime() {
        return Integer.parseInt(this.properties.getProperty("delayTime"));
    }

    public int getSpaceshipVelocity() {
        return Integer.parseInt(this.properties.getProperty("speed"));
    }

    public String getColorBackgroundRightPanel() {
        return this.properties.getProperty("colorBackgroundRightPanel");
    }

    //---------------------------------------------------------------
    // STATIC METHODS
    //---------------------------------------------------------------
    public static Config getInstance() {
        if (instance == null)
            instance = new Config();
        return instance;
    }
}
