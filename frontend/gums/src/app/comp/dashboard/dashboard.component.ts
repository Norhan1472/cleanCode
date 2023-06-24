import { IssueInfoDTO } from 'src/app/interface/issue-info-dto';
import { IssueInfoService } from './../../services/issue-info.service';
import { Component, ElementRef, OnInit, Renderer2 } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit{
  issues:IssueInfoDTO[]=[];
  ngOnInit(): void {
    this.getIssues();
  }
  constructor(private renderer: Renderer2, private el: ElementRef,
              private issueInfoService:IssueInfoService){}
  ngAfterViewInit() {
    const list:NodeListOf<HTMLElement> = this.el.nativeElement.querySelectorAll('.navigation li');

    list.forEach((item: HTMLElement) => {
      this.renderer.listen(item, 'mouseover', () => {
        list.forEach((li) => li.classList.remove('hovered'));
        item.classList.add('hovered');
      });
    });

    const toggle = this.el.nativeElement.querySelector('.toggle');
    const navigation = this.el.nativeElement.querySelector('.navigation');
    const main = this.el.nativeElement.querySelector('.main');

    this.renderer.listen(toggle, 'click', () => {
      navigation.classList.toggle('active');
      main.classList.toggle('active');
    });
  }

  getIssues(){
    this.issueInfoService.getAll().subscribe(
      response=>{
        this.issues=response;
        console.log(response);
      },
      error=>{
        console.log(error);
      }
    )
  }
  deleteIssue(id:number)
  {
    this.issueInfoService.deleteIssueId(id).subscribe
    (
      response=>
      {
        console.log(response);
        this.getIssues();
      }
      ,error=>
      {
        console.log(error);
      }
    )
  }


}
