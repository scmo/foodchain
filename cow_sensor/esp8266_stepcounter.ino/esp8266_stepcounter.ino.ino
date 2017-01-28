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
int steps      = 0;

int MIN_STEP_TIME      = 500;    // min time that steps are apart
int MIN_OPEN_TIME      = 10;     // min time that the switch must be open for a step
int LED_GREEN          = D2;
int LED_WHITE          = D1;
int LED_RED            = D6;
int PIN_READ           = D4;
String COW_ID          = "elsa-384756";
String SERVER_ENDPOINT = "http://192.168.43.197:8090/cow/" + COW_ID + "/movement-measurement";
//String SERVER_ENDPOINT = "http://54.86.191.244/cow/" + COW_ID + "/movement-measurement";
String WIFI_SSID       = "leyla";
String WIFI_PASSWORD   = "woahalaaha1879";


HTTPClient http;

void setup() {
  Serial.begin(115200);
  Serial.println("");

  // Sensors
  pinMode(PIN_READ, INPUT);

  // LEDs
  pinMode(LED_GREEN, OUTPUT);
  digitalWrite(LED_GREEN, LOW);
  pinMode(LED_WHITE, OUTPUT);
  digitalWrite(LED_WHITE, LOW);
  pinMode(LED_RED, OUTPUT);
  digitalWrite(LED_RED, LOW);

  // Wifi
  WiFi.begin(WIFI_SSID.c_str(), WIFI_PASSWORD.c_str());
}


void loop() {
  // Get Status of WiFi
  bool connected = (WiFi.status() == WL_CONNECTED);
  digitalWrite(LED_GREEN, connected);

  // Read for steps
  bool curVal = !digitalRead(PIN_READ);
  Serial.println(curVal);
  bool stepEncountered = false;

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
    stepEncountered = true;
    Serial.println("Step registred. Now have " + String(steps) + " steps");

  }
  lastVal = curVal;

  // Turn off step led after some time
  if ((millis() - lastPostTimestamp) > 500) {
    digitalWrite(LED_WHITE, LOW);
  }

  // If we have connection and we counted some steps, POST and reset
  if (connected && (steps > 0)) {
    // Type of step. If we have 1 step from this iteration: inside
    int steptype = (steps== 1 && stepEncountered) ? 1 : 0;
    
    String payload = "{\"steps\":" + String(steps) + "," + 
      "\"duration\":" + String((millis() - lastPostTimestamp)/1000) + "," +
      "\"steptype\":" + String(steptype) +
      "}";
    Serial.println("Posting..." + payload);

    // Make POST request
    http.begin(SERVER_ENDPOINT);
    http.addHeader("Content-Type", "application/json");
    int ret = http.POST(payload);

    if (ret < 0) {
      Serial.println(http.errorToString(ret).c_str());
      digitalWrite(LED_RED, HIGH);
      delay(300);
      digitalWrite(LED_RED, LOW);
      delay(2000);
    } else {
      Serial.println("Successful posted steps...");
      steps = 0;
      lastPostTimestamp = millis();
      digitalWrite(LED_WHITE, HIGH);
    }
    http.end();
  }

}
