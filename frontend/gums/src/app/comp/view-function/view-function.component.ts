import { Component, ElementRef, OnInit, Renderer2 } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DataDetails } from 'src/app/interface/data-details';
import { functiondetails } from 'src/app/interface/utils-dto';
import { IssueInfoService } from 'src/app/services/issue-info.service';
import { UtilsService } from 'src/app/services/utils.service';


@Component({
  selector: 'app-view-function',
  templateUrl: './view-function.component.html',
  styleUrls: ['./view-function.component.css']
})
export class ViewFunctionComponent implements OnInit {
  utilData!: functiondetails;
  funtionName!:string;
  functionDesc!:string;
  functionSuggestion!:string;
  data:DataDetails[]=[];
  constructor(private renderer: Renderer2, private el: ElementRef,
              private routerActive:ActivatedRoute,
              private issueService:IssueInfoService,
              private utilsService:UtilsService)
              {

   }
  ngOnInit(): void {
    this.getDetails();
  }
  ngAfterViewInit() {
    this.togglefunction();
  }
  togglefunction()
  {
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

  getDetails(){
    const id = this.routerActive.snapshot.params['id'];
    this.issueService.getIssueById(id).subscribe
    (
      (data)=>{
              console.log("Data", data);
              this.funtionName=data.problem_name;
              this.functionDesc = data.investigate_Notes;
              this.functionSuggestion = data.investigate_recommendation;
              this.getTopics(id);

            },
            (error)=>
            {
              console.log(error);
            }
    )
  }
  getTopics(id: any) {
    this.utilsService.getTopicsById(id).subscribe(
      data=>{
        console.log(data);
        this.data=data;
      },
      error=>{
        console.log(error);
      }
    )
  }

  genrate()
  {
    const id = this.routerActive.snapshot.params['id'];
    this.utilsService.genrateReport(id).subscribe
    (
      data=>
      {
        console.log(data);
      },
      error=>
      {
        console.log(error)
      }
    )
  }


}
