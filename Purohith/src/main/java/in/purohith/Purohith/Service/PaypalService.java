package in.purohith.Purohith.Service;

import org.springframework.stereotype.Service;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

@Service
public class PaypalService {

    private final String CLIENT_ID = "AUyoOq9Xq6PO-Nkdt32KQatKH5zfA20EJt-KZLgjVeArD50anVMpoXzMEh4Dg7ksEIuIVCZnKJlIGV3J";
    private final String SECRET = "ENxHkqwmTc_hG9_4bj0nf49cvZdTUIeCTCvtk_1xwiyPNkEgQna_AgicsKgygYPPMbUKkIjScq_R-OJk";
    private final String BASE_URL = "https://sandbox.paypal.com"; // Sandbox 

    private String getAccessToken() throws Exception {
        String auth = Base64.getEncoder().encodeToString((CLIENT_ID + ":" + SECRET).getBytes());

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/v1/oauth2/token"))
                .header("Authorization", "Basic " + auth)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString("grant_type=client_credentials"))
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject json = new JSONObject(response.body());
        return json.getString("access_token");
    }

    public String createOrder(double amount, String currency) throws Exception {
        String accessToken = getAccessToken();

        JSONObject order = new JSONObject();
        order.put("intent", "CAPTURE");
        JSONObject purchaseUnit = new JSONObject();
        purchaseUnit.put("amount", new JSONObject()
                .put("currency_code", currency)
                .put("value", String.format("%.2f", amount))
        );
        order.put("purchase_units", new org.json.JSONArray().put(purchaseUnit));

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/v2/checkout/orders"))
                .header("Authorization", "Bearer " + accessToken)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(order.toString()))
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject json = new JSONObject(response.body());
        return json.getString("id"); // this is the PayPal order id
    }

    public String captureOrder(String orderId) throws Exception {
        String accessToken = getAccessToken();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/v2/checkout/orders/" + orderId + "/capture"))
                .header("Authorization", "Bearer " + accessToken)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject json = new JSONObject(response.body());
        return json.toString(); // full capture response
    }
}
