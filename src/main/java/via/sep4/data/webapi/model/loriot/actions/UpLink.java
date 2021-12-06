package via.sep4.data.webapi.model.loriot.actions;

public class UpLink {
    private String cmd;
    private String EUI;
    private long ts;
    private boolean ack;
    private double fcnt;
    private int port;
    private String data;

    public UpLink(long ts, boolean ack, double fcnt, int port, String data) {
        this.cmd = "rx";
        this.EUI = "9276B3CF3B069355";
        this.ts = ts;
        this.ack = ack;
        this.fcnt = fcnt;
        this.port = port;
        this.data = data;
    }

    public String getCmd() {
        return cmd;
    }

    public String getEUI() {
        return EUI;
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
