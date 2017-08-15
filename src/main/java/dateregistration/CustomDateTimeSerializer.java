package dateregistration;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;


import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by finawei on 8/15/17.
 */
public class CustomDateTimeSerializer extends JsonSerializer<LocalDate> {
    private static final DateTimeFormatter FORMATTER= DateTimeFormatter.ofPattern("yyyy-MM-dd");
    @Override
    public void serialize(LocalDate localDate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeString(FORMATTER.format(localDate));
    }

}
