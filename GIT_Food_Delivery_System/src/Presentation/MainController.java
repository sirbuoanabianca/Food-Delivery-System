package Presentation;

public class MainController {
    private GUIAdmin guiAdmin;
    private GUILogin guiLogin;
    private GUIClient guiClient;
    private int idLoggedClient;

    public MainController(GUIAdmin guiAdmin, GUILogin guiLogin,GUIClient guiClient) {
        this.guiAdmin = guiAdmin;
        this.guiLogin = guiLogin;
        this.guiClient=guiClient;
    }

    public void setIdLoggedClient(int idLoggedClient) {
        this.idLoggedClient = idLoggedClient;
    }

    public int getIdLoggedClient() {
        return idLoggedClient;
    }

    public void setGuiLoginVisible(boolean visibility)
    {
        guiLogin.setVisible(visibility);
    }

    public void setGuiAdminVisible(boolean visibility)
    {
        guiAdmin.pack();
        guiAdmin.setLocationRelativeTo(null);
        guiAdmin.setVisible(visibility);
    }

    public void setGuiClientVisible(boolean visibility)
    {
        guiClient.pack();
        guiClient.setLocationRelativeTo(null);
        guiClient.setVisible(visibility);
    }
}
