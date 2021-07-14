package Presentation;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUIAdmin extends JFrame{
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JTable mainTable;
    private JTextField title;
    private JTextField rating;
    private JTextField calories;
    private JTextField protein;
    private JTextField fat;
    private JTextField sodium;
    private JTextField price;
    private JButton addNewProductButton;
    private JButton deleteTheSelectedProductButton;
    private JButton updateTheSelectedProductButton;
    private JButton searchByNameButton;
    private JTable tableAvailableProducts;
    private JTable tableAddedProducts;
    private JLabel availableProducts;
    private JLabel addedProducts;
    private JTextField menuName;
    private JButton createNewMenuButton;
    private JButton addTheSelectedProductButton;
    private JTextField start;
    private JLabel end_hour;
    private JLabel start_hour;
    private JTextField end;
    private JButton searchTheOrdersByTime;
    private JTextField minNumber;
    private JButton searchProducts;
    private JTextField nbOfTimes;
    private JTextField minPrice;
    private JButton searchClients;
    private JTextField date;
    private JButton searchProductsOrderedByDay;
    private JTextArea textAreaForReport;
    private JButton logout;

    public GUIAdmin()  {
        add(panel1);
        setMinimumSize(new Dimension(600,500));
    }

    public void setAddButtonActionListener(ActionListener actionListener)
    {
        addNewProductButton.addActionListener(actionListener);
    }
    public void setDeleteButtonActionListener(ActionListener actionListener)
    {
        deleteTheSelectedProductButton.addActionListener(actionListener);
    }
    public void setUpdateButtonActionListener(ActionListener actionListener)
    {
        updateTheSelectedProductButton.addActionListener(actionListener);
    }

    public void setSearchButtonActionListener(ActionListener actionListener)
    {
        searchByNameButton.addActionListener(actionListener);
    }

    public void setAddTheSelectedProductButtonActionListener(ActionListener actionListener)
    {
        addTheSelectedProductButton.addActionListener(actionListener);
    }

    public void setCreateNewMenuButtonActionListener(ActionListener actionListener)
    {
        createNewMenuButton.addActionListener(actionListener);
    }

    public void setLogoutButtonActionListener(ActionListener actionListener)
    {
        logout.addActionListener(actionListener);
    }

    public void setSearchOrdersByTimeButtonActionListener(ActionListener actionListener)
    {
        searchTheOrdersByTime.addActionListener(actionListener);
    }

    public void setSearchProductsButtonActionListener(ActionListener actionListener)
    {
        searchProducts.addActionListener(actionListener);
    }

    public void setSearchClientsButtonActionListener(ActionListener actionListener)
    {
        searchClients.addActionListener(actionListener);
    }

    public void setSearchProductsOrderedByDayButtonActionListener(ActionListener actionListener)
    {
        searchProductsOrderedByDay.addActionListener(actionListener);
    }



    public void setTabsChangeListener(ChangeListener changeListener)
    {
        tabbedPane1.addChangeListener(changeListener);
    }


    public void setMainTableModel(TableModel tableModel) {
        mainTable.setModel(tableModel);
    }

    public void setTableAvailableProductsModel(TableModel tableModel) {
        tableAvailableProducts.setModel(tableModel);
    }

    public void setTableAddedProductsModel(TableModel tableModel) {
        tableAddedProducts.setModel(tableModel);
    }

    public void showWrongDataMessage(String s)
    {
        JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(frame, s);
    }

    public JTextField getProductTitle() {
        return title;
    }

    public JTextField getRating() {
        return rating;
    }

    public JTextField getCalories() {
        return calories;
    }

    public JTextField getProtein() {
        return protein;
    }

    public JTextField getFat() {
        return fat;
    }

    public JTextField getSodium() {
        return sodium;
    }

    public JTextField getPrice() {
        return price;
    }

    public JTable getMainTable() {
        return mainTable;
    }

    public JTable getTableAvailableProducts() {
        return tableAvailableProducts;
    }

    public JTextField getMenuName() {
        return menuName;
    }

    public JTextField getStart() {
        return start;
    }

    public JTextField getEnd() {
        return end;
    }

    public JTextField getMinNumber() {
        return minNumber;
    }

    public JTextField getNbOfTimes() {
        return nbOfTimes;
    }

    public JTextField getMinPrice() {
        return minPrice;
    }

    public JTextField getDate() {
        return date;
    }

    public void setTextAreaForReport(String s) {
        this.textAreaForReport.setText(s);
    }
}
