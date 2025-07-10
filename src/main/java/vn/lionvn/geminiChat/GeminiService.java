package vn.lionvn.geminiChat;

import com.google.gson.*;
import okhttp3.*;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

public class GeminiService {
    private static String apiKey;
    private static String model;
    private static String URL;
    private static final OkHttpClient client = new OkHttpClient();
    private static final MediaType JSON = MediaType.get("application/json");

    public static void initialize(FileConfiguration config) {
        apiKey = config.getString("api-key");
        model = config.getString("model", "gemini-2.5-flash");
        URL = "https://generativelanguage.googleapis.com/v1beta/models/" + model + ":generateContent";

        Bukkit.getLogger().info("[GeminiChat] Đang dùng model: " + model);
        Bukkit.getLogger().info("[GeminiChat] API endpoint: " + URL);
    }

    public static String askGemini(String prompt) {
        try {
            // Tạo body JSON theo spec mới
            JsonObject part = new JsonObject();
            part.addProperty("text", prompt);

            JsonArray parts = new JsonArray();
            parts.add(part);

            JsonObject content = new JsonObject();
            content.add("parts", parts);

            JsonArray contents = new JsonArray();
            contents.add(content);

            JsonObject requestBody = new JsonObject();
            requestBody.add("contents", contents);

            // Gửi request
            Request request = new Request.Builder()
                    .url(URL)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("X-goog-api-key", apiKey)
                    .post(RequestBody.create(requestBody.toString(), JSON))
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful() || response.body() == null)
                    return "Gemini không phản hồi.";

                JsonObject json = JsonParser.parseString(response.body().string()).getAsJsonObject();

                String text = json.getAsJsonArray("candidates")
                        .get(0).getAsJsonObject()
                        .getAsJsonObject("content")
                        .getAsJsonArray("parts")
                        .get(0).getAsJsonObject()
                        .get("text").getAsString();

                return formatMarkdownForChat(text);

            }

        } catch (Exception e) {
            System.err.println("Gemini API error: " + e.getMessage());
            return "Lỗi khi kết nối AI.";
        }


    }

    public static String formatMarkdownForChat(String text) {
        // Bold: **text** => §ltext§r
        text = text.replaceAll("\\*\\*(.*?)\\*\\*", "§l$1§r");

        // Italic: *text* => §o$1§r (but avoid ** already handled above)
        text = text.replaceAll("(?<!\\*)\\*(?!\\*)(.*?)\\*(?!\\*)", "§o$1§r");

        // Code block hoặc inline code (optional): `text` => §7text§r
        text = text.replaceAll("`(.*?)`", "§7$1§r");

        // Bullet points (optional): - item => • item
        text = text.replaceAll("(?m)^- ", "• ");

        // Nếu nhiều dòng, bạn có thể giữ \n, hoặc cắt dòng nhỏ lại
        return text;
    }

}
