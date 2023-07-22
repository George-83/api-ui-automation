package testData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class Variables {
    public static String SCHEMA = "https:";
    public static String BASEHOST = "//swapi.dev";
    public static String PATH = "/api/people/1/";
    public static String SCHEMA_BASEHOST_PATH = SCHEMA + BASEHOST + PATH;
    public static Map<String, String> queryParameters = Map.of(
            "visitorUuid", "8485f7ea-f2e7-4478-aff3-e8fe3b0f3cf0",
            "cityUuid", "deb1d05a-71ce-40d1-b726-6ba85d70d58f",
            "categoryId", "televizory",
            "sortType", "price_asc",
            "limit", "19"
    );
    public static String URL = "https://kcentr.ru/content-service/api/desktop/v1/products";
}
