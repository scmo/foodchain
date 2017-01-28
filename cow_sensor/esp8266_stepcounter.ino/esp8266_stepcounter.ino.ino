#include <ESP8266WiFi.h>
#include <ESP8266HTTPClient.h>

bool lastVal=false;
unsigned long lastMeasurement = millis();
int steps = 0;

int LED_GREEN = D2;

HTTPClient http;

void setup() {
  Serial.begin(115200);
  Serial.println("");

  // Sensors
  pinMode(D3, INPUT);

  // LEDs
  pinMode(LED_GREEN, OUTPUT);
  digitalWrite(LED_GREEN, LOW);

  // Wifi
  WiFi.begin("WLAN-MOBILE", "6340baar");
  //WiFi.begin("funky", "kjqf7200");
}


void loop() {
  // Read for steps
  bool curVal = !digitalRead(D3);

  if (curVal != lastVal && (millis()-lastMeasurement > 500)) { // value changed...
    if (curVal == 0) {     // ...and balls touch, we have a step ;-)
      lastMeasurement = millis();
      steps++;
      Serial.println(steps);
    }
  }
  lastVal = curVal;

  // Get Status of WiFi
  bool connected = (WiFi.status() == WL_CONNECTED);
  digitalWrite(LED_GREEN, connected);

  // If we have connection and we counted some steps, POST
  if (connected && (steps > 0)) {
    Serial.println("Posting...");

    http.begin("http://54.86.191.244/");
    http.addHeader("Content-Type", "application/json");
    int ret = http.POST("{\"steps\": " + String(steps) + "}");
    
    if (ret < 0) {
      Serial.println(http.errorToString(ret).c_str());
    } else {
      Serial.println("Successful posted steps...");
      steps = 0;  
    }
    http.end();
  }

}
