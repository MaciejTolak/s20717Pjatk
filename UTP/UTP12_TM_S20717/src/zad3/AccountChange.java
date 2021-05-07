package zad3;

        import java.beans.PropertyChangeEvent;
        import java.beans.PropertyChangeListener;

public class AccountChange implements PropertyChangeListener {


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println(evt.getPropertyName() +
                ": Value changed from " + evt.getOldValue() +
                " to " + evt.getNewValue());

    }
}
