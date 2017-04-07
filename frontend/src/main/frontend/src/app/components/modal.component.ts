import { Component } from '@angular/core';

@Component({
  selector: 'modal',
  templateUrl: './html/modal.component.html',
  styleUrls: ['./css/modal.component.css']
})
export class ModalComponent {
  hidden: Boolean = true;

  show(){
    this.hidden = false;
  }

  hide(){
    this.hidden = true;
  }
}
