/*****
 
 All the resources for this project:
 https://randomnerdtutorials.com/
 
*****/
#include <ESP8266WiFi.h>
#include <PubSubClient.h>
#include "DHT.h"

// Uncomment one of the lines bellow for whatever DHT sensor type you're using!
#define DHTTYPE DHT11   // DHT 11
//#define DHTTYPE DHT21   // DHT 21 (AM2301)
//#define DHTTYPE DHT22   // DHT 22  (AM2302), AM2321

// Change the credentials below, so your ESP8266 connects to your router
const char* ssid = "Tean";
const char* password = "123456788";

// MQTT broker credentials (set to NULL if not required)
const char* MQTT_username = "NULL"; 
const char* MQTT_password = "NULL"; 

// Change the variable to your Raspberry Pi IP address, so it connects to your MQTT broker
const char* mqtt_server = "192.168.43.12";
//For example
//const char* mqtt_server = "192.168.1.106";

// Initializes the espClient. You should change the espClient name if you have multiple ESPs running in your home automation system
WiFiClient espClient;
PubSubClient client(espClient);

// DHT Sensor - GPIO 4 = D2 on ESP-12E NodeMCU board
const int DHTPin = 4;

// Lamp - LED - GPIO 5 = D1 on ESP-12E NodeMCU board
// Lamp - LED - GPIO 16 = D0 on ESP-12E NodeMCU board
const int lampRight = 5;  
const int lampLeft = 16;  
const int lamptest = 2;

const int ldrPin = A0; // Chân analog A0 cho kết nối LDR
int ldrValue = 0;     // Biến lưu giá trị đo được từ LDR

// Initialize DHT sensor.
DHT dht(DHTPin, DHTTYPE);

// Timers auxiliar variables
long noww = millis();
long lastMeasure = 0;

// This functions connects your ESP8266 to your router
void setup_wifi() {
  delay(10);
  // We start by connecting to a WiFi network
  Serial.println();
  Serial.print("Connecting to ");
  Serial.println(ssid);
  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.println("");
  Serial.print("WiFi connected - ESP IP address: ");
  Serial.println(WiFi.localIP());
}

// This function is executed when some device publishes a message to a topic that your ESP8266 is subscribed to
// Change the function below to add logic to your program, so when a device publishes a message to a topic that 
// your ESP8266 is subscribed you can actually do something
void callback(String topic, byte* message, unsigned int length) {
  Serial.print("Message arrived on topic: ");
  Serial.print(topic);
  Serial.print(". Message: ");
  String messageTemp;
  
  for (int i = 0; i < length; i++) {
    Serial.print((char)message[i]);
    messageTemp += (char)message[i];
  }
  Serial.println();

  // Feel free to add more if statements to control more GPIOs with MQTT

  // If a message is received on the topic room/lamp, you check if the message is either on or off. Turns the lamp GPIO according to the message
  if(topic=="room/lamp"){
      Serial.print("Changing Room lamp to ");
      if(messageTemp == "onlampright"){
        //digitalWrite(lampRight, HIGH); được sử dụng để đặt trạng thái của chân lampRight là HIGH trong chương trình Arduino. Điều này 
        //thường được sử dụng để bật một thiết bị ngoại vi (ví dụ: đèn LED hoặc bóng đèn) được kết nối đến chân lampRight
        digitalWrite(lampRight, HIGH);
        Serial.print("On Lamp Right");
        Serial.println();
      }
      else if(messageTemp == "offlampright"){
        digitalWrite(lampRight, LOW);
        Serial.print("Off Lamp Right");
        Serial.println();
      }
      else if(messageTemp == "offlampleft"){
        digitalWrite(lampLeft, LOW);
        Serial.print("Off Lamp Left");
        Serial.println();
      }
      else if(messageTemp == "onlampleft"){
        digitalWrite(lampLeft, HIGH);
        Serial.print("On Lamp Left");
        Serial.println();
      }
      else if(messageTemp == "onlamptest"){
        digitalWrite(lamptest, HIGH);
        Serial.print("On Lamp test");
        Serial.println();
      }
      else if(messageTemp == "offlamptest"){
        digitalWrite(lamptest, LOW);
        Serial.print("Off Lamp test");
        Serial.println();
      }
  }

}

// This functions reconnects your ESP8266 to your MQTT broker
// Change the function below if you want to subscribe to more topics with your ESP8266 
void reconnect() {
  // Loop until we're reconnected
  while (!client.connected()) {
    Serial.print("Attempting MQTT connection...");
    // Attempt to connect
    /*
     YOU MIGHT NEED TO CHANGE THIS LINE, IF YOU'RE HAVING PROBLEMS WITH MQTT MULTIPLE CONNECTIONS
     To change the ESP device ID, you will have to give a new name to the ESP8266.
     Here's how it looks:
       if (client.connect("ESP8266Client")) {
     You can do it like this:
       if (client.connect("ESP1_Office")) {
     Then, for the other ESP:
       if (client.connect("ESP2_Garage")) {
      That should solve your MQTT multiple connections problem
    */
    if (client.connect("ESP8266Client", MQTT_username, MQTT_password)) {
      Serial.println("connected");  
      // Subscribe or resubscribe to a topic
      // You can subscribe to more topics (to control more LEDs in this example)
      client.subscribe("room/lamp");
    } else {
      Serial.print("failed, rc=");
      Serial.print(client.state());
      Serial.println(" try again in 5 seconds");
      // Wait 5 seconds before retrying
      delay(5000);
    }
  }
}

// The setup function sets your ESP GPIOs to Outputs, starts the serial communication at a baud rate of 115200
// Sets your mqtt broker and sets the callback function
// The callback function is what receives messages and actually controls the LEDs
void setup() {
  
  pinMode(lampRight, OUTPUT);
  pinMode(lampLeft, OUTPUT);
  pinMode(lamptest, OUTPUT);
  dht.begin();
   Serial.begin(9600); // Bắt đầu giao tiếp với Serial Monitor
  Serial.begin(115200);
  randomSeed(analogRead(0));
  setup_wifi();
  //client.setServer(server, port): Đặt máy chủ MQTT và cổng để kết nối đến.
  client.setServer(mqtt_server, 1883);
  //Dòng code client.setCallback(callback); được đặt trong hàm setup() của chương trình Arduino
  //để thiết lập một hàm callback (callback function) cho đối tượng MQTT client. Hàm callback này sẽ được gọi mỗi khi một thông điệp MQTT mới được nhận từ máy chủ MQTT
  client.setCallback(callback);

}

// For this project, you don't need to change anything in the loop function. Basically it ensures that you ESP is connected to your broker
void loop() {
//Trả về true nếu client đang kết nối với máy chủ MQTT, false nếu không.
  if (!client.connected()) {
    reconnect();
  }
  // Gọi trong hàm loop() để duy trì kết nối MQTT và xử lý thông điệp MQTT. Hàm này cần được gọi định kỳ để đảm bảo rằng thông điệp mới có thể được xử lý.
  if(!client.loop())
    client.connect("ESP8266Client", MQTT_username, MQTT_password);

  //lấy giá trị thời gian hiện tại bằng cách sử dụng hàm 
  noww = millis();
  // Publishes new temperature and humidity every 30 seconds
  // đảm bảo rằng chúng ta sẽ đo đạc lại sau ít nhất 2 giây kể từ lần đo trước đó.
  if (noww - lastMeasure > 2000) {
    lastMeasure = noww;
    // Sensor readings may also be up to 2 seconds 'old' (its a very slow sensor)
    float humidity = dht.readHumidity();
    // Read temperature as Celsius (the default)
    float temperatureC = dht.readTemperature();
    // Read temperature as Fahrenheit (isFahrenheit = true)
    float temperatureF = dht.readTemperature(true);

    // Check if any reads failed and exit early (to try again).
    //Dòng này kiểm tra xem các đọc đo được có hợp lệ không. Nếu bất kỳ giá trị nào là 
    //"NaN" (Not-a-Number) tức là không đọc được từ cảm biến (thường xảy ra khi có lỗi trong đọc dữ liệu từ cảm biến), 
    //thì chương trình sẽ in ra thông báo "Failed to read from DHT sensor!" 
if (isnan(humidity) || isnan(temperatureC) || isnan(temperatureF)) {
      Serial.println("Failed to read from DHT sensor!");
      return;
    }
  //client.publish(topic, payload): Gửi một thông điệp MQTT đến máy chủ với chủ đề (topic) và nội dung (payload) cụ thể.
    // Publishes Temperature and Humidity values
     ldrValue = analogRead(ldrPin); // Đọc giá trị từ LDR
    int randomNumber = random(101); 
    // Lấy thời gian hiện tại
    float khois = 100;
    client.publish("room/temperature", (String(temperatureC)+","+String(humidity)+","+String(ldrValue)+","+String(randomNumber)).c_str());
    // client.publish("room/humidity", ("humidity: "+String(humidity)).c_str());
    // //Uncomment to publish temperature in F degrees
    // client.publish("room/temperature", ("temperatureF: "+String(temperatureF)).c_str());
    //  ldrValue = analogRead(ldrPin); // Đọc giá trị từ LDR
    //  client.publish("room/light", ("light: "+String(ldrValue)).c_str());
    // Serial.print("Giá trị đo được: ");
    // Serial.println(ldrValue); // In giá trị đo được lên Serial Monitor
    
    Serial.print("Humidity: ");
    Serial.print(humidity);
    Serial.println(" %");
    Serial.print("Temperature: ");
    Serial.print(temperatureC);
    Serial.println(" ºC");
    Serial.print(temperatureF);
    Serial.println(" ºF");
  }
}
