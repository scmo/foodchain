#include <ESP8266WiFi.h>
#include <WiFiClient.h>

bool lastVal=false;
unsigned long lastMeasurement = millis();
int steps = 0;

int LED_GREEN = D2;

void setup() {
  Serial.begin(115200);
  Serial.println("");

  // Sensors
  pinMode(D3, INPUT);

  // LEDs
  pinMode(LED_GREEN, OUTPUT);
  digitalWrite(LED_GREEN, LOW);

  // Wifi
  //WiFi.begin("WLAN-MOBILE", "6340baar");
  WiFi.begin("funky", "kjqf7200");
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

  // Check WiFi
  bool connected = (WiFi.status() == WL_CONNECTED);
  digitalWrite(LED_GREEN, connected);

  delay(10);
}
