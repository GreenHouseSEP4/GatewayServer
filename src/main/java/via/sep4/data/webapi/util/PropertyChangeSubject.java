package via.sep4.data.webapi.util;

import java.beans.PropertyChangeListener;

public interface PropertyChangeSubject {
    void addPropertyChangeListener(String eventName, PropertyChangeListener listener);
    //void addPropertyChangeListener(PropertyChangeListener listener);
    // void removePropertyChangeListener(String eventName, PropertyChangeListener listener);
    // void removePropertyChangeListener(PropertyChangeListener listener);
}
