export enum MessageType{
  success,
  error
}

export class Message {
  public message: string;
  public type: MessageType;
  constructor(type: MessageType, message: string){
    this.message = message;
    this.type = type;
  }

}
