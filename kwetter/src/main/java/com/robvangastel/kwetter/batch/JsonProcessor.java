package com.robvangastel.kwetter.batch;

import com.robvangastel.kwetter.domain.Tweet;

import javax.batch.api.chunk.ItemProcessor;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParserFactory;
import java.io.StringReader;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Rob on 7-4-2017.
 */

@Dependent
@Named("JsonProcessor")
public class JsonProcessor implements ItemProcessor {

    @Override
    public Object[] processItem(Object t) {
        String username = "";
        String message = "";
        Timestamp time = new Timestamp(System.currentTimeMillis());

        String json = (String) t;
        while (!json.startsWith("{") & json.length() > 0) {
            json = json.substring(1, json.length());
        }
        while (!json.endsWith("}") & json.length() > 0) {
            json = json.substring(0, json.length() - 1);
        }
        if (json.indexOf("}") <= json.indexOf("{")) {
            return new Object[]{null, null};
        }

        JsonParserFactory factory = Json.createParserFactory(null);
        JsonParser parser = factory.createParser(new StringReader(json));

        String key = "";
        String value = "";
        HashMap<String, String> map = new HashMap<>();
        while (parser.hasNext()) {
            switch (parser.next()) {
                case KEY_NAME:
                    key = parser.getString();
                    break;
                case VALUE_STRING:
                    value = parser.getString();
                    map.put(key, value);
                    break;
            }
        }

        username = map.get("username");
        message = map.get("message");
        time = new Timestamp(System.currentTimeMillis());

        Tweet tweet = new Tweet();
        tweet.setMessage(message);
        Object[] result = new Object[]{tweet, username};
        return result;
    }
}
