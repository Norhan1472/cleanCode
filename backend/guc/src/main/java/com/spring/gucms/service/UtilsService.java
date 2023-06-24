package com.spring.gucms.service;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.spring.gucms.dto.DataDetails;
import com.spring.gucms.dto.IssueInfoDTO;
import com.spring.gucms.dto.TopicsDTO;
import com.spring.gucms.dto.UtilsDTO;
import com.spring.gucms.entity.IssueInfo;
import com.spring.gucms.entity.Topics;
import com.spring.gucms.entity.Utils;
import com.spring.gucms.repository.UtilsRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.css.RGBColor;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class UtilsService {

    private UtilsRepository utilsRepository;

    private TopicService topicService;
    private IssueInfoService issueInfoService;

    // Define methods for working with the Utils entity

    public Utils updateAuditRole(Long topicId, Map<String,Object>requestMap) {
        //Optional<Utils> existingUtils = utilsRepository.findById(roleId);
        List<Utils> utils = getTopicsById(topicId);
        utils.get(0).getAuditTopic().setProblem_name((String) requestMap.get("problem_name"));
        utils.get(0).getAuditTopic().setInvestigate_Notes((String) requestMap.get("investigation_notes"));
        utils.get(0).getAuditTopic().setInvestigate_recommendation((String) requestMap.get("investigation_recommendation"));

        if (utils.size()!=0) {
           // utilToUpdate.setInvestigate_notes(updatedRole.getInvestigate_notes());
            //utilToUpdate.setInvestigate_recommendation(updatedRole.getInvestigate_recommendation());
            /*
            utilToUpdate.setAuditTopic(updatedRole.getAuditTopic());
            utilToUpdate.setAuditTopicRoleInfo(updatedRole.getAuditTopicRoleInfo());

            utilToUpdate.setCreated_date(updatedRole.getCreated_date());
            utilToUpdate.setCreated_user_id(updatedRole.getCreated_user_id());
            utilToUpdate.setLast_updated_date(LocalDateTime.now());
            utilToUpdate.setLast_updated_user_id(updatedRole.getLast_updated_user_id());*/
            /*IssueInfo issue = utilToUpdate.getAuditTopic();
            issue.setProblem_name((String) requestMap.get("problem_name"));
            issue.setInvestigate_Notes((String) requestMap.get("investigation_notes"));
            issue.setInvestigate_recommendation((String) requestMap.get("investigation_recommendation"));
            utilToUpdate.setAuditTopic(issue);
            Topics topics = utilToUpdate.getAuditTopicRoleInfo();
           // utilToUpdate.get
            utilToUpdate.setInvestigate_notes();
            utilToUpdate.setInvestigate_recommendation();*/
           // for(int i=0;i< utils.size();i++){
            if(requestMap.containsKey("topic_id")){
                long id = Long.valueOf((String) requestMap.get("topic_id"));
                Utils util= utilsRepository.getByTopicIdAndAuditId(topicId,id);
                util.setInvestigate_notes((String) requestMap.get("investigation_notes_topic"));
                util.setInvestigate_recommendation((String) requestMap.get("investigation_recommendation_topic"));
                utilsRepository.save(util);
            }

         //   }
            return utilsRepository.save(utils.get(0));
        } else {
            throw new ResourceNotFoundException("Audit Role not found with id: " + topicId);
        }
    }


    public List<Utils> getAllUtils() {
        List<Utils>util = utilsRepository.findAll();
        System.out.println("kkkkttt");
        System.out.println(util.get(0).getAuditTopic().getProblem_name());

        return util;
    }

    public Optional<Utils> getUtilsById(Long id) {
        return utilsRepository.findById(id);
    }

    public Utils saveUtils(Map<String,Object> requestMap) {
        Utils util = new Utils();
        IssueInfo issue = new IssueInfo();
        System.out.println(String.valueOf( requestMap.get("info_id")));
        issue.setTopic_id(Long.valueOf(String.valueOf( requestMap.get("info_id"))));
        Topics topic = new Topics();
        topic.setInfo_ID(Long.valueOf((String)requestMap.get("topic_id")));
        issue.getTopics_ids().add(topic);
        util.setAuditTopic(issue);
        Topics topic2 =new Topics();
        topic2.setInfo_ID(Long.valueOf((String)requestMap.get("topic_id")));
        util.setAuditTopicRoleInfo(topic2);
        util.setInvestigate_notes((String) requestMap.get("investigate_notes"));
        //System.out.println(utils);
        System.out.println("in utils");
        //System.out.println(utils.getAuditTopicRoleInfo().getInfo_ID());
        return utilsRepository.save(util);
    }

    public void deleteUtils(Long id) {
        utilsRepository.deleteById(id);
    }
    public List<UtilsDTO>getUtilsDto(){
        List<Utils>util = utilsRepository.findAll();
        List<UtilsDTO>dto = new ArrayList<>();
        for(int i=0;i<util.size();i++){
            UtilsDTO utilDto = new UtilsDTO();
            utilDto.setRole_ID(util.get(i).getRole_ID());
            utilDto.setAuditTopic(util.get(i).getAuditTopic().getProblem_name());
            utilDto.setAuditTopicRoleInfo(util.get(i).getAuditTopicRoleInfo().getInfoName());
            utilDto.setInvestigate_notes(util.get(i).getInvestigate_notes());
            utilDto.setInvestigate_recommendation(util.get(i).getInvestigate_recommendation());
            dto.add(utilDto);
        }
        return dto;
    }

    public  UtilsDTO getUtilsDtoById(Long id) {
        Utils util = utilsRepository.findById(id).get();
        UtilsDTO utilDto = new UtilsDTO();
        utilDto.setRole_ID(util.getRole_ID());
        utilDto.setAuditTopic(util.getAuditTopic().getProblem_name());
        utilDto.setAuditTopicRoleInfo(util.getAuditTopicRoleInfo().getInfoName());
        utilDto.setInvestigate_notes(util.getInvestigate_notes());
        utilDto.setInvestigate_recommendation(util.getInvestigate_recommendation());
        return utilDto;
    }

    public ResponseEntity<Object> functionReport(Long topicId) throws IOException, DocumentException {
        System.out.println("oooppp");
        System.out.println(topicId);
        List<DataDetails>dataDetails = getByTopicId(topicId);
        IssueInfo issue = issueInfoService.getIssueInfoById(topicId);
        String fileName = getUUID();
        Document document = new Document();
        PdfWriter writer =  PdfWriter.getInstance(document,new FileOutputStream(fileName+".pdf"));
        document.open();
        Paragraph paragraph = new Paragraph("Function Name : "+issue.getProblem_name(),getFont("header"));
        paragraph.setAlignment(Element.ALIGN_LEFT);
        document.add(paragraph);
        paragraph = new Paragraph("\nNote Description ->>\n",getFont("header"));
        paragraph.setAlignment(Element.ALIGN_LEFT);
        document.add(paragraph);
        paragraph = new Paragraph(issue.getInvestigate_Notes(),getFont("data"));
        paragraph.setAlignment(Element.ALIGN_LEFT);
        document.add(paragraph);//Suggagtion Section ->>

        paragraph = new Paragraph("Suggestion Section ->>\n",getFont("header"));
        paragraph.setAlignment(Element.ALIGN_LEFT);
        document.add(paragraph);
        paragraph = new Paragraph(issue.getInvestigate_recommendation(),getFont("data"));
        paragraph.setAlignment(Element.ALIGN_LEFT);
        document.add(paragraph);

        paragraph = new Paragraph("\n\nTopics\n\n",getFont("header"));
        paragraph.setAlignment(Element.ALIGN_LEFT);
        document.add(paragraph);
        List<DataDetails>data = getByTopicId(topicId);
        for(int i=0;i<data.size();i++){
            paragraph = new Paragraph("TopicName : "+data.get(i).getTopicName(),getFont("header"));
            paragraph.setAlignment(Element.ALIGN_LEFT);
            document.add(paragraph);

            paragraph = new Paragraph("Topic Description ->>\n ",getFont("header"));
            paragraph.setAlignment(Element.ALIGN_LEFT);
            document.add(paragraph);

            paragraph = new Paragraph(data.get(i).getTopicDescription()+"\n\n",getFont("data"));
            paragraph.setAlignment(Element.ALIGN_LEFT);
            document.add(paragraph);
        }
        document.close();
        return new ResponseEntity<>("Successfully report", HttpStatus.OK);
    }
    private String getUUID(){
        Date date = new Date();
        long time = date.getTime();
        return "function-"+time;
    }
    private Font getFont(String dataFont) throws DocumentException, IOException {
        BaseColor b=new BaseColor(42,33,133);
        switch (dataFont){
            case("header"):
                Font fontHeader = new Font(Font.FontFamily.TIMES_ROMAN,20,Font.BOLD,b);
                fontHeader.setStyle(Font.BOLD);
                return fontHeader;
            case("data"):
                // Font fontData = FontFactory.getFont(,11,BaseColor.BLACK);
                Font fontData = new Font(Font.FontFamily.TIMES_ROMAN,18,Font.NORMAL,BaseColor.BLACK);
                return fontData;
            default:
                return new Font();

        }

    }


    public List<DataDetails> getByTopicId(Long topicId) {
        System.out.println("killll");
        System.out.println(topicId);
        List<Utils> util=utilsRepository.getByTopicId(topicId);
        List<DataDetails>details = new ArrayList<>();
        System.out.println("hhh");
        for(int i=0;i< util.size();i++){
            //Topics
            System.out.println(util.get(i).getAuditTopicRoleInfo().getInfo_ID());
            DataDetails data = new DataDetails();
            data.setTopicId(util.get(i).getAuditTopicRoleInfo().getInfo_ID());
            data.setTopicName(util.get(i).getAuditTopicRoleInfo().getInfoName());
            data.setTopicDescription(util.get(i).getInvestigate_notes());
            details.add(data);
        }
        return details;

    }
    public List<Utils>getTopicsById(long id){
        System.out.println("kkkllllpp");
        List<Utils> util=utilsRepository.getByTopicId(id);
        System.out.println("jjjjkkkk");
        for(int i=0;i<util.size();i++){
            System.out.println(util.get(i).getAuditTopic().getProblem_name());
        }
        return util;
    }
}


