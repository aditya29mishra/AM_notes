int i=2;
void setup(){
}
void loop(){
  int c=0;
  for(int j=1;j<i;j++){
    if((i-1)%j==0)
      c++;
  }
  if(c<=2){
    digitalWrite(i,HIGH);
    delay(1000);
    digitalWrite(i,LOW);
    delay(1000);
  }
  i++;
}