package via.sep4.data.webapi.model.loriot;

import via.sep4.data.webapi.util.Constants;

public class UpLink {
    private String cmd;
    private String EUI;
    private long ts;
    private boolean ack;
    private double fcnt;
    private int port;
    private String data;

    /**
     * This model is for the data that are received from the Loriot Network.
     */
    public UpLink(String EUI, long ts, boolean ack, double fcnt, int port, String data) {
        this.cmd = Constants.RECEIVE_COMMAND;
        this.EUI = EUI;
        this.ts = ts;
        this.ack = ack;
        this.fcnt = fcnt;
        this.port = port;
        this.data = data;
    }

    public String getCmd() {
        return cmd;
    }

    public String getEui() {
        return EUI;
    }

    public void setEui(String EUI) {
        this.EUI = EUI;
    }

    public long getTs() {
        return ts;
    }

    public boolean isAck() {
        return ack;
    }

    public double getFcnt() {
        return fcnt;
    }

    public int getPort() {
        return port;
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return "UpLinkMessage{" +
                "cmd='" + cmd + '\'' +
                ", EUI='" + EUI + '\'' +
                ", ts=" + ts +
                ", ack=" + ack +
                ", fcnt=" + fcnt +
                ", port=" + port +
                ", data='" + data + '\'' +
                '}';
    }
}
