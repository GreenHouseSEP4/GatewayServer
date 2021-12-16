package via.sep4.data.webapi.service.remote;

public interface RemoteService {

    void setWindow(String eui, int commandPercentage);
    void setWater(String eui, int waterValue);
    void setLight(String eui, int lightValue);
}
