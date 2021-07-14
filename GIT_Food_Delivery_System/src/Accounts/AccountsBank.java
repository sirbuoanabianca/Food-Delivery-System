package Accounts;

import java.util.HashSet;
import java.util.stream.Collectors;

public class AccountsBank {
    private HashSet<Account> accounts;
    private int availableID; //ultimul id pentru client,la fiecare adaugare de cont de client,se increm id

    public AccountsBank() {
        accounts = new HashSet<>();
        availableID = 0;
    }

    public boolean registerNewAccount(Account a)
    {
        if(!accounts.add(a))
            return false;
        else
            if(a instanceof ClientAccount)
            {
                ((ClientAccount) a).setClientID(availableID);
                 availableID++;
            }
            return true;
    }

    public int loginAccount(String userName,String pass)
    {
        for(Account a:accounts)
        {
            if(a.getPassword().equals(pass) && a.getUserName().equals(userName)) {
                if (a instanceof ClientAccount)
                    return ((ClientAccount) a).getClientID();
                else
                    if (a instanceof AdminAccount)
                        return -2;
                else
                    return -1;
            }
        }
        return -1;
    }

    public ClientAccount getClientAccount(int clientId) {
        return (ClientAccount) accounts.stream()
                .filter(account -> account instanceof ClientAccount)
                .filter(account -> ((ClientAccount)account).getClientID()==clientId)
                .collect(Collectors.toList()).get(0);
    }

    public void show()
    {
        for(Account a:accounts)
            System.out.println(a);
        System.out.println("\n");
    }
}
