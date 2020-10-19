package SpaceClash.view;
import SpaceClash.utils.PlayersData;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RankingWindow extends JFrame {
    private JTable rankingTable;
    private JButton backButton;
    private JButton startGameButton;
    private JPanel panel;
    private String[] columnNames = {"Players","Score"};
    private int width=650;
    private int height=484;

    public RankingWindow(){
        super();
        Container cont=this.getContentPane();
        cont.add(panel);
        this.setPreferredSize(new Dimension(width,height));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
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


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleBackEvent();
            }
        });

        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleStartGameEvent();
            }
        });


    }

    private void handleBackEvent(){
        View.getInstance().openStartWindow();
        View.getInstance().closeRankingWindow();
    }

    private void handleStartGameEvent(){
        View.getInstance().openOptionsPanel();
        View.getInstance().closeRankingWindow();
    }

}
