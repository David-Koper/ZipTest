import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;

import static org.assertj.core.api.Assertions.assertThat;

public class TestJson {
    ClassLoader classLoader = TestJson.class.getClassLoader();

    @Test
    void jsonTest() {

        InputStream inputStream = classLoader.getSystemResourceAsStream("Teana");
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(new InputStreamReader(inputStream), JsonObject.class);
        assertThat(jsonObject.get("name").getAsString()).isEqualTo("Teana");
        assertThat(jsonObject.get("four-wheel drive").getAsBoolean()).isEqualTo(false);
        assertThat(jsonObject.get("year").getAsInt()).isEqualTo(2012);
        assertThat(jsonObject.get("engine").getAsJsonObject().get("type").getAsString()).isEqualTo("petrol");
        assertThat(jsonObject.get("engine").getAsJsonObject().get("volume").getAsDouble()).isEqualTo(2.5);
    }
}
