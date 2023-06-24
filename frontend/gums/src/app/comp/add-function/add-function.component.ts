import { Component, ElementRef, OnInit, Renderer2 } from '@angular/core';
import { IssueInfoService } from 'src/app/services/issue-info.service';
import anime from 'animejs/lib/anime.es.js';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { TopicsService } from 'src/app/services/topics.service';
import { Topics } from 'src/app/interface/topics';
import { UtilsService } from 'src/app/services/utils.service';
import { Router } from '@angular/router';
//import { Router } from '@angular/router';
//import addFunction from 'src/assets/js/addfunction.js';

interface Ml4Options {
  opacityIn: number[];
  scaleIn: number[];
  scaleOut: number;
  durationIn: number;
  durationOut: number;
  delay: number;
}



@Component({
  selector: 'app-add-function',
  templateUrl: './add-function.component.html',
  styleUrls: ['./add-function.component.css']
})
export class AddFunctionComponent implements OnInit{
  topics:Topics[]=[];
  functionForm:any=FormGroup;
  topicForm : any = FormGroup;
  ngOnInit(): void {
   // throw new Error('Method not implemented.');
   this.insertData();
   this.getTopics();
   this.addTopic();
   //this.TopicData();
   //this.newTopicsData();
  }
  constructor(private renderer: Renderer2, private el: ElementRef,
              private issueInfoService:IssueInfoService,
              private topicsService:TopicsService,
              private formBuilder:FormBuilder,
              private topicFormBuilder:FormBuilder,
              private utilsService:UtilsService,
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
    insertData(){
      this.functionForm = this.formBuilder.group({
        problem_name:[null,Validators.required],
        investigate_Notes:[null,Validators.required],
        investigate_recommendation:[null,Validators.required],
        topicData:new FormArray([]),
      })

    }

    submitInsertFunction(){
      var formData = this.functionForm.value;
      console.log("helllloooo");
      console.log(formData.problem_name)
      var data = {
        problem_name : formData.problem_name,
        investigate_Notes : formData.investigate_Notes,
        investigate_recommendation : formData.investigate_recommendation
      };
      this.issueInfoService.addIssueInfo(data).subscribe(
        response=>{
          console.log(response);
          console.log(response.topic_id);
          this.submitTopicDesc(response.topic_id);
        },
        error=>{
          console.log(error);
        }
      )
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

    topicData():FormArray{
      console.log(this.functionForm.get('topicData').value);
      return this.functionForm.get('topicData') as FormArray;

    }

    newTopic(): FormGroup {
      return this.formBuilder.group({
        info_ID: [null, Validators.required],
        desc: [null, Validators.required]
      });
    }

    addTopic(){
      this.topicData().push(this.newTopic())
      //console.log(this.topicData().value);
    }
    onSelectAudit(value:any){
      console.log(value);
    }
    submitTopicDesc(issueId:any){
      for(let i=0;i<this.topicData().length;i++){
        const obTopic = this.topicData().controls[i];
        console.log(obTopic.value.info_ID);
        console.log(obTopic.value.desc);
        const topic_id =this.formBuilder.group({
          'topic_id':new FormControl(obTopic.value.info_ID)
        });
        const info_ID = this.formBuilder.group({
          'info_ID':new FormControl(issueId)
        })
        var data ={
            "topic_id":obTopic.value.info_ID,
            "info_id": issueId,
            "investigate_notes":obTopic.value.desc,
        }
        this.utilsService.insertUtils(data).subscribe(
          response=>{
            console.log(response);
            this.router.navigateByUrl("/dashboard");

          },
          error=>{
            console.log(error);
          }
        )

      }
    }
}
