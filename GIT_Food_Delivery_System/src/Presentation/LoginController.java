package Presentation;

import Accounts.Account;
import Accounts.AccountsBank;
import Accounts.AdminAccount;
import Accounts.ClientAccount;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController{
    private GUILogin guiLogin; //fereastra de logare
    private MainController mainController;
    private AccountsBank accountsBank;

    public LoginController(MainController mainController,GUILogin guiLogin)
    {
        this.guiLogin =guiLogin;
        this.mainController=mainController;

        accountsBank=new AccountsBank();

        guiLogin.setLoginButtonActionListener(new LoginListener());
        guiLogin.setRegisterButtonActionListener(new RegisterListener());

        //creez automat un cont de admin:user:admin pass:1
        Account admin=new AdminAccount("admin","1");
        accountsBank.registerNewAccount(admin);

        guiLogin.pack();
        guiLogin.setLocationRelativeTo(null);
        guiLogin.setVisible(true);

    }

    public AccountsBank getAccountsBank(){
        return accountsBank;
    }

    private class LoginListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            //logare in cont,daca contul nu exista,se asteapta introducere
            //de date corecte
            String userName= guiLogin.getUserName().getText();
            String pass= String.valueOf(guiLogin.getPassword().getPassword());
            System.out.println(userName+" "+pass);

            int idOfCurrentClient=accountsBank.loginAccount(userName,pass);
            if(idOfCurrentClient==-1) {
                guiLogin.showWrongDataMessage("Contul acesta nu exista!");
            return;}

            if(userName.equals("admin") && pass.equals("1"))
            {
                mainController.setGuiAdminVisible(true);
                mainController.setGuiLoginVisible(false);
            }
            else //inseamna ca e client
            {
                mainController.setGuiClientVisible(true);
                mainController.setGuiLoginVisible(false);
                mainController.setIdLoggedClient(idOfCurrentClient);
            }
            accountsBank.show();
        }
    }

    private class RegisterListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            //doar clientul isi poate crea un nou cont
            String userName= guiLogin.getUserName().getText();
            String pass= String.valueOf(guiLogin.getPassword().getPassword());
            Account clientAcc=new ClientAccount(userName,pass);
            //adaug noul cont de client in lista de conturi
            if(!accountsBank.registerNewAccount(clientAcc))
                guiLogin.showWrongDataMessage("Nu se poate crea contul.Numele apartine altui utilizator!");
        }
    }
}
