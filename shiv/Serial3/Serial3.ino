int i=0;
void setup()
{
  pinMode(11,OUTPUT);
  pinMode(13,OUTPUT);
}
void loop()
{
  digitalWrite(12+(i%2==0),HIGH);
  delay(1000);
  digitalWrite(12+(i%2==0),LOW);
  delay(1000);
  i++;
}