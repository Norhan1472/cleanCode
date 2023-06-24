import { Component, ElementRef, Renderer2 } from '@angular/core';

@Component({
  selector: 'app-side-bar',
  templateUrl: './side-bar.component.html',
  styleUrls: ['./side-bar.component.css']
})
export class SideBarComponent {
  constructor(private renderer: Renderer2, private el: ElementRef){}
  ngAfterViewInit() {
    const list:NodeListOf<HTMLElement> = this.el.nativeElement.querySelectorAll('.navigation li');

    list.forEach((item: HTMLElement) => {
      if (item) {
        this.renderer.listen(item, 'mouseover', () => {
          list.forEach((li) => li.classList.remove('hovered'));
          item.classList.add('hovered');
        });
      }
    });

    const toggle = this.el.nativeElement.querySelector('.toggle');
    const navigation = this.el.nativeElement.querySelector('.navigation');
    const main = this.el.nativeElement.querySelector('.main');

    if (toggle) {
      this.renderer.listen(toggle, 'click', () => {
        navigation.classList.toggle('active');
        main.classList.toggle('active');
      });
    }
  }
}
