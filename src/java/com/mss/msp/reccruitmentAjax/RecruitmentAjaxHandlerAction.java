/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.reccruitmentAjax;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.mss.msp.util.ApplicationConstants;
import com.mss.msp.util.DataSourceDataProvider;
import com.mss.msp.util.ServiceLocator;
import com.opensymphony.xwork2.ActionSupport;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import com.mss.msp.util.ServiceLocatorException;

/**
 *
 * @author NagireddySeerapu
 */
public class RecruitmentAjaxHandlerAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

    public RecruitmentAjaxHandlerAction() {
    }
    // variable for addConsultant created by Aklakh
    private HttpServletResponse httpServletResponse;
    private HttpServletRequest httpServletRequest;
    private String resultMessage;
    private int orgId;
    private String consultantId;
    private String resultType;
    private String cnslt_email;
    private String cnslt_fstname;
    private String cnslt_gender;
    private String cnslt_homePhone;
    private String cnslt_add_date;
    private String cnslt_lstname;
    private String cnslt_dob;
    private String cnslt_mobileNo;
    private String cnslt_available;
    private String cnslt_midname;
    private String cnslt_mStatus;
    private int cnslt_lcountry;
    private String addAddressFlag;
    private String addConsult_Address;
    private String addConsult_Address2;
    private String addConsult_City;
    private String addConsult_Country;
    private int addConsult_State;
    private String addConsult_Zip;
    private String addConsult_Phone;
    private String currentAddressFlag;
    private String addConsult_CAddress;
    private String addConsult_CAddress2;
    private String addConsult_CCity;
    private String addConsult_CCountry;
    private int addConsult_CState;
    private String addConsult_CZip;
    private String addConsult_CPhone;
    private int cnslt_industry;
    private String cnslt_salary;
    private int cnslt_wcountry;
    private int cnslt_organization;
    private String cnslt_experience;
    private int cnslt_preferredState;
    private String cnslt_jobTitle;
    private String cnslt_workPhone;
    private String cnslt_referredBy;
    private int cnslt_pcountry;
    private String cnslt_description;
    private String cnslt_comments;
    private Map cnslt_WCountry;
    private Map organization;
    private Map industry;
    private int consult_id;
    private int UserSessionId;
    private String consult_attachment_created_date;
    //for tech review search
    private String requirementId;
    //using consult_id;
    private String interviewDate;
    private String empIdTechReview;
    private int conTechReviewId;
    private String reviewType;
    private String forwardedToId;
    private int consultantCheck;
    private String resourceType;
    private String reqId;
    private String conEmail;
    private int sessionOrgId;
    private String techReviewSeverity;
    private int techReviewQuestions;
    private String skillCategoryArry;
    private String examStatus;
    private String techExamType;
    private String gridDownload;
    private String requirementName;
    private String pdfHeaderName;
    private String withdrawComments;
    private String contactType;
    private String rejectionComments;
    private int createdByOrgId;
    private String gridDownloadFlag;

    public String getContactType() {
        return contactType;
    }

    public void setContactType(String contactType) {
        this.contactType = contactType;
    }

    public String getRejectionComments() {
        return rejectionComments;
    }

    public void setRejectionComments(String rejectionComments) {
        this.rejectionComments = rejectionComments;
    }

    public int getCreatedByOrgId() {
        return createdByOrgId;
    }

    public void setCreatedByOrgId(int createdByOrgId) {
        this.createdByOrgId = createdByOrgId;
    }

    public String getWithdrawComments() {
        return withdrawComments;
    }

    public void setWithdrawComments(String withdrawComments) {
        this.withdrawComments = withdrawComments;
    }

    public int getSessionOrgId() {
        return sessionOrgId;
    }

    public void setSessionOrgId(int sessionOrgId) {
        this.sessionOrgId = sessionOrgId;
    }

    public String getConEmail() {
        return conEmail;
    }

    public void setConEmail(String conEmail) {
        this.conEmail = conEmail;
    }

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    /**
     * **********************************************************************************
     *
     * Method: getConsultanceExistance() is to download The vendor form
     * attachment
     *
     * ************************************************************************************
     */
    public String getConsultanceExistance() {
        
         System.out.println("********************RecruitmentAjaxHandlerAction :: getConsultanceExistance() Action Start*********************");
        resultMessage = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {

                String resultNumber = "";
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));


                String result = com.mss.msp.util.DataSourceDataProvider.getInstance().getEmiltExistOrNot(this.getContactType(), this.getConEmail(), this.getSessionOrgId());



                String checkResult = "";


                if (result != null) {
                    String id[] = result.split("#");
                    resultNumber = 1 + "#" + id[1] + "#" + id[2] + "#" + id[3];
                    checkResult = com.mss.msp.util.DataSourceDataProvider.getInstance().getIsExistConsultantByReqId(this.getReqId(), id[0]);
                    int i = Integer.parseInt(checkResult);
                    if (i == 0) {
                        resultNumber = 2 + "#" + id[1] + "#" + id[2] + "#" + id[3];//file 
                    } else {
                        resultNumber = 3 + "#" + id[1] + "#" + id[2] + "#" + id[3];//already exist
                    }
                }

                //not valid email id

                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(resultNumber);
                resultMessage = null;
            } catch (Exception ex) {
                ex.printStackTrace();
                resultMessage = ERROR;
            }
        }
        System.out.println("********************RecruitmentAjaxHandlerAction :: getConsultanceExistance() Action End*********************");
        return resultMessage;
    }

    /**
     * *****************************************************************************
     * Date : May 5 2015
     *
     * Author : Aklakh Ahmad<mahmad@miraclesoft.com>
     *
     * doEmailVerify() method can be used to verify the existing email
     *
     * *****************************************************************************
     */
    public String doEmailVerify() {

        
        System.out.println("********************RecruitmentAjaxHandlerAction :: doEmailVerify() Action Start*********************");
        resultMessage = LOGIN;
        int result;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID) != null) {
            int sessionId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
            int sessionOrgId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString());
            try {
                result = DataSourceDataProvider.getInstance().checkConsultantLoginId(getConsultantId(), sessionId, sessionOrgId);

                if (result == 0) {
                    httpServletResponse.setContentType("text");
                    httpServletResponse.setCharacterEncoding("UTF-8");
                    httpServletResponse.getWriter().write(SUCCESS);
                } else {
                    httpServletResponse.setContentType("text");
                    httpServletResponse.setCharacterEncoding("UTF-8");
                    httpServletResponse.getWriter().write(ERROR);
                }
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);


            } catch (Exception ex) {
                httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            }
        }
         System.out.println("********************RecruitmentAjaxHandlerAction :: doEmailVerify() Action End*********************");
        return null;
    }

    /**
     * *****************************************************************************
     * Date : May 5 2015
     *
     * Author : Aklakh Ahmad<mahmad@miraclesoft.com>
     *
     * doAddnewConsultant() method can be used to add the new consultant on the
     * basic of organization id
     *
     * *****************************************************************************
     */
    public String doAddnewConsultant() {
        try {
           
            System.out.println("********************RecruitmentAjaxHandlerAction :: doAddnewConsultant() Action Start*********************");
            String oId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString();
            int orgid = Integer.valueOf(oId);
           
            setOrganization(DataSourceDataProvider.getInstance().getOrganizationByOrgId(orgid));
            setCnslt_WCountry(ServiceLocator.getLocationService().getCountriesNamesMap());        // getGeneralService().getCountriesNames());
            setIndustry(DataSourceDataProvider.getInstance().getIndustryName());
            int res = ServiceLocator.getRecruitmentAjaxHandlerService().getAddedConsultantDetails(this, orgid);
            httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            httpServletResponse.setHeader("Pragma", "no-cache");
            httpServletResponse.setDateHeader("Expires", 0);
            httpServletResponse.setContentType("text/html");
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.getWriter().write(res);

        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
          
        }
        System.out.println("********************RecruitmentAjaxHandlerAction :: doAddnewConsultant() Action End*********************");
        return null;
    }

    /**
     * *****************************************************************************
     * Date : 
     *
     * Author :
     *
     * Method: getAttachmentList() method can be used to get the attachment 
     *     
     * *****************************************************************************
     */
    public String getAttachmentList() {
       
          System.out.println("********************RecruitmentAjaxHandlerAction :: getAttachmentList() Action Start*********************");
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                String attachmentList = ServiceLocator.getRecruitmentAjaxHandlerService().getAttachmentDetails(this);
                int Response = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(attachmentList);

            } catch (Exception ex) {
              
                httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());

            }
        }
          System.out.println("********************RecruitmentAjaxHandlerAction :: getAttachmentList() Action End*********************");
        return null;
    }

    /**
     * *****************************************************************************
     * Date : May 19 2015
     *
     * Author : ramakrishna<lankireddy@miraclesoft.com>
     *
     * getConsultantTechReviews() method is used to add the consultant login
     * details
     *
     * *****************************************************************************
     */
    public String getConsultantTechReviews() {
        resultType = LOGIN;
        System.out.println("********************RecruitmentAjaxHandlerAction :: getConsultantTechReviews() Action Start*********************");
        String reponseString = "";
        try {
           
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {

                reponseString = ServiceLocator.getRecruitmentAjaxHandlerService().getConsultantTechReviews(this);
               
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(reponseString);

               
            } else {
                return resultType;
            }
        } catch (Exception e) {
            resultType = ERROR;
        }
          System.out.println("********************RecruitmentAjaxHandlerAction :: getConsultantTechReviews() Action End*********************");
        return null;
    }

    /**
     * *****************************************************************************
     * Date : May 19 2015
     *
     * Author : ramakrishna<lankireddy@miraclesoft.com>
     *
     * techReviewCommentsOverlay() method is used to get the tech review comments
     * 
     *
     * *****************************************************************************
     */
    public String techReviewCommentsOverlay() {
        resultType = LOGIN;
        System.out.println("********************RecruitmentAjaxHandlerAction :: techReviewCommentsOverlay() Action Start*********************");
        String reponseString = "";
        try {
           
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {

                reponseString = ServiceLocator.getRecruitmentAjaxHandlerService().techReviewCommentsOverlay(this);
              
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(reponseString);

                
            } else {
                return resultType;
            }
        } catch (Exception e) {
            resultType = ERROR;
        }
         System.out.println("********************RecruitmentAjaxHandlerAction :: techReviewCommentsOverlay() Action End*********************");
        return null;
    }

    /**
     * ****************************************************************************************
     *
     * @Created Date:05/18/2015
     * 
     * @Author:ramakrishna<lankireddy@miraclesoft.com>
     * 
     * @getTechReviewResultOnOverlay() method is used to get tech review details on overlay
     * 
     *
     *****************************************************************************************
     */
    public String getTechReviewResultOnOverlay() {
        resultType = LOGIN;
        System.out.println("********************RecruitmentAjaxHandlerAction :: getTechReviewResultOnOverlay() Action Start*********************");
        String reponseString = "";
        try {
          
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {

                reponseString = ServiceLocator.getRecruitmentAjaxHandlerService().getTechReviewResultOnOverlay(this);
               
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(reponseString);

                
            } else {
                return resultType;
            }
        } catch (Exception e) {
            resultType = ERROR;
        }
        System.out.println("********************RecruitmentAjaxHandlerAction :: getTechReviewResultOnOverlay() Action End*********************");
        return null;
    }
     /**
     * *************************************************************************************************
     *
     * @Created Date:08/18/2015
     * 
     * @Author:Jagan Chukkala<jchukkla@miraclesoft.com>
     * 
     * @questionsCount() method is used to get the questions count of particular skill of organization
     * 
     *
     ***************************************************************************************************
     */
    public String questionsCount() {
        resultType = LOGIN;
        String reponseString = "";
        try {
            System.out.println("********************RecruitmentAjaxHandlerAction :: questionsCount() Action Start*********************");
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
               
                int oId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString());
                reponseString = ServiceLocator.getRecruitmentAjaxHandlerService().questionsCount(this, oId);
               
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(reponseString);

               
            } else {
                return resultType;
            }
        } catch (Exception e) {
            resultType = ERROR;
        }
        System.out.println("********************RecruitmentAjaxHandlerAction :: questionsCount() Action End*********************");
        return null;
    }

    /**
     * *************************************************************************************************
     *
     * @Created Date:08/18/2015
     * 
     * @Author:Jagan Chukkala<jchukkla@miraclesoft.com>
     * 
     * @questionsCountCheck() method is used to get the check no of questions of skill of organization
     * based on severity
     *
     ***************************************************************************************************
     */
    public String questionsCountCheck() {
        resultType = LOGIN;
        String reponseString = "";
        try {
            System.out.println("********************RecruitmentAjaxHandlerAction :: questionsCountCheck() Action Start*********************");
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                int orgId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString());
                
                reponseString = ServiceLocator.getRecruitmentAjaxHandlerService().questionsCountCheck(this, orgId);
               
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(reponseString);

              
            } else {
                return resultType;
            }
        } catch (Exception e) {
            resultType = ERROR;
        }
         System.out.println("********************RecruitmentAjaxHandlerAction :: questionsCountCheck() Action End*********************");
        return null;
    }
     /**
     * *************************************************************************************************
     *
     * @Created Date:08/19/2015
     * 
     * @Author:Jagan Chukkala<jchukkla@miraclesoft.com>
     * 
     * @skillsQuestions() method is used to get the questions per skill of organization
     * based on severity
     *
     ***************************************************************************************************
     */
    public String skillsQuestions() {
        resultType = LOGIN;
        String reponseString = "";
        try {
            System.out.println("********************RecruitmentAjaxHandlerAction :: skillsQuestions() Action Start*********************");
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                int orgId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString());
               
                reponseString = ServiceLocator.getRecruitmentAjaxHandlerService().skillsQuestions(this, orgId);
               
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(reponseString);
                
              
            } else {
                return resultType;
            }
        } catch (Exception e) {
            resultType = ERROR;
        }
         System.out.println("********************RecruitmentAjaxHandlerAction :: skillsQuestions() Action End*********************");
        return null;
    }

    /**
     * *************************************
     *
     * @onlineExamStatusSave() method is used to set examStatus details of
     * consultant
     *
     *
     * @Author:jagan<jchukkala@miraclesoft.com>
     *
     * @Created Date:10/23/2015
     *
     **************************************
     */
    public String onlineExamStatusSave() {
        resultType = LOGIN;
        String reponseString = "";
        try {
          System.out.println("********************RecruitmentAjaxHandlerAction :: skillsQuestions() Action Start*********************");
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {

                reponseString = ServiceLocator.getRecruitmentAjaxHandlerService().saveOnlineExamStatus(this);
              
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(reponseString);

              
            } else {
                return resultType;
            }
        } catch (Exception e) {
            resultType = ERROR;
        }
        System.out.println("********************RecruitmentAjaxHandlerAction :: skillsQuestions() Action End*********************");
        return null;
    }

     /**
     *****************************************************************
     *
     * @doDownloadResults() method is used to download the  grid data
     * 
     *****************************************************************
     */
    public String doDownloadResults() throws Exception {
        try {
            resultType = SUCCESS;
            
            System.out.println("********************RecruitmentAjaxHandlerAction :: doDownloadResults() Action Start*********************");
            String accType = (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.TYPE_OF_USER).toString());
            ServletContext context = ServletActionContext.getServletContext();

            String contextPath = context.getRealPath(File.separator);
           
            if (!"".equals(getGridDownload())) {
                String data = DataSourceDataProvider.getInstance().getGridData(getGridDownload(), getGridDownloadFlag(), accType);
                setGridDownload(data);
              
                String[] s = getGridDownload().split("\\^");
                int columnCount = 0;
                for (int i = 0; i < s.length; i++) {
                   
                    String[] inner = s[0].split("\\|");
                  
                    columnCount = inner.length;
                    break;
                }
                PdfPTable table = new PdfPTable(columnCount);

                table.setWidthPercentage(100); 
            
                table = DataSourceDataProvider.getInstance().getITextPDFTable(getGridDownload(), table);

                ByteArrayOutputStream baos = new HeaderFooter().createPdf(table);

                String filename = getPdfHeaderName();
                String withoutWhitespace = StringUtils.deleteWhitespace(filename);
                filename = withoutWhitespace.concat(".pdf");
               
                httpServletResponse.setHeader("Content-Disposition", "attachment; filename=" + filename + "");
               
                httpServletResponse.setContentType("application/pdf");
                httpServletResponse.getOutputStream().write(((ByteArrayOutputStream) baos).toByteArray());
             
                baos.flush();
                baos.close();
            }

        } catch (Exception ex) {
            
        }
         System.out.println("********************RecruitmentAjaxHandlerAction :: doDownloadResults() Action End*********************");
        return null;
    }

    class HeaderFooter extends PdfPageEventHelper {

        /**
         * Alternating phrase for the header.
         */
        ServletContext context = ServletActionContext.getServletContext();
        String contextPath = context.getRealPath(File.separator);
        /**
         * Current page number (will be reset for every chapter).
         */
        int pagenumber;

        /**
         * Initialize one of the headers.
         *
         * @see com.itextpdf.text.pdf.PdfPageEventHelper#onOpenDocument(
         * com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document)
         */
        @Override
        public void onOpenDocument(PdfWriter writer, Document document) {
        }

        /**
         * Initialize one of the headers, based on the chapter title; reset the
         * page number.
         *
         * @see com.itextpdf.text.pdf.PdfPageEventHelper#onChapter(
         * com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document, float,
         * com.itextpdf.text.Paragraph)
         */
        @Override
        public void onChapter(PdfWriter writer, Document document,
                float paragraphPosition, Paragraph title) {
            pagenumber = 1;
        }

        /**
         * Increase the page number.
         *
         * @see com.itextpdf.text.pdf.PdfPageEventHelper#onStartPage(
         * com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document)
         */
        @Override
        public void onStartPage(PdfWriter writer, Document document) {
            try {
              
                Image image = Image.getInstance(contextPath + "/includes/images/logo30.png");
                Font green = new Font(FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.GREEN);

                DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                Date date = new Date();
                Chunk text = new Chunk("Document Generated On : " + dateFormat.format(date));

                Paragraph pre = new Paragraph(text);
                pre.setAlignment(Element.ALIGN_LEFT);
                document.add(pre);

                if (getRequirementName() != null) {
                    Chunk text1 = new Chunk("Requirement : " + getRequirementName());
                    Paragraph pre1 = new Paragraph(text1);
                    pre1.setAlignment(Element.ALIGN_RIGHT);
                    document.add(pre1);
                }

                Chunk greenText = new Chunk(getPdfHeaderName(), green);

                Paragraph preface = new Paragraph(greenText);
                preface.setAlignment(Element.ALIGN_CENTER);
                document.add(preface);



                image.scaleAbsolute(120f, 60f);
                document.add(image);


                Rectangle rect = writer.getBoxSize("art");

                
            } catch (DocumentException ex) {
                Logger.getLogger(RecruitmentAjaxHandlerAction.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(RecruitmentAjaxHandlerAction.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(RecruitmentAjaxHandlerAction.class.getName()).log(Level.SEVERE, null, ex);
            }
            pagenumber++;
        }

        /**
         * Adds the header and the footer.
         *
         * @see com.itextpdf.text.pdf.PdfPageEventHelper#onEndPage(
         * com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document)
         */
        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            Rectangle rect = writer.getBoxSize("art");

            ColumnText.showTextAligned(writer.getDirectContent(),
                    Element.ALIGN_CENTER, new Phrase(String.format("page %d", pagenumber)),
                    (rect.getLeft() + rect.getRight()) / 2, rect.getBottom() - 18, 0);
        }

        /**
         * Creates a PDF document.
         *
         * @param filename the path to the new PDF document
         * @throws DocumentException
         * @throws IOException
         * @throws SQLException
         */
        public ByteArrayOutputStream createPdf(PdfPTable table)
                throws IOException, DocumentException {
           
            Document document = new Document(PageSize.A4, 36, 36, 54, 54);
           
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter writer = PdfWriter.getInstance(document, baos);
            HeaderFooter event = new HeaderFooter();
          
            writer.setBoxSize("art", new Rectangle(36, 54, 559, 788));
            writer.setPageEvent(event);
          
            document.open();

            document.add(table);

            document.close();
            return baos;
        }
    }
 /**
     * ****************************************************************************
     *
     * @doWithdrawConsultant() method is used to withdraw the cosultant by vendor
     * 
     *
     *
     * @Author:manikanta eeralla<meeralla@miraclesoft.com>
     *
     * @Created Date:10/01/2016
     *
     *****************************************************************************
     */
    public String doWithdrawConsultant() {
        resultType = LOGIN;
        System.out.println("********************RecruitmentAjaxHandlerAction :: doWithdrawConsultant() Action Start*********************");
        try {
           
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                int reponseString = ServiceLocator.getRecruitmentAjaxHandlerService().doWithdrawConsultant(this);

                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(reponseString);

              
            } else {
                return resultType;
            }
        } catch (Exception e) {
            resultType = ERROR;
        }
         System.out.println("********************RecruitmentAjaxHandlerAction :: doWithdrawConsultant() Action End*********************");
        return null;
    }
    
    /**
     * ****************************************************************************
     *
     * @doDeclineConsultant() method is used to reject the cosultant by customer
     * 
     *
     *
     * @Author:manikanta eeralla<meeralla@miraclesoft.com>
     *
     * @Created Date:10/01/2016
     *
     *****************************************************************************
     */
    
    
    public String doDeclineConsultant() {
        resultType = LOGIN;
        String reponseString = "";
        try {
            System.out.println("********************RecruitmentAjaxHandlerAction :: doDeclineConsultant() Action Start*********************");
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                int reponse = ServiceLocator.getRecruitmentAjaxHandlerService().doDeclineConsultant(this);
                if (reponse > 1) {
                    reponseString = "Rejected Successfully";
                }
               
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(reponseString);

             
            } else {
                return resultType;
            }
        } catch (Exception e) {
            resultType = ERROR;
        }
             System.out.println("********************RecruitmentAjaxHandlerAction :: doDeclineConsultant() Action End*********************");
        return null;
    }

    /**
     * ******************************************************************
     *
     * @doDownloadXlsResults() method is used to download the xls file 
     * 
     *
     *
     * @Author:manikanta eeralla<meeralla@miraclesoft.com>
     *
     * @Created Date:10/01/2016
     *
     ********************************************************************
     */
    
    public String doDownloadXlsResults() throws IOException, WriteException, BiffException, ServiceLocatorException {
        System.out.println("********************RecruitmentAjaxHandlerAction :: doDownloadXlsResults() Action Start*********************");
        String filename = getPdfHeaderName();
        String accType = (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.TYPE_OF_USER).toString());
        filename = filename.concat(".xls");
        OutputStream out = null;
        httpServletResponse.setContentType("application/vnd.ms-excel");
        httpServletResponse.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
        int count;
        WritableWorkbook wworkbook;
        wworkbook = Workbook.createWorkbook(httpServletResponse.getOutputStream());
        WritableSheet wsheet = wworkbook.createSheet("First Sheet", 0);
        Label label;
        if (!"".equals(getGridDownload())) {
            String data = DataSourceDataProvider.getInstance().getGridData(getGridDownload(), getGridDownloadFlag(), accType);
            setGridDownload(data);
            WritableFont cellFont = new WritableFont(WritableFont.TIMES, 12);
            cellFont.setColour(Colour.PINK);

            WritableCellFormat cellFormat = new WritableCellFormat(cellFont);
            cellFormat.setBackground(Colour.BLUE);
        
            String[] s = getGridDownload().split("\\^");
            for (int i = 0; i < s.length; i++) {
               
                String ss = s[i];
                String[] inner = ss.split("\\|");
            
                for (int j = 0; j < inner.length; j++) {
                   
                    if (i == 0) {
                        label = new Label(j, i, inner[j], cellFormat);
                        wsheet.addCell(label);
                    } else {
                        label = new Label(j, i, inner[j]);
                        wsheet.addCell(label);
                    }
                }
            }
        }
        wworkbook.write();
        wworkbook.close();
        if (out != null) {
            out.close();
        }
         System.out.println("********************RecruitmentAjaxHandlerAction :: doDownloadXlsResults() Action End*********************");
        return null;
    }

    public void setServletRequest(HttpServletRequest httpServletRequest) {

        this.httpServletRequest = httpServletRequest;
    }

    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

    public HttpServletResponse getHttpServletResponse() {
        return httpServletResponse;
    }

    public void setHttpServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public String getConsultantId() {
        return consultantId;
    }

    public void setConsultantId(String consultantId) {
        this.consultantId = consultantId;
    }

    public String getCnslt_email() {
        return cnslt_email;
    }

    public void setCnslt_email(String cnslt_email) {
        this.cnslt_email = cnslt_email;
    }

    public String getCnslt_fstname() {
        return cnslt_fstname;
    }

    public void setCnslt_fstname(String cnslt_fstname) {
        this.cnslt_fstname = cnslt_fstname;
    }

    public String getCnslt_gender() {
        return cnslt_gender;
    }

    public void setCnslt_gender(String cnslt_gender) {
        this.cnslt_gender = cnslt_gender;
    }

    public String getCnslt_homePhone() {
        return cnslt_homePhone;
    }

    public void setCnslt_homePhone(String cnslt_homePhone) {
        this.cnslt_homePhone = cnslt_homePhone;
    }

    public String getCnslt_add_date() {
        return cnslt_add_date;
    }

    public void setCnslt_add_date(String cnslt_add_date) {
        this.cnslt_add_date = cnslt_add_date;
    }

    public String getCnslt_lstname() {
        return cnslt_lstname;
    }

    public void setCnslt_lstname(String cnslt_lstname) {
        this.cnslt_lstname = cnslt_lstname;
    }

    public String getCnslt_dob() {
        return cnslt_dob;
    }

    public void setCnslt_dob(String cnslt_dob) {
        this.cnslt_dob = cnslt_dob;
    }

    public String getCnslt_mobileNo() {
        return cnslt_mobileNo;
    }

    public void setCnslt_mobileNo(String cnslt_mobileNo) {
        this.cnslt_mobileNo = cnslt_mobileNo;
    }

    public String getCnslt_available() {
        return cnslt_available;
    }

    public void setCnslt_available(String cnslt_available) {
        this.cnslt_available = cnslt_available;
    }

    public String getCnslt_midname() {
        return cnslt_midname;
    }

    public void setCnslt_midname(String cnslt_midname) {
        this.cnslt_midname = cnslt_midname;
    }

    public String getCnslt_mStatus() {
        return cnslt_mStatus;
    }

    public void setCnslt_mStatus(String cnslt_mStatus) {
        this.cnslt_mStatus = cnslt_mStatus;
    }

    public int getCnslt_lcountry() {
        return cnslt_lcountry;
    }

    public void setCnslt_lcountry(int cnslt_lcountry) {
        this.cnslt_lcountry = cnslt_lcountry;
    }

    public String getAddAddressFlag() {
        return addAddressFlag;
    }

    public void setAddAddressFlag(String addAddressFlag) {
        this.addAddressFlag = addAddressFlag;
    }

    public String getAddConsult_Address() {
        return addConsult_Address;
    }

    public void setAddConsult_Address(String addConsult_Address) {
        this.addConsult_Address = addConsult_Address;
    }

    public String getAddConsult_Address2() {
        return addConsult_Address2;
    }

    public void setAddConsult_Address2(String addConsult_Address2) {
        this.addConsult_Address2 = addConsult_Address2;
    }

    public String getAddConsult_City() {
        return addConsult_City;
    }

    public void setAddConsult_City(String addConsult_City) {
        this.addConsult_City = addConsult_City;
    }

    public String getAddConsult_Country() {
        return addConsult_Country;
    }

    public void setAddConsult_Country(String addConsult_Country) {
        this.addConsult_Country = addConsult_Country;
    }

    public int getAddConsult_State() {
        return addConsult_State;
    }

    public void setAddConsult_State(int addConsult_State) {
        this.addConsult_State = addConsult_State;
    }

    public String getAddConsult_Zip() {
        return addConsult_Zip;
    }

    public void setAddConsult_Zip(String addConsult_Zip) {
        this.addConsult_Zip = addConsult_Zip;
    }

    public String getAddConsult_Phone() {
        return addConsult_Phone;
    }

    public void setAddConsult_Phone(String addConsult_Phone) {
        this.addConsult_Phone = addConsult_Phone;
    }

    public String getCurrentAddressFlag() {
        return currentAddressFlag;
    }

    public void setCurrentAddressFlag(String currentAddressFlag) {
        this.currentAddressFlag = currentAddressFlag;
    }

    public String getAddConsult_CAddress() {
        return addConsult_CAddress;
    }

    public void setAddConsult_CAddress(String addConsult_CAddress) {
        this.addConsult_CAddress = addConsult_CAddress;
    }

    public String getAddConsult_CAddress2() {
        return addConsult_CAddress2;
    }

    public void setAddConsult_CAddress2(String addConsult_CAddress2) {
        this.addConsult_CAddress2 = addConsult_CAddress2;
    }

    public String getAddConsult_CCity() {
        return addConsult_CCity;
    }

    public void setAddConsult_CCity(String addConsult_CCity) {
        this.addConsult_CCity = addConsult_CCity;
    }

    public String getAddConsult_CCountry() {
        return addConsult_CCountry;
    }

    public void setAddConsult_CCountry(String addConsult_CCountry) {
        this.addConsult_CCountry = addConsult_CCountry;
    }

    public int getAddConsult_CState() {
        return addConsult_CState;
    }

    public void setAddConsult_CState(int addConsult_CState) {
        this.addConsult_CState = addConsult_CState;
    }

    public String getAddConsult_CZip() {
        return addConsult_CZip;
    }

    public void setAddConsult_CZip(String addConsult_CZip) {
        this.addConsult_CZip = addConsult_CZip;
    }

    public String getAddConsult_CPhone() {
        return addConsult_CPhone;
    }

    public void setAddConsult_CPhone(String addConsult_CPhone) {
        this.addConsult_CPhone = addConsult_CPhone;
    }

    public int getCnslt_industry() {
        return cnslt_industry;
    }

    public void setCnslt_industry(int cnslt_industry) {
        this.cnslt_industry = cnslt_industry;
    }

    public String getCnslt_salary() {
        return cnslt_salary;
    }

    public void setCnslt_salary(String cnslt_salary) {
        this.cnslt_salary = cnslt_salary;
    }

    public int getCnslt_wcountry() {
        return cnslt_wcountry;
    }

    public void setCnslt_wcountry(int cnslt_wcountry) {
        this.cnslt_wcountry = cnslt_wcountry;
    }

    public int getCnslt_organization() {
        return cnslt_organization;
    }

    public void setCnslt_organization(int cnslt_organization) {
        this.cnslt_organization = cnslt_organization;
    }

    public String getCnslt_experience() {
        return cnslt_experience;
    }

    public void setCnslt_experience(String cnslt_experience) {
        this.cnslt_experience = cnslt_experience;
    }

    public int getCnslt_preferredState() {
        return cnslt_preferredState;
    }

    public void setCnslt_preferredState(int cnslt_preferredState) {
        this.cnslt_preferredState = cnslt_preferredState;
    }

    public String getCnslt_jobTitle() {
        return cnslt_jobTitle;
    }

    public void setCnslt_jobTitle(String cnslt_jobTitle) {
        this.cnslt_jobTitle = cnslt_jobTitle;
    }

    public String getCnslt_workPhone() {
        return cnslt_workPhone;
    }

    public void setCnslt_workPhone(String cnslt_workPhone) {
        this.cnslt_workPhone = cnslt_workPhone;
    }

    public String getCnslt_referredBy() {
        return cnslt_referredBy;
    }

    public void setCnslt_referredBy(String cnslt_referredBy) {
        this.cnslt_referredBy = cnslt_referredBy;
    }

    public int getCnslt_pcountry() {
        return cnslt_pcountry;
    }

    public void setCnslt_pcountry(int cnslt_pcountry) {
        this.cnslt_pcountry = cnslt_pcountry;
    }

    public String getCnslt_description() {
        return cnslt_description;
    }

    public void setCnslt_description(String cnslt_description) {
        this.cnslt_description = cnslt_description;
    }

    public String getCnslt_comments() {
        return cnslt_comments;
    }

    public void setCnslt_comments(String cnslt_comments) {
        this.cnslt_comments = cnslt_comments;
    }

    public Map getCnslt_WCountry() {
        return cnslt_WCountry;
    }

    public void setCnslt_WCountry(Map cnslt_WCountry) {
        this.cnslt_WCountry = cnslt_WCountry;
    }

    public Map getOrganization() {
        return organization;
    }

    public void setOrganization(Map organization) {
        this.organization = organization;
    }

    public Map getIndustry() {
        return industry;
    }

    public void setIndustry(Map industry) {
        this.industry = industry;
    }

    public int getConsult_id() {
        return consult_id;
    }

    public void setConsult_id(int consult_id) {
        this.consult_id = consult_id;
    }

    public int getUserSessionId() {
        return UserSessionId;
    }

    public void setUserSessionId(int UserSessionId) {
        this.UserSessionId = UserSessionId;
    }

    public String getConsult_attachment_created_date() {
        return consult_attachment_created_date;
    }

    public void setConsult_attachment_created_date(String consult_attachment_created_date) {
        this.consult_attachment_created_date = consult_attachment_created_date;
    }

    public String getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(String requirementId) {
        this.requirementId = requirementId;
    }

    public String getInterviewDate() {
        return interviewDate;
    }

    public void setInterviewDate(String interviewDate) {
        this.interviewDate = interviewDate;
    }

    public String getEmpIdTechReview() {
        return empIdTechReview;
    }

    public void setEmpIdTechReview(String empIdTechReview) {
        this.empIdTechReview = empIdTechReview;
    }

    public int getConTechReviewId() {
        return conTechReviewId;
    }

    public void setConTechReviewId(int conTechReviewId) {
        this.conTechReviewId = conTechReviewId;
    }

    public String getReviewType() {
        return reviewType;
    }

    public void setReviewType(String reviewType) {
        this.reviewType = reviewType;
    }

    public String getForwardedToId() {
        return forwardedToId;
    }

    public void setForwardedToId(String forwardedToId) {
        this.forwardedToId = forwardedToId;
    }

    public int getConsultantCheck() {
        return consultantCheck;
    }

    public void setConsultantCheck(int consultantCheck) {
        this.consultantCheck = consultantCheck;
    }

    public String getTechReviewSeverity() {
        return techReviewSeverity;
    }

    public void setTechReviewSeverity(String techReviewSeverity) {
        this.techReviewSeverity = techReviewSeverity;
    }

    public int getTechReviewQuestions() {
        return techReviewQuestions;
    }

    public void setTechReviewQuestions(int techReviewQuestions) {
        this.techReviewQuestions = techReviewQuestions;
    }

    public String getSkillCategoryArry() {
        return skillCategoryArry;
    }

    public void setSkillCategoryArry(String skillCategoryArry) {
        this.skillCategoryArry = skillCategoryArry;
    }

    public String getExamStatus() {
        return examStatus;
    }

    public void setExamStatus(String examStatus) {
        this.examStatus = examStatus;
    }

    public String getTechExamType() {
        return techExamType;
    }

    public void setTechExamType(String techExamType) {
        this.techExamType = techExamType;
    }

    public String getGridDownload() {
        return gridDownload;
    }

    public void setGridDownload(String gridDownload) {
        this.gridDownload = gridDownload;
    }

    public String getRequirementName() {
        return requirementName;
    }

    public void setRequirementName(String requirementName) {
        this.requirementName = requirementName;
    }

    public String getPdfHeaderName() {
        return pdfHeaderName;
    }

    public void setPdfHeaderName(String pdfHeaderName) {
        this.pdfHeaderName = pdfHeaderName;
    }

    public String getGridDownloadFlag() {
        return gridDownloadFlag;
    }

    public void setGridDownloadFlag(String gridDownloadFlag) {
        this.gridDownloadFlag = gridDownloadFlag;
    }
}
