import Business.DeliveryService;
import Presentation.*;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {


        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }

        GUIAdmin guiAdmin=new GUIAdmin();
        GUILogin guiLogin=new GUILogin();
        GUIClient guiClient=new GUIClient();
        MainController mainController=new MainController(guiAdmin,guiLogin,guiClient);

        guiLogin.pack();
        guiLogin.setLocationRelativeTo(null);
        DeliveryService delivery=new DeliveryService();
        delivery.readFromCSV();
        LoginController loginController=new LoginController(mainController,guiLogin);
        AdminController adminController=new AdminController(mainController,guiAdmin,delivery, loginController.getAccountsBank());
        ClientController clientController=new ClientController(mainController,guiClient,delivery);
    }
}
