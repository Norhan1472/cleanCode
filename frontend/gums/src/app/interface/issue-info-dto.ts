export class IssueInfoDTO {
  topic_id!: number;
  problem_name!: string;
  investigate_recommendation!: string;
  investigate_Notes!: string;
  infoName:string[]=[];
  created_date!:Date;
  last_updated_date!:Date;
}
