package via.sep4.data.webapi.model.loriot.actions;

import via.sep4.data.webapi.util.Constants;

public class DownLink {
    private String cmd;
    private String eui;
    private int port;
    private boolean confirmed;
    private String data;

    public DownLink(String eui, int port, boolean confirmed, String data) {
        this.cmd = Constants.SEND_COMMAND;
        this.eui = eui;
        this.port = port;
        this.confirmed = confirmed;
        this.data = data;
    }

    public String getCmd() {
        return cmd;
    }

    public String getEui() {
        return eui;
    }

    public void setEui(String eui) {
        this.eui = eui;
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
                ", EUI='" + eui + '\'' +
                ", port=" + port +
                ", confirmed=" + confirmed +
                ", data='" + data + '\'' +
                '}';
    }
}
