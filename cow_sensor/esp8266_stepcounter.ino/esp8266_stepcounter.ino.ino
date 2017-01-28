#include <ESP8266WiFi.h>
#include <ESP8266HTTPClient.h>

bool lastVal=false;

// Time when last step was measured
unsigned long lastStepTimestamp = millis();

// Time when the switch was last open (for debouncing)
unsigned long lastOpenTimestamp = millis();

// Time when the last successful post was made
unsigned long lastPostTimestamp = millis();

// Counting of steps
int steps = 0;

int MIN_STEP_TIME = 500;    // min time that steps are apart
int MIN_OPEN_TIME = 10;     // min time that the switch must be open for a step
int LED_GREEN = D2;
String SERVER_ENDPOINT = "http://54.86.191.244/";
String COW_ID = "ELSA1337";

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
  //WiFi.begin("WLAN-MOBILE", "6340baar");
  WiFi.begin("funky", "kjqf7200");
}


void loop() {
  // Read for steps
  bool curVal = !digitalRead(D3);

  // Rising, edge: Remember time
  if (curVal == 1 && lastVal == 0) {
    lastOpenTimestamp = millis();
  }

  // Falling edge: Check for step
  // Must have been at least MIN_OPEN_TIME open
  // And the time since the last step is at least DEBOUNCE_TIME
  if (curVal == 0 && 
      lastVal == 1 &&
      (millis()-lastOpenTimestamp) > MIN_OPEN_TIME &&
      (millis()-lastStepTimestamp) > MIN_STEP_TIME) {

    // We have a step
    lastStepTimestamp = millis();
    steps++;
    Serial.println("Step registred. Now have " + String(steps) + " steps");
  }
  lastVal = curVal;

  // Get Status of WiFi
  bool connected = (WiFi.status() == WL_CONNECTED);
  digitalWrite(LED_GREEN, connected);

  // If we have connection and we counted some steps, POST and reset
  if (connected && (steps > 0)) {
    String payload = "{\"steps\":" + String(steps) + "," + 
      "\"duration\":" + String((millis() - lastPostTimestamp)/1000) + "" +
      "}";
    Serial.println("Posting..." + payload);

    http.begin(SERVER_ENDPOINT);
    http.addHeader("Content-Type", "application/json");
    int ret = http.POST(payload);

    if (ret < 0) {
      Serial.println(http.errorToString(ret).c_str());
    } else {
      Serial.println("Successful posted steps...");
      steps = 0;
      lastPostTimestamp = millis();
    }
    http.end();
  }

}
