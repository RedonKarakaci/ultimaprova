package SpaceClash.view;

import SpaceClash.utils.Config;
import SpaceClash.utils.PlayersData;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverDialog extends JFrame{

    private JPanel panel;
    private JButton restartButton;
    private int width=450;
    private int height=450;
    private JButton exitButton;
    private String[] columnNames = {"Players","Score"};
    private JTable rankingTable;

    public GameOverDialog(){
        super();
        Container cont=this.getContentPane();
        cont.add(panel);
        this.setPreferredSize(new Dimension(width,height));

        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRestartEvent();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleExitEvent();
            }
        });
    }

    public void handleShowRankingEvent(){
        Config.getInstance().updateScoreInTheXmlFile();
        PlayersData.getInstance().clearList();
        Config.getInstance().ReadXml();
        TableModel dataModel = new
                AbstractTableModel() {
                    public int getColumnCount() {
                        return columnNames.length;
                    }

                    public int getRowCount() {
                        int size;
                        if (PlayersData.getInstance().getListOfPlayers() == null) {
                            size = 0;
                        }
                        else {
                            size = PlayersData.getInstance().getListOfPlayers().size();
                        }
                        return size;
                    }

                    public Object getValueAt(int row, int col) {
                        Object temp = null;
                        PlayersData.getInstance().getReorderedListOfPlayers();
                        if (col == 0) {
                            temp = PlayersData.getInstance().getListOfPlayers().get(row).getPlayerName();
                        }
                        else if (col == 1) {
                            temp = PlayersData.getInstance().getListOfPlayers().get(row).getScore();
                        }
                        return temp;
                    }

                    @Override
                    public String getColumnName(int column) {
                        return columnNames[column];
                    }
                };

        rankingTable.setModel(dataModel);
    }

    private void handleRestartEvent() {
        View.getInstance().handleRestartEvent();
        View.getInstance().closeGameOverDialogWindow();
    }

    private void handleExitEvent(){
        View.getInstance().handleExitEvent();
    }
}
