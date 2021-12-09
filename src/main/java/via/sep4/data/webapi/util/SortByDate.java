package via.sep4.data.webapi.util;

import via.sep4.data.webapi.model.SensorData;

import java.util.Comparator;

public class SortByDate implements Comparator<SensorData> {
    @Override
    public int compare(SensorData o1, SensorData o2) {
        return o1.getDate().compareTo(o2.getDate());
    }
}
