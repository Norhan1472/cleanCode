import { Component, ElementRef, OnInit, Renderer2 } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import anime from 'animejs/lib/anime.es.js';
import { Topics } from 'src/app/interface/topics';
import { IssueInfoService } from 'src/app/services/issue-info.service';
import { TopicsService } from 'src/app/services/topics.service';
import { UtilsService } from 'src/app/services/utils.service';
interface Ml4Options {
  opacityIn: number[];
  scaleIn: number[];
  scaleOut: number;
  durationIn: number;
  durationOut: number;
  delay: number;
}

@Component({
  selector: 'app-edit-function',
  templateUrl: './edit-function.component.html',
  styleUrls: ['./edit-function.component.css']
})


export class EditFunctionComponent implements OnInit{
  functionForm:any=FormGroup;
  topics:Topics[]=[];
  ngOnInit(): void {
    this.formData();
    this.getData();
    this.getTopics();
  }
  constructor(private renderer: Renderer2, private el: ElementRef,
              private formBuilder:FormBuilder,
              private issueService:IssueInfoService,
              private routerActive:ActivatedRoute,
              private utilsService:UtilsService,
              private topicsService:TopicsService,
              private router:Router){}
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
  fileJavaScript(){
    const ml4: Ml4Options = {
      opacityIn: [0, 1],
      scaleIn: [0.2, 1],
      scaleOut: 3,
      durationIn: 800,
      durationOut: 600,
      delay: 500
    };

      anime.timeline({loop: true})
       .add({
         targets: '.ml4 .letters-1',
         opacity: ml4.opacityIn,
         scale: ml4.scaleIn,
         duration: ml4.durationIn
       }).add({
         targets: '.ml4 .letters-1',
         opacity: 0,
         scale: ml4.scaleOut,
         duration: ml4.durationOut,
         easing: "easeInExpo",
         delay: ml4.delay
       }).add({
         targets: '.ml4 .letters-2',
         opacity: ml4.opacityIn,
         scale: ml4.scaleIn,
         duration: ml4.durationIn
       }).add({
         targets: '.ml4 .letters-2',
         opacity: 0,
         scale: ml4.scaleOut,
         duration: ml4.durationOut,
         easing: "easeInExpo",
         delay: ml4.delay
       }).add({
         targets: '.ml4 .letters-3',
         opacity: ml4.opacityIn,
         scale: ml4.scaleIn,
         duration: ml4.durationIn
       }).add({
         targets: '.ml4 .letters-3',
         opacity: 0,
         scale: ml4.scaleOut,
         duration: ml4.durationOut,
         easing: "easeInExpo",
         delay: ml4.delay
       }).add({
         targets: '.ml4',
         opacity: 0,
         duration: 500,
         delay: 500
       });
  }
  formData(){
    this.functionForm = this.formBuilder.group({
      problem_name:[null,Validators.required],
      investigate_Notes:[null,Validators.required],
      investigate_recommendation:[null,Validators.required],
      topicData:new FormArray([]),
    })
  }
  getData2(){
    const id = this.routerActive.snapshot.params["id"];
    this.issueService.getIssueById(id).subscribe(
      data=>{
        console.log("data here");
        console.log(data);
        this.functionForm.value.problem_name=data.problem_name;
        this.functionForm.value.investigate_Notes=data.investigate_Notes;
        this.functionForm.value.investigate_recommendation=data.investigate_recommendatio;
        console.log(this.functionForm.value.problem_name);
         /* this.functionForm.setValue({
            problem_name:data.problem_name,
            investigate_Notes:data.investigate_Notes,
            investigate_recommendation:data.investigate_recommendation,
            topicData:new FormArray([]),
          });*/
         // this.getTopicsData(id);
      }
    )
  }
  getData() {
    const id = this.routerActive.snapshot.params["id"];
    this.issueService.getIssueById(id).subscribe(
      data => {
        console.log("data here");
        console.log(data);
        this.functionForm.setValue({
          problem_name: data.problem_name,
          investigate_Notes: data.investigate_Notes,
          investigate_recommendation: data.investigate_recommendation,
          topicData: [],
        });
      }
    )
    this.getTopicsData(id);
  }

  submitInsertFunction(){
    const id = this.routerActive.snapshot.params["id"];
   var formInfo = this.functionForm.value;
   console.log("kkk");
   console.log(formInfo.topicData);
    (formInfo.topicData).forEach(
      (      item: any)=>{
          console.log(item);

   var info={
    problem_name:formInfo.problem_name,
    investigation_notes:formInfo.investigate_Notes,
    investigation_recommendation:formInfo.investigate_recommendation,
    topic_id:(item.topicId).toString(),
    investigation_notes_topic:item.topicName,
    investigation_recommendation_topic:item.topicDescription,
    }
    console.log("llkkll");
    console.log(info);
    this.utilsService.updateUtils(info,id).subscribe(
      data=>{
        console.log(data);
      },
      error=>{
        console.log(error);
      }
    )
  }
  )
 // this.router.navigateByUrl("/dashboard");
  }
  getTopicsData(id: any) {
    this.utilsService.getTopicsById(id).subscribe(
      data=>{
        console.log(data);
      const topicDataArray = this.functionForm.get('topicData') as FormArray;
      data.forEach((data1: { topicId: any; topicName: any; topicDescription: any; })=>{
        const group = this.formBuilder.group({
          topicId:[data1.topicId],
          topicName:[data1.topicName],
          topicDescription:[data1.topicDescription],
        });
        topicDataArray.push(group);
      })
      this.functionForm.patchValue({
        topicData: topicDataArray.value
      });

    },
    error=>{
      console.log(error);
    }
       /* console.log(data);
        data.forEach((data1: { topicId: any; topicName: any; topicDescription: any; })=>{
          const group = new FormGroup({
            topicId:new FormControl(data1.topicId),
            topicName:new FormControl(data1.topicName),
            topicDescription:new FormControl(data1.topicDescription),
          });

        (this.functionForm.get('topicData') as FormArray).push(group);
        })*/
        /*data.forEach((item: { name: any; description: any; }) => {
          const group = new FormGroup({
            name: new FormControl(item.name),
            description: new FormControl(item.description)
          });
          (this.functionForm.get('topicData') as FormArray).push(group);
        });*/

    )
  }
  onSelectAudit(auditInfo:any){

  }
  getTopics(){
    this.topicsService.getAllTopics().subscribe(
      response=>{
        console.log(response);
        this.topics = response;
      },
      error=>{
        console.log(error);
      }
    )
  }
}
