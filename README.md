# 💬 GeminiChat - Plugin hỏi đáp AI cho Minecraft

**GeminiChat** là một plugin Minecraft sử dụng mô hình AI Gemini của Google để người chơi có thể trò chuyện và đặt câu hỏi ngay trong game.

## 🚀 Tính năng

- Gửi câu hỏi tới AI Gemini bằng lệnh `/askai <câu hỏi>`
- Tùy chọn mô hình AI: `gemini-2.0-flash` (miễn phí 250 lượt/ngày) hoặc `gemini-2.5-flash` (miễn phí 200 lượt/ngày)
- Cấu hình API key và model dễ dàng qua file `config.yml`
- Phản hồi trực tiếp trong Minecraft chat
- Hoạt động mượt mà với Paper 1.18.2+

---

## 📦 Yêu cầu

- Máy chủ Minecraft sử dụng [Paper](https://papermc.io/)
- Java 17 trở lên
- Tài khoản Google Cloud đã bật API Gemini ([Xem hướng dẫn tại đây](https://aistudio.google.com/app/apikey))

---

## 🔧 Cài đặt

1. **Clone project** hoặc tải về từ GitHub:

    ```bash
    git clone https://github.com/lion3993vn/GeminiChat.git
    cd GeminiChat
    ```

2. **Cấu hình API key:**

    Plugin sẽ tự sinh file `config.yml` khi chạy lần đầu. Ví dụ:

    ```yaml
    # Nhập API key của bạn tại đây (lấy từ Google AI Studio)
    api-key: "YOUR_API_KEY"

    # Chọn model để sử dụng:
    # - gemini-2.0-flash: Miễn phí 250 request/ngày
    # - gemini-2.5-flash: Miễn phí 200 request/ngày
    model: "gemini-2.5-flash"
    ```

3. **Build plugin:**

    ```bash
    ./gradlew clean build
    ```

    Plugin sau khi build sẽ nằm tại:

    ```
    build/libs/GeminiChat-1.0-SNAPSHOT-all.jar
    ```

4. **Cài đặt vào server:**

    - Sao chép file `.jar` vào thư mục `plugins/` của server Minecraft.
    - Khởi động lại server.

---

## 💡 Cách sử dụng

- Trong game, sử dụng lệnh:

    ```
    /askai <câu hỏi của bạn>
    ```

    Ví dụ:

    ```
    /askai Minecraft là gì?
    ```

- AI sẽ phản hồi lại trực tiếp trong khung chat.

---

## 🛠 Lưu ý kỹ thuật

- Plugin sử dụng thư viện:
  - `OkHttp` để gửi HTTP request
  - `Gson` để xử lý JSON
- Plugin tự động format lại phản hồi AI để dễ đọc trong game
- API Key được load từ `config.yml` trong thư mục plugin

---

## 📜 Giấy phép

MIT License © 2025

---

## 🤝 Đóng góp

Bạn có thể mở `issue` hoặc `pull request` nếu muốn cải thiện plugin này. Mọi đóng góp đều được hoan nghênh!
