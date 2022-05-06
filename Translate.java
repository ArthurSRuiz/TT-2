import java.io.*;
import java.net.*;
import java.util.*;
import com.google.gson.*;
import com.squareup.okhttp.*;

public class Translate {
    private static String Key = "9546f57e79e6410388cb4c3bf540ab54";

    private static String location = "westus2";

    HttpUrl url = new HttpUrl.Builder()
        .scheme("https")
        .host("api.cognitive.microsofttranslator.com")
        .addPathSegment("/translate")
        .addQueryParameter("api-version" "3.0")
        .addQueryParameter("from" "en")
        .addQueryParameter("to" "de")
        .addQueryParameter("to" "it")
        .build();

    OkHttpClient client = new OkHttpClient();

    public String Post() throws IOException {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType
                "[{"Text": "Hello World!"}]");
        Request request = new Request.Builder().url(url).post(body)
                .addHeader("Ocp-Apim-Subscription-Key", Key)
                .addHeader("Ocp-Apim-Subscription-Region", location)
                .addHeader("Content-type" "application/json")
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static String prettify(String json_text) {
        JsonParser parser = new JsonParser();
        JsonElement json = parser.parse(json_text);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(json);
    }

    public static void main(String[] args) {
        try {
            Translate translateRequest = new Translate();
            String response = translateRequest.Post();
            System.out.println(prettify(response));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}