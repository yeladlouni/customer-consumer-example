import org.apache.commons.lang3.SerializationUtils;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class CustomerDeserializer implements Deserializer<Customer> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public Customer deserialize(String s, byte[] bytes) {
        return SerializationUtils.deserialize(bytes);
    }
}
