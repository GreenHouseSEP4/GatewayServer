package via.sep4.data.webapi.model.loriot.actions;

import via.sep4.data.webapi.model.Device;
import via.sep4.data.webapi.util.Constants;

public class DownLink {
    private String cmd;
    private String EUI;
    private int port;
    private boolean confirmed;
    private String data;

    public DownLink(int port, boolean confirmed, String data) {
        this.cmd = Constants.SEND_COMMAND;
        this.EUI = Constants.EUI;
        this.port = port;
        this.confirmed = confirmed;
        this.data = data;
    }
    public DownLink(int port, boolean confirmed, String data, Device device) {
        this.cmd = Constants.SEND_COMMAND;
        this.EUI = device.getEUI();
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
