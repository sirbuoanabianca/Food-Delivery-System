package Presentation;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUIClient extends JFrame{
    private JTable productsTable;
    private JTable orderTable;
    private JButton placeTheOrderButton;
    private JButton addButton;
    private JPanel panel;
    private JButton logout;
    private JComboBox comboBox;
    private JTextField keyword;

    public GUIClient()  {
        add(panel);
        setMinimumSize(new Dimension(550,550));
    }

    public void setAddTheSelectedProductButtonActionListener(ActionListener actionListener)
    {
        addButton.addActionListener(actionListener);
    }

    public void setPlaceTheOrderButtonActionListener(ActionListener actionListener)
    {
        placeTheOrderButton.addActionListener(actionListener);
    }

    public void setProductsTableModel(TableModel tableModel) {
        productsTable.setModel(tableModel);
    }

    public void setTableSelectedProductsModel(TableModel tableModel) {
        orderTable.setModel(tableModel);
    }

    public void setComboBoxButtonListener(ActionListener actionListener)
    {
        comboBox.addActionListener(actionListener);
    }

    public void setLogoutButtonActionListener(ActionListener actionListener)
    {
        logout.addActionListener(actionListener);
    }
    public JTable getProductsTable() {
        return productsTable;
    }

    public JTable getOrderTable() {
        return orderTable;
    }

    public JTextField getKeyword() {
        return keyword;
    }
}
