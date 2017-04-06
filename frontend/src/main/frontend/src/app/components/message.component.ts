import { Component } from '@angular/core';

import { Message, MessageType } from '../models/message';

@Component({
  selector: 'message',
  templateUrl: './html/message.component.html',
  styleUrls: ['./css/message.component.css']
})
export class MessageComponent {
  messages: Message[] = [];
  MessageType: typeof MessageType = MessageType;

  add(message: Message){
    this.messages.push(message);
  }

  remove(index: number){
    this.messages.splice(index, 1);
  }
}
