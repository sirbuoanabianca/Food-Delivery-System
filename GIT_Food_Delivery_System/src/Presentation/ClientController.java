package Presentation;

import Business.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class ClientController {
    private GUIClient guiClient;            //fereastra care se deschide doar pt client
    private MainController mainController;  //controleaza care fereastra se va deschide
    private DeliveryService deliveryService;
    private Table tableProducts;            //meniul
    private Table tableOrder;               //tabelul cu produse selectate
    private ArrayList<MenuItem> selectedItems;

    public ClientController(MainController mainController,GUIClient guiClient,DeliveryService deliveryService) {
        this.guiClient = guiClient;
        this.mainController = mainController;

        this.deliveryService = deliveryService;
        tableProducts = new Table();
        tableOrder = new Table();
        selectedItems = new ArrayList<>();

        guiClient.setAddTheSelectedProductButtonActionListener(new AddSelectedProductListener());
        guiClient.setPlaceTheOrderButtonActionListener(new PlaceTheOrderListener());
        guiClient.setLogoutButtonActionListener(new LogoutListener());
        guiClient.setComboBoxButtonListener(new ComboBoxListener());
        refreshTable();
    }

    private void refreshTable()
    {
        tableProducts.generateTable(deliveryService.getAllProducts());
        guiClient.setProductsTableModel(tableProducts.getTableModel());
    }

    private class AddSelectedProductListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            int noSelRowProduct=guiClient.getProductsTable().getSelectedRow();
            String title = (String) guiClient.getProductsTable().getValueAt(noSelRowProduct, 0);
            String rating = (String) guiClient.getProductsTable().getValueAt(noSelRowProduct, 1);
            String calories = (String) guiClient.getProductsTable().getValueAt(noSelRowProduct, 2);
            String proteins = (String) guiClient.getProductsTable().getValueAt(noSelRowProduct, 3);
            String fat = (String) guiClient.getProductsTable().getValueAt(noSelRowProduct, 4);
            String sodium = (String) guiClient.getProductsTable().getValueAt(noSelRowProduct, 5);
            String price = (String) guiClient.getProductsTable().getValueAt(noSelRowProduct, 6);

            BaseProduct b=new BaseProduct(title,Double.parseDouble(rating),
                    Integer.parseInt(calories),Integer.parseInt(proteins),
                    Integer.parseInt(fat),Integer.parseInt(sodium),Integer.parseInt(price));

            selectedItems.add(b);
            //update tabelul din dreapta care contine produsele selectate
            tableOrder.addRow(b);
            guiClient.setTableSelectedProductsModel(tableOrder.getTableModel());
        }
    }

    private class LogoutListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            mainController.setGuiClientVisible(false);
            mainController.setGuiLoginVisible(true);
        }
    }

    private class PlaceTheOrderListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {

            int lastOrder=deliveryService.getOrders().size();
            int idOrder=lastOrder+1;
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Order order=new Order(idOrder,mainController.getIdLoggedClient(),formatter.format(date));

            //adaugare comanda noua in lista cu celelalte comenzi
            deliveryService.createOrder(selectedItems,order);


            StringBuilder bill=new StringBuilder();
            bill.append(order.getOrderDate()).append("\n");
            bill.append("Clientul/clienta"+" cu id-ul "+mainController.getIdLoggedClient()+
                    " a plasat comanda cu numarul "+idOrder+"\n"+"A achizitionat "+ deliveryService.getProductList(order).size()+
                    " produse in valoare TOTALA de "+ deliveryService.getTotalPrice(order)+"\n");
            bill.append("Produsele comandate:\n");
            bill.append(deliveryService.getProductList(order));
            String fileName="order"+idOrder;
            Bill writeBill=new Bill(bill,fileName);



            //golire arraylist pentru retinere temporara produse selectate
            selectedItems=new ArrayList<>();
            //golire tabel din dreapta
            DefaultTableModel d=(DefaultTableModel)tableOrder.getTableModel();
            d.setRowCount(0);
        }
    }

    private class ComboBoxListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            String keyword=guiClient.getKeyword().getText();
            JComboBox comboBox =(JComboBox) e.getSource();
            if(comboBox.getSelectedIndex()==0) //search dupa title
            {
                List<MenuItem> filteredList=  deliveryService.getAllProducts().stream().filter(p->
                        p.getTitle().contains(keyword)).
                        collect(Collectors.toList());
                HashSet<MenuItem> hashSet = new HashSet<>();

                filteredList.forEach(value -> { hashSet.add(value); });
                tableProducts.generateTable(hashSet);
                guiClient.setProductsTableModel(tableProducts.getTableModel());
            }

            if(comboBox.getSelectedIndex()==1) //search dupa rating
            {   double key=Double.parseDouble(keyword);
                List<MenuItem> filteredList=  deliveryService.getAllProducts().stream().filter(p->p.getRating()==key).
                        collect(Collectors.toList());
                HashSet<MenuItem> hashSet = new HashSet<>();

                filteredList.forEach(value -> { hashSet.add(value); });
                tableProducts.generateTable(hashSet);
                guiClient.setProductsTableModel(tableProducts.getTableModel());
            }

            if(comboBox.getSelectedIndex()==2) //search dupa calorii
            {   int key=Integer.parseInt(keyword);
                List<MenuItem> filteredList=  deliveryService.getAllProducts().stream().filter(p->p.getCalories()==key).
                        collect(Collectors.toList());
                HashSet<MenuItem> hashSet = new HashSet<>();

                filteredList.forEach(value -> { hashSet.add(value); });
                tableProducts.generateTable(hashSet);
                guiClient.setProductsTableModel(tableProducts.getTableModel());
            }

            if(comboBox.getSelectedIndex()==3) //search dupa proteine
            {   int key=Integer.parseInt(keyword);
                List<MenuItem> filteredList=  deliveryService.getAllProducts().stream().filter(p->p.getProtein()==key).
                        collect(Collectors.toList());
                HashSet<MenuItem> hashSet = new HashSet<>();

                filteredList.forEach(value -> { hashSet.add(value); });
                tableProducts.generateTable(hashSet);
                guiClient.setProductsTableModel(tableProducts.getTableModel());
            }

            if(comboBox.getSelectedIndex()==4) //search dupa fat
            {   int key=Integer.parseInt(keyword);
                List<MenuItem> filteredList=  deliveryService.getAllProducts().stream().filter(p->p.getFat()==key).
                        collect(Collectors.toList());
                HashSet<MenuItem> hashSet = new HashSet<>();

                filteredList.forEach(value -> { hashSet.add(value); });
                tableProducts.generateTable(hashSet);
                guiClient.setProductsTableModel(tableProducts.getTableModel());
            }

            if(comboBox.getSelectedIndex()==5) //search dupa sodium
            {   int key=Integer.parseInt(keyword);
                List<MenuItem> filteredList=  deliveryService.getAllProducts().stream().filter(p->p.getSodium()==key).
                        collect(Collectors.toList());
                HashSet<MenuItem> hashSet = new HashSet<>();

                filteredList.forEach(value -> { hashSet.add(value); });
                tableProducts.generateTable(hashSet);
                guiClient.setProductsTableModel(tableProducts.getTableModel());
            }

            if(comboBox.getSelectedIndex()==6) //search dupa price
            {   int key=Integer.parseInt(keyword);
                List<MenuItem> filteredList=  deliveryService.getAllProducts().stream().filter(p->p.getPrice()==key).
                        collect(Collectors.toList());
                HashSet<MenuItem> hashSet = new HashSet<>();

                filteredList.forEach(value -> { hashSet.add(value); });
                tableProducts.generateTable(hashSet);
                guiClient.setProductsTableModel(tableProducts.getTableModel());
            }
        }
    }
}

