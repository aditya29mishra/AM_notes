#include<Servo.h>
Servo m;
int r,p;
void setup()
{
  Serial.begin(9600);
  pinMode(A0,INPUT);
  pinMode(A1,OUTPUT);
  m.attach(9);
  r=analogRead(A0);
  analogWrite(A1,r);
}

void loop()
{
  Serial.println(r);
  for (p = 0; p <= 180; p += 1) { 
    m.write(p);      
    delay(15); 
    r=analogRead(A0);
    analogWrite(A1,r);
  }
  for (p = 180; p >= 0; p -= 1) {
    m.write(p); 
    delay(15); 
    r=analogRead(A0);
    analogWrite(A1,r);
  }
}