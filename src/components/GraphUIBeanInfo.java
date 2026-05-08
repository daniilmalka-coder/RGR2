package components;
import java.beans.*;

public class GraphUIBeanInfo extends SimpleBeanInfo {
    @Override
    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            return new PropertyDescriptor[] {
                    new PropertyDescriptor("color", GraphUI.class)
            };
        } catch (IntrospectionException e) { return null; }
    }
}