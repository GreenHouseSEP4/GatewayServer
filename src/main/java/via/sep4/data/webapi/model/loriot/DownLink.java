package via.sep4.data.webapi.model.loriot;

import via.sep4.data.webapi.util.Constants;

/**
 * This model is for the commands that are sent to the Loriot Network.
 */
public class DownLink {
    private String cmd;
    private String EUI;
    private int port;
    private boolean confirmed;
    private String data;

    public DownLink(String eui, int port, boolean confirmed, String data) {
        this.cmd = Constants.SEND_COMMAND;
        this.EUI = eui;
        this.port = port;
        this.confirmed = confirmed;
        this.data = data;
    }

    public String getCmd() {
        return cmd;
    }

    public String getEUI() {
        return EUI;
    }

    public void setEUI(String EUI) {
        this.EUI = EUI;
    }

    public int getPort() {
        return port;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return "DownLinkMessage{" +
                "cmd='" + cmd + '\'' +
                ", EUI='" + EUI + '\'' +
                ", port=" + port +
                ", confirmed=" + confirmed +
                ", data='" + data + '\'' +
                '}';
    }
}
