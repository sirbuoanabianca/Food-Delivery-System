package Accounts;

public class ClientAccount extends Account{
    private int clientID;
    public ClientAccount(String userName, String password) {
        super(userName, password);
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }
}
