export class Image {
  id: number;
  extension: String;
  data: String;
  constructor(id: number, extension: String, data: String){
    this.id = id;
    this.extension = extension;
    this.data = data;
  }
}
