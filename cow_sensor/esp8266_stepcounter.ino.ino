bool lastVal=false;
int steps=0;

void setup() {
  Serial.begin(115200);
  Serial.println("");
  pinMode(D3, INPUT);
}


void loop() {
  bool curVal = !digitalRead(D3);

  if (curVal != lastVal) {
    if (curVal == 0) {
      steps++;
      Serial.println(steps);
    }
    delay(500); //debounce
  }

  lastVal = curVal;
  delay(10);
}
