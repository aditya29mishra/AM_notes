int cm=0,l1=13,l2=12,l3=11;

long readUltrasonicDistance(int triggerPin, int echoPin)
{
  pinMode(triggerPin, OUTPUT);
  digitalWrite(triggerPin, LOW);
  delayMicroseconds(2);
  digitalWrite(triggerPin, HIGH);
  delayMicroseconds(10);
  digitalWrite(triggerPin, LOW);
  pinMode(echoPin, INPUT);
  return pulseIn(echoPin, HIGH);
}

void setup()
{
}

void loop()
{
  cm = 0.01723 * readUltrasonicDistance(7, 7);
  if(cm<20){
    digitalWrite(l1,HIGH);
    digitalWrite(l2,LOW);
    digitalWrite(l3,LOW);
  }
  else if(20<cm && cm<45){
    digitalWrite(l1,LOW);
    digitalWrite(l2,HIGH);
    digitalWrite(l3,LOW);
  }
  else{
    digitalWrite(l1,LOW);
    digitalWrite(l2,LOW);
    digitalWrite(l3,HIGH);
  }
  delay(100);
}