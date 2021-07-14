package Presentation;

import Accounts.AccountsBank;
import Business.*;
import Business.MenuItem;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

public class AdminController {
    private GUIAdmin guiAdmin;//fereastra care se deschide doar pt admin
    private MainController mainController;//controleaza care fereastra se va deschide
    private DeliveryService deliveryService;
    private Table table;
    private Table tableAvailableProducts;
    private Table tableAddedProducts;
    private ArrayList<MenuItem> selectedItems;
    private AccountsBank accountsList;

    public AdminController(MainController mainController,GUIAdmin guiAdmin,DeliveryService deliveryService,AccountsBank accountsBank) {
        this.guiAdmin =guiAdmin;
        this.mainController=mainController;

        this.deliveryService=deliveryService;
        table=new Table();
        tableAvailableProducts=new Table();
        tableAddedProducts=new Table();
        selectedItems=new ArrayList<>();
        accountsList=accountsBank;

        refreshTable();

        guiAdmin.setTabsChangeListener(new TabsListener());
        guiAdmin.setAddButtonActionListener(new AddListener());
        guiAdmin.setSearchButtonActionListener(new SearchListener());
        guiAdmin.setDeleteButtonActionListener(new DeleteListener());
        guiAdmin.setUpdateButtonActionListener(new UpdateListener());
        guiAdmin.setAddTheSelectedProductButtonActionListener(new AddSelProdListener());
        guiAdmin.setCreateNewMenuButtonActionListener(new CreateMenuListener());
        guiAdmin.setSearchOrdersByTimeButtonActionListener(new SearchOrdersByTimeListener());
        guiAdmin.setSearchProductsButtonActionListener(new MoreOrderedProductsListener());
        guiAdmin.setSearchClientsButtonActionListener(new TopClientsListener());
        guiAdmin.setSearchProductsOrderedByDayButtonActionListener(new ProductsOrderedByDayListener());

        guiAdmin.setLogoutButtonActionListener(new LogoutListener());
    }


    private void refreshTable()
    {
        table.generateTable(deliveryService.getAllProducts());
        guiAdmin.setMainTableModel(table.getTableModel());
    }

    private void refreshTableAvailableProducts()
    {
        tableAvailableProducts.generateTable(deliveryService.getAllProducts());
        guiAdmin.setTableAvailableProductsModel(tableAvailableProducts.getTableModel());
    }

    private class TabsListener implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {

            JTabbedPane t = (JTabbedPane) e.getSource();

            if (t.getSelectedIndex() == 0) //tabul pentru add/delete/update produs
            {//daca e selectat,se face update la tabel
                refreshTable();
            }

            if (t.getSelectedIndex() == 1) //tabul pentru creare produs compus
            {//daca e selectat,se face update la tabel
                refreshTableAvailableProducts();
                guiAdmin.setTableAddedProductsModel(tableAddedProducts.getTableModel());
            }
        }
    }

    private class AddListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            String title=guiAdmin.getProductTitle().getText();
            String rating=guiAdmin.getRating().getText();
            String calories=guiAdmin.getCalories().getText();
            String protein=guiAdmin.getProtein().getText();
            String fat=guiAdmin.getFat().getText();
            String sodium=guiAdmin.getSodium().getText();
            String price=guiAdmin.getPrice().getText();

            BaseProduct b=new BaseProduct(title,Double.parseDouble(rating),
                    Integer.parseInt(calories),Integer.parseInt(protein),
                    Integer.parseInt(fat),Integer.parseInt(sodium),Integer.parseInt(price));

            deliveryService.addProduct(b);
            refreshTable();
        }
    }

    private class SearchListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            String title=guiAdmin.getProductTitle().getText();
            MenuItem searched=deliveryService.searchProductByName(title);
            if(! searched.getTitle().equals("notfound"))
            {//creez o alta lista in care plasez doar obiectul cautat
                HashSet<MenuItem> found=new HashSet<>();
                found.add(searched);
                table.generateTable(found);
                guiAdmin.setMainTableModel(table.getTableModel());
            }
            else
                guiAdmin.showWrongDataMessage("Produsul cautat nu exista!");

        }
    }


    private class DeleteListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            int noSelRowProduct=guiAdmin.getMainTable().getSelectedRow();
            String title = (String) guiAdmin.getMainTable().getValueAt(noSelRowProduct, 0);

            if(!deliveryService.deleteProduct(title))
                guiAdmin.showWrongDataMessage("Produsul pe care doriti sa il stergeti nu exista!");

            refreshTable();
        }
    }

    private class UpdateListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            int noSelRowProduct=guiAdmin.getMainTable().getSelectedRow();
            String title = (String) guiAdmin.getMainTable().getValueAt(noSelRowProduct, 0);
            String rating = (String) guiAdmin.getMainTable().getValueAt(noSelRowProduct, 1);
            String calories = (String) guiAdmin.getMainTable().getValueAt(noSelRowProduct, 2);
            String proteins = (String) guiAdmin.getMainTable().getValueAt(noSelRowProduct, 3);
            String fat = (String) guiAdmin.getMainTable().getValueAt(noSelRowProduct, 4);
            String sodium = (String) guiAdmin.getMainTable().getValueAt(noSelRowProduct, 5);
            String price = (String) guiAdmin.getMainTable().getValueAt(noSelRowProduct, 6);

            BaseProduct b=new BaseProduct(title,Double.parseDouble(rating),
                    Integer.parseInt(calories),Integer.parseInt(proteins),
                    Integer.parseInt(fat),Integer.parseInt(sodium),Integer.parseInt(price));

           if(!deliveryService.modifyProduct(b))
               guiAdmin.showWrongDataMessage("Produsele compuse nu pot fi modificate!");

            refreshTable();

        }
    }

    private class AddSelProdListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            int noSelRowProduct=guiAdmin.getTableAvailableProducts().getSelectedRow();
            String title = (String) guiAdmin.getTableAvailableProducts().getValueAt(noSelRowProduct, 0);
            String rating = (String) guiAdmin.getTableAvailableProducts().getValueAt(noSelRowProduct, 1);
            String calories = (String) guiAdmin.getTableAvailableProducts().getValueAt(noSelRowProduct, 2);
            String proteins = (String) guiAdmin.getTableAvailableProducts().getValueAt(noSelRowProduct, 3);
            String fat = (String) guiAdmin.getTableAvailableProducts().getValueAt(noSelRowProduct, 4);
            String sodium = (String) guiAdmin.getTableAvailableProducts().getValueAt(noSelRowProduct, 5);
            String price = (String) guiAdmin.getTableAvailableProducts().getValueAt(noSelRowProduct, 6);

            BaseProduct b=new BaseProduct(title,Double.parseDouble(rating),
                    Integer.parseInt(calories),Integer.parseInt(proteins),
                    Integer.parseInt(fat),Integer.parseInt(sodium),Integer.parseInt(price));

            selectedItems.add(b);
            //update tabelul din dreapta care contine produsele selectate
            tableAddedProducts.addRow(b);
            guiAdmin.setTableAddedProductsModel(tableAddedProducts.getTableModel());
            refreshTableAvailableProducts();
        }
    }

    private class CreateMenuListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
           String menuName=guiAdmin.getMenuName().getText();
           CompositeProduct compositeProduct=new CompositeProduct(menuName,selectedItems);
           //adaugare produs nou compus in lista cu toate produsele
           deliveryService.addProduct(compositeProduct);
            //golire arraylist pentru retinere temporara produse selectate
            selectedItems=new ArrayList<>();
            //golire tabel din dreapta
            DefaultTableModel d=(DefaultTableModel)tableAddedProducts.getTableModel();
            d.setRowCount(0);
            refreshTableAvailableProducts();
        }
    }

    private class LogoutListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            mainController.setGuiAdminVisible(false);
            mainController.setGuiLoginVisible(true);
        }
    }

    /**RAPORTURI*/
    private class SearchOrdersByTimeListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {

            int startHour=Integer.parseInt(guiAdmin.getStart().getText());
            int endHour=Integer.parseInt(guiAdmin.getEnd().getText());
            DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"); // or "hh:mm" for 12 hour format
            StringBuilder productList=new StringBuilder();

            deliveryService.getOrders().keySet().stream()
                    .filter(order ->
                            {
                                try {
                                    return sdf.parse(order.getOrderDate()).getHours() >= startHour
                                            &&sdf.parse(order.getOrderDate()).getHours() <= endHour;
                                } catch (ParseException exception) { exception.printStackTrace(); }
                                return false;
                            })
                    .forEach(order ->
                    {
                        System.out.println(order);
                        System.out.println(deliveryService.getProductList(order));
                        productList.append(order).append(deliveryService.getProductList(order)).append("\n\n");
                    });
            guiAdmin.setTextAreaForReport(productList.toString());
        }
    }

    private class MoreOrderedProductsListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {

            int minNumber=Integer.parseInt(guiAdmin.getMinNumber().getText());
            HashMap<MenuItem,Integer> o=new HashMap<>();
            deliveryService.getOrders().values().forEach(
                    list -> list.forEach(menuItem -> {
                        if(o.containsKey(menuItem))
                            o.replace(menuItem,o.get(menuItem)+1);
                        else
                            o.put(menuItem,1);
                    })
            );

            StringBuilder sb=new StringBuilder();
            sb.append("Produsele comandate mai mult de ").append(minNumber).append(" ori\n");
            o.keySet().stream()
                    .filter(p -> o.get(p) >= minNumber)
                    .forEach(p-> sb.append(p).append("\nNr de aparitii: ").append(o.get(p)).append("\n\n"));
            guiAdmin.setTextAreaForReport(sb.toString());
        }
    }

    private class TopClientsListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {

            int nbOfTime=Integer.parseInt(guiAdmin.getNbOfTimes().getText());
            int minValue=Integer.parseInt(guiAdmin.getMinPrice().getText());
            HashMap<Integer,Integer> clNbOrders=new HashMap<>();
            deliveryService.getOrders().keySet().forEach(o ->
                    {
                   if(deliveryService.getTotalPrice(o)>=minValue){
                       if(clNbOrders.containsKey(o.getClientID()))
                           clNbOrders.replace(o.getClientID(),clNbOrders.get(o.getClientID())+1);
                       else
                           clNbOrders.put(o.getClientID(),1);
                   }
            }
            );

            StringBuilder sb=new StringBuilder();
            sb.append("Clientii cu numar de comenzi mai mare sau egal decat "+nbOfTime+" si valoare comanda>="+minValue+"\n");

            clNbOrders.keySet().stream().filter(client->
                    clNbOrders.get(client)>=nbOfTime)
                    .forEach( client->
                            sb.append("Nume client : ").append(accountsList.getClientAccount(client).getUserName())
                            .append(" id client : ").append(client).append("\nNumar comenzi = ")
                                    .append(clNbOrders.get(client)).append("\n\n")

                    );

            guiAdmin.setTextAreaForReport(sb.toString());
        }
    }
    private class ProductsOrderedByDayListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            String date=guiAdmin.getDate().getText();
            HashMap<MenuItem,Integer> o=new HashMap<>();/**are cheia produs si valoarea numarul de comenzi corespunzator*/
            DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            DateFormat sd = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

             deliveryService.getOrders().keySet().stream().filter(order->
             {
                 try { return sdf.format(sd.parse(order.getOrderDate())).equals(date); }
                 catch (ParseException exception) { exception.printStackTrace(); }
                 return false;
             })
                     /**pana aici comenzile sunt filtrate dupa data*/
                     .map(order -> deliveryService.getProductList(order))
                     .forEach(
                      /**se parcurge lista filtrata si se incrementeaza acolo unde un produs apare de mai multe ori*/
                             list -> list.forEach(menuItem -> {
                                 if(o.containsKey(menuItem))
                                     o.replace(menuItem,o.get(menuItem)+1);
                                 else
                                     o.put(menuItem,1);
                     }
                     ));
            StringBuilder sb=new StringBuilder();
            sb.append("Produsele comandate din ziua specificata :\n");

            o.keySet().forEach(menuItem ->
                    sb.append(menuItem.getTitle()).append(" cu numar comenzi= ").append(o.get(menuItem)).append("\n\n")
                    );

            guiAdmin.setTextAreaForReport(sb.toString());


        }
    }



}
