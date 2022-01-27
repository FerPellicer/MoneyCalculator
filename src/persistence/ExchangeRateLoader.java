package persistence;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import model.CurrencySet;
import model.ExchangeRate;
import model.ExchangeRateSet;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class ExchangeRateLoader {

    public ExchangeRateSet load(CurrencySet c) throws IOException, ParseException {
        ExchangeRateSet set = new ExchangeRateSet();
        for (int i = 0; i < c.size(); i++) {
            for (int j = 0; j < c.size(); j++) {
                if (c.get(i).equals(c.get(j))) {
                    set.add(new ExchangeRate(c.get(i), c.get(i), 1));
                } else {
                    
                    set.add(new ExchangeRate(c.get(i), c.get(j), rate(c.get(i).getCode(), c.get(j).getCode())));
                    
                }
            }
        }

        return set;
    }

    private float rate(String from, String to) {
    
        return MyJsonReader.readJsonFromUrl(from, to);
        

    }


}
