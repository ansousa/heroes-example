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
    document.getElementsByTagName("body")[0].classList.add("modal-open");
  }

  hide(){
    this.hidden = true;
    document.getElementsByTagName("body")[0].classList.remove("modal-open");
  }
}
